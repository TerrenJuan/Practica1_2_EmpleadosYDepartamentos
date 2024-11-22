package com.iessanalberto.JTT.services;

import com.iessanalberto.JTT.libs.CheckFiles;
import com.iessanalberto.JTT.libs.Leer;
import com.iessanalberto.JTT.models.Empleado;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class EmpleadoService {
    private String mensaje;

    private String pedirEmpleados(List<Empleado> empleados) {
        Path p = Path.of("src/main/resources/empleados.csv");
        boolean salir = true;
        String nombre, antiguedad;
        int sueldo, añoNacimiento;
        do {
            //El usuario introduce todos los datos del empleado que quuiere añadir
            nombre = Leer.introduceString("Introduce nombre del empleado");
            sueldo = Leer.introduceEntero("Introduce sueldo del empleado");
            añoNacimiento = Leer.introduceEntero("Introduce año de nacimiento del empleado");
            antiguedad = Leer.introduceString("Introduce antigüedad del empleado");

            //Se crea el nuevo empleado
            Empleado empleado = new Empleado(nombre, sueldo, añoNacimiento, antiguedad);

            //Se añado el empleado a la lista de todos los empleados
            empleados.add(empleado);
            //comprobamos si el fichero introducide se puede escribir
            if(CheckFiles.ficheroEscribible(p)){
                //Escribimos en el fichero
                try(FileWriter writer = new FileWriter(p.toFile())){
                    //Recorremos todos los empleados para escribirlos en el csv
                    for(Empleado empleadoCSV : empleados){
                        //Creamos la linea con todos los datos del empleado para añadirla al csv
                        String linea = empleadoCSV.getNombre()+";"+
                                empleadoCSV.getSueldo()+";"+
                                empleadoCSV.getYear()+";"+
                                empleadoCSV.getAntiguedad();
                        //Escribimos la linea en el csv y saltamos la linea
                        writer.write(linea + "\n");
                    }
                    mensaje = "Escritura del empleado correcta";
                }catch(IOException e){
                    System.out.println("Error en la escritura del csv");
                }
            }else{
                System.out.println("No es posible escribir en este fichero");
            }
            //El usuario introducira true si quiere introducir más empleados o false si no quiere introducir más
            salir = Leer.introduceBoolean("Introduce true si quiere seguir añadiendo empleados, de lo contrario introduzca false");
        } while (salir);
        return  mensaje;
    }

}
