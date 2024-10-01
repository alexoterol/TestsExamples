package main;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CalculationTest {

    @Test
    void testFindMax() {
        // Caso con números positivos
        assertEquals(4, Calculation.findMax(new int[]{1, 2, 3, 4}));
        
        // Caso con números negativos y un número positivo
        assertEquals(50, Calculation.findMax(new int[]{-12, -1, -3, -4, -2, 50}));
        
        // Caso con solo números negativos
        assertEquals(-1, Calculation.findMax(new int[]{-12, -1, -3, -4, -2}));
    }
    
    @Test
    void testFindMaxWithSingleElement() {
        // Caso con un solo elemento
        assertEquals(7, Calculation.findMax(new int[]{7}));
    }

    @Test
    void testFindMaxWithAllSameElements() {
        // Caso con todos los elementos iguales
        assertEquals(3, Calculation.findMax(new int[]{3, 3, 3, 3}));
    }
    
    @Test
    void testFindMaxWithLargeNumbers() {
        // Caso con números grandes
        assertEquals(999999, Calculation.findMax(new int[]{999999, -1000, 345, 0}));
    }

    @Test
    void testFindMaxWithEmptyArray() {
        // Prueba que espera una excepción cuando el array está vacío
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Calculation.findMax(new int[]{});
        });
        
        assertEquals("Array must not be empty", exception.getMessage());
    }
}

