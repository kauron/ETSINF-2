package LTP.Lab2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class UsoDeArrayList {
    public static void main(String[] args) throws FileNotFoundException {
        File fichero = new File("file.txt");
        Scanner scanner = new Scanner(fichero);

        ArrayList<String> lista = new ArrayList<>();

        while(scanner.hasNext())
            lista.add(scanner.nextLine());

        Collections.sort(lista);
        System.out.println(lista);
    }
}