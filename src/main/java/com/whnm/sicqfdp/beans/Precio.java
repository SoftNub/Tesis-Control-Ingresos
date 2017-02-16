/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whnm.sicqfdp.beans;

import java.util.Date;

/**
 *
 * @author whnm
 */
public class Precio {
    private Long id;
    private Double precio;
    private String fechaInicioVigencia;
    private String fechaFinVigencia;
    private Integer indTablaPrecio;
    private Integer indTmpPrecio;
    private Integer indError;
    private String msjError;

    public Precio() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getFechaInicioVigencia() {
        return fechaInicioVigencia;
    }

    public void setFechaInicioVigencia(String fechaInicioVigencia) {
        this.fechaInicioVigencia = fechaInicioVigencia;
    }

    public Integer getIndTablaPrecio() {
        return indTablaPrecio;
    }

    public void setIndTablaPrecio(Integer indTablaPrecio) {
        this.indTablaPrecio = indTablaPrecio;
    }

    public Integer getIndTmpPrecio() {
        return indTmpPrecio;
    }

    public void setIndTmpPrecio(Integer indTmpPrecio) {
        this.indTmpPrecio = indTmpPrecio;
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

    public String getFechaFinVigencia() {
        return fechaFinVigencia;
    }

    public void setFechaFinVigencia(String fechaFinVigencia) {
        this.fechaFinVigencia = fechaFinVigencia;
    }
    
    
}

