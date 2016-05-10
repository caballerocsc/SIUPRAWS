/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;

import com.google.gson.Gson;
import db.Consultas;
import obj.FiltroJson;
import obj.Parametros;
import util.Conversion;

/**
 *
 * @author Usuario
 * Clase encargada de unir la capa de base de datos con la capa de logica del negocio, en esta última
 * se encuentra la clase encargada de generar los Json (Conversion) y retornar un String
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
     * Método que recibe el alias de la consulta y consulta los resultados
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
     * @param alias de la consulta que hay que realizar
     * @param json cadena en formato json con los filtros seleccionados por el usuario
     * @return resultado de la consulta
     */
    public String seleccionarConsulta(String alias , String json){
        FiltroJson fil=convertirJsonFiltro(json);
        Consultas con= new Consultas();
        Conversion conv=new Conversion();
        String resultado="";
        switch(alias){
            case Parametros.PRECIOS:{
                resultado=conv.crearJsonPrecios(con.consultarTablaContenidoporMenuConsulta(alias), con.consultarServicioporMenuConsulta(alias), 
                        con.consultarCapasporMenuConsulta(alias), fil, con.consultaPrecios(fil),con.consultaSumatoriaPrecios(fil));
                break;
            }
            case Parametros.AVALUOS:{
                con.consultaAvaluos(fil);
                break;
            }
            case Parametros.TRANSACCIONES:{
                //List<DinamicaMercados> dm=;
                resultado=conv.crearJsonDinamMerc(fil, con.consultaDinamicaMerc(fil), con.consultarTablaContenidoporMenuConsulta(alias), 
                        con.consultarServicioporMenuConsulta(alias), con.consultarCapasporMenuConsulta(alias));
                break;
            }
            case Parametros.RESTRICCIONES:{
                resultado=conv.crearJsonRestricciones(con.consultarTablaContenidoporMenuConsulta(alias),
                        con.consultarServicioporMenuConsulta(alias), con.consultarCapasporMenuConsulta(alias), con.consultaRestricciones());
                break;
            }
            case Parametros.EXCLUSIONES:{
                resultado=conv.crearJsonExclusiones(con.consultarTablaContenidoporMenuConsulta(alias),
                        con.consultarServicioporMenuConsulta(alias), con.consultarCapasporMenuConsulta(alias),
                        con.consultaExclusiones(), con.consultarInfoyDocs(alias, true), con.consultarInfoyDocs(alias, false));
                break;
            }
            case Parametros.INDICEFRACCIONAMIENTO:{
                resultado=conv.crearJsonExclusiones(con.consultarTablaContenidoporMenuConsulta(alias),
                        con.consultarServicioporMenuConsulta(alias), con.consultarCapasporMenuConsulta(alias),
                        con.consultaExclusiones(), con.consultarInfoyDocs(alias, true), con.consultarInfoyDocs(alias, false));
                break;
            }
        }
        return resultado;
    }
    
    /**
     * Método que se encarga de tomar una cadena string y convertirla en 
     * un objeto java de tipo FiltroJson
     * @param json String que continen en formato json los filtros seleccionados por el usuario
     * @return Objeto de tipo FiltroJson con los filtros que seleccionó el usuario
     */
    public FiltroJson convertirJsonFiltro(String json){
        final Gson gson=new Gson();
        FiltroJson fjson=gson.fromJson(json, FiltroJson.class);
        return fjson;
    }
}
