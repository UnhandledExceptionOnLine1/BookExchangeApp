/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.pibookexchange;

import hr.algebra.dal.sql.SqlRepository;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

/**
 *
 * @author Tin
 */
public class ComboBoxPopulator {

    private final SqlRepository repo;

    public ComboBoxPopulator(SqlRepository repo) {
        this.repo = repo;
    }

    public void populateCategoryComboBox(JComboBox<String> comboBox) {
        try {
            List<String> categoryNames = repo.getAllCategoryNames();
            DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
            for (String categoryName : categoryNames) {
                model.addElement(categoryName);
            }
            comboBox.setModel(model);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException("Error loading category names: " + ex.getMessage());
        }
    }

    public void populatePaymentComboBox(JComboBox<String> comboBox) {
        try {
            List<String> paymentNames = repo.getAllPaymentNames();
            DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
            for (String paymentName : paymentNames) {
                model.addElement(paymentName);
            }
            comboBox.setModel(model);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException("Error loading payment types: " + ex.getMessage());
        }
    }
}
