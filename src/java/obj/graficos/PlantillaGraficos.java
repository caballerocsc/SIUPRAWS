/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package obj.graficos;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class PlantillaGraficos {

    public PlantillaGraficos(){
        this.colores= new ArrayList<>();
        this.ejeX=new ArrayList<>();
        this.ejeY=new ArrayList<>();
        this.serie=new ArrayList<>();
        this.datos=new ArrayList<>();
    }
    
    private int id;
    private int ind;
    private String tipo;
    private String titulo;
    private List<String> colores;
    private List<String> ejeX;
    private List<String> ejeY;
    private List<String> serie;
    private List<String> datos;

    public int getInd() {
        return ind;
    }

    public void setInd(int ind) {
        this.ind = ind;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<String> getColores() {
        return colores;
    }

    public void setColores(List<String> colores) {
        this.colores = colores;
    }

    public List<String> getEjeX() {
        return ejeX;
    }

    public void setEjeX(List<String> ejeX) {
        this.ejeX = ejeX;
    }

    public List<String> getEjeY() {
        return ejeY;
    }

    public void setEjeY(List<String> ejeY) {
        this.ejeY = ejeY;
    }

    public List<String> getSerie() {
        return serie;
    }

    public void setSerie(List<String> serie) {
        this.serie = serie;
    }

    public List<String> getDatos() {
        return datos;
    }

    public void setDatos(List<String> datos) {
        this.datos = datos;
    }

   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
 
}
