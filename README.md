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

## 1. Testare Structurala (verifica logica interna a codului)
### 1. Transformare in graf orientat (CFG)
<img width="404" alt="image" src="https://github.com/user-attachments/assets/92795aac-e9c2-4f92-a491-c5586616764a" />

### 2. Statement Coverage

Se verifica daca fiecare instructiune a fost executata cel putin o data.

### Tabel Statement Coverage

| Test                | Input                                   | Rezultat             | Instructiuni acoperite          |
|---------------------|-----------------------------------------|----------------------|----------------------------------|
| test1               | "Buna ziua! ..."                        | mesaj decriptat      | toate din codifica + filtru     |
| test6               | null                                    | null                 | return imediat                  |
| test9               | "#$%^&*"                                | ""                   | doar bucla filtru               |

**Acoperire:** 100% (toate ramurile executate)

---

### 3. Branch / Decision Coverage

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

### 4. Condition Coverage

Se testeaza fiecare sub-conditie dintr-o expresie compusa, sa fie atat true cat si false.

### Tabel Condition Coverage

| Test       | Conditie                       | Valori testate (true/false) |
|------------|--------------------------------|------------------------------|
| test6      | mesaj == null                  | true                         |
| test1      | mesaj == null                  | false                        |
| test2/9    | index != -1                    | true / false                 |

**Acoperire:** 100% (fiecare sub-conditie a fost testata)

---

### 5. Condition/Decision Coverage

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

### 6. Multiple Condition Coverage

Toate combinatiile posibile ale conditiilor individuale.

### Tabel Multiple Conditions

| Test       | C1: criptat == null | C2: criptat.length() == 0 | C1 || C2 | Comentariu                      |
|------------|----------------------|----------------------------|----------|---------------------------------|
| test13     | true                 | -                          | true     | return null                     |
| test5      | false                | true                       | true     | return null                     |
| test1      | false                | false                      | false    | se executa codificarea completa |

**Acoperire:** 100% (toate combinatiile de valori acoperite)

---

### 7. MC/DC (Modified Condition/Decision Coverage)

Verifica daca fiecare conditie influenteaza rezultatul deciziei in mod individual.

### Tabel MC/DC

| Test       | C1: criptat == null | C2: criptat.length()==0 | Rezultat | Demonstratie Influenta          |
|------------|----------------------|--------------------------|----------|----------------------------------|
| test13     | true                 | x                        | true     | C1 influenteaza decizia         |
| test5      | false                | true                     | true     | C2 influenteaza decizia         |
| test1      | false                | false                    | false    | Ambele false => decizie false   |

**Acoperire:** 100%

---

### 8. Testarea circuitelor independente

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

### 9. Path Coverage

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

### 2. Testare Functionala --continuare

## AI in testare

Pentru evaluare comparativa:
- Am generat o suita de teste `MesajTestAI.java` cu ChatGPT.
- Le-am comparat cu testele scrise manual (`MesajTest.java`).

Se observa ca testele pe care le-am rulat eu au o acoperire mult mai buna, asadar cu rezultate mult mai bune. Am incercat sa acopar fiecare mutant si se observa ca AI-ul are mai multe probleme.

Rezultate Teste in PIT

<img width="936" alt="image" src="https://github.com/user-attachments/assets/aa408241-cf87-444c-a3b8-cfe9b3a247d5" />

AI PIT

<img width="943" alt="image" src="https://github.com/user-attachments/assets/3db98584-5751-4e7b-981b-dd0053e3ed16" />

## 📖 Bibliografie

- [JUnit 5 Documentation](https://junit.org/junit5/docs/current/user-guide/)
- [PITest Mutation Testing](https://pitest.org/)
- ChatGPT (pentru generarea unor teste automate)
