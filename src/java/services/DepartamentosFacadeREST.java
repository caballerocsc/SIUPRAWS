/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

//import obj.Departamentos;
import Controller.Operaciones;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
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
    public byte[] findAllDepart() throws UnsupportedEncodingException {
        Operaciones op=new Operaciones();
        String s=op.getDepartamentosMun();
        return s.getBytes("windows-1252");
                
    }

}
