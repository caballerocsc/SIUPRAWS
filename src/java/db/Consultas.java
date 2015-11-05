/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import obj.Avaluos;
import obj.Capas;
import obj.Departamentos;
import obj.DinamicaMercados;
import obj.Filtros;
import obj.Menuconsultas;
import obj.Municipios;
import obj.Precios;
import obj.Servicios;
import obj.Tablacontenido;
import obj.TipoTransaccion;

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
        List<Departamentos> depar=new ArrayList<>();
        Connection cn=con.getConexion();
        try{
            ps=cn.prepareStatement("SELECT d.codigodane, d.deptoid, d.nomcorto, d.nombre, d.nomlargo, d.ext FROM carto_basica.departamentos d");
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
        }catch(SQLException e){
            e.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
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
        try{
            ps=cn.prepareStatement("SELECT m.codigodane, m.nombre, m.nomcorto, m.nomlargo, m.ext, m.deptosfk  FROM carto_basica.municipios m where m.deptosfk = ?");
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
        }catch(SQLException e){
            e.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            con.cerrar(cn);
            con.cerrar(ps);
            con.cerrar(rs);
        }
        return mun;
    }
    
    /**
     * Método que obtiene la tabla de contenido de la aplicación
     * @return Lista de tipo Tablacontenido
     */
    public List<Tablacontenido> getTabladeContenido(){
        Conexion con=new Conexion();
        Connection cn=con.getConexion();
        ResultSet rs=null;
        PreparedStatement ps=null;
        List<Tablacontenido> tablaContenido=new ArrayList<>();
        try{
            ps=cn.prepareStatement("SELECT tablacontenidoupraid, alias, descripcion, imagen, nombre, palabrasclave," +
                "orden, desplegado  FROM adminsiupra.tablacontenido order by orden;");
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
        }catch(SQLException e){
            e.printStackTrace();
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
        try{
            ps=cn.prepareStatement("SELECT serviciosupraid, alias, tiposervidor, url, versionservicio," +
            "estado, importado, descripcion, nombre, palabrasclave, orden, " +
            "accesofkid FROM adminsiupra.servicios;");
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
        }catch(SQLException e){
            e.printStackTrace();
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
        try{
            ps=cn.prepareStatement("SELECT C.capasupraid, C.alias, C.aliasgrupo, C.aliasservicio, C.resolucionmax, \n" +
"       C.resolucionmin, C.filtro, C.limites, C.nombrecapa, C.opacidad, C.titulo, \n" +
"       C.estado, C.autoidentificable, C.base, C.leyendacargada, C.identificable, \n" +
"       C.ordenable, C.transparente, C.visible, C.anio, C.descripcion, C.escala, \n" +
"       C.fuente, C.imagen, C.nombre, C.palabrasclave, C.orden, C.servicioid, C.accesofkid, \n" +
"       C.formatofkid, C.crsfkid, C.tiposerviciofkid, C.tipocapafkid, C.vistageneral,tc.alias,\n" +
"       (select texto from dominios where dominioid=C.accesofkid),\n" +
"       (select texto from dominios where dominioid=C.formatofkid),\n" +
"       (select texto from dominios where dominioid=C.crsfkid),\n" +
"       (select texto from dominios where dominioid=C.tipocapafkid)\n" +
"       FROM adminsiupra.capas C inner join adminsiupra.tablacontenido_capas tcc on C.capasupraid=tcc.capasupraid\n" +
"       inner join adminsiupra.tablacontenido tc on tcc.tablacontenidoupraid=tc.tablacontenidoupraid;");
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
        }catch(SQLException e){
            e.printStackTrace();
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
        try{
            ps=cn.prepareStatement("SELECT menuconsultaid, alias, nombre, texto, dependede, consultable\n" +
            "  FROM adminsiupra.menuconsultas;");
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
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            con.cerrar(cn);
            con.cerrar(ps);
            con.cerrar(rs);
        }
        return mConList;
        
    }
    /**
     * Método que retorna la informacion de la consulta de precios de la base de datos 
     * @param filtro parametro where de la sentencia sql 
     * @return retorna una lista de objetos del tipo Precios
     */
    public List<Precios> consultaPrecios(String filtro){
        Conexion con=new Conexion();
        Connection cn=con.getConexion();
        ResultSet rs=null;
        PreparedStatement ps=null;
        List<Precios> precios=new ArrayList<>();
        try {
            if(filtro=="")
                ps=cn.prepareStatement("SELECT mercado_tierras_rurales.funcion_parametros_precios('');");
            else
                ps=cn.prepareStatement("SELECT mercado_tierras_rurales.funcion_parametros_precios('where departamento=''"+filtro+"''');");
            System.out.println(ps.toString());
            rs=ps.executeQuery();
            while (rs.next()) {                
              int i=0;
              //System.out.println(rs.getString(i++));
              String[] row=rs.getString(1).split(",");
              Precios p=new Precios();
              p.setId(row[i++]);
              p.setNombre(row[i++]);
              p.setRango(row[i++]);
              p.setDepartamento(row[i++]);
              p.setGeo(row[i++]);
              precios.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(precios.size());
        return precios;
    }
    /**
     * Método que retorna la informacion de dinamicas de mercado de todos los años
     * @return lista de tipo DinamicaMercados
     */
    public List<DinamicaMercados> consultaDinamicaMerc(){
        Conexion con=new Conexion();
        Connection cn=con.getConexion();
        ResultSet rs=null;
        PreparedStatement ps=null;
        List<DinamicaMercados> Dinam=new ArrayList<>();
        try {
            ps=cn.prepareStatement("SELECT mercado_tierras_rurales.funcion_parametros_dinamica_mercados('');");
            System.out.println(ps.toString());
            rs=ps.executeQuery();
            while (rs.next()) {                
              int i=0;
              //System.out.println(rs.getString(i++));
              String[] row=rs.getString(1).split(",");
              DinamicaMercados dm=new DinamicaMercados();
              TipoTransaccion tt= new TipoTransaccion();
              List<TipoTransaccion> transaccions=new ArrayList<>();
              dm.setId(row[i++]);
              dm.setDepartamento(row[i++]);
              tt.setAno(2011);
              tt.setCompraventa(Double.parseDouble(row[i++]));
              tt.setHipoteca(Double.parseDouble(row[i++]));
              tt.setRemate(Double.parseDouble(row[i++]));
              tt.setPermuta(Double.parseDouble(row[i++]));
              tt.setEmbargo(Double.parseDouble(row[i++]));
              tt.setPeso(Double.parseDouble(row[i++]));
              transaccions.add(tt);
              tt.setAno(2012);
              tt.setCompraventa(Double.parseDouble(row[i++]));
              tt.setHipoteca(Double.parseDouble(row[i++]));
              tt.setRemate(Double.parseDouble(row[i++]));
              tt.setPermuta(Double.parseDouble(row[i++]));
              tt.setEmbargo(Double.parseDouble(row[i++]));
              tt.setPeso(Double.parseDouble(row[i++]));
              transaccions.add(tt);
              tt.setAno(2013);
              tt.setCompraventa(Double.parseDouble(row[i++]));
              tt.setHipoteca(Double.parseDouble(row[i++]));
              tt.setRemate(Double.parseDouble(row[i++]));
              tt.setPermuta(Double.parseDouble(row[i++]));
              tt.setEmbargo(Double.parseDouble(row[i++]));
              tt.setPeso(Double.parseDouble(row[i++]));
              transaccions.add(tt);
              tt.setAno(2014);
              tt.setCompraventa(Double.parseDouble(row[i++]));
              tt.setHipoteca(Double.parseDouble(row[i++]));
              tt.setRemate(Double.parseDouble(row[i++]));
              tt.setPermuta(Double.parseDouble(row[i++]));
              tt.setEmbargo(Double.parseDouble(row[i++]));
              tt.setPeso(Double.parseDouble(row[i++]));
              transaccions.add(tt);
              dm.setTiposT(transaccions);
              dm.setGeo(row[i++]);
              Dinam.add(dm);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(Dinam.size());
        return Dinam;
    }
    /**
     * Método que retorna la información de avaluos 
     * @param filtro parametro where de la sentencia sql
     * @return lista de tipo Avaluos con la información 
     */
    public List<Avaluos> consultaAvaluos(String filtro){
        System.out.println("entre");
        Conexion con=new Conexion();
        Connection cn=con.getConexion();
        ResultSet rs=null;
        PreparedStatement ps=null;
        List<Avaluos> avaluos=new ArrayList<>();
        try {
            if(filtro=="")
                ps=cn.prepareStatement("SELECT mercado_tierras_rurales.funcion_parametros_avaluos_catastrales('');");
            else
                ps=cn.prepareStatement("SELECT mercado_tierras_rurales.funcion_parametros_avaluos_catastrales('"+filtro+"');");
            rs=ps.executeQuery();int j=0;
            while (rs.next()) {                
                int i=0;
                String[] row=rs.getString(1).split(",");
                Avaluos ava=new Avaluos();
                ava.setId(row[i++]);
                ava.setAno(Integer.parseInt(row[i++]));
                ava.setDepartamento(row[i++]);
                ava.setMunicipio(row[i++]);
                ava.setRango(row[i++]);
                ava.setArea(row[i++]);
                ava.setGeo(row[i++]);
                avaluos.add(ava);
                System.out.println(i++);
            }
        } catch (Exception e) {
        }
        System.out.println(avaluos.size());
        return avaluos;
    }
    
    /**
     * Método que obtiene los filtros de las diferentes tablas asociados a una 
     * consulta a traves del alias de la consulta
     * @param alias campo de alias en la tabla de menuconsultas
     * @return lista de tipo Filtros con todos los registros encontrados 
     */
    public List<Filtros> consultaFiltros(String alias){
        System.out.println("entre");
        Conexion con=new Conexion();
        Connection cn=con.getConexion();
        ResultSet rs=null;
        PreparedStatement ps=null;
        List<Filtros> filtros=new ArrayList<>();
        try {
            ps=cn.prepareStatement("select f.alias, f.nombre, f.texto, sf.alias, sf.nombre, tfp.valor, d.texto, '' as tipofiltropadre  \n" +
"                	from adminsiupra.tipofiltros f  \n" +
"                	inner join adminsiupra.subtipofiltros sf on f.tipofiltroid=sf.tipofiltrofkid  \n" +
"                	inner join adminsiupra.filtroperiodos tfp on tfp.subttipovalorfkid=sf.subtipofiltroid  \n" +
"                	inner join dominios  d on tipoelementofkid=dominioid \n" +
"			inner join adminsiupra.menuconsultas mc on tfp.menuconsultaid=mc.menuconsultaid\n" +
"                	where mc.alias like ? \n" +
"                union  \n" +
"                select f.alias, f.nombre, f.texto, sf.alias, sf.nombre, dep.codigodane, d.texto, '' as tipofiltropadre  \n" +
"                	from adminsiupra.tipofiltros f  \n" +
"                	inner join adminsiupra.subtipofiltros sf on f.tipofiltroid=sf.tipofiltrofkid  \n" +
"                	inner join adminsiupra.filtrovaloresdeptos fvd on fvd.subttipovalorid=sf.subtipofiltroid  \n" +
"                	inner join carto_basica.departamentos  dep on fvd.departamentosid=dep.deptoid  \n" +
"                	inner join dominios  d on tipoelementofkid=dominioid \n" +
"			inner join adminsiupra.menuconsultas mc on mc.menuconsultaid=fvd.menuconsultaid\n" +
"                	where mc.alias like ?\n" +
"                union \n" +
"                select f.alias, f.nombre, f.texto, sf.alias, sf.nombre, mun.codigodane, d.texto, '' as tipofiltropadre  \n" +
"                	from adminsiupra.tipofiltros f  \n" +
"                	inner join adminsiupra.subtipofiltros sf on f.tipofiltroid=sf.tipofiltrofkid  \n" +
"                	inner join adminsiupra.filtrovaloresmpios fvm on fvm.subttipovalorid=sf.subtipofiltroid \n" +
"                	inner join carto_basica.municipios mun on fvm.municipiosid=mun.municipid  \n" +
"                	inner join dominios  d on tipoelementofkid=dominioid \n" +
"                	inner join adminsiupra.menuconsultas mc on mc.menuconsultaid=fvm.menuconsultaid\n" +
"                	where mc.alias like ?");
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
            e.printStackTrace();
        }
        return filtros;
    }
}



