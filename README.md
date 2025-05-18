# Aplicatie de criptare si testare

## Descriere generala

Acest proiect Java implementeaza un sistem simplu de criptare simetrica a mesajelor, impreuna cu o suita de teste unitare si de mutatie. Codul este organizat modular si include atat functii de criptare, cat si teste detaliate pentru validarea comportamentului corect.

## Detalii tehnice

- **Limbaj:** Java 19
- **Tool-uri utilizate:** IntelliJ IDEA, Maven, JUnit 5, PITest
- **Versiune Maven Plugin PITest:** `1.15.2`

## Clase Java in proiect

### 1. `Cript`

Aceasta clasa contine toata logica de criptare si decriptare a mesajelor text.  
- Metoda `codifica(String mesaj)` cripteaza mesajul, pe baza unei chei generate din suma caracterelor.
- Metoda `decodifica(String criptat)` decripteaza mesajul, extrage mesajul original.
- Metoda `genereazaCheie(String mesaj)` calculeaza o cheie numerica pe baza sumei codurilor caracterelor din mesaj, modulo lungimea alfabetului.
- Metoda `filtru(String mesaj)` elimina orice caracter care nu face parte din alfabetul romanesc.
<img width="397" alt="image" src="https://github.com/user-attachments/assets/1a8fe32d-3dde-4eb9-8b42-e193262d6c6e" />

---

### 2. `Functii`

O clasa de testare pentru operatii pe stringuri:
- `esteValid(String mesaj)` verifica daca un mesaj este valid.
- `invers(String mesaj)` intoarce mesajul scris invers.
- `lungime(String mesaj)` returneaza lungimea unui mesaj sau 0 daca este null.

---

### 3. `Main`

- Contine `main(String[] args)` unde este testata functionalitatea criptarii si decriptarii pe un mesaj.

---

### 4. `MesajTest`

Clasa de teste scrisa manual pentru testare functionala si structurala.
- Verifica simetria criptare-decriptare.
- Testeaza comportamentul metodelor pentru cazuri limita (null, siruri goale).
- Include teste de regresie si mutanti.

---

### 5. `MesajTestAI`

Clasa de teste generate de un AI (ChatGPT).
- Include teste de baza si acopera cazuri comune.
- Folosita pentru comparatie cu suita manuala de teste.

## Testare

Aplicatia a fost testata functional, structural si prin testare de mutanti.

- **Line Coverage:** 95%
-  **Mutation Coverage:** 93%
-  **Test Strength:** 100%

## 📂 Diagrame


## 🤖 AI in testare

Pentru evaluare comparativa:
- Am generat o suita de teste `MesajTestAI.java` cu ChatGPT.
- Le-am comparat cu testele scrise manual (`MesajTest.java`).
- Am inclus capturi si rezultate in ``.

## 📷 Capturi de ecran

Capturi relevante se gasesc in ``:
- Rezultate rulate in IntelliJ
- Rapoarte HTML PITest (`index.html`)
- Comparatii intre teste AI vs umane

## 📖 Bibliografie

- [JUnit 5 Documentation](https://junit.org/junit5/docs/current/user-guide/)
- [PITest Mutation Testing](https://pitest.org/)
- ChatGPT (pentru generarea unor teste automate)
