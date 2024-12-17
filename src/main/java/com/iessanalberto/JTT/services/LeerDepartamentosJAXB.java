package com.iessanalberto.JTT.services;

import com.iessanalberto.JTT.libs.CheckFiles;
import com.iessanalberto.JTT.models.Departamento;
import com.iessanalberto.JTT.models.Departamentos;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class LeerDepartamentosJAXB {
    public String mensaje ="";
    public List<Departamento> leerDepartamentos() {
        List<Departamento> listaDepartamentos = new ArrayList<Departamento>();

        //Leeremos el archivo departamentos.xml con JAXB para pasarlo a una clase
        //Archivo a leer
        Path p = Path.of("src/main/resources/departamentos.xml");

        if(CheckFiles.ficheroReadable(p)){
            Departamentos departamentos = new Departamentos();
            Departamento departamento = new Departamento();
            //Creo las variables donde almacenar en mi código la información
            //Un contexto se utiliza cuando en nuestro código hemos cargado datos persistentes
            JAXBContext  jaxbContext = null;
            try{
                jaxbContext = JAXBContext.newInstance(Departamentos.class);
                //Para pasar el codigo de XML -- Unmarshaller
                Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
                departamentos = (Departamentos) unmarshaller.unmarshal(p.toFile());
                for(Departamento dep: departamentos.getDepartamentos()){
                    listaDepartamentos.add(dep);
                }
            }catch(JAXBException ex){
                mensaje = "Error al leer el XML.";
            }
        }
        return listaDepartamentos;
    }
}
