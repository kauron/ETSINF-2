all: grafos

grafos:
	javac librerias/estructurasDeDatos/grafos/*.java

test-grafos: grafos
	java librerias.estructurasDeDatos.grafos.TestGrafos1

test-grafos-2: grafos
	java librerias.estructurasDeDatos.grafos.TestGrafos2

clean:
	find . -name *.class -exec rm {} \;
