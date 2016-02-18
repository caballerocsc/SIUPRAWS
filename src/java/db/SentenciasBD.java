/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

/**
 * Clase encargada de almacenar todas las consultas sql de la aplicación
 * @author cesar.solano
 */
public enum SentenciasBD {
    
    DEPARTAMENTOSMUN("SELECT d.codigodane, d.deptoid, d.nomcorto, d.nombre, d.nomlargo, d.ext FROM carto_basica.departamentos d"),
    MUNICIPIOS("SELECT m.codigodane, m.nombre, m.nomcorto, m.nomlargo, m.ext, m.deptosfk  FROM carto_basica.municipios m where m.deptosfk = ?"),
    TABLADECONTENIDO("SELECT tablacontenidoupraid, alias, descripcion, imagen, nombre, palabrasclave," +
        "orden, desplegado  FROM adminsiupra.tablacontenido order by orden;"),
    SERVICIOS("SELECT serviciosupraid, alias, tiposervidor, url, versionservicio, estado, importado, descripcion, nombre, palabrasclave, orden, " +
        "accesofkid FROM adminsiupra.servicios;"),
    CAPAS("SELECT C.capasupraid, C.alias, C.aliasgrupo, C.aliasservicio, C.resolucionmax, \n" +
        "C.resolucionmin, C.filtro, C.limites, C.nombrecapa, C.opacidad, C.titulo, \n" +
        "C.estado, C.autoidentificable, C.base, C.leyendacargada, C.identificable, \n" +
        "C.ordenable, C.transparente, C.visible, C.anio, C.descripcion, C.escala, \n" +
        "C.fuente, C.imagen, C.nombre, C.palabrasclave, C.orden, C.servicioid, C.accesofkid, \n" +
        "C.formatofkid, C.crsfkid, C.tiposerviciofkid, C.tipocapafkid, C.vistageneral,tc.alias,\n" +
        "(select texto from dominios where dominioid=C.accesofkid),\n" +
        "(select texto from dominios where dominioid=C.formatofkid),\n" +
        "(select texto from dominios where dominioid=C.crsfkid),\n" +
        "(select texto from dominios where dominioid=C.tipocapafkid)\n" +
        "FROM adminsiupra.capas C inner join adminsiupra.tablacontenido_capas tcc on C.capasupraid=tcc.capasupraid\n" +
        "inner join adminsiupra.tablacontenido tc on tcc.tablacontenidoupraid=tc.tablacontenidoupraid "+ 
        " where C.estado=true order by C.orden asc ;"),
    MENUCONSULTAS("SELECT menuconsultaid, alias, nombre, texto, dependede, consultable\n" +
        "FROM adminsiupra.menuconsultas where estado = true order by orden asc;"),
    CONSULTAFILTROS("select f.alias, f.nombre, f.texto, sf.alias, sf.nombre, tfp.valor, d.texto, '' as tipofiltropadre  \n" +
        "from adminsiupra.tipofiltros f  \n" +
        "inner join adminsiupra.subtipofiltros sf on f.tipofiltroid=sf.tipofiltrofkid  \n" +
        "inner join adminsiupra.filtroperiodos tfp on tfp.subttipovalorfkid=sf.subtipofiltroid  \n" +
        "inner join dominios  d on tipoelementofkid=dominioid \n" +
        "inner join adminsiupra.menuconsultas mc on tfp.menuconsultaid=mc.menuconsultaid\n" +
        "where mc.alias like ? \n" +
        "union  \n" +
        "select f.alias, f.nombre, f.texto, sf.alias, sf.nombre, dep.codigodane, d.texto, '' as tipofiltropadre  \n" +
        "from adminsiupra.tipofiltros f  \n" +
        "inner join adminsiupra.subtipofiltros sf on f.tipofiltroid=sf.tipofiltrofkid  \n" +
        "inner join adminsiupra.filtrovaloresdeptos fvd on fvd.subttipovalorid=sf.subtipofiltroid  \n" +
        "inner join carto_basica.departamentos  dep on fvd.departamentosid=dep.deptoid  \n" +
        "inner join dominios  d on tipoelementofkid=dominioid \n" +
        "inner join adminsiupra.menuconsultas mc on mc.menuconsultaid=fvd.menuconsultaid\n" +
        "where mc.alias like ?\n" +
        "union \n" +
        "select f.alias, f.nombre, f.texto, sf.alias, sf.nombre, mun.codigodane, d.texto, '' as tipofiltropadre  \n" +
        "from adminsiupra.tipofiltros f  \n" +
        "inner join adminsiupra.subtipofiltros sf on f.tipofiltroid=sf.tipofiltrofkid  \n" +
        "inner join adminsiupra.filtrovaloresmpios fvm on fvm.subttipovalorid=sf.subtipofiltroid \n" +
        "inner join carto_basica.municipios mun on fvm.municipiosid=mun.municipid  \n" +
        "inner join dominios  d on tipoelementofkid=dominioid \n" +
        "inner join adminsiupra.menuconsultas mc on mc.menuconsultaid=fvm.menuconsultaid\n" +
        "where mc.alias like ?"),
    DINAMICAMERCADO("SELECT mercado_tierras_rurales.funcion_parametros_dinamica_mercados(?);"),
    TABLACONTENIDO_MENUCONSULTAS("select tc.alias,tc.nombre, tc.palabrasclave from adminsiupra.tablacontenido tc\n" +
        "inner join adminsiupra.menuconsultas_tablacontenido mtc on tc.tablacontenidoupraid=mtc.tablacontenidoid\n" +
        "inner join adminsiupra.menuconsultas mc on mtc.menuconsultaid=mc.menuconsultaid\n" +
        "where mc.alias like ? "),
    CAPAS_MENUCONSULTAS("select c.alias,c.aliasgrupo,c.aliasservicio,c.resolucionmax,c.filtro,c.nombrecapa,c.opacidad,(select texto from dominios where dominioid=c.crsfkid),c.anio,c.fuente,c.nombre,\n" +
        "c.visible, c.identificable, c.leyendacargada, c.autoidentificable from adminsiupra.capas c\n" +
        "inner join adminsiupra.menuconsultas_capas mcc on mcc.capasid=c.capasupraid\n" +
        "inner join adminsiupra.menuconsultas mc on mcc.menuconsultasid=mc.menuconsultaid\n" +
        "where mc.alias like ?"),
    SERVICIOS_MENUCONSULTAS("select distinct s.alias, s.tiposervidor, s.url, s.importado,s.nombre,s.palabrasclave from adminsiupra.capas c\n" +
    "inner join adminsiupra.menuconsultas_capas mcc on mcc.capasid=c.capasupraid\n" +
    "inner join adminsiupra.menuconsultas mc on mcc.menuconsultasid=mc.menuconsultaid\n" +
    "inner join adminsiupra.servicios s on c.servicioid=s.serviciosupraid\n" +
    "where mc.alias like ?"),
    PRECIOS("SELECT mercado_tierras_rurales.funcion_parametros_precios(?);"),
    PRECIOSSUMATORIARANGOS("SELECT  rango_precios, sum(area_hectareas),municipioid,municipio\n" +
        "FROM mercado_tierras_rurales.v_precios_mpios P where deptoid=? group by municipio,municipioid, rango_precios order by municipioid;"),
    PLANTILLAGENERALGRAFICOS("select pe.plantelemenid, pe.alias, pe.compcg, pe.comptab, pe.indtab,pe.indgraf from mod.plantilla_elementos pe "
            + "inner join adminsiupra.areastrabajo_plantillas_menuconsultas atpmc on pe.plantillaidfk=atpmc.plantillaid where areatrabajoid=? and menuconsultaid=?"),
    GRAFICOSCOLUMNAS("select pc.columnas, pc.tipo from mod.plantilla_columnas pc "
            + "inner join  mod.plantilla_elementos pe on pc.plantelemidfk=pe.plantelemenid where pe.plantelemenid=? and pc.tipo=1 order by pc.orden asc"),
    PLANTILLAGRAFICOS("select pg.plantgrafid, pg.tipo, pg.titulo from mod.plantilla_grafico pg "
            + "inner join mod.plantilla_elementos pe on pg.plantelemidfk=pe.plantelemenid where pe.plantelemenid=? "),
    GRAFICOSEJEY("select pgy.valor from mod.plantilla_graficoy pgy inner join mod.plantilla_grafico pg on pgy.plantgrafidfk=pg.plantgrafid where pg.plantgrafid = ?"),
    GRAFICOSEJEX("select pgx.valor from mod.plantilla_graficox pgx inner join mod.plantilla_grafico pg on pgx.plantgrafidfk=pg.plantgrafid where pg.plantgrafid = ?"),
    GRAFICOSSERIES("select gs.valor from mod.plantilla_graficos_series gs "
            + "inner join mod.plantilla_grafico pg on gs.plantillagrafidfk=pg.plantgrafid where pg.plantgrafid = ? order by gs.orden asc "),
    GRAFICOSDATOS("select gd.valor from mod.plantilla_grafico_datos gd inner join mod.plantilla_grafico pg on gd.plantgrafseridfk=pg.plantgrafid"
            + " where pg.plantgrafid = ? order by gd.orden asc"),
    GRAFICOSCOLORES("select gc.color from mod.plantilla_grafico_colores gc inner join mod.plantilla_grafico pg on gc.plantgrafidfk=pg.plantgrafid  where pg.plantgrafid =? order by gc.orden asc");
    
    private final String sentencia;

    private SentenciasBD(String sentencia) {
        this.sentencia = sentencia;
    }

    public String getSentencia() {
        return sentencia;
    }
    
    
}
