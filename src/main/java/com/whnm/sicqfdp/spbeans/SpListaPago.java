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
public class SpListaPago extends StoredProcedure {
    public static final String SPROC_NAME = "mi_sp_con_pagos";
    public static final String PARAM_IN_OPC = "_tipo_operacion";
    public static final String PARAM_IN_DNI = "_num_dni";
    public static final String PARAM_IN_NUM_COLEG = "_num_coleg";
    public static final String PARAM_IN_FECHA_INI = "_fecha_ini";
    public static final String PARAM_IN_FECHA_FIN = "_fecha_fin";
    public static final String PARAM_IN_COD_TIPO_PAGO = "_cod_tipo_pago";
    public static final String PARAM_IN_COD_PAGO = "_cod_pago";
    public static final String PARAM_IN_ESTADO_PAGO = "_estado_pago";
    public static final String PARAM_OUT_IND = "ind";
    public static final String PARAM_OUT_MSJ = "msj";
    
    public SpListaPago(DataSource dataSource) {
        super(dataSource, SPROC_NAME);
        declareParameter(new SqlParameter(PARAM_IN_OPC, Types.INTEGER));
        declareParameter(new SqlParameter(PARAM_IN_DNI, Types.VARCHAR));
        declareParameter(new SqlParameter(PARAM_IN_NUM_COLEG, Types.VARCHAR));
        declareParameter(new SqlParameter(PARAM_IN_FECHA_INI, Types.VARCHAR));
        declareParameter(new SqlParameter(PARAM_IN_FECHA_FIN, Types.VARCHAR));
        declareParameter(new SqlParameter(PARAM_IN_COD_TIPO_PAGO, Types.INTEGER));
        declareParameter(new SqlParameter(PARAM_IN_COD_PAGO, Types.BIGINT));
        declareParameter(new SqlParameter(PARAM_IN_ESTADO_PAGO, Types.INTEGER));
        declareParameter(new SqlOutParameter(PARAM_OUT_IND, Types.INTEGER));
        declareParameter(new SqlOutParameter(PARAM_OUT_MSJ, Types.VARCHAR));
        compile();
    }
    
    @Override
    public Map<String, Object> execute(Map<String,?> campos) {
        Map<String, Object> result = super.execute(campos);  
        return result;
    }
}
