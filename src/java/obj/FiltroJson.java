/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obj;

/**
 * Clase utilizada para mapear un json a una clase con el fin de construir los filtros de las consultas
 * @author cesar.solano
 */
public class FiltroJson {

    private boolean nal;
    private String[] deps;
    private String[] muns;
    private String[] regis;
    private String[] terrs;
    private String[] anios;
    private String[] meses;
    private String[] trimestre;
    private String[] semestre;
    
    
    public FiltroJson() {
    }

    public boolean isNal() {
        return nal;
    }

    public void setNal(boolean nal) {
        this.nal = nal;
    }

    public String[] getDeps() {
        return deps;
    }

    public void setDeps(String[] deps) {
        this.deps = deps;
    }

    public String[] getMuns() {
        return muns;
    }

    public void setMuns(String[] muns) {
        this.muns = muns;
    }

    public String[] getRegis() {
        return regis;
    }

    public void setRegis(String[] regis) {
        this.regis = regis;
    }

    public String[] getTerrs() {
        return terrs;
    }

    public void setTerrs(String[] terrs) {
        this.terrs = terrs;
    }

    public String[] getAnios() {
        return anios;
    }

    public void setAnios(String[] anios) {
        this.anios = anios;
    }

    public String[] getMeses() {
        return meses;
    }

    public void setMeses(String[] meses) {
        this.meses = meses;
    }

    public String[] getTrimestre() {
        return trimestre;
    }

    public void setTrimestre(String[] trimestre) {
        this.trimestre = trimestre;
    }

    public String[] getSemestre() {
        return semestre;
    }

    public void setSemestre(String[] semestre) {
        this.semestre = semestre;
    }
    
    
}
