/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package hr.pibookexchange;

import hr.algebra.dal.Repository;
import hr.algebra.dal.RepositoryFactory;
import hr.algebra.dal.UserRepositoryInterface;
import hr.algebra.dal.sql.SqlRepository;
import hr.algebra.model.Ad;
import hr.algebra.model.User;
import hr.algebra.observer.NewAdNotifier;
import hr.algebra.uploads.DropboxStorageAdapter;
import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import hr.algebra.uploads.StorageService;
import hr.algebra.builder.AdBuilder;
import hr.algebra.mediator.AdFormMediator;

/**
 *
 * @author Tin
 */
public class CreateAdForm extends javax.swing.JFrame {

    private String selectedImagePath = ""; // Čuva putanju slike
    private final StorageService storageService;

    //Observer pattern Bruno (Tin ne diraj ovo)
    private NewAdNotifier notifyer;
    private List<User> admins;
    private AdFormMediator mediator;
    ComboBoxPopulator populator = new ComboBoxPopulator(new SqlRepository());

    /**
     * Creates new form AddAdForm
     */
    public CreateAdForm() {
        initComponents();
        storageService = new DropboxStorageAdapter();
        populator.populateCategoryComboBox(cbCategory);
        populator.populatePaymentComboBox(cbPayment);

        notifyer = new NewAdNotifier();
        admins = new ArrayList<>();
        mediator = new AdFormMediator(cbCategory, tfPrice, lblMaxPriceValue, btnSubmit);

        try {   // punjenje liste admina
            Repository repo = RepositoryFactory.getRepository();
            admins = ((UserRepositoryInterface) repo).selectAllUsers();
            for (User user : admins) {
                if (user.getIsAdmin()) {
                    notifyer.add(user);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error loading users: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }

        cbCategory.addActionListener(e -> mediator.notify(cbCategory, "selectionChanged"));

        tfPrice.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                mediator.notify(tfPrice, "textChanged");
            }

            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                mediator.notify(tfPrice, "textChanged");
            }

            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                mediator.notify(tfPrice, "textChanged");
            }
        });

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cbCategory = new javax.swing.JComboBox<>();
        cbPayment = new javax.swing.JComboBox<>();
        tfPrice = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        taDescription = new javax.swing.JTextArea();
        btnSubmit = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        btnUploadImage = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        tfAdName1 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        lblMaxPrice = new javax.swing.JLabel();
        lblMaxPriceValue = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Naziv:");

        jLabel2.setText("Vrsta Naplate");

        jLabel3.setText("Kategorija");

        jLabel4.setText("Opis");

        cbCategory.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbCategory.setBorder(null);

        cbPayment.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbPayment.setBorder(null);

        tfPrice.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(1, 1, 1), 1, true));

        taDescription.setColumns(20);
        taDescription.setRows(5);
        taDescription.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(1, 1, 1), 1, true));
        jScrollPane1.setViewportView(taDescription);

        btnSubmit.setText("Submit");
        btnSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubmitActionPerformed(evt);
            }
        });

        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        jLabel5.setText("Odabrana slika:");

        btnUploadImage.setText("Odaberi sliku");
        btnUploadImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUploadImageActionPerformed(evt);
            }
        });

        jLabel6.setText("Cijena:");

        tfAdName1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(1, 1, 1), 1, true));

        jLabel7.setText("€");

        lblMaxPrice.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        lblMaxPrice.setText("max:");

        lblMaxPriceValue.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        lblMaxPriceValue.setText("N/A");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbPayment, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfAdName1, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(66, 66, 66)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnUploadImage, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(49, 49, 49)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblMaxPrice)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblMaxPriceValue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(tfPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(btnSubmit)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(btnCancel))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 453, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(130, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfAdName1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUploadImage, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbPayment, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblMaxPrice)
                        .addComponent(lblMaxPriceValue))
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSubmit)
                    .addComponent(btnCancel))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnUploadImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUploadImageActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Image files", "jpg", "png", "jpeg"));

        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            selectedImagePath = fileChooser.getSelectedFile().getAbsolutePath();
            jLabel5.setText("Odabrana slika: " + selectedImagePath);
            System.out.println("Selected Image Path: " + selectedImagePath); // Debug log
        } else {
            JOptionPane.showMessageDialog(this, "Nije odabrana slika.");
        }
    }//GEN-LAST:event_btnUploadImageActionPerformed

    private void btnSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubmitActionPerformed
        String adName = tfAdName1.getText();
        String category = cbCategory.getSelectedItem().toString();
        String paymentType = cbPayment.getSelectedItem().toString();
        String description = taDescription.getText();
        String imagePath = selectedImagePath;
        String priceText = tfPrice.getText();
        double price;
        try {
            price = Double.parseDouble(priceText);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number for the price.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int korisnikID = 1; //default id za testiranje

        if (adName.isEmpty() || category.isEmpty() || paymentType.isEmpty() || description.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill out all fields before submitting.");
            return;
        }

        //UPload na DROPBOX
        String imageName = ""; // Spremanje imena slike
        if (!selectedImagePath.isEmpty()) {
            try {
                //Rezanje imena iz putanje
                imageName = new File(selectedImagePath).getName(); // Dobivamo npr. "programiranje.jpg"

                // Pošalji sliku na Dropbox ---ADAPTER
                String remotePath = "/" + imageName; // Putanja na Dropboxu
                storageService.uploadFile(selectedImagePath, remotePath);

                JOptionPane.showMessageDialog(this, "Slika je uspješno prenesena na Dropbox!");
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Došlo je do pogreške prilikom prijenosa slike na Dropbox.", "Greška", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } else {
            JOptionPane.showMessageDialog(this, "Nije odabrana slika. Molimo odaberite sliku prije nego što nastavite.");
            return;
        }
        try {
            SqlRepository repo = new SqlRepository();
            int categoryId = repo.getCategoryIdByName(category);
            int paymentId = repo.getPaymentIdByName(paymentType);

            Ad ad = new AdBuilder(adName, korisnikID)
                    .categoryId(categoryId)
                    .paymentTypeId(paymentId)
                    .imagePath(imageName)
                    .description(description)
                    .price(price)
                    .build();

            int x = repo.createAd(ad);

            JOptionPane.showMessageDialog(this, "Oglas je uspješno spremljen!");
            notifyer.notify("Dodan je novi oglas. Oglas ID: " + x);
            clearForm(); // Clear the form after successful submission

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error finding category: " + ex.getMessage());
            ex.printStackTrace();
            return;
        }

    }//GEN-LAST:event_btnSubmitActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CreateAdForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CreateAdForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CreateAdForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CreateAdForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CreateAdForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnSubmit;
    private javax.swing.JButton btnUploadImage;
    private javax.swing.JComboBox<String> cbCategory;
    private javax.swing.JComboBox<String> cbPayment;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblMaxPrice;
    private javax.swing.JLabel lblMaxPriceValue;
    private javax.swing.JTextArea taDescription;
    private javax.swing.JTextField tfAdName1;
    private javax.swing.JTextField tfPrice;
    // End of variables declaration//GEN-END:variables

    private void clearForm() {
        tfAdName1.setText("");
        cbCategory.setSelectedIndex(0);
        cbPayment.setSelectedIndex(0);
        taDescription.setText("");
        jLabel5.setText("Odabrana slika:");
        selectedImagePath = "";
        System.out.println("Image Path Cleared in clearForm");
        tfPrice.setText("");
    }

   

}
