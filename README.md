# ECOMMERCE-BACKEND

Este es un proyecto que realiza la implementación de un backend utilizando JEE y Wildfly. Ademas se utiliza PostgreSQL y Postamn para las pruebas de la API.

## Instalacion del servidor de aplicaciones

1- Descargar el archivo wildfly-xx.x.x [wildfly](https://www.wildfly.org/downloads/) y descomprimirlo.

2- Descargar el JDBC PostgreSQL version 42.7.5 y copiar este .jar a la direccion (``wildfly-xx.x.x.Final\modules\org\postgresql\main)``
3- Si no existiese, crear el archivo ``module.xml`` en la direccion especificada en el paso 2 y agregar elsiguiente codigo:

## module.xml

```java
<?xml version='1.0' encoding='UTF-8'?> 

<module xmlns="urn:jboss:module:1.1" name="org.postgresql"> 
    <resources> 
        <resource-root path="postgresql-42.7.5.jar"/> 
    </resources> 

    <dependencies> 
        <module name="javax.api"/> 
        <module name="javax.transaction.api"/> 
    </dependencies> 
</module> 

```
5- Se debe configurar el archivo ``wildfly-26.1.3.Final\standalone\configuration\standalone.xml``, en la seccion que mencione (``<subsystem xmlns="urn:jboss:domain:datasources:x.x">``) de la siguiente manera:
```java
<subsystem xmlns="urn:jboss:domain:datasources:7.0">
    <datasources>
        <datasource jndi-name="java:jboss/datasources/bd" pool-name="bd" enabled="true" use-java-context="true" statistics-enabled="${wildfly.datasources.statistics-enabled:${wildfly.statistics-enabled:false}}">
            <connection-url>jdbc:postgresql://localhost:5432/bd</connection-url>
            <driver>postgresql</driver>
            <security>
                <user-name>postgres</user-name>
                <password>postgres</password>
            </security>
        </datasource>
        <drivers>
            <driver name="postgresql" module="org.postgresql"> 
                <driver-class>org.postgresql.Driver</driver-class> 
            </driver> 
        </drivers>
    </datasources>
</subsystem>

```
Donde ``bd`` es el nombre de la base de datos que seleccione.
## Observacion
El valor de ``<datasource jndi-name="java:jboss/datasources/bd"`` debe coincidir con el valor de la linea ``<jta-data source>java:jboss/datasources/bd</jta-data-source>`` del archivo ``persistence.xml`` localizado en su poryecto JAVA (``src/main/resources/META-INF/persistence.xml``).

6- Luego puede iniciar el servidor Wildfly ejecutando la linea:
```cmd
C:\path\wildfly-xx.x.x.Final\bin>standalone.bat
```


## DEPLOY-Configuraciones
1- Se debe generar el .war del proyecto ejecutando:
```cmd
mvn clean install
```
2- Luego se debe inicar el cliente de jboss ``jboss-cli``:
```cmd
C:\path\wildfly-xx.x.x.Final\bin>jboss-cli.bat --connect
```
3- Y una vez que nos encontremos en la terminal del jboss-cli, ejecutamos el siguiente comando para deployar la aplicacion:
```cmd
deploy rutaDelWar
```

## Ruta de la aplicacion:
host: http://localhost:8080/prueba
