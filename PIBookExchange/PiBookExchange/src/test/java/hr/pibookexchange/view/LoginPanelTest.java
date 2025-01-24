/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package hr.pibookexchange.view;

import hr.algebra.dal.RepositoryFactory;
import hr.algebra.dal.UserRepositoryInterface;
import hr.algebra.model.User;
import java.util.Optional;
import javax.swing.JOptionPane;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

/**
 *
 * @author bruno
 */
public class LoginPanelTest {

    @Test
    void testLoginValidationUserNameTooShort() {
        // Arrange
        LoginPanel panel = new LoginPanel();
        panel.getTxtUsername().setText("ab");
        panel.getTxtPassword().setText("validPassword");

        // Act
        panel.getBtnLogin().doClick();

        // Assert
        assertTrue(panel.getLblUsernameError().isVisible());
        assertFalse(panel.getLblPassswordError().isVisible());
    }

    @Test
    void testLoginValidationUserNameNotProvided() {
        // Arrange
        LoginPanel panel = new LoginPanel();
        panel.getTxtUsername().setText("");
        panel.getTxtPassword().setText("validPassword");

        // Act
        panel.getBtnLogin().doClick();

        // Assert
        assertTrue(panel.getLblUsernameError().isVisible());
        assertFalse(panel.getLblPassswordError().isVisible());
    }

    @Test
    void testLoginValidationPasswordNotProvided() {
        // Arrange
        LoginPanel panel = new LoginPanel();
        panel.getTxtUsername().setText("validUsername");
        panel.getTxtPassword().setText("");

        // Act
        panel.getBtnLogin().doClick();

        // Assert
        assertFalse(panel.getLblUsernameError().isVisible());
        assertTrue(panel.getLblPassswordError().isVisible());
    }

    @Test
    void testLoginValidationPasswordTooShort() {
        // Arrange
        LoginPanel panel = new LoginPanel();
        panel.getTxtUsername().setText("validUsername");
        panel.getTxtPassword().setText("");

        // Act
        panel.getBtnLogin().doClick();

        // Assert
        assertFalse(panel.getLblUsernameError().isVisible());
        assertTrue(panel.getLblPassswordError().isVisible());
    }

    @Test
    void testSuccessfulLogin() throws Exception {
        // Arrange
        LoginPanel panel = new LoginPanel();
        panel.getTxtUsername().setText("validUsername");
        panel.getTxtPassword().setText("validPassword");

        UserRepositoryInterface mockRepository = Mockito.mock(UserRepositoryInterface.class);
        when(mockRepository.loginUser("validUsername", "validPassword"))
                .thenReturn(Optional.of(new User()));

        RepositoryFactory.setMockRepository(mockRepository);

        // Act
        panel.getBtnLogin().doClick();

        // Assert
        assertTrue(panel.getParentFrame() == null || !panel.getParentFrame().isVisible());
    }


}
