/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whnm.sicqfdp.interfaces;

/**
 *
 * @author wilson
 * @param <T>
 */
public interface Generica<T> {
    public T grabar(T elemento);
    public T editar(T elemento);
    public T eliminar(T elemento);
    public T listar(T elemento);
}
