# Agregar librerías de Spring MVC utilizando Maven

Para poder implementar una solución web usando `spring MVC` tenemos la opción de descargar cada paquete que compone este `framework` o podemos utilizar `Maven`. (Hay más opciones como usar `Gradle`; pero eso no lo veremos en este manual). 
`Maven`, posee un repositorio oficial([https://mvnrepository.com/](https://mvnrepository.com/ "https://mvnrepository.com/")), donde podemos descargar y usar los paquetes que aparecen allí. Uno de ellos es `spring-webmvc`.

## Pasos a seguir:
+ Creamos el proyecto maven, lo transformamos a 
+ En el buscador de paquetes, ingresamos `spring-webmvc`, esto nos lleva al siguiente enlace: [https://mvnrepository.com/artifact/org.springframework/spring-webmvc](https://mvnrepository.com/artifact/org.springframework/spring-webmvc "https://mvnrepository.com/artifact/org.springframework/spring-webmvc"). Seleccionamos la última versión. En la fecha en que se desarrolla este ejemplo la última versión es la `5.2.2.RELEASE`. A continuación, tenemos varias pestañas, seleccionamos la que dice `Maven`. Esto debería contener el siguiente código:

```html
<!-- https://mvnrepository.com/artifact/org.springframework/spring-webmvc -->
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-webmvc</artifactId>
    <version>5.2.2.RELEASE</version>
</dependency>
```

+ Esta dependencia irá directo al `pom.xml`, es este lugar encargado de administrar las dependencias de un proyecto. Para incluirlo hay que añadir la etiqueta `<dependencies>` ya que no viene incluida y dentro de estas, irán todas las dependencias que posea el proyecto.
+ Antes de incluir esta dependencia, veamos como estaría nuestro proyecto inicial en `STS`. ![](DraggedImage.png). Podemos ver que en el proyecto no existe un directorio de dependencias. Esto se realiza automáticamente, ya que estamos usando `Maven`.
+ El archivo `pom.xml`, debería contener el siguiente código:
```html
<project xmlns="http://maven.apache.org/POM/4.0.0"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<groupId>cl.lherrera</groupId>
	<artifactId>simple-springwebmvc-maven</artifactId>
	<version>19.0.0</version>
</project>
```
+ Lo modificamos y queda de la siguiente forma:
```html
<project xmlns="http://maven.apache.org/POM/4.0.0"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<groupId>cl.lherrera</groupId>
	<artifactId>simple-springwebmvc-maven</artifactId>
	<version>19.0.0</version>

	<dependencies>

		<!-- https://mvnrepository.com/artifact/org.springframework/spring-webmvc -->
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-webmvc</artifactId>
			<version>5.2.2.RELEASE</version>
		</dependency>

	</dependencies>

</project>
```
+ Desde el momento en que grabamos, `Maven` comenzará a descargar los paquetes necesarios para satisfacer estas dependencias declaradas en el `pom.xml`, cuando finalice la descarga, aparecerá el siguiente directorio: `Maven Dependencies` ![](DraggedImage-1.png). Este directorio contiene las dependencias descargadas por `Maven`.  Para ser más específicos, podremos ver dónde están físicamente en: `/Users/luisherrera/.m2/repository`. Es acá donde `Maven` busca en una primera instancia si los paquetes se encuentran, si no es así se conecta al repositorio de `Maven` online y los descarga.
+ Finalmente, es necesario agregar estas dependencias a la directiva de ensamblaje. ¿Para qué?, por que cuando en el futuro se construya el paquete final con nuestra app web, al momento de empaquetarlo antes de enviarlo al servidor de producción, queremos que junto a nuestra aplicación, se incluyan todas estas dependencias. Para eso vamos a: `propiedades del proyecto > Deployment Assembly > add` ![](DraggedImage-2.png), luego en siguiente ventana, tenemos la opción `Java Build Path Entries` y presionamos `next`, nos aparecerá el directorio que se acaba de crear `Maven Dependencies`. ![](DraggedImage-3.png) \> ![](DraggedImage-4.png).  Aceptamos y cerramos.

# Configuración del `DispatcherServlet` en el archivo web.xml

El `DispatcherServlet`, conocido además como el `Front Controller`, es una clase especial de `Spring Framework`, podemos encontrar su código fuente en la siguiente dirección: [https://raw.githubusercontent.com/spring-projects/spring-framework/master/spring-webmvc/src/main/java/org/springframework/web/servlet/DispatcherServlet.java](https://raw.githubusercontent.com/spring-projects/spring-framework/master/spring-webmvc/src/main/java/org/springframework/web/servlet/DispatcherServlet.java "https://raw.githubusercontent.com/spring-projects/spring-framework/master/spring-webmvc/src/main/java/org/springframework/web/servlet/DispatcherServlet.java"). Vemos entonces que este despachador, posee la siguiente cadena de herencias `FrameworkServlet > HttpServlet`. 
Este `DispatcherServlet`, es una clase muy flexible y versátil. Podemos entonces, mediante el archivo `web.xml` utilizar las funcionalidades mediante una previa configuración indicada en dicho archivo `xml`.
El archivo `web.xml`, debería poseer el siguiente código:![](DraggedImage.png)

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns="http://xmlns.jcp.org/xml/ns/javaee"
	xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
	id="WebApp_ID" version="4.0">
	<display-name>simple-springwebmvc-maven</display-name>
	<welcome-file-list>
		<welcome-file>index.html</welcome-file>
		<welcome-file>index.htm</welcome-file>
		<welcome-file>index.jsp</welcome-file>
		<welcome-file>default.html</welcome-file>
		<welcome-file>default.htm</welcome-file>
		<welcome-file>default.jsp</welcome-file>
	</welcome-file-list>
</web-app>
```

Lo primero que haremos será dejar solamente las etiquetas de nuestra web.

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns="http://xmlns.jcp.org/xml/ns/javaee"
	xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
	id="WebApp_ID" version="4.0">
	<!--  secciOn de todas las configuraciones de la aplicaciOn web  -->
</web-app>
```

En la siguiente estructura definimos un `servlet` llamado `springmvc` (puede llamarse como queramos). Este se abastece de `DispatcherServlet`
```xml
	<servlet>
		<servlet-name>springmvc</servlet-name>
		<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
		<load-on-startup>1</load-on-startup>
	</servlet>
```

Donde:
+ `servlet-name`: Indica el nombre del `servlet` que crearemos.
+ `servlet-class`: Indica la clase base desde la que crearemos nuestro `servlet`. En el ejemplo, podemos ver que usamos la que nos proporciona `Spring`; pero podría ser otra, como cuando algunas compañías, crean sus propias bibliotecas, muchas a partir de las mismas que lo hacemos nosotros, más quizás algunas funcionalidades personalizadas de seguridad.
+ `load-on-startup`:  Indica el orden en que se cargan los `servlets`, partiendo desde el 1.
Luego debemos indicar qué tipos de archivos estará representando o `mapeado`. este `servlet`, en este caso serán todos los archivos terminados en `.html`.

```xml
	<servlet-mapping>
		<servlet-name>springmvc</servlet-name>
		<url-pattern>*.html</url-pattern>
	</servlet-mapping>
```
 
De esta forma cuando el `DispatcherServlet` se ejecute, buscará el archivo `springmvc-servlet.xml` en la ruta donde tenemos el `web.xml`. 
`FrameworkServlet`, del que hereda `DispatcherServlet`, posee el siguiente atributo:

```java
...
/** Explicit context config location. */
	@Nullable
	private String contextConfigLocation;
...
```

Luego podemos indicar en el `web.xml` el valor de este parámetro, para indicar una ruta personalizada a partir de `/project/webapp/`. Lo hacemos de la siguiente forma:

```xml
	<servlet>
		<servlet-name>springmvc</servlet-name>
		<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
		<!-- es acA donde indicamos el parAmetro -->
		<init-param>
			<param-name>contextConfigLocation</param-name>
			<param-value>/WEB-INF/nuevaCarpeta/springmvc-servlet.xml</param-value>
		</init-param>
		<!-- ----------------------------------- -->
		<load-on-startup>1</load-on-startup>

	</servlet>
```

Se indica que el archivo que configurará el `servlet` se estará dentro de `nuevaCarpeta` (no olvidar ponerlo antes de `load-on-startup`, ya que el orden importa). Esto se indica a modo de ejemplificar el como podemos indicar una ruta para nuestros `servlets`; pero no lo usaremos en el ejemplo.

Finalmente el archivo `web.xml` queda de la siguiente forma.

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns="http://xmlns.jcp.org/xml/ns/javaee"
	xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
	id="WebApp_ID" version="4.0">

	<servlet>
		<servlet-name>springmvc</servlet-name>
		<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
		<load-on-startup>1</load-on-startup>

	</servlet>

	<servlet-mapping>
		<servlet-name>springmvc</servlet-name>
		<url-pattern>*.html</url-pattern>
	</servlet-mapping>

</web-app>

```

Con la configuración realizada, queda entonces crear el archivo `springmvc-servlet.xml`, ya que en `web.xml` definimos ese nombre. La ruta por defecto será: `projecto/webapp/WEB-INF/` es justamente donde se encuentra el archivo `web.xml`. ![](DraggedImage-1.png)

En el archivo creado ingresamos el siguiente código:

```xml
<beans xmlns="http://www.springframework.org/schema/beans"
	   xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	   xmlns:context="http://www.springframework.org/schema/context"
	   xmlns:mvc="http://www.springframework.org/schema/mvc"
	   xsi:schemaLocation="http://www.springframework.org/schema/beans 
			http://www.springframework.org/schema/beans/spring-beans.xsd
			http://www.springframework.org/schema/context 
			http://www.springframework.org/schema/context/spring-context-4.3.xsd
			http://www.springframework.org/schema/mvc 
			http://www.springframework.org/schema/mvc/spring-mvc-4.3.xsd">

	<!-- acA toda la configuraciOn -->

	
</beans>
```

Es entre estas etiquetas donde irán los `beans`, los atributos (espacios de nombres) de esta etiqueta, simplemente indican archivos de validaciones oficiales, esto solamente debe ser reemplazado si es que se tienen unas propias. 
Antes de poder agregar los `beans`, debemos definir dos etiquetas (`context`, `mvc`):

```xml
<context:component-scan base-package="cl.lherrera.app.controller" />
<mvc:annotation-driven />
```

Donde: 
+ `component-scan`: Indica a `Spring`, que busque los `beans` con las anotaciones `@Component, @Controller, @Service, @Repository`.
+ `base-package`: Indica el paquete del proyecto que contiene los controladores.
+ `annotation-driven`: Indica que se active el reconocimiento de anotaciones como `@Controller, @RequestMapping, etc.`

Luego  definimos el `bean` encargado de enlazar las vistas con el controlador. Hablo del `viewResolver`.

```xml
	<bean id="viewResolver"
		class="org.springframework.web.servlet.view.InternalResourceViewResolver">

		<property name="prefix">
			<value>/WEB-INF/views/</value>
		</property>

		<property name="suffix">
			<value>.jsp</value>
		</property>

	</bean>
```

Se pueden ver que posee dos propiedades:
+ `prefix`: Indica en qué directorio estarán ubicadas las vistas.
+ `suffix`: Indica la extensión que tendrán las vistas. En este caso será `.jsp`.

Estas propiedades son de la clase `org.springframework.web.servlet.view.InternalResourceViewResolver` que indicamos en el `bean` que ocuparemos en base a dicha clase.  Veamos un fragmento.

```java
package org.springframework.web.servlet.view;
// ...

public class InternalResourceViewResolver extends UrlBasedViewResolver {

// ...	
    public InternalResourceViewResolver(String prefix, String suffix) {
		this();
		setPrefix(prefix);
		setSuffix(suffix);
    }
// ...
```

Entonces el archivo `projecto/webapp/WEB-INF/springmvc-servlet.xml`, queda de la siguiente manera:

```xml
<?xml version="1.0" encoding="UTF-8"?>

<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:context="http://www.springframework.org/schema/context"
	xmlns:mvc="http://www.springframework.org/schema/mvc"
	xsi:schemaLocation="http://www.springframework.org/schema/beans 
			http://www.springframework.org/schema/beans/spring-beans.xsd
			http://www.springframework.org/schema/context 
			http://www.springframework.org/schema/context/spring-context-4.3.xsd
			http://www.springframework.org/schema/mvc 
			http://www.springframework.org/schema/mvc/spring-mvc-4.3.xsd">

	<context:component-scan
		base-package="cl.lherrera.app.controller" />
	
	<mvc:annotation-driven />

	<bean id="viewResolver"
		class="org.springframework.web.servlet.view.InternalResourceViewResolver">

		<property name="prefix">
			<value>/WEB-INF/views/</value>
		</property>

		<property name="suffix">
			<value>.jsp</value>
		</property>

	</bean>

</beans>

```

## Desarrollando el primer controlador.
Un controlador, no es más que una clase que irá en el paquete que indicamos en `springmvc-servlet.xml`. Esta clase tendrá una anotación especial `@Controller` y con esto, `Spring` creará un `bean` y se lo llevará al controlador de `beans` de `spring`. ![](DraggedImage-2.png)
Luego de crear el paquete creamos la clase que será nuestro controlador, este quedará en: `/simple-springwebmvc-maven/src/main/java/cl/lherrera/app/controller/EjemploDeControlador.java`. Este debe poseer el siguiente contenido:

```java
package cl.lherrera.app.controller;

import org.springframework.stereotype.Controller;

@Controller
public class EjemploDeControlador {

}
```

Donde: 
+ `org.springframework.stereotype.Controller`, es el paquete que contiene la anotación `@Controller`.

Es momento entonces de crear el primer método de esta clase. Este método procesará una petición y finalmente entregará al `viewResolver` la responsabilidad de mostrar la información en una vista.

Veamos como queda nuestro controlador finalmente para el ejemplo:

```java
package cl.lherrera.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class EjemploDeControlador {
	
	@RequestMapping(value = "/unaURL", method = RequestMethod.GET)
	public String miNombreNoImporta() {
		// todo el procesamiento de la petición
		System.out.println("Se ejecuta el procesamiento de la petición");
		
		return "unaVista";
	}

}

```

`RequestMapping`: Es para indicar que asocie las peticiones con el método que las procesará. Podemos ver que se le asocian dos atributos: `value` y `method`. El primero corresponde al valor que irá en el `request` y el otro que tipo de petición es.
Luego de todo el procesamiento que requiera esta petición, como lo podría ser un gran trabajo de procesamiento y recolección, retornamos la vista, indicando entre comillas el nombre. Recordando que esta vista debe estar en `WEB-INF/views/`.  Archivo que aún no existe. 

### Primera prueba
Al momento ejecutar la aplicación en el servidor, y en un navegador escribir el siguiente enlace: [http://localhost:8080/simple-springwebmvc-maven/unaURL.html](http://localhost:8080/simple-springwebmvc-maven/unaURL.html "http://localhost:8080/simple-springwebmvc-maven/unaURL.html"), obtenemos un error 404. ![](DraggedImage-3.png), pero en la consola podemos ver que se imprime `Se ejecuta el procesamiento de la petición`. Esto quiere decir que el programa si ejecutó todo lo que corresponde al proceso del método que maneja la petición, este método, efectivamente retorna el valor del `String` que en este caso es `"unaVista"`, por lo que es el `viewResolver` quien resuelve un error 404 de no encontrado.  Para solucionar esto, hay que crear el archivo `unaVista.jsp` en la ruta que configuramos en el  `viewResolver` dentro del `springmvc-servlet.xml`.

> Nota: es `/unaURL.html` y no `/unaURL` ya que así se lo indicamos en el archivo `web.xml` en la etiqueta `servlet-mapping`.

### Segunda prueba
Creamos el directorio `views` y dentro el archivo `unaVista.jsp`, quedando en la siguiente ruta: `/simple-springwebmvc-maven/webapp/WEB-INF/views/unaVista.jsp`.

```html
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>Hola mundo desde la vista</h1>

</body>
</html>
```

Volvemos a ejecutar la aplicación en el servidor y volvemos a poner la misma URL anterior obteniendo esta vez el mensaje contenido en el `h1` ![](DraggedImage-4.png)