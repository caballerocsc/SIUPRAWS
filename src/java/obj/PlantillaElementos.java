/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obj;

/**
 * Clase que tienen como funcion mapear los objectos de las tablas 
 * elementosgraficos, elementosinformacion, elementostablas
 * plantillas_elementos_graficos, plantillas_elementos_informacion, plantillas_elementos_tablas
 * @author cesar.solano
 */
public class PlantillaElementos {

    private String aliasElemento;
    private String nombreelemento;
    private String textoElemento;
    private int columnGroup;
    private String valorElemento;
    private int dependede;
    private boolean filtro;
    
    public PlantillaElementos() {
    }

    public String getAliasElemento() {
        return aliasElemento;
    }

    public void setAliasElemento(String aliasElemento) {
        this.aliasElemento = aliasElemento;
    }

    public String getNombreelemento() {
        return nombreelemento;
    }

    public void setNombreelemento(String nombreelemento) {
        this.nombreelemento = nombreelemento;
    }

    public String getTextoElemento() {
        return textoElemento;
    }

    public void setTextoElemento(String textoElemento) {
        this.textoElemento = textoElemento;
    }

    public int getColumnGroup() {
        return columnGroup;
    }

    public void setColumnGroup(int columnGroup) {
        this.columnGroup = columnGroup;
    }

    public String getValorElemento() {
        return valorElemento;
    }

    public void setValorElemento(String valorElemento) {
        this.valorElemento = valorElemento;
    }

    public int getDependede() {
        return dependede;
    }

    public void setDependede(int dependede) {
        this.dependede = dependede;
    }    

    public boolean isFiltro() {
        return filtro;
    }

    public void setFiltro(boolean filtro) {
        this.filtro = filtro;
    }
    
}
