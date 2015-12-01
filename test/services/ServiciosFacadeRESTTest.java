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
public class ServiciosFacadeRESTTest {
    
    public ServiciosFacadeRESTTest() {
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
     * Test of findServicios method, of class ServiciosFacadeREST.
     */
    @Test
    public void testFindServicios() throws Exception {
        System.out.println("findServicios");
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        ServiciosFacadeREST instance = (ServiciosFacadeREST)container.getContext().lookup("java:global/classes/ServiciosFacadeREST");
        byte[] expResult = null;
        byte[] result = instance.findServicios();
        assertArrayEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
