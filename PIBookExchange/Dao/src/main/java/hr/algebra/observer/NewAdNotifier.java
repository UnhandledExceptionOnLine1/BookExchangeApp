/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.observer;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author bruno
 */
public class NewAdNotifier { //PUBLISHER / SUBJECT
    
    private final List<Subscriber> subscribers = new ArrayList();
    
    public void add(Subscriber subscriber){
        subscribers.add(subscriber);
    }
    
    public void remove(Subscriber subscriber){
        subscribers.remove(subscriber);
    }
    
    public void notify(String message){
        for(Subscriber s : subscribers){
            s.alert(message);
        }
    }
}
