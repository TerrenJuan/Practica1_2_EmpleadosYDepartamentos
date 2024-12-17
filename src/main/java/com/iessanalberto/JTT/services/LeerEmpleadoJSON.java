package com.iessanalberto.JTT.services;

import com.google.gson.Gson;
import com.iessanalberto.JTT.libs.CheckFiles;
import com.iessanalberto.JTT.models.Empleado;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class LeerEmpleadoJSON {
    private String mensaje= "";

    public String leerNuevosEmpleadosJSON(List<Empleado> empleados) {

        // Cogemos la fecha actual en una variable
        SimpleDateFormat formateo = new SimpleDateFormat("yyyy/MM/dd");
        Date fecha = new Date(System.currentTimeMillis());
        String fechaFormateada = formateo.format(fecha);
        Path p = Path.of("src/main/resources/nuevosEmpleados.json");

        // Varaible para almacenar el contenido del fichero
        Empleado[] empleadoJson;
        // Leemos el contenido del Json, que es un texto
        String txtJson;
        // Leemos el contenido del archivo de texto
        try{
            if(CheckFiles.ficheroReadable(p)){
                txtJson = Files.readString(p);
                // Creamos el Gson que transforma de texto a objeto
                Gson gson = new Gson();
                empleadoJson = gson.fromJson(txtJson,Empleado[].class);
                for(Empleado empleado: empleadoJson){
                    empleado.setAntiguedad(fechaFormateada);
                    empleados.add(empleado);
                }
                Path p2 = Path.of("src/main/resources/empleados.csv");
                if(CheckFiles.ficheroEscribible(p2)){
                    //Escribimos en el fichero
                    try(FileWriter writer = new FileWriter(p2.toFile())){
                        //Recorremos todos los empleados para escribirlos en el csv
                        for(Empleado empleadoCSV : empleados){
                            //Creamos la linea con todos los datos del empleado para añadirla al csv
                            String linea = empleadoCSV.getNombre()+";"+empleadoCSV.getSueldo()+";"+
                                    empleadoCSV.getYear()+";"+empleadoCSV.getAntiguedad();
                            //Escribimos la linea en el csv y saltamos la linea
                            writer.write(linea + "\n");
                        }
                        mensaje = "Escritura del empleado correcta desde el archivo json";
                    }catch(IOException e){
                        mensaje = "Error en la escritura del csv";
                    }
                }else{
                    mensaje = "No es posible escribir en este fichero";
                }
            }else{
                mensaje = "El fichero no se puede leer";
            }
        }catch(IOException ex){
            mensaje = "El fichero no se ha podido crear";
        }
        return mensaje ;
    }

}
