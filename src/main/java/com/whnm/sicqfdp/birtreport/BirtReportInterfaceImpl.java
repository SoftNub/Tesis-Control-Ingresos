/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whnm.sicqfdp.birtreport;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import org.eclipse.birt.report.engine.api.EXCELRenderOption;
import org.eclipse.birt.report.engine.api.EngineException;
import org.eclipse.birt.report.engine.api.IRenderOption;
import org.eclipse.birt.report.engine.api.IReportRunnable;
import org.eclipse.birt.report.engine.api.IRunAndRenderTask;
import org.eclipse.birt.report.engine.api.PDFRenderOption;
import org.eclipse.birt.report.engine.api.RenderOption;
import org.eclipse.birt.report.model.api.DesignElementHandle;
import org.eclipse.birt.report.model.api.ReportDesignHandle;
import org.eclipse.birt.report.model.api.TableHandle;
import org.eclipse.birt.report.model.api.activity.SemanticException;

//@Service("birtReportInterfaceImpl")
public class BirtReportInterfaceImpl implements BirtReportInterface{
    //@Autowired
    private BirtFactory birtFactory;
    //@Autowired
    private ServletContext servletContext=null;
    
    @Override
        public byte[] getReport(String reportName,String reportFormat, Map parameters,String logUser) {
        IReportRunnable runnable = null;
        try {
            runnable = birtFactory.getBirtEngine().openReportDesign(servletContext.getRealPath(birtFactory.getReportsRootPath()) +File.separator+ reportName);
            IRunAndRenderTask task = birtFactory.getBirtEngine().createRunAndRenderTask(runnable);
            task.setParameterValues(parameters);
            IRenderOption options = new RenderOption();
            ByteArrayOutputStream outs = new ByteArrayOutputStream();
            if(reportFormat.trim().equals("pdf")){
                PDFRenderOption pdfOptions = new PDFRenderOption(options);
                pdfOptions.setOutputFormat("pdf");
                pdfOptions.setOutputStream(outs);
                task.setRenderOption(pdfOptions);
            }else{
                //CVSRenderOption cvsOptions = new CVSRenderOption(options);
                options.setOutputFormat(reportFormat);
                options.setOutputStream(outs);
                task.setRenderOption(options);
            }
            task.run();
            options=null;
            String user="";
            if(parameters!=null)
            {
                user=parameters.get("parUser")!=null?parameters.get("parUser").toString():"";
            }
            Logger.getLogger(BirtReportInterfaceImpl.class.getName()).log(Level.INFO,"Reporte :"+reportName+" - Usuario:"+user);
            return outs.toByteArray();

        } catch (EngineException ex) {
            Logger.getLogger(BirtReportInterfaceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
         
    public byte[] getReportXLS(String reportName,Map parameters,String logUser){
        IReportRunnable runnable = null;
        try {
            runnable = birtFactory.getBirtEngine().openReportDesign(servletContext.getRealPath(birtFactory.getReportsRootPath()) +File.separator+ reportName);
            IRunAndRenderTask task = birtFactory.getBirtEngine().createRunAndRenderTask(runnable);
            task.setParameterValues(parameters);
//            IRenderOption options = new RenderOption();
            IRenderOption options = null;
            ByteArrayOutputStream outs = new ByteArrayOutputStream();
            options = new EXCELRenderOption();  
            // SpudSoft Emitter
            options.setEmitterID("uk.co.spudsoft.birt.emitters.excel.XlsEmitter");
            options.setOption("ExcelEmitter.SingleSheetWithPageBreaks", true);
            options.setOutputFormat("xls_spudsoft");
            options.setOutputStream(outs);
            ReportDesignHandle designHandle = (ReportDesignHandle) runnable.getDesignHandle();
            DesignElementHandle designElement =  designHandle.getBody().get(0);
            if( designElement instanceof TableHandle ) {
                try {
                    ((TableHandle)designElement).setPageBreakInterval(0);
                    ((TableHandle)designElement).setRepeatHeader(false);
                } catch (SemanticException ex) {
                    Logger.getLogger(BirtReportInterfaceImpl.class.getName()).log(Level.SEVERE, null, ex);                    
                }
            }
            task.setRenderOption(options);
            task.run();
            options=null;
            String user="";
            if(parameters!=null)
            {
                user=parameters.get("parUser")!=null?parameters.get("parUser").toString():"";
            }
            Logger.getLogger(BirtReportInterfaceImpl.class.getName()).log(Level.INFO,"Reporte :"+reportName+" - Usuario:"+user);
            return outs.toByteArray();

        } catch (EngineException ex) {
            Logger.getLogger(BirtReportInterfaceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
