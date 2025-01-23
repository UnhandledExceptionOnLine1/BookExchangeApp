/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.mediator;

import javax.swing.*;
import java.awt.*;
/**
 *
 * @author Tin
 */
public class AdFormMediator {

    private final JComboBox<String> cbCategory;
    private final JTextField tfPrice;
    private final JLabel lblMaxPriceValue;

    public AdFormMediator(JComboBox<String> cbCategory, JTextField tfPrice, JLabel lblMaxPriceValue) {
        this.cbCategory = cbCategory;
        this.tfPrice = tfPrice;
        this.lblMaxPriceValue = lblMaxPriceValue;
    }

    public void notify(Component sender, String event) {
        if (sender == cbCategory && "selectionChanged".equals(event)) {
            String selectedCategory = (String) cbCategory.getSelectedItem();
            if ("Knjiga".equals(selectedCategory)) {
                lblMaxPriceValue.setText("500 €");
            } else if ("Ostali materijali".equals(selectedCategory)) {
                lblMaxPriceValue.setText("200 €");
            } else {
                lblMaxPriceValue.setText("N/A");
            }
        }

        if (sender == tfPrice && "textChanged".equals(event)) {
            try {
                double enteredPrice = Double.parseDouble(tfPrice.getText());
                double maxPrice = "Knjiga".equals((String) cbCategory.getSelectedItem()) ? 500 : 200;

                if (enteredPrice > maxPrice) {
                    tfPrice.setBackground(Color.PINK); // Oboji polje crveno ako je cijena prevelika
                } else {
                    tfPrice.setBackground(Color.WHITE); // Vrati na bijelo
                }
            } catch (NumberFormatException ex) {
                tfPrice.setBackground(Color.PINK); // Oboji crveno ako nije validan broj
            }
        }
    }
}