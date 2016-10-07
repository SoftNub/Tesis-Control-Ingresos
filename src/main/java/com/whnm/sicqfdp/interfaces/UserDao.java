/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whnm.sicqfdp.interfaces;

import com.whnm.sicqfdp.beans.User;

/**
 *
 * @author wilson
 */
public interface UserDao extends Generica<User>{
    public User login(User user);
}
