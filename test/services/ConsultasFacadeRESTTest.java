/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import javax.ejb.embeddable.EJBContainer;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author cesar.solano
 */
public class ConsultasFacadeRESTTest {
    
    public ConsultasFacadeRESTTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of consultasAplicacion method, of class ConsultasFacadeREST.
     */
    @Test
    public void testConsultasAplicacion() throws Exception {
        System.out.println("consultasAplicacion");
        String alias = "";
        String json = "";
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        ConsultasFacadeREST instance = (ConsultasFacadeREST)container.getContext().lookup("java:global/classes/ConsultasFacadeREST");
        byte[] expResult = null;
        byte[] result = instance.consultasAplicacion(alias, json);
        assertArrayEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findAllTablaContenido method, of class ConsultasFacadeREST.
     */
    @Test
    public void testFindAllTablaContenido() throws Exception {
        System.out.println("findAllTablaContenido");
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        ConsultasFacadeREST instance = (ConsultasFacadeREST)container.getContext().lookup("java:global/classes/ConsultasFacadeREST");
        String expResult = "";
        String result = instance.findAllTablaContenido();
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
