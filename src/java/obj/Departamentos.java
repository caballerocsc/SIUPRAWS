/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obj;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.xml.bind.annotation.XmlTransient;
/**
 * Clase en la cual se hace el mapeo de la tabla Departamentos de la base de datos 
 * @author cesar.solano
 */

public class Departamentos implements Serializable {
    private static final long serialVersionUID = 1L;
    private int gid;
    private Short deptoid;
    private String codigodane;
    private String nombre;
    private String fuente;
    private Short domfkesc;
    private Date fechadato;
    private String nomcorto;
    private String nomlargo;
    private String ext;
    private Collection<Municipios> municipiosCollection;

    public Departamentos() {
    }

    public Departamentos(Short deptoid) {
        this.deptoid = deptoid;
    }

    public Departamentos(Short deptoid, int gid) {
        this.deptoid = deptoid;
        this.gid = gid;
    }

    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    public Short getDeptoid() {
        return deptoid;
    }

    public void setDeptoid(Short deptoid) {
        this.deptoid = deptoid;
    }

    public String getCodigodane() {
        return codigodane;
    }

    public void setCodigodane(String codigodane) {
        this.codigodane = codigodane;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFuente() {
        return fuente;
    }

    public void setFuente(String fuente) {
        this.fuente = fuente;
    }

    public Short getDomfkesc() {
        return domfkesc;
    }

    public void setDomfkesc(Short domfkesc) {
        this.domfkesc = domfkesc;
    }
    public Date getFechadato() {
        return fechadato;
    }

    public void setFechadato(Date fechadato) {
        this.fechadato = fechadato;
    }

    public String getNomcorto() {
        return nomcorto;
    }

    public void setNomcorto(String nomcorto) {
        this.nomcorto = nomcorto;
    }

    public String getNomlargo() {
        return nomlargo;
    }

    public void setNomlargo(String nomlargo) {
        this.nomlargo = nomlargo;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    @XmlTransient
    public Collection<Municipios> getMunicipiosCollection() {
        return municipiosCollection;
    }

    public void setMunicipiosCollection(Collection<Municipios> municipiosCollection) {
        this.municipiosCollection = municipiosCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (deptoid != null ? deptoid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Departamentos)) {
            return false;
        }
        Departamentos other = (Departamentos) object;
        if ((this.deptoid == null && other.deptoid != null) || (this.deptoid != null && !this.deptoid.equals(other.deptoid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "adminSIUPRA.Departamentos[ deptoid=" + deptoid + " ]";
    }
    
}
