# Proyecto-GlobalLogic
  * Proyecto de Autentificación
  
# Requisitos para el Proyecto:
  * IDE: sts-4.7.0.RELEASE o El que mas Ocupe. 
  * JAVA: se utilizó la version 1.8, puede ocupar una versión superior.
  * LIBRERIA: Se ocupo el banco de librerias de MVN-Repository.
  * BD: Se ocupo una base de datos H2 Database.
  * Spring Boot: tecnologia ocuapda.


# Bajar y levantar el proyecto desde Linux (Consola):

  * Bajar el proyecto por console:
     - copiar la url "CODE" => https://github.com/jdanielmq/Proyecto-GlobalLogic.git
     - En pc o notebook abrir un Terminal
     - posicionar se en la carpeta donde bajara el proyecto.
     - clonar proyecto => git clone https://github.com/jdanielmq/Proyecto-GlobalLogic.git "nombre propio"
     
  * Abrir el IDE:
     - Abrir un worksapce, lo ideal dejar el proyecto dentro de la carperta workspace.
     - Importar el proyecto: Import -> General -> Proyects From Folder o Archive -> ir a la carpeta donde se bajo el proyecto.
     - limpiar el proyecto: Proyect -> Clean...
     - Bajar las Dependencia:  Botón derecho del mouse sobre el proyecto -> Gradel -> Resfresh Gradle Proyect
     - Corregir el path Java por el que tengan instalado en la máquina.
      
# Bajar y levantar el proyecto desde Windows:

  * Bajar el proyecto empaquetado:
     - Ir al botón "CODE" => Download ZIP
     - El archivo empaquetado queda en la carpeta Descarga
     - Copiar y pegar en el workspaces de trabajo
     - Descomprimir el proyecto.
     
  * Abrir el IDE:
     - Abrir un worksapce, lo ideal dejar el proyecto dentro de la carperta workspace.
     - Importar el proyecto: Import -> General -> Proyects From Folder o Archive -> ir a la carpeta donde se bajo el proyecto.
     - Limpiar el proyecto: Proyect -> Clean...
     - Bajar las Dependencia:  Botón derecho del mouse sobre el proyecto -> Gradel -> Resfresh Gradle Proyect
     - Corregir el path Java por el que tengan instalado en la máquina.
     
# Ejecutar el Proyecto:

  * pasos:
     - Levantar Proyecto: Botón derecho del mouse sobre el proyecto -> Run as -> Spring Boot App
  
       ** Si el proyecto no presenta errores de librerias, levantara sin problema

# Caracteristica Generales:
  * JWT: Librerias para la seguidad. se trabajo bajo token.
  * Hibernate: Librerias para interactuar con la base de datos. (Interfaz ocupada CrudRepository)
  * BD: Se ocupo una base de datos H2 Database.

# Url del Proyecto:
  * Logear: http://{domino o localhost}:8080/private/user/login  Método: POST
    - JSON de Entrada: Request
      {
       "name": "Juan Daniel",
       "email": "juan@daniel.com",
       "password": "JdmQ1481#",
       "phones": [
               {
               "number": "1234567",
               "citycode": "1",
               "contrycode": "57"
               }
           ]
       }     
       
    - JSON de Respuesta: Response
       {
           "data": {
               "id": 1,
               "created": "23-08-2020 02:58 AM",
               "modified": "23-08-2020 02:58 AM",
               "lastLogin": "23-08-2020 02:58 AM",
               "token": "Bearer eyJhbGciOiJIUzUxMiJ9.
               eyJqdGkiOiJzb2Z0dGVrSldUIiwic3ViIjoiMSIsImF1dGhvcml0aWVzIjpbIlJPTEVfVVNFUiJdLCJpYXQiOjE1OTgxNjU5MzcsImV4cCI6MTU5ODE2NjUzN30.
               faILbx3cic1pGXzsYIv86OHqN5m0KN-Vp5TkQ-Jk2CD497fLcjdAk-Txof_jqUoAZ34zwnbK6XqF7tNi643laQ",
               "active": true
           }
       }          
       
  * Consultar Usuario: http://{domino o localhost}:8080/private/user/{id del usuario}  Método: GET
    - JSON de Respuesta: Response
       {
           "data": {
               "id": 1,
               "created": "23-08-2020 02:58 AM",
               "modified": "23-08-2020 02:58 AM",
               "lastLogin": "23-08-2020 02:58 AM",
               "token": "Bearer eyJhbGciOiJIUzUxMiJ9.
               eyJqdGkiOiJzb2Z0dGVrSldUIiwic3ViIjoiMSIsImF1dGhvcml0aWVzIjpbIlJPTEVfVVNFUiJdLCJpYXQiOjE1OTgxNjU5MzcsImV4cCI6MTU5ODE2NjUzN30.
               faILbx3cic1pGXzsYIv86OHqN5m0KN-Vp5TkQ-Jk2CD497fLcjdAk-Txof_jqUoAZ34zwnbK6XqF7tNi643laQ",
               "active": true
           }
       }   
       
       
  * Actualizar: http://{domino o localhost}:8080/private/user/update/{id del usuario}  Método: PUT
    - JSON de Entrada: Request
      {
       "name": "Juan Daniel",
       "email": "juan@daniel.com",
       "password": "JdmQ1481#",
       "phones": [
               {
               "number": "1234567",
               "citycode": "1",
               "contrycode": "57"
               }
           ]
       }     
       
    - JSON de Respuesta: Response
      {
          "data": "Se modificaron los datos del usuario correctamente."
      }
 
   * Logout: http://{domino o localhost}:8080/private/user/logout/{id del usuario}  Método: PUT
    - JSON de Respuesta: Response
     {
         "data": "cierre de sesiòn."
     }

# Conclusión de la API Restfull
  * Cuenta con seguirdad con token en JWT y seguridad de Spring Boot.
  * Se implementa Hibernate con la interfaces CrudRepository
  * Se trabajo bajo el concepto cuenta con bajo acoplamiento y alta cohesión. (DI = Inyección de Dependecia)
  * Trabaja con una base de dato H2 Database.
    
 # Fin
