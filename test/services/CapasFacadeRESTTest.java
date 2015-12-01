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
public class CapasFacadeRESTTest {
    
    public CapasFacadeRESTTest() {
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
     * Test of findAllCapas method, of class CapasFacadeREST.
     */
    @Test
    public void testFindAllCapas() throws Exception {
        System.out.println("findAllCapas");
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        CapasFacadeREST instance = (CapasFacadeREST)container.getContext().lookup("java:global/classes/CapasFacadeREST");
        byte[] expResult = null;
        byte[] result = instance.findAllCapas();
        assertArrayEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
