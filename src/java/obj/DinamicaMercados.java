/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obj;

/**
 * Clase para hacer el mapeo a objetos de la informacion en la tabla
 * de dinámica de mercados en la base de datos
 * @author cesar.solano
 */
public class DinamicaMercados {

    private String idTabla;
    private String idDepart;
    private String departamento;
    private int anio;
    private double compraventa;
    private double hipoteca;
    private double remate;
    private double permuta;
    private double embargo;
    private double peso;
    
    public DinamicaMercados() {
    }

    public String getIdTabla() {
        return idTabla;
    }

    public void setIdTabla(String idTabla) {
        this.idTabla = idTabla;
    }

    public String getIdDepart() {
        return idDepart;
    }

    public void setIdDepart(String idDepart) {
        this.idDepart = idDepart;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public double getCompraventa() {
        return compraventa;
    }

    public void setCompraventa(double compraventa) {
        this.compraventa = compraventa;
    }

    public double getHipoteca() {
        return hipoteca;
    }

    public void setHipoteca(double hipoteca) {
        this.hipoteca = hipoteca;
    }

    public double getRemate() {
        return remate;
    }

    public void setRemate(double remate) {
        this.remate = remate;
    }

    public double getPermuta() {
        return permuta;
    }

    public void setPermuta(double permuta) {
        this.permuta = permuta;
    }

    public double getEmbargo() {
        return embargo;
    }

    public void setEmbargo(double embargo) {
        this.embargo = embargo;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }
    
}
