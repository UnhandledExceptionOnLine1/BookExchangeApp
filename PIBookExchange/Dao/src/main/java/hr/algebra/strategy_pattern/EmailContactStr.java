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
public class EmailContactStr implements ContactUserStrategy {

    @Override
    public void contactUser(User user, String message) {
        System.out.println("Sending Email to " + user.getEmail() + ": " + message);
        //implement email sending logic here
    }

}
