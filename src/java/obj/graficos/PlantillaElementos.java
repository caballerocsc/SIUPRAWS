/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package obj.graficos;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class PlantillaElementos {

    private int id;
    private String al; 
    private String compcg;
    private String comptab;
    private List<PlantillaColumnas> pc;
    private PlantillaGraficos pg;
    private int indtab;
    private int indgraf;
    
    public PlantillaElementos() {
        pc=new ArrayList<>();
        pg=new PlantillaGraficos();
    }

    public String getAl() {
        return al;
    }

    public void setAl(String al) {
        this.al = al;
    }

    public String getCompcg() {
        return compcg;
    }

    public void setCompcg(String compcg) {
        this.compcg = compcg;
    }

    public String getComptab() {
        return comptab;
    }

    public void setComptab(String comptab) {
        this.comptab = comptab;
    }

    public List<PlantillaColumnas> getPc() {
        return pc;
    }

    public void setPc(List<PlantillaColumnas> pc) {
        this.pc = pc;
    }

    public PlantillaGraficos getPg() {
        return pg;
    }

    public void setPg(PlantillaGraficos pg) {
        this.pg = pg;
    }
   
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIndtab() {
        return indtab;
    }

    public void setIndtab(int indtab) {
        this.indtab = indtab;
    }

    public int getIndgraf() {
        return indgraf;
    }

    public void setIndgraf(int indgraf) {
        this.indgraf = indgraf;
    }
    
}
