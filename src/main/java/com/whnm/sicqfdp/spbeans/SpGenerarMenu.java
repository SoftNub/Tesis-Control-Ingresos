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
public class SpGenerarMenu extends StoredProcedure {
    public static final String SPROC_NAME = "ms_sp_generar_menus";
    public static final String PARAM_IN_ID_PERFIL = "cod_perfil";
    public static final String PARAM_IN_USUARIO_MENU = "_usuario_menu";
    public static final String PARAM_IN_USUARIO = "_usuario";
    public static final String PARAM_OUT_IND = "ind";
    public static final String PARAM_OUT_MSJ = "msj";
    
    public SpGenerarMenu(DataSource dataSource) {
        super(dataSource, SPROC_NAME);
        declareParameter(new SqlParameter(PARAM_IN_ID_PERFIL, Types.INTEGER));
        declareParameter(new SqlParameter(PARAM_IN_USUARIO_MENU, Types.VARCHAR));
        declareParameter(new SqlParameter(PARAM_IN_USUARIO, Types.VARCHAR));
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
