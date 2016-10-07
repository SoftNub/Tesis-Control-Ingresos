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
 * @author wilson
 */
public class DistritoList implements Serializable{
    private static final long serialVersionUID = 1L;
    private List<Distrito> listaDistritos;
    private int indError;
    private String msjError;

    public List<Distrito> getListaDistritos() {
        return listaDistritos;
    }

    public void setListaDistritos(List<Distrito> listaDistritos) {
        this.listaDistritos = listaDistritos;
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
