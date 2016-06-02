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
public class SentenciasBD {
    
    private final String DEPARTAMENTOSMUN="SELECT d.codigodane, d.deptoid, d.nomcorto, d.nombre, d.nomlargo, d.ext FROM carto_basica.departamentos d";
    private final String MUNICIPIOS="SELECT m.codigodane, m.nombre, m.nomcorto, m.nomlargo, m.ext, m.deptosfk  FROM carto_basica.municipios m where m.deptosfk = ?";
    private final String TABLADECONTENIDO="SELECT tablacontenidoupraid, alias, descripcion, imagen, nombre, palabrasclave," +
        "orden, desplegado  FROM adminsiupra.tablacontenido order by orden;";
    private final String SERVICIOS="SELECT serviciosupraid, alias, tiposervidor, url, versionservicio, estado, importado, descripcion, nombre, palabrasclave, orden, " +
        "accesofkid FROM adminsiupra.servicios;";
    private final String CAPAS="SELECT C.capasupraid, C.alias, C.aliasgrupo, C.aliasservicio, C.resolucionmax, \n" +
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
        " where C.estado=true order by C.orden asc ;";
    private final String MENUCONSULTAS="SELECT menuconsultaid, alias, nombre, texto, dependede, consultable\n" +
        "FROM adminsiupra.menuconsultas where estado = true order by orden asc;";
    private final String CONSULTAFILTROS="select f.alias, f.nombre, f.texto, sf.alias, sf.nombre, tfp.valor, d.texto, '' as tipofiltropadre  \n" +
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
        "where mc.alias like ?";
    private final String DINAMICAMERCADO="SELECT mercado_tierras_rurales.funcion_parametros_dinamica_mercados(?);";
    private final String TABLACONTENIDO_MENUCONSULTAS="select tc.alias,tc.nombre, tc.palabrasclave from adminsiupra.tablacontenido tc\n" +
        "inner join adminsiupra.menuconsultas_tablacontenido mtc on tc.tablacontenidoupraid=mtc.tablacontenidoid\n" +
        "inner join adminsiupra.menuconsultas mc on mtc.menuconsultaid=mc.menuconsultaid\n" +
        "where mc.alias like ? ";
    private final String CAPAS_MENUCONSULTAS="select c.alias,c.aliasgrupo,c.aliasservicio,c.resolucionmax,c.filtro,c.nombrecapa,c.opacidad,(select texto from dominios where dominioid=c.crsfkid),c.anio,c.fuente,c.nombre,\n" +
        "c.visible, c.identificable, c.leyendacargada, c.autoidentificable, c.titulo from adminsiupra.capas c\n" +
        "inner join adminsiupra.menuconsultas_capas mcc on mcc.capasid=c.capasupraid\n" +
        "inner join adminsiupra.menuconsultas mc on mcc.menuconsultasid=mc.menuconsultaid\n" +
        "where mc.alias like ?";
    private final String SERVICIOS_MENUCONSULTAS="select distinct s.alias, s.tiposervidor, s.url, s.importado,s.nombre,s.palabrasclave from adminsiupra.capas c\n" +
        "inner join adminsiupra.menuconsultas_capas mcc on mcc.capasid=c.capasupraid\n" +
        "inner join adminsiupra.menuconsultas mc on mcc.menuconsultasid=mc.menuconsultaid\n" +
        "inner join adminsiupra.servicios s on c.servicioid=s.serviciosupraid\n" +
        "where mc.alias like ?";
    private final String PRECIOS="SELECT deptoid||municipioid as codeDane, departamento , municipio, area_hectareas, rango_precios \n" +
        "FROM mercado_tierras_rurales.v_precios_mpios where deptoid like ? \n" +
        "order by codeDane asc;";
    /*private final String PRECIOSSUMATORIARANGOS="SELECT  rango_precios, sum(area_hectareas)municipioid,municipio\n" +
        "FROM mercado_tierras_rurales.v_precios_mpios P where deptoid=? group by municipio,municipioid, rango_precios order by municipioid;";*/
    private final String  PLANTILLAGENERALGRAFICOS="select pe.plantelemenid, pe.alias, pe.compcg, pe.comptab, pe.indtab,pe.indgraf from mod.plantilla_elementos pe "
            + "inner join adminsiupra.areastrabajo_plantillas_menuconsultas atpmc on pe.plantillaidfk=atpmc.plantillaid where areatrabajoid=? and menuconsultaid=?";
    private final String GRAFICOSCOLUMNAS="select pc.columnas, pc.tipo from mod.plantilla_columnas pc "
            + "inner join  mod.plantilla_elementos pe on pc.plantelemidfk=pe.plantelemenid where pe.plantelemenid=? and pc.tipo=1 order by pc.orden asc";
    private final String PLANTILLAGRAFICOS="select pg.plantgrafid, pg.tipo, pg.titulo from mod.plantilla_grafico pg "
            + "inner join mod.plantilla_elementos pe on pg.plantelemidfk=pe.plantelemenid where pe.plantelemenid=? ";
    private final String GRAFICOSEJEY="select pgy.valor from mod.plantilla_graficoy pgy inner join mod.plantilla_grafico pg on pgy.plantgrafidfk=pg.plantgrafid where pg.plantgrafid = ?";
    private final String GRAFICOSEJEX="select pgx.valor from mod.plantilla_graficox pgx inner join mod.plantilla_grafico pg on pgx.plantgrafidfk=pg.plantgrafid where pg.plantgrafid = ?";
    private final String GRAFICOSSERIES="select gs.valor from mod.plantilla_graficos_series gs "
            + "inner join mod.plantilla_grafico pg on gs.plantillagrafidfk=pg.plantgrafid where pg.plantgrafid = ? order by gs.orden asc ";
    private final String GRAFICOSDATOS="select gd.valor from mod.plantilla_grafico_datos gd inner join mod.plantilla_grafico pg on gd.plantgrafseridfk=pg.plantgrafid"
            + " where pg.plantgrafid = ? order by gd.orden asc";
    private final String GRAFICOSCOLORES="select gc.color from mod.plantilla_grafico_colores gc inner join mod.plantilla_grafico pg on gc.plantgrafidfk=pg.plantgrafid  where pg.plantgrafid =? order by gc.orden asc";
    private final String RESTRICCIONES="select codigodanedepto,tipo_zona,departamento,round((sum(area_ha_rest_agro)*100)/area_ha_depto,2) , round(area_ha_depto,2) \n" +
            "from areas_disponibles_ordenamiento_productivo.v_restricciones_agropecuarias \n" +
            "group by tipo_zona,departamento,codigodanedepto,area_ha_depto \n" +
            "order by departamento,tipo_zona";
    private final String EXCLUSIONES="select cod_depart,diss_f,nombre,round(sum(areaha),2),round((sum(areaha)*100)/areadptoha,2) , round(areadptoha,2) \n" +
            "from areas_disponibles_ordenamiento_productivo.exclusiones_mercado_tierras \n" +
            "group by diss_f,nombre,cod_depart,areadptoha \n" +
            "order by nombre,diss_f";
    private final String DOCSINFO="SELECT d.titulo,d.descripcion, d.nombre\n" +
            "FROM adminsiupra.documentos d\n" +
            "inner join adminsiupra.menuconsultas_documentos mcd on mcd.documentosid=d.documentoid\n" +
            "inner join adminsiupra.menuconsultas mc on mc.menuconsultaid=mcd.menuconsultasid \n" +
            "where mc.alias like ? and d.docs = ? \n" +
            "order by d.orden";
    private final String IFPR="select cod_dane_depto, departamento, fraccionamiento "+ 
            "from indicadores.v_fraccionamiento order by cod_dane_depto";
    private final String DISTRITOSDATS="SELECT id, id_incoder, nom_dat, cod_depto, depto, cod_mcpio, mcpio, \n" +
            "vereda, area_neta, area_bruta, escala, coorn1, coorn2, coore1, \n" +
            "coore2, altitud, tipo_dat, etapa_dat, funciona, usuarios, inversion, \n" +
            "admin, asoc_usuar, resol_asoc, repr_asoc, tel_asoc, e_mail, direc_asoc, \n" +
            "cultivos, car, fuent_hidr, captacion, concesion, szh, zh, georef, \n" +
            "id_polig, observac\n" +
            "FROM adecuacion_tierras_rurales.v_distritos_riego_alfa_total;";
    private final String DISTRITOSGRAN="select count(*),departamento from adecuacion_tierras_rurales."
            + "v_distritos_riego_total_gran group by departamento order by departamento;";
    private final String DISTRITOSMED="select count(*),departamento from adecuacion_tierras_rurales."
            + "v_distritos_riego_total_mediana group by departamento order by departamento;";
    private final String DISTRITOPEQ="select count(*),departamento from adecuacion_tierras_rurales."
            + "v_distritos_riego_total_pequena group by departamento order by departamento;";
    private final String DEPARTAMENTOS="SELECT  nombre\n" +
            "FROM carto_basica.v_departamentos\n" +
            "order by nombre;";
    private final String INDICE_CONCENTRACION="SELECT cod_dane_depto, departamento, concentracion\n" +
            "FROM indicadores.v_concentracion "
            + "order by concentracion desc;";
    private final String SUP_SIN_REST_LEG="SELECT cod_dane_depto, departamento, porc_superf_suelo_sin_restr_act_ag_pec_for, \n" +
            "categoria FROM indicadores.v_biof_e01 \n" +
            "order by porc_superf_suelo_sin_restr_act_ag_pec_for desc;";
    private final String SUP_EXC_LEG="SELECT cod_dane_depto, departamento, porc_superficie_suelo_rural_exclusion_legal, \n" +
            "categoria FROM indicadores.v_biof_e02\n" +
            "order by porc_superficie_suelo_rural_exclusion_legal desc";
    private final String SUP_USO_COND="SELECT codigodane, nombre, zuc, categoria FROM indicadores.biof_e03\n" +
            "order by zuc desc";
    private final String SUP_SOBRE="SELECT cod_dane_depto, departamento, porc_superficie_suelo_rural_conflicto_sobreutilizacion,"
            + " categoria FROM indicadores.v_biof_e04"
            + " order by porc_superficie_suelo_rural_conflicto_sobreutilizacion desc";
    private final String SUP_SUB="SELECT cod_dane_depto, departamento, porc_superf_suelo_conf_uso_sin_subut,"
            + " categoria FROM indicadores.v_biof_e05"
            + " order by porc_superf_suelo_conf_uso_sin_subut desc";
    private final String SUP_SIN_CONFLICTO="SELECT cod_dane_depto, departamento, porc_superf_suelo_u_a,"
            + " categoria FROM indicadores.v_biof_e06"
            + " order by porc_superf_suelo_u_a desc";
    private final String AREAS_POTENCIALES="SELECT codigodanedepto, departamento, round (sum(area_ha_pot_riego_drenaje),2) as riego\n" +
            "FROM adecuacion_tierras_rurales.v_pot_riego_drenaje where descripcion like ? \n" +
            "group by codigodanedepto,departamento order by codigodanedepto;";
    

