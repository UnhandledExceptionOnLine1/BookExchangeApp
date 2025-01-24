/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package hr.pibookexchange.view;

import hr.algebra.dal.RepositoryFactory;
import hr.algebra.dal.UserRepositoryInterface;
import hr.algebra.model.User;
import javax.swing.JLabel;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mockito;

/**
 *
 * @author bruno
 */
public class RegisterPanelTest {

    @Test
    void testEmptyFieldsValidation() {
        // Arrange
        RegisterPanel panel = new RegisterPanel();

        // Act
        panel.getBtnRegister().doClick();

        // Assert
        for (JLabel errorLabel : panel.getErrorLabels()) {
            assertTrue(errorLabel.isVisible(), "Error label should be visible for empty field");
        }
    }

    @Test
    void testUsernameValidation() {
        // Arrange
        RegisterPanel panel = new RegisterPanel();

        // Set up other fields to valid values to isolate username validation
        panel.getTfPassword().setText("validPassword");
        panel.getTfName().setText("ValidName");
        panel.getTfSurname().setText("ValidSurname");
        panel.getTfAddress().setText("ValidAddress");
        panel.getTfTelephone().setText("123456789");
        panel.getTfEmail().setText("email@example.com");

        // Invalid username (less than 3 characters)
        panel.getTfUsername().setText("us");

        // Act
        panel.getBtnRegister().doClick();

        // Assert
        assertTrue(panel.getLbUsernameError().isVisible(), "Username error label should be visible for invalid username");
        assertFalse(panel.getLbPasswordError().isVisible(), "Password error label should not be visible for valid password");
        assertFalse(panel.getLbNameError().isVisible(), "Name error label should not be visible for valid name");
        assertFalse(panel.getLbSurnameError().isVisible(), "Surname error label should not be visible for valid surname");
        assertFalse(panel.getLbAddressError().isVisible(), "Address error label should not be visible for valid address");
        assertFalse(panel.getLbTelephoneError().isVisible(), "Telephone error label should not be visible for valid telephone");
        assertFalse(panel.getLbEmailError().isVisible(), "Email error label should not be visible for valid email");
    }

    @Test
    void testPasswordValidation() {
        // Arrange
        RegisterPanel panel = new RegisterPanel();
        panel.getTfUsername().setText("validUser");
        panel.getTfPassword().setText("1234"); // Less than 5 characters
        panel.getTfName().setText("Valid Name");
        panel.getTfSurname().setText("Valid Surname");
        panel.getTfAddress().setText("Valid Address");
        panel.getTfTelephone().setText("123456789");
        panel.getTfEmail().setText("valid@example.com");

        // Act
        panel.getBtnRegister().doClick();

        // Assert
        assertTrue(panel.getLbPasswordError().isVisible(), "Password error label should be visible");
        assertFalse(panel.getLbUsernameError().isVisible(), "Username error label should not be visible");
        assertEquals("1234", panel.getTfPassword().getText(), "Password field should retain the entered value");
    }

    @Test
    void testSuccessfulRegistration() throws Exception {
        // Arrange
        RegisterPanel panel = new RegisterPanel();

        // Set valid inputs for all fields
        panel.getTfUsername().setText("validUser");
        panel.getTfPassword().setText("validPassword");
        panel.getTfName().setText("ValidName");
        panel.getTfSurname().setText("ValidSurname");
        panel.getTfAddress().setText("ValidAddress");
        panel.getTfTelephone().setText("123456789");
        panel.getTfEmail().setText("email@example.com");

        // Mock repository to simulate successful registration
        UserRepositoryInterface mockRepository = Mockito.mock(UserRepositoryInterface.class);
        RepositoryFactory.setMockRepository(mockRepository);

        // Act
        panel.getBtnRegister().doClick();

        // Assert
        Mockito.verify(mockRepository).createUser(Mockito.any(User.class));
        for (JLabel errorLabel : panel.getErrorLabels()) {
            assertFalse(errorLabel.isVisible(), "Error label should not be visible for valid input");
        }
        assertEquals("", panel.getTfUsername().getText(), "Fields should be cleared after successful registration");
    }

    @Test
    void testFailedRegistration() throws Exception {
        // Arrange
        RegisterPanel panel = new RegisterPanel();

        // Set valid inputs for all fields
        panel.getTfUsername().setText("validUser");
        panel.getTfPassword().setText("validPassword");
        panel.getTfName().setText("ValidName");
        panel.getTfSurname().setText("ValidSurname");
        panel.getTfAddress().setText("ValidAddress");
        panel.getTfTelephone().setText("123456789");
        panel.getTfEmail().setText("email@example.com");

        // Mock repository to throw an exception on createUser call
        UserRepositoryInterface mockRepository = Mockito.mock(UserRepositoryInterface.class);
        Mockito.doThrow(new RuntimeException("Registration failed")).when(mockRepository).createUser(Mockito.any(User.class));
        RepositoryFactory.setMockRepository(mockRepository);

        // Act
        panel.getBtnRegister().doClick();

        // Assert
        Mockito.verify(mockRepository).createUser(Mockito.any(User.class));
        for (JLabel errorLabel : panel.getErrorLabels()) {
            assertFalse(errorLabel.isVisible(), "Error label should not be visible for valid input");
        }
        // Ensure fields are not cleared
        assertEquals("validUser", panel.getTfUsername().getText(), "Fields should not be cleared after failed registration");
    }

}
