/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whnm.sicqfdp.beans;

import java.util.List;

/**
 *
 * @author whnm
 */
public class ListMenu {
    private List<Menu> listMenus;
    private Integer indError;
    private String msjError;

    public List<Menu> getListMenus() {
        return listMenus;
    }

    public void setListMenus(List<Menu> listMenus) {
        this.listMenus = listMenus;
    }

    public Integer getIndError() {
        return indError;
    }

    public void setIndError(Integer indError) {
        this.indError = indError;
    }

    public String getMsjError() {
        return msjError;
    }

    public void setMsjError(String msjError) {
        this.msjError = msjError;
    }
}
