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
public class FiltrosFacadeRESTTest {
    
    public FiltrosFacadeRESTTest() {
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
     * Test of findFiltroConsulta method, of class FiltrosFacadeREST.
     */
    @Test
    public void testFindFiltroConsulta() throws Exception {
        System.out.println("findFiltroConsulta");
        String al = "";
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        FiltrosFacadeREST instance = (FiltrosFacadeREST)container.getContext().lookup("java:global/classes/FiltrosFacadeREST");
        byte[] expResult = null;
        byte[] result = instance.findFiltroConsulta(al);
        assertArrayEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findFiltros method, of class FiltrosFacadeREST.
     */
    @Test
    public void testFindFiltros() throws Exception {
        System.out.println("findFiltros");
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        FiltrosFacadeREST instance = (FiltrosFacadeREST)container.getContext().lookup("java:global/classes/FiltrosFacadeREST");
        String expResult = "";
        String result = instance.findFiltros();
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
