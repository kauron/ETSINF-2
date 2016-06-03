all: libs

libs: grafos util lineales modelos jerarquicos deDispersion

apps: municipios pruebasOrdenacion letras hospital editorPredictivo impresora

grafos:
	javac librerias/estructurasDeDatos/grafos/*.java

util:
	javac librerias/util/*.java

modelos:
	javac librerias/estructurasDeDatos/modelos/*.java

lineales:
	javac librerias/estructurasDeDatos/lineales/*.java

jerarquicos:
	javac librerias/estructurasDeDatos/jerarquicos/*.java

deDispersion:
	javac librerias/estructurasDeDatos/deDispersion/*.java

municipios:
	javac aplicaciones/municipios/*.java
	java aplicaciones.municipios.GuiGrafo

pruebasOrdenacion:
	javac aplicaciones/pruebasOrdenacion/*.java
	java aplicaciones.pruebasOrdenacion.TestOrdenacion comprobar
	java aplicaciones.pruebasOrdenacion.TestOrdenacion temporizar

letras:
	javac aplicaciones/letras/*.java
	java aplicaciones.letras.TestOCR

hospital:
	javac aplicaciones/hospital/*.java
	java aplicaciones.hospital.EficienciaCPGui
	java aplicaciones.hospital.TestUrgencias

editorPredictivo:
	javac aplicaciones/editorPredictivo/*.java
	java aplicaciones.editorPredictivo.TestEditorPredictivo

impresora:
	javac aplicaciones/impresora/*.java
	java aplicaciones.impresora.SimuladorImpresion


clean: cleanLibs cleanApps

cleanLibs: cleanGrafos cleanModelos cleanLineales cleanJerarquicos cleanDispersion cleanUtil

cleanApps: cleanMunicipios cleanLetras cleanHospital cleanPruebasOrdenacion cleanEditorPredictivo

cleanGrafos:
	rm -f librerias/estructurasDeDatos/grafos/*.class

cleanModelos:
	rm -f librerias/estructurasDeDatos/modelos/*.class

cleanUtil:
	rm -f librerias/util/*.class

cleanLineales:
	mv librerias/estructurasDeDatos/lineales/PruebaLEGLPI.class{,.bak}
	rm -f librerias/estructurasDeDatos/lineales/*.class
	mv librerias/estructurasDeDatos/lineales/PruebaLEGLPI.class{.bak,}

cleanJerarquicos:
	mv librerias/estructurasDeDatos/jerarquicos/TestMonticuloBinarioMaxR0.class{,.bak}
	rm -f librerias/estructurasDeDatos/jerarquicos/*.class
	mv librerias/estructurasDeDatos/jerarquicos/TestMonticuloBinarioMaxR0.class{.bak,}

cleanDispersion:
	rm -f librerias/estructurasDeDatos/deDispersion/{EntradaHash,TablaHash}.class

cleanMunicipios:
	rm -f aplicaciones/municipios/*.class

cleanPruebasOrdenacion:
	rm -f aplicaciones/pruebasOrdenacion/*.class

cleanLetras:
	rm -f aplicaciones/letras/*.class

cleanHospital:
	rm -f aplicaciones/hospital/{Hospital,Paciente,EficienciaCPGui,Urgencias}.class

cleanEditorPredictivo:
	rm -f aplicaciones/editorPredictivo/*.class



test-grafos: grafos
	java librerias.estructurasDeDatos.grafos.TestGrafos1

test-grafos-2: grafos
	java librerias.estructurasDeDatos.grafos.TestGrafos2

