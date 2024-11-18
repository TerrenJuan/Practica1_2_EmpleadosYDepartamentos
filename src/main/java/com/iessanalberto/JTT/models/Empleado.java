package com.iessanalberto.JTT.models;


import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "empleado")
@XmlAccessorType(XmlAccessType.NONE)
public class Empleado {


        public String nombre;
        public int sueldo;
        public int year;
        public String antiguedadEmpleado;
        public int idDep;

        public Empleado() {
        }

        public Empleado(String nombre, int sueldo, int year, String antiguedad) {
            this.nombre = nombre;
            this.sueldo = sueldo;
            this.year = year;
            this.antiguedadEmpleado = antiguedad;
        }

        public int getDepartamento() {
            return idDep;
        }

        public void setDepartamento(int departamento) {
            this.idDep = departamento;
        }

        @XmlElement(name = "nombre")
        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        @XmlElement(name = "sueldo")
        public int getSueldo() {
            return sueldo;
        }

        public void setSueldo(int sueldo) {
            this.sueldo = sueldo;
        }

        @XmlElement(name = "año")
        public int getYear() {
            return year;
        }

        public void setYear(int yearNacimiento) {
            this.year = yearNacimiento;
        }

        @XmlElement
        public String getAntiguedad() {
            return antiguedadEmpleado;
        }

        public void setAntiguedad(String antiguedad) {
            this.antiguedadEmpleado = antiguedad;
        }

}
