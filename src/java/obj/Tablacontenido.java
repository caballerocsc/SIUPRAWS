/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obj;

import java.io.Serializable;

/**
 * Clase en la cual se hace el mapeo de la tabla Tablacontenido de la base de datos 
 * @author cesar.solano
 */
public class Tablacontenido implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer tablacontenidoupraid;
    private String alias;
    private String descripcion;
    private String imagen;
    private String nombre;
    private String palabrasclave;
    private String capas;
    private int orden;
    private boolean desplegado;

    public Tablacontenido() {
    }

    public Tablacontenido(Integer tablacontenidoupraid) {
        this.tablacontenidoupraid = tablacontenidoupraid;
    }

    public Tablacontenido(Integer tablacontenidoupraid, String alias, String nombre, String capas, int orden) {
        this.tablacontenidoupraid = tablacontenidoupraid;
        this.alias = alias;
        this.nombre = nombre;
        this.capas = capas;
        this.orden = orden;
    }

    public Integer getTablacontenidoupraid() {
        return tablacontenidoupraid;
    }

    public void setTablacontenidoupraid(Integer tablacontenidoupraid) {
        this.tablacontenidoupraid = tablacontenidoupraid;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPalabrasclave() {
        return palabrasclave;
    }

    public void setPalabrasclave(String palabrasclave) {
        this.palabrasclave = palabrasclave;
    }

    public String getCapas() {
        return capas;
    }

    public void setCapas(String capas) {
        this.capas = capas;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public boolean isDesplegado() {
        return desplegado;
    }

    public void setDesplegado(boolean desplegado) {
        this.desplegado = desplegado;
    }

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tablacontenidoupraid != null ? tablacontenidoupraid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tablacontenido)) {
            return false;
        }
        Tablacontenido other = (Tablacontenido) object;
        if ((this.tablacontenidoupraid == null && other.tablacontenidoupraid != null) || (this.tablacontenidoupraid != null && !this.tablacontenidoupraid.equals(other.tablacontenidoupraid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "adminSIUPRA.Tablacontenido[ tablacontenidoupraid=" + tablacontenidoupraid + " ]";
    }
    
}
