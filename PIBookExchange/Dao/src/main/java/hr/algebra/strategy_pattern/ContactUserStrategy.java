/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package hr.algebra.strategy_pattern;

import hr.algebra.model.User;

/**
 *
 * @author bruno
 */
public interface ContactUserStrategy {
    void contactUser(User user, String message);
}
