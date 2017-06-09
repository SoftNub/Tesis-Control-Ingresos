/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whnm.sicqfdp.birtreport;

import java.util.Map;

public interface BirtReportInterface {
    
    public byte[] getReport(String reportName,String reportFormat,Map parameters,String logUser) ;
    public byte[] getReportXLS(String reportName,Map parameters,String logUser) ;
    //public byte[] getReportZIP(byte[] reporte, int opt) ;
}
