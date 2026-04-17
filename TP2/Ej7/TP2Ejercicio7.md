# Trabajo Práctico Computación Móvil - Android con Java

##	Investigar la forma en la que se aplica JWT entre una aplicación Android y una aplicación Web mediante la comunicación de API. 

- JSON Web Tokens: es un procedimiento de intercambios de Tokens por medio de archivos JSON. Permiten identificar y autorizar a un usuario de forma segura - y stateless.

Un token es un objeto que representa el derecho a realizar una operación o acceder a un recurso. Es decir, nos permite acceder e interactuar con información la cual estaría restringida de otra forma.
La idea es que el servidor identifique al usuario sin tener que recibir el nombre de usuario y contraseña en cada petición. Entonces se lleva a cabo un procedimiento:

Primero, el usuario ingresa sus credenciales en la App y estas se envían al servidor mediante una petición POST por HTTPS. Esto, si los datos son correctos, desencadena la generación de un JWT firmado con una clave secreta y lo devuelve a la App.

La App debe almacenar este token de forma segura y usarlo posteriormente para autorizar cada acción que lleva a cabo el usuario, es decir, la app debe incluir el token en cada petición para interactuar con la API aplicación web, el token se agrega al encabezado de cada petición a la API.

Del otro lado, el servidor verifica la firma de cada token recibido en los encabezados y en caso de ser válidos, procede a procesar la solicitud.

