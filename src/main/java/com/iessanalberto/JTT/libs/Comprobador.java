package com.iessanalberto.JTT.libs;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Comprobador {

        // Clase que nos permite llamar a métodos para comprobar si se pueden leer o escribir

        public static boolean directorioSePuedeEscribir(Path p) {
            // Método que comprueba si se puede escribir en un path por parámetro, y sino lo crea
            boolean directorioValido = false;
            if (Files.exists(p) && Files.isDirectory(p)) {
                if (Files.isWritable(p)) {
                    directorioValido = true;
                }

            } else {
                try {
                    Files.createDirectory(p);
                    directorioValido = true;
                } catch (IOException e) {
                    System.out.println("Error al intentar crear el fichero.");
                }
            }
            return directorioValido;
        }


        public static boolean ficheroSePuedeLeer(Path p) {

            // Método que comprueba si se puede escribir en un path por parámetro, y sino lo crea el fichero
            boolean ficheroOK = false;
            if (Files.exists(p)) {
                if (Files.isReadable(p)) {
                    ficheroOK = true;
                }
            } else {
                try {
                    Files.createFile(p);
                    ficheroOK = true;
                } catch (IOException e) {
                    System.out.println("Error al intentar crear el fichero.");
                }
            }
            return ficheroOK;
        }

        public static boolean ficheroSePuedeEscribir(Path p) {

            // Método que comprueba si se puede escribir en un fichero y si no lo crea
            boolean ficheroOK = false;
            if (Files.exists(p)) {
                if (Files.isWritable(p)){
                    ficheroOK = true;
                }

            }
            else {
                try {
                    Files.createFile(p);
                    ficheroOK = true;
                } catch (IOException e) {
                    System.out.println("Error al intentar crear el fichero.");
                }
            }
            return ficheroOK;
        }


}
