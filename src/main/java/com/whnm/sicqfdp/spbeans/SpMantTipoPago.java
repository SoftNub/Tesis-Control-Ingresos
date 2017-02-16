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
public class SpMantTipoPago extends StoredProcedure {
    public static final String SPROC_NAME = "mi_sp_mant_tipo_pago";
    public static final String PARAM_IN_IDTIPOOPERACION = "_tipo_operacion";
    public static final String PARAM_IN_IDPAGO = "_id_pago";
    public static final String PARAM_IN_DENOMINACION = "_denominacion";
    public static final String PARAM_IN_ES_INHABILITADORA = "_es_inhabit";
    public static final String PARAM_IN_CONCEPTO_PARA = "_concepto_para";
    public static final String PARAM_IN_NUMERO_ACTIVAS = "_numero_activas";
    public static final String PARAM_IN_ESTADO = "_estado";
    public static final String PARAM_IN_TIPO_GENERACION = "_tipo_generacion";
    public static final String PARAM_IN_EST_COLEG = "_est_coleg";
    public static final String PARAM_IN_PRECIO = "_precio";
    public static final String PARAM_IN_FECHA_VIG = "_fecha_vigencia";
    public static final String PARAM_IN_USUARIO = "_usuario";
    
    public static final String PARAM_OUT_IND = "ind";
    public static final String PARAM_OUT_MSJ = "msj";
    
    
    public SpMantTipoPago(DataSource dataSource) {
        super(dataSource, SPROC_NAME);
        declareParameter(new SqlParameter(PARAM_IN_IDTIPOOPERACION, Types.INTEGER));
        declareParameter(new SqlParameter(PARAM_IN_IDPAGO, Types.INTEGER));
        declareParameter(new SqlParameter(PARAM_IN_DENOMINACION, Types.VARCHAR));
        declareParameter(new SqlParameter(PARAM_IN_ES_INHABILITADORA, Types.INTEGER));
        declareParameter(new SqlParameter(PARAM_IN_CONCEPTO_PARA, Types.VARCHAR));
        declareParameter(new SqlParameter(PARAM_IN_NUMERO_ACTIVAS, Types.INTEGER));
        declareParameter(new SqlParameter(PARAM_IN_ESTADO, Types.INTEGER));
        declareParameter(new SqlParameter(PARAM_IN_TIPO_GENERACION, Types.INTEGER));
        declareParameter(new SqlParameter(PARAM_IN_EST_COLEG, Types.VARCHAR));
        declareParameter(new SqlParameter(PARAM_IN_PRECIO, Types.DECIMAL));
        declareParameter(new SqlParameter(PARAM_IN_FECHA_VIG, Types.VARCHAR));
        declareParameter(new SqlParameter(PARAM_IN_USUARIO, Types.VARCHAR));
        declareParameter(new SqlOutParameter(PARAM_OUT_IND, Types.INTEGER));
        declareParameter(new SqlOutParameter(PARAM_OUT_MSJ, Types.VARCHAR));
        compile();
    }
    
    public Map<String, Object> execute(Map<String,?> campos) {
        Map<String, Object> result = super.execute(campos);  
        return result;
    }
}
