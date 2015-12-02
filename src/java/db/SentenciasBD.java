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
    
    DEPARTAMENTOSMUN("SELECT d.codigodane, d.deptoid, d.nomcorto, d.nombre, d.nomlargo, d.ext FROM carto_basica.departamentos"),
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
        "inner join adminsiupra.tablacontenido tc on tcc.tablacontenidoupraid=tc.tablacontenidoupraid;"),
    MENUCONSULTAS("SELECT menuconsultaid, alias, nombre, texto, dependede, consultable\n" +
        "FROM adminsiupra.menuconsultas where estado = true;"),
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
    DINAMICAMERCADO("SELECT mercado_tierras_rurales.funcion_parametros_dinamica_mercados(?);")
    ;
    
    private final String sentencia;

    private SentenciasBD(String sentencia) {
        this.sentencia = sentencia;
    }

    public String getSentencia() {
        return sentencia;
    }
    
    
}
