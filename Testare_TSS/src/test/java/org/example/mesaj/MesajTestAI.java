package org.example.mesaj;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MesajTestAI {

    @Test
    void testEncodeDecodeSimple() {
        String input = "Hello world";
        String encrypted = Cript.codifica(input);
        String decrypted = Cript.decodifica(encrypted);
        assertEquals(Cript.filtru(input), decrypted);
    }

    @Test
    void testEncodeNotNull() {
        String input = "sample";
        String result = Cript.codifica(input);
        assertNotNull(result);
    }

    @Test
    void testDecodeNotNull() {
        String input = "test";
        String encrypted = Cript.codifica(input);
        assertNotNull(Cript.decodifica(encrypted));
    }

    @Test
    void testFiltruRemovesSymbols() {
        String input = "Hello@123!";
        String expected = "Hello123!";
        String result = Cript.filtru(input);
        assertEquals(expected, result);
    }

    @Test
    void testKeyConsistency() {
        String msg = "constant";
        int key1 = Cript.genereazaCheie(msg);
        int key2 = Cript.genereazaCheie(msg);
        assertEquals(key1, key2);
    }

    @Test
    void testFunctiiEsteValidTrue() {
        assertTrue(Functii.esteValid("Data"));
    }

    @Test
    void testFunctiiEsteValidFalse() {
        assertFalse(Functii.esteValid(""));
    }

    @Test
    void testFunctiiInvers() {
        String input = "abc";
        String expected = "cba";
        assertEquals(expected, Functii.invers(input));
    }

    @Test
    void testFunctiiLength() {
        assertEquals(4, Functii.lungime("Test"));
    }

    @Test
    void testCodificareConsistenta() {
        String input = "aaa";
        String encrypted1 = Cript.codifica(input);
        String encrypted2 = Cript.codifica(input);
        assertEquals(encrypted1, encrypted2);
    }
}