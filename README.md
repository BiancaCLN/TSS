# Aplicatie de criptare si testare

## Descriere generala

Acest proiect Java implementeaza un sistem simplu de criptare simetrica a mesajelor, impreuna cu o suita de teste unitare si de mutatie. Codul este organizat modular si include atat functii de criptare, cat si teste detaliate pentru validarea comportamentului corect.

## Detalii tehnice

- **Limbaj:** Java 19
- **Tool-uri utilizate:** IntelliJ IDEA, Maven, JUnit 5, PITest
- **Versiune Maven Plugin PITest:** `1.15.2`

## Configuratia sistemului de testare

- **Sistem de operare:** Windows 11 / macOS Ventura / Ubuntu 22.04
- **RAM:** 16 GB
- **Procesor:** Intel i5 / M1 / Ryzen 5
- **JVM:** OpenJDK 19
- **Build tool:** Maven 3.9.x
- **IDE:** IntelliJ IDEA 2023.x

---

## Clase Java in proiect

### 1. `Cript`

Aceasta clasa contine toata logica de criptare si decriptare a mesajelor text.  
- Metoda `codifica(String mesaj)` cripteaza mesajul, pe baza unei chei generate din suma caracterelor.
- Metoda `decodifica(String criptat)` decripteaza mesajul, extrage mesajul original.
- Metoda `genereazaCheie(String mesaj)` calculeaza o cheie numerica pe baza sumei codurilor caracterelor din mesaj, modulo lungimea alfabetului.
- Metoda `filtru(String mesaj)` elimina orice caracter care nu face parte din alfabetul romanesc.
<img width="397" alt="image" src="https://github.com/user-attachments/assets/1a8fe32d-3dde-4eb9-8b42-e193262d6c6e" />

####Diagrama a fost realizata cu [app.diagrams.net](https://app.diagrams.net), conform cerintelor.
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

---

## Testare

Aplicatia a fost testata functional, structural si prin testare de mutanti.

- **Line Coverage:** 95%
-  **Mutation Coverage:** 93%
-  **Test Strength:** 100%

## Strategii de testare

Am aplicat o combinatie de strategii de testare:
- Testare functionala: validam comportamentul in functie de cerinte.
- Testare structurala: acoperim ramuri, decizii si cai prin analiza codului.
- Testare de mutanti: folosim PITest pentru a verifica robustetea testelor scrise.
- Testare negativa: introducem inputuri invalide pentru a observa comportamente defensive.

---

## 1. Testare Structurala (verifica logica interna a codului)

---

### 1.1 Transformare in graf orientat
<img width="404" alt="image" src="https://github.com/user-attachments/assets/92795aac-e9c2-4f92-a491-c5586616764a" />

### 1.2 Statement Coverage

Se verifica daca fiecare instructiune a fost executata cel putin o data.

### Tabel Statement Coverage

| Test                | Input                                   | Rezultat             | Instructiuni acoperite          |
|---------------------|-----------------------------------------|----------------------|----------------------------------|
| test1               | "Buna ziua! ..."                        | mesaj decriptat      | toate din codifica + filtru     |
| test6               | null                                    | null                 | return imediat                  |
| test9               | "#$%^&*"                                | ""                   | doar bucla filtru               |

**Acoperire:** 100% (toate ramurile executate)

---

### 1.3 Branch / Decision Coverage

Se verifica daca fiecare decizie (if/for) ia ambele valori: true si false.

### Tabel Branch Coverage

| Test       | Decizie                          | Valoare evaluata | Ramura executata                  |
|------------|----------------------------------|------------------|------------------------------------|
| test6      | mesaj == null                    | true             | return null                        |
| test1      | mesaj == null                    | false            | se continua criptarea              |
| test9      | index != -1                      | false            | caracter ignorat                   |
| test2      | index != -1                      | true             | caracter adaugat in rezultat       |

**Acoperire:** 100% (toate ramurile posibile acoperite)

---

### 1.4 Condition Coverage

Se testeaza fiecare sub-conditie dintr-o expresie compusa, sa fie atat true cat si false.

