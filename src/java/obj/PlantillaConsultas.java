/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obj;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que contiene las plantillas de información para las consultas
 * de usuario
 * @author cesar.solano
 */
public class PlantillaConsultas {
    
    private String AliasPlantilla;
    private String nombrePlantilla;
    private String textoPlantilla;
    private List<PlantillaElementos> column;
    private List<PlantillaElementos> columnGroup;
    private List<PlantillaElementos> propiedad;

    public PlantillaConsultas() {
        this.column=new ArrayList<>();
        this.columnGroup=new ArrayList<>();
        this.propiedad=new ArrayList<>();
    }

    public String getAliasPlantilla() {
        return AliasPlantilla;
    }

    public void setAliasPlantilla(String AliasPlantilla) {
        this.AliasPlantilla = AliasPlantilla;
    }

    public String getNombrePlantilla() {
        return nombrePlantilla;
    }

    public void setNombrePlantilla(String nombrePlantilla) {
        this.nombrePlantilla = nombrePlantilla;
    }

    public String getTextoPlantilla() {
        return textoPlantilla;
    }

    public void setTextoPlantilla(String textoPlantilla) {
        this.textoPlantilla = textoPlantilla;
    }

    public List<PlantillaElementos> getColumnGroup() {
        return columnGroup;
    }

    public void setColumnGroup(List<PlantillaElementos> columnGroup) {
        this.columnGroup = columnGroup;
    }

    public List<PlantillaElementos> getColumn() {
        return column;
    }

    public void setColumn(List<PlantillaElementos> column) {
        this.column = column;
    }

    public List<PlantillaElementos> getPropiedad() {
        return propiedad;
    }

    public void setPropiedad(List<PlantillaElementos> propiedad) {
        this.propiedad = propiedad;
    }
    
    
    /**
     * Método que permite agregar de a un solo objeto elementos a la lista de ColumnGroup
     * @param elemento objecto que será agregado
     */
    public void agregarColumnGroup(PlantillaElementos elemento){
        this.columnGroup.add(elemento);
    }
    
     /**
     * Método que permite agregar de a un solo objeto elementos a la lista de Column
     * @param elemento objecto que será agregado
     */
    public void agregarColumn(PlantillaElementos elemento){
        this.column.add(elemento);
    }
    
    /**
     * Método que permite agregar de a un solo objeto elementos a la lista de propiedades
     * @param elemento objecto que será agregado
     */
    public void agregarPropiedad(PlantillaElementos elemento){
        this.propiedad.add(elemento);
    }
}