    public SentenciasBD() {
    }

    public String getDEPARTAMENTOSMUN() {
        return DEPARTAMENTOSMUN;
    }

    public String getMUNICIPIOS() {
        return MUNICIPIOS;
    }

    public String getTABLADECONTENIDO() {
        return TABLADECONTENIDO;
    }

    public String getSERVICIOS() {
        return SERVICIOS;
    }

    public String getCAPAS() {
        return CAPAS;
    }

    public String getMENUCONSULTAS() {
        return MENUCONSULTAS;
    }

    public String getCONSULTAFILTROS() {
        return CONSULTAFILTROS;
    }

    public String getDINAMICAMERCADO() {
        return DINAMICAMERCADO;
    }

    public String getTABLACONTENIDO_MENUCONSULTAS() {
        return TABLACONTENIDO_MENUCONSULTAS;
    }

    public String getCAPAS_MENUCONSULTAS() {
        return CAPAS_MENUCONSULTAS;
    }

    public String getSERVICIOS_MENUCONSULTAS() {
        return SERVICIOS_MENUCONSULTAS;
    }

    public String getPRECIOS() {
        return PRECIOS;
    }

    /*public String getPRECIOSSUMATORIARANGOS() {
        return PRECIOSSUMATORIARANGOS;
    }*/

