/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obj;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author cesar.solano
 */
public class Capas implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private BigDecimal acceso;
    private String alias;
    private String aliasgrupo;
    private String aliasservicio;
    private BigDecimal escmax;
    private BigDecimal escmin;
    private String filtro;
    private String formato;
    private String limites;
    private String nombre_capa;
    private BigDecimal opacidad;
    private String crs;
    private String titulo;
    private String tipo_serv;
    private boolean estado;
    private Boolean autoident;
    private Boolean base;
    private Boolean leyenda_c;
    private Boolean identific;
    private Boolean ordenable;
    private Boolean transparente;
    private Boolean visible;
    private Integer anio;
    private String descripcion;
    private String escala;
    private String fuente;
    private String imagen;
    private String nombre;
    private String palab_cl;
    private int orden;
    private int tipo;
    private boolean vistaGeral;
    private int id_servicio;
    private String aliasTablaContendio;
    

    public Capas() {
    }

    public Capas(Integer capasupraid) {
        this.id = capasupraid;
    }

    public Capas(Integer capasupraid, String alias, String aliasgrupo, String aliasservicio, String nombrecapa, String titulo, boolean estado, String nombre, int orden, int tipo) {
        this.id = capasupraid;
        this.alias = alias;
        this.aliasgrupo = aliasgrupo;
        this.aliasservicio = aliasservicio;
        this.nombre_capa = nombrecapa;
        this.titulo = titulo;
        this.estado = estado;
        this.nombre = nombre;
        this.orden = orden;
        this.tipo = tipo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getAcceso() {
        return acceso;
    }

    public void setAcceso(BigDecimal acceso) {
        this.acceso = acceso;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getAliasgrupo() {
        return aliasgrupo;
    }

    public void setAliasgrupo(String aliasgrupo) {
        this.aliasgrupo = aliasgrupo;
    }

    public String getAliasservicio() {
        return aliasservicio;
    }

    public void setAliasservicio(String aliasservicio) {
        this.aliasservicio = aliasservicio;
    }

    public BigDecimal getEscmax() {
        return escmax;
    }

    public void setEscmax(BigDecimal escmax) {
        this.escmax = escmax;
    }

    public BigDecimal getEscmin() {
        return escmin;
    }

    public void setEscmin(BigDecimal escmin) {
        this.escmin = escmin;
    }

    public String getFiltro() {
        return filtro;
    }

    public void setFiltro(String filtro) {
        this.filtro = filtro;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public String getLimites() {
        return limites;
    }

    public void setLimites(String limites) {
        this.limites = limites;
    }

    public String getNombre_capa() {
        return nombre_capa;
    }

    public void setNombre_capa(String nombre_capa) {
        this.nombre_capa = nombre_capa;
    }

    public BigDecimal getOpacidad() {
        return opacidad;
    }

    public void setOpacidad(BigDecimal opacidad) {
        this.opacidad = opacidad;
    }

    public String getCrs() {
        return crs;
    }

    public void setCrs(String crs) {
        this.crs = crs;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTipo_serv() {
        return tipo_serv;
    }

    public void setTipo_serv(String tipo_serv) {
        this.tipo_serv = tipo_serv;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Boolean getAutoident() {
        return autoident;
    }

    public void setAutoident(Boolean autoident) {
        this.autoident = autoident;
    }

    public Boolean getBase() {
        return base;
    }

    public void setBase(Boolean base) {
        this.base = base;
    }

    public Boolean getLeyenda_c() {
        return leyenda_c;
    }

    public void setLeyenda_c(Boolean leyenda_c) {
        this.leyenda_c = leyenda_c;
    }

    public Boolean getIdentific() {
        return identific;
    }

    public void setIdentific(Boolean identific) {
        this.identific = identific;
    }

    public Boolean getOrdenable() {
        return ordenable;
    }

    public void setOrdenable(Boolean ordenable) {
        this.ordenable = ordenable;
    }

    public Boolean getTransparente() {
        return transparente;
    }

    public void setTransparente(Boolean transparente) {
        this.transparente = transparente;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEscala() {
        return escala;
    }

    public void setEscala(String escala) {
        this.escala = escala;
    }

    public String getFuente() {
        return fuente;
    }

    public void setFuente(String fuente) {
        this.fuente = fuente;
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

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getId_servicio() {
        return id_servicio;
    }

    public void setId_servicio(int id_servicio) {
        this.id_servicio = id_servicio;
    }

    public boolean isVistaGeral() {
        return vistaGeral;
    }

    public void setVistaGeral(boolean vistaGeral) {
        this.vistaGeral = vistaGeral;
    }

    public String getAliasTablaContendio() {
        return aliasTablaContendio;
    }

    public void setAliasTablaContendio(String aliasTablaContendio) {
        this.aliasTablaContendio = aliasTablaContendio;
    }
    
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Capas)) {
            return false;
        }
        Capas other = (Capas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "admin.Capas[ capasupraid=" + id + " ]";
    }
    
}
