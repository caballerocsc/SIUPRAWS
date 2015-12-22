/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obj;

import java.io.Serializable;

/**
 * Clase en la cual se hace el mapeo de la tabla Menuconsultas de la base de datos 
 * @author cesar.solano
 */
public class Menuconsultas implements Serializable {
    private static final long serialVersionUID = 1L;
    private int menuconsultaid;
    private String alias;
    private String nombre;
    private String texto;
    private int dependede;
    private boolean consultable;

    public Menuconsultas() {
    }

    public Menuconsultas(Integer menuconsultaid) {
        this.menuconsultaid = menuconsultaid;
    }

    public Menuconsultas(Integer menuconsultaid, String alias, String nombre, boolean consultable) {
        this.menuconsultaid = menuconsultaid;
        this.alias = alias;
        this.nombre = nombre;
        this.consultable = consultable;
    }

    public int getMenuconsultaid() {
        return menuconsultaid;
    }

    public void setMenuconsultaid(int menuconsultaid) {
        this.menuconsultaid = menuconsultaid;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public int getDependede() {
        return dependede;
    }

    public void setDependede(int dependede) {
        this.dependede = dependede;
    }

    public boolean getConsultable() {
        return consultable;
    }

    public void setConsultable(boolean consultable) {
        this.consultable = consultable;
    }

    @Override
    public String toString() {
        return "adminSIUPRA.Menuconsultas[ menuconsultaid=" + menuconsultaid + " ]";
    }
    
}