### Tabel Condition Coverage

| Test       | Conditie                       | Valori testate (true/false) |
|------------|--------------------------------|------------------------------|
| test6      | mesaj == null                  | true                         |
| test1      | mesaj == null                  | false                        |
| test2/9    | index != -1                    | true / false                 |

**Acoperire:** 100% (fiecare sub-conditie a fost testata)

---

### 1.5 Condition/Decision Coverage

Se verifica atat conditiile individuale cat si rezultatul deciziei in ansamblu.

### Tabel Condition/Decision Coverage

| Test       | Decizie                              | Conditii                  | Decizie evaluata |
|------------|---------------------------------------|---------------------------|------------------|
| test6      | mesaj == null                         | true                      | true             |
| test1      | mesaj == null                         | false                     | false            |
| test13     | criptat == null || criptat.length()==0 | true || false            | true             |
| test5      | criptat == null || criptat.length()==0 | false || true            | true             |

**Acoperire:** 100%

---

### 1.6 Multiple Condition Coverage

Toate combinatiile posibile ale conditiilor individuale.

### Tabel Multiple Conditions

| Test       | C1: criptat == null | C2: criptat.length() == 0 | C1 || C2 | Comentariu                      |
|------------|----------------------|----------------------------|----------|---------------------------------|
| test13     | true                 | -                          | true     | return null                     |
| test5      | false                | true                       | true     | return null                     |
| test1      | false                | false                      | false    | se executa codificarea completa |

**Acoperire:** 100% (toate combinatiile de valori acoperite)

---

### 1.7 MC/DC (Modified Condition/Decision Coverage)

Verifica daca fiecare conditie influenteaza rezultatul deciziei in mod individual.

### Tabel MC/DC

| Test       | C1: criptat == null | C2: criptat.length()==0 | Rezultat | Demonstratie Influenta          |
|------------|----------------------|--------------------------|----------|----------------------------------|
| test13     | true                 | x                        | true     | C1 influenteaza decizia         |
| test5      | false                | true                     | true     | C2 influenteaza decizia         |
| test1      | false                | false                    | false    | Ambele false => decizie false   |

**Acoperire:** 100%

---

### 1.8 Testarea circuitelor independente

Baza de circuite acoperita cu:

- `test5`: mesaj gol → bucla nu se executa
- `test12`: un caracter → o singura iteratie
- `test1`: mesaj lung → bucla se repeta

### Tabel Circuite

| Test       | Caz circuit                      | Comportament bucla     |
|------------|----------------------------------|-------------------------|
| test5      | mesaj gol                        | 0 iteratii              |
| test12     | un caracter                      | 1 iteratie              |
| test1      | mesaj lung                       | n iteratii              |

**Acoperire:** 100%

---

### 1.9 Path Coverage

Am acoperit toate caile logice importante:

### Tabel Cale

| Test       | Cale parcursa                              | Comentariu                        |
|------------|---------------------------------------------|-----------------------------------|
| test6      | return imediat                              | path scurt                        |
| test1      | codificare completa + return                | path lung                         |
| test8      | codificare + modificare + return diferit    | path alternativ                   |

**Acoperire:** 100%

---

Exemplu functionare test 1:

<img width="433" alt="image" src="https://github.com/user-attachments/assets/8b6759fd-21a1-4c9b-90b1-c46f9962f0b8" />

| Nr. Test | Descriere                           | Input                               | Asteptat                               | Rezultat          |
|----------|-------------------------------------|-------------------------------------|----------------------------------------|-------------------|
| 1        | Codificare + decodificare simetrică | "Salut, Ana! Ne vedem la 18:30."    | Mesajul inițial filtrat                | Bine decodificat  |
| 2        | Validare mesaj gol                  | ""                                  | Invalid                                | Invalidat corect  | 
| 3        | Codificare NULL                     | null                                | null                                   | null              |
| 4        | Codificare mesaj cu simboluri       | "Test@#$%"                          | Doar "Test" codificat                  | Doar "Test"       | 
| 5        | Decriptare text corupt              | Cod criptat modificat               | Decriptare greșită                     | Decriptare eșuată |    
<img width="839" alt="image" src="https://github.com/user-attachments/assets/0baa7151-e8d4-44ae-90cd-7928d2dde439" />

