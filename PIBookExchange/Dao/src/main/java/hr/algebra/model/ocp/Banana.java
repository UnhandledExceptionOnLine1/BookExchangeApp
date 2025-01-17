/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.model.ocp;

/**
 *
 * @author bruno
 */
public class Banana implements Fruit {

    private String Name = "Banana";
    private double Price = 5;

    @Override
    public String toString() {
        return "Banana{" + "Name=" + Name + ", Price=" + Price + '}';
    }

    @Override
    public double getPrice() { return Price; }

}
