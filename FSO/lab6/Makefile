all: hello add matrix

add: sequential threads threads2

matrix: matrix_draw matrix_erase matrix_complete matrix_complete_pro

hello:
	gcc hello.c -o ./out/hello -lpthread

sequential:
	gcc SequentialAdd.c -o ./out/SeqAdd -lm

threads:
	gcc ThreadsAdd.c -o ./out/ThAdd -lm -lpthread

threads2:
	gcc ThreadsAdd2.c -o ./out/ThAdd2 -lm -lpthread

matrix_draw:
	gcc matrix_draw.c -o ./out/matrix_draw -lpthread

matrix_erase:
	gcc matrix_erase.c -o ./out/matrix_erase -lpthread

matrix_complete:
	gcc matrix_complete.c -o ./out/matrix_complete -lpthread

matrix_complete_pro:
	gcc matrix_complete_pro.c -o ./out/matrix_complete_pro -lpthread

clean:
	rm out/*
