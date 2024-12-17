package com.iessanalberto.JTT.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.iessanalberto.JTT.libs.CheckFiles;
import com.iessanalberto.JTT.models.Departamentos;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class JSONinformacionEmpresa {
    private String mensaje;
    EmpleadoService empleadoService = new EmpleadoService();
    public String crearJSONinformacionEmpresa(){
        String empleadosJson = null;
        String departamentosJson = null;

        // Convertimos a JSON
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.setPrettyPrinting().create();
        empleadosJson = gson.toJson(empleadoService.obtenerEmpleados());
        departamentosJson = gson.toJson(new Departamentos());
        Path p = Path.of("src/main/resources/empresa.json");
        try{
            // Si el archivo no existe lo creamos
            if (!Files.exists(p)){
                Files.createFile(p);
            }
            // Comprobamos que se pueda escribir
            if (CheckFiles.ficheroEscribible(p)){
                // Generamos el fichero
                try{
                    Files.writeString(p, empleadosJson + departamentosJson);
                    mensaje = "JSON creado con éxito.";
                } catch (IOException e) {
                    mensaje = "Error al escribir el JSON";
                }
            }
            else {
                mensaje = "No se pudo escribir el JSON";
            }
        } catch (IOException e) {
            mensaje = "Error al crear el JSON";
        }


        return mensaje;
    }
}
