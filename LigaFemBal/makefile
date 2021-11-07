#Makefile
#Para usarlo, crear carpeta copiando las carpetas main,controller,view y data de src del proyecto y añadir la biblioteca.jar
#para poder ejecutar, copiar carpeta com de librería de coti biblioteca.jar a PracticaFinal.jar

CLASSES = main/Main.class view/View.class controller/Controller.class data/LigaFem.class data/Equipo.class data/Jugadora.class data/Partido.class data/Datos_equipo.class data/Jornada.class

CLASS_USADAS = data/Equipo.class data/Jugadora.class data/Partido.class data/Datos_equipo.class data/Jornada.class
JAVAS_USADOS = data/Equipo.java data/Jugadora.java data/Partido.java data/Datos_equipo.java data/Jornada.java

CLASSES_MAIN = view/View.class controller/Controller.class data/LigaFem.class data/Equipo.class data/Jugadora.class data/Partido.class data/Datos_equipo.class data/Jornada.class
JAVAS_MAIN = view/View.java controller/Controller.java data/LigaFem.java data/Equipo.java data/Jugadora.java data/Partido.java data/Datos_equipo.java data/Jornada.java

CLASSES_View = controller/Controller.class data/LigaFem.class ${CLASS_USADAS}
JAVAS_View = controller/Controller.java data/LigaFem.java ${JAVAS_USADOS}


all: PracticaFinal.jar

PracticaFinal.jar: ${CLASSES} biblioteca.jar
	jar cmfv Manifest PracticaFinal.jar ${CLASSES} biblioteca.jar
	
main/Main.class: main/Main.java ${CLASSES_MAIN} biblioteca.jar
	javac main/Main.java ${JAVAS_MAIN} -cp biblioteca.jar
	
view/View.class: view/View.java ${CLASSES_View} biblioteca.jar
	javac view/View.java ${JAVAS_View} -cp biblioteca.jar

controller/Controller.class: controller/Controller.java data/LigaFem.class ${CLASS_USADAS} biblioteca.jar
	javac controller/Controller.java data/LigaFem.java ${JAVAS_USADOS} -cp biblioteca.jar

data/LigaFem.class: data/LigaFem.java ${CLASS_USADAS} biblioteca.jar
	javac data/LigaFem.java ${JAVAS_USADOS} -cp biblioteca.jar

data/Equipo.class: data/Equipo.java data/Jornada.class data/Datos_equipo.class biblioteca.jar
	javac data/Equipo.java data/Jornada.java data/Datos_equipo.java -cp biblioteca.jar

data/Jugadora.class: data/Jugadora.java data/Equipo.class data/Datos_equipo.class  data/Jornada.class biblioteca.jar
	javac data/Jugadora.java data/Equipo.java data/Datos_equipo.java data/Jornada.java -cp biblioteca.jar

data/Partido.class: data/Partido.java data/Datos_equipo.class data/Jornada.class biblioteca.jar
	javac data/Partido.java data/Datos_equipo.java data/Jornada.java -cp biblioteca.jar

data/Datos_equipo.class: data/Datos_equipo.java data/Jornada.class biblioteca.jar
	javac data/Datos_equipo.java data/Jornada.java -cp biblioteca.jar

data/Jornada.class: data/Jornada.java biblioteca.jar
	javac data/Jornada.java -cp biblioteca.jar

lean:
	rm -rf ${CLASSES}

clean: 
	rm -rf ${CLASSES}
	rm -rf PracticaFinal.jar


