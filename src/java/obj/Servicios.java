/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obj;

import java.io.Serializable;

/**
 * Clase en la cual se hace el mapeo de la tabla Servicios de la base de datos 
 * @author cesar.solano
 */
public class Servicios implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String alias;
    private String capas;
    private String tipoServidor;
    private String url;
    private String version;
    private boolean estado;
    private Boolean importado;
    private String descripcion;
    private String nombre;
    private String palab_cl;
    private int orden;
    private int acceso;
    //private Collection<Capas> capasCollection;

    public Servicios() {
    }

    public Servicios(Integer serviciosupraid) {
        this.id = serviciosupraid;
    }

    public Servicios(Integer serviciosupraid, String alias, String capas, String url, boolean estado, String nombre, int orden) {
        this.id = serviciosupraid;
        this.alias = alias;
        this.capas = capas;
        this.url = url;
        this.estado = estado;
        this.nombre = nombre;
        this.orden = orden;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getCapas() {
        return capas;
    }

    public void setCapas(String capas) {
        this.capas = capas;
    }

    public String getTipoServidor() {
        return tipoServidor;
    }

    public void setTipoServidor(String tipo_serv) {
        this.tipoServidor = tipo_serv;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Boolean getImportado() {
        return importado;
    }

    public void setImportado(Boolean importado) {
        this.importado = importado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPalab_cl() {
        return palab_cl;
    }

    public void setPalab_cl(String palab_cl) {
        this.palab_cl = palab_cl;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public int getAcceso() {
        return acceso;
    }

    public void setAcceso(int acceso) {
        this.acceso = acceso;
    }

    
//    public Collection<Capas> getCapasCollection() {
//        return capasCollection;
//    }
//
//    public void setCapasCollection(Collection<Capas> capasCollection) {
//        this.capasCollection = capasCollection;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Servicios)) {
            return false;
        }
        Servicios other = (Servicios) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "admin.Servicios[ serviciosupraid=" + id + " ]";
    }
    
}

