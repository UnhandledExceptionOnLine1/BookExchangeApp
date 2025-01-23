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
}
