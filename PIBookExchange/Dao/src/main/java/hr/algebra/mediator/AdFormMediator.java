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
    private final JButton btnSubmit;

    public AdFormMediator(JComboBox<String> cbCategory, JTextField tfPrice, JLabel lblMaxPriceValue, JButton btnSubmit) {
        this.cbCategory = cbCategory;
        this.tfPrice = tfPrice;
        this.lblMaxPriceValue = lblMaxPriceValue;
        this.btnSubmit = btnSubmit;
    }

    public void notify(Component sender, String event) {
        if (sender == cbCategory && "selectionChanged".equals(event)) {
            String selectedCategory = (String) cbCategory.getSelectedItem();
            if ("Knjiga".equals(selectedCategory)) {
                lblMaxPriceValue.setText("500 €");
            } else if ("Ostali materijali".equals(selectedCategory)) {
                lblMaxPriceValue.setText("200 €");
            }
        }

        if (sender == tfPrice && "textChanged".equals(event)) {
            try {
                double enteredPrice = Double.parseDouble(tfPrice.getText());
                double maxPrice = "Knjiga".equals((String) cbCategory.getSelectedItem()) ? 500 : 200;

                if (enteredPrice > maxPrice) {
                    tfPrice.setBackground(Color.PINK); // Oboji polje crveno ako je cijena prevelika
                    btnSubmit.setEnabled(false); // Onemogući gumb Submit
                } else {
                    tfPrice.setBackground(Color.WHITE); // Vrati na bijelo
                    btnSubmit.setEnabled(true); // Omogući gumb Submit
                }
            } catch (NumberFormatException ex) {
                tfPrice.setBackground(Color.PINK); // Oboji crveno ako nije validan broj
                btnSubmit.setEnabled(false); // Onemogući gumb Submit
            }
        }
    }

}
