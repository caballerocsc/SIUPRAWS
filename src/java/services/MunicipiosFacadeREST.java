/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

//import obj.Municipios;
import java.util.List;
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
@Path("obj.municipios")
public class MunicipiosFacadeREST{
    

    public MunicipiosFacadeREST() {
    }

    
//    @GET
//    @Path("{id}")
//    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
//    public Municipios find(@PathParam("id") Integer id) {
//        return super.find(id);
//    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public String findAll() {
//        System.out.println("entre");
//        List<Municipios> list=super.findAll();
//        return list;
        return "hola caiman";
    }

    
    
}
