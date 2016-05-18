all: libs apps

libs: grafos util lineales modelos jerarquicos deDispersion

apps: municipios pruebasOrdenacion letras hospital editorPredictivo

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


clean: cleanLibs cleanApps

cleanLibs: cleanGrafos cleanModelos cleanLineales cleanJerarquicos cleanDispersion cleanUtil

cleanApps: cleanMunicipios cleanLetras cleanHospital cleanPruebasOrdenacion cleanEditorPredictivo

cleanGrafos:
	rm librerias/estructurasDeDatos/grafos/*.class

cleanModelos:
	rm librerias/estructurasDeDatos/modelos/*.class

cleanUtil:
	rm librerias/util/*.class

cleanLineales:
	mv librerias/estructurasDeDatos/lineales/PruebaLEGLPI.class{,.bak}
	rm librerias/estructurasDeDatos/lineales/*.class
	mv librerias/estructurasDeDatos/lineales/PruebaLEGLPI.class{.bak,}

cleanJerarquicos:
	rm librerias/estructurasDeDatos/jerarquicos/*.class

cleanDispersion:
	rm librerias/estructurasDeDatos/deDispersion/{EntradaHash,TablaHash}.class

cleanMunicipios:
	rm aplicaciones/municipios/*.class

cleanPruebasOrdenacion:
	rm aplicaciones/pruebasOrdenacion/*.class

cleanLetras:
	rm aplicaciones/letras/*.class

cleanHospital:
	rm aplicaciones/hospital/{Hospital,Paciente,EficienciaCPGui,Urgencias}.class

cleanEditorPredictivo:
	rm aplicaciones/editorPredictivo/*.class



test-grafos: grafo
	java librerias.estructurasDeDatos.grafos.TestGrafos1

test-grafos-2: grafos
	java librerias.estructurasDeDatos.grafos.TestGrafos2

