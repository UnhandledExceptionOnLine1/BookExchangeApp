/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package hr.algebra.dal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertNotNull;


/**
 *
 * @author Tin
 */
public class RepositoryFactoryTest {

    @Mock
    private UserRepositoryInterface mockRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getRepository_ShouldReturnNonNullRepository() {
        // Act
        Repository repository = RepositoryFactory.getRepository();

        // Assert
        assertNotNull(repository, "Repository should not be null");
    }

    @Test
    void setMockRepository_ShouldSetRepository() {
        // Arrange
        assertNotNull(mockRepository, "Mock repository should not be null before setting");

        // Act
        RepositoryFactory.setMockRepository(mockRepository);

        // Assert
        assertEquals(mockRepository, RepositoryFactory.getRepository(), "getRepository should return the mock repository after it's set");
    }

}
