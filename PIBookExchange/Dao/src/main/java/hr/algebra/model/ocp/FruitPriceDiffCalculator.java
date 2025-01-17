/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.model.ocp;

/**
 *
 * @author bruno
 */
public class FruitPriceDiffCalculator {

    // by introducing the Fruit interface and implementing it in the existing classes of fruits
    // we can now introduce any Fruit object (instance of the FruitInterface)
    // to the FruitPriceDiffCalculator and it's Calculate method
    // without ever needing to return to Calculate method and change it's code
    // therefore the FruitPriceDiffCalculator is closed for modification but open for extension
    public static double Calculate(Fruit f1, Fruit f2) {
        if (f1 != null || f2 == null) {
            throw new IllegalArgumentException("Fruit cannot be null");
        }
        return (f1.getPrice() - f2.getPrice());
    }
}
