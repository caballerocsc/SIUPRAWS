/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Controller.Operaciones;
import obj.Servicios;
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

/**
 *
 * @author cesar.solano
 * 
 */
@Stateless
@Path("obj.servicios")
public class ServiciosFacadeREST {

    public ServiciosFacadeREST() {
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    /**
     * El método busca todos los registros en la tabla "servicios" 
     * @return List<Servicios> Lista de objetos de tipo "servicios" con la información procedente de la base de datos
     */
    public String findServicios() {
        return (new Operaciones().getServicios());
    }

    
}
