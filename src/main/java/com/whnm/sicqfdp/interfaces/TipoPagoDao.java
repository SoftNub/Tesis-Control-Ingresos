/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whnm.sicqfdp.interfaces;

import com.whnm.sicqfdp.beans.CustomUser;
import com.whnm.sicqfdp.beans.ListTipoPago;
import com.whnm.sicqfdp.beans.TipoPago;

/**
 *
 * @author whnm
 */
public interface TipoPagoDao extends Generica<TipoPago>{
    /**
     * Devuelve una lista de Tipos de Pagos segun la opcion enviada.
     * @param opc : opcion de listado del tipo de pago
     * 1 : listado de todos los tipos de pago
     * 2: listado de tipo pago por estado
     * 3: listado de tipo pago por concepto
     * 4: listado de tipo pago por id
     * @param tipoPago: objeto con los parametros necesarios para el proceso
     * de busqueda
     * @return lista de tipo de pago
     */
    public ListTipoPago listarTipoPago(Integer opc, TipoPago tipoPago);
    
    /**
     * Realiza un opcion de mantenimiento insert o update segun el tipo de opcion
     * enviado
     * @param opc: indica si se realizara un insert o update
     * 1: insert
     * 2: update
     * @param tipoPago: objeto con los datos que se grabaran
     * @param user: datos del usuario que realizara la opcion de mantenimiento
     * @return datos de confirmacion de la accion realizada.
     */
    public TipoPago mantenimientoTipoPago(Integer opc, TipoPago tipoPago, CustomUser user);
}
