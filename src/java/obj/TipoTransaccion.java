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
public class TipoTransaccion {
    private int ano;
    private double compraventa;
    private double hipoteca;
    private double remate;
    private double permuta;
    private double embargo;
    private double peso;

    public TipoTransaccion() {
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
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
