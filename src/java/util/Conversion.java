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
import obj.PlantillaConsultas;
import obj.PlantillaElementos;
import obj.Servicios;
import obj.Tablacontenido;

/**
 * Clase que se encarga de tomar los objetos y convertirlos a Json
 * @author cesar.solano
 */
public class Conversion {
    List<String> titulosColum=new ArrayList<>();
    
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
             return null;
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
    
    /**
     * Método que se encarga de crear el json de las propiedades de las tabla de información
     * que se le muestra al usuario.
     * @param filtro filtros seleccionados por el usuario
     * @param pc plantilla asociada a la consulta de usuario
     * @return String en formato json con las propiedades de la tabla de información
     */
    public String crearTablaPropiedadesJson(FiltroJson filtro, PlantillaConsultas pc){
        String tmp="";
        String columnGroup="";
        List<String> lColumnGroup=new ArrayList<>();
        List<String> lColumn=new ArrayList<>();
        List<String> field=new ArrayList<>();
        List<String> lColumns=new ArrayList<>();
        String column="";
        List<PlantillaElementos> pecg=pc.getColumnGroup();
        List<PlantillaElementos> peco=pc.getColumn();
        ArrayList<Integer> indicesColGrp=new ArrayList<>();
        List<String> tabla= new ArrayList<>();
        /////////columnGroups/////////////
        if (pecg.size()>0) {
            for (PlantillaElementos tColGrp : pecg) {
                //pecg.remove(tColGrp);
                if(!tColGrp.isFiltro()){
                    indicesColGrp=guardarIndicesTablaColumnGroup(indicesColGrp, tColGrp.getColumnGroup());
                    tmp="{\""+tColGrp.getAliasElemento()+"\":"+"\""+tColGrp.getValorElemento()+"\",";
                    for (PlantillaElementos tColGrp2 : pecg) {
                        if(tColGrp.getColumnGroup()==tColGrp2.getColumnGroup()){
                            tmp+="\""+tColGrp2.getAliasElemento()+"\":"+"\""+tColGrp2.getValorElemento()+"\"}";
                            //pecg.remove(tColGrp2);
                            break;
                        }
                    }
                    lColumnGroup.add(tmp);
                }else{
                    for (int i = 0; i < filtro.getAnios().length; i++) {
                        if(filtro.getAnios()[i].equals(tColGrp.getValorElemento())){
                            indicesColGrp=guardarIndicesTablaColumnGroup(indicesColGrp, tColGrp.getColumnGroup());
                            tmp="{\""+tColGrp.getAliasElemento()+"\":"+"\""+tColGrp.getValorElemento()+"\",";
                            for (PlantillaElementos tColGrp2 : pecg) {
                                if (tColGrp.getColumnGroup() == tColGrp2.getColumnGroup()) {
                                    tmp += "{\"" + tColGrp.getAliasElemento() + "\":" + "\"" + tColGrp.getValorElemento() + "\"";
                                    pecg.remove(tColGrp2);
                                }
                            }
                            lColumnGroup.add(tmp);
                        }
                    }
                }
                columnGroup="\"columnGroups\":["+addComma(lColumnGroup)+"]";
            }
        } 
        ////////columnas/////////////
        if(peco.size()>0){
            for (Integer ind : indicesColGrp) {
                 for (PlantillaElementos colTemp : peco) {
                     if(ind==colTemp.getColumnGroup()){
                         if(colTemp.getAliasElemento().equals("field")){
                             titulosColum.add(colTemp.getValorElemento());
                         }
                        tmp="\""+colTemp.getAliasElemento()+"\":"+"\""+colTemp.getValorElemento()+"\"";
                        field.add(tmp);
                        peco.remove(colTemp);
                     }
                 }
                 lColumns.add("{"+addComma(field)+"}");
            }
            column="\"columns\":["+addComma(lColumn)+"]";
        }
        ///////falta por hacer el json de las propiedades//////
        if (!columnGroup.equals("")) {
            tabla.add(columnGroup);
        }if (!column.equals("")) {
            tabla.add(column);
        }
        System.out.println(addComma(tabla));
        return addComma(tabla);
        
        

//        for (PlantillaElementos elemento : pecg) {
//                for (PlantillaElementos elemento2 : pecg) {
//                    if(elemento2.getColumnGroup()==1&&(elemento2.getOrden()==elemento.getOrden())&&(!elemento2.getAliasElemento().equals(elemento.getAliasElemento()))){
//                        tmp="{\""+elemento.getAliasElemento()+"\":"+"\""+elemento.getValorElemento()+"\",";
//                        tmp+="\""+elemento2.getAliasElemento()+"\":"+"\""+elemento2.getValorElemento()+"\"}";
//                        lColumnGroup.add(tmp);
//                    }
//                }
//        }
//        columnGroup="\"columnGroups\":["+addComma(lColumnGroup)+"]";
//        /////crear el json de las columnas
//        //obtener la cantidad de columnas 
//        int cantColumn=peco.get(peco.size()-1).getOrden();
//        for (int i = 1; i <= cantColumn; i++) {
//            for (PlantillaElementos pe : peco) {
//                if(pe.getOrden()==i){
//                    tmp="\""+pe.getAliasElemento()+"\":"+"\""+pe.getValorElemento()+"\"";
//                    field.add(tmp);
//                }
//            }
//            lColumns.add("{"+addComma(field)+"}");
//        }
//        column="\"columns\":["+addComma(lColumn)+"]";
//       ////crear el jsson para los registros con informacion
//       
    }
    
