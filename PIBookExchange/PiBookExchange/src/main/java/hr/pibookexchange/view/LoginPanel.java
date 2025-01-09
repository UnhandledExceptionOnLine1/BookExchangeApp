/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package hr.pibookexchange.view;

import hr.algebra.dal.Repository;
import hr.algebra.dal.RepositoryFactory;
import hr.algebra.dal.sql.SqlRepository;
import hr.algebra.model.User;
import java.sql.Connection;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author bruno
 */
public class LoginPanel extends javax.swing.JPanel {

    /**
     * Creates new form LoginPanelForm
     */
    public LoginPanel() {
        initComponents();
        txtUsername.setText("");
        txtPassword.setText("");
        lblUsernameError.setVisible(false);
        lblPassswordError.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbUsername = new javax.swing.JLabel();
        lbPassword = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        txtPassword = new javax.swing.JPasswordField();
        btnRegister = new javax.swing.JButton();
        btnLogin = new javax.swing.JButton();
        lblPassswordError = new javax.swing.JLabel();
        lblUsernameError = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        lbUsername.setText("Username");

        lbPassword.setText("Password");

        btnRegister.setText("Register");

        btnLogin.setText("Login");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        lblPassswordError.setForeground(new java.awt.Color(255, 51, 51));
        lblPassswordError.setText("X");

        lblUsernameError.setForeground(new java.awt.Color(255, 51, 51));
        lblUsernameError.setText("X");

        jLabel1.setText("User Login");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbPassword)
                    .addComponent(lbUsername))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtUsername, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
                    .addComponent(txtPassword))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPassswordError)
                    .addComponent(lblUsernameError))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(106, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnLogin)
                        .addGap(18, 18, 18)
                        .addComponent(btnRegister)
                        .addGap(88, 88, 88))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(143, 143, 143))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbUsername)
                    .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblUsernameError))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbPassword)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPassswordError))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLogin)
                    .addComponent(btnRegister))
                .addGap(25, 25, 25))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        lblUsernameError.setVisible(false);
        lblPassswordError.setVisible(false);
        
        String username = txtUsername.getText();
        String password = new String(txtPassword.getPassword());
        
        boolean valid = true;
        
        if(username.isEmpty() || username.length() < 3){
            lblUsernameError.setVisible(true);
            JOptionPane.showMessageDialog(this, "Korisničko ime mora sadržavati najmanje 3 znaka.", "Greška", JOptionPane.ERROR_MESSAGE);
            valid = false;
        }
        if (password.isEmpty() || password.length() < 5){
            lblPassswordError.setVisible(true);
            JOptionPane.showMessageDialog(this, "Lozinka mora sadržavati najmanje 5 znakova.", "Greška", JOptionPane.ERROR_MESSAGE);
            valid = false;
        }
        if(!valid){
            JOptionPane.showMessageDialog(this, "Molim popunite sva polja", "Greška", JOptionPane.ERROR_MESSAGE);
        }
        
        try {
            Repository repo = RepositoryFactory.getRepository(); //returns sql repository        
            Optional<User> user = repo.loginUser(username, password);
            if (user.isPresent()) {
                JOptionPane.showMessageDialog(this, "Prijava uspješna!", "Prijava", JOptionPane.INFORMATION_MESSAGE);
            } else {    
                JOptionPane.showMessageDialog(this, "Pogrešno korisničko ime ili lozinka.", "Greška", JOptionPane.ERROR_MESSAGE);                
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
    }//GEN-LAST:event_btnLoginActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogin;
    private javax.swing.JButton btnRegister;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lbPassword;
    private javax.swing.JLabel lbUsername;
    private javax.swing.JLabel lblPassswordError;
    private javax.swing.JLabel lblUsernameError;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}