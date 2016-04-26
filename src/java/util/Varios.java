/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.math.BigDecimal;
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
import obj.Restricciones;

/**
 * Clase que contiene diversos metodos que pueden ser utilizados por todas las clases
 * @author cesar.solano
 */
public class Varios {

    //private List<Menuconsultas> listaOrdenada=new ArrayList<>();
    
    public Varios() {
    }
    
    /**
     * Método que se encarga de ordenar las lista de menu de consultas  de forma que si un elemento depende de otro,
     * el elemento del cual depende se encuentre en antes en la lista
     * @param lista objeto de tipo menuconsultas con el listado almacenado en la base de datos
     * @return objeto de tipo menuconsultas con los elementos ordenados
     */
//    public List<Menuconsultas> ordenarMenuConsultasPorPadre(List<Menuconsultas> lista){
//        Menuconsultas mc =null;
//        for(int i=0; i<lista.size(); i++){
//            mc=lista.get(i);
//            //Si el elemento no depende de otro lo agrega a la lista ordenada
//            if (mc.getDependede()==0) {
//                listaOrdenada.add(mc);
//            }
//            else{
//                verificarPadreMenuConsulta(lista, mc);
//            }
//        }
//        return listaOrdenada;
//    }
    
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
     *  Método recursivo que verifica si el padre de un elemento ya se encuentra en la lista
     * @param lista lista de elementos en la base de datos
     * @param actual elemento que se revisa para saber si el padre ya se inserto a la nueva lista
     */
//    public void verificarPadreMenuConsulta(List<Menuconsultas> lista, Menuconsultas actual) {
//        Menuconsultas tmp=null;
//        boolean existe=false;
//        for (int i = 0; i < listaOrdenada.size(); i++) {
//            tmp=listaOrdenada.get(i);
//            if(tmp.getMenuconsultaid()==actual.getDependede()){
//                listaOrdenada.add(actual);
//                existe=true;
//            }
//        }
//        if(!existe){
//            for (int i = 0; i < lista.size(); i++) {
//                tmp=lista.get(i);
//                if(tmp.getMenuconsultaid()==actual.getDependede()){
//                    verificarPadreMenuConsulta(lista, tmp);
//                }
//            }
//        }
//    }
    
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
//        while(iterator.hasNext()){
//          String key   = (String)iterator.next();
//          double valor=(double)map.get(key);
//            System.out.println("llave: "+key+" valor: "+valor);
//        }
        return map;
    }
    
    public float promedioRestricciones(List<Restricciones> list, int zona){
        float resul=0;
        final int cond=1;
        final int excl=2;
        final int sinRest=3;
        for (Restricciones restricciones : list) {
            switch(zona){
                case cond:{
                    resul+=restricciones.getCondicionante();
                    break;
                }
                case excl:{
                    resul+=restricciones.getExclusion();
                    break;
                }
                case sinRest:{
                    resul+=restricciones.getSinRestriccion();
                    break;
                }
            }
        }
        resul=resul/list.size();
        return resul;
    }
    
    public List<BigDecimal> totalAreaxZonaRestricciones(List<Restricciones> list, int zona){
        List<BigDecimal> listTotal = new ArrayList<>();
        final int cond=1;
        final int excl=2;
        final int sinRest=3;
        for (Restricciones res : list) {
            switch(zona){
                case cond:{
                    listTotal.add(res.getAreaDepto().multiply(BigDecimal.valueOf(res.getCondicionante())).divide(new BigDecimal(100)).setScale(2, RoundingMode.HALF_UP));
                    break;
                }
                case excl:{
                    listTotal.add(res.getAreaDepto().multiply(BigDecimal.valueOf(res.getExclusion())).divide(new BigDecimal(100)).setScale(2,RoundingMode.HALF_UP));
                    break;
                }
                case sinRest:{
                    listTotal.add(res.getAreaDepto().multiply(BigDecimal.valueOf(res.getSinRestriccion())).divide(new BigDecimal(100)).setScale(2,RoundingMode.HALF_UP));
                    break;
                }
            }
        }
        return listTotal;
    }
    
    public BigDecimal sacarPorcentaje(float num1, BigDecimal num2){
        BigDecimal divisor = new BigDecimal(num1);
        BigDecimal mult = new BigDecimal(100);
        divisor=divisor.multiply(mult);
        divisor = divisor.divide(num2,2, RoundingMode.HALF_UP);
        return divisor;
    }
}