    /**
     * Método que encarga de almacenar en un arreglo la cantidad de columnGroups existentes
     * en la tabla de información
     * @param listaColumnGroups lista que almacena la cantidad de ColumnGroups
     * @param indiceColGrp valor que se desea verificar para saber si se debe almacenar o no
     * @return lista con los ColumnGroups actualizados
     */
    public ArrayList<Integer> guardarIndicesTablaColumnGroup(ArrayList<Integer> listaColumnGroups, int indiceColGrp){
        boolean existe=false;
        bucle1:
        for (Integer i : listaColumnGroups) {
            if(i==indiceColGrp){
                existe=true;
                break bucle1;
            }
        }
        if(!existe)
            listaColumnGroups.add(indiceColGrp);
        return listaColumnGroups;
    }
    
    /**
     * Método que se encarga de crear un json con los registros de información 
     * de una tabla de información
     * @param dm Objeto de tipo DinamicaMercados que almacena la información consultada
     * @return String en formato json con la información 
     */
    public String crearRegistrosTablaDinaMerc(List<DinamicaMercados> dm){
        String tmp="";
        String registros="";
        int id=1;
        int indT=0;
        List<String> lcolumn=new ArrayList<>();
        List<String> Lreg=new ArrayList<>();
        int numAnios=(titulosColum.size()-3)/6;
        for (int i = 0; i < dm.size(); i=i+numAnios) {
            tmp="\""+titulosColum.get(indT++)+"\":"+id;
            lcolumn.add(tmp);
            tmp="\""+titulosColum.get(indT++)+"\":"+"\""+dm.get(i).getIdDepart()+"\"";
            lcolumn.add(tmp);
            tmp="\""+titulosColum.get(indT++)+"\":"+"\""+dm.get(i).getDepartamento()+"\"";
            lcolumn.add(tmp);
            for (int j = i; j < j+numAnios; j++) {
                tmp="\""+titulosColum.get(indT++)+"\":"+dm.get(j).getCompraventa();
                lcolumn.add(tmp);
                tmp="\""+titulosColum.get(indT++)+"\":"+dm.get(j).getHipoteca();
                lcolumn.add(tmp);
                tmp="\""+titulosColum.get(indT++)+"\":"+dm.get(j).getRemate();
                lcolumn.add(tmp);
                tmp="\""+titulosColum.get(indT++)+"\":"+dm.get(j).getPermuta();
                lcolumn.add(tmp);
                tmp="\""+titulosColum.get(indT++)+"\":"+dm.get(j).getEmbargo();
                lcolumn.add(tmp);
                tmp="\""+titulosColum.get(indT++)+"\":"+dm.get(j).getPeso();
                lcolumn.add(tmp);
            }
            Lreg.add("\""+addComma(lcolumn)+"\"");
            id++;
        }
        registros="\"records\":["+addComma(Lreg)+"]";
        System.out.println(registros);
        return registros;
    }
}
