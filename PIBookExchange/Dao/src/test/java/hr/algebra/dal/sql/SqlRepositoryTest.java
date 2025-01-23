/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package hr.algebra.dal.sql;

import hr.algebra.model.User;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mockito;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 *
 * @author bruno
 */
public class SqlRepositoryTest {

    // USER_ADMINISTRATION_CONSTANTS
    private static final String ID_USER = "IDKorisnik";
    private static final String USER_NAME = "KorisnickoIme";
    private static final String PASSWORD = "Lozinka";
    private static final String FIRST_NAME = "Ime";
    private static final String LAST_NAME = "Prezime";
    private static final String ADDRESS = "Adresa";
    private static final String TELEPHONE = "Telefon";
    private static final String EMAIL = "Email";
    private static final String MSG = "message";
    private static final String ID_ROLA = "IDRola";

    @Test
    void testCreateUserWithValidParameters() throws Exception {
        // Arrange
        User testUser = new User("username", "password", "FirstName", "LastName", "Address", "123456789", "email@test.com", false);
        // Mock dependencies
        DataSource mockDataSource = Mockito.mock(DataSource.class);
        Connection mockConnection = Mockito.mock(Connection.class);
        CallableStatement mockStatement = Mockito.mock(CallableStatement.class);
        // Mock behavior => //KAD SE PROBA SPOJIT DAJ MU KONEKCIJU KUZIS
        when(mockDataSource.getConnection()).thenReturn(mockConnection);
        when(mockConnection.prepareCall("{ CALL CreateUser (?,?,?,?,?,?,?,?) }"))
                .thenReturn(mockStatement);
        // Mock the output parameter for the generated ID
        when(mockStatement.getInt(8)).thenReturn(123); // Dynamic value simulated by mock
        // Inject the mock DataSource into the repository
        SqlRepository repository = new SqlRepository(mockDataSource);
        // Act
        int result = repository.createUser(testUser);
        // Assert
        verify(mockStatement).setString(1, "username");
        verify(mockStatement).setString(2, "password");
        verify(mockStatement).setString(3, "FirstName");
        verify(mockStatement).setString(4, "LastName");
        verify(mockStatement).setString(5, "Address");
        verify(mockStatement).setString(6, "123456789");
        verify(mockStatement).setString(7, "email@test.com");
        verify(mockStatement).registerOutParameter(8, java.sql.Types.INTEGER);
        verify(mockStatement).executeUpdate();
        // Verify that the returned result matches the mocked value
        assertTrue(result > 0); // Ensure the returned ID is a valid, non-hardcoded value
        assertEquals(123, result); // Optional: verify the mocked ID
    }

    @Test
    void testCreateUserWithInvalidParameters() throws Exception {
        User testUser = new User("username", "password", "FirstName", "LastName", "Address", "123456789", "email@test.com", false);

        //mokaj datasors, konekciju i stejtment
        //nebuš mu dao pravi kužiš nitko se ne spaja na bazu tu
        DataSource mockDataSource = Mockito.mock(DataSource.class);
        Connection mockConnection = Mockito.mock(Connection.class);
        CallableStatement mockStmt = Mockito.mock(CallableStatement.class);

        //KAD SE PROBA SPOJIT DAJ MU KONEKCIJU KUZIS
        when(mockDataSource.getConnection()).thenReturn(mockConnection);
        when(mockConnection.prepareCall("{ CALL CreateUser (?,?,?,?,?,?,?,?) }"))
                .thenReturn(mockStmt);

        //IZBACI TO VAN, NEKAJ NE VALJA S BAZOM, NIJE SE SPOJILO VALJDA
        doThrow(new SQLException("Error in DB call")).when(mockStmt).executeUpdate();

        SqlRepository repo = new SqlRepository(mockDataSource);

        Exception e = assertThrows(SQLException.class, () -> {
            repo.createUser(testUser);
        });

        assertEquals("Error in DB call", e.getMessage());
    }

    @Test
    void testUpdateUserWithValidId() throws Exception {

        int userId = 1;
        User validUser = new User("updatedUsername", "updatedPassword", "UpdatedFirstName", "UpdatedLastName", "UpdatedAddress", "987654321", "updated@email.com", false);

        String successMessage = "Ažuriranje korisnika uspješno."; // Expected success message

        DataSource mockDataSource = Mockito.mock(DataSource.class);
        Connection mockConnection = Mockito.mock(Connection.class);
        CallableStatement mockStatement = Mockito.mock(CallableStatement.class);

        when(mockDataSource.getConnection()).thenReturn(mockConnection);
        when(mockConnection.prepareCall("{ CALL UpdateUser (?,?,?,?,?,?,?,?) }")).thenReturn(mockStatement);
        when(mockStatement.getString("message")).thenReturn(successMessage);

        SqlRepository repository = new SqlRepository(mockDataSource);

        repository.updateUser(userId, validUser);

        verify(mockStatement).setInt("IDKorisnik", userId);
        verify(mockStatement).setString("KorisnickoIme", validUser.getUserName());
        verify(mockStatement).setString("Lozinka", validUser.getPassword());
        verify(mockStatement).setString("Ime", validUser.getFirstName());
        verify(mockStatement).setString("Prezime", validUser.getLastName());
        verify(mockStatement).setString("Adresa", validUser.getAddress());
        verify(mockStatement).setString("Telefon", validUser.getTelephone());
        verify(mockStatement).setString("Email", validUser.getEmail());
        verify(mockStatement).executeUpdate();
        verify(mockStatement).getString("message");
    }

