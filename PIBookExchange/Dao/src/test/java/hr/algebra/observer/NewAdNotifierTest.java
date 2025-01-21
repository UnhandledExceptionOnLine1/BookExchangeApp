/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package hr.algebra.observer;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import org.mockito.MockitoAnnotations;

/**
 *
 * @author bruno
 */
public class NewAdNotifierTest {

    private NewAdNotifier notifier;

    @Mock
    private Subscriber sub1;
    @Mock
    private Subscriber sub2;
    @Mock
    private Subscriber sub3;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        notifier = new NewAdNotifier();
    }

    @Test
    void thisShouldAddSubscriber() {
        notifier.add(sub1);
        assertEquals(1, notifier.getSubscribers().size());
    }

    @Test
    void thisShouldRemoveSubscriber() {
        notifier.add(sub1);
        notifier.add(sub2);
        notifier.add(sub3);
        notifier.remove(sub1);
        assertEquals(2, notifier.getSubscribers().size());
        assertEquals(sub2, notifier.getSubscribers().get(0));
        assertEquals(sub3, notifier.getSubscribers().get(1));
    }

    @Test
    void thisShouldNotifySubscriber() {
        notifier.add(sub1);
        notifier.add(sub2);
        notifier.add(sub3);

        notifier.notify("New ad!");

        verify(sub1).alert("New ad!");
        verify(sub2).alert("New ad!");
        verify(sub3).alert("New ad!");
    }
}
