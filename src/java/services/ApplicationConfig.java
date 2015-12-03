/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author cesar.solano
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
//        resources.add(servicios.CapasFacadeREST.class);
        resources.add(services.CapasFacadeREST.class);
//        resources.add(servicios.MenuconsultasFacadeREST.class);
        resources.add(services.DepartamentosFacadeREST.class);
//        resources.add(servicios.ServiciosFacadeREST.class);
//        resources.add(servicios.TablacontenidoFacadeREST.class);
        resources.add(services.MunicipiosFacadeREST.class);
        resources.add(services.ServiciosFacadeREST.class);
        resources.add(services.TablacontenidoFacadeREST.class);
    }
    
}
