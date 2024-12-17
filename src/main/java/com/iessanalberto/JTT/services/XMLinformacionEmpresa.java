package com.iessanalberto.JTT.services;

import com.iessanalberto.JTT.libs.CheckFiles;
import com.iessanalberto.JTT.models.Departamentos;
import com.iessanalberto.JTT.models.Empresa;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class XMLinformacionEmpresa {
    private String mensaje = "";
    LeerDepartamentosJAXB leerDepartamentosJAXB = new LeerDepartamentosJAXB();
    EmpleadoService empleadoService = new EmpleadoService();
    public String crearXMLinformacionEmpresa() {
        Path p = Path.of("src/main/resources/empresa.xml");

        try {
            // Si el archivo no existe lo creamos
            if (!Files.exists(p)) {
                Files.createFile(p);
            }
            // Comprobamos si se puede escribir el fichero
            if (CheckFiles.ficheroEscribible(p)){
                // Generamos el fichero
                try{
                    JAXBContext jaxbContext = JAXBContext.newInstance(Empresa.class);
                    //  Creamos el marshaller para pasarle el codigo
                    Marshaller marshaller = jaxbContext.createMarshaller();

                    // Configuramos el formato del xml
                    marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
                    // Generamos el objeto
                    Empresa empresa = new Empresa();
                    empresa.setDepartamentos(new Departamentos());
                    empresa.setEmpleados(empleadoService.obtenerEmpleados());
                    // Escribimos el xml
                    marshaller.marshal(empresa, p.toFile());
                    mensaje = "XML creado con éxito.";

                } catch (JAXBException e) {
                    mensaje = "Error al escribir el XML";
                }
            }
            else {
                mensaje = "No se pudo escribir el XML";
            }


        } catch (IOException e) {
            mensaje = "Error al crear el fichero";
        }
        return mensaje;
    }
}