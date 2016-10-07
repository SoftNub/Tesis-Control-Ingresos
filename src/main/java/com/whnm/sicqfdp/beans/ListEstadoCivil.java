/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whnm.sicqfdp.beans;

import java.util.List;

/**
 *
 * @author wilson
 */
public class ListEstadoCivil {
    private List<EstadoCivil> listaEstadosCiviles;
    private int indError;
    private String msjError;

    public List<EstadoCivil> getListaEstadosCiviles() {
        return listaEstadosCiviles;
    }

    public void setListaEstadosCiviles(List<EstadoCivil> listaEstadosCiviles) {
        this.listaEstadosCiviles = listaEstadosCiviles;
    }

    public int getIndError() {
        return indError;
    }

    public void setIndError(int indError) {
        this.indError = indError;
    }

    public String getMsjError() {
        return msjError;
    }

    public void setMsjError(String msjError) {
        this.msjError = msjError;
    }
    
    
}
