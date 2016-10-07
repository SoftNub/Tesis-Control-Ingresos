/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whnm.sicqfdp.beans;

import java.io.Serializable;
import java.security.Principal;
import java.util.HashSet;
import java.util.Set;
import org.springframework.stereotype.Repository;

/**
 *
 * @author wilson
 */
@Repository
public class User implements Principal, Serializable{
    private String nombreUsuario;
    private String password;
    private Integer habilitado;
    private Set<Role> myRoles;
    private Integer indError;
    private String msjError;

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(Integer habilitado) {
        this.habilitado = habilitado;
    }

    @Override
    public String getName() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

    public Set<Role> getMyRoles() {
        return myRoles;
    }

    public void setMyRoles(Set<Role> myRoles) {
        this.myRoles = myRoles;
    }
    
    
}
