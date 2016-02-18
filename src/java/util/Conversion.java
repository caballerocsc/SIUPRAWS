/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package util;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import obj.Capas;
import obj.Departamentos;
import obj.DinamicaMercados;
import obj.FiltroJson;
import obj.Filtros;
import obj.Menuconsultas;
import obj.Municipios;
import obj.Precios;
import obj.Servicios;
import obj.Tablacontenido;
import obj.graficos.PlantillaElementos;

/**
 * Clase que se encarga de tomar los objetos y convertirlos a Json
 * @author cesar.solano
 */
public class Conversion {
    
    public Conversion(){
        
    }
    
    /**
     * Método que convierte en json las lista del menú de consultas, y los departamentos con municipios
     * @param dep Lista de departamentos con municipios
     * @param menuconsultas Lista del menu de consultas
     * @return String con texto en formato json
     */
    public String DepartMuntoJson(List<Departamentos> dep, List<Menuconsultas> menuconsultas){
         String json;
         List<String> dep1=new ArrayList<>();
         List<String> mun=new ArrayList<>();
         Varios v=new Varios();
         menuconsultas=v.ordenarMenuConsultasPorPadre(menuconsultas);
         for (Departamentos d : dep) {
             List<String> l=new ArrayList<>();
             for (Municipios m : d.getMunicipiosCollection()) {
                     l.add("\""+m.getCodigodane()+"\"");
                     //este bloque de codigo se utiliza para no tener que recorrer de nuevo
                     // el arreglo de municipos mas adelante
                     String tmp="\"ext\":\""+m.getExt()+"\",";
                     tmp+="\"nomCorto\":\""+m.getNomcorto()+"\",";
                     tmp+="\"nom\":\""+m.getNombre()+"\",";
                     tmp+="\"nomLargo\":\""+m.getNomlargo()+"\"";
                     tmp="\""+m.getCodigodane()+"\":{"+tmp+"}";
                     mun.add(tmp);
             }
             json="\"dats\":{\"ens\":{\"muns\":["+addComma(l)+"]}},";
//             System.out.println(json);
             //
             json=json+"\"ext\":\""+d.getExt()+"\",";
             json=json+"\"nomCorto\":\""+d.getNomcorto()+"\",";
             json=json+"\"nom\":\""+d.getNombre()+"\",";
             json=json+"\"nomLargo\":\""+d.getNomlargo()+"\"";
             json="\""+d.getCodigodane()+"\":{"+json+"}";
//             System.out.println(json);
             dep1.add(json);
         }
         json="\"dats\":{"+addComma(dep1)+"},";
         json+="\"nom\":\"Departamentos\"";
         json="\"deps\":{"+json+"},";
         json+="\"muns\":{\"dats\":{"+addComma(mun)+"}, \"nom\":\"Municipios\"}";
         json="\"ens\":{"+json+"},";
         List<String> sMenu=new ArrayList<>();
         String men="";
         for (Menuconsultas m : menuconsultas) {
             if (m.getDependede()!=0) {
                 for (Menuconsultas m2 : menuconsultas) {
                     if(m.getDependede()==m2.getMenuconsultaid())
                         men="\"alOpPa\":\""+m2.getAlias()+"\",";
                 }
             }else
                 men="\"alOpPa\":null,";
             men+="\"tex\":\""+m.getTexto()+"\",";
             men+="\"nom\":\""+m.getNombre()+"\"";
             if(m.getConsultable())
                 men+=",\"es\":{\"c\":true}";
             men="\""+m.getAlias()+"\":{"+men+"}";
             sMenu.add(men);
        }
         men="\"dats\":{"+addComma(sMenu)+"}";
         men="\"mcsPpl\":{"+men+"}";
         json="resp({"+json+men+"})";
         return json;
     }
     
    /**
     * Método toma una lista de objetos y los concatena separados por comas
     * @param lista Lista String de objetos 
     * @return texto concatenado
     */
     public String addComma(List<String> lista){
         if (lista.size()>0) {
             int cont = lista.size();
             String json = "";
             for (int i = 0; i < cont - 1; i++) {
                 json += lista.get(i) + ",";
             }
             json += lista.get(cont - 1);
             return json;
         }else
             return "";
     }
     
     /**
      * Método que convierte en json la tabla de contendio
      * @param tc Lista con la tabla de contenido
      * @return String en formato json
      */
     public String TablaContenidotoJson( List<Tablacontenido> tc){
        String json="";
        List<String> sub2=new ArrayList<>();
        int cont;
        for (Tablacontenido tab : tc) {
            List<String> sub1=new ArrayList<>();
            if(tab.getDescripcion()!=null){
                json="\"desc\":"+"\""+tab.getDescripcion()+"\"";
                sub1.add(json);
            }
            if(tab.getNombre()!=null){
                json="\"nom\":"+"\""+tab.getNombre()+"\"";
                sub1.add(json);
            }
            if(tab.getPalabrasclave()!=null){
                json="\"palsCla\":"+"\""+tab.getPalabrasclave()+"\"";
                sub1.add(json);
            }
            cont=sub1.size();
            if(cont>1){
                json="";
                for (int i = 0; i < cont-1; i++) {// este for se puede rehusar en un metodo
                    json=json+sub1.get(i)+",";
                }
                json="\"inf\":{"+json+sub1.get(cont-1)+"}";   
            }
            if(cont>=1)
                json="\"inf\":{"+sub1.get(0)+"}";
            if(tab.isDesplegado())
                json=json+",\"es\":{\"desp\":true}";
            sub2.add("\""+tab.getAlias()+"\":{"+json+"}");
            System.out.println("el json va: "+json);
        }
        cont=sub2.size();
        json="";
        if(cont>1){
            for (int i = 0; i < cont-1; i++) {// este for se puede rehusar en un metodo
                json=json+sub2.get(i)+",";
            }
            json="\"acsgs\":{"+json+sub2.get(cont-1)+"}";
        }if(cont==1)
            json="\"acsgs\":{"+json+sub2.get(cont-1)+"}";
        System.out.println("ultimo paso: "+json);
        return json;
    }
     
