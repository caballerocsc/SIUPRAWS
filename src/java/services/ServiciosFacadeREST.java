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
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author cesar.solano
 * 
 */
@Path("obj.servicios")
public class ServiciosFacadeREST {

    public ServiciosFacadeREST() {
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    /**
     * El método que  recibe la petición de buscar los servicios  
     * @return String en formato Json con los servicios
     */
    public byte[] findServicios() {
        try {
            Operaciones op=new Operaciones();
            String s=op.getServicios();
            return (s.getBytes("windows-1252"));
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(CapasFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    
}