    public String getPLANTILLAGENERALGRAFICOS() {
        return PLANTILLAGENERALGRAFICOS;
    }

    public String getGRAFICOSCOLUMNAS() {
        return GRAFICOSCOLUMNAS;
    }

    public String getPLANTILLAGRAFICOS() {
        return PLANTILLAGRAFICOS;
    }

    public String getGRAFICOSEJEY() {
        return GRAFICOSEJEY;
    }

    public String getGRAFICOSEJEX() {
        return GRAFICOSEJEX;
    }

    public String getGRAFICOSSERIES() {
        return GRAFICOSSERIES;
    }

    public String getGRAFICOSDATOS() {
        return GRAFICOSDATOS;
    }

    public String getGRAFICOSCOLORES() {
        return GRAFICOSCOLORES;
    }

    public String getRESTRICCIONES() {
        return RESTRICCIONES;
    }
    
    public String getEXCLUSIONES() {
        return EXCLUSIONES;
    }
    
    public String getDOCSINFO(){
        return DOCSINFO;
    }
    
    public String getIFPR(){
        return IFPR;
    }

    public String getDISTRITOSDATS() {
        return DISTRITOSDATS;
    }

    public String getDISTRITOSGRAN() {
        return DISTRITOSGRAN;
    }

    public String getDISTRITOSMED() {
        return DISTRITOSMED;
    }

    public String getDISTRITOPEQ() {
        return DISTRITOPEQ;
    }

    public String getDEPARTAMENTOS() {
        return DEPARTAMENTOS;
    }

    public String getINDICE_CONCENTRACION() {
        return INDICE_CONCENTRACION;
    }

    public String getSUP_SIN_REST_LEG() {
        return SUP_SIN_REST_LEG;
    }

    public String getSUP_EXC_LEG() {
        return SUP_EXC_LEG;
    }

    public String getSUP_USO_COND() {
        return SUP_USO_COND;
    }

    public String getSUP_SOBRE() {
        return SUP_SOBRE;
    }

    public String getSUP_SUB() {
        return SUP_SUB;
    }

    public String getSUP_SIN_CONFLICTO() {
        return SUP_SIN_CONFLICTO;
    }

    public String getAREAS_POTENCIALES() {
        return AREAS_POTENCIALES;
    }
    
}
