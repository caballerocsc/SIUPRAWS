/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Controller.Operaciones;
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
@Path("obj.tablacontenido")
public class TablacontenidoFacadeREST {

    public TablacontenidoFacadeREST() {
    }

//    @POST
//    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
//    /** 
//     * Método que guarda un objeto de tipo "tablacontenido" en la base de datos
//     * @param entity Objeto de tipo "tablacontenido" que sera guardado en la base de datos 
//     */
//    public void create(Tablacontenido entity) {
//        super.create(entity);
//    }
//
//    @PUT
//    @Path("{id}")
//    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
//    /**
//     * Método que permite modificar un registro en la tabla "tablacontenido" en la base de datos
//     * @param id llave primaria del registro que se modificará
//     * @param entity Objeto de tipo "tablacontenido" que contiene la informacion a guardar. En caso de que ya exista un atributo y este se envie vacio, el método lo sobrescribirá
//     */
//    public void edit(@PathParam("id") Integer id, Tablacontenido entity) {
//        super.edit(entity);
//    }
//
//    @DELETE
//    @Path("{id}")
//    /**
//     * El método borra un registro un registro de la tabla de "tablacontenido"
//     * @param id Llave primaria del registro que se desea borrar.
//     * 
//     */
//    public void remove(@PathParam("id") Integer id) {
//        super.remove(super.find(id));
//    }
//
//    @GET
//    @Path("{id}")
//    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
//    /**
//     * El método busca el registro en la tabla "tablacontenido" que tenga la llave primaria enviada 
//     * @param id Llave primaria del registro que se desea obtener
//     * @return Tablacontenido Objeto de tipo "tablacontenido" con la información procedente de la base de datos
//     */
//    public Tablacontenido find(@PathParam("id") Integer id) {
//        return super.find(id);
//    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    /**
     * El método que  recibe la petición de buscar la tabla de contenido  
     * @return String en formato Json con la tabla de contenido
     */
    public String findAllTablaContenido() {
        Operaciones op=new Operaciones();
        return op.getTablaContenido();
    }

//    @GET
//    @Path("{from}/{to}")
//    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
//    /**
//     * El método busca los servicios dentro del un rango establecido
//     * @param from valor míminmo desde que empezará la busqueda
//     * @param to valor máximo para hacer la busqueda
//     */
//    public List<Tablacontenido> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
//        return super.findRange(new int[]{from, to});
//    }
//
//    @GET
//    @Path("count")
//    @Produces(MediaType.TEXT_PLAIN)
//    /**
//     * El método devuelve el valor del numero de registros almacenados en la tabla "servicios"
//     * @return String devuleve el número de elementos presentes en la tabla
//     */
//    public String countREST() {
//        return String.valueOf(super.count());
//    }

    
}
