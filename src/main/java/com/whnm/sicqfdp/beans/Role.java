/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whnm.sicqfdp.beans;

import java.io.Serializable;
import org.springframework.stereotype.Repository;

/**
 *
 * @author wilson
 */
@Repository
public class Role implements Serializable{
    private Integer idRol;
    private String descripcion;
    private String autorisacion;

    public Integer getIdRol() {
        return idRol;
    }

    public void setIdRol(Integer idRol) {
        this.idRol = idRol;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getAutorisacion() {
        return autorisacion;
    }

    public void setAutorisacion(String autorisacion) {
        this.autorisacion = autorisacion;
    }
    
    
}
