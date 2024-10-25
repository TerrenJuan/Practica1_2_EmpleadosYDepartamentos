package com.iessanalberto.JTT.menus;

import java.nio.file.Path;
import java.util.Scanner;

public class MenuPrincipal {
    private boolean salir = false;
    private Scanner sc = new Scanner(System.in);
        public void mostrarMenuPrincipal(){
        String opcion;
        do {
            System.out.println("Elige una opción:"+"\n"+
                "1. Leer XML con JAXB-Insituto"+"\n"+
                "2. Leer XML con JAXB-Espacios"+"\n"+
                "3. Escribir XML con JAXB"+"\n"+
                "0. Salir."
            );
            opcion = this.pideOpcion();
            this.procesarOpcion(opcion);
        } while (!salir);
    }

    private void procesarOpcion(String opcion) {
        switch (opcion) {
            case "0" -> salir = true;
            case "1" -> {

            }
            case "2" -> {

            }
            case "3" -> {}
            default -> System.out.println("Opción no válida.");
        }
    }

    private String pideOpcion() {
        return this.sc.nextLine();
    }

    public String pideRuta(){
        System.out.println("Introuce la ruta");
        return this.sc.nextLine();
    }

}
