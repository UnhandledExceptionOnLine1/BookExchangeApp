/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package hr.algebra.mediator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import javax.swing.*;
import java.awt.*;
import static org.mockito.Mockito.*;

/**
 *
 * @author Tin
 */
public class AdFormMediatorTest {

    @Mock
    private JComboBox<String> cbCategory;
    @Mock
    private JTextField tfPrice;
    @Mock
    private JLabel lblMaxPriceValue;
    @Mock
    private JButton btnSubmit;

    private AdFormMediator mediator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mediator = new AdFormMediator(cbCategory, tfPrice, lblMaxPriceValue, btnSubmit);
    }

    @Test
    void shouldDisplayCorrectMaxPriceForBooks() {
        when(cbCategory.getSelectedItem()).thenReturn("Knjiga");
        mediator.notify(cbCategory, "selectionChanged");
        verify(lblMaxPriceValue).setText("500 €");
    }

    @Test
    void shouldDisplayCorrectMaxPriceForOtherMaterials() {
        when(cbCategory.getSelectedItem()).thenReturn("Ostali materijali");
        mediator.notify(cbCategory, "selectionChanged");
        verify(lblMaxPriceValue).setText("200 €");
    }

    @Test
    void shouldEnableSubmitButtonWhenPriceIsValidforBooks() {
        when(cbCategory.getSelectedItem()).thenReturn("Knjiga");
        when(tfPrice.getText()).thenReturn("100");
        when(tfPrice.getBackground()).thenReturn(Color.WHITE);
        mediator.notify(tfPrice, "textChanged");
        verify(btnSubmit).setEnabled(true);
    }

    @Test
    void shouldEnableSubmitButtonWhenPriceIsValidforOtherMaterials() {
        when(cbCategory.getSelectedItem()).thenReturn("Ostali materijali");
        when(tfPrice.getText()).thenReturn("100");
        when(tfPrice.getBackground()).thenReturn(Color.WHITE);
        mediator.notify(tfPrice, "textChanged");
        verify(btnSubmit).setEnabled(true);
    }

    @Test
    void shouldDisableSubmitButtonWhenPriceIsInvalidForBooks() {
        when(cbCategory.getSelectedItem()).thenReturn("Knjiga");
        when(tfPrice.getText()).thenReturn("600");
        mediator.notify(tfPrice, "textChanged");
        verify(btnSubmit).setEnabled(false);
        verify(tfPrice).setBackground(Color.PINK);
    }

    @Test
    void shouldDisableSubmitButtonWhenPriceIsInvalidForOtherMaterials() {
        when(cbCategory.getSelectedItem()).thenReturn("Ostali materijali");
        when(tfPrice.getText()).thenReturn("250");
        mediator.notify(tfPrice, "textChanged");
        verify(btnSubmit).setEnabled(false);
        verify(tfPrice).setBackground(Color.PINK);
    }

    @Test
    void shouldEnableSubmitButtonWhenInputIsNotValidforBooks() {
        when(cbCategory.getSelectedItem()).thenReturn("Knjiga");
        when(tfPrice.getText()).thenReturn("asd");
        when(tfPrice.getBackground()).thenReturn(Color.PINK);
        mediator.notify(tfPrice, "textChanged");
        verify(btnSubmit).setEnabled(false);
    }

    @Test
    void shouldEnableSubmitButtonWhenInputIsNotValidforOtherMaterials() {
        when(cbCategory.getSelectedItem()).thenReturn("Ostali materijali");
        when(tfPrice.getText()).thenReturn("asd");
        when(tfPrice.getBackground()).thenReturn(Color.PINK);
        mediator.notify(tfPrice, "textChanged");
        verify(btnSubmit).setEnabled(false);
    }

    @Test
    void shouldHandleExactBoundaryPriceForBooks() {
        when(cbCategory.getSelectedItem()).thenReturn("Knjiga");
        when(tfPrice.getText()).thenReturn("500");
        mediator.notify(tfPrice, "textChanged");
        verify(btnSubmit).setEnabled(true);
        verify(tfPrice).setBackground(Color.WHITE);
    }

    @Test
    void shouldHandleExactBoundaryPriceForOtherMaterials() {
        when(cbCategory.getSelectedItem()).thenReturn("Ostali materijali");
        when(tfPrice.getText()).thenReturn("200");
        mediator.notify(tfPrice, "textChanged");
        verify(btnSubmit).setEnabled(true);
        verify(tfPrice).setBackground(Color.WHITE);
    }

}
