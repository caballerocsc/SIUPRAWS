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
 *
 * @author cesar.solano
 */
@Stateless
@Path("obj.filtros")
public class FiltrosFacadeREST {

    public FiltrosFacadeREST() {
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    /**
     * Método que consulta los filtros de una consulta segun el alias de la consulta
     * @param alias de la consulta de la cual se desean obtener los filtros
     * @return String en formato json con los filtros correspondientes
     */
    public byte[] findFiltroConsulta(@PathParam("id") String al) {
        try {
            Operaciones op=new Operaciones();
            String s=op.getFiltroConsulta(al);
            return (s.getBytes("windows-1252"));
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(CapasFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    /**
     * Método vacio ya que no se implementara la busqueda de todos los filtros, 
     * sin embargo es obligacion colocarlo para implementar la busqueda por alias.
     * @return siempre retorna vacio
     */
    public String findFiltros() {
        return null;
    }


}
