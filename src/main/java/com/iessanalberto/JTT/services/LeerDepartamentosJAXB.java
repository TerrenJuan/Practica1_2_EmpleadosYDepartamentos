package com.iessanalberto.JTT.services;

import com.iessanalberto.JTT.libs.CheckFiles;
import com.iessanalberto.JTT.models.Departamentos;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class LeerDepartamentosJAXB {
    public String mensaje ="";
    public List<Departamentos> leerDepartamentos() {
        List<Departamentos> listaDepartamentos = new ArrayList<Departamentos>();
        Departamentos departamentos = new Departamentos();
        //Leeremos el archivo departamentos.xml con JAXB para pasarlo a una clase
        //Archivo a leer
        Path p = Path.of("src/main/resources/departamentos.xml");

        if(CheckFiles.ficheroReadable(p)){
            //Creo las variables donde almacenar en mi código la información
            //Un contexto se utiliza cuando en nuestro código hemos cargado datos persistentes
            JAXBContext contexto = null;
            try{
                contexto = JAXBContext.newInstance(Departamentos.class);
                //Para pasar el codigo de XML -- Unmarshaller
                Unmarshaller unmarshaller = contexto.createUnmarshaller();
                departamentos = (Departamentos) unmarshaller.unmarshal(p.toFile());
            }catch(JAXBException ex){
                mensaje = "Error al leer el XML.";
            }
        }
        return listaDepartamentos;
    }
}
