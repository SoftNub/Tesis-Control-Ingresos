/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whnm.sicqfdp.beans;

import java.util.List;

/**
 *
 * @author whnm
 */
public class ListLogTipoPago {
    private List<LogTipoPago> listLogTipoPago;
    private int indError;
    private String msjError;

    public List<LogTipoPago> getListLogTipoPago() {
        return listLogTipoPago;
    }

    public void setListLogTipoPago(List<LogTipoPago> listLogTipoPago) {
        this.listLogTipoPago = listLogTipoPago;
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