### 2. Testare Functionala 

---

### 2.1 Partitionare de echivalenta (Equivalence Partitioning)

### Clase de echivalenta (dupa specificatie)

- E1: mesaj valid, cu caractere acceptate
- E2: mesaj valid, contine caractere invalide
- E3: mesaj gol
- E4: mesaj null
- E5: criptare valida, decriptare modificata
- E6: mesaj valid, rezultat cheie controlat

### Date de test

| Test    | Intrare                    | Rezultat asteptat                      | Clasa echivalenta |
|---------|-----------------------------|----------------------------------------|-------------------|
| test1   | "Buna ziua! ..."            | mesajul decriptat este identic         | E1                |
| test2   | "AaBbCc 123 .!?;"           | mesaj pastrat                          | E1                |
| test3   | "Test@#$%"                  | caractere eliminate                    | E2                |
| test5   | ""                          | criptare si decriptare pe mesaj gol    | E3                |
| test6   | null                        | return null                            | E4                |
| test8   | mesaj criptat corupt        | decriptare diferita                    | E5                |
| test10  | mesaj = "abcdefgh"          | verificare cheie generata              | E6                |

---

### 2.2 Analiza valorilor de frontiera (Boundary Value Analysis)

### Valori de frontiera:

- mesaj: null, "", 1 caracter, lungime lunga
- cheie: 0, max ALFABET
- pozitie: caracter la inceput/sfarsit

| Test    | Conditie testata                 | Valoare frontiera | Rezultat asteptat              |
|---------|----------------------------------|-------------------|-------------------------------|
| test6   | mesaj null                       | null              | return null                   |
| test5   | mesaj gol                        | ""                | ""                            |
| test12  | un singur caracter               | "a"               | a                            |
| test13  | codificare + decriptare          | "" / null         | null                          |
| test10  | cheie = suma % ALFABET           | calcul explicit   | cheie corecta                 |

---

### 2.3 Partitionare in categorii (Category Partitioning)

### Categorii si alternative

| Parametru | Categorie           | Alternative                          |
|-----------|---------------------|---------------------------------------|
| mesaj     | lungime             | null, "", 1, mediu, lung              |
| caractere | validitate          | toate valide, toate invalide, mix    |
| criptat   | integritate         | valid, corupt                        |
| output    | corectitudine       | egal cu input, diferit, null         |

### Combinatii acoperite prin teste

| Test    | Combinatie descrisa                               |
|---------|----------------------------------------------------|
| test1   | mesaj valid, caractere valide, output corect       |
| test3   | caractere mixte, output filtrat                    |
| test6   | mesaj null, output null                            |
| test8   | mesaj valid, criptat modificat, output incorect    |
| test12  | mesaj scurt, valid, output corect                  |

---


## AI in testare

Pentru evaluare comparativa:
- Am generat o suita de teste `MesajTestAI.java` cu ChatGPT.
- Le-am comparat cu testele scrise manual (`MesajTest.java`).

Se observa ca testele pe care le-am rulat eu au o acoperire mult mai buna, asadar cu rezultate mult mai bune. Am incercat sa acopar fiecare mutant si se observa ca AI-ul are mai multe probleme.

Rezultate Teste in PIT

Acoperirea de mutatie a fost analizata cu ajutorul PITest [2], iar testele au fost structurate conform principiilor JUnit [1].

<img width="936" alt="image" src="https://github.com/user-attachments/assets/aa408241-cf87-444c-a3b8-cfe9b3a247d5" />

AI PIT

<img width="943" alt="image" src="https://github.com/user-attachments/assets/3db98584-5751-4e7b-981b-dd0053e3ed16" />

## 📖 Bibliografie

- [1] [JUnit 5 Documentation](https://junit.org/junit5/docs/current/user-guide/)
- [2] [PITest Mutation Testing](https://pitest.org/)
- ChatGPT (pentru generarea unor teste automate)