    @Test
    void testUpdateUserWithInvalidUserId() throws Exception {
        // Arrange
        int invalidUserId = -999; // Invalid user ID
        User validUser = new User("updatedUsername", "updatedPassword", "UpdatedFirstName", "UpdatedLastName", "UpdatedAddress", "987654321", "updated@email.com", false);

        String errorMessage = "Korisnik s ID-jem ne postoji."; // Expected message from the procedure

        // Mock dependencies
        DataSource mockDataSource = Mockito.mock(DataSource.class);
        Connection mockConnection = Mockito.mock(Connection.class);
        CallableStatement mockStatement = Mockito.mock(CallableStatement.class);

        // Mock behavior
        when(mockDataSource.getConnection()).thenReturn(mockConnection);
        when(mockConnection.prepareCall("{ CALL UpdateUser (?,?,?,?,?,?,?,?) }")).thenReturn(mockStatement);
        when(mockStatement.getString("message")).thenReturn(errorMessage); // Simulate procedure's error message
        doThrow(new SQLException("Invalid user ID")).when(mockStatement).executeUpdate(); // Simulate SQL exception

        SqlRepository repository = new SqlRepository(mockDataSource);

        // Act & Assert
        Exception exception = assertThrows(SQLException.class, () -> {
            repository.updateUser(invalidUserId, validUser); // Call with invalid ID
        });

        // Assertions
        assertEquals("Invalid user ID", exception.getMessage());
        verify(mockStatement).setInt("IDKorisnik", invalidUserId); // Verify invalid ID was passed
        verify(mockStatement).executeUpdate();
    }

    @Test
    void testDeleteUserWithValidUserId() throws Exception {
        // Arrange
        int validUserId = 1; // Valid user ID
        String successMessage = "Brisanje korisnika uspješno."; // Expected message

        // Mock dependencies
        DataSource mockDataSource = Mockito.mock(DataSource.class);
        Connection mockConnection = Mockito.mock(Connection.class);
        CallableStatement mockStatement = Mockito.mock(CallableStatement.class);

        // Mock behavior
        when(mockDataSource.getConnection()).thenReturn(mockConnection);
        when(mockConnection.prepareCall("{ CALL DeleteUser (?) }")).thenReturn(mockStatement);
        when(mockStatement.getString("message")).thenReturn(successMessage); // Simulate success message

        SqlRepository repository = new SqlRepository(mockDataSource);

        // Act
        repository.deleteUser(validUserId);

        // Assert
        verify(mockStatement).setInt(1, validUserId); // Ensure the correct ID was passed
        verify(mockStatement).executeUpdate(); // Ensure the procedure was executed
        verify(mockStatement).getString("message"); // Verify the message retrieval
    }

    @Test
    void testDeleteUserWithInvalidUserId() throws Exception {
        // Arrange
        int invalidUserId = -1; // Invalid user ID
        String errorMessage = "Korisnik s ID-jem ne postoji."; // Expected error message

        // Mock dependencies
        DataSource mockDataSource = Mockito.mock(DataSource.class);
        Connection mockConnection = Mockito.mock(Connection.class);
        CallableStatement mockStatement = Mockito.mock(CallableStatement.class);

        // Mock behavior
        when(mockDataSource.getConnection()).thenReturn(mockConnection);
        when(mockConnection.prepareCall("{ CALL DeleteUser (?) }")).thenReturn(mockStatement);
        when(mockStatement.getString("message")).thenReturn(errorMessage); // Simulate error message
        doThrow(new SQLException("Invalid user ID")).when(mockStatement).executeUpdate(); // Simulate exception for invalid ID

        SqlRepository repository = new SqlRepository(mockDataSource);

        // Act & Assert
        Exception exception = assertThrows(SQLException.class, () -> {
            repository.deleteUser(invalidUserId); // Call with invalid ID
        });

        // Assertions
        assertEquals("Invalid user ID", exception.getMessage());
        verify(mockStatement).setInt(1, invalidUserId); // Ensure the invalid ID was passed
        verify(mockStatement).executeUpdate(); // Ensure the procedure was executed
    }

}
