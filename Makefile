old-%:
	gcc -lm examples/$(@:old-%=%).c examples/graphics.c -D OLDDRAWAPP
	a.out | java -jar drawapp.jar

%:
	gcc -lm examples/$@.c examples/graphics.c
	a.out | java -jar DrawAppFX/dist/DrawAppFX.jar
