/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obj;

/**
 *
 * @author cesar.solano
 */
public class Precios {
    
    private String id;
    private String nombre;
    private String rango;
    private double area;
    private String idDept;
    private String departamento;
    private String idMun;
    private String municipio;
    
    public Precios(){
        
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRango() {
        return rango;
    }

    public void setRango(String rango) {
        this.rango = rango;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public String getIdDept() {
        return idDept;
    }

    public void setIdDept(String idDept) {
        this.idDept = idDept;
    }

    public String getIdMun() {
        return idMun;
    }

    public void setIdMun(String idMun) {
        this.idMun = idMun;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }
    
    
    
}
