/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package util;

import java.util.ArrayList;
import java.util.List;
import obj.Capas;
import obj.Departamentos;
import obj.DinamicaMercados;
import obj.FiltroJson;
import obj.Filtros;
import obj.Menuconsultas;
import obj.Municipios;
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
         String json="";
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
        List<String> sub2=new ArrayList<String>();
        int cont;
        for (Tablacontenido tab : tc) {
            List<String> sub1=new ArrayList<String>();
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
        List<String> al=new ArrayList<String>();
        for (Servicios s : serv) {
            List<String> sub1=new ArrayList<String>();
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
            List<String> sub2=new ArrayList<String>();
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
         String nivel1;
         String nivel2;
         String nivel3;
         String nivel4="";
         String t4;
         int cont;
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
            cont=tmp.size();
            if(cont>1){
                for (int i = 0; i < cont-1; i++) {// este for se puede rehusar en un metodo
                    nivel4=nivel4+tmp.get(i)+",";
                }
            }
            nivel4=nivel4+tmp.get(cont-1);
            nivel4="\"conf\":{"+nivel4+"}";
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
            cont=tmp.size();
            t4="";
            if(cont>1){
                for (int i = 0; i < cont-1; i++) {
                     t4=t4+tmp.get(i)+",";
                }
            }
            t4=t4+tmp.get(cont-1);
            nivel4=nivel4+",\"es\":{"+t4+"}";
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
            cont=tmp.size();
            t4="";
            if(cont>1){
                for (int i = 0; i < cont-1; i++) {
                     t4=t4+tmp.get(i)+",";
                }
            }
            t4=t4+tmp.get(cont-1);
            nivel4=nivel4+",\"inf\":{"+t4+"}";
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
             nivel1="";
             nivel2="";
             nivel3="";
             nivel4="";
             tmp=new ArrayList<>();
         }
        cont=json.size();
        resul="";
        if(cont>1){
            for (int i = 0; i < cont-1; i++) {
                 resul=resul+json.get(i)+",";
            }
        }
        resul="\"csgs\":{"+resul+json.get(cont-1)+"}";
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
        String otros;
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
        if(nac!="")
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
        String json="";
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
    
    
    public String crearJsonDinamMerc(FiltroJson filtro, List<DinamicaMercados> lista){
        String tmp="";
        int numAnios=filtro.getAnios().length;
        String columnGroup="";
        List<String> lColumnGroup=new ArrayList<>();
        List<String> lColumn=new ArrayList<>();
        String column="";
        ArrayList<String> titulosColumnas=new ArrayList<>();
        List<String> lSubRegistros= new ArrayList<>();
        List<String> lRegistros= new ArrayList<>();
        String registros="";
        //////Creacion de los ColumnGroups
        for (int i = 0; i < 3; i++) {
            tmp="{\"caption\":\"\",\"span\":\"1\"}";
            lColumnGroup.add(tmp);
        }
        for (int i = 0; i < numAnios; i++) {
            tmp="{\"caption\":\""+filtro.getAnios()[i]+"\",\"span\":\"6\"}";
            lColumnGroup.add(tmp);
        }
        columnGroup="\"columnGroups\":["+addComma(lColumnGroup)+"]";
        //Creacion de las columnas
        tmp="{\"field\":  \"recid\",\"caption\": \"#\",\"size\": \"50px\",\"sortable\": true,\"attr\": \"align=center\"}";
        lColumn.add(tmp);
        tmp="{\"field\":  \"codDane\",\"caption\": \"CódigoDANE\",\"size\": \"30px\",\"sortable\": true,\"resizable\": true}";
        lColumn.add(tmp);
        tmp="{\"field\":  \"departamento\",\"caption\": \"Departamento\",\"size\": \"100px\",\"sortable\": true,\"resizable\": true}";
        lColumn.add(tmp);
        String titulo="";
        for (int i = 0; i < numAnios; i++) {
            titulo="\"compraventa"+i+"\"";
            tmp="{\"field\":"+titulo+",\"caption\": \"# de compraventas\",\"size\": \"8%\",\"sortable\": true,\"attr\": \"align=center\",\"resizable\": true}";
            titulosColumnas.add(titulo);
            lColumn.add(tmp);
            titulo="\"hipoteca"+i+"\"";
            tmp="{\"field\":"+titulo+",\"caption\": \"# de hipotecas\",\"size\": \"8%\",\"sortable\": true,\"attr\": \"align=center\",\"resizable\": true}";
            titulosColumnas.add(titulo);
            lColumn.add(tmp);
            titulo="\"remate"+i+"\"";
            tmp="{\"field\": "+titulo+",\"caption\": \"# de remates\",\"size\": \"8%\",\"sortable\": true,\"attr\": \"align=center\",\"resizable\": true}";
            titulosColumnas.add(titulo);
            lColumn.add(tmp);
            titulo="\"permuta"+i+"\"";
            tmp="{\"field\": "+titulo+",\"caption\": \"# de permutas\",\"size\": \"8%\",\"sortable\": true,\"attr\": \"align=center\",\"resizable\": true}";
            titulosColumnas.add(titulo);
            lColumn.add(tmp);
            titulo="\"embargo"+i+"\"";
            tmp="{\"field\": "+titulo+",\"caption\": \"# de embargos\",\"size\": \"8%\",\"sortable\": true,\"attr\": \"align=center\",\"resizable\": true}";
            titulosColumnas.add(titulo);
            lColumn.add(tmp);
            titulo="\"pesoTransaccion"+i+"\"";
            tmp="{\"field\": "+titulo+",\"caption\": \"#Peso de Transacciones\",\"size\": \"8%\",\"sortable\": true,\"attr\": \"align=center\",\"resizable\": true}";
            titulosColumnas.add(titulo);
            lColumn.add(tmp);
        }
        column="\"columns\":["+addComma(lColumn)+"]";
        //creación de los registros
        int id =1;
        for (int i = 0; i < lista.size(); i+=numAnios) {
            int cont=0;
            DinamicaMercados din=lista.get(i);
            tmp="\"recid\":"+id;
            lSubRegistros.add(tmp);
            tmp="\"codDane\":\""+din.getIdDepart()+"\"";
            lSubRegistros.add(tmp);
            tmp="\"departamento\":\""+din.getDepartamento()+"\"";
            lSubRegistros.add(tmp);
            for (int j = i; j < i+numAnios; j++) {
                din=lista.get(j);
                tmp=titulosColumnas.get(cont)+":"+din.getCompraventa();
                lSubRegistros.add(tmp);
                cont++;
                tmp=titulosColumnas.get(cont)+":"+din.getHipoteca();
                lSubRegistros.add(tmp);
                cont++;
                tmp=titulosColumnas.get(cont)+":"+din.getRemate();
                lSubRegistros.add(tmp);
                cont++;
                tmp=titulosColumnas.get(cont)+":"+din.getPermuta();
                lSubRegistros.add(tmp);
                cont++;
                tmp=titulosColumnas.get(cont)+":"+din.getEmbargo();
                lSubRegistros.add(tmp);
                cont++;
                tmp=titulosColumnas.get(cont)+":"+din.getPeso();
                lSubRegistros.add(tmp);
                cont++;
            }
            lRegistros.add("{"+addComma(lSubRegistros)+"}");
            lSubRegistros.clear();
            id++;
        }
        registros="\"records\":["+addComma(lRegistros)+"}";
        System.out.println(registros);
        System.out.println(column);
        System.out.println(columnGroup);
        return null;
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
}
