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
public class TipoPago {
    private Long id;
    private String concepto;
    private String conceptoPara;
    private Integer esInhabilitadora;
    private Integer numPagosActivos;
    private Integer tipoGeneracion;
    private Integer estado;
    private String estadosColegiados;
    private Integer annadido;
    private Integer tipoCantidad;
    private Precio precioActual;
    private Precio precioEspera;
    private List<Precio> preciosHistoricos;
    private Integer indTablaPrecio;
    private Integer indTmpPrecio;
    private Integer indError;
    private String msjError;

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

    public TipoPago() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public String getConceptoPara() {
        return conceptoPara;
    }

    public void setConceptoPara(String conceptoPara) {
        this.conceptoPara = conceptoPara;
    }

    public Integer getEsInhabilitadora() {
        return esInhabilitadora;
    }

    public void setEsInhabilitadora(Integer esInhabilitadora) {
        this.esInhabilitadora = esInhabilitadora;
    }

    public Integer getNumPagosActivos() {
        return numPagosActivos;
    }

    public void setNumPagosActivos(Integer numPagosActivos) {
        this.numPagosActivos = numPagosActivos;
    }

    public Integer getTipoGeneracion() {
        return tipoGeneracion;
    }

    public void setTipoGeneracion(Integer tipoGeneracion) {
        this.tipoGeneracion = tipoGeneracion;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public String getEstadosColegiados() {
        return estadosColegiados;
    }

    public void setEstadosColegiados(String estadosColegiados) {
        this.estadosColegiados = estadosColegiados;
    }

    public Integer getAnnadido() {
        return annadido;
    }

    public void setAnnadido(Integer annadido) {
        this.annadido = annadido;
    }
    
    public Precio getPrecioActual() {
        return precioActual;
    }

    public void setPrecioActual(Precio precioActual) {
        this.precioActual = precioActual;
    }

    public Precio getPrecioEspera() {
        return precioEspera;
    }

    public void setPrecioEspera(Precio precioEspera) {
        this.precioEspera = precioEspera;
    }

    public List<Precio> getPreciosHistoricos() {
        return preciosHistoricos;
    }

    public void setPreciosHistoricos(List<Precio> preciosHistoricos) {
        this.preciosHistoricos = preciosHistoricos;
    }

    public Integer getTipoCantidad() {
        return tipoCantidad;
    }

    public void setTipoCantidad(Integer tipoCantidad) {
        this.tipoCantidad = tipoCantidad;
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
