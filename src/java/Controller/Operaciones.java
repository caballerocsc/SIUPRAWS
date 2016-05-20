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
//            case Parametros.AVALUOS:{
////                con.consultaAvaluos(fil);
//                resultado=conv.crearJsonAvaluos(con.consultarTablaContenidoporMenuConsulta(alias),
//                        con.consultarServicioporMenuConsulta(alias), con.consultarCapasporMenuConsulta(alias));
//                break;
//            }
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
                resultado=conv.crearJsonIndiceFraccionamiento(con.consultarTablaContenidoporMenuConsulta(alias),
                        con.consultarServicioporMenuConsulta(alias), con.consultarCapasporMenuConsulta(alias),
                        con.consultarIndiceFraccionamiento(), con.consultarInfoyDocs(alias, true), con.consultarInfoyDocs(alias, false));
                break;
            }
            case Parametros.DISTRITOS_RIEGO_DRENAJE:{
                resultado=conv.crearJsonDistritosRiegos(con.consultarTablaContenidoporMenuConsulta(alias),
                        con.consultarServicioporMenuConsulta(alias), con.consultarCapasporMenuConsulta(alias),
                        con.datosDistritosRiego(), con.conteoDistritosRiego(1), con.conteoDistritosRiego(2), 
                        con.conteoDistritosRiego(3), con.consultarDepartamentos());
                break;
            }
            case Parametros.INDICE_CONCENTRACION:{
                resultado=conv.crearJsonConcentracionRelativa(con.consultarTablaContenidoporMenuConsulta(alias),
                        con.consultarServicioporMenuConsulta(alias), con.consultarCapasporMenuConsulta(alias),
                        con.consultarIndicadorConcentracion(), con.consultarInfoyDocs(alias, true), con.consultarInfoyDocs(alias, false));
                break;
            }
            case Parametros.SUPERFICIE_SIN_REST:{
                resultado=conv.crearJsonSuperficieSinRest(con.consultarTablaContenidoporMenuConsulta(alias),
                        con.consultarServicioporMenuConsulta(alias), con.consultarCapasporMenuConsulta(alias),
                        con.consultarIndicadoresSuperficie(1), con.consultarInfoyDocs(alias, true), con.consultarInfoyDocs(alias, false));
                break;
            }
            case Parametros.SUPERFICIE_CON_EXC_LEGAL:{
                resultado=conv.crearJsonSuperficieConExcLeg(con.consultarTablaContenidoporMenuConsulta(alias),
                        con.consultarServicioporMenuConsulta(alias), con.consultarCapasporMenuConsulta(alias),
                        con.consultarIndicadoresSuperficie(2), con.consultarInfoyDocs(alias, true), con.consultarInfoyDocs(alias, false));
                break;
            }
            case Parametros.SUPERFICIE_USO_COND:{
                resultado=conv.crearJsonSuperficieUsoCond(con.consultarTablaContenidoporMenuConsulta(alias),
                        con.consultarServicioporMenuConsulta(alias), con.consultarCapasporMenuConsulta(alias),
                        con.consultarIndicadoresSuperficie(3), con.consultarInfoyDocs(alias, true), con.consultarInfoyDocs(alias, false));
                break;
            }
            case Parametros.SUPERFICIE_USO_SOBRE:{
                resultado=conv.crearJsonSuperficieSobreUtilizacion(con.consultarTablaContenidoporMenuConsulta(alias),
                        con.consultarServicioporMenuConsulta(alias), con.consultarCapasporMenuConsulta(alias),
                        con.consultarIndicadoresSuperficie(4), con.consultarInfoyDocs(alias, true), con.consultarInfoyDocs(alias, false));
                break;
            }
            case Parametros.SUPERFICIE_USO_SUB:{
                resultado=conv.crearJsonSuperficieSubUtilizacion(con.consultarTablaContenidoporMenuConsulta(alias),
                        con.consultarServicioporMenuConsulta(alias), con.consultarCapasporMenuConsulta(alias),
                        con.consultarIndicadoresSuperficie(5), con.consultarInfoyDocs(alias, true), con.consultarInfoyDocs(alias, false));
                break;
            }
            case Parametros.SUPERFICIE_SIN_CONFLICTO:{
                resultado=conv.crearJsonSuperficieSinConflicto(con.consultarTablaContenidoporMenuConsulta(alias),
                        con.consultarServicioporMenuConsulta(alias), con.consultarCapasporMenuConsulta(alias),
                        con.consultarIndicadoresSuperficie(6), con.consultarInfoyDocs(alias, true), con.consultarInfoyDocs(alias, false));
                break;
            }
            default:
                if(alias.equals(Parametros.AVALUOS)||alias.equals(Parametros.AREAS_FORMALIZACION)||
                        alias.equals(Parametros.INTERRELACION_CAT_REG)||alias.equals(Parametros.VIGENCIA_CATASTRAL2014)){
                     resultado=conv.crearJsonConsultaGenericaInfoGeo(con.consultarTablaContenidoporMenuConsulta(alias),
                        con.consultarServicioporMenuConsulta(alias), con.consultarCapasporMenuConsulta(alias));
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
