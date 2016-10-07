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
public class Titulacion {
    private Integer id;
    private String numColegiatura;
    private String univTitulacion;
    private String annoTitulacion;
    private String numTitulo;
    private String numRegistro;
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

    public String getAnnoTitulacion() {
        return annoTitulacion;
    }

    public void setAnnoTitulacion(String annoTitulacion) {
        this.annoTitulacion = annoTitulacion;
    }

    public String getNumTitulo() {
        return numTitulo;
    }

    public void setNumTitulo(String numTitulo) {
        this.numTitulo = numTitulo;
    }

    public String getNumRegistro() {
        return numRegistro;
    }

    public void setNumRegistro(String numRegistro) {
        this.numRegistro = numRegistro;
    }

    public Integer getIndEliminacion() {
        return indEliminacion;
    }

    public void setIndEliminacion(Integer indEliminacion) {
        this.indEliminacion = indEliminacion;
    }

    public String getUnivTitulacion() {
        return univTitulacion;
    }

    public void setUnivTitulacion(String univTitulacion) {
        this.univTitulacion = univTitulacion;
    }
 
}
