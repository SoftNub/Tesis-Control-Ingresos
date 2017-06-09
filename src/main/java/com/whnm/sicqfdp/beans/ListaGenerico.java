/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whnm.sicqfdp.beans;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author whnm
 */
public class ListaGenerico<T> implements Serializable{
    private List<T> listaGenerico;
    private int indError;
    private String msjError;

    public ListaGenerico() {
    }

    
    public List<T> getListaGenerico() {
        return listaGenerico;
    }

    public void setListaGenerico(List<T> listaGenerico) {
        this.listaGenerico = listaGenerico;
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
