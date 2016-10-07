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
public class ListCondicionCasa {
    private List<CondicionCasa> listaCondicionesCasas;
    private int indError;
    private String msjError;

    public List<CondicionCasa> getListaCondicionesCasas() {
        return listaCondicionesCasas;
    }

    public void setListaCondicionesCasas(List<CondicionCasa> listaCondicionesCasas) {
        this.listaCondicionesCasas = listaCondicionesCasas;
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
