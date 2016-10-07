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
public class Colegiado extends Persona{
    private String numColegiatura;
    private Integer estado;
    private Integer indSolicitud;
    private String fechaIngreso;
    private String fechaInscripcion;
    private Integer esColegiado;
    private List<ExperienciaLaboral> expeLaborales;
    private List<Familiar> familiares;
    private Titulacion titulacion;
    private List<OtrosGrados> otrosEstudios;

    public String getNumColegiatura() {
        return numColegiatura;
    }

    public void setNumColegiatura(String numColegiatura) {
        this.numColegiatura = numColegiatura;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Integer getIndSolicitud() {
        return indSolicitud;
    }

    public void setIndSolicitud(Integer indSolicitud) {
        this.indSolicitud = indSolicitud;
    }

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(String fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(String fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    public Integer getEsColegiado() {
        return esColegiado;
    }

    public void setEsColegiado(Integer esColegiado) {
        this.esColegiado = esColegiado;
    }

    public List<ExperienciaLaboral> getExpeLaborales() {
        return expeLaborales;
    }

    public void setExpeLaborales(List<ExperienciaLaboral> expeLaborales) {
        this.expeLaborales = expeLaborales;
    }

    public List<Familiar> getFamiliares() {
        return familiares;
    }

    public void setFamiliares(List<Familiar> familiares) {
        this.familiares = familiares;
    }

    public Titulacion getTitulacion() {
        return titulacion;
    }

    public void setTitulacion(Titulacion titulacion) {
        this.titulacion = titulacion;
    }

    public List<OtrosGrados> getOtrosEstudios() {
        return otrosEstudios;
    }

    public void setOtrosEstudios(List<OtrosGrados> otrosEstudios) {
        this.otrosEstudios = otrosEstudios;
    }   
}
