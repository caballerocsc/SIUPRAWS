/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obj;

import java.util.List;

/**
 * Clase para hacer el mapeo a objetos de la informacion en la tabla
 * de dinámica de mercados en la base de datos
 * @author cesar.solano
 */
public class DinamicaMercados {

    private String id;
    private String departamento;
    private List<TipoTransaccion> tiposT;
    private String geo;
    
    public DinamicaMercados() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getGeo() {
        return geo;
    }

    public void setGeo(String geo) {
        this.geo = geo;
    }

    public List<TipoTransaccion> getTiposT() {
        return tiposT;
    }

    public void setTiposT(List<TipoTransaccion> tiposT) {
        this.tiposT = tiposT;
    }
    
}
