/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whnm.sicqfdp.interfaces;

import com.whnm.sicqfdp.beans.CustomUser;

/**
 *
 * @author wilson
 * @param <T>
 */
public interface Generica<T> {
    public T grabar(T elemento, CustomUser user);
    public T editar(T elemento, CustomUser user);
    public T eliminar(T elemento, CustomUser user);
    public T listar(T elemento);
}
