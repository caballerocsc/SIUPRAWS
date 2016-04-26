/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package obj;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 *
 * @author Usuario
 */
public class Restricciones {

    public Restricciones() {
    }
    
    private String codigoDane;
    private float condicionante;
    private float exclusion;
    private float sinRestriccion;
    private String departamento;
    private BigDecimal areaDepto;

    public String getCodigoDane() {
        return codigoDane;
    }

    public void setCodigoDane(String codigoDane) {
        this.codigoDane = codigoDane;
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

    public float getCondicionante() {
        return condicionante;
    }

    public void setCondicionante(float condicionante) {
        this.condicionante = condicionante;
    }

    public float getExclusion() {
        return exclusion;
    }

    public void setExclusion(float exclusion) {
        this.exclusion = exclusion;
    }

    public float getSinRestriccion() {
        return sinRestriccion;
    }

    public void setSinRestriccion(float sinRestriccion) {
        this.sinRestriccion = sinRestriccion;
    }
    
    
}
