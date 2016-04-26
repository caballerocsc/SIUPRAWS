/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Controller.Operaciones;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Clase que recibe cualquier petición de consulta para la aplicación
 * @author cesar.solano
 */

@Path("obj.consultas")
public class ConsultasFacadeREST {

    
    
    public ConsultasFacadeREST() {
    }
    
    @GET
    @Path("{alias}/{json}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    /**
     * El método recibe las peticiones de consulta de usuario de la aplicación 
     * @param alias parametro de busqueda de la consulta en la base de datos
     * @param json string con la información de filtros que se deben aplicar
     */
    public String /*byte[]*/ consultasAplicacion(@PathParam("alias") String alias, @PathParam("json") String json){
//        try{
            Operaciones op=new Operaciones();
            System.out.println("alias: "+alias+" json: "+json);
            String s=op.seleccionarConsulta(alias, json);
            return s;//.getBytes("windows-1252");
//        }
//        catch (UnsupportedEncodingException ex) {
//            Logger.getLogger(CapasFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
//            return "";
//        }
    }
    
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    /**
     * 
     */
    public String findAllTablaContenido() {
        return "";
    }
    
}
