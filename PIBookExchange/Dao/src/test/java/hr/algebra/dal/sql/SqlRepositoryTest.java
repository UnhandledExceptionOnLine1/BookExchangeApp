/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package hr.algebra.dal.sql;

import hr.algebra.model.User;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
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

    @Test
    void testGetUserWithValidId() throws Exception {
        // Arrange
        int validUserId = 1; // Valid user ID
        User expectedUser = new User(
                validUserId,
                "testUser",
                "password123",
                "FirstName",
                "LastName",
                "123 Fake Street",
                "123456789",
                "test@example.com",
                false // Not an admin
        );

        // Mock dependencies
        DataSource mockDataSource = Mockito.mock(DataSource.class);
        Connection mockConnection = Mockito.mock(Connection.class);
        CallableStatement mockStatement = Mockito.mock(CallableStatement.class);
        ResultSet mockResultSet = Mockito.mock(ResultSet.class);

        // Mock behavior
        when(mockDataSource.getConnection()).thenReturn(mockConnection);
        when(mockConnection.prepareCall("{ CALL GetUser (?) }")).thenReturn(mockStatement);
        when(mockStatement.executeQuery()).thenReturn(mockResultSet);

        // Simulate ResultSet data for a valid user
        when(mockResultSet.next()).thenReturn(true); // Simulate that a user was found
        when(mockResultSet.getInt("IDKorisnik")).thenReturn(expectedUser.getId());
        when(mockResultSet.getString("KorisnickoIme")).thenReturn(expectedUser.getUserName());
        when(mockResultSet.getString("Lozinka")).thenReturn(expectedUser.getPassword());
        when(mockResultSet.getString("Ime")).thenReturn(expectedUser.getFirstName());
        when(mockResultSet.getString("Prezime")).thenReturn(expectedUser.getLastName());
        when(mockResultSet.getString("Adresa")).thenReturn(expectedUser.getAddress());
        when(mockResultSet.getString("Telefon")).thenReturn(expectedUser.getTelephone());
        when(mockResultSet.getString("Email")).thenReturn(expectedUser.getEmail());
        when(mockResultSet.getInt("IDRola")).thenReturn(2); // Not an admin (IDRola != 1)

        SqlRepository repository = new SqlRepository(mockDataSource);

        // Act
        Optional<User> result = repository.getUser(validUserId);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(expectedUser, result.get()); // Validate the retrieved user matches the expected user
        verify(mockStatement).setInt("IDKorisnik", validUserId); // Verify correct ID was passed
        verify(mockStatement).executeQuery(); // Verify query execution
    }

    @Test
    void testGetUserWithInvalidIdReturnsEmpty() throws Exception {
        // Arrange
        int invalidUserId = -1; // Invalid user ID

        // Mock dependencies
        DataSource mockDataSource = Mockito.mock(DataSource.class);
        Connection mockConnection = Mockito.mock(Connection.class);
        CallableStatement mockStatement = Mockito.mock(CallableStatement.class);
        ResultSet mockResultSet = Mockito.mock(ResultSet.class);

        when(mockDataSource.getConnection()).thenReturn(mockConnection);
        when(mockConnection.prepareCall("{ CALL GetUser (?) }")).thenReturn(mockStatement);
        when(mockStatement.executeQuery()).thenReturn(mockResultSet);

        // Simulate ResultSet returning no rows
        when(mockResultSet.next()).thenReturn(false);

        SqlRepository repository = new SqlRepository(mockDataSource);

        // Act
        Optional<User> result = repository.getUser(invalidUserId);

        // Assert
        assertFalse(result.isPresent()); // Ensure result is empty
        verify(mockStatement).setInt("IDKorisnik", invalidUserId); // Verify the correct ID was set
        verify(mockStatement).executeQuery(); // Ensure the query was executed
    }

    @Test
    void testSelectAllUsers() throws Exception {
        // Arrange
        List<User> expectedUsers = List.of(
                new User(1, "user1", "password1", "FirstName1", "LastName1", "Address1", "123456789", "user1@example.com", false),
                new User(2, "user2", "password2", "FirstName2", "LastName2", "Address2", "987654321", "user2@example.com", true)
        );

        DataSource mockDataSource = Mockito.mock(DataSource.class);
        Connection mockConnection = Mockito.mock(Connection.class);
        CallableStatement mockStatement = Mockito.mock(CallableStatement.class);
        ResultSet mockResultSet = Mockito.mock(ResultSet.class);

        when(mockDataSource.getConnection()).thenReturn(mockConnection);
        when(mockConnection.prepareCall("{ CALL GetAllUsers }")).thenReturn(mockStatement);
        when(mockStatement.executeQuery()).thenReturn(mockResultSet);

        // Mock ResultSet behavior for each user
        when(mockResultSet.next())
                .thenReturn(true) // First user
                .thenReturn(true) // Second user
                .thenReturn(false); // End of result set

        // Mock data for the first user
        when(mockResultSet.getInt("IDKorisnik")).thenReturn(1, 2);
        when(mockResultSet.getString("KorisnickoIme")).thenReturn("user1", "user2");
        when(mockResultSet.getString("Lozinka")).thenReturn("password1", "password2");
        when(mockResultSet.getString("Ime")).thenReturn("FirstName1", "FirstName2");
        when(mockResultSet.getString("Prezime")).thenReturn("LastName1", "LastName2");
        when(mockResultSet.getString("Adresa")).thenReturn("Address1", "Address2");
        when(mockResultSet.getString("Telefon")).thenReturn("123456789", "987654321");
        when(mockResultSet.getString("Email")).thenReturn("user1@example.com", "user2@example.com");
        when(mockResultSet.getInt("IDRola")).thenReturn(2, 1); // Second user is an admin

        SqlRepository repository = new SqlRepository(mockDataSource);

        // Act
        List<User> actualUsers = repository.selectAllUsers();

        // Assert
        assertEquals(expectedUsers.size(), actualUsers.size());
        assertEquals(expectedUsers, actualUsers); // Ensure the user lists match
        verify(mockStatement).executeQuery();
    }

    @Test
    void testSelectAllUsersReturnsNoUsers() throws Exception {
        // Arrange
        DataSource mockDataSource = Mockito.mock(DataSource.class);
        Connection mockConnection = Mockito.mock(Connection.class);
        CallableStatement mockStatement = Mockito.mock(CallableStatement.class);
        ResultSet mockResultSet = Mockito.mock(ResultSet.class);

        when(mockDataSource.getConnection()).thenReturn(mockConnection);
        when(mockConnection.prepareCall("{ CALL GetAllUsers }")).thenReturn(mockStatement);
        when(mockStatement.executeQuery()).thenReturn(mockResultSet);

        // Simulate an empty ResultSet
        when(mockResultSet.next()).thenReturn(false); // No rows

        SqlRepository repository = new SqlRepository(mockDataSource);

        // Act
        List<User> actualUsers = repository.selectAllUsers();

        // Assert
        assertTrue(actualUsers.isEmpty()); // Ensure the result list is empty
        verify(mockStatement).executeQuery(); // Verify the query execution
    }

    @Test
    void testLoginUserWithValidCredentials() throws Exception {
        // Arrange
        String username = "validUser";
        String password = "validPassword";
        User expectedUser = new User(
                1,
                username,
                password,
                "FirstName",
                "LastName",
                "123 Street",
                "123456789",
                "valid@example.com",
                true // Admin user
        );

        // Mock dependencies
        DataSource mockDataSource = Mockito.mock(DataSource.class);
        Connection mockConnection = Mockito.mock(Connection.class);
        CallableStatement mockStatement = Mockito.mock(CallableStatement.class);
        ResultSet mockResultSet = Mockito.mock(ResultSet.class);

        // Mock the DataSource to return a mock Connection
        when(mockDataSource.getConnection()).thenReturn(mockConnection);

        // Mock the Connection to return a mock CallableStatement
        when(mockConnection.prepareCall("{ CALL LoginUser(?,?) }")).thenReturn(mockStatement);

        // Mock the CallableStatement to return a mock ResultSet
        when(mockStatement.executeQuery()).thenReturn(mockResultSet);

        // Simulate ResultSet for a valid user
        when(mockResultSet.next()).thenReturn(true); // Simulate user found
        when(mockResultSet.getInt("IDKorisnik")).thenReturn(1);
        when(mockResultSet.getString("KorisnickoIme")).thenReturn(username);
        when(mockResultSet.getString("Lozinka")).thenReturn(password);
        when(mockResultSet.getString("Ime")).thenReturn("FirstName");
        when(mockResultSet.getString("Prezime")).thenReturn("LastName");
        when(mockResultSet.getString("Adresa")).thenReturn("123 Street");
        when(mockResultSet.getString("Telefon")).thenReturn("123456789");
        when(mockResultSet.getString("Email")).thenReturn("valid@example.com");
        when(mockResultSet.getInt("IDRola")).thenReturn(1); // Admin role

        SqlRepository repository = new SqlRepository(mockDataSource);

        // Act
        Optional<User> actualUser = repository.loginUser(username, password);

        // Assert
        assertTrue(actualUser.isPresent());
        assertEquals(expectedUser, actualUser.get());
        verify(mockStatement).setString(1, username);
        verify(mockStatement).setString(2, password);
        verify(mockStatement).executeQuery();
    }

    @Test
    void testLoginUserWithInvalidCredentials() throws Exception {
        // Arrange
        String username = "invalidUser";
        String password = "wrongPassword";

        // Mock objects
        DataSource mockDataSource = Mockito.mock(DataSource.class);
        Connection mockConnection = Mockito.mock(Connection.class);
        CallableStatement mockStatement = Mockito.mock(CallableStatement.class);
        ResultSet mockResultSet = Mockito.mock(ResultSet.class);

        // Mock behaviors
        when(mockDataSource.getConnection()).thenReturn(mockConnection);
        when(mockConnection.prepareCall("{ CALL LoginUser(?,?) }")).thenReturn(mockStatement);
        when(mockStatement.executeQuery()).thenReturn(mockResultSet);

        // Simulate an empty ResultSet (no user found)
        when(mockResultSet.next()).thenReturn(false);

        SqlRepository repository = new SqlRepository(mockDataSource);

        // Act
        Optional<User> actualUser = repository.loginUser(username, password);

        // Assert
        assertFalse(actualUser.isPresent()); // Ensure no user is returned
        verify(mockStatement).setString(1, username); // Verify username is set correctly
        verify(mockStatement).setString(2, password); // Verify password is set correctly
        verify(mockStatement).executeQuery(); // Ensure the query was executed
    }

}
