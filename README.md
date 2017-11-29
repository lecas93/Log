# Log
**Log** es una libreria sencilla que te ayudara en el manejo del registro de actividad.

## Primeros pasos

Para utilizar Log solo necesitas importar en tus proyectos el archivo `Log.jar` que puedes descarga [aquí](https://drive.google.com/open?id=1tOjR6zdexpncdnSsdTiNvBCfdj13L7Bt). Y también debes importar la librería de `JavaMail 1.4.7` que igualmente puedes descargar desde este [enlace](https://mvnrepository.com/artifact/javax.mail/mail/1.4.7).

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
* `mode`: Establece el modo con el que desees trabajar: `debug | production`
* `level`: Establece la prioridad con la que debe trabajar la librería: `all, info, warning, error, off`
* `filesize`: Establece el tamaño maximo en KB para los archivos generados
* `emailTO`: El correo al que deseas enviar los logs
* `emailFROM`: El correo anfitrión desde el cual se conectará la librearía para enviar los logs
* `emailPASS`: La contraseña del correo anfitrión para poder realizar la conexión

**El archivo `config.xml` debe estar ubicado en la carpeta raíz del proyecto.**
**Si la libreria no encuentra el archivo o este no tiene el formato antes proporcionado, la librería cargará los valores por defecto: `mode = production` y `level = info`**

## ¿Cómo utilizar?

**Log** es muy sencillo de utilizar. Basta con que obtengas una instancia de Logger al inicio de cada clase donde desees utilizarlo y  posteriormente ya podras llamar a las funciones que necesites.

## Ejemplo básico:
```Java
package Test;

import Log.Logger;

public class MyClass {

	private Logger logger;

	public MyClass() {
		logger = Logger.getLogger(MyClass.class);

		logger.info("Se instanció el logger");
		
		myMethod1();
		myMethod2();
	}

	private void myMethod1() {
		logger.warning("Este es un warning");
	}

	private void myMethod2() {
		String s = "t";
		try {
			int i = Integer.parseInt(s);
			System.out.println(i);
		} catch (NumberFormatException nfe) {
			logger.error("Error: " + nfe.getMessage());
		}

	}

}
```

## Características

* Bitácora configurable (via archivo XML o código) por nivel (WARNING, ERROR, INFO, etc.) y el 
tamaño máximo que tendrán los archivos de la bitácora.

* Bitácora almacenada por archivos, los cuales son separados por 
día.

* Envío de reportes por email, teniendo la opción de configurar los reportes por nivel de error.
	* Los email solo contienen los registros del dia actual. Ver [Ejemplo](https://github.com/lecas93/Log/blob/master/src/Test/Main.java).

* Separación entre nivel implementación (debug o producción).
	* **debug**: Las notificaciones de la bitácora son arrojados en consola.
	* **production**:  Las notificaciones de la bitácora son registradas en archivos.

<!--
### Clase x
* Descripción:
* Dependencias: Dependencias con otras clases: <<Listar las asociaciones, nombre y descripción
* Atributos: Enumerarlas y adicionar el nombre, tipo, visibilidad, valor por omisión y descripción.
* Funciones: Enumerarlas y adicionar el nombre, listado de argumentos con su tipo, valor de retorno, visibilidad, si es función pública mencionar el servicio que esta implementando (componente e interface de salida) y descripción.
-->
	
## Diagrama de clases

![class-diagram](https://i.imgur.com/2OxSEQb.png)
