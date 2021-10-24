# Prekode for Oblig3 oppgave 1

Dette er et skall for oppgave 1 i Oblig3 i IN2010 høsten 2021. Det er _ikke_
påkrevet å benytte seg av denne koden.

## Prekode for Java

Den består av:
- `Oblig3.java` som inneholder `main` og kjører sorteringsalgoritmene dine på
  en gitt inputfil.
- `Sorter.java` som er en (abstrakt) klasse, som inneholder hjelpemetoder for å
  telle sammenligninger og bytter. I tillegg inneholder den hjelpemetoder for å
  kunne ta tiden på sorteringen, og returnere resultatene i form av strenger.
  Skal du bruke denne prekoden bør alle sorteringsalgoritmene dine
  implementeres som en egen klasse som arver fra `Sorter`.
- `Oblig3Runner.java` står for kjøringen av eksperimenter og skriving til
  fil. Merk at den inneholder disse feltene:
  ```java
  // Put the sorting algorithms under test for part 1 here
  static final Sorter[] ALGS1 = { new Insertion(), new Quick() };
  // Put the sorting algorithms under test for part 2 here
  static final Sorter[] ALGS2 = { new Insertion(), new Quick() };
  // Time limit for a single sorting in milliseconds
  static final long TIME_LIMIT_MS = 100;
  // How much n grows each iteration
  static final int INCREMENT = 1;
  ```
  som du kan justere for å få resultatene du ønsker.
- `Insertion.java` som inneholder et skall for insertion sort, og arver fra
  `Sorter`.
- `Quick.java` som inneholder et skall for quicksort, og arver fra `Sorter`.

## Prekode for Python

Den består av:
- `oblig3.py` som inneholder `main` og kjører sorteringsalgoritmene dine på en
  gitt inputfil.
- `countcompares.py` inneholder en klasse `CountCompares` som "wrapper"
  elementet som skal sorteres, og øker en teller hver gang elementet
  sammenlignes med et annet.
- `countswaps.py` inneholder en klasse `CountSwaps` som "wrapper" arrayet, og
  eksponerer en `swap`-metode som også teller byttet.
- `oblig3runner.py` står for kjøringen av eksperimenter og skriving til fil.
  Den sørger for at arrayet som sendes til sorteringsalgoritmen er wrappet med
  `CountSwaps` og at elementene er wrappet med `CountCompares`.
  Merk at den inneholder disse konstantene:
  ```python
  # Put the sorting algorithms under test for part 1 here
  ALGS1 = [insertion.sort, quick.sort]
  # Put the sorting algorithms under test for part 2 here
  ALGS2 = [insertion.sort, quick.sort]
  # Time limit for a single sorting in milliseconds
  TIME_LIMIT_MS = 100;
  # How much n grows each iteration for part 2
  INCREMENT = 1;
  ```
  som du kan justere for å få resultatene du ønsker.
- `insertion.py` som inneholder et skall for insertion sort.
- `quick.py` som inneholder et skall for quicksort.

Merk at løsningsforslaget i for Python kjører betydelig tregere enn
løsningsforslaget i Java. Dette kan være på grunn av måten sammenligninger og
bytter måles, i tillegg til at Python er sort sett mindre effektivt enn Java.
Det er fortsatt mulig å sammenligne de ulike sorteringsalgoritmene mot
hverandre, og har derfor lite å si for oppgaven.

## Kjøre koden

I tillegg finner du en rekke inputfiler i mappen `inputs`. Noen filer er store,
og vi forventer ikke at algoritmene dine er raske nok til å håndtere alle.

Ved å kjøre for eksempel:
```sh
$ javac *.java && java Oblig3 ../inputs/random_100
```


eller tilsvarende for Python:
```sh
$ python3 oblig3.py ../inputs/random_100
```

vil du se følgende utskrift:
```
  n, insertion_cmp, insertion_swaps, insertion_time, quick_cmp, quick_swaps, quick_time
```

og det vil ligge tre nye filer i `inputs`-mappen:
- `random_100_insertion.out`
- `random_100_quick.out`
- `random_100_results.csv`

Merk at resultatene ikke vil være rimelige, fordi sorteringen ikke er
implementert.

Grunnen til den ene finurlige linjen med utskrift er at programmet vil én gang
i sekundet skrive ut en linje som skrives til `csv`-filen. Dette er slik at du
kan se om programmet gjør noen fremgang når du utfører eksperimenter.

For å gjøre det enklere å utføre eksperimenter har vi lagt inn en
tidsbegrensning på hvor lenge en sorteringsalgoritme kan kjøre for
deloppgave 2. Den kan justeres ved å endre på `TIME_LIMIT_MS` i
`Oblig3Runner.java` eller `oblig3runner.py`. Den er satt til 100 millisekunder,
så om en algoritme overstiger dette, så vil den ikke kjøres for høyere `n` (som
vil si at du får tomme felter i den resulterende tabellen).
