/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whnm.sicqfdp.beans;

import java.io.Serializable;

/**
 *
 * @author whnm
 */
public class Menu implements Serializable{
    private Integer idMenu;
    private Integer idRol;
    private String descripMenu;
    private String descripRol;
    private Integer estado;
    private Integer indError;
    private String msjError;

    public Integer getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(Integer idMenu) {
        this.idMenu = idMenu;
    }

    public Integer getIdRol() {
        return idRol;
    }

    public void setIdRol(Integer idRol) {
        this.idRol = idRol;
    }

    public String getDescripMenu() {
        return descripMenu;
    }

    public void setDescripMenu(String descripMenu) {
        this.descripMenu = descripMenu;
    }

    public String getDescripRol() {
        return descripRol;
    }

    public void setDescripRol(String descripRol) {
        this.descripRol = descripRol;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
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
