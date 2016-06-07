/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package db;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import obj.Actores;
import obj.Avaluos;
import obj.Capas;
import obj.Departamentos;
import obj.DinamicaMercados;
import obj.FiltroJson;
import obj.Filtros;
import obj.Menuconsultas;
import obj.Municipios;
import obj.Precios;
import obj.Areas;
import obj.DistritosRiego;
import obj.InfoyDocs;
import obj.Servicios;
import obj.Tablacontenido;
import util.Conversion;

/**
 * Clase encargada de generar las consultas a la base de datos 
 * 
 * @author Cesar.Solano
 */
public class Consultas {

    public Consultas() {
    }
    
    /**
     * Método que obtinen los departamentos con municipios 
     * @return Lista de tipo Departamentos
     */
    public List<Departamentos> getDepartamentosMun(){
        Conexion con=new Conexion();
        PreparedStatement ps=null;
        ResultSet rs=null;
        List<Departamentos> depar;
        depar=new ArrayList<>();
        Connection cn=con.getConexion();
        SentenciasBD sbd=new SentenciasBD();
        try{
            ps=cn.prepareStatement(sbd.getDEPARTAMENTOSMUN());
            rs=ps.executeQuery();
            while(rs.next()){
                Departamentos d = new Departamentos();
                int i=1;
                d.setCodigodane(rs.getString(i++));
                d.setDeptoid(rs.getShort(i++));
                d.setNomcorto(rs.getString(i++));
                d.setNombre(rs.getString(i++));
                d.setNomlargo(rs.getString(i++));
                d.setExt(rs.getString(i++));
                d.setMunicipiosCollection(getMunicipios(d.getDeptoid()));
                depar.add(d);
            }
        }catch(Exception e){
            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, e);
        }finally{
            con.cerrar(cn);
            con.cerrar(ps);
            con.cerrar(rs);
        }
        return depar;
    }
    
    /**
     * Método que obtiene los municipios de un departamento
     * @param idDepart id del departamento al cual se le consultan los municipios
     * @return Lista de tipo Municipios
     */
    public List<Municipios> getMunicipios(int idDepart){
        Conexion con=new Conexion();
        PreparedStatement ps=null;
        ResultSet rs=null;
        Connection cn=con.getConexion();
        List<Municipios> mun=new ArrayList<>();
        SentenciasBD sbd=new SentenciasBD();
        try{
            ps=cn.prepareStatement(sbd.getMUNICIPIOS());
            ps.setInt(1, idDepart);
            rs=ps.executeQuery();
            while(rs.next()){
                int i=1;
                Municipios m=new Municipios();
                m.setCodigodane(rs.getString(i++));
                m.setNombre(rs.getString(i++));
                m.setNomcorto(rs.getString(i++));
                m.setNomlargo(rs.getString(i++));
                m.setExt(rs.getString(i++));
                m.setDeptosfk(rs.getInt(i++));
                mun.add(m);
            }
        }catch(Exception e){
            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, e);
        }finally{
            con.cerrar(cn);
            con.cerrar(ps);
            con.cerrar(rs);
        }
        return mun;
    }
    
    /**
     * Método que obtiene todos los registros de la base de datos de la tabla tablacontenido 
     * @return Lista de tipo Tablacontenido
     */
    public List<Tablacontenido> getTabladeContenido(){
        Conexion con=new Conexion();
        Connection cn=con.getConexion();
        ResultSet rs=null;
        PreparedStatement ps=null;
        List<Tablacontenido> tablaContenido=new ArrayList<>();
        SentenciasBD sbd=new SentenciasBD();
        try{
            ps=cn.prepareStatement(sbd.getTABLADECONTENIDO());
            rs=ps.executeQuery();
            while (rs.next()) {
                int i=1;
                Tablacontenido tb=new Tablacontenido();
                tb.setTablacontenidoupraid(rs.getInt(i++));
                tb.setAlias(rs.getString(i++));
                tb.setDescripcion(rs.getString(i++));
                tb.setImagen(rs.getString(i++));
                tb.setNombre(rs.getString(i++));
                tb.setPalabrasclave(rs.getString(i++));
                tb.setOrden(rs.getInt(i++));
                tb.setDesplegado(rs.getBoolean(i++));
                tablaContenido.add(tb);
            }
        }catch(Exception e){
            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, e);
        }finally{
            con.cerrar(cn);
            con.cerrar(ps);
            con.cerrar(rs);
        }
        return tablaContenido;
    }
    
    /**
     * Método que obtiene la lista de servicios geográficos
     * @return Lista de tipo Servicios 
     */
    public List<Servicios> getServicios(){
        Conexion con=new Conexion();
        Connection cn=con.getConexion();
        ResultSet rs=null;
        PreparedStatement ps=null;
        List<Servicios> servicios=new ArrayList<>();
        SentenciasBD sbd=new SentenciasBD();
        try{
            ps=cn.prepareStatement(sbd.getSERVICIOS());
            rs=ps.executeQuery();
            while (rs.next()) {
                int i=1;
                Servicios s=new Servicios();
                s.setId(rs.getInt(i++));
                s.setAlias(rs.getString(i++));
                s.setTipoServidor(rs.getString(i++));
                s.setUrl(rs.getString(i++));
                s.setVersion(rs.getString(i++));
                s.setEstado(rs.getBoolean(i++));
                s.setImportado(rs.getBoolean(i++));
                s.setDescripcion(rs.getString(i++));
                s.setNombre(rs.getString(i++));
                s.setPalab_cl(rs.getString(i++));
                s.setOrden(rs.getInt(i++));
                s.setAcceso(rs.getInt(i++));
                servicios.add(s);
                
            }
        }catch(Exception e){
            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, e);
        }finally{
            con.cerrar(cn);
            con.cerrar(ps);
            con.cerrar(rs);
        }
        return servicios;
    }
    
    /**
     * Método que obtiene las capas de la base de datos 
     * @return Lista de tipo Capas
     */
    public List<Capas> getCapas(){
        Conexion con=new Conexion();
        Connection cn=con.getConexion();
        ResultSet rs=null;
        PreparedStatement ps=null;
        List<Capas> capas=new ArrayList<>();
        SentenciasBD sbd=new SentenciasBD();
        try{
            ps=cn.prepareStatement(sbd.getCAPAS());
            rs=ps.executeQuery();
            while (rs.next()) {
                int i=1;
                Capas c=new Capas();
                c.setId(rs.getInt(i++));
                c.setAlias(rs.getString(i++));
                c.setAliasgrupo(rs.getString(i++));
                c.setAliasservicio(rs.getString(i++));
                c.setEscmax(rs.getBigDecimal(i++));
                c.setEscmin(rs.getBigDecimal(i++));
                c.setFiltro(rs.getString(i++));
                c.setLimites(rs.getString(i++));
                c.setNombre_capa(rs.getString(i++));
                c.setOpacidad(rs.getBigDecimal(i++));
                c.setTitulo(rs.getString(i++));
                c.setEstado(rs.getBoolean(i++));
                c.setAutoident(rs.getBoolean(i++));
                c.setBase(rs.getBoolean(i++));
                c.setLeyenda_c(rs.getBoolean(i++));
                c.setIdentific(rs.getBoolean(i++));
                c.setOrdenable(rs.getBoolean(i++));
                c.setTransparente(rs.getBoolean(i++));
                c.setVisible(rs.getBoolean(i++));
                c.setAnio(rs.getInt(i++));
                c.setDescripcion(rs.getString(i++));
                c.setEscala(rs.getString(i++));
                c.setFuente(rs.getString(i++));
                c.setImagen(rs.getString(i++));
                c.setNombre(rs.getString(i++));
                c.setPalab_cl(rs.getString(i++));
                c.setOrden(rs.getInt(i++));
                c.setId_servicio(rs.getInt(i++));
                c.setAcceso(rs.getBigDecimal(i++));
                c.setFormato(rs.getString(i++));
                c.setCrs(rs.getString(i++));
                c.setTipo_serv(rs.getString(i++));
                c.setTipo(rs.getInt(i++));
                c.setVistaGeral(rs.getBoolean(i++));
                c.setAliasTablaContendio(rs.getString(i++));
                c.setsTipoAcceso(rs.getString(i++));
                c.setsTipoFormato(rs.getString(i++));
                c.setsTipocrs(rs.getString(i++));
                c.setsTipoCapa(rs.getString(i++));
                capas.add(c);
            }
        }catch(Exception e){
            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, e);
        }finally{
            con.cerrar(cn);
            con.cerrar(ps);
            con.cerrar(rs);
        }
        return capas;
        
    }
    
    /**
     * Método que obtiene el menu de consultas de la aplicación
     * @return Lista de tipo Menuconsultas
     */
    public List<Menuconsultas> getMenuConsultas(){
        Conexion con=new Conexion();
        Connection cn=con.getConexion();
        ResultSet rs=null;
        PreparedStatement ps=null;
        List<Menuconsultas> mConList=new ArrayList<>();
        SentenciasBD sbd=new SentenciasBD();
        try{
            ps=cn.prepareStatement(sbd.getMENUCONSULTAS());
            rs=ps.executeQuery();
            while (rs.next()) {
                int i=1;
                Menuconsultas m=new Menuconsultas();
                m.setMenuconsultaid(rs.getInt(i++));
                m.setAlias(rs.getString(i++));
                m.setNombre(rs.getString(i++));
                m.setTexto(rs.getString(i++));
                m.setDependede(rs.getInt(i++));
                m.setConsultable(rs.getBoolean(i++));
                mConList.add(m);
            }
        }catch(Exception e){
            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, e);
        }finally{
            con.cerrar(cn);
            con.cerrar(ps);
            con.cerrar(rs);
        }
        return mConList;
        
    }
    /**
     * Método que retorna la informacion de la consulta de precios de la base de datos 
     * @param codDepto string con el codigo dane del departamento que se desea consultar
     * @return retorna una lista de objetos del tipo Precios
     */
    public List<Areas> consultaPrecios(String codDepto){
        Conexion con=new Conexion();
        Connection cn=con.getConexion();
        ResultSet rs=null;
        PreparedStatement ps=null;
        List<Areas> areas=new ArrayList<>();
        SentenciasBD sbd=new SentenciasBD();
        try {
            ps=cn.prepareStatement(sbd.getPRECIOS());
            ps.setString(1, codDepto);
            System.out.println(ps.toString());
            rs=ps.executeQuery();
            while (rs.next()) { 
                int i=1;
                Areas a =new Areas();
                a.setCodigoDane(rs.getString(i++));
                a.setDepartamento(rs.getString(i++));
                a.setMunicipio(rs.getString(i++));
                a.setArea(rs.getBigDecimal(i++));
                a.setTipo(rs.getString(i++));
                areas.add(a);
            }
        } catch (SQLException e) {
            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, e);
        }finally{
            con.cerrar(cn);
            con.cerrar(ps);
            con.cerrar(rs);
        }
        return areas;
    }
    /**
     * Método que retorna la informacion de dinamicas de mercado de todos los años
     * @param filtro Objeto que almacena los filtros seleccionados para la consulta
     * @return lista de tipo DinamicaMercados
     */
    public List<DinamicaMercados> consultaDinamicaMerc(FiltroJson filtro){
        String clausula=filtrosAniosToString(filtro);
        clausula+=" order by departamento, anio asc";
        Conexion con=new Conexion();
        Connection cn=con.getConexion();
        ResultSet rs=null;
        PreparedStatement ps=null;
        List<DinamicaMercados> Dinam=new ArrayList<>();
        SentenciasBD sbd=new SentenciasBD();
        try {
            ps=cn.prepareStatement(sbd.getDINAMICAMERCADO());
            ps.setString(1, clausula);
            System.out.println(ps.toString());
            rs=ps.executeQuery();
            while (rs.next()) {                
              int i=0;
              String[] row=rs.getString(1).split(",");
              DinamicaMercados dm=new DinamicaMercados();
              dm.setIdTabla(row[i++].replace("(", ""));
              dm.setIdDepart(row[i++]);
              dm.setDepartamento(row[i++]);
              dm.setAnio(Integer.parseInt(row[i++]));
              dm.setCompraventa(Double.parseDouble(row[i++]));
              dm.setHipoteca(Double.parseDouble(row[i++]));
              dm.setRemate(Double.parseDouble(row[i++]));
              dm.setPermuta(Double.parseDouble(row[i++]));
              dm.setEmbargo(Double.parseDouble(row[i++]));
              dm.setPeso(Double.parseDouble(row[i++].replace(")", "")));
              Dinam.add(dm);
            }
        } catch (SQLException e) {
            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, e);
        }finally{
            con.cerrar(cn);
            con.cerrar(ps);
            con.cerrar(rs);
        }
        System.out.println(Dinam.size());
        return Dinam;
    }
    /**
     * Método que retorna la información de avaluos 
     * @param filtro parametro where de la sentencia sql
     * @return lista de tipo Avaluos con la información 
     */
