/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whnm.sicqfdp.tareas;

import com.whnm.sicqfdp.beans.CustomUser;
import com.whnm.sicqfdp.beans.TipoPago;
import com.whnm.sicqfdp.interfaces.TipoPagoDao;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 *
 * @author ISBA
 */
@Component("ProcesosAutomaticosTask")
public class ProcesosAutomaticos {
    @Autowired
    @Qualifier(value = "tipoPagoService")        
    TipoPagoDao tipoPagoService;

    public void iniciarVigenciaPreciosProgramados(){
        TipoPago tipoPagoRep = new TipoPago();
        try {
            Date date = new Date();
            SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
            Logger.getLogger(ProcesosAutomaticos.class.getName()).log(Level.INFO, "Iniciando Tarea iniciarVigenciaPreciosProgramados");
            CustomUser user = new CustomUser();
            user.setUsername("AUTOMATICO");
            tipoPagoRep = tipoPagoService.iniciarVigenciaPreciosProgramados(sf.format(date), user);
            Logger.getLogger(ProcesosAutomaticos.class.getName()).log(Level.INFO, "Ejecutando Tarea de iniciarVigenciaPreciosProgramados {0} ", date.toString());
            Logger.getLogger(ProcesosAutomaticos.class.getName()).log(Level.INFO, "Respuesta: {0} ", tipoPagoRep.getMsjError());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void crearCuotasInhabilitadoras(){
        TipoPago tipoPagoRep = new TipoPago();
        try {
            Date date = new Date();
            SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
            Logger.getLogger(ProcesosAutomaticos.class.getName()).log(Level.INFO, "Iniciando Tarea crearCuotasInhabilitadoras");
            CustomUser user = new CustomUser();
            user.setUsername("AUTOMATICO");
            tipoPagoRep = tipoPagoService.crearCuotasInhabilitadoras(sf.format(date), user);
            Logger.getLogger(ProcesosAutomaticos.class.getName()).log(Level.INFO, "Ejecutando Tarea de crearCuotasInhabilitadoras {0} ", date.toString());
            Logger.getLogger(ProcesosAutomaticos.class.getName()).log(Level.INFO, "Respuesta: {0} ", tipoPagoRep.getMsjError());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
