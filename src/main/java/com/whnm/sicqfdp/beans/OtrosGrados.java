/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whnm.sicqfdp.beans;

/**
 *
 * @author wilson
 */
public class OtrosGrados {
    private Integer id;
    private String numColegiatura;
    private Integer tipo;
    private String denominacion;
    private Integer indEliminacion;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumColegiatura() {
        return numColegiatura;
    }

    public void setNumColegiatura(String numColegiatura) {
        this.numColegiatura = numColegiatura;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public Integer getIndEliminacion() {
        return indEliminacion;
    }

    public void setIndEliminacion(Integer indEliminacion) {
        this.indEliminacion = indEliminacion;
    }

    public String getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }
    
    
}
