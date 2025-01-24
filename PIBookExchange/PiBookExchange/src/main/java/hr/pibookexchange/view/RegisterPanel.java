/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package hr.pibookexchange.view;

import hr.algebra.dal.RepositoryFactory;
import hr.algebra.model.User;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import hr.algebra.dal.UserRepositoryInterface;
import javax.swing.JButton;

/**
 *
 * @author bruno
 */
public class RegisterPanel extends javax.swing.JPanel {

    private List<JTextField> textFields;
    private List<JLabel> errorLabels;

    public List<JLabel> getErrorLabels() {
        return errorLabels;
    }
    private MainFrame parentFrame;

    /**
     * Creates new form RegisterPanel
     */
    public RegisterPanel(MainFrame parentFrame) {
        this.parentFrame = parentFrame;
        initComponents();
        initTextFields();
        initErrorLabels();
        cleanTextFields();
        hideErrorLabels();
    }

    // mora imati default konstruktor zbog inicijalizacije forme!
    public RegisterPanel() {
        this(null); // Pozivanje prilagođenog konstruktora s null
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblUserRegistration = new javax.swing.JLabel();
        lbUsername = new javax.swing.JLabel();
        tfUsername = new javax.swing.JTextField();
        lbUsernameError = new javax.swing.JLabel();
        lbPassword = new javax.swing.JLabel();
        tfPassword = new javax.swing.JTextField();
        lbPasswordError = new javax.swing.JLabel();
        lbName = new javax.swing.JLabel();
        tfName = new javax.swing.JTextField();
        lbNameError = new javax.swing.JLabel();
        lbSurname = new javax.swing.JLabel();
        tfSurname = new javax.swing.JTextField();
        lbSurnameError = new javax.swing.JLabel();
        lbAddress = new javax.swing.JLabel();
        tfAddress = new javax.swing.JTextField();
        lbAddressError = new javax.swing.JLabel();
        lbTelephone = new javax.swing.JLabel();
        tfTelephone = new javax.swing.JTextField();
        lbTelephoneError = new javax.swing.JLabel();
        lbEmail = new javax.swing.JLabel();
        tfEmail = new javax.swing.JTextField();
        lbEmailError = new javax.swing.JLabel();
        btnLogin = new javax.swing.JButton();
        btnRegister = new javax.swing.JButton();
        lblUserRegistration1 = new javax.swing.JLabel();

        setMaximumSize(new java.awt.Dimension(400, 320));
        setMinimumSize(new java.awt.Dimension(400, 320));
        setPreferredSize(new java.awt.Dimension(400, 320));

        lblUserRegistration.setText("BOOK EXCHANGE PLATFORM");
        lblUserRegistration.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        lbUsername.setText("Username");

        lbUsernameError.setForeground(new java.awt.Color(255, 51, 51));
        lbUsernameError.setText("X");

        lbPassword.setText("Password");

        lbPasswordError.setForeground(new java.awt.Color(255, 51, 51));
        lbPasswordError.setText("X");

        lbName.setText("Name");

        lbNameError.setForeground(new java.awt.Color(255, 51, 51));
        lbNameError.setText("X");

        lbSurname.setText("Surname");

        lbSurnameError.setForeground(new java.awt.Color(255, 51, 51));
        lbSurnameError.setText("X");

        lbAddress.setText("Address");

        lbAddressError.setForeground(new java.awt.Color(255, 51, 51));
        lbAddressError.setText("X");

        lbTelephone.setText("Telephone");

        lbTelephoneError.setForeground(new java.awt.Color(255, 51, 51));
        lbTelephoneError.setText("X");

        lbEmail.setText("Email");

        lbEmailError.setForeground(new java.awt.Color(255, 51, 51));
        lbEmailError.setText("X");

        btnLogin.setText("Login");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        btnRegister.setText("Register");
        btnRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegisterActionPerformed(evt);
            }
        });

        lblUserRegistration1.setText("User Registration");
        lblUserRegistration1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(71, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbAddress)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbAddressError))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(lbPassword)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(tfPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lbPasswordError))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(lbName)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(tfName, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lbNameError))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(lbSurname)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(tfSurname, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lbSurnameError)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbTelephone)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfTelephone, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbTelephoneError))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbEmail)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnLogin)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnRegister))
                            .addComponent(tfEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbEmailError))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbUsername)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblUserRegistration))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbUsernameError)))
                .addContainerGap(99, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblUserRegistration1)
                .addGap(151, 151, 151))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblUserRegistration)
                .addGap(4, 4, 4)
                .addComponent(lblUserRegistration1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbUsername)
                    .addComponent(tfUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbUsernameError))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbPassword)
                    .addComponent(tfPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbPasswordError))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbName)
                    .addComponent(tfName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbNameError))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbSurname)
                    .addComponent(tfSurname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbSurnameError))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbAddress)
                    .addComponent(tfAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbAddressError))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbTelephone)
                    .addComponent(tfTelephone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbTelephoneError))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbEmail)
                    .addComponent(tfEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbEmailError))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLogin)
                    .addComponent(btnRegister))
                .addContainerGap(90, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegisterActionPerformed

        hideErrorLabels();
        boolean valid = true;
        // Provjera ispunjenosti svih tekstualnih polja
        for (int i = 0; i < textFields.size(); i++) {
            JTextField textField = textFields.get(i);
            JLabel errorLabel = errorLabels.get(i);
            if (textField.getText().isEmpty()) {
                errorLabel.setVisible(true);
                valid = false;
            }
        }

        if (!valid) {
            JOptionPane.showMessageDialog(this, "Molim popunite sva polja.", "Greška", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String username = tfUsername.getText();
        String password = tfPassword.getText();
        String name = tfName.getText();
        String surname = tfSurname.getText();
        String address = tfAddress.getText();
        String telephone = tfTelephone.getText();
        String email = tfEmail.getText();

        if (username.isEmpty() || username.length() < 3) {
            lbUsernameError.setVisible(true);
            JOptionPane.showMessageDialog(this, "Korisničko ime mora sadržavati najmanje 3 znaka.", "Greška", JOptionPane.ERROR_MESSAGE);
            valid = false;
        }
        if (password.isEmpty() || password.length() < 5) {
            lbPasswordError.setVisible(true);
            JOptionPane.showMessageDialog(this, "Lozinka mora sadržavati najmanje 5 znakova.", "Greška", JOptionPane.ERROR_MESSAGE);
            valid = false;
        }
//        if (!valid) {
//            JOptionPane.showMessageDialog(this, "Molim popunite sva polja", "Greška", JOptionPane.ERROR_MESSAGE);
//        }

        try {
       if (valid) {
            ((UserRepositoryInterface) RepositoryFactory.getRepository()).createUser(new User(
                    username,
                    password,
                    name,
                    surname,
                    address,
                    telephone,
                    email,
                    false)); //postavljanje IDRola na Korisnik (boolean isAdmin = false)
     
                JOptionPane.showMessageDialog(this, "Korisnik je uspješno registriran!", "Uspjeh", JOptionPane.INFORMATION_MESSAGE);
                cleanTextFields();
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Dogodila se greška prilikom registracije. Pokušajte ponovo.", "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnRegisterActionPerformed

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        parentFrame.showPanel("LoginPanel");
    }//GEN-LAST:event_btnLoginActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogin;
    private javax.swing.JButton btnRegister;
    private javax.swing.JLabel lbAddress;
    private javax.swing.JLabel lbAddressError;
    private javax.swing.JLabel lbEmail;
    private javax.swing.JLabel lbEmailError;
    private javax.swing.JLabel lbName;
    private javax.swing.JLabel lbNameError;
    private javax.swing.JLabel lbPassword;
    private javax.swing.JLabel lbPasswordError;
    private javax.swing.JLabel lbSurname;
    private javax.swing.JLabel lbSurnameError;
    private javax.swing.JLabel lbTelephone;
    private javax.swing.JLabel lbTelephoneError;
    private javax.swing.JLabel lbUsername;
    private javax.swing.JLabel lbUsernameError;
    private javax.swing.JLabel lblUserRegistration;
    private javax.swing.JLabel lblUserRegistration1;
    private javax.swing.JTextField tfAddress;
    private javax.swing.JTextField tfEmail;
    private javax.swing.JTextField tfName;
    private javax.swing.JTextField tfPassword;
    private javax.swing.JTextField tfSurname;
    private javax.swing.JTextField tfTelephone;
    private javax.swing.JTextField tfUsername;
    // End of variables declaration//GEN-END:variables

    private void initTextFields() {
        textFields = Arrays.asList(
                tfUsername,
                tfPassword,
                tfName,
                tfSurname,
                tfAddress,
                tfTelephone,
                tfEmail);
    }

    private void cleanTextFields() {
        for (JTextField textField : textFields) {
            textField.setText("");
        }
    }

    private void initErrorLabels() {
        errorLabels = Arrays.asList(
                lbUsernameError,
                lbPasswordError,
                lbNameError,
                lbSurnameError,
                lbAddressError,
                lbTelephoneError,
                lbEmailError);
    }

    private void hideErrorLabels() {
        for (JLabel label : errorLabels) {
            label.setVisible(false);
        }
    }

    //test getters
    public javax.swing.JButton getBtnRegister() {
        return btnRegister;
    }

    public List<JTextField> getTextFields() {
        return textFields;
    }

    public MainFrame getParentFrame() {
        return parentFrame;
    }

    public JButton getBtnLogin() {
        return btnLogin;
    }

    public JLabel getLbAddress() {
        return lbAddress;
    }

    public JLabel getLbAddressError() {
        return lbAddressError;
    }

    public JLabel getLbEmail() {
        return lbEmail;
    }

    public JLabel getLbEmailError() {
        return lbEmailError;
    }

    public JLabel getLbName() {
        return lbName;
    }

    public JLabel getLbNameError() {
        return lbNameError;
    }

    public JLabel getLbPassword() {
        return lbPassword;
    }

    public JLabel getLbPasswordError() {
        return lbPasswordError;
    }

    public JLabel getLbSurname() {
        return lbSurname;
    }

    public JLabel getLbSurnameError() {
        return lbSurnameError;
    }

    public JLabel getLbTelephone() {
        return lbTelephone;
    }

    public JLabel getLbTelephoneError() {
        return lbTelephoneError;
    }

    public JLabel getLbUsername() {
        return lbUsername;
    }

    public JLabel getLbUsernameError() {
        return lbUsernameError;
    }

    public JLabel getLblUserRegistration() {
        return lblUserRegistration;
    }

    public JLabel getLblUserRegistration1() {
        return lblUserRegistration1;
    }

    public JTextField getTfAddress() {
        return tfAddress;
    }

    public JTextField getTfEmail() {
        return tfEmail;
    }

    public JTextField getTfName() {
        return tfName;
    }

    public JTextField getTfPassword() {
        return tfPassword;
    }

    public JTextField getTfSurname() {
        return tfSurname;
    }

    public JTextField getTfTelephone() {
        return tfTelephone;
    }

    public JTextField getTfUsername() {
        return tfUsername;
    }
}
