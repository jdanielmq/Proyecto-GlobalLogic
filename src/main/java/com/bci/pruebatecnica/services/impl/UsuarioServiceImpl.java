package com.bci.pruebatecnica.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bci.pruebatecnica.data.dataAccess.IPhoneDataAccess;
import com.bci.pruebatecnica.data.dataAccess.IUserDataAcces;
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
	
	
	/**
	 *  Metodo donde se guarda la información del usuario
	 *  
	 * @param RequestUser  
	 * @return ResponseUser
	 * @exception Exception, IllegalArgumentException, NullPointerException 
	 */

	@Override
	public ResponseUser saveUser(RequestUser reqUser) throws Exception, IllegalArgumentException, NullPointerException {
		String mensaje = null;
		long idUser = 0;
		LocalDateTime dateTime = LocalDateTime.now();
		try {
			
			/*validar el correo que cumpla con el formato*/
			mensaje = Validador.validarEmail(reqUser.getEmail());
			if(mensaje !=null) throw new IllegalArgumentException(mensaje);
			
			/*valida la clave del usuario*/
			mensaje = Validador.validarPassword(reqUser.getPassword());
			if(mensaje !=null) throw new IllegalArgumentException(mensaje);
			
			/*analizar si existe el correo en la base de datos*/
			UserDto userDto = iUserDataAcces.findByEmail(reqUser.getEmail());
			if(userDto != null && userDto.isActive()) 
				throw new IllegalArgumentException("el usuario esta conectado el correo:".concat(userDto.getEmail()));
			
			/*crear objeto de userDto */
			if(userDto == null) {
				userDto = new UserDto(reqUser.getName(),reqUser.getEmail(),reqUser.getPassword(),dateTime,dateTime,dateTime, true);
				idUser = iUserDataAcces.saveUser(userDto);
			} 
			
			/*actualizacion de datos cuando */
			if(!userDto.isActive()) {
				idUser = userDto.getIdUser();
				userDto.setActive(true);
				userDto.setLastLogin(dateTime);
				iUserDataAcces.saveUser(userDto);				
			}
			
		
			/*carga la lista de phones y se verifica el ingresos de los numeros*/
			 List<Phone>  listPhones = Mapper.evaluarPhones(reqUser.getPhones(), idUser, phoneRq -> phoneRq.getCityCode() != null && 
																									  phoneRq.getContryCode() !=null && 
																									  phoneRq.getNumber() !=null);


			iPhoneDataAccess.saveAll(listPhones); 

			
			/*crear token en base id generado al guardar*/
			String token = Validador.getJWTToken(String.valueOf(idUser));
			
			
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
	 * @exception Exception or DataAccessException
	 */
	@Override
	public ResponseUser getUserById(long id) throws Exception, NullPointerException {
		try {
			UserDto userDto = iUserDataAcces.findById(id);
				return new ResponseUser(userDto.getIdUser(),
					        Fecha.getLocalDateTimeString(userDto.getCreateDate()),
					        Fecha.getLocalDateTimeString(userDto.getModified()),
					        Fecha.getLocalDateTimeString(userDto.getLastLogin()),
					        Validador.getJWTToken(String.valueOf(userDto.getIdUser())),
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
	 * @exception Exception or DataAccessException
	 */
	@Override
	public boolean updateUser(RequestUser reqUser, long id) throws Exception, IllegalArgumentException, NullPointerException{
		String mensaje = null;
		LocalDateTime dateTime = LocalDateTime.now();
		try {
			/*validar el correo que cumpla con el formato*/
			mensaje = Validador.validarEmail(reqUser.getEmail());
			if(mensaje !=null) throw new IllegalArgumentException(mensaje);
			
			/*valida la clave del usuario*/
			mensaje = Validador.validarPassword(reqUser.getPassword());
			if(mensaje !=null) throw new IllegalArgumentException(mensaje);
			
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
	 * @exception Exception or DataAccessException
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
			
			return false;
		}catch (NullPointerException e) {
			throw e;
		}catch (IllegalArgumentException e) {
			throw e;
		}catch (Exception e) {
			logger.error("ERROR - [UsuarioServiceImpl -> Metodo - logOutUser] ", e);
			throw e;
		}
	}
}
