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
public class Pago {
    private Long codigo;
    private Colegiado colegiado;
    private Double total;
    private Integer estado;
    private String fechaPago;
    private String fechaPagoFin;
    private List<PagoDetalle> listPagoDetalle;
    private Integer indError;
    private String msjError;

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public Colegiado getColegiado() {
        return colegiado;
    }

    public void setColegiado(Colegiado colegiado) {
        this.colegiado = colegiado;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public String getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(String fechaPago) {
        this.fechaPago = fechaPago;
    }

    public List<PagoDetalle> getListPagoDetalle() {
        return listPagoDetalle;
    }

    public void setListPagoDetalle(List<PagoDetalle> listPagoDetalle) {
        this.listPagoDetalle = listPagoDetalle;
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

    public String getFechaPagoFin() {
        return fechaPagoFin;
    }

    public void setFechaPagoFin(String fechaPagoFin) {
        this.fechaPagoFin = fechaPagoFin;
    }
    
    
}
