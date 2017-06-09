package com.whnm.sicqfdp.birtreport;

import org.eclipse.birt.report.engine.api.IReportEngine;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;


public class BirtFactory implements ApplicationContextAware {

    private ApplicationContext context;
    private IReportEngine birtEngine;
    private String reportsRootPath;

    public IReportEngine getBirtEngine() {
        return birtEngine;
    }

    public void setBirtEngine(IReportEngine birtEngine) {
        this.birtEngine = birtEngine;
    }

    public String getReportsRootPath() {
        return reportsRootPath;
    }

    public void setReportsRootPath(String reportsRootPath) {
        this.reportsRootPath = reportsRootPath;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }
}
