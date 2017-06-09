/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whnm.sicqfdp.spbeans;

import java.sql.Types;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;

/**
 *
 * @author wilson
 */
public class SpMantPago extends StoredProcedure {
    public static final String SPROC_NAME = "mi_sp_mant_pago";
    public static final String PARAM_IN_OPC_CRUD = "_tipo_operacion";
    public static final String PARAM_IN_NUM_COLEG = "_num_colegiatura";
    public static final String PARAM_IN_DNI = "_dni";
    public static final String PARAM_IN_NOMBRES = "_nombres";
    public static final String PARAM_IN_TRAMA_PAGOS = "_trama_pagos";   
    public static final String PARAM_IN_USUARIO = "_usuario";   
    public static final String PARAM_IN_COD_PAGO = "_cod_pago";   
    public static final String PARAM_OUT_IND = "ind";
    public static final String PARAM_OUT_MSJ = "msj";
    
    public SpMantPago(DataSource dataSource) {
        super(dataSource, SPROC_NAME);
        declareParameter(new SqlParameter(PARAM_IN_OPC_CRUD, Types.INTEGER));
        declareParameter(new SqlParameter(PARAM_IN_NUM_COLEG, Types.VARCHAR));
        declareParameter(new SqlParameter(PARAM_IN_DNI, Types.VARCHAR));
        declareParameter(new SqlParameter(PARAM_IN_NOMBRES, Types.VARCHAR));
        declareParameter(new SqlParameter(PARAM_IN_TRAMA_PAGOS, Types.VARCHAR));
        declareParameter(new SqlParameter(PARAM_IN_USUARIO, Types.VARCHAR));
        declareParameter(new SqlParameter(PARAM_IN_COD_PAGO, Types.BIGINT));
        declareParameter(new SqlOutParameter(PARAM_OUT_IND, Types.INTEGER));
        declareParameter(new SqlOutParameter(PARAM_OUT_MSJ, Types.VARCHAR));
        compile();
    }
    
    public Map<String, Object> execute(Map<String,?> campos) {
        Map<String, Object> result = super.execute(campos);  
        return result;
    }
}
