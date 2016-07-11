/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import obj.Actores;
import obj.Capas;
import obj.Departamentos;
import obj.DinamicaMercados;
import obj.FiltroJson;
import obj.Filtros;
import obj.Menuconsultas;
import obj.Municipios;
import obj.Precios;
import obj.Areas;
import obj.BancoProyectos;
import obj.DistritosRiego;
import obj.InfoyDocs;
import obj.Servicios;
import obj.Tablacontenido;

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
             json="\"dats\":{\"ens\":{\"muns\":["+addCommaString(l)+"]}},";
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
         json="\"dats\":{"+addCommaString(dep1)+"},";
         json+="\"nom\":\"Departamentos\"";
         json="\"deps\":{"+json+"},";
         json+="\"muns\":{\"dats\":{"+addCommaString(mun)+"}, \"nom\":\"Municipios\"}";
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
         men="\"dats\":{"+addCommaString(sMenu)+"}";
         men="\"mcsPpl\":{"+men+"}";
         json="resp({"+json+men+"})";
         return json;
     }
     
    /**
     * Método toma una lista de objetos y los concatena separados por comas
     * @param lista Lista String de objetos 
     * @return texto concatenado
     */
     public String addCommaString(List<String> lista){
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
     * Método toma una lista de objetos y los concatena separados por comas
     * @param lista Lista de tipo bigdecimal
     * @return texto concatenado
     */
    public String addCommaBigDecimal(List<BigDecimal> lista){
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
     * Método toma una lista de objetos y los concatena separados por comas
     * @param lista Lista de tipo Integer
     * @return texto concatenado
     */
    public String addCommaInteger(List<Integer> lista){
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
//            System.out.println("el json va: "+json);
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
//        System.out.println("ultimo paso: "+json);
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
//        System.out.println("ultimo paso: "+json);
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
                 tmp.add("\"nom\":\""+capa.getNombre_capa()+"\"");
             if(capa.getOpacidad()!=null)
                 tmp.add("\"opa\":"+capa.getOpacidad());
             if(capa.getsTipoAcceso()!=null)
                 tmp.add("\"t\":\""+capa.getsTipoCapa()+"\"");
            nivel4="\"conf\":{"+addCommaString(tmp)+"}";
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
            if(capa.getBase())
                tmp.add("\"bas\":"+capa.getBase());
            nivel4=nivel4+",\"es\":{"+addCommaString(tmp)+"}";
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
            if(capa.getImagen()!=null)
                tmp.add("\"img\":\""+capa.getImagen()+"\"");
            nivel4=nivel4+",\"inf\":{"+addCommaString(tmp)+"}";
            t4="";
            if(capa.getAliasTablaContendio()!=null)
                t4="\"alGr\":"+"\""+capa.getAliasTablaContendio()+"\",";
            if(capa.getAliasservicio()!=null)
                t4=t4+"\"alSg\":\""+capa.getAliasservicio()+"\",";
            nivel3=t4+nivel4;
            nivel2="\"cg"+capa.getAlias()+"\":{"+nivel3+"}";
             json.add(nivel2);
             nivel2="";
             nivel3="";
             nivel4="";
             tmp=new ArrayList<>();
         }
        resul="\"csgs\":{"+addCommaString(json)+"}";
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
    public String filtrostoJson(List<Filtros> filtros) {
        if (filtros.size() == 0) {
            return null;
        } else {
            String ent = "";
            String per = "";
            //String otros;
            String nac = "";
            List<String> Ldepar = new ArrayList<>();
            List<String> Lmun = new ArrayList<>();
            List<String> Lregi = new ArrayList<>();
            List<String> Lterr = new ArrayList<>();
            List<String> Lanios = new ArrayList<>();
            List<String> Lmeses = new ArrayList<>();
            List<String> Ltri = new ArrayList<>();
            List<String> Lsem = new ArrayList<>();
            List<String> LValotros = new ArrayList<>();
            List<String> Lentidades = new ArrayList<>();
            List<String> Lperiodos = new ArrayList<>();
            List<String> Lotros = new ArrayList<>();
            String nomFi = "";
            String subTFi = "";
            String tFi = "";
            String tFiPa = "";
            for (Filtros f : filtros) {
                switch (f.getAliasTipo()) {
                    case "ent":
                        if (f.getAliasSubTipo().equals("nac")) {
                            nac = ("\"nal\":{\"dats\":true}");
                        }
                        if (f.getAliasSubTipo().equals("dep")) {
                            Ldepar.add("\"" + f.getValorFiltro() + "\"");
                        }
                        if (f.getAliasSubTipo().equals("mun")) {
                            Lmun.add("\"" + f.getValorFiltro() + "\"");
                        }
                        if (f.getAliasSubTipo().equals("reg")) {
                            Lregi.add("\"" + f.getValorFiltro() + "\"");
                        }
                        if (f.getAliasSubTipo().equals("terr")) {
                            Lterr.add("\"" + f.getValorFiltro() + "\"");
                        }
                        break;
                    case "per":
                        if (f.getAliasSubTipo().equals("an")) {
                            Lanios.add(f.getValorFiltro());
                        }
                        if (f.getAliasSubTipo().equals("sem")) {
                            Lsem.add(f.getValorFiltro());
                        }
                        if (f.getAliasSubTipo().equals("trim")) {
                            Ltri.add(f.getValorFiltro());
                        }
                        if (f.getAliasSubTipo().equals("mens")) {
                            Lmeses.add(f.getValorFiltro());
                        }
                        break;
                    case "otr":
                        if (f.getValorFiltro() != null) {
                            LValotros.add(f.getValorFiltro());
                        }
                        if (f.getTextoTipo() != null) {
                            nomFi = "\"nomFi\":\"" + f.getTextoTipo() + "\"";
                        }
                        if (f.getTipoElemento() != null) {
                            subTFi = "\"subTFi\":\"" + f.getTipoElemento() + "\"";
                            if (f.getTipoElemento().equals("select")) {
                                tFi = "\"tFi\":\"input\"";
                            }
                        }
                        if (f.getTipoElemtoPadre() != null) {
                            tFiPa = "\"tFiPa\":\"" + f.getTipoElemtoPadre() + "\"";
                        }
                        break;
                }
            }
            String tmp;
        ///////////////////////////////
            ///////Entidades//////////////
            if (!nac.equals("")) {
                Lentidades.add(nac);
            }
            tmp = addCommaString(Ldepar);
            if (tmp != null) {
                Lentidades.add("\"deps\":{\"dats\":[" + tmp + "]}");
            }
            tmp = addCommaString(Lmun);
            if (tmp != null) {
                Lentidades.add("\"muns\":{\"dats\":[" + tmp + "]}");
            }
            tmp = addCommaString(Lregi);
            if (tmp != null) {
                Lentidades.add("\"regis\":{\"dats\":[" + tmp + "]}");
            }
            tmp = addCommaString(Lterr);
            if (tmp != null) {
                Lentidades.add("\"terrs\":{\"dats\":[" + tmp + "]}");
            }
            if (Lentidades.size() > 0) {
                ent = "\"ens\":{" + addCommaString(Lentidades) + "}";
            }
        //////////////////////////////////////////
            ////////Periodos/////////////////////
            tmp = addCommaString(Lanios);
            if (tmp != null) {
                Lperiodos.add("\"anios\":{\"dats\":[" + tmp + "]}");
            }
            tmp = addCommaString(Lmeses);
            if (tmp != null) {
                Lperiodos.add("\"meses\":{\"dats\":[" + tmp + "]}");
            }
            tmp = addCommaString(Ltri);
            if (tmp != null) {
                Lperiodos.add("\"trimestres\":{\"dats\":[" + tmp + "]}");
            }
            tmp = addCommaString(Lsem);
            if (tmp != null) {
                Lperiodos.add("\"semestres\":{\"dats\":[" + tmp + "]}");
            }
            if (Lperiodos.size() > 0) {
                per = "\"pers\":{\"dats\":{" + addCommaString(Lperiodos) + "}}";
            }
            //json  final
            String json;
            List<String> ljson = new ArrayList<>();
            if (!ent.equals("")) {
                ljson.add(ent);
            }
            if (!per.equals("")) {
                ljson.add(per);
            }
            json = addCommaString(ljson);
            json = "resp({" + json + "})";
            return json;
        }
    }
    
    /**
     * @param filtro Objeto que contiene la lista de años que se deben filtrar para la consulta
     * @param lista Información temática de la dinámica de mercados
     * @param tc tabla de contenido asociada a la consulta
     * @param serv servicio geográfico asociado a las consultas
     * @param capas capas geográficas asociadas a la consulta
     * @return String en formato json con la información de la consulta
     */
    public String crearJsonDinamMerc(FiltroJson filtro, List<DinamicaMercados> lista,Tablacontenido tc, List<Servicios> serv, List<Capas> capas){
        Varios v = new Varios();
        boolean opcional = (capas.size() != 1);
        String iden2 = "";
        String orCsgs = (opcional)?"[\""+capas.get(0).getAlias()+"\",\""+capas.get(1).getAlias()+"\", \"cgDepartamentos\"]":"[\""+capas.get(0).getAlias()+"\", \"cgDepartamentos\"]";
        String iden1="{\n" +
                    "\"al\": \"cgDepartamentos\",\n" +
                    //"\"ind\": 0 ,\n" +
                    "\"compCg\": \"codigodane\",\n" +
                    "\"compTab\": \"Código DANE\",\n" +
                    "\"tex\": \"Transacciones "+filtro.getAnios()[0]+"\"," +    
                    "\"tab\":{\n" +
                    "\"ind\": 0,\n" +
                    "\"colums\": [\"Departamento\",\"compraventa1\",\"remate1\",\"permuta1\",\"embargo1\",\"hipoteca1\",\"total1\"]," +
                    "\"otros\":{\"texRemplazar\":\""+filtro.getAnios()[0]+"\"}}," +
                    "\"gra\":{\"t\": \"p\",\n" +
                    "\"indTab\": 0,\n" +
                    "\"colums\": [\"compraventa1\", \"remate1\", \"permuta1\", \"embargo1\", \"hipoteca1\"],\n" +
                    "\"cols\": [\"#FFAA00\", \"#FFFF00\", \"#FF00C5\", \"#C400B2\", \"#C500FF\", \"#FEB7E2\"], \n" +
                    "\"otros\": {\"texRemplazar\": \""+filtro.getAnios()[0]+"\"}}" +
                    "}";
        if(opcional){
            iden2="{\n" +
                    "\"al\": \"cgDepartamentos\",\n" +
                    "\"compCg\": \"codigodane\",\n" +
                    "\"compTab\": \"Código DANE\",\n" +
                    "\"tex\": \"Transacciones "+filtro.getAnios()[1]+"\"," +    
                    "\"tab\":{\n" +
                    //"\"ind\": 0,\n" +
                    "\"colums\": [\"Departamento\",\"compraventa2\",\"remate2\",\"permuta2\",\"embargo2\",\"hipoteca2\",\"total2\"]," +
                    "\"otros\":{\"texRemplazar\":\""+filtro.getAnios()[1]+"\"}}," +
                    "\"gra\":{\"t\": \"p\",\n" +
                    "\"indTab\": 0,\n" +
                    "\"colums\": [\"compraventa2\", \"remate2\", \"permuta2\", \"embargo2\", \"hipoteca2\"],\n" +
                    "\"cols\": [\"#FFAA00\", \"#FFFF00\", \"#FF00C5\", \"#C400B2\", \"#C500FF\", \"#FEB7E2\"], \n" +
                    "\"otros\": {\"texRemplazar\": \""+filtro.getAnios()[1]+"\"}}" +
                    "}";
        }
        String identificacion="\"ext\":[],\"orCsgs\":"+orCsgs+",\n" +
                    "\"identificacion\":{\"t\": \"auto\",\"dats\": ["+iden1+","+iden2+"]}";
        String atMaps=crearJsonInfGeoConsultas(tc, serv, capas,identificacion);
        //creacion de los encabezados de la tabla
        List<String> listColumnas = new ArrayList();
        String column;
        listColumnas.add("[\"Departamento\", \"t\", \"150px\"]");
        listColumnas.add("[\"Compraventa1\", \"n\", \"120px\"]");
        if (opcional) {
            listColumnas.add("[\"Compraventa2\", \"n\", \"120px\"]");
            listColumnas.add("[\"Variación Compraventa\", \"n\", \"120px\"]");
            listColumnas.add("[\"Variación Porcentual Compraventa\", \"p\", \"50px\"]");
        }
        listColumnas.add("[\"Remate1\", \"n\", \"120px\"]");
        if (opcional) {
            listColumnas.add("[\"Remate2\", \"n\", \"120px\"]");
            listColumnas.add("[\"Variación Remate\", \"n\", \"120px\"]");
            listColumnas.add("[\"Variación Porcentual Remate\", \"p\", \"50px\"]");
        }
        listColumnas.add("[\"Permuta1\", \"n\", \"120px\"]");
        if (opcional) {
            listColumnas.add("[\"Permuta2\", \"n\", \"120px\"]");
            listColumnas.add("[\"Variación Permuta\", \"n\", \"120px\"]");
            listColumnas.add("[\"Variación Porcentual Permuta\", \"p\", \"50px\"]");
        }
        listColumnas.add("[\"Hipoteca1\", \"n\", \"120px\"]");
        if (opcional) {
            listColumnas.add("[\"Hipoteca2\", \"n\", \"120px\"]");
            listColumnas.add("[\"Variación Hipoteca\", \"n\", \"120px\"]");
            listColumnas.add("[\"Variación Porcentual Hipoteca\", \"p\", \"50px\"]");
        }
        listColumnas.add("[\"Total Transacciones\", \"n\", \"150px\"]");
        if (opcional) {
            listColumnas.add("[\"Total Transacciones2\", \"n\", \"150px\"]");
        }
        column = "\"colums\":[" + addCommaString(listColumnas) + "]";
        List<String> registros = new ArrayList();
        String tabla;
        int inc = (opcional)?2:1;
        for (int i = 0; i < lista.size(); i+=inc) {
            List<String> subReg = new ArrayList<>();
            DinamicaMercados dm =lista.get(i);
            subReg.add("\""+dm.getDepartamento()+"\"");
            subReg.add(""+dm.getCompraventa());
            if (opcional) {
                subReg.add(""+lista.get(i+1).getCompraventa());
                subReg.add(""+(lista.get(i+1).getCompraventa()-dm.getCompraventa()));
                subReg.add(""+v.calcularVariacionPorcentual(dm.getCompraventa(),lista.get(i+1).getCompraventa()));
            }
            subReg.add(""+dm.getRemate());
            if (opcional) {
                subReg.add(""+lista.get(i+1).getRemate());
                subReg.add(""+(lista.get(i+1).getRemate()-dm.getRemate()));
                subReg.add(""+v.calcularVariacionPorcentual(dm.getRemate(),lista.get(i+1).getRemate()));
            }
            subReg.add(""+dm.getPermuta());
            if (opcional) {
                subReg.add(""+lista.get(i+1).getPermuta());
                subReg.add(""+(lista.get(i+1).getPermuta()-dm.getPermuta()));
                subReg.add(""+v.calcularVariacionPorcentual(dm.getPermuta(),lista.get(i+1).getPermuta()));
            }
            subReg.add(""+dm.getEmbargo());
            if (opcional) {
                subReg.add(""+lista.get(i+1).getEmbargo());
                subReg.add(""+(lista.get(i+1).getEmbargo()-dm.getEmbargo()));
                subReg.add(""+v.calcularVariacionPorcentual(dm.getEmbargo(),lista.get(i+1).getEmbargo()));
            }
            subReg.add(""+dm.getHipoteca());
            if (opcional) {
                subReg.add(""+lista.get(i+1).getHipoteca());
                subReg.add(""+(lista.get(i+1).getHipoteca()-dm.getHipoteca()));
                subReg.add(""+v.calcularVariacionPorcentual(dm.getHipoteca(),lista.get(i+1).getHipoteca()));
            }
            subReg.add(""+(dm.getCompraventa()+dm.getRemate()+dm.getPermuta()+dm.getEmbargo()+dm.getHipoteca()));
            if (opcional) {
                subReg.add(""+(lista.get(i+1).getCompraventa()+lista.get(i+1).getRemate()+lista.get(i+1).getPermuta()
                        +lista.get(i+1).getEmbargo()+lista.get(i+1).getHipoteca()));
            }
            registros.add("[{},"+addCommaString(subReg)+"]");
            subReg.clear();
        }
        tabla="\"dats\":["+addCommaString(registros)+"]";
        String atTabs="\"atTabs\":{\"dats\":[{"+column+","+tabla+"}]}" ;
        //configuracion de la consulta
        String conf="\"conf\":{	\"atSel\": \"atMaps\"," +
                    "\"plantillas\": [1, 1, 1, 1, 1]}";
         String json="resp({\"ast\":{"+atMaps+","+atTabs+"},"+conf+"})";
        return json;
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
    
    /**
     * Método encargado de crear el json con la información geográfica para cualquier consulta de usuario, a partir
     * de los parametros que se le envian.
     * @param tc objeto de tipo tablaContenido asociado a una consulta determinada
     * @param lServ Lista de tipo Servicios con los servicios asociadas la consulta
     * @param capas Lista de tipo Capas con las capas asociadas a la consulta
     * @param iden json con el parametro de identificacion para la consulta, este parametro no se construye en el 
     * método ya que es único para cada consulta.
     * @return String en formato json con la información geográfica de una consulta 
     */
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
        ssgs = "\"ssgs\":{" + addCommaString(listServ) + "}";
        //(capas )
        List<String> lCsgs = new ArrayList<>();
        String csgs;
        for (Capas c : capas) {
            String tipoS;
            if (c.getTipo_serv().size()>1) {
                tipoS="\"t\":[" + addCommaString(c.getTipo_serv())+ "],";
            } else {
                tipoS="\"t\":" + c.getTipo_serv()+ ",";
            }
            tmp = "\"alGr\":\"" + c.getAliasgrupo() + "\",";
            tmp += "\"alSg\":\"" + c.getAliasservicio() + "\",";
            tmp += "\"conf\":{\"reMa\":" + c.getEscmax() + ","
                    + "\"fis\":\"" + c.getFiltro() + "\","
                    + "\"nom\":\"" + c.getNombre_capa() + "\","
                    + "\"opa\":" + c.getOpacidad() + ","
                    + tipoS
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
        csgs = "\"csgs\":{" +addCommaString(lCsgs)+ "}";
        atMaps = "\"atMaps\":{\"idAt\":\"atMaps\",\"dats\":{" + acsgs + "," + ssgs + "," + csgs+"}" +((iden.equals(""))?"":","+iden)+"}";
        return atMaps;
    }
    
    /**
     * Método encargado de crear el json con la información de la consulta de precios para el 
     * departamento de Boyacá
     * @param tc objeto de tipo tablaContenido asociado a una consulta determinada
     * @param serv Lista de tipo Servicios con los servicios asociadas la consulta
     * @param capas Lista de tipo Capas con las capas asociadas a la consulta
     * @param areas lista de tipo Areas con la información de los precios de Boyacá
     * @return String de en formato Json con la información de la consulta para precios Boyacá
     */
    public String crearJsonPreciosBoyaca(Tablacontenido tc, List<Servicios> serv, List<Capas> capas, List<Areas> areas){
        Varios v = new Varios();
        String iden="\"ext\":[-73.57703, 5.38009, -73.17961, 4.98548],\"orCsgs\":[\"PB\"],\n" +
                    "\"identificacion\":{\"t\": \"auto\",\"dats\": [{\n" +
                    "\"al\": \"PB\",\n" +
                    "\"ind\": 1 ,\n" +
                    "\"compCg\": \"id\",\n" +
                    "\"compTab\": \"Id\",\n" +
                    "\"tab\":{\n" +
                    "\"ind\": 0,\n" +
                    "\"colums\": [\"Departamento\",\"Municipio\",\"Área (ha)\",\"Rango de precio\"]}\n" +
                    "}]}";
        String atMaps=crearJsonInfGeoConsultas(tc, serv, capas,iden);
        List<String> listColumnas = new ArrayList();
        String column;
        listColumnas.add("[\"Id\", \"n\", \"20px\"]");
        listColumnas.add("[\"Código DANE\", \"t\", \"100px\"]");
        listColumnas.add("[\"Departamento\", \"t\", \"100px\"]");
        listColumnas.add("[\"Municipio\", \"t\", \"120px\"]");
        listColumnas.add("[\"Área (ha)\", \"n\", \"70px\"]");
        listColumnas.add("[\"Rango de precio\", \"t\", \"120px\"]");
        column = "\"colums\":[" + addCommaString(listColumnas) + "]";
        List<String> registros = new ArrayList();
        String tabla;
        int k = 1;
        for (Areas r : areas) {
            registros.add("[{},"+k+",\""+r.getCodigoDane()+"\","
                    + "\""+r.getDepartamento()+"\","
                    + "\""+r.getMunicipio()+"\","
                    + r.getArea().setScale(2,RoundingMode.HALF_UP)+","
                    + "\""+r.getTipo()+"\"]");
            k++;
        }
        tabla="\"dats\":["+addCommaString(registros)+"]";
        String atTabs="\"atTabs\":{\"dats\":[{"+column+","+tabla+"}]}" ;
        //creacion del grafico
        String graf1;
        List<String> datGraf = new ArrayList<>();
        datGraf.add("[\"1 a 5 millones\","+addCommaBigDecimal(v.sumarAreaXCategoria(areas, "1 a 5 millones","",null))+"]");
        datGraf.add("[\"5 a 10 millones\","+addCommaBigDecimal(v.sumarAreaXCategoria(areas, "5 a 10 millones","",null))+"]");
        datGraf.add("[\"10 a 20 millones\","+addCommaBigDecimal(v.sumarAreaXCategoria(areas, "10 a 20 millones","",null))+"]");
        datGraf.add("[\"20 a 30 millones\","+addCommaBigDecimal(v.sumarAreaXCategoria(areas, "20 a 30 millones","",null))+"]");
        datGraf.add("[\"30 a 40 millones\","+addCommaBigDecimal(v.sumarAreaXCategoria(areas, "30 a 40 millones","",null))+"]");
        datGraf.add("[\"40 a 50 millones\","+addCommaBigDecimal(v.sumarAreaXCategoria(areas, "40 a 50 millones","",null))+"]");
        datGraf.add("[\"50 a 60 millones\","+addCommaBigDecimal(v.sumarAreaXCategoria(areas, "50 a 60 millones","",null))+"]");
        graf1="{\"t\":\"t\","
                + "\"tit\":\"Porcentaje de hectáreas por rango de precios\","
                + "\"es\":{\"3d\":true,\"caLe\":true},"
                + "\"col\":[\"#ffebaf\",\"#f5d072\",\"#edb337\",\"#e69900\",\"#bf5600\",\"#992100\",\"#720000\"],"
                + "\"dats\":["+addCommaString(datGraf)+"]}";
        List<String> categorias = new ArrayList<>();
        categorias.add("1 a 5 millones");
        categorias.add("5 a 10 millones");
        categorias.add("10 a 20 millones");
        categorias.add("20 a 30 millones");
        categorias.add("30 a 40 millones");
        categorias.add("40 a 50 millones");
        categorias.add("50 a 60 millones");
        String graf2;
        datGraf.clear();
        datGraf.add("[\"TENZA\",["+addCommaBigDecimal(v.sumarAreaXCategoria(areas, "", "TENZA",categorias))+"]]");
        datGraf.add("[\"LA CAPILLA\",["+addCommaBigDecimal(v.sumarAreaXCategoria(areas, "", "LA CAPILLA",categorias))+"]]");
        datGraf.add("[\"GARAGOA\",["+addCommaBigDecimal(v.sumarAreaXCategoria(areas, "", "GARAGOA",categorias))+"]]");
        datGraf.add("[\"PACHAVITA\",["+addCommaBigDecimal(v.sumarAreaXCategoria(areas, "", "PACHAVITA",categorias))+"]]");
        datGraf.add("[\"CHINAVITA\",["+addCommaBigDecimal(v.sumarAreaXCategoria(areas, "", "CHINAVITA",categorias))+"]]");
        datGraf.add("[\"ÚMBITA\",["+addCommaBigDecimal(v.sumarAreaXCategoria(areas, "", "ÚMBITA",categorias))+"]]");
        datGraf.add("[\"TIBANÁ\",["+addCommaBigDecimal(v.sumarAreaXCategoria(areas, "", "TIBANÁ",categorias))+"]]");
        graf2="{\"t\":\"c\",\"tit\":\"Total de hectáreas municipal por rango de precios\","
                + "\"otrosDats\":{\"ejeY\":\"Área (ha)\", \"ejeX\": [\"1 a 5 millones\",\"5 a 10 millones\","
                + "\"10 a 20 millones\",\"20 a 30 millones\",\"30 a 40 millones\",\"40 a 50 millones\",\"50 a 60 millones\"]},"
                + "\"es\":{\"3d\":true, \"caLe\":true},"
                + "\"cols\":[\"#5b9bd5\",\"#ed7d31\",\"#a5a5a5\",\"#ffc000\",\"#4472c4\",\"#70ad47\",\"#255e91\"],"
                + "\"dats\":["+addCommaString(datGraf)+"]}";
        String atGraf="\"atGras\":{\"dats\":["+graf1+","+graf2+"]}";
        //configuracion de la consulta
        String conf="\"conf\":{	\"atSel\": \"atMaps\"," +
                    "\"plantillas\": [0, 1, 1, 3, 0]}";
         String json="resp({\"ast\":{"+atMaps+","+atTabs+","+atGraf+"},"+conf+"})";
        return json;
    }
    
    /**
     * Método que se encarga de crear el json para la consulta de usuario de restricciones 
     * @param tc tabla de contenido asociada a la consulta
     * @param serv servicios asociados a la consulta
     * @param capas capas asociadas a la consulta
     * @param areas información de las areas de restricciones 
     * @return String en formato json con toda la inforacion de la consulta de usuario
     */
    public String crearJsonRestricciones(Tablacontenido tc, List<Servicios> serv, List<Capas> capas, List<Areas> areas){
        Varios v = new Varios();
        String iden="\"ext\":[],\"orCsgs\":[\""+capas.get(0).getAlias()+"\", \"cgDepartamentos\"],\n" +
                    "\"identificacion\":{\"t\": \"auto\",\"dats\": [{\n" +
                    "\"al\": \"cgDepartamentos\",\n" +
                    //"\"ind\": 0 ,\n" +
                    "\"compCg\": \"codigodane\",\n" +
                    "\"compTab\": \"Código DANE\",\n" +
                    "\"tab\":{\n" +
                    "\"ind\": 0,\n" +
                    "\"colums\": [\"Departamento\", \"Condicionante\", \"Exclusión\", \"Sin Restricción\", \"Área Depto (ha)\"]},\n" +
                    "\"gra\": {\"t\": \"t\",\"indTab\": 0,\n" +
                    "\"colums\": [\"Condicionante\", \"Exclusión\", \"Sin Restricción\"],\n" +
                    "\"cols\": [\"#FFFFB9\", \"#FFB3B3\", \"#C4E6B2\"]}\n}]\n}";
        String atMaps=crearJsonInfGeoConsultas(tc, serv, capas,iden);
        //area trabajo tabla
        List<String> listColumnas = new ArrayList();
        String column;
        listColumnas.add("[\"Código DANE\", \"t\", \"100px\"]");
        listColumnas.add("[\"Departamento\", \"t\", \"150px\"]");
        listColumnas.add("[\"Condicionante\", \"p\", \"100px\"]");
        listColumnas.add("[\"Exclusión\", \"p\", \"100px\"]");
        listColumnas.add("[\"Sin Restricción\", \"p\", \"100px\"]");
        listColumnas.add("[\"Área Depto (ha)\", \"n\", \"110px\"]");
        column = "\"colums\":[" + addCommaString(listColumnas) + "]";
        List<String> registros = new ArrayList();
        String tabla;
        for (Areas r : areas) {
            registros.add("[{},\""+r.getCodigoDane()+"\",\""+r.getDepartamento()+
                    "\","+r.getCondicionante()+
                    ","+r.getExclusion()+
                    ","+r.getSinRestriccion()+
                    ","+r.getAreaDepto()+"]");
        }
        tabla="\"dats\":["+addCommaString(registros)+"]";
        String atTabs="\"atTabs\":{\"dats\":[{"+column+","+tabla+"}]}" ;
        //creacion de los graficos
        String graf1="{\"t\":\"t\","
             + "\"tit\":\"Porcentaje de hectáreas nacional por categoría\","
             + "\"es\":{\"caLe\":true,\"3d\":true},"
             + "\"cols\":[\"#FFFFB9\", \"#FFB3B3\", \"#C4E6B2\"],"
             + "\"dats\":["
             + "[\"Condicionante\", "+v.promedioAreas(areas, 1)+"],"
             + "[\"Exclusión\", "+v.promedioAreas(areas, 2)+"],"
             + "[\"Sin restricción\", "+v.promedioAreas(areas, 3)+"]]}";
        List<String> depto = new ArrayList<>();
        for (Areas r : areas) {
            depto.add("\""+r.getDepartamento()+"\"");
        }
        String ejeX="["+addCommaString(depto)+"]";
        String cond="["+addCommaBigDecimal(v.PorcentajeAreaxZonaRestricciones(areas, 1))+"]";
        String exc="["+addCommaBigDecimal(v.PorcentajeAreaxZonaRestricciones(areas, 2))+"]";
        String SinRes="["+addCommaBigDecimal(v.PorcentajeAreaxZonaRestricciones(areas, 3))+"]";
        String graf2="{\"t\":\"p\",\"tit\":\"Total de hectáreas departamental por categoría\","
                + "\"es\":{\"3d\":true},"
                + "\"cols\":[\"#FFFFB9\", \"#FFB3B3\", \"#C4E6B2\"],"
                + "\"otrosDats\":{\"ejeX\":"+ejeX+",\"ejeY\":\"Área (ha)\"},"
                + "\"es\":{\"caLe\":true,\"3d\":true},"
                + "\"dats\":[[\"Condicionante\","+cond+"],"
                + "[\"Exclusión\","+exc+"],"
                + "[\"Sin restricción\","+SinRes+"]]}";
        String atGraf="\"atGras\":{\"dats\":["+graf1+","+graf2+"]}";
        String conf="\"conf\":{	\"atSel\": \"atMaps\"," +
                    "\"plantillas\": [1, 1, 1, 3, 1]}";
        String json="resp({\"ast\":{"+atMaps+","+atTabs+","+atGraf+"},"+conf+"})";
        return json;
    }
    
    /**
     * Método que se encarga de crear el json para la consulta de usuario de exclusiones de la UPRA
     * @param tc tabla de contenido de la consulta asociada
     * @param serv lista de servicios asociados a la consulta
     * @param capas lista de capas asociadas a la consulta de usuario
     * @param areas lista de areas con exclusiones 
     * @param docs lista de documentos asociados a la consulta
     * @param info lista de con objetos de tipo InfoyDocs con la información adicional a la consulta
     * @return String en formato json con toda la información de la consulta de exclusiones.
     */
    public String crearJsonExclusiones(Tablacontenido tc, List<Servicios> serv,
            List<Capas> capas, List<Areas> areas,List<InfoyDocs> docs,List<InfoyDocs> info){
        Varios v = new Varios();
        String iden="\"ext\":[],\"orCsgs\":[\""+capas.get(0).getAlias()+"\", \"cgDepartamentos\"],\n" +
                    "\"identificacion\":{\"t\": \"auto\",\"dats\": [{\n" +
                    "\"al\": \"cgDepartamentos\",\n" +
                    //"\"ind\": 0 ,\n" +
                    "\"compCg\": \"codigodane\",\n" +
                    "\"compTab\": \"Código DANE\",\n" +
                    "\"tab\":{\n" +
                    "\"ind\": 0,\n" +
                    "\"colums\": [\"Departamento\", \"Territorio Incluido (ha)\", \"Incluido\", " +
                    "\"Territorio Restringido (ha)\", \"Restringido\",\"Territorio Excluido (ha)\"," +
                    "\"Excluido\",\"Área Depto (ha)\"]},\n" +
                    "\"gra\": {\"t\": \"t\",\"indTab\": 0,\n" +
                    "\"colums\": [\"Incluido\", \"Restringido\", \"Excluido\"],\n" +
                    "\"cols\": [\"#98E600\", \"#FFAA00\", \"#FFFFBE\"]}\n}]\n}";
        String atMaps=crearJsonInfGeoConsultas(tc, serv, capas,iden);
        //area trabajo tabla
        List<String> listColumnas = new ArrayList();
        String column;
        listColumnas.add("[\"Código DANE\", \"t\", \"100px\"]");
        listColumnas.add("[\"Departamento\", \"t\", \"150px\"]");
        listColumnas.add("[\"Territorio Incluido (ha)\", \"n\", \"150px\"]");
        listColumnas.add("[\"Incluido\", \"p\", \"100px\"]");
        listColumnas.add("[\"Territorio Restringido (ha)\", \"n\", \"170px\"]");
        listColumnas.add("[\"Restringido\", \"p\", \"100px\"]");
        listColumnas.add("[\"Territorio Excluido (ha)\", \"n\", \"150px\"]");
        listColumnas.add("[\"Excluido\", \"p\", \"100px\"]");
        listColumnas.add("[\"Área Depto (ha)\", \"n\", \"110px\"]");
        column = "\"colums\":[" + addCommaString(listColumnas) + "]";
        List<String> registros = new ArrayList();
        String tabla;
        for (Areas r : areas) {
            registros.add("[{},\""+r.getCodigoDane()+"\",\""+r.getDepartamento()+
                    "\","+r.getAreaIncl()+
                    ","+r.getIncluidas()+
                    ","+r.getAreaCond()+
                    ","+r.getCondicionante()+
                    ","+r.getAreaExcl()+
                    ","+r.getExclusion()+
                    ","+r.getAreaDepto()+"]");
        }
        tabla="\"dats\":["+addCommaString(registros)+"]";
        String atTabs="\"atTabs\":{\"dats\":[{"+column+","+tabla+"}]}" ;
        //creacion de los graficos
        String graf1="{\"t\":\"t\","
             + "\"tit\":\"Porcentaje de hectáreas nacional por categoría\","
             + "\"es\":{\"caLe\":true,\"3d\":true},"
             + "\"cols\":[\"#FFAA00\", \"#FFFFBE\", \"#98E600\"],"
             + "\"dats\":["
             + "[\"Incluido\", "+v.promedioAreas(areas, 4)+"],"
             + "[\"Restringido\", "+v.promedioAreas(areas, 1)+"],"
             + "[\"Excluido\", "+v.promedioAreas(areas, 2)+"]]}";
        List<String> depto = new ArrayList<>();
        for (Areas r : areas) {
            depto.add("\""+r.getDepartamento()+"\"");
        }
        String ejeX="["+addCommaString(depto)+"]";
        String incl="["+addCommaBigDecimal(v.totalSepararExclusiones(areas, 1))+"]";
        String exc="["+addCommaBigDecimal(v.totalSepararExclusiones(areas, 2))+"]";
        String restr="["+addCommaBigDecimal(v.totalSepararExclusiones(areas, 3))+"]";
        String graf2="{\"t\":\"p\",\"tit\":\"Total de hectáreas departamental por categoría\","
                + "\"es\":{\"3d\":true},"
                + "\"cols\":[\"#FFAA00\", \"#FFFFBE\", \"#98E600\"],"
                + "\"otrosDats\":{\"ejeX\":"+ejeX+",\"ejeY\":\"Área (ha)\"},"
                + "\"es\":{\"caLe\":true,\"3d\":true},"
                + "\"dats\":[[\"Incluido\","+incl+"],"
                + "[\"Restringido\","+restr+"],"
                + "[\"Excluido\","+exc+"]]}";
        String atGraf="\"atGras\":{\"dats\":["+graf1+","+graf2+"]}";
        //area de trabajo documentos
        String atDocs=jsonDocs(docs);
         //area de trabajo informacion
        String atInf=jsonInfo(info);
        //configuracion de la consulta
        String conf="\"conf\":{	\"atSel\": \"atMaps\"," +
                    "\"plantillas\": [1, 1, 1, 3, 1]}";
         String json="resp({\"ast\":{"+atMaps+","+atTabs+","+atGraf+","+atDocs+","+atInf+"},"+conf+"})";
        return json;
    }
    
    /**
     * Método que se encarga de crear el json para la consulta de usuario de indice de fraccionamiento de la UPRA
     * @param tc tabla de contenido de la consulta asociada
     * @param serv lista de servicios asociados a la consulta
     * @param capas lista de capas asociadas a la consulta de usuario
     * @param areas lista de areas con indice de fraccionamiento
     * @param docs lista de documentos asociados a la consulta
     * @param info lista de con objetos de tipo InfoyDocs con la información adicional a la consulta
     * @return String en formato json con toda la información de la consulta de fraccionamiento.
     */
    public String crearJsonIndiceFraccionamiento(Tablacontenido tc, List<Servicios> serv,
            List<Capas> capas, List<Areas> areas,List<InfoyDocs> docs,List<InfoyDocs> info){
        Varios v = new Varios();
        String iden="\"ext\":[],\"orCsgs\":[\""+capas.get(0).getAlias()+"\", \"cgDepartamentos\"],\n" +
                    "\"identificacion\":{\"t\": \"auto\",\"dats\": [{\n" +
                    "\"al\": \"cgDepartamentos\",\n" +
                    //"\"ind\": 0 ,\n" +
                    "\"compCg\": \"codigodane\",\n" +
                    "\"compTab\": \"Código DANE\",\n" +
                    "\"tab\":{\n" +
                    "\"ind\": 0,\n" +
                    "\"colums\": [\"Departamento\",\"Índice de Fraccionamiento\"]},\n" +
                    "}]}";
        String atMaps=crearJsonInfGeoConsultas(tc, serv, capas,iden);
        List<String> listColumnas = new ArrayList();
        String column;
        listColumnas.add("[\"Código DANE\", \"t\", \"100px\"]");
        listColumnas.add("[\"Departamento\", \"t\", \"120px\"]");
        listColumnas.add("[\"Índice de Fraccionamiento\", \"n\", \"165px\"]");
        column = "\"colums\":[" + addCommaString(listColumnas) + "]";
        List<String> registros = new ArrayList();
        String tabla;
        for (Areas r : areas) {
            registros.add("[{},\""+r.getCodigoDane()+"\","
                    + "\""+r.getDepartamento()+"\","
                    + "\""+r.getArea()+"\"]");
        }
        registros.add("[{}, \"\", \"<span style=\"float: center; font-weight: bold; background-color: #ff5500\">COLOMBIA</span>\", "+v.promedioAreas(areas, 5)+"]");
        tabla="\"dats\":["+addCommaString(registros)+"]";
        String atTabs="\"atTabs\":{\"dats\":[{"+column+","+tabla+"}]}" ;
        //creacion del grafico
        String graf1;
        List<String> datGraf = new ArrayList<>();
        for (Areas a : areas) {
            datGraf.add("[\""+a.getDepartamento()+"\","+a.getArea()+"]");
        }
        graf1="\"dats\":["+addCommaString(datGraf)+"]";
        String atGraf="\"atGras\":{\"dats\":[{\"t\":\"c\","
                + "\"tit\":\"\","
                + "\"otrosDats\":{\"ejeY\":\"Índice de Fraccionamiento\"},"
                + "\"es\":{\"3d\":true},"
                + "\"col\":[\"#4472c4\"],"
                + graf1+"}]}";
        //area de trabajo documentos
        String atDocs=jsonDocs(docs);
         //area de trabajo informacion
        String atInf=jsonInfo(info);
        //configuracion de la consulta
        String conf="\"conf\":{	\"atSel\": \"atMaps\"," +
                    "\"plantillas\": [1, 1, 1, 1, 1]}";
         String json="resp({\"ast\":{"+atMaps+","+atTabs+","+atGraf+","+atDocs+","+atInf+"},"+conf+"})";
        return json;
    }
    
    /**
     * Método que se encarga de crear el json para el area de trabajo 
     * de documentos en una consulta de usuario
     * @param docs lista de tipo InfoyDocs con las informacion de los documentos
     * asociados a la consulta
     * @return String en formato json con la informacion de los documentos de la consulta
     */
    public String jsonDocs(List<InfoyDocs> docs){
        List<String> ldocs = new ArrayList<>();
        for (InfoyDocs d : docs) {
            String doc="{\"desc\":\""+d.getDescripcion()+"\","
                    + "\"nom\":\""+d.getNombre()+"\","
                    + "\"tit\":\""+d.getTitulo()+"\"}";
            ldocs.add(doc);
        }
        return "\"atDocs\":{\"dats\":["+addCommaString(ldocs)+"]}";
    }
    
    /**
     * Método que se encarga de crear el json para el area de trabajo 
     * de información adicional en una consulta de usuario
     * @param info lista de tipo InfoyDocs con las informacion 
     * adicional a la consulta de usuario
     * @return String en formato json con la informacion adicional de la consulta
     */
    public String jsonInfo(List<InfoyDocs> info){
        List<String> linfo = new ArrayList<>();
        for (InfoyDocs inf : info) {
            String i="{\"tit\":\""+inf.getTitulo()+"\","
                    + "\"tex\":\""+inf.getDescripcion()+"\"}";
            linfo.add(i);
        }
        return "\"atInf\":{\"dats\":["+addCommaString(linfo)+"]}";
    }
    
    /**
     * Metodo encargado de hacer el json para la consulta de usuario de los distritos de riego
     * @param tc tabla de contenido de la consulta asociada
     * @param serv lista de servicios asociados a la consulta
     * @param capas lista de capas asociadas a la consulta de usuario
     * @param dats Lista de DistritosRiego con el consolidado de toda la información
     * @param peq Lista de DistritosRiego con el discriminado de los distritos pequeños
     * @param med Lista de DistritosRiego con el discriminado de los distritos medianos
     * @param gran Lista de DistritosRiego con el discriminado de los distritos grandes
     * @param dpto Lista de los departamentos de Colombia
     * @return String en formato json con toda la información de la consulta
     */
    public String crearJsonDistritosRiegos(Tablacontenido tc, List<Servicios> serv,
            List<Capas> capas, List<DistritosRiego> dats, List<DistritosRiego> peq, 
            List<DistritosRiego> med, List<DistritosRiego> gran, List<String> dpto){
        Varios v = new Varios();
        List<String> temCap = new ArrayList<>();
        for(Capas c: capas){
            temCap.add("\""+c.getAlias()+"\"");
        }
        String iden="\"ext\":[],\"orCsgs\":["+addCommaString(temCap)+"],\n" +
                    "\"identificacion\":{\"t\": \"auto\",\"dats\": [{\n" +
                    "\"al\": [\"distGranPolCg\",\"distMedianaPolCg\",\"distPequenaPolCg\"],\n" +
                    "\"compCg\": \"id_incoder\",\n" +
                    "\"compTab\": \"ID INCODER\",\n" +
                    "\"tab\":{\n" +
                    "\"ind\": 0,\n" +
                    "\"colums\": [\"ID INCODER\",\"Nombre Distrito\",\"Departamento\",\"Municipio\","
                + "\"Escala\",\"Usuarios\",\"Cultivos\",\"Tipo\",\"Etapa\",\"Área Neta\",\"Fuente Hídrica\","
                + "\"¿Funciona?\",\"Concesión\"]}\n" +
                    "}]}";
        String atMaps=crearJsonInfGeoConsultas(tc, serv, capas,iden);
        //creacion de la columnas
        List<String> listColumnas = new ArrayList();
        String column;
        listColumnas.add("[\"ID UPRA\", \"t\", \"10%\"]");
        listColumnas.add("[\"ID INCODER\", \"n\", \"10%\"]");
        listColumnas.add("[\"Nombre Distrito\", \"t\", \"10%\"]");
        listColumnas.add("[\"Código DANE Depto\", \"t\", \"10%\"]");
        listColumnas.add("[\"Departamento\", \"t\", \"10%\"]");
        listColumnas.add("[\"Código DANE Mpio\", \"t\", \"10%\"]");
        listColumnas.add("[\"Municipio\", \"t\", \"10%\"]");
        listColumnas.add("[\"Vereda\", \"t\", \"10%\"]");
        listColumnas.add("[\"Área Neta\", \"n\", \"10%\"]");
        listColumnas.add("[\"Área Bruta\", \"n\", \"10%\"]");
        listColumnas.add("[\"Escala\", \"n\", \"10%\"]");
        listColumnas.add("[\"Coordenada 1\", \"t\", \"10%\"]");
        listColumnas.add("[\"Coordenada 2\", \"t\", \"10%\"]");
        listColumnas.add("[\"Coordenada 3\", \"t\", \"10%\"]");
        listColumnas.add("[\"Coordenada 4\", \"t\", \"10%\"]");
        listColumnas.add("[\"Altitud\", \"n\", \"10%\"]");
        listColumnas.add("[\"Tipo\", \"t\", \"10%\"]");
        listColumnas.add("[\"Etapa\", \"t\", \"10%\"]");
        listColumnas.add("[\"¿Funciona?\", \"t\", \"10%\"]");
        listColumnas.add("[\"Usuarios\", \"n\", \"10%\"]");
        listColumnas.add("[\"Inversión\", \"n\", \"10%\"]");
        listColumnas.add("[\"Administrador\", \"t\", \"10%\"]");
        listColumnas.add("[\"Asociación de Usuarios\", \"t\", \"10%\"]");
        listColumnas.add("[\"Resolución de la Asociación\", \"t\", \"10%\"]");
        listColumnas.add("[\"Representante de la Asociación\", \"t\", \"10%\"]");
        listColumnas.add("[\"Teléfono de la Asociación\", \"t\", \"10%\"]");
        listColumnas.add("[\"E-mail de la Asociación\", \"t\", \"10%\"]");
        listColumnas.add("[\"Dirección de la Asociación\", \"t\", \"10%\"]");
        listColumnas.add("[\"Cultivos\", \"t\", \"10%\"]");
        listColumnas.add("[\"CAR\", \"t\", \"10%\"]");
        listColumnas.add("[\"Fuente Hídrica\", \"t\", \"10%\"]");
        listColumnas.add("[\"Captación\", \"t\", \"10%\"]");
        listColumnas.add("[\"Concesión\", \"t\", \"10%\"]");
        listColumnas.add("[\"SZH\", \"t\", \"10%\"]");
        listColumnas.add("[\"ZH\", \"t\", \"10%\"]");
        listColumnas.add("[\"¿Georeferenciado?\", \"t\", \"10%\"]");
        listColumnas.add("[\"ID Polígono\", \"t\", \"10%\"]");
        listColumnas.add("[\"Observaciones\", \"t\", \"10%\"]");
        column = "\"colums\":[" + addCommaString(listColumnas) + "]";
        List<String> registros = new ArrayList();
        String tabla;
        //llenar la tabla con los datos
        for (DistritosRiego dr : dats) {
            registros.add("[{},\""+dr.getIdUpra()+"\","
                    + dr.getIdIncoder()+","
                    + "\""+dr.getNomDistrito()+"\","
                    + "\""+dr.getCodDaneDepto()+"\","
                    + "\""+dr.getDepto()+"\","
                    + "\""+dr.getCodDaneMpio()+"\","
                    + "\""+dr.getMpio()+"\","
                    + "\""+dr.getVereda()+"\","
                    + "\""+dr.getAreaNeta()+"\","
                    + "\""+dr.getAreaBruta()+"\","
                    + "\""+dr.getEscala()+"\","
                    + "\""+dr.getCoor1()+"\","
                    + "\""+dr.getCoor2()+"\","
                    + "\""+dr.getCoor3()+"\","
                    + "\""+dr.getCoor4()+"\","
                    + dr.getAltitud()+","
                    + "\""+dr.getTipo()+"\","
                    + "\""+dr.getEtapa()+"\","
                    + "\""+dr.getFunciona()+"\","
                    + dr.getUsuarios()+","
                    + dr.getInversion()+","
                    + "\""+dr.getAdmin()+"\","
                    + "\""+dr.getAsoUsuarios()+"\","
                    + "\""+dr.getResolucion()+"\","
                    + "\""+dr.getRepresentante()+"\","
                    + "\""+dr.getTel()+"\","
                    + "\""+dr.getEmail()+"\","
                    + "\""+dr.getDir()+"\","
                    + "\""+dr.getCultivos()+"\","
                    + "\""+dr.getCar()+"\","
                    + "\""+dr.getFuenteHid()+"\","
                    + "\""+dr.getCaptacion()+"\","
                    + "\""+dr.getConcesion()+"\","
                    + "\""+dr.getSzh()+"\","
                    + "\""+dr.getZh()+"\","
                    + "\""+dr.getGeoref()+"\","
                    + "\""+dr.getIdPol()+"\","
                    + "\""+dr.getObser()+"\"]");
        }
        tabla="\"dats\":["+addCommaString(registros)+"]";
        String atTabs="\"atTabs\":{\"dats\":[{"+column+","+tabla+"}]}" ;
        //creacion del grafico
        List<Integer> datGrafPeq = new ArrayList<>();
        List<Integer> datGrafMed = new ArrayList<>();
        List<Integer> datGrafGran = new ArrayList<>();
        //compara la lista de departamentos contra las de distritos
        //si no encuentra un departamento agrega cero, del lo contrario 
        //la cantidad de distritos en ese departamento
        for (int i = 0; i < dpto.size(); i++) {
            boolean igualPeq=false;
            boolean igualMed=false;
            boolean igualGrand=false;
            System.out.println("Departamento: "+dpto.get(i));
            for (int j = 0; j < peq.size(); j++) {
                System.out.println("Comparando Peq: "+peq.get(j).getDepto());
                if (dpto.get(i).equals(peq.get(j).getDepto())) {
                    datGrafPeq.add(peq.get(j).getCantDist());
                    igualPeq=true;
                }
            }
            if(!igualPeq)
                datGrafPeq.add(0);
            for (int j = 0; j < med.size(); j++) {
                System.out.println("Comparando Med: "+med.get(j).getDepto());
                if (dpto.get(i).equals(med.get(j).getDepto())) {
                    datGrafMed.add(med.get(j).getCantDist());
                    igualMed=true;
                }
            }
            if(!igualMed)
                datGrafMed.add(0);
            for (int j = 0; j < gran.size(); j++) {
                System.out.println("Comparando gran: "+gran.get(j).getDepto());
                if (dpto.get(i).equals(gran.get(j).getDepto())) {
                    datGrafGran.add(gran.get(j).getCantDist());
                    igualGrand=true;
                }
            }
            if(!igualGrand)
                datGrafGran.add(0);
        }
        //agregar comillas a la lista de departamentos
        for (int i = 0; i < dpto.size(); i++) {
            dpto.set(i, "\""+dpto.get(i)+"\"");
        }
        String atGraf="\"atGras\":{\"dats\":[{\"t\":\"p\","
                + "\"tit\":\"Número de distritos de riego y drenaje por departamento y escala\","
                + "\"es\":{\"3d\":true, \"caLe\":true},"
                + "\"col\":[\"#ab4d6b\",\"#d593a7\",\"#40ff40\"],"
                + "\"otrosDats\":{\"ejeX\":["+addCommaString(dpto)+"],\"ejeY\":\"Número de distritos\"},"
                + "\"dats\":[ [\"Gran escala\","+addCommaInteger(datGrafGran)+"],"
                + "[\"Mediana escala\","+addCommaInteger(datGrafMed)+"],"
                + "[\"Pequeña escala\","+addCommaInteger(datGrafPeq)+"] ]"
                + "}]}";
        //configuracion de la consulta
        String conf="\"conf\":{	\"atSel\": \"atMaps\"," +
                    "\"plantillas\": [0, 1, 1, 1, 0]}";
         String json="resp({\"ast\":{"+atMaps+","+atTabs+","+atGraf+"},"+conf+"})";
        return json;
    }
     
    /**
     * Método que se encarga de crear el json para las consutas que solo disponen de
     * la información geográfica, solo mapa
     * @param tc tabla de contenido de la consulta asociada
     * @param serv lista de servicios asociados a la consulta
     * @param capas lista de capas asociadas a la consulta de usuario
     * @return String del json con la información de la consulta con solo mapa
     */
     public String crearJsonConsultaGenericaInfoGeo(Tablacontenido tc, List<Servicios> serv,
            List<Capas> capas){
        Varios v = new Varios();
        String iden="\"orCsgs\":[\""+capas.get(0).getAlias()+"\"]";
        String atMaps=crearJsonInfGeoConsultas(tc, serv, capas,iden);
        //configuracion de la consulta
        String conf="\"atSel\": \"atMaps\"";
         String json="resp({\"ast\":{"+atMaps+"},"+conf+"})";
        return json;
     }
     
     /**
      * Método encargado de crear el json para la consulta del indicador 
      * de indice de concentración relativa de la propiedad
      * @param tc tabla de contenido de la consulta asociada
      * @param serv lista de servicios asociados a la consulta
      * @param capas lista de capas asociadas a la consulta de usuario
      * @param areas lista de tipo Areas con la información del indicador de concentración
      * @param docs lista de tipo InfoyDocs con los documentos asociados a la consulta
      * @param info lista de tipo InfoyDocs con la información adicional asociada a la consulta
      * @return String en json con toda la información de la consulta de concentración relativa de la propiedad
      */
     public String crearJsonConcentracionRelativa(Tablacontenido tc, List<Servicios> serv,
            List<Capas> capas, List<Areas> areas,List<InfoyDocs> docs,List<InfoyDocs> info){
        Varios v = new Varios();
        String iden="\"ext\":[],\"orCsgs\":[\""+capas.get(0).getAlias()+"\", \"cgDepartamentos\"],\n" +
                    "\"identificacion\":{\"t\": \"auto\",\"dats\": [{\n" +
                    "\"al\": \"cgDepartamentos\",\n" +
                    //"\"ind\": 0,\n" +
                    "\"compCg\": \"codigodane\",\n" +
                    "\"compTab\": \"Código DANE\",\n" +
                    "\"tab\":{\n" +
                    "\"ind\": 0,\n" +
                    "\"colums\": [\"Departamento\",\"Indicador de Concentración\"]}\n" +
                    "}]}";
        String atMaps=crearJsonInfGeoConsultas(tc, serv, capas,iden);
        List<String> listColumnas = new ArrayList();
        String column;
        listColumnas.add("[\"Código DANE\", \"t\", \"100px\"]");
        listColumnas.add("[\"Departamento\", \"t\", \"120px\"]");
        listColumnas.add("[\"Indicador de Concentración\", \"n\", \"175px\"]");
        column = "\"colums\":[" + addCommaString(listColumnas) + "]";
        List<String> registros = new ArrayList();
        String tabla;
        for (Areas r : areas) {
            registros.add("[{},\""+r.getCodigoDane()+"\","
                    + "\""+r.getDepartamento()+"\","
                    + "\""+r.getArea()+"\"]");
        }
        registros.add("[{}, \"\", \"<span style=\"float: center; font-weight: bold; background-color: #009c4b\">COLOMBIA</span>\", "+v.promedioAreas(areas, 5)+"]");
        tabla="\"dats\":["+addCommaString(registros)+"]";
        String atTabs="\"atTabs\":{\"dats\":[{"+column+","+tabla+"}]}" ;
        //creacion del grafico
        String graf1;
        List<String> datGraf = new ArrayList<>();
        for (Areas a : areas) {
            datGraf.add("[\""+a.getDepartamento()+"\","+a.getArea()+"]");
        }
        graf1="\"dats\":["+addCommaString(datGraf)+"]";
        String atGraf="\"atGras\":{\"dats\":[{\"t\":\"c\","
                + "\"tit\":\"\","
                + "\"otrosDats\":{\"ejeY\":\"Indicador de Concentración\"},"
                + "\"es\":{\"3d\":true},"
                + "\"col\":[\"#4472c4\"],"
                + graf1+"}]}";
        //area de trabajo documentos
        String atDocs=jsonDocs(docs);
         //area de trabajo informacion
        String atInf=jsonInfo(info);
        //configuracion de la consulta
        String conf="\"conf\":{	\"atSel\": \"atMaps\"," +
                    "\"plantillas\": [1, 1, 1, 1, 1]}";
         String json="resp({\"ast\":{"+atMaps+","+atTabs+","+atGraf+","+atDocs+","+atInf+"},"+conf+"})";
        return json;
     }
     
     /**
      * Método encargado de crear el json para la consulta del indicador 
      * de superficie sin restricción legal
      * @param tc tabla de contenido de la consulta asociada
      * @param serv lista de servicios asociados a la consulta
      * @param capas lista de capas asociadas a la consulta de usuario
      * @param areas lista de tipo Areas con la información del indicador 
      * @param docs lista de tipo InfoyDocs con los documentos asociados a la consulta
      * @param info lista de tipo InfoyDocs con la información adicional asociada a la consulta
      * @return String en json con toda la información de la consulta de superficie sin restricción legal
      */
     public String crearJsonSuperficieSinRest(Tablacontenido tc, List<Servicios> serv,
            List<Capas> capas, List<Areas> areas,List<InfoyDocs> docs,List<InfoyDocs> info){
        Varios v = new Varios();
        String iden="\"ext\":[],\"orCsgs\":[\""+capas.get(0).getAlias()+"\", \"cgDepartamentos\"],\n" +
                    "\"identificacion\":{\"t\": \"auto\",\"dats\": [{\n" +
                    "\"al\": \"cgDepartamentos\",\n" +
                    //"\"ind\": 0,\n" +
                    "\"compCg\": \"codigodane\",\n" +
                    "\"compTab\": \"Código DANE\",\n" +
                    "\"tab\":{\n" +
                    "\"ind\": 0,\n" +
                    "\"colums\": [\"Departamento\",\"% del suelo deptal sin restricción legal\",\"Categoría\"]}\n" +
                    "}]}";
        String atMaps=crearJsonInfGeoConsultas(tc, serv, capas,iden);
        List<String> listColumnas = new ArrayList();
        String column;
        listColumnas.add("[\"Código DANE\", \"t\", \"100px\"]");
        listColumnas.add("[\"Departamento\", \"t\", \"120px\"]");
        listColumnas.add("[\"Indicador de Concentración\", \"n\", \"175px\"]");
        listColumnas.add("[\"Categoría\", \"t\", \"80px\"]");
        column = "\"colums\":[" + addCommaString(listColumnas) + "]";
        List<String> registros = new ArrayList();
        String tabla;
        for (Areas r : areas) {
            registros.add("[{},\""+r.getCodigoDane()+"\","
                    + "\""+r.getDepartamento()+"\","
                    + r.getArea()+","
                    + "\""+r.getTipo()+"\"]");
        }
        registros.add("[{}, \"\", \"<span style=\"float: center; font-weight: bold; background-color: #C6E0B3\">COLOMBIA</span>\", "+v.promedioAreas(areas, 5)+", \"Bajo\"]");
        tabla="\"dats\":["+addCommaString(registros)+"]";
        String atTabs="\"atTabs\":{\"dats\":[{"+column+","+tabla+"}]}" ;
        //creacion del grafico
        String graf1;
        List<String> datGraf = new ArrayList<>();
        datGraf.add("[\"Alto\","+v.promedioCategoriaZonas(areas, "Alto")+"]");
        datGraf.add("[\"Medio\","+v.promedioCategoriaZonas(areas, "Medio")+"]");
        datGraf.add("[\"Bajo\","+v.promedioCategoriaZonas(areas, "Bajo")+"]");
        datGraf.add("[\"Muy Bajo\","+v.promedioCategoriaZonas(areas, "Muy Bajo")+"]");
        graf1="{\"t\":\"t\","
                + "\"tit\":\"Porcentaje del suelo nacional sin restricción legal\","
                + "\"es\":{\"3d\":true},"
                + "\"col\":[\"#ED7D31\",\"#FFFF00\",\"#C6E0B3\",\"#70AD46\"],"
                + "\"dats\":["+addCommaString(datGraf)+"]}";
        String graf2;
        datGraf.clear();
        for (Areas r : areas) {
            datGraf.add("[\""+r.getDepartamento()+"\","+r.getArea()+"]");
        }
        graf2="{\"t\":\"c\",\"tit\":\"Porcentaje del suelo departamental sin restricción legal\","
                + "\"otrosDats\":{\"ejeY\":\"Porcentaje del suelo departamental sin restricción legal\"},"
                + "\"es\":{\"3d\":true},"
                + "\"cols\":[\"#4472c4\"],"
                + "\"dats\":["+addCommaString(datGraf)+"]}";
        String atGraf="\"atGras\":{\"dats\":["+graf1+","+graf2+"]}";
        //area de trabajo documentos
        String atDocs=jsonDocs(docs);
         //area de trabajo informacion
        String atInf=jsonInfo(info);
        //configuracion de la consulta
        String conf="\"conf\":{	\"atSel\": \"atMaps\"," +
                    "\"plantillas\": [1, 1, 1, 3, 1]}";
         String json="resp({\"ast\":{"+atMaps+","+atTabs+","+atGraf+","+atDocs+","+atInf+"},"+conf+"})";
        return json;
     }
     
     /**
      * Método encargado de crear el json para la consulta del indicador 
      * de superficie con Exclusión legal
      * @param tc tabla de contenido de la consulta asociada
      * @param serv lista de servicios asociados a la consulta
      * @param capas lista de capas asociadas a la consulta de usuario
      * @param areas lista de tipo Areas con la información del indicador 
      * @param docs lista de tipo InfoyDocs con los documentos asociados a la consulta
      * @param info lista de tipo InfoyDocs con la información adicional asociada a la consulta
      * @return String en json con toda la información de la consulta de superficie con exclusión legal
      */
     public String crearJsonSuperficieConExcLeg(Tablacontenido tc, List<Servicios> serv,
            List<Capas> capas, List<Areas> areas,List<InfoyDocs> docs,List<InfoyDocs> info){
        Varios v = new Varios();
        String iden="\"ext\":[],\"orCsgs\":[\""+capas.get(0).getAlias()+"\", \"cgDepartamentos\"],\n" +
                    "\"identificacion\":{\"t\": \"auto\",\"dats\": [{\n" +
                    "\"al\": \"cgDepartamentos\",\n" +
                    //"\"ind\": 0,\n" +
                    "\"compCg\": \"codigodane\",\n" +
                    "\"compTab\": \"Código DANE\",\n" +
                    "\"tab\":{\n" +
                    "\"ind\": 0,\n" +
                    "\"colums\": [\"Departamento\",\"% del suelo deptal con exclusión legal\",\"Categoría\"]}\n" +
                    "}]}";
        String atMaps=crearJsonInfGeoConsultas(tc, serv, capas,iden);
        List<String> listColumnas = new ArrayList();
        String column;
        listColumnas.add("[\"Código DANE\", \"t\", \"100px\"]");
        listColumnas.add("[\"Departamento\", \"t\", \"120px\"]");
        listColumnas.add("[\"% del suelo deptal con exclusión legal\", \"n\", \"240px\"]");
        listColumnas.add("[\"Categoría\", \"t\", \"80px\"]");
        column = "\"colums\":[" + addCommaString(listColumnas) + "]";
        List<String> registros = new ArrayList();
        String tabla;
        for (Areas r : areas) {
            registros.add("[{},\""+r.getCodigoDane()+"\","
                    + "\""+r.getDepartamento()+"\","
                    + r.getArea()+","
                    + "\""+r.getTipo()+"\"]");
        }
        tabla="\"dats\":["+addCommaString(registros)+"]";
        String atTabs="\"atTabs\":{\"dats\":[{"+column+","+tabla+"}]}" ;
        //creacion del grafico
        String graf1;
        List<String> datGraf = new ArrayList<>();
        datGraf.add("[\"Bajo\","+v.promedioCategoriaZonas(areas, "Bajo")+"]");
        datGraf.add("[\"Muy Bajo\","+v.promedioCategoriaZonas(areas, "Muy Bajo")+"]");
        graf1="{\"t\":\"t\","
                + "\"tit\":\"Porcentaje del suelo nacional con exclusión legal\","
                + "\"es\":{\"3d\":true},"
                + "\"col\":[\"#C6E0B3\",\"#70AD46\"],"
                + "\"dats\":["+addCommaString(datGraf)+"]}";
        String graf2;
        datGraf.clear();
        for (Areas r : areas) {
            datGraf.add("[\""+r.getDepartamento()+"\","+r.getArea()+"]");
        }
        graf2="{\"t\":\"c\",\"tit\":\"Porcentaje del suelo departamental con exclusión legal\","
                + "\"otrosDats\":{\"ejeY\":\"Porcentaje del suelo departamental con exclusión legal\"},"
                + "\"es\":{\"3d\":true},"
                + "\"cols\":[\"#4472c4\"],"
                + "\"dats\":["+addCommaString(datGraf)+"]}";
        String atGraf="\"atGras\":{\"dats\":["+graf1+","+graf2+"]}";
        //area de trabajo documentos
        String atDocs=jsonDocs(docs);
         //area de trabajo informacion
        String atInf=jsonInfo(info);
        //configuracion de la consulta
        String conf="\"conf\":{	\"atSel\": \"atMaps\"," +
                    "\"plantillas\": [1, 1, 1, 3, 1]}";
         String json="resp({\"ast\":{"+atMaps+","+atTabs+","+atGraf+","+atDocs+","+atInf+"},"+conf+"})";
        return json;
     }
     
     
     /**
      * Método encargado de crear el json para la consulta del indicador 
      * de superficie con uso condicionado
      * @param tc tabla de contenido de la consulta asociada
      * @param serv lista de servicios asociados a la consulta
      * @param capas lista de capas asociadas a la consulta de usuario
      * @param areas lista de tipo Areas con la información del indicador 
      * @param docs lista de tipo InfoyDocs con los documentos asociados a la consulta
      * @param info lista de tipo InfoyDocs con la información adicional asociada a la consulta
      * @return String en json con toda la información de la consulta de superficie 
      * con uso condicionado
      */
     public String crearJsonSuperficieUsoCond(Tablacontenido tc, List<Servicios> serv,
            List<Capas> capas, List<Areas> areas,List<InfoyDocs> docs,List<InfoyDocs> info){
        Varios v = new Varios();
        String iden="\"ext\":[],\"orCsgs\":[\""+capas.get(0).getAlias()+"\", \"cgDepartamentos\"],\n" +
                    "\"identificacion\":{\"t\": \"auto\",\"dats\": [{\n" +
                    "\"al\": \"cgDepartamentos\",\n" +
                    //"\"ind\": 0,\n" +
                    "\"compCg\": \"codigodane\",\n" +
                    "\"compTab\": \"Código DANE\",\n" +
                    "\"tab\":{\n" +
                    "\"ind\": 0,\n" +
                    "\"colums\": [\"Departamento\",\"% del suelo deptal con uso condicionado\",\"Categoría\"]}\n" +
                    "}]}";
        String atMaps=crearJsonInfGeoConsultas(tc, serv, capas,iden);
        List<String> listColumnas = new ArrayList();
        String column;
        listColumnas.add("[\"Código DANE\", \"t\", \"100px\"]");
        listColumnas.add("[\"Departamento\", \"t\", \"120px\"]");
        listColumnas.add("[\"% del suelo deptal con uso condicionado\", \"n\", \"250px\"]");
        listColumnas.add("[\"Categoría\", \"t\", \"80px\"]");
        column = "\"colums\":[" + addCommaString(listColumnas) + "]";
        List<String> registros = new ArrayList();
        String tabla;
        for (Areas r : areas) {
            registros.add("[{},\""+r.getCodigoDane()+"\","
                    + "\""+r.getDepartamento()+"\","
                    + r.getArea()+","
                    + "\""+r.getTipo()+"\"]");
        }
        registros.add("[{}, \"\", \"<span style=\"float: center; font-weight: bold; background-color: #ED7D31\">COLOMBIA</span>\", "+v.promedioAreas(areas, 5)+", \"Alto\"]");
        tabla="\"dats\":["+addCommaString(registros)+"]";
        String atTabs="\"atTabs\":{\"dats\":[{"+column+","+tabla+"}]}" ;
        //creacion del grafico
        String graf1;
        List<String> datGraf = new ArrayList<>();
        datGraf.add("[\"Muy Alto\","+v.promedioCategoriaZonas(areas, "Muy Alto")+"]");
        datGraf.add("[\"Alto\","+v.promedioCategoriaZonas(areas, "Alto")+"]");
        datGraf.add("[\"Medio\","+v.promedioCategoriaZonas(areas, "Medio")+"]");
        datGraf.add("[\"Bajo\","+v.promedioCategoriaZonas(areas, "Bajo")+"]");
        graf1="{\"t\":\"t\","
                + "\"tit\":\"Porcentaje del suelo nacional con uso condicionado\","
                + "\"es\":{\"3d\":true},"
                + "\"col\":[\"#FF0000\",\"#ED7D31\",\"#FFFF00\",\"#C6E0B4\"],"
                + "\"dats\":["+addCommaString(datGraf)+"]}";
        String graf2;
        datGraf.clear();
        for (Areas r : areas) {
            datGraf.add("[\""+r.getDepartamento()+"\","+r.getArea()+"]");
        }
        graf2="{\"t\":\"c\",\"tit\":\"Porcentaje del suelo departamental con uso condicionado\","
                + "\"otrosDats\":{\"ejeY\":\"Porcentaje del suelo departamental con uso condicionado\"},"
                + "\"es\":{\"3d\":true},"
                + "\"cols\":[\"#4472c4\"],"
                + "\"dats\":["+addCommaString(datGraf)+"]}";
        String atGraf="\"atGras\":{\"dats\":["+graf1+","+graf2+"]}";
        //area de trabajo documentos
        String atDocs=jsonDocs(docs);
         //area de trabajo informacion
        String atInf=jsonInfo(info);
        //configuracion de la consulta
        String conf="\"conf\":{	\"atSel\": \"atMaps\"," +
                    "\"plantillas\": [1, 1, 1, 3, 1]}";
         String json="resp({\"ast\":{"+atMaps+","+atTabs+","+atGraf+","+atDocs+","+atInf+"},"+conf+"})";
        return json;
     }
     
     /**
      * Método encargado de crear el json para la consulta del indicador 
      * de superficie con conflicto de uso por sobreutlización
      * @param tc tabla de contenido de la consulta asociada
      * @param serv lista de servicios asociados a la consulta
      * @param capas lista de capas asociadas a la consulta de usuario
      * @param areas lista de tipo Areas con la información del indicador 
      * @param docs lista de tipo InfoyDocs con los documentos asociados a la consulta
      * @param info lista de tipo InfoyDocs con la información adicional asociada a la consulta
      * @return String en json con toda la información de la consulta de superficie
      * con conflicto de uso de uso por sobreutilización
      */
     public String crearJsonSuperficieSobreUtilizacion(Tablacontenido tc, List<Servicios> serv,
            List<Capas> capas, List<Areas> areas,List<InfoyDocs> docs,List<InfoyDocs> info){
        Varios v = new Varios();
        String iden="\"ext\":[],\"orCsgs\":[\""+capas.get(0).getAlias()+"\", \"cgDepartamentos\"],\n" +
                    "\"identificacion\":{\"t\": \"auto\",\"dats\": [{\n" +
                    "\"al\": \"cgDepartamentos\",\n" +
                    //"\"ind\": 0,\n" +
                    "\"compCg\": \"codigodane\",\n" +
                    "\"compTab\": \"Código DANE\",\n" +
                    "\"tab\":{\n" +
                    "\"ind\": 0,\n" +
                    "\"colums\": [\"Departamento\",\"% del suelo deptal con conflicto por sobreutilización\",\"Categoría\"]}\n" +
                    "}]}";
        String atMaps=crearJsonInfGeoConsultas(tc, serv, capas,iden);
        List<String> listColumnas = new ArrayList();
        String column;
        listColumnas.add("[\"Código DANE\", \"t\", \"100px\"]");
        listColumnas.add("[\"Departamento\", \"t\", \"120px\"]");
        listColumnas.add("[\"% del suelo deptal con conflicto por sobreutilización\", \"n\", \"315px\"]");
        listColumnas.add("[\"Categoría\", \"t\", \"80px\"]");
        column = "\"colums\":[" + addCommaString(listColumnas) + "]";
        List<String> registros = new ArrayList();
        String tabla;
        for (Areas r : areas) {
            registros.add("[{},\""+r.getCodigoDane()+"\","
                    + "\""+r.getDepartamento()+"\","
                    + r.getArea()+","
                    + "\""+r.getTipo()+"\"]");
        }
        registros.add("[{}, \"\", \"<span style=\"float: center; font-weight: bold; background-color: #70AD46\">COLOMBIA</span>\", "+v.promedioAreas(areas, 5)+", \"Muy Bajo\"]");
        tabla="\"dats\":["+addCommaString(registros)+"]";
        String atTabs="\"atTabs\":{\"dats\":[{"+column+","+tabla+"}]}" ;
        //creacion del grafico
        String graf1;
        List<String> datGraf = new ArrayList<>();
        datGraf.add("[\"Medio\","+v.promedioCategoriaZonas(areas, "Medio")+"]");
        datGraf.add("[\"Bajo\","+v.promedioCategoriaZonas(areas, "Bajo")+"]");
        datGraf.add("[\"Muy Bajo\","+v.promedioCategoriaZonas(areas, "Muy Bajo")+"]");
        graf1="{\"t\":\"t\","
                + "\"tit\":\"Porcentaje del suelo nacional con conflicto de uso por sobreutilización\","
                + "\"es\":{\"3d\":true},"
                + "\"col\":[\"#FF0000\",\"#C6E0B3\",\"#70AD46\"],"
                + "\"dats\":["+addCommaString(datGraf)+"]}";
        String graf2;
        datGraf.clear();
        for (Areas r : areas) {
            datGraf.add("[\""+r.getDepartamento()+"\","+r.getArea()+"]");
        }
        graf2="{\"t\":\"c\",\"tit\":\"Porcentaje del suelo departamental con conflicto de uso por sobreutilización\","
                + "\"otrosDats\":{\"ejeY\":\"% del suelo departamental con conflicto de uso por sobreutilización\"},"
                + "\"es\":{\"3d\":true},"
                + "\"cols\":[\"#4472c4\"],"
                + "\"dats\":["+addCommaString(datGraf)+"]}";
        String atGraf="\"atGras\":{\"dats\":["+graf1+","+graf2+"]}";
        //area de trabajo documentos
        String atDocs=jsonDocs(docs);
         //area de trabajo informacion
        String atInf=jsonInfo(info);
        //configuracion de la consulta
        String conf="\"conf\":{	\"atSel\": \"atMaps\"," +
                    "\"plantillas\": [1, 1, 1, 3, 1]}";
         String json="resp({\"ast\":{"+atMaps+","+atTabs+","+atGraf+","+atDocs+","+atInf+"},"+conf+"})";
        return json;
     }
     
     /**
      * Método encargado de crear el json para la consulta del indicador 
      * de superficie con conflicto de uso por subutilización
      * @param tc tabla de contenido de la consulta asociada
      * @param serv lista de servicios asociados a la consulta
      * @param capas lista de capas asociadas a la consulta de usuario
      * @param areas lista de tipo Areas con la información del indicador 
      * @param docs lista de tipo InfoyDocs con los documentos asociados a la consulta
      * @param info lista de tipo InfoyDocs con la información adicional asociada a la consulta
      * @return String en json con toda la información de la consulta de superficie 
      * con conflicto de uso por subutilización
      */
     public String crearJsonSuperficieSubUtilizacion(Tablacontenido tc, List<Servicios> serv,
            List<Capas> capas, List<Areas> areas,List<InfoyDocs> docs,List<InfoyDocs> info){
        Varios v = new Varios();
        String iden="\"ext\":[],\"orCsgs\":[\""+capas.get(0).getAlias()+"\", \"cgDepartamentos\"],\n" +
                    "\"identificacion\":{\"t\": \"auto\",\"dats\": [{\n" +
                    "\"al\": \"cgDepartamentos\",\n" +
                    //"\"ind\": 0,\n" +
                    "\"compCg\": \"codigodane\",\n" +
                    "\"compTab\": \"Código DANE\",\n" +
                    "\"tab\":{\n" +
                    "\"ind\": 0,\n" +
                    "\"colums\": [\"Departamento\",\"% del suelo deptal con conflicto por subutilización\",\"Categoría\"]}\n" +
                    "}]}";
        String atMaps=crearJsonInfGeoConsultas(tc, serv, capas,iden);
        List<String> listColumnas = new ArrayList();
        String column;
        listColumnas.add("[\"Código DANE\", \"t\", \"100px\"]");
        listColumnas.add("[\"Departamento\", \"t\", \"120px\"]");
        listColumnas.add("[\"% del suelo deptal con conflicto por subutilización\", \"n\", \"300px\"]");
        listColumnas.add("[\"Categoría\", \"t\", \"80px\"]");
        column = "\"colums\":[" + addCommaString(listColumnas) + "]";
        List<String> registros = new ArrayList();
        String tabla;
        for (Areas r : areas) {
            registros.add("[{\"c\":\"#"+coloresCategorias(r.getTipo())+"\"},\""+r.getCodigoDane()+"\","
                    + "\""+r.getDepartamento()+"\","
                    + r.getArea()+","
                    + "\""+r.getTipo()+"\"]");
        }
        registros.add("[{\"c\": \"#ED7D31\"}, \"\", \"<span style=\"float: center; font-weight: bold; background-color: #70AD46\">COLOMBIA</span>\", "+v.promedioAreas(areas, 5)+", \"Muy Bajo\"]");
        tabla="\"dats\":["+addCommaString(registros)+"]";
        String atTabs="\"atTabs\":{\"dats\":[{"+column+","+tabla+"}]}" ;
        //creacion del grafico
        String graf1;
        List<String> datGraf = new ArrayList<>();
        datGraf.add("[\"Muy Alto\","+v.promedioCategoriaZonas(areas, "Muy Alto")+"]");
        datGraf.add("[\"Medio\","+v.promedioCategoriaZonas(areas, "Medio")+"]");
        datGraf.add("[\"Bajo\","+v.promedioCategoriaZonas(areas, "Bajo")+"]");
        datGraf.add("[\"Muy Bajo\","+v.promedioCategoriaZonas(areas, "Muy Bajo")+"]");
        graf1="{\"t\":\"t\","
                + "\"tit\":\"Porcentaje del suelo nacional con conflicto de uso por subutilización\","
                + "\"es\":{\"3d\":true},"
                + "\"col\":[\"#FF0000\",\"#FFFF00\",\"#C6E0B4\",\"#70AD4F\"],"
                + "\"dats\":["+addCommaString(datGraf)+"]}";
        String graf2;
        datGraf.clear();
        for (Areas r : areas) {
            datGraf.add("[\""+r.getDepartamento()+"\","+r.getArea()+"]");
        }
        graf2="{\"t\":\"c\",\"tit\":\"Porcentaje del suelo departamental con conflicto de uso por subutilización\","
                + "\"otrosDats\":{\"ejeY\":\"% del suelo departamental con conflicto de uso por subutilización\"},"
                + "\"es\":{\"3d\":true},"
                + "\"cols\":[\"#4472c4\"],"
                + "\"dats\":["+addCommaString(datGraf)+"]}";
        String atGraf="\"atGras\":{\"dats\":["+graf1+","+graf2+"]}";
        //area de trabajo documentos
        String atDocs=jsonDocs(docs);
         //area de trabajo informacion
        String atInf=jsonInfo(info);
        //configuracion de la consulta
        String conf="\"conf\":{	\"atSel\": \"atMaps\"," +
                    "\"plantillas\": [1, 1, 1, 3, 1]}";
         String json="resp({\"ast\":{"+atMaps+","+atTabs+","+atGraf+","+atDocs+","+atInf+"},"+conf+"})";
        return json;
     }
     
     /**
      * Método encargado de crear el json para la consulta del indicador 
      * de superficie sin conflicto de uso
      * @param tc tabla de contenido de la consulta asociada
      * @param serv lista de servicios asociados a la consulta
      * @param capas lista de capas asociadas a la consulta de usuario
      * @param areas lista de tipo Areas con la información del indicador 
      * @param docs lista de tipo InfoyDocs con los documentos asociados a la consulta
      * @param info lista de tipo InfoyDocs con la información adicional asociada a la consulta
      * @return String en json con toda la información de la consulta de superficie 
      * con conflicto de uso por subutilización
      */
     public String crearJsonSuperficieSinConflicto(Tablacontenido tc, List<Servicios> serv,
            List<Capas> capas, List<Areas> areas,List<InfoyDocs> docs,List<InfoyDocs> info){
        Varios v = new Varios();
        String iden="\"ext\":[],\"orCsgs\":[\""+capas.get(0).getAlias()+"\", \"cgDepartamentos\"],\n" +
                    "\"identificacion\":{\"t\": \"auto\",\"dats\": [{\n" +
                    "\"al\": \"cgDepartamentos\",\n" +
                    //"\"ind\": 0,\n" +
                    "\"compCg\": \"codigodane\",\n" +
                    "\"compTab\": \"Código DANE\",\n" +
                    "\"tab\":{\n" +
                    "\"ind\": 0,\n" +
                    "\"colums\": [\"Departamento\",\"% del suelo deptal con uso adecuado\",\"Categoría\"]}\n" +
                    "}]}";
        String atMaps=crearJsonInfGeoConsultas(tc, serv, capas,iden);
        List<String> listColumnas = new ArrayList();
        String column;
        listColumnas.add("[\"Código DANE\", \"t\", \"100px\"]");
        listColumnas.add("[\"Departamento\", \"t\", \"120px\"]");
        listColumnas.add("[\"% del suelo deptal con uso adecuado\", \"n\", \"230px\"]");
        listColumnas.add("[\"Categoría\", \"t\", \"80px\"]");
        column = "\"colums\":[" + addCommaString(listColumnas) + "]";
        List<String> registros = new ArrayList();
        String tabla;
        for (Areas r : areas) {
            registros.add("[{\"c\":\"#"+coloresCategorias(r.getTipo())+"\"},\""+r.getCodigoDane()+"\","
                    + "\""+r.getDepartamento()+"\","
                    + r.getArea()+","
                    + "\""+r.getTipo()+"\"]");
        }
        registros.add("[{\"c\": \"#ED7D31\"}, \"\", \"<span style=\"float: center; font-weight: bold; background-color: #ed7d31\">COLOMBIA</span>\", "+v.promedioAreas(areas, 5)+", \"Alto\"]");
        tabla="\"dats\":["+addCommaString(registros)+"]";
        String atTabs="\"atTabs\":{\"dats\":[{"+column+","+tabla+"}]}" ;
        //creacion del grafico
        String graf1;
        List<String> datGraf = new ArrayList<>();
        datGraf.add("[\"Muy Alto\","+v.promedioCategoriaZonas(areas, "Muy Alto")+"]");
        datGraf.add("[\"Alto\","+v.promedioCategoriaZonas(areas, "Alto")+"]");
        datGraf.add("[\"Medio\","+v.promedioCategoriaZonas(areas, "Medio")+"]");
        datGraf.add("[\"Bajo\","+v.promedioCategoriaZonas(areas, "Bajo")+"]");
        datGraf.add("[\"Muy Bajo\","+v.promedioCategoriaZonas(areas, "Muy Bajo")+"]");
        graf1="{\"t\":\"t\","
                + "\"tit\":\"Porcentaje del suelo nacional con uso adecuado de la tierra\","
                + "\"es\":{\"3d\":true},"
                + "\"col\":[\"#FF0000\",\"#ED7D31\",\"#FFFF00\",\"#C6E0B4\",\"#70AD4F\"],"
                + "\"dats\":["+addCommaString(datGraf)+"]}";
        String graf2;
        datGraf.clear();
        for (Areas r : areas) {
            datGraf.add("[\""+r.getDepartamento()+"\","+r.getArea()+"]");
        }
        graf2="{\"t\":\"c\",\"tit\":\"Porcentaje del suelo departamental con uso adecuado de la tierra\","
                + "\"otrosDats\":{\"ejeY\":\"Porcentaje del suelo departamental con uso adecuado de la tierra\"},"
                + "\"es\":{\"3d\":true},"
                + "\"cols\":[\"#4472c4\"],"
                + "\"dats\":["+addCommaString(datGraf)+"]}";
        String atGraf="\"atGras\":{\"dats\":["+graf1+","+graf2+"]}";
        //area de trabajo documentos
        String atDocs=jsonDocs(docs);
         //area de trabajo informacion
        String atInf=jsonInfo(info);
        //configuracion de la consulta
        String conf="\"conf\":{	\"atSel\": \"atMaps\"," +
                    "\"plantillas\": [1, 1, 1, 3, 1]}";
         String json="resp({\"ast\":{"+atMaps+","+atTabs+","+atGraf+","+atDocs+","+atInf+"},"+conf+"})";
        return json;
     }
     
     /**
      * Método que se encarga de tomar una categoria de las consultas de indicadores y devolver
      * el color correspondiente a esa categoria
      * @param categoria Categoria de la zona en las consultas de indicadores, los valores son: Muy Alto,
      * Alto, Medio, Bajo y Muy Bajo.
      * @return color en formato hexagesimal
      */
     public String coloresCategorias(String categoria){
         switch(categoria){
            case "Muy Alto":
                 return "FF0000";
            case "Alto":
                return "ED7D31";
            case "Medio":
                return "FFFF00";
            case "Bajo":
                return "C6E0B4";
            case "Muy Bajo":
                return "70AD4F";
            default:
                return "";
         }
     }
     
     /**
     * Método encargado de crear el json con la información de la consulta de precios para el 
     * departamento de Cordoba
     * @param tc objeto de tipo tablaContenido asociado a una consulta determinada
     * @param serv Lista de tipo Servicios con los servicios asociadas la consulta
     * @param capas Lista de tipo Capas con las capas asociadas a la consulta
     * @param areas lista de tipo Areas con la información de los precios de Cordoba
     * @return String de en formato Json con la información de la consulta para precios Cordoba
     */
    public String crearJsonPreciosCordoba(Tablacontenido tc, List<Servicios> serv, List<Capas> capas, List<Areas> areas){
        Varios v = new Varios();
        String iden="\"ext\":[-76.13914,9.28477,-75.21107,8.53169],\"orCsgs\":[\"PC\"],\n" +
                    "\"identificacion\":{\"t\": \"auto\",\"dats\": [{\n" +
                    "\"al\": \"PC\",\n" +
                    "\"ind\": 1 ,\n" +
                    "\"compCg\": \"id\",\n" +
                    "\"compTab\": \"Id\",\n" +
                    "\"tab\":{\n" +
                    "\"ind\": 0,\n" +
                    "\"colums\": [\"Departamento\",\"Municipio\",\"Área (ha)\",\"Rango de precio\"]}\n" +
                    "}]}";
        String atMaps=crearJsonInfGeoConsultas(tc, serv, capas,iden);
        List<String> listColumnas = new ArrayList();
        String column;
        listColumnas.add("[\"Id\", \"n\", \"20px\"]");
        listColumnas.add("[\"Código DANE\", \"t\", \"100px\"]");
        listColumnas.add("[\"Departamento\", \"t\", \"100px\"]");
        listColumnas.add("[\"Municipio\", \"t\", \"120px\"]");
        listColumnas.add("[\"Área (ha)\", \"n\", \"70px\"]");
        listColumnas.add("[\"Rango de precio\", \"t\", \"120px\"]");
        column = "\"colums\":[" + addCommaString(listColumnas) + "]";
        List<String> registros = new ArrayList();
        String tabla;
        int k = 1;
        for (Areas r : areas) {
            registros.add("[{},"+k+",\""+r.getCodigoDane()+"\","
                    + "\""+r.getDepartamento()+"\","
                    + "\""+r.getMunicipio()+"\","
                    + r.getArea().setScale(2, RoundingMode.HALF_UP)+","
                    + "\""+r.getTipo()+"\"]");
            k++;
        }
        tabla="\"dats\":["+addCommaString(registros)+"]";
        String atTabs="\"atTabs\":{\"dats\":[{"+column+","+tabla+"}]}" ;
        //creacion del grafico
        String graf1;
        List<String> datGraf = new ArrayList<>();
        datGraf.add("[\"1 a 5 millones\","+addCommaBigDecimal(v.sumarAreaXCategoria(areas, "1 a 5 millones","",null))+"]");
        datGraf.add("[\"10 a 15 millones\","+addCommaBigDecimal(v.sumarAreaXCategoria(areas, "10 a 15 millones","",null))+"]");
        datGraf.add("[\"15 a 20 millones\","+addCommaBigDecimal(v.sumarAreaXCategoria(areas, "15 a 20 millones","",null))+"]");
        datGraf.add("[\"20 a 30 millones\","+addCommaBigDecimal(v.sumarAreaXCategoria(areas, "20 a 30 millones","",null))+"]");
        datGraf.add("[\"30 a 40 millones\","+addCommaBigDecimal(v.sumarAreaXCategoria(areas, "30 a 40 millones","",null))+"]");
        datGraf.add("[\"40 a 60 millones\","+addCommaBigDecimal(v.sumarAreaXCategoria(areas, "40 a 60 millones","",null))+"]");
        datGraf.add("[\"100 a 120 millones\","+addCommaBigDecimal(v.sumarAreaXCategoria(areas, "100 a 120 millones","",null))+"]");
        graf1="{\"t\":\"t\","
                + "\"tit\":\"Porcentaje de hectáreas por rango de precios\","
                + "\"es\":{\"3d\":true,\"caLe\":true},"
                + "\"col\":[\"#ffebaf\",\"#f5d072\",\"#edb337\",\"#e69900\",\"#bf5600\",\"#992100\",\"#720000\"],"
                + "\"dats\":["+addCommaString(datGraf)+"]}";
        List<String> cate = new ArrayList<>();
        cate.add("1 a 5 millones");
        cate.add("10 a 15 millones");
        cate.add("10 a 15 millones");
        cate.add("20 a 30 millones");
        cate.add("30 a 40 millones");
        cate.add("40 a 60 millones");
        cate.add("100 a 120 millones");
        String graf2;
        datGraf.clear();
            datGraf.add("[\"CERETÉ\",["+addCommaBigDecimal(v.sumarAreaXCategoria(areas, "", "CERETÉ",cate))+"]]");
            datGraf.add("[\"CHIMA\",["+addCommaBigDecimal(v.sumarAreaXCategoria(areas, "", "CHIMA",cate))+"]]");
            datGraf.add("[\"CIÉNAGA DE ORO\",["+addCommaBigDecimal(v.sumarAreaXCategoria(areas, "", "CIÉNAGA DE ORO",cate))+"]]");
            datGraf.add("[\"COTORRA\",["+addCommaBigDecimal(v.sumarAreaXCategoria(areas, "", "COTORRA",cate))+"]]");
            datGraf.add("[\"SAHAGÚN\",["+addCommaBigDecimal(v.sumarAreaXCategoria(areas, "", "SAHAGÚN",cate))+"]]");
        graf2="{\"t\":\"c\",\"tit\":\"Total de hectáreas municipal por rango de precios\","
                + "\"otrosDats\":{\"ejeY\":\"Área (ha)\", \"ejeX\": [\"5 a 10 millones\",\"10 a 15 millones\","
                + "\"15 a 20 millones\",\"20 a 30 millones\",\"30 a 40 millones\",\"40 a 60 millones\",\"100 a 120 millones\"]},"
                + "\"es\":{\"3d\":true, \"caLe\":true},"
                + "\"cols\":[\"#5b9bd5\",\"#ed7d31\",\"#a5a5a5\",\"#ffc000\",\"#4472c4\"],"
                + "\"dats\":["+addCommaString(datGraf)+"]}";
        String atGraf="\"atGras\":{\"dats\":["+graf1+","+graf2+"]}";
        //configuracion de la consulta
        String conf="\"conf\":{	\"atSel\": \"atMaps\"," +
                    "\"plantillas\": [0, 1, 1, 3, 0]}";
         String json="resp({\"ast\":{"+atMaps+","+atTabs+","+atGraf+"},"+conf+"})";
        return json;
    }
    
    /**
     * Método encargado de crear el json con la información de la consulta de precios para el 
     * departamento de Tolima
     * @param tc objeto de tipo tablaContenido asociado a una consulta determinada
     * @param serv Lista de tipo Servicios con los servicios asociadas la consulta
     * @param capas Lista de tipo Capas con las capas asociadas a la consulta
     * @param areas lista de tipo Areas con la información de los precios de Tolima
     * @return String de en formato Json con la información de la consulta para precios Tolima
     */
    public String crearJsonPreciosTolima(Tablacontenido tc, List<Servicios> serv, List<Capas> capas, List<Areas> areas){
        Varios v = new Varios();
        String iden="\"ext\":[-75.16998,4.02431,-74.65341,3.58132],\"orCsgs\":[\"PT\"],\n" +
                    "\"identificacion\":{\"t\": \"auto\",\"dats\": [{\n" +
                    "\"al\": \"PT\",\n" +
                    "\"ind\": 1 ,\n" +
                    "\"compCg\": \"id\",\n" +
                    "\"compTab\": \"Id\",\n" +
                    "\"tab\":{\n" +
                    "\"ind\": 0,\n" +
                    "\"colums\": [\"Departamento\",\"Municipio\",\"Área (ha)\",\"Rango de precio\"]}\n" +
                    "}]}";
        String atMaps=crearJsonInfGeoConsultas(tc, serv, capas,iden);
        List<String> listColumnas = new ArrayList();
        String column;
        listColumnas.add("[\"Id\", \"n\", \"20px\"]");
        listColumnas.add("[\"Código DANE\", \"t\", \"100px\"]");
        listColumnas.add("[\"Departamento\", \"t\", \"100px\"]");
        listColumnas.add("[\"Municipio\", \"t\", \"120px\"]");
        listColumnas.add("[\"Área (ha)\", \"n\", \"70px\"]");
        listColumnas.add("[\"Rango de precio\", \"t\", \"120px\"]");
        column = "\"colums\":[" + addCommaString(listColumnas) + "]";
        List<String> registros = new ArrayList();
        String tabla;
        int k = 1;
        for (Areas r : areas) {
            registros.add("[{},"+k+",\""+r.getCodigoDane()+"\","
                    + "\""+r.getDepartamento()+"\","
                    + "\""+r.getMunicipio()+"\","
                    + r.getArea().setScale(2, RoundingMode.HALF_UP)+","
                    + "\""+r.getTipo()+"\"]");
            k++;
        }
        tabla="\"dats\":["+addCommaString(registros)+"]";
        String atTabs="\"atTabs\":{\"dats\":[{"+column+","+tabla+"}]}" ;
        //creacion del grafico
        String graf1;
        List<String> datGraf = new ArrayList<>();
        datGraf.add("[\"1 a 3 millones\","+addCommaBigDecimal(v.sumarAreaXCategoria(areas, "1 a 3 millones","",null))+"]");
        datGraf.add("[\"3 a 5 millones\","+addCommaBigDecimal(v.sumarAreaXCategoria(areas, "3 a 5 millones","",null))+"]");
        datGraf.add("[\"10 a 20 millones\","+addCommaBigDecimal(v.sumarAreaXCategoria(areas, "10 a 20 millones","",null))+"]");
        datGraf.add("[\"20 a 30 millones\","+addCommaBigDecimal(v.sumarAreaXCategoria(areas, "20 a 30 millones","",null))+"]");
        datGraf.add("[\"30 a 40 millones\","+addCommaBigDecimal(v.sumarAreaXCategoria(areas, "30 a 40 millones","",null))+"]");
        graf1="{\"t\":\"t\","
                + "\"tit\":\"Porcentaje de hectáreas por rango de precios\","
                + "\"es\":{\"3d\":true,\"caLe\":true},"
                + "\"col\":[\"#ffffb0\",\"#f2e599\",\"#e69900\",\"#ab3900\",\"#720000\"],"
                + "\"dats\":["+addCommaString(datGraf)+"]}";
        List<String> cate = new ArrayList<>();
        cate.add("1 a 3 millones");
        cate.add("3 a 5 millones");
        cate.add("10 a 20 millones");
        cate.add("20 a 30 millones");
        cate.add("30 a 40 millones");
        String graf2;
        datGraf.clear();
        datGraf.add("[\"PRADO\",["+addCommaBigDecimal(v.sumarAreaXCategoria(areas, "", "PRADO",cate))+"]]");
        datGraf.add("[\"PURIFICACIÓN\",["+addCommaBigDecimal(v.sumarAreaXCategoria(areas, "", "PURIFICACIÓN",cate))+"]]");
        datGraf.add("[\"SALDAÑA\",["+addCommaBigDecimal(v.sumarAreaXCategoria(areas, "", "SALDAÑA",cate))+"]]");
        graf2="{\"t\":\"c\",\"tit\":\"Total de hectáreas municipal por rango de precios\","
                + "\"otrosDats\":{\"ejeY\":\"Área (ha)\", \"ejeX\": [\"1 a 3 millones\",\"3 a 5 millones\","
                + "\"10 a 20 millones\",\"20 a 30 millones\",\"30 a 40 millones\"]},"
                + "\"es\":{\"3d\":true, \"caLe\":true},"
                + "\"cols\":[\"#5b9bd5\",\"#ed7d31\",\"#a5a5a5\"],"
                + "\"dats\":["+addCommaString(datGraf)+"]}";
        String atGraf="\"atGras\":{\"dats\":["+graf1+","+graf2+"]}";
        //configuracion de la consulta
        String conf="\"conf\":{	\"atSel\": \"atMaps\"," +
                    "\"plantillas\": [0, 1, 1, 3, 0]}";
         String json="resp({\"ast\":{"+atMaps+","+atTabs+","+atGraf+"},"+conf+"})";
        return json;
    }
    
    /**
     * Método encargado de crear el json con la información de la consulta de precios para el 
     * departamento de Meta
     * @param tc objeto de tipo tablaContenido asociado a una consulta determinada
     * @param serv Lista de tipo Servicios con los servicios asociadas la consulta
     * @param capas Lista de tipo Capas con las capas asociadas a la consulta
     * @param areas lista de tipo Areas con la información de los precios de Meta
     * @return String de en formato Json con la información de la consulta para precios Meta
     */
    public String crearJsonPreciosMeta(Tablacontenido tc, List<Servicios> serv, List<Capas> capas, List<Areas> areas){
        Varios v = new Varios();
        String iden="\"ext\":[-73.7875124577786,3.23848574686818,-72.2792437665612,3.8110734306383],\"orCsgs\":[\"PM\"],\n" +
                    "\"identificacion\":{\"t\": \"auto\",\"dats\": [{\n" +
                    "\"al\": \"PM\",\n" +
                    "\"ind\": 1 ,\n" +
                    "\"compCg\": \"id\",\n" +
                    "\"compTab\": \"Id\",\n" +
                    "\"tab\":{\n" +
                    "\"ind\": 0,\n" +
                    "\"colums\": [\"Departamento\",\"Municipio\",\"Área (ha)\",\"Rango de precio\"]}\n" +
                    "}]}";
        String atMaps=crearJsonInfGeoConsultas(tc, serv, capas,iden);
        List<String> listColumnas = new ArrayList();
        String column;
        listColumnas.add("[\"Id\", \"n\", \"20px\"]");
        listColumnas.add("[\"Código DANE\", \"t\", \"100px\"]");
        listColumnas.add("[\"Departamento\", \"t\", \"100px\"]");
        listColumnas.add("[\"Municipio\", \"t\", \"120px\"]");
        listColumnas.add("[\"Área (ha)\", \"n\", \"70px\"]");
        listColumnas.add("[\"Rango de precio\", \"t\", \"120px\"]");
        column = "\"colums\":[" + addCommaString(listColumnas) + "]";
        List<String> registros = new ArrayList();
        String tabla;
        int k = 1;
        for (Areas r : areas) {
            registros.add("[{},"+k+",\""+r.getCodigoDane()+"\","
                    + "\""+r.getDepartamento()+"\","
                    + "\""+r.getMunicipio()+"\","
                    + r.getArea().setScale(2,RoundingMode.HALF_UP)+","
                    + "\""+r.getTipo()+"\"]");
            k++;
        }
        tabla="\"dats\":["+addCommaString(registros)+"]";
        String atTabs="\"atTabs\":{\"dats\":[{"+column+","+tabla+"}]}" ;
        //creacion del grafico
        String graf1;
        List<String> datGraf = new ArrayList<>();
        datGraf.add("[\"1 a 3 millones\","+addCommaBigDecimal(v.sumarAreaXCategoria(areas, "1 a 3 millones","",null))+"]");
        datGraf.add("[\"3 a 5 millones\","+addCommaBigDecimal(v.sumarAreaXCategoria(areas, "3 a 5 millones","",null))+"]");
        datGraf.add("[\"10 a 20 millones\","+addCommaBigDecimal(v.sumarAreaXCategoria(areas, "10 a 20 millones","",null))+"]");
        datGraf.add("[\"20 a 30 millones\","+addCommaBigDecimal(v.sumarAreaXCategoria(areas, "20 a 30 millones","",null))+"]");
        graf1="{\"t\":\"t\","
                + "\"tit\":\"Porcentaje de hectáreas por rango de precios\","
                + "\"es\":{\"3d\":true,\"caLe\":true},"
                + "\"col\":[\"#ffffb0\",\"#f2e599\",\"#e69900\",\"#ab3900\"],"
                + "\"dats\":["+addCommaString(datGraf)+"]}";
        String atGraf="\"atGras\":{\"dats\":["+graf1+"]}";
        //configuracion de la consulta
        String conf="\"conf\":{	\"atSel\": \"atMaps\"," +
                    "\"plantillas\": [0, 1, 1, 1, 0]}";
         String json="resp({\"ast\":{"+atMaps+","+atTabs+","+atGraf+"},"+conf+"})";
        return json;
    }
    
    /**
     * Método encargado de crear el json con la información de la consulta de areas potenciales de 
     * riego y drenaje 
     * @param tc objeto de tipo tablaContenido asociado a una consulta determinada
     * @param serv Lista de tipo Servicios con los servicios asociadas la consulta
     * @param capas Lista de tipo Capas con las capas asociadas a la consulta
     * @param riego lista de tipo Areas con la información de las areas de riego por departamento
     * @param drenaje lista de tipo Areas con la información de las areas de drenaje por departamento
     * @return String de en formato Json con la información de de areas potenciales de 
     * riego y drenaje
     */
    public String crearJsonAreasPotenciales(Tablacontenido tc, List<Servicios> serv, List<Capas> capas, 
            List<Areas> riego,List<Areas> drenaje){
        Varios v = new Varios();
        String iden="\"ext\":[],\"orCsgs\":[\""+capas.get(0).getAlias()+"\", \"cgDepartamentos\"],\n" +
                    "\"identificacion\":{\"t\": \"auto\",\"dats\": [{\n" +
                    "\"al\": \"cgDepartamentos\",\n" +
                    //"\"ind\": 0,\n" +
                    "\"compCg\": \"codigodane\",\n" +
                    "\"compTab\": \"Código DANE\",\n" +
                    "\"tab\":{\n" +
                    "\"ind\": 0,\n" +
                    "\"colums\": [\"Departamento\",\"Suelos con potencial para distritos de riego (ha)\",\"Suelos con potencial para distritos de drenaje (ha)\"]},"+  
                    "\"gra\":{\"t\":\"c\",\"indTab\":0,"+
                    "\"colums\":[\"Suelos con potencial para distritos de riego (ha)\", \"Suelos con potencial para distritos de drenaje (ha)\"],"+
                    "\"cols\":[\"#96BADC\", \"#B3E7E6\"],\"otros\":{\"titEjeY\":\"Área (ha)\"}}" +
                    "}]}";
        String atMaps=crearJsonInfGeoConsultas(tc, serv, capas,iden);
        List<String> listColumnas = new ArrayList();
        String column;
        listColumnas.add("[\"Código DANE\", \"t\", \"100px\"]");
        listColumnas.add("[\"Departamento\", \"t\", \"120px\"]");
        listColumnas.add("[\"Suelos con potencial para distritos de riego (ha)\", \"n\", \"300px\"]");
        listColumnas.add("[\"Suelos con potencial para distritos de drenaje (ha)\", \"n\", \"320px\"]");
        column = "\"colums\":[" + addCommaString(listColumnas) + "]";
        List<String> registros = new ArrayList();
        String tabla;
        int tam = riego.size();
        for (int i = 0; i < tam; i++ ) {
            registros.add("["+i+",\""+riego.get(i).getCodigoDane()+"\","
                    + "\""+riego.get(i).getDepartamento()+"\","
                    + riego.get(i).getArea()+","
                    + drenaje.get(i).getArea()+"]");
        }
        tabla="\"dats\":["+addCommaString(registros)+"]";
        String atTabs="\"atTabs\":{\"dats\":[{"+column+","+tabla+"}]}" ;
        //creacion del grafico
        String graf1;
        List<String> datGraf = new ArrayList<>();
        datGraf.add("[\"Potencial para distritos de riego\","+v.promedioAreas(riego, 5)+"]");
        datGraf.add("[\"Potencial para distritos de drenaje\","+v.promedioAreas(drenaje, 5)+"]");
        graf1="{\"t\":\"c\","
                + "\"tit\":\"Total de hectáreas nacional por categoría\","
                + "\"otrosDats\":{\"ejeY\":\"Área (ha)\"},"
                + "\"es\":{\"3d\":true},"
                + "\"col\":[\"#96badc\",\"#b3e7e6\"],"
                + "\"dats\":["+addCommaString(datGraf)+"]}";
        String graf2;
        datGraf.clear();
        List<BigDecimal> r = new ArrayList<>();
        List<BigDecimal> d = new ArrayList<>();
        List<String> depto = new ArrayList<>();
        for (int i = 0; i < tam; i++ ) {
            r.add(riego.get(i).getArea());
            d.add(drenaje.get(i).getArea());
            depto.add("\""+riego.get(i).getDepartamento()+"\"");
        }
        datGraf.add("[\"Para distritos de riego\","+addCommaBigDecimal(r)+"]");
        datGraf.add("[\"Para distritos de drenaje\","+addCommaBigDecimal(d)+"]");
        graf2="{\"t\":\"p\",\"tit\":\"Total de hectáreas departamental por categoría\","
                + "\"otrosDats\":{\"ejeY\":\"Área (ha)\",\"ejeX\":["+addCommaString(depto)+"]},"
                + "\"es\":{\"3d\":true, \"caLe\":true},"
                + "\"cols\":[\"#96badc\",\"b3e7e6\"],"
                + "\"dats\":["+addCommaString(datGraf)+"]}";
        String atGraf="\"atGras\":{\"dats\":["+graf1+","+graf2+"]}";
        //configuracion de la consulta
        String conf="\"conf\":{	\"atSel\": \"atMaps\"," +
                    "\"plantillas\": [1, 1, 1, 3, 1]}";
         String json="resp({\"ast\":{"+atMaps+","+atTabs+","+atGraf+"},"+conf+"})";
        return json;
    }
    
    
    /**
     * Método encargado de crear el json con la información de la consulta de areas con
     * aptitud forestal comercial 
     * @param tc objeto de tipo tablaContenido asociado a una consulta determinada
     * @param serv Lista de tipo Servicios con los servicios asociadas la consulta
     * @param capas Lista de tipo Capas con las capas asociadas a la consulta
     * @param zonas departamentos con areas potenciales de categoria alto
     * @return String de en formato Json con la información de areas con
     * aptitud forestal comercial
     */
    public String crearJsonAptitudForestal(Tablacontenido tc, List<Servicios> serv, List<Capas> capas, 
            List<Areas> zonas){
        Varios v = new Varios();
        String iden="\"ext\":[],\"orCsgs\":[\""+capas.get(0).getAlias()+"\", \"cgDepartamentos\"],\n" +
                    "\"identificacion\":{\"t\": \"auto\",\"dats\": [{\n" +
                    "\"al\": \"cgDepartamentos\",\n" +
                    //"\"ind\": 0,\n" +
                    "\"compCg\": \"codigodane\",\n" +
                    "\"compTab\": \"Código DANE\",\n" +
                    "\"tab\":{\n" +
                    "\"ind\": 0,\n" +
                    "\"colums\": [\"Departamento\",\"Alta (ha)\",\"Media (ha)\",\"Baja (ha)\",\"No Apto (ha)\"]},"+  
                    "\"gra\":{\"t\":\"t\",\"indTab\":0,"+
                    "\"colums\":[\"Alta (ha)\",\"Media (ha)\",\"Baja (ha)\",\"No Apto (ha)\"],"+
                    "\"cols\":[\"#267300\", \"#69CC66\",\"FFF86E\",\"B8B8B8\"],\"otros\":{\"texRemplazar\":\" (ha)\"}}" +
                    "}]}";
        String atMaps=crearJsonInfGeoConsultas(tc, serv, capas,iden);
        List<String> listColumnas = new ArrayList();
        String column;
        listColumnas.add("[\"Código DANE\", \"t\", \"100px\"]");
        listColumnas.add("[\"Departamento\", \"t\", \"120px\"]");
        listColumnas.add("[\"Alta (ha)\", \"n\", \"150px\"]");
        listColumnas.add("[\"Media (ha)\", \"n\", \"150px\"]");
        listColumnas.add("[\"Baja (ha)\", \"n\", \"150px\"]");
        listColumnas.add("[\"No Apto\", \"n\", \"150px\"]");
        column = "\"colums\":[" + addCommaString(listColumnas) + "]";
        List<String> registros = new ArrayList();
        String tabla;
        for (Areas a: zonas) {
            registros.add("[{},\""+a.getCodigoDane()+"\","
                    + "\""+a.getDepartamento()+"\","
                    + a.getZonaAlta()+","
                    + a.getZonaMedia()+","
                    + a.getZonaBaja()+","
                    + a.getZonaNoApta()+"]");
        }
        registros.add("[{},\"\",\"TOTALES\",7507862.21,6593516.57,11829609.45,88054807.68]");
        tabla="\"dats\":["+addCommaString(registros)+"]";
        String atTabs="\"atTabs\":{\"dats\":[{"+column+","+tabla+"}]}" ;
        //creacion del grafico
        String graf1;
        List<String> datGraf = new ArrayList<>();
        datGraf.add("[\"Alta\","+v.promedioAreas(zonas, 6)+"]");
        datGraf.add("[\"Media\","+v.promedioAreas(zonas, 7)+"]");
        datGraf.add("[\"Baja\","+v.promedioAreas(zonas, 8)+"]");
        datGraf.add("[\"No Apto\","+v.promedioAreas(zonas, 9)+"]");
        graf1="{\"t\":\"t\","
                + "\"tit\":\"Porcentaje de hectáreas nacional por categoría\","
                + "\"otrosDats\":{\"ejeY\":\"Área (ha)\"},"
                + "\"es\":{\"3d\":true, \"caLe\":true},"
                + "\"col\":[\"#267300\",\"#69CC66\",\"FFF86E\",\"B8B8B8\"],"
                + "\"dats\":["+addCommaString(datGraf)+"]}";
        String graf2;
        datGraf.clear();
        List<BigDecimal> a = new ArrayList<>();
        List<BigDecimal> m = new ArrayList<>();
        List<BigDecimal> b = new ArrayList<>();
        List<BigDecimal> na = new ArrayList<>();
        List<String> depto = new ArrayList<>();
        for (Areas area: zonas) {
            a.add(area.getZonaAlta());
            m.add(area.getZonaMedia());
            b.add(area.getZonaBaja());
            na.add(area.getZonaNoApta());
            depto.add("\""+area.getDepartamento()+"\"");
        }
        datGraf.add("[\"Alta\","+addCommaBigDecimal(a)+"]");
        datGraf.add("[\"Media\","+addCommaBigDecimal(m)+"]");
        datGraf.add("[\"Baja\","+addCommaBigDecimal(b)+"]");
        datGraf.add("[\"No Apta\","+addCommaBigDecimal(na)+"]");
        graf2="{\"t\":\"p\",\"tit\":\"Total de hectáreas departamental por categoría\","
                + "\"otrosDats\":{\"ejeY\":\"Área (ha)\",\"ejeX\":["+addCommaString(depto)+"]},"
                + "\"es\":{\"3d\":true, \"caLe\":true},"
                + "\"cols\":[\"#267300\",\"#69CC66\",\"FFF86E\",\"B8B8B8\"],"
                + "\"dats\":["+addCommaString(datGraf)+"]}";
        String atGraf="\"atGras\":{\"dats\":["+graf1+","+graf2+"]}";
        //configuracion de la consulta
        String conf="\"conf\":{	\"atSel\": \"atMaps\"," +
                    "\"plantillas\": [0, 1, 1, 3, 0]}";
         String json="resp({\"ast\":{"+atMaps+","+atTabs+","+atGraf+"},"+conf+"})";
        return json;
    }
    
    /**
     * Método encargado de crear el json con la información de la consulta de usuario
     * de presencia de actores involucrados 
     * @param tc objeto de tipo tablaContenido asociado a una consulta determinada
     * @param serv Lista de tipo Servicios con los servicios asociadas la consulta
     * @param capas Lista de tipo Capas con las capas asociadas a la consulta
     * @param depto Lista con la información organizada por departamentos
     * @param org Lista con la información organizada por organizaciones
     * @param res  Lista con el resumen de las organizaciones por departamento
     * @return String de en formato Json con la información de los actores involucrados
     */
    public String crearJsonPresenciaActores(Tablacontenido tc, List<Servicios> serv, List<Capas> capas, 
            List<Actores> depto,List<Actores> org,List<Actores> res){
        Varios v = new Varios();
        String iden="\"ext\":[],\"orCsgs\":[\""+capas.get(0).getAlias()+"\", \"cgDepartamentos\"],\n" +
                    "\"identificacion\":{\"t\": \"auto\",\"dats\": [{\n" +
                    "\"al\": \"cgDepartamentos\",\n" +
                    //"\"ind\": 0,\n" +
                    "\"compCg\": \"codigodane\",\n" +
                    "\"compTab\": \"Código DANE\",\n" +
                    "\"tab\":{\n" +
                    "\"ind\": 0,\n" +
                    "\"colums\": [\"Departamento\",\"Categoría\"]}"+  
                    "}]}";
        String atMaps=crearJsonInfGeoConsultas(tc, serv, capas,iden);
        List<String> listColumnas = new ArrayList();
        String column;
        listColumnas.add("[\"Código DANE\", \"t\", \"100px\"]");
        listColumnas.add("[\"Departamento\", \"t\", \"120px\"]");
        listColumnas.add("[\"Número de organizaciones\", \"n\", \"170px\"]");
        listColumnas.add("[\"Categoría\", \"t\", \"160px\"]");
        column = "\"colums\":[" + addCommaString(listColumnas) + "]";
        List<String> registros = new ArrayList();
        String tabla;
        for (Actores d:depto ) {
            registros.add("[{},\""+d.getCodDane()+"\","
                    + "\""+d.getDepto()+"\","
                    + d.getNumOrg()+","
                    + "\""+d.getCategoria()+"\"]");
        }
        tabla="\"dats\":["+addCommaString(registros)+"]";
        String column2;
        listColumnas.clear();
        listColumnas.add("[\"Organización\", \"t\", \"100px\"]");
        listColumnas.add("[\"Localización\", \"t\", \"120px\"]");
        listColumnas.add("[\"Departamentos\", \"t\", \"170px\"]");
        listColumnas.add("[\"Municipios\", \"t\", \"160px\"]");
        column2 = "\"colums\":[" + addCommaString(listColumnas) + "]";
        List<String> registros2 = new ArrayList();
        String tabla2;
        for (Actores o:org ) {
            registros2.add("[{},\""+o.getOrg()+"\","
                    + "\""+o.getLocalizacion()+"\","
                    + "\""+o.getDepto()+"\","
                    + "\""+o.getMunpio()+"\"]");
        }
        tabla2="\"dats\":["+addCommaString(registros2)+"]";
        String atTabs="\"atTabs\":{\"dats\":[{"+column+","+tabla+"},{"+column2+","+tabla2+"}]}" ;
        //creacion del grafico
        String graf1;
        List<String> datGraf = new ArrayList<>();
        for (Actores r : res) {
            datGraf.add("[\""+r.getDepto()+"\","+r.getNumOrg()+"]");
        }
        graf1="{\"t\":\"c\","
                + "\"tit\":\"Número de organizaciones por departamento\","
                + "\"otrosDats\":{\"ejeY\":\"Número de organizaciones\"},"
                + "\"es\":{\"3d\":true},"
                + "\"col\":[\"#4472c4\"],"
                + "\"dats\":["+addCommaString(datGraf)+"]}";
        String atGraf="\"atGras\":{\"dats\":["+graf1+"]}";
        //configuracion de la consulta
        String conf="\"conf\":{	\"atSel\": \"atMaps\"," +
                    "\"plantillas\": [0, 1, 2, 1, 0]}";
         String json="resp({\"ast\":{"+atMaps+","+atTabs+","+atGraf+"},"+conf+"})";
        return json;
    }
    /**
     * Método encargado de crear el json con la información de la consulta de usuario
     * de presencia de actores involucrados 
     * @param tc objeto de tipo tablaContenido asociado a una consulta determinada
     * @param serv Lista de tipo Servicios con los servicios asociadas la consulta
     * @param capas Lista de tipo Capas con las capas asociadas a la consulta
     * @param bps lista con la informacion del banco de proyectos 
     * @param deptos Lista con los departamentos del pais
     * @param docs Lista con los documentos asociados a la consulta del banco de proyectos
     * @return String de en formato Json con la información del banco de proyectos
     */
    public String crearJsonBancoProyectos(Tablacontenido tc, List<Servicios> serv, List<Capas> capas, 
            List<BancoProyectos> bps, List<String> deptos,List<InfoyDocs> docs){
        Varios v = new Varios();
        String iden="\"ext\":[],\"orCsgs\":[\""+capas.get(0).getAlias()+"\"],\n" +
                    "\"identificacion\":{\"t\": \"auto\",\"dats\": [{\n" +
                    "\"al\": \""+capas.get(0).getAlias()+"\",\n" +
                    "\"ind\": 0,\n" +
                    "\"compCg\": \"id\",\n" +
                    "\"compTab\": \"ID\",\n" +
                    "\"tab\":{\n" +
                    "\"ind\": 0,\n" +
                    "\"colums\": [\"Nombre proyecto\",\"Departamento(s) beneficiados\",\"Municipio(s) beneficiados\","
                + "\"Número de familias beneficiadas\",\"Subetapa\",\"Tipo de proyecto\",\"Área neta (ha)\"]}"+  
                    "}]}";
        String atMaps=crearJsonInfGeoConsultas(tc, serv, capas,iden);
        List<String> listColumnas = new ArrayList();
        String column;
        listColumnas.add("[\"ID\", \"n\", \"30px\"]");
        listColumnas.add("[\"Nombre proyecto\", \"t\", \"115px\"]");
        listColumnas.add("[\"Departamento(s) beneficiados\", \"t\", \"125px\"]");
        listColumnas.add("[\"Municipio(s) beneficiados\", \"t\", \"100px\"]");
        listColumnas.add("[\"Fecha\", \"t\", \"50px\"]");
        listColumnas.add("[\"Número de municipios\", \"n\", \"110px\"]");
        listColumnas.add("[\"Número de veredas\", \"n\", \"105px\"]");
        listColumnas.add("[\"Número de familias beneficiadas\", \"n\", \"105px\"]");
        listColumnas.add("[\"Postulante\", \"t\", \"90px\"]");
        listColumnas.add("[\"Tiempo Programación Obra\", \"t\", \"100px\"]");
        listColumnas.add("[\"Costo Obra\", \"t\", \"110px\"]");
        listColumnas.add("[\"Subetapa\", \"t\", \"120px\"]");
        listColumnas.add("[\"Tipo de proyecto\", \"t\", \"110px\"]");
        listColumnas.add("[\"Área bruta (ha)\", \"n\", \"90px\"]");
        listColumnas.add("[\"Área neta (ha)\", \"n\", \"85px\"]");
        listColumnas.add("[\"Documentos\", \"t\", \"90px\"]");
        column = "\"colums\":[" + addCommaString(listColumnas) + "]";
        List<String> registros = new ArrayList();
        String tabla;
        for (BancoProyectos b:bps ) {
            int i=1;
            registros.add("[{},"+i+",\""+b.getNomProyecto()+"\","
                    + "\""+b.getDepto()+"\","
                    + "\""+b.getMunpio()+"\","
                    + "\""+b.getFecha()+"\","
                    + b.getNumMunpios()+","
                    + b.getNumveredas()+","
                    + b.getNumfamilias()+","
                    + "\""+b.getPostulante()+"\","
                    + "\""+b.getTiempo()+"\","
                    + b.getCosto()+","
                    + "\""+b.getSubetapa()+"\","
                    + "\""+b.getTipoProy()+"\","
                    + b.getAreaBruta()+","
                    + b.getAreaNeta()+","
                    + "\""+b.getDocum()+"\"]");
            i++;
        }
        registros.add("[{},0,\"<span style=\"float: left; font-weight: bold; background-color: #cccccc\">TOTALES</span>\",null,null,"
                + "null,92,82,73371,null,null,3505607327832.0,null,null,721839.10,606831.0,null]");
        tabla="\"dats\":["+addCommaString(registros)+"]";
        String atTabs="\"atTabs\":{\"dats\":[{"+column+","+tabla+"}]}" ;
        //creacion del grafico
        String graf1;
        List<String> datGraf = new ArrayList<>();
        datGraf.add("[\"Prefactibilidad\","+v.BancoProyDeptoXSubetapa(deptos, bps, "PREFACTIBILIDAD")+"]");
        datGraf.add("[\"factibilidad\","+v.BancoProyDeptoXSubetapa(deptos, bps, "FACTIBILIDAD")+"]");
        datGraf.add("[\"Diseño\","+v.BancoProyDeptoXSubetapa(deptos, bps, "DISEÑO")+"]");
        for (int i = 0; i < deptos.size(); i++) {
            deptos.set(i, "\""+deptos.get(i)+"\"");
        }
        graf1="{\"t\":\"p\","
                + "\"tit\":\"Número de proyectos por departamento y etapa\","
                + "\"otrosDats\":{\"ejeY\":\"Número de proyectos\",\"ejeX\":["+addCommaString(deptos)+"]},"
                + "\"es\":{\"3d\":true,\"caLe\":true},"
                + "\"col\":[\"#ffff99\",\"#F7D6B5\",\"#F4631F\"],"
                + "\"dats\":["+addCommaString(datGraf)+"]}";
        String atGraf="\"atGras\":{\"dats\":["+graf1+"]}";
         //area de trabajo documentos
        String atDocs=jsonDocs(docs);
        //configuracion de la consulta
        String conf="\"conf\":{	\"atSel\": \"atMaps\"," +
                    "\"plantillas\": [0, 1, 1, 1, 0]}";
         String json="resp({\"ast\":{"+atMaps+","+atTabs+","+atGraf+","+atDocs+"},"+conf+"})";
        return json;
    }
}
