/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package obj;

import java.math.BigDecimal;

/**
 * Clase encargada de mapaear las consultas de restricciones y exlusiones agropecuarias
 * tambien se utiliza para las consultas de indicadores
 * @author cesar.solano
 */
public class Areas {


    public Areas() {
        this.condicionante = BigDecimal.ZERO;
        this.exclusion = BigDecimal.ZERO;
        this.sinRestriccion = BigDecimal.ZERO;
        this.incluidas = BigDecimal.ZERO;
        this.areaDepto = BigDecimal.ZERO;
        this.areaCond = BigDecimal.ZERO;
        this.areaIncl = BigDecimal.ZERO;
        this.areaExcl = BigDecimal.ZERO;
        this.area = BigDecimal.ZERO;
    }
    
    
    
    private String codigoDane;
    private BigDecimal condicionante;//para la consulta de exclusiones se utiliza este campo homologando a restringido
    private BigDecimal exclusion;
    private BigDecimal sinRestriccion;
    private String departamento;
    private BigDecimal incluidas;
    private BigDecimal areaDepto;
    private BigDecimal areaCond;
    private BigDecimal areaIncl;
    private BigDecimal areaExcl;
    private BigDecimal area;
    private String tipo;
    private String municipio;
    private BigDecimal zonaAlta;
    private BigDecimal zonaMedia;
    private BigDecimal zonaBaja;
    private BigDecimal zonaNoApta;

    public String getCodigoDane() {
        return codigoDane;
    }

    public void setCodigoDane(String codigoDane) {
        this.codigoDane = codigoDane;
    }

    public BigDecimal getCondicionante() {
        return condicionante;
    }

    public void setCondicionante(BigDecimal condicionante) {
        this.condicionante = condicionante;
    }

    public BigDecimal getExclusion() {
        return exclusion;
    }

    public void setExclusion(BigDecimal exclusion) {
        this.exclusion = exclusion;
    }

    public BigDecimal getSinRestriccion() {
        return sinRestriccion;
    }

    public void setSinRestriccion(BigDecimal sinRestriccion) {
        this.sinRestriccion = sinRestriccion;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public BigDecimal getAreaDepto() {
        return areaDepto;
    }

    public void setAreaDepto(BigDecimal areaDepto) {
        this.areaDepto = areaDepto;
    }

    public BigDecimal getIncluidas() {
        return incluidas;
    }

    public void setIncluidas(BigDecimal incluidas) {
        this.incluidas = incluidas;
    }

    public BigDecimal getAreaCond() {
        return areaCond;
    }

    public void setAreaCond(BigDecimal areaCond) {
        this.areaCond = areaCond;
    }

    public BigDecimal getAreaIncl() {
        return areaIncl;
    }

    public void setAreaIncl(BigDecimal areaIncl) {
        this.areaIncl = areaIncl;
    }

    public BigDecimal getAreaExcl() {
        return areaExcl;
    }

    public void setAreaExcl(BigDecimal areaExcl) {
        this.areaExcl = areaExcl;
    }

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String categoria) {
        this.tipo = categoria;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public BigDecimal getZonaAlta() {
        return zonaAlta;
    }

    public void setZonaAlta(BigDecimal zonaAlta) {
        this.zonaAlta = zonaAlta;
    }

    public BigDecimal getZonaMedia() {
        return zonaMedia;
    }

    public void setZonaMedia(BigDecimal zonaMedia) {
        this.zonaMedia = zonaMedia;
    }

    public BigDecimal getZonaBaja() {
        return zonaBaja;
    }

    public void setZonaBaja(BigDecimal zonaBaja) {
        this.zonaBaja = zonaBaja;
    }

    public BigDecimal getZonaNoApta() {
        return zonaNoApta;
    }

    public void setZonaNoApta(BigDecimal zonaNoApta) {
        this.zonaNoApta = zonaNoApta;
    }

}
