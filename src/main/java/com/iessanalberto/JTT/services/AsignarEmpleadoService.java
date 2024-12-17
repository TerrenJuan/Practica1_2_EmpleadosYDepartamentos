package com.iessanalberto.JTT.services;

import com.iessanalberto.JTT.libs.Leer;
import com.iessanalberto.JTT.models.Departamento;

import com.iessanalberto.JTT.models.Empleado;

import java.util.ArrayList;
import java.util.List;

public class AsignarEmpleadoService {

    EmpleadoService empleadoService = new EmpleadoService();
    LeerDepartamentosJAXB leerDepartamentos = new LeerDepartamentosJAXB();
    private String mensaje = "";
    public String  asignarCadaEmpleadoDepartamento() {
        boolean salir = false;
        ArrayList<Empleado> empleados = empleadoService.obtenerEmpleados();

        List<Departamento> listaDepartamentos = leerDepartamentos.leerDepartamentos();
        Departamento departamento = null;


        //Recorremos toda la lista empleados dependiendo de su tamaño
        for (Empleado empleado : empleados) {

            // Mostramos todos los nombres de los departamentos que tenemos
            Leer.mostrarEnPantalla("Lista de departamentos: ");
            // Recorremos e
            for (int y = 1; y <= listaDepartamentos.size(); y++) {
                Leer.mostrarEnPantalla("\t" + y + "." + listaDepartamentos.get(y - 1).getNombre());
            }
            //El usuario debe introducir el departamentro al que pertenece el empleado
            int departamentoElegido = Leer.introduceEntero("Introduce el departamento que quieres para el empleado: " + empleado.getNombre());
            //Se introduce el departamento elegido al empleado
            if (departamentoElegido <= listaDepartamentos.size()) {
                empleado.setDepartamento(departamentoElegido);

            } else {
                mensaje = "El número introducido no corresponde con un departamento";

            }

        }
        return mensaje;
    }
}
