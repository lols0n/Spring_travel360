package pl.sda.spring2_travel360;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CalculatorTest {

    @Mock
    private Display display;

    @InjectMocks
    private Calculator calculator;

    @Test
    void shouldAddTwoValues() {
        //given - NIE POTRZEBA BO JEST MOCKOWANIE
        //Display display = Mockito.mock(Display.class);
        //Calculator calculator = new Calculator();
        //when
        var result = calculator.add(5, 4);
        //then
        assertEquals(9, result);
        verify(display, times(1))
                .show(5, 4, "+", 9);
    }

    @Test
    void shouldSubTwoValues() {
        //given

        //when
        var result = calculator.sub(5, 4);
        //then
        assertEquals(1, result);
        verify(display, times(1))
                .show(anyInt(), anyInt(), any(), anyInt());
    }

    @Test
    void shouldDivTwoValues() {
        //given

        //when
        var result = calculator.div(10, 5);
        //then
        assertEquals(2, result);
        verify(display, times(1))
                .show(10, 5, "/", 2);
    }

    @Test
    void shouldMullTwoValues() {
        //given

        //when
        var result = calculator.mull(2, 5);
        //then
        assertEquals(10, result);
        verify(display, times(1))
                .show(2, 5, "*", 10);
    }

    @Test
    void shouldThrowExceptionWhenDividerIsZero() {
        //given

        //when & then
        assertThrows(IllegalArgumentException.class, () -> {
            calculator.div(10, 0);
        });
        verifyNoInteractions(display);
    }
}