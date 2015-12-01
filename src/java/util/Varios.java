/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.ArrayList;
import java.util.List;
import obj.Menuconsultas;

/**
 * Clase que contiene diversos metodos que pueden ser utilizados por todas las clases
 * @author cesar.solano
 */
public class Varios {

    private List<Menuconsultas> listaOrdenada=new ArrayList<>();
    
    public Varios() {
    }
    
    /**
     * Método que se encarga de ordenar las lista de menu de consultas  de forma que si un elemento depende de otro,
     * el elemento del cual depende se encuentre en antes en la lista
     * @param lista objeto de tipo menuconsultas con el listado almacenado en la base de datos
     * @return objeto de tipo menuconsultas con los elementos ordenados
     */
    public List<Menuconsultas> ordenarMenuConsultasPorPadre(List<Menuconsultas> lista){
        Menuconsultas mc =null;
        for(int i=0; i<lista.size(); i++){
            mc=lista.get(i);
            //Si el elemento no depende de otro lo agrega a la lista ordenada
            if (mc.getDependede()==0) {
                listaOrdenada.add(mc);
            }
            else{
                verificarPadreMenuConsulta(lista, mc);
            }
        }
        return listaOrdenada;
    }
    
    /**
     *  Método recursivo que verifica si el padre de un elemento ya se encuentra en la lista
     * @param lista lista de elementos en la base de datos
     * @param actual elemento que se revisa para saber si el padre ya se inserto a la nueva lista
     */
    public void verificarPadreMenuConsulta(List<Menuconsultas> lista, Menuconsultas actual) {
        Menuconsultas tmp=null;
        boolean existe=false;
        for (int i = 0; i < listaOrdenada.size(); i++) {
            tmp=listaOrdenada.get(i);
            if(tmp.getMenuconsultaid()==actual.getDependede()){
                listaOrdenada.add(actual);
                existe=true;
            }
        }
        if(!existe){
            for (int i = 0; i < lista.size(); i++) {
                tmp=lista.get(i);
                if(tmp.getMenuconsultaid()==actual.getDependede()){
                    verificarPadreMenuConsulta(lista, tmp);
                }
            }
        }
    }
    
}
