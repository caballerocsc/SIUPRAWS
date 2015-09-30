/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

//import obj.Departamentos;
import Controller.Operaciones;
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
//import traduccion.TraduccionJson;

/**
 *
 * @author cesar.solano
 */
@Stateless
@Path("obj.departamentos")
public class DepartamentosFacadeREST {
    

    public DepartamentosFacadeREST() {
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    /**
     * El método que  recibe la petición de buscar los Departamentos
     * @return String en formato Json con los departamentos 
     */
    public String findAllDepart() {
        Operaciones op=new Operaciones();
        return op.getDepartamentosMun();
    }

}