     /**
      * Método que convierte en json los servicios
      * @param serv List con los servicios geográficos
      * @return String en formato json
      */
    public String ServiciostoJson(List<Servicios> serv){
        String json="";
        int cont;
        List<String> al=new ArrayList<>();
        for (Servicios s : serv) {
            List<String> sub1=new ArrayList<>();
            if(s.getTipoServidor()!=null){
                json="\"tServG\":\""+s.getTipoServidor()+"\"";
                sub1.add(json);
            }
            if(s.getUrl()!=null){
                json="\"url\":\""+s.getUrl()+"\"";
                sub1.add(json);
            }
            if(s.getVersion()!=null){
                json="\"verWms\":\""+s.getVersion()+"\"";
                sub1.add(json);
            }
            cont=sub1.size();
            if(cont>1){
                json="";
                for (int i = 0; i < cont-1; i++) {// este for se puede rehusar en un metodo
                    json=json+sub1.get(i)+",";
                }
                json="\"conf\":{"+json+sub1.get(cont-1)+"}"; 
            }
            if(s.getImportado()){
                json=json+",\"es\":"+s.getImportado()+"";
            }
            List<String> sub2=new ArrayList<>();
            if(s.getDescripcion()!=null){
                sub2.add("\"desc\":\""+s.getDescripcion()+"\"");
            }
            if(s.getNombre()!=null){
                sub2.add("\"nom\":\""+s.getNombre()+"\"");
            }
            if(s.getPalab_cl()!=null){
                sub2.add("\"palsCla\":\""+s.getPalab_cl()+"\"");
            }
            cont=sub2.size();
            String tmp="";
            if(cont>1){
                for (int i = 0; i < cont-1; i++) {// este for se puede rehusar en un metodo
                    tmp=tmp+sub2.get(i)+",";
                }
                json=json+",\"inf\":{"+tmp+sub2.get(cont-1)+"}"; 
            }
            al.add("\""+s.getAlias()+"\":{"+json+"}");
        }
        cont=al.size();
        json="";
        if(cont>1){
            for (int i = 0; i < cont-1; i++) {// este for se puede rehusar en un metodo
                json=json+al.get(i)+",";
            }
            json="\"ssgs\":{"+json+al.get(cont-1)+"}";
        }if(cont==1)
            json="\"ssgs\":{"+json+al.get(cont-1)+"}";
        System.out.println("ultimo paso: "+json);
        return json;
    }
    
    /**
     * Método que convierte en json las capas
     * @param capas Lista de capas
     * @return String en formato json
     */
    public String capastoJson(List<Capas> capas){
         List<String> json=new ArrayList<>();
         String resul;
         //String nivel1;
         String nivel2;
         String nivel3;
         String nivel4;
         String t4;
         List<String> tmp=new ArrayList<>();
         
         for (Capas capa : capas) {
             if(capa.getEscmax()!=null)
                 tmp.add("\"reMa\":"+capa.getEscmax());
             if(capa.getEscmin()!=null)
                 tmp.add("\"reMi\":"+capa.getEscmin());
             if(capa.getFiltro()!=null)
                 tmp.add("\"fis\":\""+capa.getFiltro()+"\"");
             if(capa.getLimites()!=null)
                 tmp.add("\"lims\":\""+capa.getLimites()+"\"");
             if(capa.getNombre_capa()!=null)
                 tmp.add("\"nomCg\":\""+capa.getNombre_capa()+"\"");
             if(capa.getOpacidad()!=null)
                 tmp.add("\"opa\":"+capa.getOpacidad());
             if(capa.getsTipoAcceso()!=null)
                 tmp.add("\"tCg\":\""+capa.getsTipoCapa()+"\"");
//            cont=tmp.size();
//            if(cont>1){
//                for (int i = 0; i < cont-1; i++) {// este for se puede rehusar en un metodo
//                    nivel4=nivel4+tmp.get(i)+",";
//                }
//            }
//            nivel4=nivel4+tmp.get(cont-1);
//            nivel4="\"conf\":{"+nivel4+"}";
            nivel4="\"conf\":{"+addComma(tmp)+"}";
            System.out.println(nivel4);
            tmp=new ArrayList<>();
            if(capa.getAutoident())
                 tmp.add("\"autoIden\":"+capa.getAutoident());
            if(capa.isVistaGeral())
                tmp.add("\"viVisGen\":"+capa.isVistaGeral());
            if(capa.getVisible())
                 tmp.add("\"vi\":"+capa.getVisible());
            if(capa.getLeyenda_c())
                 tmp.add("\"caLe\":"+capa.getLeyenda_c());
            if(capa.getIdentific())
                 tmp.add("\"iden\":"+capa.getIdentific());
            if(capa.getOrdenable())
                 tmp.add("\"or\":"+capa.getOrdenable());
            if(capa.getTransparente())
                 tmp.add("\"transp\":"+capa.getTransparente());
//            cont=tmp.size();
//            t4="";
//            if(cont>1){
//                for (int i = 0; i < cont-1; i++) {
//                     t4=t4+tmp.get(i)+",";
//                }
//            }
//            t4=t4+tmp.get(cont-1);
//            nivel4=nivel4+",\"es\":{"+t4+"}";
            nivel4=nivel4+",\"es\":{"+addComma(tmp)+"}";
            System.out.println(nivel4);
            tmp=new ArrayList<>();
            if(capa.getAnio()!=null)
                tmp.add("\"anio\":\""+capa.getAnio()+"\"");
            if(capa.getDescripcion()!=null)
                 tmp.add("\"desc\":\""+capa.getDescripcion()+"\"");
            if(capa.getEscala()!=null)
                 tmp.add("\"esc\":\""+capa.getEscala()+"\"");
            if(capa.getFuente()!=null)
                 tmp.add("\"fu\":\""+capa.getFuente()+"\"");
            if(capa.getNombre()!=null)
                 tmp.add("\"nom\":\""+capa.getNombre()+"\"");   
            if(capa.getPalab_cl()!=null)
                 tmp.add("\"palCla\":\""+capa.getPalab_cl()+"\"");
//            cont=tmp.size();
//            t4="";
//            if(cont>1){
//                for (int i = 0; i < cont-1; i++) {
//                     t4=t4+tmp.get(i)+",";
//                }
//            }
//            t4=t4+tmp.get(cont-1);
//            nivel4=nivel4+",\"inf\":{"+t4+"}";
            nivel4=nivel4+",\"inf\":{"+addComma(tmp)+"}";
            System.out.println(nivel4);
            t4="";
            if(capa.getAliasTablaContendio()!=null)
                t4="\"alGrcsgs\":"+"\""+capa.getAliasTablaContendio()+"\",";
            if(capa.getAliasservicio()!=null)
                t4=t4+"\"alSg\":\""+capa.getAliasservicio()+"\",";
            nivel3=t4+nivel4;
             System.out.println(nivel3);
            nivel2="\"cg"+capa.getAlias()+"\":{"+nivel3+"}";
             System.out.println(nivel2);
             //nivel1="{\"csgs\":{"+nivel2+"}";
             json.add(nivel2);
             //limpieza de variables
             //nivel1="";
             nivel2="";
             nivel3="";
             nivel4="";
             tmp=new ArrayList<>();
         }
//         resul="";
//        cont=json.size();
//        if(cont>1){
//            for (int i = 0; i < cont-1; i++) {
//                 resul=resul+json.get(i)+",";
//            }
//        }
//        resul="\"csgs\":{"+resul+json.get(cont-1)+"}";
        resul="\"csgs\":{"+addComma(json)+"}";
        System.out.println(resul);
        return resul;
     }
    
