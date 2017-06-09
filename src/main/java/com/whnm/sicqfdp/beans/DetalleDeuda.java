/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whnm.sicqfdp.beans;

/**
 *
 * @author whnm
 */
public class DetalleDeuda {
    private TipoPago tipoPago;
    private String periodo;
    private Double deudaTotal;
    private Integer indError;
    private String msjError;

    public TipoPago getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(TipoPago tipoPago) {
        this.tipoPago = tipoPago;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public Double getDeudaTotal() {
        return deudaTotal;
    }

    public void setDeudaTotal(Double deudaTotal) {
        this.deudaTotal = deudaTotal;
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
