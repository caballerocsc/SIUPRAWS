/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import obj.FiltroJson;
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
public class OperacionesTest {
    
    public OperacionesTest() {
    }
    
    String jsonPrueba="{\n" +
"    \"nal\": true,\n" +
"    \"deps\": [\n" +
"        \"01\",\n" +
"        \"02\",\n" +
"        \"03\"\n" +
"    ],\n" +
"    \"muns\": [\n" +
"        \"04\",\n" +
"        \"05\",\n" +
"        \"06\"\n" +
"    ],\n" +
"    \"regis\": [\n" +
"        \"07\",\n" +
"        \"08\",\n" +
"        \"09\"\n" +
"    ],\n" +
"    \"terrs\": [\n" +
"        \"10\",\n" +
"        \"11\",\n" +
"        \"12\"\n" +
"    ],\n" +
"    \"anios\": [\n" +
"        2012,\n" +
"        2013,\n" +
"        2014\n" +
"    ],\n" +
"    \"meses\": [\n" +
"        1,\n" +
"        2,\n" +
"        3\n" +
"    ],\n" +
"    \"trimestres\": [\n" +
"        1,\n" +
"        2,\n" +
"        3,\n" +
"        4\n" +
"    ],\n" +
"    \"semestre\": [\n" +
"        1,\n" +
"        2\n" +
"    ]\n" +
"}";
    
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

//    /**
//     * Test of getDepartamentosMun method, of class Operaciones.
//     */
//    @Test
//    public void testGetDepartamentosMun() {
//        System.out.println("getDepartamentosMun");
//        Operaciones instance = new Operaciones();
//        String expResult = "";
//        String result = instance.getDepartamentosMun();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getTablaContenido method, of class Operaciones.
//     */
//    @Test
//    public void testGetTablaContenido() {
//        System.out.println("getTablaContenido");
//        Operaciones instance = new Operaciones();
//        String expResult = "";
//        String result = instance.getTablaContenido();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getServicios method, of class Operaciones.
//     */
//    @Test
//    public void testGetServicios() {
//        System.out.println("getServicios");
//        Operaciones instance = new Operaciones();
//        String expResult = "";
//        String result = instance.getServicios();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getCapas method, of class Operaciones.
//     */
//    @Test
//    public void testGetCapas() {
//        System.out.println("getCapas");
//        Operaciones instance = new Operaciones();
//        String expResult = "";
//        String result = instance.getCapas();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getFiltroConsulta method, of class Operaciones.
//     */
//    @Test
//    public void testGetFiltroConsulta() {
//        System.out.println("getFiltroConsulta");
//        String alias = "";
//        Operaciones instance = new Operaciones();
//        String expResult = "";
//        String result = instance.getFiltroConsulta(alias);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of seleccionarConsulta method, of class Operaciones.
     */
    @Test
    public void testSeleccionarConsulta() {
        System.out.println("seleccionarConsulta");
        String alias = "trans";
        Operaciones instance = new Operaciones();
        String expResult = "";
        String result = instance.seleccionarConsulta(alias, jsonPrueba);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

//    /**
//     * Test of convertirJsonFiltro method, of class Operaciones.
//     */
//    @Test
//    public void testConvertirJsonFiltro() {
//        System.out.println("convertirJsonFiltro");
//        String json = "";
//        Operaciones instance = new Operaciones();
//        FiltroJson expResult = null;
//        FiltroJson result = instance.convertirJsonFiltro(json);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    
}
