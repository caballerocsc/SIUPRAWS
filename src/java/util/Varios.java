/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import obj.DinamicaMercados;
import obj.Menuconsultas;
import obj.Precios;
import obj.Areas;
import obj.BancoProyectos;
import obj.Capas;
import obj.FiltroJson;

/**
 * Clase que contiene diversos metodos que pueden ser utilizados por todas las clases
 * @author cesar.solano
 */
public class Varios {

    
    public Varios() {
    }
    
    /**
     * Método que se encarga de ordenar las lista de menu de consultas  de forma que si un elemento depende de otro,
     * el elemento del cual depende se encuentre en antes en la lista
     * @param lista objeto de tipo menuconsultas con el listado almacenado en la base de datos
     * @return objeto de tipo menuconsultas con los elementos ordenados
     */    
    public List<Menuconsultas> ordenarMenuConsultasPorPadre(List<Menuconsultas> lista){
        List<Menuconsultas> listaOrdenada=new ArrayList<>();
        for (Menuconsultas m : lista) {
            listaOrdenada=recursivo(lista,listaOrdenada, m);
        }
        return listaOrdenada;
    }
    
    public List<Menuconsultas> recursivo(List<Menuconsultas> lista,List<Menuconsultas> ordenada, Menuconsultas actual){
        boolean existe=false;
        Menuconsultas m=new Menuconsultas();
        for (int i=0;i<ordenada.size();i++) {
            m=ordenada.get(i);
            if(m.getMenuconsultaid()==actual.getMenuconsultaid()){
                existe=true;
                break;
            }
        }
        if(!existe&&actual.getDependede()==0)
            ordenada.add(actual);
        else if(existe){
            return ordenada;
        }else
            for (Menuconsultas m1 : lista) {
                if(actual.getDependede()==m1.getMenuconsultaid()){
                    ordenada=recursivo(lista,ordenada,m1);
                    ordenada.add(actual);
                }
            }
        return ordenada;
    }
    
    
    /**
     * Método que se encarga de realizar la sumatoria de todas las transacciones de 
     * dinámicas de mercado por año
     * @param anio indica cual sera el año para sumar todas las transacciones
     * @param lista lista con registro de transacciones, la cual puede contener varios años
     * @return Lista de tipo Integer, en cada ítem  de la lista se encuentra la sumatoria de Compraventas,
     * Hipotecas, Remates, Permutas y Embargos,
     */
    public List<Integer>  sumatoriaTransaccionesPorAnio(int anio, List<DinamicaMercados> lista){
        List<Integer> lSumatoria=new ArrayList();
        int sCompraVent=0;
        int sHipoteca=0;
        int sRemate=0;
        int sPermuta=0;
        int sEmbargo=0;
        for (DinamicaMercados dm : lista) {
            if (dm.getAnio()==anio) {
                sCompraVent+=dm.getCompraventa();
                sHipoteca+=dm.getHipoteca();
                sRemate+=dm.getRemate();
                sPermuta+=dm.getPermuta();
                sEmbargo+=dm.getEmbargo();
            }
        }
        lSumatoria.add(sCompraVent);
        lSumatoria.add(sHipoteca);
        lSumatoria.add(sRemate);
        lSumatoria.add(sPermuta);
        lSumatoria.add(sEmbargo);
        return lSumatoria;
    }
    
    /**
     * Metodo que calcula la variación porcentual entro dos numeros
     * @param valor1 Parametro 1
     * @param valor2 Parametro 1
     * @return Devuelve la variación porcentual del valor2 respecto al valor1 
     */
    public String calcularVariacionPorcentual(double valor1, double valor2){
        String res;
        if (valor1!=0) {
            //DecimalFormat formateador = new DecimalFormat("#,##");
            double op=((valor2-valor1)/valor1)*100;
            DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
            simbolos.setDecimalSeparator('.');
            DecimalFormat formateador = new DecimalFormat("####.##",simbolos);
            res=formateador.format(op);
        }else
            if(valor2==0){
                res="0";
            }else
                res="100";
       return res;
    }
    
    /**
     * Método que suma todas las areas por rango
     * @param precios lista de tipo Precios con las áreas y el rango del precio
     * @return Retorna un HashMap con el rango y la sumatoria de las áreas en ese rango
     */
    public HashMap<String,Double> SumatoriaAreasPrecios(List<Precios> precios){
        HashMap map=new HashMap();
        double sum=0;
        //recore toda la lista de precios, y va agregando los diferentes rangos en el hashmap
        for (Precios p : precios) {
            if(!map.containsKey(p.getRango()))
                map.put(p.getRango(), 0);
        }
        //calcular la sumatoria por rango
        Iterator iterator = map.keySet().iterator();
        while(iterator.hasNext()){
          String key   = (String)iterator.next();
          for (Precios p : precios) {
            if(key.equals(p.getRango()))
                sum+=p.getArea();
          }
          map.put(key, sum);
        }
        return map;
    }
    
