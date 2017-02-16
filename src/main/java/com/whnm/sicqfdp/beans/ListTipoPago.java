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
public class ListTipoPago {
    private List<TipoPago> listTipoPago;
    private Integer indError;
    private String msjError;

    public ListTipoPago() {
    }

    public List<TipoPago> getListTipoPago() {
        return listTipoPago;
    }

    public void setListTipoPago(List<TipoPago> listTipoPago) {
        this.listTipoPago = listTipoPago;
    }

    public Integer getIndError() {
        return indError;
    }

    public void setIndError(Integer indError) {
        this.indError = indError;
    }

    public String getMsjError() {
        return msjError;
    }

    public void setMsjError(String msjError) {
        this.msjError = msjError;
    }
}
