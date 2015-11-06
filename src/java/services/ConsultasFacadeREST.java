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
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Clase que recibe cualquier petición de consulta para la aplicación
 * @author cesar.solano
 */

@Stateless
@Path("obj.consultas")
public class ConsultasFacadeREST {

    String jsonPrueba="{\n" +
"    \"nal\": true,\n" +
"    \"deps\": [\n" +
"        \"01\",\n" +
"        \"02\",\n" +
"        \"03\"\n" +
"    ],\n" +
"    \"muns\": [\n" +
"        \"04\",\n" +
"        \"05\",\n" +
"        \"06\"\n" +
"    ],\n" +
"    \"regis\": [\n" +
"        \"07\",\n" +
"        \"08\",\n" +
"        \"09\"\n" +
"    ],\n" +
"    \"terrs\": [\n" +
"        \"10\",\n" +
"        \"11\",\n" +
"        \"12\"\n" +
"    ],\n" +
"    \"anios\": [\n" +
"        2012,\n" +
"        2013,\n" +
"        2014\n" +
"    ],\n" +
"    \"meses\": [\n" +
"        1,\n" +
"        2,\n" +
"        3\n" +
"    ],\n" +
"    \"trimestres\": [\n" +
"        1,\n" +
"        2,\n" +
"        3,\n" +
"        4\n" +
"    ],\n" +
"    \"semestre\": [\n" +
"        1,\n" +
"        2\n" +
"    ]\n" +
"}";
    
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
    public byte[] consultasAplicacion(@PathParam("alias") String alias, @PathParam("json") String json){
        Operaciones op=new Operaciones();
        System.out.println("alias: "+alias+" json: "+json);
        op.seleccionarConsulta(alias, jsonPrueba);
        return null;
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
