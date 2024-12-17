package com.iessanalberto.JTT.services;

import com.iessanalberto.JTT.libs.CheckFiles;
import com.iessanalberto.JTT.libs.Leer;
import com.iessanalberto.JTT.models.Empleado;

import java.io.*;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class EmpleadoService {
    private String mensaje;

    // Metodo que  solicita al usuario introducir una serie de datos, que después añadiremos a un archivo csv
    public String pedirEmpleados() {
        // Lista donde añadiremos los empleados que genera el usuario

        ArrayList<Empleado> empleados = new ArrayList<>();
        Path p = Path.of("src/main/resources/empleados.csv");

        boolean salir = true;
        String nombre, antiguedad;
        int sueldo, yearNacimiento;
        do {
            SimpleDateFormat formateo = new SimpleDateFormat("dd/MM/yyyy");

            //El usuario introduce todos los datos del empleado que quuiere añadir
            nombre = Leer.introduceString("Introduce nombre del empleado");
            sueldo = Leer.introduceEntero("Introduce sueldo del empleado");
            yearNacimiento = Leer.introduceEntero("Introduce año de nacimiento del empleado");
            antiguedad = formateo.format(Leer.pedirDate("Introduce antigüedad del empleado dd/MM/yyyy"));

            //Se crea el nuevo empleado
            Empleado empleado = new Empleado(nombre, sueldo, yearNacimiento, antiguedad);

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
                    mensaje = "Escritura del csv correcta \n";
                }catch(IOException e){
                    mensaje = "Error en la escritura del csv";
                }
            }else{
                mensaje = "No es posible escribir en este fichero";
            }
            //El usuario introducira true si quiere introducir más empleados o false si no quiere introducir más, y saldremos al menu principal
            salir = Leer.introduceBoolean("Introduce 'true' si quiere seguir añadiendo empleados, de lo contrario introduzca 'false'.");
        } while (salir);
        return  mensaje;
    }


    public ArrayList<Empleado> obtenerEmpleados(){
        ArrayList<Empleado> empleados = new ArrayList<>();
        Empleado empleado;
        String empleadosCSV = "src/main/resources/empleados.csv";
        String linea = "";

        try {
            BufferedReader br = new BufferedReader(new FileReader(empleadosCSV));
            while((linea=br.readLine()) !=null){
                empleado = new Empleado();
                String[] datos = linea.split(";");
                empleado.setNombre(datos[0]);
                empleado.setSueldo(Integer.parseInt(datos[1]));
                empleado.setYear(Integer.parseInt(datos[2]));
                empleado.setAntiguedad(datos[3]);

                empleados.add(empleado);
            }
        } catch (FileNotFoundException e) {
            Leer.mostrarEnPantalla("Error en la lectura del csv.");
        } catch (IOException e) {
            Leer.mostrarEnPantalla("Error en la lectura del csv.");
        }

        return empleados;
    }
}