    /**
     * Método que se encarga de generar un json con la tabla de contenido, servicios y capas
     * @param sCapas String json de capas
     * @param sServ String json de servicios
     * @param sTc   String json de tabla de contenido
     * @return String en formato json 
     */
    public String tablasServiciosCapas(String sCapas, String sServ, String sTc){
        return "resp({"+sTc+","+sServ+","+sCapas+"})";
    }
    
    /**
     * Método que convierte una lista de filtros en una cadena String en formato Json
     * @param filtros lista de tipo Filtros para convertirla en Json
     * @return String en formato json con la información
     */
    public String filtrostoJson(List<Filtros> filtros){
        String ent="";
        String per="";
        //String otros;
        String nac="";
        List<String> Ldepar= new ArrayList<>();
        List<String> Lmun= new ArrayList<>();
        List<String> Lregi= new ArrayList<>();
        List<String> Lterr= new ArrayList<>();
        List<String> Lanios= new ArrayList<>();
        List<String> Lmeses= new ArrayList<>();
        List<String> Ltri= new ArrayList<>();
        List<String> Lsem= new ArrayList<>();
        List<String> LValotros= new ArrayList<>();
        List<String> Lentidades= new ArrayList<>();
        List<String> Lperiodos= new ArrayList<>();
        List<String> Lotros= new ArrayList<>();
        String nomFi="";
        String subTFi="";
        String tFi="";
        String tFiPa="";
        for (Filtros f : filtros) {
            switch(f.getAliasTipo()){
                case "ent":
                    if(f.getAliasSubTipo().equals("nac"))
                        nac=("\"nal\":{\"dats\":true}");
                    if(f.getAliasSubTipo().equals("dep"))
                        Ldepar.add("\""+f.getValorFiltro()+"\"");
                    if(f.getAliasSubTipo().equals("mun"))
                        Lmun.add("\""+f.getValorFiltro()+"\"");
                    if(f.getAliasSubTipo().equals("reg"))
                        Lregi.add("\""+f.getValorFiltro()+"\"");
                    if(f.getAliasSubTipo().equals("terr"))
                        Lterr.add("\""+f.getValorFiltro()+"\"");
                    break;
                case "per":
                    if(f.getAliasSubTipo().equals("an"))
                         Lanios.add(f.getValorFiltro());
                    if(f.getAliasSubTipo().equals("sem"))
                         Lsem.add(f.getValorFiltro());
                    if(f.getAliasSubTipo().equals("trim"))
                         Ltri.add(f.getValorFiltro());
                    if(f.getAliasSubTipo().equals("mens"))
                         Lmeses.add(f.getValorFiltro());
                    break;
                case "otr":
                    if(f.getValorFiltro()!=null)
                        LValotros.add(f.getValorFiltro());
                    if(f.getTextoTipo()!=null)
                        nomFi="\"nomFi\":\""+f.getTextoTipo()+"\"";
                    if(f.getTipoElemento()!=null){
                        subTFi="\"subTFi\":\""+f.getTipoElemento()+"\"";
                        if(f.getTipoElemento().equals("select"))
                           tFi="\"tFi\":\"input\"";
                    }
                    if(f.getTipoElemtoPadre()!=null)
                        tFiPa="\"tFiPa\":\""+f.getTipoElemtoPadre()+"\"";
                    break;
            }
        }
        String tmp;
        ///////////////////////////////
        ///////Entidades//////////////
        if(!nac.equals(""))
            Lentidades.add(nac);
        tmp=addComma(Ldepar);
        if(tmp!=null)
            Lentidades.add("\"deps\":{\"dats\":["+tmp+"]}");
        tmp=addComma(Lmun);
        if(tmp!=null)
            Lentidades.add("\"muns\":{\"dats\":["+tmp+"]}");
        tmp=addComma(Lregi);
        if(tmp!=null)
            Lentidades.add("\"regis\":{\"dats\":["+tmp+"]}");
        tmp=addComma(Lterr);
        if(tmp!=null)
            Lentidades.add("\"terrs\":{\"dats\":["+tmp+"]}");
        if(Lentidades.size()>0)
            ent="\"ens\":{"+addComma(Lentidades)+"}";
        //////////////////////////////////////////
        ////////Periodos/////////////////////
        tmp=addComma(Lanios);
        if(tmp!=null)
            Lperiodos.add("\"anios\":{\"dats\":["+tmp+"]}");
        tmp=addComma(Lmeses);
        if(tmp!=null)
            Lperiodos.add("\"meses\":{\"dats\":["+tmp+"]}");
        tmp=addComma(Ltri);
        if(tmp!=null)
            Lperiodos.add("\"trimestres\":{\"dats\":["+tmp+"]}");
         tmp=addComma(Lsem);
        if(tmp!=null)
            Lperiodos.add("\"semestres\":{\"dats\":["+tmp+"]}");
        if(Lperiodos.size()>0)
            per="\"pers\":{\"dats\":{"+addComma(Lperiodos)+"}}";
        //json  final
        String json;
        List<String> ljson=new ArrayList<>();
        if(!ent.equals(""))
            ljson.add(ent);
        if(!per.equals(""))
            ljson.add(per);
        json=addComma(ljson);
        json="resp({"+json+"})";
        System.out.println(json);
        return json;
        
    }
    
    
    public String crearJsonDinamMerc(FiltroJson filtro, List<DinamicaMercados> lista,Tablacontenido tc, List<Servicios> serv, List<Capas> capas){
        String tmp;
        String json;
        //inicio seccion tabla de información
        int numAnios=filtro.getAnios().length;
        if (numAnios==2) {
            String columnGroup = "";
            String column;
            String registros;
            String show;
            String sortData;
            String atTabs;
            List<String> titulosColumnas = new ArrayList<>();
            List<String> lSubRegistros = new ArrayList<>();
            List<String> lRegistros = new ArrayList<>();
            List<String> lColumnGroup = new ArrayList<>();
            List<String> lColumn = new ArrayList<>();
        //////Creacion de los ColumnGroups 
            //        for (int i = 0; i < 3; i++) {
            //            tmp="{\"caption\":\"\",\"span\":\"1\"}";
            //            lColumnGroup.add(tmp);
            //        }
            //        for (int i = 0; i < numAnios; i++) {
            //            tmp="{\"caption\":\""+filtro.getAnios()[i]+"\",\"span\":\"6\"}";
            //            lColumnGroup.add(tmp);
            //        }
            //        columnGroup="\"columnGroups\":["+addComma(lColumnGroup)+"]";
            //Creacion de las columnas
            lColumn.add("{\"field\":  \"recid\",\"caption\": \"#\",\"size\": \"50px\",\"sortable\": true,\"attr\": \"align=center\"}");
            lColumn.add("{\"field\":  \"codDane\",\"caption\": \"CódigoDANE\",\"size\": \"30px\",\"sortable\": true,\"resizable\": true}");
            lColumn.add("{\"field\":  \"departamento\",\"caption\": \"Departamento\",\"size\": \"100px\",\"sortable\": true,\"resizable\": true}");
            String titulo;
            //compraventa
            titulosColumnas.add("\"compraventa" + filtro.getAnios()[0] + "\"");
            lColumn.add("{\"field\":" + "\"compraventa" + filtro.getAnios()[0] + "\"" + ",\"caption\": \"# de compraventas\",\"size\": \"8%\",\"sortable\": true,\"attr\": \"align=center\",\"resizable\": true}");
            titulosColumnas.add("\"compraventa " + filtro.getAnios()[1] + "\"");
            lColumn.add("{\"field\":" + "\"compraventa " + filtro.getAnios()[1] + "\"" + ",\"caption\": \"# de compraventas\",\"size\": \"8%\",\"sortable\": true,\"attr\": \"align=center\",\"resizable\": true}");
            titulosColumnas.add("\"variacion1\"");
            lColumn.add("{\"field\":" + "\"variacion1\"" + ",\"caption\": \"Variación\",\"size\": \"8%\",\"sortable\": true,\"attr\": \"align=center\",\"resizable\": true}");
            titulosColumnas.add("\"variacionPorcentual1\"");
            lColumn.add("{\"field\":" + "\"variacionPorcentual1\"" + ",\"caption\": \"Variación Porcentual\",\"size\": \"10%\",\"sortable\": true,\"attr\": \"align=center\",\"resizable\": true}");
            //hipoteca
            titulosColumnas.add("\"hipoteca" + filtro.getAnios()[0] + "\"");
            lColumn.add("{\"field\":" + "\"hipoteca" + filtro.getAnios()[0] + "\"" + ",\"caption\": \"# de hipotecas\",\"size\": \"8%\",\"sortable\": true,\"attr\": \"align=center\",\"resizable\": true}");
            titulosColumnas.add("\"hipoteca" + filtro.getAnios()[1] + "\"");
            lColumn.add("{\"field\":" + "\"hipoteca" + filtro.getAnios()[1] + "\"" + ",\"caption\": \"# de hipotecas\",\"size\": \"8%\",\"sortable\": true,\"attr\": \"align=center\",\"resizable\": true}");
            titulosColumnas.add("\"variacion2\"");
            lColumn.add("{\"field\":" + "\"variacion2\"" + ",\"caption\": \"Variación\",\"size\": \"8%\",\"sortable\": true,\"attr\": \"align=center\",\"resizable\": true}");
            titulosColumnas.add("\"variacionPorcentual2\"");
            lColumn.add("{\"field\":" + "\"variacionPorcentual2\"" + ",\"caption\": \"Variación Porcentual\",\"size\": \"10%\",\"sortable\": true,\"attr\": \"align=center\",\"resizable\": true}");
            //remate
            titulosColumnas.add("\"remate" + filtro.getAnios()[0] + "\"");
            lColumn.add("{\"field\": " + "\"remate" + filtro.getAnios()[0] + "\"" + ",\"caption\": \"# de remates\",\"size\": \"8%\",\"sortable\": true,\"attr\": \"align=center\",\"resizable\": true}");
            titulosColumnas.add("\"remate" + filtro.getAnios()[1] + "\"");
            lColumn.add("{\"field\": " + "\"remate" + filtro.getAnios()[1] + "\"" + ",\"caption\": \"# de remates\",\"size\": \"8%\",\"sortable\": true,\"attr\": \"align=center\",\"resizable\": true}");
            titulosColumnas.add("\"variacion3\"");
            lColumn.add("{\"field\":" + "\"variacion3\"" + ",\"caption\": \"Variación\",\"size\": \"8%\",\"sortable\": true,\"attr\": \"align=center\",\"resizable\": true}");
            titulosColumnas.add("\"variacionPorcentual3\"");
            lColumn.add("{\"field\":" + "\"variacionPorcentual3\"" + ",\"caption\": \"Variación Porcentual\",\"size\": \"10%\",\"sortable\": true,\"attr\": \"align=center\",\"resizable\": true}");
            //permuta
            titulosColumnas.add("\"permuta" + filtro.getAnios()[0] + "\"");
            lColumn.add("{\"field\": " + "\"permuta" + filtro.getAnios()[0] + "\"" + ",\"caption\": \"# de permutas\",\"size\": \"8%\",\"sortable\": true,\"attr\": \"align=center\",\"resizable\": true}");
            titulosColumnas.add("\"permuta" + filtro.getAnios()[1] + "\"");
            lColumn.add("{\"field\": " + "\"permuta" + filtro.getAnios()[1] + "\"" + ",\"caption\": \"# de permutas\",\"size\": \"8%\",\"sortable\": true,\"attr\": \"align=center\",\"resizable\": true}");
            titulosColumnas.add("\"variacion4\"");
            lColumn.add("{\"field\":" + "\"variacion4\"" + ",\"caption\": \"Variación\",\"size\": \"8%\",\"sortable\": true,\"attr\": \"align=center\",\"resizable\": true}");
            titulosColumnas.add("\"variacionPorcentual4\"");
            lColumn.add("{\"field\":" + "\"variacionPorcentual4\"" + ",\"caption\": \"Variación Porcentual\",\"size\": \"10%\",\"sortable\": true,\"attr\": \"align=center\",\"resizable\": true}");
            //embargo
            titulosColumnas.add("\"embargo" + filtro.getAnios()[0] + "\"");
            lColumn.add("{\"field\": " + "\"embargo" + filtro.getAnios()[0] + "\"" + ",\"caption\": \"# de embargos\",\"size\": \"8%\",\"sortable\": true,\"attr\": \"align=center\",\"resizable\": true}");
            titulosColumnas.add("\"embargo" + filtro.getAnios()[1] + "\"");
            lColumn.add("{\"field\": " + "\"embargo" + filtro.getAnios()[1] + "\"" + ",\"caption\": \"# de embargos\",\"size\": \"8%\",\"sortable\": true,\"attr\": \"align=center\",\"resizable\": true}");
            titulosColumnas.add("\"variacion5\"");
            lColumn.add("{\"field\":" + "\"variacion5\"" + ",\"caption\": \"Variación\",\"size\": \"8%\",\"sortable\": true,\"attr\": \"align=center\",\"resizable\": true}");
            titulosColumnas.add("\"variacionPorcentual5\"");
            lColumn.add("{\"field\":" + "\"variacionPorcentual5\"" + ",\"caption\": \"Variación Porcentual\",\"size\": \"10%\",\"sortable\": true,\"attr\": \"align=center\",\"resizable\": true}");
            //titulo="\"pesoTransaccion"+i+"\"";
            //tmp="{\"field\": "+titulo+",\"caption\": \"#Peso de Transacciones\",\"size\": \"8%\",\"sortable\": true,\"attr\": \"align=center\",\"resizable\": true}";
            //titulosColumnas.add(titulo);
            //lColumn.add(tmp);
            column = "\"columns\":[" + addComma(lColumn) + "]";
            //creación de los registros
            int id = 1;
            Varios var = new Varios();
            for (int i = 0; i < lista.size(); i += numAnios) {
                int cont = 0;
                DinamicaMercados din = lista.get(i);
                lSubRegistros.add("\"recid\":" + id);
                lSubRegistros.add("\"codDane\":\"" + din.getIdDepart() + "\"");
                lSubRegistros.add("\"departamento\":\"" + din.getDepartamento() + "\"");
                //Compraventa
                lSubRegistros.add(titulosColumnas.get(cont++) + ":" + din.getCompraventa());
                lSubRegistros.add(titulosColumnas.get(cont++) + ":" + lista.get(i + 1).getCompraventa());
                lSubRegistros.add(titulosColumnas.get(cont++) + ":" + ((lista.get(i + 1)).getCompraventa() - din.getCompraventa()));
                lSubRegistros.add(titulosColumnas.get(cont++) + ":" + (var.calcularVariacionPorcentual(din.getCompraventa(), lista.get(i + 1).getCompraventa())));
                //hipoteca
                lSubRegistros.add(titulosColumnas.get(cont++) + ":" + din.getHipoteca());
                lSubRegistros.add(titulosColumnas.get(cont++) + ":" + lista.get(i + 1).getHipoteca());
                lSubRegistros.add(titulosColumnas.get(cont++) + ":" + ((lista.get(i + 1)).getHipoteca() - din.getHipoteca()));
                lSubRegistros.add(titulosColumnas.get(cont++) + ":" + (var.calcularVariacionPorcentual(din.getHipoteca(), (lista.get(i + 1)).getHipoteca())));
                //remate
                lSubRegistros.add(titulosColumnas.get(cont++) + ":" + din.getRemate());
                lSubRegistros.add(titulosColumnas.get(cont++) + ":" + lista.get(i + 1).getRemate());
                lSubRegistros.add(titulosColumnas.get(cont++) + ":" + ((lista.get(i + 1)).getRemate() - din.getRemate()));
                lSubRegistros.add(titulosColumnas.get(cont++) + ":" + (var.calcularVariacionPorcentual(din.getRemate(), (lista.get(i + 1)).getRemate())));
                //Permuta
                lSubRegistros.add(titulosColumnas.get(cont++) + ":" + din.getPermuta());
                lSubRegistros.add(titulosColumnas.get(cont++) + ":" + lista.get(i + 1).getPermuta());
                lSubRegistros.add(titulosColumnas.get(cont++) + ":" + ((lista.get(i + 1)).getPermuta() - din.getPermuta()));
                lSubRegistros.add(titulosColumnas.get(cont++) + ":" + (var.calcularVariacionPorcentual(din.getPermuta(), (lista.get(i + 1)).getPermuta())));
                //embargo
                lSubRegistros.add(titulosColumnas.get(cont++) + ":" + din.getEmbargo());
                lSubRegistros.add(titulosColumnas.get(cont++) + ":" + lista.get(i + 1).getEmbargo());
                lSubRegistros.add(titulosColumnas.get(cont++) + ":" + ((lista.get(i + 1)).getEmbargo() - din.getEmbargo()));
                lSubRegistros.add(titulosColumnas.get(cont++) + ":" + (var.calcularVariacionPorcentual(din.getEmbargo(), (lista.get(i + 1)).getEmbargo())));
                lRegistros.add("{" + addComma(lSubRegistros) + "}");
                lSubRegistros.clear();
                id++;
            }
            registros = "\"records\":[" + addComma(lRegistros) + "]";
            //creacion parametro show
            show = "\"show\":{\"toolbar\":false,\"footer\":false}";
            //creacion del parametro sortdata
            sortData = "\"sortData\": [{\"field\": \"recid\",\"direction\": \"ASC\"}]";
            atTabs = "\"atTabs\":{\"plantilla\":1,\"dats\":[{" + show + "," + column + "," + sortData + "," + registros + "}]}";
            System.out.println(atTabs);
        //fin seccion tabla de información
            /////////////////////////////////
            //inicio seccion gráfico
            String tipoGrafico;
            String tituloGraf;
            String ejeX;
            String ejeY;
            String tooltip;
            String series;
            String atGras;
            List<String> subseries = new ArrayList<>();
            //creació tpo de grafico
            tipoGrafico = "\"chart\":{\"type\":\"column\"}";
            //Creación del titulo del gráfico
            tmp = "";
            for (int i = 0; i < filtro.getAnios().length - 1; i++) {
                if (i == filtro.getAnios().length - 2) {
                    tmp += filtro.getAnios()[i] + " y ";
                } else {
                    tmp += filtro.getAnios()[i] + ", ";
                }
            }
            tmp += filtro.getAnios()[filtro.getAnios().length - 1];
            System.out.println(tmp);
            tituloGraf = "\"titulo\":{\"text\":\"Número de Transacciones y Actos Registrales " + tmp + "\"}";
            //Creación del eje X
            ejeX = "\"xAxis\":{\"categories\":[\"Compraventas\",\"Hipotecas\",\"Remates\",\"Remates\",\"Embargos\"],\"crosshair\":true}";
            //Creación del eje Y
            ejeY = "\"yAxis\": {\"min\":0,\"title\":{\"text\": \"# de Transacciones y Actos Registrales\"},"
                    + "\"labels\": {\"format\": \"{value}\"}}";
            //Creación del tooltip
            tooltip = "\"tooltip\": {\"shared\": true}";
            //Creación de las series
            for (int i = 0; i < filtro.getAnios().length; i++) {
                List<Integer> sum = var.sumatoriaTransaccionesPorAnio(Integer.parseInt(filtro.getAnios()[i]), lista);
                tmp = "";
                tmp += "\"data\":[" + sum.get(0) + "," + sum.get(1) + "," + sum.get(2) + "," + sum.get(3) + "," + sum.get(4) + "]";
                tmp = "{\"name\":\"" + filtro.getAnios()[i] + "\",\"type\":\"column\"," + tmp + "}";
                subseries.add(tmp);
            }
            series = "\"series\":[" + addComma(subseries) + "]";
            atGras = "\"atGras\":{\"plantilla\":1,\"dats\":[{" + tipoGrafico + "," + tituloGraf + "," + ejeX + "," + ejeY + "," + tooltip
                    + "," + series + "}]}";
            System.out.println(atGras);
            //fin sección gráfico
            //////////////////////////////////////
            //inicio sección geográfica (tabla de contenido)
            List<String> iden=new ArrayList<>();
            List<String> dats=new ArrayList<>();
            String identificacion;
            for (int i = 0; i < filtro.getAnios().length; i++) {
                iden.add("\"al\": \"cgDepartamentos\"");
                iden.add("\"compCg\": \"codigodane\"");
                iden.add("\"compTab\": \"codDane\"");
                iden.add("\"tex\":\"Transacciones "+filtro.getAnios()[i]+"\"");
                iden.add("\"tab\":{\"ind\":0,\"cols\":[\"departamento\", \"compraventa"+filtro.getAnios()[i]+"\", \"remate"+filtro.getAnios()[i]+"\","
                        + " \"permuta"+filtro.getAnios()[i]+"\", \"embargo"+filtro.getAnios()[i]+"\", \"hipoteca"+filtro.getAnios()[i]+"\"]}");
                iden.add("\"gra\":{\"t\":\"pie\",\"indTab\": 0,\"cols\":[\"compraventa"+filtro.getAnios()[i]+"\", \"remate"+filtro.getAnios()[i]+"\","
                        + " \"permuta"+filtro.getAnios()[i]+"\", \"embargo"+filtro.getAnios()[i]+"\", \"hipoteca"+filtro.getAnios()[i]+"\"],"
                        + "\"colores\":[\"#FFAA00\", \"#FFFF00\", \"#FF00C5\", \"#C400B2\", \"#C500FF\", \"#FEB7E2\"]}");
                dats.add("{"+addComma(iden)+"}");
            }
            identificacion="\"identificacion\":{\"t\":\"auto\",\"dats\":["+addComma(dats)+"]}";
            String atMaps=crearJsonInfGeoConsultas(tc, serv, capas,identificacion);
            System.out.println(atMaps);
            //fin seccion geográfica
            json = "resp({\"ast\":{" + atMaps + "," + atTabs + "," + atGras + "},\"atSel\":\"atMaps\"}})";
            return json;
        } else {
            return "resp(\"la consulta contiene mas de dos años como filtro de búsqueda\")";
        }
    }
    