    /**
     * Método encargado de tomar una lista de tipo areas y dependiendo del paramtro zona sacar el promedio
     * de determinada area
     * @param list lista con las areas de cada departamento 
     * @param zona 1:condicionante 2:exlusión 3:Sin restricción 4:inclusión 5: el dato que se encuentre en la variable area
     * @return BigDecimal con el promedio de la zona elegida
     */
    public BigDecimal promedioAreas(List<Areas> list, int zona){
        BigDecimal resul = new BigDecimal(BigInteger.ZERO);
        final int cond=1;
        final int excl=2;
        final int sinRest=3;
        final int incl=4;
        final int area=5;
        final int alta=6;
        final int media=7;
        final int baja=8;
        final int noApto=9;
        
        for (Areas restricciones : list) {
            switch(zona){
                case cond:{// tambien restringido
                    resul=resul.add(restricciones.getCondicionante());
                    break;
                }
                case excl:{
                    resul=resul.add(restricciones.getExclusion());
                    break;
                }
                case sinRest:{
                    resul=resul.add(restricciones.getSinRestriccion());
                    break;
                }
                case incl:{
                    resul=resul.add(restricciones.getIncluidas());
                    break;
                }
                case area:{
                    resul=resul.add(restricciones.getArea());
                    break;
                }
                case alta:{
                    resul=resul.add(restricciones.getZonaAlta());
                    break;
                }
                case media:{
                    resul=resul.add(restricciones.getZonaMedia());
                    break;
                }
                case baja:{
                    resul=resul.add(restricciones.getZonaBaja());
                    break;
                }
                case noApto:{
                    resul=resul.add(restricciones.getZonaNoApta());
                    break;
                }
            }
        }
        resul=resul.divide(new BigDecimal(list.size()),2, RoundingMode.HALF_UP);
        return resul;
    }
    
    /**
     * Método encargado de sacar el porcentaje de una zona con respecto al total del departamento
     * @param list Lista de las areas por departamento
     * @param zona tipo de zona a la cual se le saca el porcentaje. 
     * 1:Condicionante, 2:Exclusiones, 3:Sin Restricciones
     * @return Lista de tipo BigDecimal con los porcentajes departamentales por zona
     */
    public List<BigDecimal> PorcentajeAreaxZonaRestricciones(List<Areas> list, int zona){
        List<BigDecimal> listTotal = new ArrayList<>();
        final int cond=1;
        final int excl=2;
        final int sinRest=3;
        for (Areas res : list) {
            switch(zona){
                case cond:{
                    listTotal.add(res.getAreaDepto().multiply(res.getCondicionante()).divide(new BigDecimal(100)).setScale(2, RoundingMode.HALF_UP));
                    break;
                }
                case excl:{
                    listTotal.add(res.getAreaDepto().multiply(res.getExclusion()).divide(new BigDecimal(100)).setScale(2,RoundingMode.HALF_UP));
                    break;
                }
                case sinRest:{
                    listTotal.add(res.getAreaDepto().multiply(res.getSinRestriccion()).divide(new BigDecimal(100)).setScale(2,RoundingMode.HALF_UP));
                    break;
                }
            }
        }
        return listTotal;
    }
    
    /**
     * Método que se encarga de crear una lista con los valores de todos los
     * departamentos para una zona específica. 
     * @param list lista de areas por departamento
     * @param zona parametro que indica a cual tipo de área sacar el listado
     * 1: inclusión, 2:exclusión, 3:restricción
     * @return lista de tipo BigDecimal con todos los valores de un area específica
     */
    public List<BigDecimal> totalSepararExclusiones(List<Areas> list, int zona){
        List<BigDecimal> listTotal = new ArrayList<>();
        final int incl=1;
        final int excl=2;
        final int rest=3;
        for (Areas res : list) {
            switch(zona){
                case incl:{
                    listTotal.add(res.getAreaIncl().setScale(2, RoundingMode.HALF_UP));
                    break;
                }
                case excl:{
                    listTotal.add(res.getAreaExcl().setScale(2,RoundingMode.HALF_UP));
                    break;
                }
                case rest:{
                    listTotal.add(res.getAreaCond().setScale(2,RoundingMode.HALF_UP));
                    break;
                }
            }
        }
        return listTotal;
    }
    
