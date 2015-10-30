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
 * Clase encargada de unir la capa de base de datos con la capa de logica del negocio
 * en la cual se encuentra la clase encargada de generar los Json y retornar un String
 */
public class Operaciones {

    public Operaciones() {
    }

    /**
     * Método que trae los departamentos con sus municipios
     * @return retorna el resultado en texto de formato json
     */
    public String getDepartamentosMun(){
        Consultas con=new Consultas();
        Conversion conv = new Conversion();
//        con.consultaPrecios("");
//        con.consultaDinamicaMerc();
//        con.consultaAvaluos("limit 10");
        return conv.DepartMuntoJson(con.getDepartamentosMun(),con.getMenuConsultas());
    }
    
    /**
     * Método que retorna la tabla de contenido con los servicios y las capas
     * @return retorna el resultado en texto de formato json
     */
    public String getTablaContenido(){
        Consultas con= new Consultas();
        Conversion conv=new Conversion();
        return conv.tablasServiciosCapas(getCapas(), getServicios(), conv.TablaContenidotoJson(con.getTabladeContenido()));
        //return conv.TablaContenidotoJson(con.getTabladeContenido());
    }
    
    /**
     * Método que retorna los servicios geográficos que dispone la aplicación
     * @return retorna el resultado en texto de formato json 
     */
    public String getServicios(){
        Consultas con= new Consultas();
        Conversion conv=new Conversion();
        return conv.ServiciostoJson(con.getServicios());
    }
    
    /**
     * Método que retorna las capas que se pueden consultar en la aplicación
     * @return retorna el resultado en texto de formato json
     */
    public String getCapas(){
        Consultas con= new Consultas();
        Conversion conv=new Conversion();
        return conv.capastoJson(con.getCapas());
    }
    
    /**
     * Método que recibe un alias de la consulta y consulta los resultados
     * los transforma a json y retorna el resultado
     * @param alias de la consulta que se selecciono
     * @return String en formato json con la informacion de la base de datos
     */
    public String getFiltroConsulta(String alias){
        Consultas con= new Consultas();
        Conversion conv=new Conversion();
        return conv.filtrostoJson(con.consultaFiltros(alias));
    }
    
    /**
     *  Método que recibe un identificador y selecciona la consulta que hay que realizar 
     * @param id de la consulta que hay que realizar
     * @return resultado de la consulta
     */
    public String seleccionarConsulta(int id){
        Consultas con= new Consultas();
        switch(id){
            case 13:
                con.consultaPrecios("");
                break;
            case 19:
                con.consultaAvaluos("");
                break;
            case 24:
                con.consultaDinamicaMerc();
                break;
        }
        return null;
    }
}
