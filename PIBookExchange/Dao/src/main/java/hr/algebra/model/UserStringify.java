/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.model;

/**
 *
 * @author bruno
 */
public class UserStringify {

    public static String getString(User user) {
        return user.getFirstName() + " " + user.getLastName();
    }
}
