
package org.example.mesaj;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
public class MesajTest {

    @Test
    void test1_CriptareDiacritice() {
        System.out.println("TEST 1 - Criptare simetrică cu diacritice");
        String original = "Bună ziua! Ne vedem mâine la ora 15:40";
        String filtrat = Cript.filtru(original);
        String criptat = Cript.codifica(original);
        String decriptat = Cript.decodifica(criptat);
        System.out.println("Original : " + original);
        System.out.println("Filtrat  : " + filtrat);
        System.out.println("Criptat  : " + criptat);
        System.out.println("Decriptat: " + decriptat);
        assertEquals(filtrat, decriptat);
    }

    @Test
    void test2_Filtru() {
        System.out.println("TEST 2 - Filtru pastreaza caracterele");
        String mesaj = "AaBbCc 123 .!?;";
        String filtrat = Cript.filtru(mesaj);
        System.out.println("Filtrat: " + filtrat);
        assertEquals(mesaj, filtrat);
    }

    @Test
    void test3_CodificaSimb() {
        System.out.println("TEST 3 - Codificare cu simboluri eliminate");
        String mesaj = "Test@#$%^";
        String rezultat = Cript.codifica(mesaj);
        System.out.println("Rezultat: " + rezultat);
        assertEquals(Cript.codifica("Test"), rezultat);
    }

    @Test
    void test4_CheieDiferite() {
        System.out.println("TEST 4 - Chei generate diferite pentru texte diferite");
        int c1 = Cript.genereazaCheie("abc");
        int c2 = Cript.genereazaCheie("abcd");
        System.out.println("Cheie 1: " + c1 + ", Cheie 2: " + c2);
        assertNotEquals(c1, c2);
    }

    @Test
    void test5_Mesaj0() {
        System.out.println("TEST 5 - Criptare si decriptare mesaj null");
        String mesaj = "";
        String criptat = Cript.codifica(mesaj);
        String decriptat = Cript.decodifica(criptat);
        System.out.println("Criptat: " + criptat + ", Decriptat: " + decriptat);
        assertEquals(mesaj, decriptat);
    }

    @Test
    void test6_NullInput() {
        System.out.println("TEST 6 - Codificare si decriptare NULL");
        assertNull(Cript.codifica(null));
        assertNull(Cript.decodifica(null));
    }

    @Test
    void test7_ValidareMesaje() {
        System.out.println("TEST 7 - Validare mesaje");
        assertTrue(Functii.esteValid("abc"));
        assertFalse(Functii.esteValid(""));
        assertFalse(Functii.esteValid("   "));
        assertFalse(Functii.esteValid(null));
    }

    @Test
    void test8_ModificareTextCriptat() {
        System.out.println("TEST 8 - Decriptare text corupt");
        String mesaj = "Mesaj test";
        String criptat = Cript.codifica(mesaj);
        String corupt = "Z" + criptat.substring(1);
        String decriptat = Cript.decodifica(corupt);
        System.out.println("Criptat: " + criptat + ", Corupt: " + corupt);
        assertNotEquals(Cript.filtru(mesaj), decriptat);
    }

    @Test
    void test9_FiltruSimboluriInvalide() {
        System.out.println("TEST 9 - Filtrul elimina simboluri neacceptate");
        String mesaj = "#$%^&*";
        String rezultat = Cript.filtru(mesaj);
        System.out.println("Filtrat: " + rezultat);
        assertEquals("", rezultat);
    }

    @Test
    void test10_GenereazaCheie() {
        System.out.println("TEST 10 - Verificare cheie generata manual");
        String mesaj = "abcdefgh";
        int cheie = Cript.genereazaCheie(mesaj);

        int suma = 0;
        for (char c : mesaj.toCharArray()) {
            suma += c;
        }

        int expected = suma % Cript.ALFABET.length();
        System.out.println("Cheie calculata: " + cheie + ", Asteptata: " + expected);
        assertEquals(expected, cheie);
    }
    @Test
    public void test11_MutantFiltru() {
        System.out.println("TEST 11 - Mutant pentru filtru");
        String textCuDubluri = "aabbcc";
        String filtrat = Cript.filtru(textCuDubluri);
        assertEquals(textCuDubluri, filtrat, "Filtrul nu ar trebui să elimine caractere acceptate duplicat");
    }
    @Test
    void test12_MutantCodificaSiDecriptare() {
        System.out.println("TEST 12 - Mutant codifica si verificare reversibilitate");
        String mesaj = "a";
        String codificat = Cript.codifica(mesaj);
        String decriptat = Cript.decodifica(codificat);
        System.out.println("Codificat: " + codificat + ", Decriptat: " + decriptat);
        assertEquals(mesaj, decriptat);

        String special = "a@b#c";
        String c = Cript.codifica(special);
        String d = Cript.decodifica(c);
        assertEquals("abc", d);
    }

    @Test
    void test13_MutantDecodificaIndexNegativ() {
        System.out.println("TEST 13 - Mutant decodifica - verificare cu index negativ");
        String mesaj = "a";
        String codificat = Cript.codifica(mesaj);
        String decodificat = Cript.decodifica(codificat);
        assertEquals(mesaj, decodificat);

        assertNull(Cript.decodifica(null));
        assertNull(Cript.decodifica(""));
    }
    @Test
    void testMainExecutaFaraExceptii() {
        String[] args = {};  // sau orice parametru dorești
        Main.main(args);
        // Nu testăm output, doar că nu aruncă excepții
    }
    @Test
    void testMainOutput() {
        // Capturează System.out
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        // Rulează main()
        Main.main(new String[]{});

        // Restaurare System.out
        System.setOut(originalOut);

        String output = outContent.toString().trim();

        // Verifică dacă conține toate liniile importante
        assertTrue(output.contains("Mesaj original"), "Nu s-a afișat mesajul original");
        assertTrue(output.contains("Mesaj criptat"), "Nu s-a afișat mesajul criptat");
        assertTrue(output.contains("Mesaj decriptat"), "Nu s-a afișat mesajul decriptat");
    }
    @Test
    void testInversNull() {
        System.out.println("TEST 14 - Verificare Functia invers");
        String rezultat = Functii.invers(null);
        assertNull(rezultat);
    }
}
