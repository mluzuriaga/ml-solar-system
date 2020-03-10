# ml-solar-system

# Problema
En una galaxia lejana, existen tres civilizaciones. Vulcanos, Ferengis y Betasoides. Cada civilizacion vive en paz en su respectivo planeta.

Dominan la prediccion del clima mediante un complejo sistema informatico.

**Premisas:**
* El planeta Ferengi se desplaza con una velocidad angular de 1 grado por dia en sentido horario. Su distancia con respecto al sol es de 500km.
* El planeta Betasoide se desplaza con una velocidad angular de 3 grados por dia en sentido horario. Su distancia con respecto al sol es de 2000km.
* El planeta Vulcano se desplaza con una velocidad angular de 5 grados por dia en sentido anti-horario. Su distancia con respecto al sol es de 1000km.
* Todas las orbitas son circulares.

Cuando los tres planetas estan alineados entre si y a su vez alineados con respecto al sol, el sistema solar experimenta un periodo de sequia.
Cuando los tres planetas no estan alineados, forman entre si un triangulo. Es sabido que en el momento en el que el sol se encuentra dentro del triangulo, el sistema solar experimenta un periodo de lluvia, teniendo este, un pico de intensidad cuando el perimetro del triangulo esta en su maximo.
Las condiciones optimas de presion y temperatura se dan cuando los tres planetas estan alineados entre si pero no estan alineados con el sol.

**Realizar:**
Un programa informatico para poder predecir en los proximos 10 años:
* Cuantos periodos de sequia habra?
* Cuantos periodos de lluvia habra y que dia sera el pico maximo de lluvia?
* Cuantos periodos de condiciones optimas de presion y temperatura habra?

Generar un modelo de datos con las condiciones de todos los dias hasta 10 años en adelante utilizando un job para calcularlas.
Generar una API REST la cual devuelve en formato JSON la condicion climatica del dia consultado.
Hostear el modelo de datos y la API REST en un cloud computing libre y enviar la URL para consultar el clima de un dia en particular.

# Solucion

**Diagrama de clases:**

![Image description](https://github.com/mluzuriaga/ml-solar-system/src/main/resources/public/use-case-diagram.png)

**El desarrollo se hizo utilizando:**
* Java 8
* Spring Framework 
* Maven
* Heroku
* TestNG

**Para levantarlo en un entorno local:**
 * Configurar en el ml-solar-system\src\main\resources\application.yml para apuntar a una BD local.
 * Crear un esquema de BD
 * Correr mvn clean install -U
 * Dentro de la carpeta ml-solar-system\target se encontrara el jar generado: ml-solar-system-0.0.1-SNAPSHOT.jar
 * Correr java -jar ml-solar-system-0.0.1-SNAPSHOT.jar
 
El sistema cuando inicia ejecuta un Job para calcular el pronostico del clima desde la fecha actual hasta 10 años en adelante.
En ml-solar-system\src\main\resources\application.yml esta la configuracion del Job. El periodo a pronosticar esta representado en dias.
El sistema esta desplegado en Heroku. Dado una restriccion a la cantidad de querys que se pueden hacer a la base de datos por hora, se establecio que corra el Job cada 3 segundos.

Una vez levantado acceder a http://localhost:8080/solar-system
Se vera una pagina de portada explicando, en menor detalle, lo mismo que el presente documento.

La API REST expone 3 endpoints:
* GET http://localhost:8080/solar-system/clima?dia=x : Obtiene el pronostico para el dia 'x'. El numero 'x' debe ser la cantidad de dias desde la fecha inicial del ultimo reporte generado.
* GET http://localhost:8080/solar-system/reporte : Obtiene el ultimo reporte generado.
* POST http://localhost:8080/solar-system/nuevo?dias=x : Genera un nuevo pronostico a 'x' dias partir de la fecha final del ultimo reporte generado.

**Acceso al sistema de Heroku:**
* Acceso a la portada: http://ml-solar-system.herokuapp.com/solar-system/
* GET http://ml-solar-system.herokuapp.com/solar-system/clima?dia=x : Obtiene el pronostico para el dia 'x'. El numero 'x' debe ser la cantidad de dias desde la fecha inicial del ultimo reporte generado.
* GET http://ml-solar-system.herokuapp.com/solar-system/reporte : Obtiene el ultimo reporte generado.
* POST http://ml-solar-system.herokuapp.com/solar-system/nuevo?dias=x : Genera un nuevo pronostico a 'x' dias partir de la fecha final del ultimo reporte generado.

#Mejoras:

* Mas tests unitarios

# Restricciones:

* El cloud free sobre que esta desplegado tiene como restriccion hasta 3600 queries por hora a la base de datos
