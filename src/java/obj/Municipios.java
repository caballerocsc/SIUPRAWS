/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obj;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.postgis.Geometry;
import org.postgis.PGgeometry;

/**
 *
 * @author cesar.solano
 */
public class Municipios implements Serializable {
    private static final long serialVersionUID = 1L;
    private int gid;
    private Integer municipid;
    private String nombre;
    private String codigodane;
    private BigInteger areaHa;
    private BigInteger areaKm2;
    private Geometry geom;
    private Integer domfkesc;
    private Date fechadato;
    private String fuente;
    private Integer codigodanedepto;
    private String codigodanempios;
    private String nomcorto;
    private String nomlargo;
    private String ext;
    private int deptosfk;

    public Municipios() {
    }

    public Municipios(Integer municipid) {
        this.municipid = municipid;
    }

    public Municipios(Integer municipid, int gid) {
        this.municipid = municipid;
        this.gid = gid;
    }

    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    public Integer getMunicipid() {
        return municipid;
    }

    public void setMunicipid(Integer municipid) {
        this.municipid = municipid;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigodane() {
        return codigodane;
    }

    public void setCodigodane(String codigodane) {
        this.codigodane = codigodane;
    }

    public BigInteger getAreaHa() {
        return areaHa;
    }

    public void setAreaHa(BigInteger areaHa) {
        this.areaHa = areaHa;
    }

    public BigInteger getAreaKm2() {
        return areaKm2;
    }

    public void setAreaKm2(BigInteger areaKm2) {
        this.areaKm2 = areaKm2;
    }

    public Geometry getGeom() {
        return geom;
    }

    public void setGeom(Geometry geom) {
        this.geom = geom;
    }

    public Integer getDomfkesc() {
        return domfkesc;
    }

    public void setDomfkesc(Integer domfkesc) {
        this.domfkesc = domfkesc;
    }

    public Date getFechadato() {
        return fechadato;
    }

    public void setFechadato(Date fechadato) {
        this.fechadato = fechadato;
    }

    public String getFuente() {
        return fuente;
    }

    public void setFuente(String fuente) {
        this.fuente = fuente;
    }

    public Integer getCodigodanedepto() {
        return codigodanedepto;
    }

    public void setCodigodanedepto(Integer codigodanedepto) {
        this.codigodanedepto = codigodanedepto;
    }

    public String getCodigodanempios() {
        return codigodanempios;
    }

    public void setCodigodanempios(String codigodanempios) {
        this.codigodanempios = codigodanempios;
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

    public int getDeptosfk() {
        return deptosfk;
    }

    public void setDeptosfk(int deptosfk) {
        this.deptosfk = deptosfk;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (municipid != null ? municipid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Municipios)) {
            return false;
        }
        Municipios other = (Municipios) object;
        if ((this.municipid == null && other.municipid != null) || (this.municipid != null && !this.municipid.equals(other.municipid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "adminSIUPRA.Municipios[ municipid=" + municipid + " ]";
    }
    
}
