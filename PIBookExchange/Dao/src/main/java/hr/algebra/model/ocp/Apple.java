/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.model.ocp;

/**
 *
 * @author bruno
 */
public class Apple implements Fruit {

    private String Name = "Apple";
    private double Price = 5;

    @Override
    public String toString() {
        return "Apple{" + "Name=" + Name + ", Price=" + Price + '}';
    }

    @Override
    public double getPrice() { return Price; }

}
