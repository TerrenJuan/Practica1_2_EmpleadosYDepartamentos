package com.iessanalberto.JTT.services;

import com.iessanalberto.JTT.libs.Leer;
import com.iessanalberto.JTT.models.Departamento;
import com.iessanalberto.JTT.models.Departamentos;
import com.iessanalberto.JTT.models.Empleado;

import java.util.List;

public class AsignarEmpleadoService {

    private static void asignarCadaEmpleadoDepartamento(List<Empleado> empleados, List<Departamento> listaDepartamentos) {
        boolean salir = false;
        Departamento departamento = null;
        //Recorremos toda la lista empleados dependiendo de su tamaño
        for(int i = 0;i<empleados.size();i++){
            do{
                // Mostramos todos los nombres de los departamentos que tenemos
                Leer.mostrarEnPantalla("Lista de departamentos: ");
                // Recorremos e
                for(int y=1;y<=listaDepartamentos.size();y++){
                    Leer.mostrarEnPantalla("\t"+y +"."+listaDepartamentos.get(y-1).getNombre());
                }
                //El usuario debe introducir el departamentro al que pertenece el empleado
                int departamentoElegido = Leer.introduceEntero("Introduce el departamento que quieres para el empleado: " + empleados.get(i).getNombre());
                //Se introduce el departamento elegido al empleado
                if(departamentoElegido<=listaDepartamentos.size()){
                    empleados.get(i).setDepartamento(departamentoElegido);
                    salir = true;
                }else{
                    System.out.println("El número introducido no corresponde con un departamento");
                    salir=false;
                }
            }while(!salir);
        }
    }
}
