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
public class Deuda {
    private Colegiado colegiad;
    private TipoPago tipoPago;
    private String periodo;
    private Double deudaTotal;
    private List<DetalleDeuda> detalleDeuda;
    private Integer indError;
    private String msjError;

    public Colegiado getColegiad() {
        return colegiad;
    }

    public void setColegiad(Colegiado colegiad) {
        this.colegiad = colegiad;
    }

    public TipoPago getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(TipoPago tipoPago) {
        this.tipoPago = tipoPago;
    }

    public Double getDeudaTotal() {
        return deudaTotal;
    }

    public void setDeudaTotal(Double deudaTotal) {
        this.deudaTotal = deudaTotal;
    }

    public List<DetalleDeuda> getDetalleDeuda() {
        return detalleDeuda;
    }

    public void setDetalleDeuda(List<DetalleDeuda> detalleDeuda) {
        this.detalleDeuda = detalleDeuda;
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

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }
}