    /**
     * Método que se encarga de tomar los filtro y concatenarlos con un OR, si la lista no
     * contiene elementos retorna un String vacio.
     * @param lista con elemento a unir
     * @return ejemplo 1: 201 or 2015 or 2016 ejemplo 2: auto or casa or oficina
     */
    public String addOr(List<String> lista){
         if (lista.size()>0) {
             int cont = lista.size();
             String cad = "";
             for (int i = 0; i < cont - 1; i++) {
                 cad += lista.get(i) + " or ";
             }
             cad += lista.get(cont - 1);
             return cad;
         }else
             return "";
     }
    
    public String crearJsonInfGeoConsultas(Tablacontenido tc, List<Servicios> lServ, List<Capas> capas,String iden){
        String tmp;
        String atMaps;
        String acsgs = "\"desc\":\"" + tc.getDescripcion() + "\","
                + "\"nom\":\"" + tc.getNombre() + "\","
                + "\"palsCla\":\"" + tc.getPalabrasclave() + "\"";
        acsgs = "\"inf\":{" + acsgs + "}";
        acsgs = "\"" + tc.getAlias() + "\":{" + acsgs + "}";
        acsgs = "\"acsgs\":{" + acsgs + "}";
        //(Servicios geográficos)
        List<String> listServ=new ArrayList<>();
        String ssgs;
        for (Servicios serv : lServ) {
            ssgs = "\"tServSg\":\"" + serv.getTipoServidor() + "\",";
            ssgs += "\"url\":\"" + serv.getUrl() + "\"";
            ssgs = "\"conf\":{" + ssgs + "},";
            ssgs += "\"es\":{\"impor\":" + serv.getImportado() + "},";
            ssgs += "\"inf\":{\"nom\":\"" + serv.getNombre() + "\","
                    + "\"palsCla\":\"" + serv.getPalab_cl() + "\"}";
            ssgs = "\"" + serv.getAlias() + "\":{" + ssgs + "}";
            listServ.add(ssgs);
        }
        ssgs = "\"ssgs\":{" + addComma(listServ) + "}";
        //(capas )
        List<String> lCsgs = new ArrayList<>();
        String csgs;
        for (Capas c : capas) {
            //tmp = "";
            tmp = "\"alGrcsgs\":\"" + c.getAliasgrupo() + "\",";
            tmp += "\"alSg\":\"" + c.getAliasservicio() + "\",";
            tmp += "\"conf\":{\"reMa\":" + c.getEscmax() + ","
                    + "\"fis\":\"" + c.getFiltro() + "\","
                    + "\"nomCg\":\"" + c.getNombre_capa() + "\","
                    + "\"opa\":" + c.getOpacidad() + ","
                    + "\"sire\":\"" + c.getCrs() + "\"},";
            tmp += "\"inf\":{\"anio\":" + c.getAnio() + ","
                    + "\"fu\":\"" + c.getFuente() + "\","
                    + "\"nom\":\"" + c.getTitulo() + "\"},";
            tmp+="\"es\":{\"vi\":"+c.getVisible()+","
                    + "\"iden\":"+c.getIdentific()+","
                    + "\"caLe\":"+c.getLeyenda_c()+"}";
            tmp = "\"" + c.getAlias() + "\":{" + tmp + "}";
            lCsgs.add(tmp);
        }
        csgs = "\"ssgs\":{" +addComma(lCsgs)+ "}";
        atMaps = "\"atMaps\":{\"idAt\":\"atMaps\",\"dats\":{" + acsgs + "," + ssgs + "," + csgs +((iden.equals(""))?"":","+iden)+"}";
        return atMaps;
    }
    
