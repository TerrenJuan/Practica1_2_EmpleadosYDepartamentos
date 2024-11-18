package com.iessanalberto.JTT.menus;


import java.util.Scanner;

public class MenuPrincipal {
    private boolean salir = false;
    private Scanner sc = new Scanner(System.in);
        public void mostrarMenuPrincipal(){
        String opcion;
        do {
            System.out.println("-------------------------------------------");
            System.out.println("MENU PRINCIPAL - Gestión empleados y departamentos");
            System.out.println("-------------------------------------------");
            System.out.println("Elige una opción:\n"+"\n"+
                "1. Pedir Empleados\n"+
                "2. Leer Departamentos\n"+
                "3. Asignar empleado en departamento\n"+
                "4. Leer JSON\n"+
                "4. Mostrar información de la empresa empresa\n"+
                "0. Salir."
            );
            System.out.println("-------------------------------------------");
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



}