//    public List<Avaluos> consultaAvaluos(FiltroJson filtro){
//        String clausula=filtrosAniosToString(filtro);
//        Conexion con=new Conexion();
//        Connection cn=con.getConexion();
//        ResultSet rs=null;
//        PreparedStatement ps=null;
//        List<Avaluos> avaluos=new ArrayList<>();
//        try {
//            if(clausula.equals(""))
//                ps=cn.prepareStatement("SELECT mercado_tierras_rurales.funcion_parametros_avaluos_catastrales('');");
//            else
//                ps=cn.prepareStatement("SELECT mercado_tierras_rurales.funcion_parametros_avaluos_catastrales('"+clausula+"');");
//            rs=ps.executeQuery();int j=0;
//            while (rs.next()) {                
//                int i=0;
//                String[] row=rs.getString(1).split(",");
//                Avaluos ava=new Avaluos();
//                ava.setId(row[i++]);
//                ava.setAno(Integer.parseInt(row[i++]));
//                ava.setIdDepart(Integer.parseInt(row[i++]));
//                ava.setDepartamento(row[i++]);
//                ava.setIdMun(Integer.parseInt(row[i++]));
//                ava.setMunicipio(row[i++]);
//                ava.setRango(row[i++]);
//                ava.setArea(row[i++]);
//                avaluos.add(ava);
//                System.out.println(i++);
//            }
//        } catch (SQLException e) {
//            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, e);
//        }finally{
//            con.cerrar(cn);
//            con.cerrar(ps);
//            con.cerrar(rs);
//        }
//        System.out.println(avaluos.size());
//        return avaluos;
//    }
    
    /**
     * Método que obtiene los filtros de las diferentes tablas asociados a una 
     * consulta a traves del alias de la consulta
     * @param alias campo de alias en la tabla de menuconsultas
     * @return lista de tipo Filtros con todos los registros encontrados 
     */
    public List<Filtros> consultaFiltros(String alias){
        Conexion con=new Conexion();
        Connection cn=con.getConexion();
        ResultSet rs=null;
        PreparedStatement ps=null;
        List<Filtros> filtros=new ArrayList<>();
        SentenciasBD sentenciasBD=new SentenciasBD();
        try {
            ps=cn.prepareStatement(sentenciasBD.getCONSULTAFILTROS());
            ps.setString(1, alias);
            ps.setString(2, alias);
            ps.setString(3, alias);
            rs=ps.executeQuery();
            while (rs.next()) {          
                int i=1;
                Filtros f=new Filtros();
                f.setAliasTipo(rs.getString(i++));
                f.setNombreTipo(rs.getString(i++));
                f.setTextoTipo(rs.getString(i++));
                f.setAliasSubTipo(rs.getString(i++));
                f.setNombreSubTipo(rs.getString(i++));
                f.setValorFiltro(rs.getString(i++));
                f.setTipoElemento(rs.getString(i++));
                f.setTipoElemtoPadre(rs.getString(i++));
                filtros.add(f);
            }
        } catch (SQLException e) {
            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, e);
        }finally{
            con.cerrar(cn);
            con.cerrar(ps);
            con.cerrar(rs);
        }
        return filtros;
    }
    
    /**
     * Método que convierte un objeto de tipo FiltroJson en las clausulas de la sentencia 
     * where en sql para consultar la tabla de avaluos catastrales.
     * @param filtro objeto de tipo FiltroJson con los filtros seleccionados por los usuarios.
     * @return retorna un String con la clausula where y todos los filtros para ser adicionado a una consulta
     */
    public String filtrosAniosToString(FiltroJson filtro){
        List<String> lista=new ArrayList<>();
        String clausula="";
        if(filtro.getAnios()!=null){
            for (String anio:filtro.getAnios()) {
                clausula="anio='"+anio+"'";
                lista.add(clausula);
            }
        }
        Conversion conv=new Conversion();
        if (lista.size()>0) {
            clausula = "where "+conv.addOr(lista);
        }
        return clausula;
    }
    /**
     * Método que consulta la tabla de contenido asociada a una consulta de usuario por 
     * medio del alias de la consulta
     * @param alias alias de la consulta de usuario en la base de datos
     * @return objeto de tipo tablaContenido asociado a la consulta
     */
    public Tablacontenido consultarTablaContenidoporMenuConsulta(String alias){
        Tablacontenido tablacontenido=new Tablacontenido();
        SentenciasBD sbd=new SentenciasBD();
        Conexion con=new Conexion();
        Connection cn=con.getConexion();
        ResultSet rs=null;
        PreparedStatement ps=null;
        try {
            ps=cn.prepareStatement(sbd.getTABLACONTENIDO_MENUCONSULTAS());
            ps.setString(1, alias);
            rs=ps.executeQuery();
            int i=1;
            while (rs.next()) {
                tablacontenido.setAlias(rs.getString(i++));
                tablacontenido.setNombre(rs.getString(i++));
                tablacontenido.setPalabrasclave(rs.getString(i++));
                i=1;
            }
        } catch (Exception e) {
            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, e);
        }finally{
            con.cerrar(cn);
            con.cerrar(ps);
            con.cerrar(rs);
        }
        return tablacontenido;
    }
    
    /**
     * Método que permite consultar las capas asociadas a un consuilta de usuario
     * por medio del alias de la consulta
     * @param alias alias de la consulta de usuario en la base de datos
     * @return lista de tipo Capas, con las capas asociadas a la consulta
     */
    public List<Capas> consultarCapasporMenuConsulta(String alias){
        List<Capas> lista=new ArrayList<>();
        SentenciasBD sbd=new SentenciasBD();
        Conexion con=new Conexion();
        Connection cn=con.getConexion();
        ResultSet rs=null;
        PreparedStatement ps=null;
        try {
            ps=cn.prepareStatement(sbd.getCAPAS_MENUCONSULTAS());
            ps.setString(1, alias);
            rs=ps.executeQuery();
            int i=1;
            while (rs.next()) {
                Capas capas=new Capas();
                capas.setAlias(rs.getString(i++));
                capas.setAliasgrupo(rs.getString(i++));
                capas.setAliasservicio(rs.getString(i++));
                capas.setEscmax(rs.getBigDecimal(i++));
                capas.setFiltro(rs.getString(i++));
                capas.setNombre_capa(rs.getString(i++));
                capas.setOpacidad(rs.getBigDecimal(i++));
                capas.setCrs(rs.getString(i++));
                capas.setAnio(rs.getInt(i++));
                capas.setFuente(rs.getString(i++));
                capas.setNombre(rs.getString(i++));
                capas.setVisible(rs.getBoolean(i++));
                capas.setIdentific(rs.getBoolean(i++));
                capas.setLeyenda_c(rs.getBoolean(i++));
                capas.setAutoident(rs.getBoolean(i++));
                capas.setTitulo(rs.getString(i++));
                lista.add(capas);
                i=1;
            }
        } catch (Exception e) {
            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, e);
        }finally{
            con.cerrar(cn);
            con.cerrar(ps);
            con.cerrar(rs);
        }
        return lista;
    }
    
    /**
     * Método que consulta los servicios geográficas asociados a una consulta de
     * usuario, por medio del alias de la consulta
     * @param alias alias de la consulta de usuario en la base de datos
     * @return lista de tipo Servicios con los servicios asociados a la consulta seleccionada
     */
    public List<Servicios> consultarServicioporMenuConsulta(String alias){
        Servicios servicios=new Servicios();
        SentenciasBD sbd=new SentenciasBD();
        Conexion con=new Conexion();
        Connection cn=con.getConexion();
        ResultSet rs=null;
        PreparedStatement ps=null;
        List<Servicios> list=new ArrayList<>();
        try {
            ps=cn.prepareStatement(sbd.getSERVICIOS_MENUCONSULTAS());
            ps.setString(1, alias);
            rs=ps.executeQuery();
            int i=1;
            while (rs.next()) {
                servicios.setAlias(rs.getString(i++));
                servicios.setTipoServidor(rs.getString(i++));
                servicios.setUrl(rs.getString(i++));
                servicios.setImportado(rs.getBoolean(i++));
                servicios.setNombre(rs.getString(i++));
                servicios.setPalab_cl(rs.getString(i++));
                list.add(servicios);
                i=1;
            }
        } catch (Exception e) {
            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, e);
        }finally{
            con.cerrar(cn);
            con.cerrar(ps);
            con.cerrar(rs);
        }
        return list;
    }
    
    /**
     * @deprecated 
     * @param filtro
     * @return 
     */
    public String filtrosEntidadesToString(FiltroJson filtro){
        String clausula="";
        List<String> lista=new ArrayList<>();
        Conversion conv=new Conversion();
        if(filtro.getDeps()!=null){
            for (String dep:filtro.getDeps()) {
                clausula="deptoid='"+dep+"'";
                lista.add(clausula);
            }
        }
        if(filtro.getMuns()!=null){
            for (int i = 0; i < filtro.getMuns().length; i++) {
                clausula="municipioid="+filtro.getMuns()[i];
            }
            
        }
        if (lista.size()>0) {
            clausula = "where "+conv.addOr(lista);
        }
        return clausula;
    }
    
    /**
     * Método que que se encarga de consultar la información de la cancha de restricciones agropecuarias
     * de la UPRA
     * @return lista de tipo Areas donde se encuentra la informacion de las restricciones por departamento.
     */
    public List<Areas> consultaRestricciones() {
        Conexion con = new Conexion();
        Connection cn = con.getConexion();
        ResultSet rs = null;
        PreparedStatement ps = null;
        List<Areas> list = new ArrayList<>();
        SentenciasBD sbd = new SentenciasBD();
        try {
            ps = cn.prepareStatement(sbd.getRESTRICCIONES());
            System.out.println(ps.toString());
            rs = ps.executeQuery();
            Areas r = new Areas();
            int cont = 0;
            while (rs.next()) {
                int i = 1;
                r.setCodigoDane(rs.getString(i++));
                String tipoZona = rs.getString(i++);
                r.setDepartamento(rs.getString(i++));
                switch (tipoZona) {
                    case "Condicionante": {
                        r.setCondicionante(rs.getBigDecimal(i++));
                        break;
                    }
                    case "Exclusión": {
                        r.setExclusion(rs.getBigDecimal(i++));
                        break;
                    }
                    case "Sin Restricción": {
                        r.setSinRestriccion(rs.getBigDecimal(i++));
                        break;
                    }
                }
                r.setAreaDepto(rs.getBigDecimal(i++));
                cont++;
                if (cont % 3 == 0) {
                    list.add(r);
                    r = new Areas();
                }
            }

        } catch (SQLException e) {
            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            con.cerrar(cn);
            con.cerrar(ps);
            con.cerrar(rs);
        }
        return list;
    }
    
    /**
     * Método que consulta la información de la cancha de exclusiones de la UPRA
     * @return lista de tipo Areas con la información de las exclusiones por departamento
     */
    public List<Areas> consultaExclusiones() {
        Conexion con = new Conexion();
        Connection cn = con.getConexion();
        ResultSet rs = null;
        PreparedStatement ps = null;
        List<Areas> list = new ArrayList<>();
        SentenciasBD sbd = new SentenciasBD();
        String cod="91";//codigo del primer departamento
        try {
            ps = cn.prepareStatement(sbd.getEXCLUSIONES());
            System.out.println(ps.toString());
            rs = ps.executeQuery();
            Areas r = new Areas();
            //int cont = 0;
            while (rs.next()) {
                int i = 1;
                if(!cod.equals(rs.getString(i))){
                    list.add(r);
                    r=new Areas();
                }
                r.setCodigoDane(rs.getString(i++));
                cod=r.getCodigoDane();
                String tipoZona = rs.getString(i++);
                r.setDepartamento(rs.getString(i++));
                switch (tipoZona) {
                    case "Excluidos": {
                        r.setAreaExcl(rs.getBigDecimal(i++));
                        r.setExclusion(rs.getBigDecimal(i++));
                        break;
                    }
                    case "Incluidos": {
                        r.setAreaIncl(rs.getBigDecimal(i++));
                        r.setIncluidas(rs.getBigDecimal(i++));
                        break;
                    }
                    case "Condicionante": {//en la aplicacion web se le dice restringido
                        r.setAreaCond(rs.getBigDecimal(i++));
                        r.setCondicionante(rs.getBigDecimal(i++));
                        break;
                    }
                }
                r.setAreaDepto(rs.getBigDecimal(i++));
            }

        } catch (SQLException e) {
            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            con.cerrar(cn);
            con.cerrar(ps);
            con.cerrar(rs);
        }
        return list;
    }
    
    /**
     * Método que se encarga de consultar los datos de documentos e información adicional
     * de las consultas de usuario de acuerdo al alias suministrado
     * @param alias alias de la consulta en la base de datos
     * @param tipo true: si es para consultar documentos, false: para consultar información adicional
     * @return objeto de tipo InfoyDocs con los documentos o información adicional según se elija
     */
    public List<InfoyDocs> consultarInfoyDocs(String alias, boolean tipo){
        Conexion con=new Conexion();
        Connection cn=con.getConexion();
        ResultSet rs=null;
        PreparedStatement ps=null;
        List<InfoyDocs> list=new ArrayList<>();
        SentenciasBD sbd=new SentenciasBD();
        try {
            ps=cn.prepareStatement(sbd.getDOCSINFO());
            ps.setString(1, alias);
            ps.setBoolean(2, tipo);
            rs=ps.executeQuery();
            while (rs.next()) {                
              int i=1;
              InfoyDocs docs=new InfoyDocs();
              docs.setTitulo(rs.getString(i++));
              docs.setDescripcion(rs.getString(i++));
                if (tipo) {
                    docs.setNombre(rs.getString(i++));
                }
              list.add(docs);
            }
        } catch (SQLException e) {
            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, e);
        }finally{
            con.cerrar(cn);
            con.cerrar(ps);
            con.cerrar(rs);
        }
        return list;
    }
    
    /**
     * Método encargado de consultar la información de la vista del indice de 
     * fracionamiento de la propiedad
     * @return lista de tipo areas con la informacion de fracionamiento en cada departamento
     */
    public List<Areas> consultarIndiceFraccionamiento(){
        Conexion con=new Conexion();
        Connection cn=con.getConexion();
        ResultSet rs=null;
        PreparedStatement ps=null;
        List<Areas> list=new ArrayList<>();
        SentenciasBD sbd=new SentenciasBD();
        try {
             ps=cn.prepareStatement(sbd.getIFPR());
             rs=ps.executeQuery();
            while (rs.next()) {                
              int i=1;
              Areas a = new Areas();
              a.setCodigoDane(rs.getString(i++));
              a.setDepartamento(rs.getString(i++));
              a.setArea(rs.getBigDecimal(i++));
              list.add(a);
            }
        } catch (SQLException e) {
            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, e);
        }finally{
            con.cerrar(cn);
            con.cerrar(ps);
            con.cerrar(rs);
        }
        return list;
    } 
    
    /**
     * Método que se encarga de contar cuantos distritos de riego existen 
     * para la escala elegida por el parametro tipo
     * @param tipo 1 para distritos pequeños, 2 para medianos, 3 para grandes
     * @return lista de tipo DistritosRiego con el conteo de cuantos distritos de riego por departamento
     * existen para la escala elegida
     */
    public List<DistritosRiego> conteoDistritosRiego(int tipo){
        Conexion con=new Conexion();
        Connection cn=con.getConexion();
        ResultSet rs=null;
        PreparedStatement ps=null;
        List<DistritosRiego> list=new ArrayList<>();
        SentenciasBD sbd=new SentenciasBD();
        final int peq=1;
        final int med=2;
        final int gran=3;
        try {
            switch(tipo){
                case peq:{
                    ps=cn.prepareStatement(sbd.getDISTRITOPEQ());
                    break;
                }
                case med:{
                    ps=cn.prepareStatement(sbd.getDISTRITOSMED());
                    break;
                }
                case gran:{
                    ps=cn.prepareStatement(sbd.getDISTRITOSGRAN());
                    break;
                }
            }
            rs=ps.executeQuery();
            while (rs.next()) {                
              int i=1;
              DistritosRiego dr = new DistritosRiego();
              dr.setCantDist(rs.getInt(i++));
              dr.setDepto(rs.getString(i++));
              list.add(dr);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, e);
        }finally{
            con.cerrar(cn);
            con.cerrar(ps);
            con.cerrar(rs);
        }
        return list;
    }
    
    /**
     * Método que se encarga de consultar tioda la informacion de los distritos de riego existentes en el país
     * @return lista de tipo DistritosRiego con toda la información de los distritos de riegos
     */
    public List<DistritosRiego> datosDistritosRiego(){
        Conexion con=new Conexion();
        Connection cn=con.getConexion();
        ResultSet rs=null;
        PreparedStatement ps=null;
        List<DistritosRiego> list=new ArrayList<>();
        SentenciasBD sbd=new SentenciasBD();
        try {
            ps=cn.prepareStatement(sbd.getDISTRITOSDATS());
            rs=ps.executeQuery();
            while (rs.next()) {                
              int i=1;
              DistritosRiego dr = new DistritosRiego();
              dr.setIdUpra(rs.getString(i++));
              dr.setIdIncoder(rs.getInt(i++));
              dr.setNomDistrito(rs.getString(i++));
              dr.setCodDaneDepto(rs.getString(i++));
              dr.setDepto(rs.getString(i++));
              dr.setCodDaneMpio(rs.getString(i++));
              dr.setMpio(rs.getString(i++));
              dr.setVereda(rs.getString(i++));
              dr.setAreaNeta(rs.getBigDecimal(i++));
              dr.setAreaBruta(rs.getBigDecimal(i++));
              dr.setEscala(rs.getString(i++));
              dr.setCoor1(rs.getString(i++));
              dr.setCoor2(rs.getString(i++));
              dr.setCoor3(rs.getString(i++));
              dr.setCoor4(rs.getString(i++));
              dr.setAltitud(rs.getBigDecimal(i++));
              dr.setTipo(rs.getString(i++));
              dr.setEtapa(rs.getString(i++));
              dr.setFunciona(rs.getString(i++));
              dr.setUsuarios(rs.getInt(i++));
              dr.setInversion(rs.getInt(i++));
              dr.setAdmin(rs.getString(i++));
              dr.setAsoUsuarios(rs.getString(i++));
              dr.setResolucion(rs.getString(i++));
              dr.setRepresentante(rs.getString(i++));
              dr.setTel(rs.getString(i++));
              dr.setEmail(rs.getString(i++));
              dr.setDir(rs.getString(i++));
              dr.setCultivos(rs.getString(i++));
              dr.setCar(rs.getString(i++));
              dr.setFuenteHid(rs.getString(i++));
              dr.setCaptacion(rs.getString(i++));
              dr.setConcesion(rs.getString(i++));
              dr.setSzh(rs.getString(i++));
              dr.setZh(rs.getString(i++));
              dr.setGeoref(rs.getString(i++));
              dr.setIdPol(rs.getString(i++));
              dr.setObser(rs.getString(i++));
              list.add(dr);
            }
        } catch (SQLException e) {
            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, e);
        }finally{
            con.cerrar(cn);
            con.cerrar(ps);
            con.cerrar(rs);
        }
        return list;
    }
    
    /**
     * Metodo que obtiene la lista de departamentos
     * @return Lista de departamentos de Colombia
     */
    public List<String> consultarDepartamentos(){
        Conexion con=new Conexion();
        Connection cn=con.getConexion();
        ResultSet rs=null;
        PreparedStatement ps=null;
        List<String> list=new ArrayList<>();
        SentenciasBD sbd=new SentenciasBD();
        try {
             ps=cn.prepareStatement(sbd.getDEPARTAMENTOS());
             rs=ps.executeQuery();
            while (rs.next()) {                
              int i=1;
              list.add(rs.getString(i++));
            }
        } catch (SQLException e) {
            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, e);
        }finally{
            con.cerrar(cn);
            con.cerrar(ps);
            con.cerrar(rs);
        }
        return list;
    }
    
    /**
     * Método que obtiene la información del indicador 
     * de concentración de la propiedad 
     * @return Lista de tipo Areas con la información del indicador
     */
    public List<Areas> consultarIndicadorConcentracion(){
        Conexion con=new Conexion();
        Connection cn=con.getConexion();
        ResultSet rs=null;
        PreparedStatement ps=null;
        List<Areas> list=new ArrayList<>();
        SentenciasBD sbd=new SentenciasBD();
        try {
             ps=cn.prepareStatement(sbd.getINDICE_CONCENTRACION());
             rs=ps.executeQuery();
            while (rs.next()) {                
              int i=1;
              Areas a = new Areas();
              a.setCodigoDane(rs.getString(i++));
              a.setDepartamento(rs.getString(i++));
              a.setArea(rs.getBigDecimal(i++));
              list.add(a);
            }
        } catch (SQLException e) {
            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, e);
        }finally{
            con.cerrar(cn);
            con.cerrar(ps);
            con.cerrar(rs);
        }
        return list;
    }
    
    /**
     * Método que se encarga de obtener de la base de datos la información de cualquiera de los indicadores
     * de superficie 
     * @param ind Indice que hace referencia al indicador que se quiere consultar.
     * 1: SUP_SIN_REST_LEG, 2: SUP_EXC_LEG, 3: SUP_USO_COND, 4: SUP_SOBRE, 5: SUP_SUB, 6: SUP_SIN_CONFLICTO 
     * @return lista de Tipo Areas con la información del indicador seleccionado
     */
    public List<Areas> consultarIndicadoresSuperficie(int ind){
        Conexion con=new Conexion();
        Connection cn=con.getConexion();
        ResultSet rs=null;
        PreparedStatement ps=null;
        List<Areas> list=new ArrayList<>();
        SentenciasBD sbd=new SentenciasBD();
        try {
            switch(ind){
                case 1:
                    ps=cn.prepareStatement(sbd.getSUP_SIN_REST_LEG());
                    break;
                case 2:
                    ps=cn.prepareStatement(sbd.getSUP_EXC_LEG());
                    break;
                case 3: 
                    ps=cn.prepareStatement(sbd.getSUP_USO_COND());
                    break;
                case 4:
                    ps=cn.prepareStatement(sbd.getSUP_SOBRE());
                    break;
                case 5:
                    ps=cn.prepareStatement(sbd.getSUP_SUB());
                    break;
                case 6:
                    ps=cn.prepareStatement(sbd.getSUP_SIN_CONFLICTO());
                    break;
        }
             rs=ps.executeQuery();
            while (rs.next()) {                
              int i=1;
              Areas a = new Areas();
              a.setCodigoDane(rs.getString(i++));
              a.setDepartamento(rs.getString(i++));
              a.setArea(rs.getBigDecimal(i++));
              a.setTipo(rs.getString(i++));
              list.add(a);
            }
        } catch (SQLException e) {
            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, e);
        }finally{
            con.cerrar(cn);
            con.cerrar(ps);
            con.cerrar(rs);
        }
        return list;
    }
   
    /**
     * Método que se encarga de consultar la información de las áreas potenciales de riego y drenaje
     * @param filtro consulta las areas potenciales segun el filtro: riego, drenaje
     * @return Lista de tipo Areas con la información de los departamentos con areas potenciales ya sea 
     * de riego o drenaje, según se haya elejido
     */
     public List<Areas> consultarAreasPotenciales(String filtro){
        Conexion con=new Conexion();
        Connection cn=con.getConexion();
        ResultSet rs=null;
        PreparedStatement ps=null;
        List<Areas> list=new ArrayList<>();
        SentenciasBD sbd=new SentenciasBD();
        try {
             ps=cn.prepareStatement(sbd.getAREAS_POTENCIALES());
             ps.setString(1, "%"+filtro+"%");
             rs=ps.executeQuery();
            while (rs.next()) {                
              int i=1;
              Areas a = new Areas();
              a.setCodigoDane(rs.getString(i++));
              a.setDepartamento(rs.getString(i++));
              a.setArea(rs.getBigDecimal(i++));
              list.add(a);
            }
        } catch (SQLException e) {
            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, e);
        }finally{
            con.cerrar(cn);
            con.cerrar(ps);
            con.cerrar(rs);
        }
        return list;
    }
    
     /**
      * Método que consulta la información de la la aptitud para la planeación forestal
      * @return lista con la información de la planeación forestal de todos los departamentos
      */
      public List<Areas> consultarAptitudForestal(){
        Conexion con=new Conexion();
        Connection cn=con.getConexion();
        ResultSet rs=null;
        PreparedStatement ps=null;
        List<Areas> list=new ArrayList<>();
        SentenciasBD sbd=new SentenciasBD();
        try {
             ps=cn.prepareStatement(sbd.getAPTITUD_FORESTAL());
             rs=ps.executeQuery();
            while (rs.next()) {                
              int i=1;
              Areas a = new Areas();
              a.setCodigoDane(rs.getString(i++));
              a.setDepartamento(rs.getString(i++));
              BigDecimal alt=rs.getBigDecimal(i++);
              BigDecimal med=rs.getBigDecimal(i++);
              BigDecimal baj=rs.getBigDecimal(i++);
              BigDecimal noap=rs.getBigDecimal(i++);
              a.setZonaAlta((alt==null)?BigDecimal.ZERO:alt);
              a.setZonaMedia((med==null)?BigDecimal.ZERO:med);
              a.setZonaBaja((baj==null)?BigDecimal.ZERO:baj);
              a.setZonaNoApta((noap==null)?BigDecimal.ZERO:noap);
              list.add(a);
            }
        } catch (SQLException e) {
            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, e);
        }finally{
            con.cerrar(cn);
            con.cerrar(ps);
            con.cerrar(rs);
        }
        return list;
    }
     /**
      * Método que consulta la información de la tabla de presencia de actores involucrados
      * ya sea por departamentos u organizaciones 
      * @param filtro permite seleccionar si se desea consultar la informacion por departamento u organizacion o consolidado
      * 1: departamentos 2: organizaciones 3: resumen organizaciones
      * @return lista de tipo Actores con la informacion de la presencia de actores según el filtro seleccionado
      */
      public List<Actores> consultarPresenciaActores(int filtro){
        Conexion con=new Conexion();
        Connection cn=con.getConexion();
        ResultSet rs=null;
        PreparedStatement ps=null;
        List<Actores> list=new ArrayList<>();
        SentenciasBD sbd=new SentenciasBD();
        try {
            if(filtro==1)//
                ps=cn.prepareStatement(sbd.getACTORES_DEPARTAMENTOS());
            if(filtro==2)
                ps=cn.prepareStatement(sbd.getACTORES_ORGANIZACIONES());
            if(filtro==3)
                ps=cn.prepareStatement(sbd.getACTORES_RESUMEN());
             rs=ps.executeQuery();
            while (rs.next()) {                
              int i=1;
              Actores a = new Actores();
              if(filtro==1){
                a.setCodDane(rs.getString(i++));
                a.setDepto(rs.getString(i++));
                a.setNumOrg(rs.getInt(i++));
                a.setCategoria(rs.getString(i++));
              }
              if(filtro==2){
                a.setOrg(rs.getString(i++));
                a.setLocalizacion(rs.getString(i++));
                a.setDepto(rs.getString(i++));
                a.setMunpio(rs.getString(i++));
              }
              if(filtro==3){
                  a.setDepto(rs.getString(i++));
                  a.setNumOrg(rs.getInt(i++));
              }
              list.add(a);
            }
        } catch (SQLException e) {
            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, e);
        }finally{
            con.cerrar(cn);
            con.cerrar(ps);
            con.cerrar(rs);
        }
        return list;
    }
}



