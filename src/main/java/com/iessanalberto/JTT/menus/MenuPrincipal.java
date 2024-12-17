package com.iessanalberto.JTT.menus;


import com.iessanalberto.JTT.libs.Leer;
import com.iessanalberto.JTT.models.Departamento;
import com.iessanalberto.JTT.models.Departamentos;
import com.iessanalberto.JTT.services.*;

import java.util.Scanner;

public class MenuPrincipal {
    private boolean salir = false;
    private Scanner sc = new Scanner(System.in);
    
    EmpleadoService empleadoService = new EmpleadoService();
    LeerDepartamentosJAXB leerDepartamentosJAXB = new LeerDepartamentosJAXB();
    AsignarEmpleadoService asignarEmpleadoService = new AsignarEmpleadoService();
    LeerEmpleadoJSON leerEmpleadoJSON = new LeerEmpleadoJSON();
    JSONinformacionEmpresa jsoninformacionEmpresa = new JSONinformacionEmpresa();
    XMLinformacionEmpresa xmlinformacionEmpresa = new XMLinformacionEmpresa();

    private String mensaje = "";

    public void mostrarMenuPrincipal(){
        String opcion;
        do {
            System.out.println("-------------------------------------------");
            System.out.println("MENU PRINCIPAL - Gestión empleados y departamentos");
            System.out.println("-------------------------------------------");
            System.out.println("Elige una opción:\n\n"+
                "1. Pedir Empleados\n"+
                "2. Leer Departamentos\n"+
                "3. Asignar empleado a un departamento\n"+
                "4. Leer y cargar JSON\n"+
                "5. Generar información de la empresa (JSON y XML) \n"+
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
                Leer.mostrarEnPantalla(empleadoService.pedirEmpleados());

            }
            case "2" -> {
                for(Departamento departamento: leerDepartamentosJAXB.leerDepartamentos()){
                    Leer.mostrarEnPantalla("Departamento de: "+departamento.getNombre());
                }
            }
            case "3" -> {
                Leer.mostrarEnPantalla(asignarEmpleadoService.asignarCadaEmpleadoDepartamento(
                       ));
                }
            case "4" -> {
                Leer.mostrarEnPantalla(leerEmpleadoJSON.leerNuevosEmpleadosJSON(empleadoService.obtenerEmpleados()));
            }
            case "5" -> {
                Leer.mostrarEnPantalla(jsoninformacionEmpresa.crearJSONinformacionEmpresa());
                Leer.mostrarEnPantalla(xmlinformacionEmpresa.crearXMLinformacionEmpresa());
            }

            default -> {
                mensaje = "Opción no válida.";
                Leer.mostrarEnPantalla(mensaje);
            }
        }
    }

    private String pideOpcion() {
        return this.sc.nextLine();
    }



}
