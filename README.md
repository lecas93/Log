# Log
**Log** es una libreria sencilla que te ayudara en el manejo del registro de actividad.

## ¿Cómo utilizar?

Para utilizar Log solo necesitas importar en tus proyectos el archivo `Log.jar` que puedes descarga [aquí](http://google.com). Y también debes importar la librería de `JavaMail 1.4.7` que igualmente puedes descargar desde este [enlace](https://mvnrepository.com/artifact/javax.mail/mail/1.4.7).

## Configuración inicial

Por default, Log trabaja con un archivo `config.xml` para cargar la configuración con la que se desea trabajar.
El archivo debe ser creado de la siguiente manera:
```xml
<?xml version="1.0" encoding="UTF-8" standalone="no" ?>
<config>
	<mode>debug</mode>
	<level>info</level>
	<filesize>1024</filesize>
	
	<emailTO>angi.lecas93@gmail.com</emailTO>
	<emailFROM>angi.lecas93@gmail.com</emailFROM>
	<emailPASS>myPassHere</emailPASS>
</config>
```
Donde:
* mode: Establece el modo con el que desees trabajar: debug | production
* level: Establece la prioridad con la que debe trabajar la librería: all, info, warning, error, off
* filesize: Establece el tamaño maximo en KB para los archivos generados
* emailTO: El correo al que deseas enviar los logs
* emailFROM: El correo anfitrión desde el cual se conectará la librearía para enviar los logs
* emailPASS: La contraseña del correo anfitrión para poder realizar la conexión
