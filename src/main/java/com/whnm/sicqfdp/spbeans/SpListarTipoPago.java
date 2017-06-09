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
public class SpListarTipoPago extends StoredProcedure {
    public static final String SPROC_NAME = "mi_sp_con_tipo_pago";
    public static final String PARAM_IN_IDTIPOOPERACION = "_tipo_operacion";
    public static final String PARAM_IN_IDPAGO = "_id_pago";
    public static final String PARAM_IN_ESTADO = "_estado";
    public static final String PARAM_IN_CONCEPTO_PARA = "_concepto_para";
    public static final String PARAM_IN_ESTADOS_COLEG = "v_estados_colegiados";
    public static final String PARAM_OUT_IND = "ind";
    public static final String PARAM_OUT_MSJ = "msj";
    
    public SpListarTipoPago(DataSource dataSource) {
        super(dataSource, SPROC_NAME);
        declareParameter(new SqlParameter(PARAM_IN_IDTIPOOPERACION, Types.INTEGER));
        declareParameter(new SqlParameter(PARAM_IN_IDPAGO, Types.INTEGER));
        declareParameter(new SqlParameter(PARAM_IN_ESTADO, Types.INTEGER));
        declareParameter(new SqlParameter(PARAM_IN_CONCEPTO_PARA, Types.VARCHAR));
        declareParameter(new SqlParameter(PARAM_IN_ESTADOS_COLEG, Types.VARCHAR));
        declareParameter(new SqlOutParameter(PARAM_OUT_IND, Types.INTEGER));
        declareParameter(new SqlOutParameter(PARAM_OUT_MSJ, Types.VARCHAR));
        compile();
    }
    
    public Map<String, Object> execute(Map<String,?> campos) {
        Map<String, Object> result = super.execute(campos);  
        return result;
    }
}
