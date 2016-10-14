/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whnm.sicqfdp.interfaces;

import com.whnm.sicqfdp.beans.CustomUser;
import org.springframework.security.core.userdetails.User;

/**
 *
 * @author wilson
 */
public interface UserDao extends Generica<CustomUser>{
    public CustomUser login(String username);
    //public User traer_password(User user);
}
