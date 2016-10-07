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
public class SpMantEstadoCivil extends StoredProcedure {
    public static final String SPROC_NAME = "mg_sp_mant_estCiviles";
    public static final String PARAM_IN_IDESTADOCIVIL = "codEstadoCivil";
    public static final String PARAM_IN_DESCESTADOCIVIL = "descEstadoCivil";
    public static final String PARAM_IN_ABBRESTADOCIVIL = "abbrEstadoCivil";
    public static final String PARAM_IN_HABILITADO = "habilit";
    public static final String PARAM_IN_OPCCRUD = "opcCrud";
    
    public static final String PARAM_OUT_IND = "ind";
    public static final String PARAM_OUT_MSJ = "msj";
    
    public SpMantEstadoCivil(DataSource dataSource) {
        super(dataSource, SPROC_NAME);
        declareParameter(new SqlParameter(PARAM_IN_IDESTADOCIVIL, Types.INTEGER));
        declareParameter(new SqlParameter(PARAM_IN_DESCESTADOCIVIL, Types.VARCHAR));
        declareParameter(new SqlParameter(PARAM_IN_ABBRESTADOCIVIL, Types.CHAR));
        declareParameter(new SqlParameter(PARAM_IN_HABILITADO, Types.INTEGER));
        declareParameter(new SqlParameter(PARAM_IN_OPCCRUD, Types.INTEGER));
        declareParameter(new SqlOutParameter(PARAM_OUT_IND, Types.INTEGER));
        declareParameter(new SqlOutParameter(PARAM_OUT_MSJ, Types.VARCHAR));
        compile();
    }
    
    public Map<String, Object> execute(Map<String,?> campos) {
        Map<String, Object> result = super.execute(campos);  
        return result;
    }
}
