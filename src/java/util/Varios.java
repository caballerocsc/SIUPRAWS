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
    
    public List<Menuconsultas> ordenarMenuConsultasPorPadre(List<Menuconsultas> lista){
        Menuconsultas mc =null;
        for(int i=0; i<lista.size(); i++){
            mc=lista.get(i);
            //Si el elemento no depende de otro lo agrega a la lista ordenada
            if (mc.getDependede()==0) {
                listaOrdenada.add(mc);
            }
            else{
//                Menuconsultas tmp=null;
//                boolean existe=false;
//                for (int j = 0; j < listaOrdenada.size(); j++) {
//                    tmp=listaOrdenada.get(j);
//                    //verifica que si el elmento del cual depende el elemento original ya se encuentra en la lista ordenada
//                    if(mc.getDependede()==tmp.getMenuconsultaid())
//                        existe=true;
//                }
//                //el elemento del que depende no ha sido agregado, por ende hay que buscarlo y agregarlo
//                if(!existe){
//                    for (int j = 0; j < lista.size(); j++) {
//                        tmp=lista.get(j);
//                        if(mc.getDependede()==tmp.getMenuconsultaid())
//                            listaOrdenada.add(tmp);
//                    }
//                }
                verificarPadreMenuConsulta(lista, mc);
                //por último se agrega el elemento original 
                //listaOrdenada.add(mc);
            }
        }
        listaOrdenada.stream().forEach((t) -> {
            System.out.println(t.getMenuconsultaid()+", "+t.getNombre()+", "+t.getDependede());
        });
        return listaOrdenada;
    }
    
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
