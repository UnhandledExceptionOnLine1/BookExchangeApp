/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.strategy_pattern;

import hr.algebra.model.User;

/**
 *
 * @author bruno
 */
public class ContactService {

    private ContactUserStrategy strategy;

    public ContactService(ContactUserStrategy cs) {
        this.strategy = cs;
    }

    public void contactUser(String message, User user) throws Exception{
        if(strategy == null){
            throw new Exception("Strategy not set");
        }
        strategy.contactUser(user, message);
    }
   
}
