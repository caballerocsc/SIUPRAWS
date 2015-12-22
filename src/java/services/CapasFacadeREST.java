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
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author cesar.solano
 */
@Stateless
@Path("obj.capas")
public class CapasFacadeREST {

    public CapasFacadeREST() {
    }


    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    /**
     * El método que  recibe la petición de buscar las capas  
     * @return String en formato Json con las capas
     */
    public byte[] findAllCapas() {
        try {
            Operaciones op=new Operaciones();
            String s=op.getCapas();
            return (s.getBytes("windows-1252"));
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(CapasFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    
}