    public String crearJsonPrecios(Tablacontenido tc, List<Servicios> serv, List<Capas> capas, FiltroJson fil, List<Precios> precios, List<Precios> sumatoria){
        String tmp;
        //Creación del json para el área de trabajo del mapa
        String identificacion="";
        for (Capas capas1 : capas) {
            if (capas1.getAutoident()) {
                identificacion="\"identificacion\":{\"t\":\"auto\",\"dats\":[{\"al\":\""+capas1.getAlias()+"\",\"compCg\":\"id\",\"compTab\":\"recid\","
                        + "\"tab\":{\"ind\":0,\"cols\":[\"departamento\", \"municipio\", \"rango_precio\", \"area\"]}}]},\"ext\": "
                        + "["+capas1.getLimites()+"]";
            }
        }
        String atMaps=crearJsonInfGeoConsultas(tc, serv, capas,identificacion);
        System.out.println(atMaps);
        //Creación del área de trabajo de tabla
        List<String> lColumn=new ArrayList<>();
        List<String> lSubRegistros = new ArrayList<>();
        List<String> lRegistros = new ArrayList<>();
        String column;
        String registros;
        String show;
        String sortData;
        String searches;
        String atTabs;
        ///Creación de las columnas de la tabla
        lColumn.add("{\"field\":  \"recid\",\"caption\": \"No.\",\"size\": \"40px\",\"sortable\": true,\"attr\": \"align=center\"}");
        lColumn.add("{\"field\":  \"codDane\",\"caption\": \"Código DANE\",\"size\": \"10%\",\"sortable\": true,\"attr\": \"align=center\"}");
        lColumn.add("{\"field\":  \"departamento\",\"caption\": \"Departamento\",\"size\": \"15%\",\"sortable\": true,\"resizable\": true}");
        lColumn.add("{\"field\":  \"municipio\",\"caption\": \"Municipio\",\"size\": \"15%\",\"sortable\": true,\"attr\": \"align=center\"}");
        lColumn.add("{\"field\":  \"rango_precio\",\"caption\": \"Rango de Precio\",\"size\": \"10%\",\"sortable\": true,\"resizable\": true}");
        lColumn.add("{\"field\":  \"area\",\"caption\": \"Área (Ha)\",\"size\": \"10%\",\"sortable\": true,\"resizable\": true}");
        /*lColumn.add("{\"field\":  \"rango\",\"caption\": \"Rango\",\"size\": \"10%\",\"sortable\": true,\"resizable\": true}");*/
        column = "\"columns\":[" + addComma(lColumn) + "]";
        ///Creación de los registros de la tabla
        int i=1;
        for (Precios p : precios) {
            lSubRegistros.add("\"recid\":"+i);
            lSubRegistros.add("\"codDane\":\""+p.getIdMun()+"\"");
            lSubRegistros.add("\"departamento\":\""+p.getDepartamento()+"\"");
            lSubRegistros.add("\"municipio\":\""+p.getMunicipio()+"\"");
            lSubRegistros.add("\"rango_precio\":\""+p.getRango()+"\"");
            lSubRegistros.add("\"area\":"+p.getArea());
            lSubRegistros.add("\"rango\":\""+p.getNombre()+"\"");
            lRegistros.add("{" + addComma(lSubRegistros) + "}");
            lSubRegistros.clear();
            i++;
        }
        registros = "\"records\":[" + addComma(lRegistros) + "]";
        //creacion parametro show
        show = "\"show\":{\"toolbar\":false,\"footer\":false}";
        //creacion del parametro sortdata
        sortData = "\"sortData\": [{\"field\": \"recid\",\"direction\": \"ASC\"}]";
        //creacion del parametro searches
        searches="\"searches\": [{\"field\": \"departamento\",\"caption\": \"Departamento\",\"type\": \"text\"}]";
        atTabs = "\"atTabs\":{\"plantilla\":1,\"dats\":[{" + show + "," + column + "," + sortData + "," + registros +","+searches+"}]}";
        System.out.println(atTabs);
        //creacion del grafico
        List<String> graf1=new ArrayList();
        String grafico1;
        graf1.add("\"chart\":{\"type\": \"pie\",\"options3d\": {\"enabled\": true,\"alpha\": 45,\"beta\": 0}}");
        graf1.add("\"title\": {\"text\": \"Área en hectáreas por rango de precios\"}");
        graf1.add("\"tooltip\": {\"pointFormat\": \"{series.name}: <b>{point.percentage:.1f}%</b>\"}");
        graf1.add("\"plotOptions\": {\"pie\": {\"allowPointSelect\": true,\"cursor\": \"pointer\",\"depth\": 35," +
                    "\"dataLabels\":{\"enabled\": false},\"showInLegend\": true}}");
        List<String> lSeries=new ArrayList();
        HashMap<String,Double> rangos;
        Varios var=new Varios();
        rangos=var.SumatoriaAreasPrecios(precios);
        Iterator iterator = rangos.keySet().iterator();
        while(iterator.hasNext()){
            String key=(String)iterator.next();
            tmp = "[\""+key+"\","+rangos.get(key)+"]";
            lSeries.add(tmp);
        }
        graf1.add("\"series\": [{\"type\": \"pie\",\"name\": \"Porcentaje\",\"data\": ["+addComma(lSeries)+"]}]");
        grafico1="{"+addComma(graf1)+"}";
        List<String> graf2=new ArrayList();
        String grafico2;
        graf2.add("\"chart\":{\"type\":\"column\",\"options3d\":{\"enabled\":true,\"alpha\": 45,\"beta\": 0}}");
        graf2.add("\"title\":{\"text\": \"Área en hectáreas por rango de precios por municipio\"}");
        List<String> lCat=new ArrayList<>();
        iterator= rangos.keySet().iterator();
        while(iterator.hasNext()){
          lCat.add("\""+(String)iterator.next()+"\"");
        }
        graf2.add("\"xAxis\":{\"categories\":["+addComma(lCat)+"],\"crosshair\": true}");
        graf2.add("\"yAxis\":{\"min\": 0,\"title\":{\"text\":\"Área en hectáreas\"},\"labels\":{\"format\": \"{value}\"}}");
        graf2.add("\"tooltip\":{\"shared\": true}");
        //almacenar la lista de municipios
        List<String> lMunpios=new ArrayList<>();
        for (Precios p : sumatoria) {
            if(!lMunpios.contains(p.getMunicipio()))
                lMunpios.add(p.getMunicipio());
        }
        //sumatoria de los rangos por municipio
        lSeries.clear();
        int numCat=lCat.size();
        for (String munpio : lMunpios) {
            int con=0;
            List<String> sum=new ArrayList <>();
            for (String categoria : lCat) {
                for (Precios p :sumatoria) {
                    if(p.getMunicipio().equals(munpio)&&p.getRango().equals(categoria)){
                      sum.add(String.valueOf(p.getArea()));
                      con++;
                    }
                }
            }
            //si hay rangos sin area, se coloca cero
            if(con!=numCat)
                for (int j = 0; j < numCat-con; j++) {
                  sum.add("0");
                }
            lSeries.add("{\"name\":\""+munpio+"\",\"type\":\"column\",\"data\":["+addComma(sum)+"]}");
        }
        graf2.add("\"series\":["+addComma(lSeries)+"]");
        grafico2="{"+addComma(graf2)+"}";
        String atGras="\"atGras\":{\"plantilla2\":2,\"dats\":["+grafico1+","+grafico2+"]}";
        String json = "resp({\"ast\":{" + atMaps + "," + atTabs + "," + atGras + "},\"atSel\":\"atMaps\"}})";
        return json;
    }
    
    public String pruebaJsonGson(PlantillaElementos pe){
        Gson gson= new Gson();
        return gson.toJson(pe, PlantillaElementos.class);
    }
}
