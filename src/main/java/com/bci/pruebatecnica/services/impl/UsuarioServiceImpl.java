package com.bci.pruebatecnica.services.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.bci.pruebatecnica.data.dataAccess.IPhoneDataAccess;
import com.bci.pruebatecnica.data.dataAccess.IUserDataAcces;
import com.bci.pruebatecnica.data.dto.RequestPhone;
import com.bci.pruebatecnica.data.dto.RequestUser;
import com.bci.pruebatecnica.data.dto.ResponseUser;
import com.bci.pruebatecnica.data.dto.UserDto;
import com.bci.pruebatecnica.data.entity.Phone;
import com.bci.pruebatecnica.services.IUsuarioService;
import com.bci.pruebatecnica.utils.Fecha;
import com.bci.pruebatecnica.utils.Mapper;
import com.bci.pruebatecnica.utils.Validador;



@Service
public class UsuarioServiceImpl implements IUsuarioService {
	
	private static final Logger logger = LoggerFactory.getLogger(UsuarioServiceImpl.class);
	
	@Autowired
	private IUserDataAcces iUserDataAcces;
	
	@Autowired
	private IPhoneDataAccess iPhoneDataAccess;
	
	private static String key;
	
	@Value("${jwt.secret}")
	public void setKEY_SECRET(String kEY_SECRET) {
		key = kEY_SECRET;
	}



	/**
	 *  Metodo donde se guarda la información del usuario
	 *  
	 * @param RequestUser  
	 * @return ResponseUser
	 * @exception Exception, IllegalArgumentException, NullPointerException 
	 */

	@Override
	public ResponseUser saveUser(RequestUser reqUser) throws Exception, IllegalArgumentException, NullPointerException {
		long idUser = 0;
		LocalDateTime dateTime = LocalDateTime.now();
		try {
			
			/*validar el correo que cumpla con el formato*/
			validarCorreo(reqUser.getEmail());
			
			/*valida la clave del usuario*/
			validarPassword(reqUser.getPassword());
			
			/*analizar si existe el correo en la base de datos*/
			UserDto userDto = iUserDataAcces.findByEmail(reqUser.getEmail());
			if(userDto != null && userDto.isActive()) 
				throw new IllegalArgumentException("el usuario esta conectado el correo:".concat(userDto.getEmail()));
			
			/*crear objeto de userDto */
			if(userDto == null) {
				userDto = new UserDto(reqUser.getName(),reqUser.getEmail(),reqUser.getPassword(),dateTime,dateTime,dateTime, true);
				idUser = iUserDataAcces.saveUser(userDto);
			} 
			
			/*actualizacion de datos cuando existe y este desactivado */
			if(!userDto.isActive()) {
				idUser = userDto.getIdUser();
				userDto.setActive(true);
				userDto.setModified(dateTime);
				iUserDataAcces.saveUser(userDto);				
			}
			
		
			/*carga la lista de phones y se verifica el ingresos de los numeros*/
			 List<Phone>  listPhones = crearListaEntityPhone(reqUser.getPhones(),idUser);


			iPhoneDataAccess.saveAll(listPhones); 

			
			/*crear token en base id generado al guardar*/
			String token = Validador.getJWTToken(String.valueOf(idUser), key);
			
			
			return new ResponseUser(idUser, 
					                Fecha.getLocalDateTimeString(userDto.getCreateDate()),
					                Fecha.getLocalDateTimeString(userDto.getModified()),
					                Fecha.getLocalDateTimeString(userDto.getLastLogin()),
					                token,
					                userDto.isActive());
			
		}catch (NullPointerException e) {
			throw e;
		}catch (IllegalArgumentException e) {
			throw e;
		}catch (Exception e) {
			logger.error("ERROR - [UsuarioServiceImpl -> Metodo - getUserById] ", e);
			throw e;
		}
	}
	
	
	/**
	 *  Metodo que obtiene la información de usuario
	 *  
	 * @param id  
	 * @return ResponseUser
	 * @exception Exception or NullPointerException
	 */
	@Override
	public ResponseUser getUserById(long id) throws Exception, NullPointerException {
		try {
			UserDto userDto = iUserDataAcces.findById(id);
				return new ResponseUser(userDto.getIdUser(),
					        Fecha.getLocalDateTimeString(userDto.getCreateDate()),
					        Fecha.getLocalDateTimeString(userDto.getModified()),
					        Fecha.getLocalDateTimeString(userDto.getLastLogin()),
					        Validador.getJWTToken(String.valueOf(userDto.getIdUser()),key),
					        userDto.isActive()
							);

			
		}catch (NullPointerException e) {
			throw e;
		}catch (Exception e) {
			logger.error("ERROR - [UsuarioServiceImpl -> Metodo - getUserById] ", e);
			throw e;
		}
	}
	
	/**
	 *  Metodo que actualizar información del usuario
	 *  
	 * @param RequestUser  
	 * @param id
	 * @return boolean
	 * @exception Exception or IllegalArgumentException or NullPointerException
	 */
	@Override
	public boolean updateUser(RequestUser reqUser, long id) throws Exception, IllegalArgumentException, NullPointerException{
		LocalDateTime dateTime = LocalDateTime.now();
		try {
			/*validar el correo que cumpla con el formato*/
			validarCorreo(reqUser.getEmail());
			
			/*valida la clave del usuario*/
			validarPassword(reqUser.getPassword());

			
			UserDto userDto = iUserDataAcces.findById(id);
			
			userDto.setName(reqUser.getName());
			userDto.setEmail(reqUser.getName());
			userDto.setPassword(reqUser.getPassword());
			userDto.setModified(dateTime);
		
			return iUserDataAcces.saveUser(userDto) !=0 ? true : false;
		}catch (NullPointerException e) {
			throw e;
		}catch (IllegalArgumentException e) {
			throw e;
		}catch (Exception e) {
			logger.error("ERROR - [UsuarioServiceImpl -> Metodo - updateUser] ", e);
			throw e;
		}
	}
	
	/**
	 *  Metodo que cierra la sesión del usuario
	 *  
	 * @param id
	 * @return boolean
	 * @exception Exception or NullPointerException
	 */
	@Override
	public boolean logOutUser(long id) throws Exception, NullPointerException {
		LocalDateTime dateTime = LocalDateTime.now();
		try {
		
			UserDto userDto = iUserDataAcces.findById(id);
			userDto.setLastLogin(dateTime);
			userDto.setActive(false);
			if(iUserDataAcces.saveUser(userDto)!=0)
				return true;
			
		}catch (NullPointerException e) {
			throw e;
		}catch (IllegalArgumentException e) {
			throw e;
		}catch (Exception e) {
			logger.error("ERROR - [UsuarioServiceImpl -> Metodo - logOutUser] ", e);
			throw e;
		}
		return false;
	}
	
	
	private List<Phone> crearListaEntityPhone(ArrayList<RequestPhone> listaPhones, long idUser){
			return Mapper.evaluarPhones(listaPhones, idUser, phoneRq -> phoneRq.getCityCode() != null && 
					  phoneRq.getContryCode() !=null && 
					  phoneRq.getNumber() !=null);				

	}
	
	
	private void validarCorreo(String email) {
		String mensaje = Validador.validarEmail(email);
		if(mensaje !=null) 
			throw new IllegalArgumentException(mensaje);
		
	}
	private void validarPassword(String password) {
		String mensaje = Validador.validarPassword(password);
		if(mensaje !=null) 
			throw new IllegalArgumentException(mensaje);
		
	}
	


}
