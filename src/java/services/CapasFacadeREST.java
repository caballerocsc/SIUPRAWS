/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Controller.Operaciones;
import obj.Capas;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.persistence.Query;

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
     * El método busca todos los registros en la tabla "capas" 
     * @return List<Capas> Lista de objetos de tipo "capas" con la información procedente de la base de datos
     */
    public String findAllCapas() {
        return (new Operaciones().getCapas());
    }

    
}
