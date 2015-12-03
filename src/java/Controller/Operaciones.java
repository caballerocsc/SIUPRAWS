/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;

import db.Consultas;
import util.Conversion;

/**
 *
 * @author Usuario
 */
public class Operaciones {

    public Operaciones() {
    }

    public String getDepartamentosMun(){
        Consultas con=new Consultas();
        Conversion conv = new Conversion();
        return conv.DepartMuntoJson(con.getDepartamentosMun(),con.getMenuConsultas());
    }
    
    public String getTablaContenido(){
        Consultas con= new Consultas();
        Conversion conv=new Conversion();
        return conv.tablasServiciosCapas(getCapas(), getServicios(), conv.TablaContenidotoJson(con.getTabladeContenido()));
        //return conv.TablaContenidotoJson(con.getTabladeContenido());
    }
    
    public String getServicios(){
        Consultas con= new Consultas();
        Conversion conv=new Conversion();
        return conv.ServiciostoJson(con.getServicios());
    }
    
    public String getCapas(){
        Consultas con= new Consultas();
        Conversion conv=new Conversion();
        return conv.capastoJson(con.getCapas());
    }
}
