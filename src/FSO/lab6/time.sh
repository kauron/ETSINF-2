#!/bin/bash
for i in $(find . -type f -executable -name "RaceCond*" -print);
do 
	echo "$ time $i";
	time $i; 
done;