    /**
     * Método encargado de sacar el promedio del area de cada una de las categorias
     * de los indicadores 
     * @param area lista con las areas de cada uno de los departamentos
     * @param tipo categoria en que se encuentra cada area, las opciones pueden ser Alto, Medio, Bajo y Muy Bajo 
     * @return promedio del area de la zona elegina con el paramentro tipo
     */
    public BigDecimal promedioCategoriaZonas(List<Areas> area, String tipo){
        BigDecimal total = new BigDecimal(BigInteger.ZERO);
        BigDecimal cont = new BigDecimal(BigInteger.ZERO);
        BigDecimal uno = new BigDecimal(BigInteger.ONE);
        for (Areas a : area) {
            System.out.println(a.getTipo());
            System.out.println(tipo);
            System.out.println(a.getTipo().equals(tipo));
            if(a.getTipo().equals(tipo)){
                total=total.add(a.getArea());
                cont=cont.add(uno);
            }
        }
        return total.divide(cont, 2,RoundingMode.HALF_DOWN);
    }
    
    /**
     * Método encargado de tomar una lista de capas y retornar una nueva lista
     * en la cual se encuentren las capas que tengan en el alias, el año seleccionado
     * en el filtro que se pasa como parámetro
     * @param cap lista de tipo Capas con las capas de una consulta 
     * @param fj Objeto que contiene los años seleccionados por el usuario
     * @return lista de Capas correspondiente a los años seleccionados por el usuario
     */
    public List<Capas> buscarCapaXfiltroAnio(List<Capas> cap, FiltroJson fj){
        List<Capas> c = new ArrayList<>();
        for (Capas capas : cap) {
            for (String anio : fj.getAnios()) {
                if(capas.getAlias().contains(anio))
                    c.add(capas);
            }
        }
        return c;
    }
    
    /**
     * Método que se encarga de sumar todas las areas segun el caso correspondiente:
     * caso 1: sumar todas las áreas de una única categoría para todos los municipios o
     * caso 2: sumar las áreas de todas las categorías de un único municipio
     * esto dependiendo de los parametros que se envien
     * @param areas lista de tipo Areas con la información que se debe sumar 
     * @param cat categoría para sumar en el caso 1
     * @param munpio municipio para sumar las areas en el caso 2
     * @param listCat lista de las categorías que debe sumar en el caso 2
     * @return Lista de tipo BigDecimal con la información de las áreas según el filtro correspondientes
     */
    public List<BigDecimal> sumarAreaXCategoria(List<Areas> areas, String cat, String munpio, List<String> listCat) {
        BigDecimal total = new BigDecimal(BigInteger.ZERO);
        List<BigDecimal> lista = new ArrayList<>();
        List<BigDecimal> listaResult = new ArrayList<>();
        if (!munpio.equals("")) {
            for (String listCat1 : listCat) {// inicializa en cero la lista
                listaResult.add(BigDecimal.ZERO);
            }
        }
        for (Areas a : areas) {
            if (!munpio.equals("")) {
                //caso 2
                if (a.getMunicipio().equals(munpio)) {
                    for (int i = 0; i < listCat.size(); i++) {
                        if (a.getTipo().equals(listCat.get(i))) {
                            listaResult.set(i, listaResult.get(i).add(a.getArea()).setScale(2, RoundingMode.HALF_UP));
                        }
                    }
                }
            } else if (a.getTipo().equals(cat)) {
                //caso 1
                total = total.add(a.getArea());
            }
        }
        if (munpio.equals("")) {
            lista.add(total.setScale(2, RoundingMode.HALF_UP));
            return lista;
        } else {
            return listaResult;
        }
    }
    /**
     * Método que permite contar cuantos departamentos tiene proyectos en cada una de las subetapas
     * @param deptos lista de departamentos de Colombia
     * @param bank lista de proyectos
     * @param subEtapa indica el filtro por el que se debe contar los departamentos
     * @return lista de tipo Integer con la cantidad de proyectos de la subetapa seleccionada de cada uno de los departamentos
     */
    public List<Integer> BancoProyDeptoXSubetapa(List<String> deptos, List<BancoProyectos> bank, String subEtapa){
        List<Integer> list = new ArrayList<>();
        for (String d : deptos) {
            int resultado=0;
            for (BancoProyectos b : bank) {
                if(b.getDepto().equals(d)&&b.getSubetapa().equals(subEtapa)){
                    resultado++;
                }
            }
            list.add(resultado);
        }
        return list;
    }
}
