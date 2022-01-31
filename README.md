# Monopoly Game

![Version](https://img.shields.io/badge/version-1.0.0-success)
![License](https://img.shields.io/github/license/UnimibSoftEngCourse2022/progetto-monopoly-1-gangoffour2)
![Stars](https://img.shields.io/github/stars/UnimibSoftEngCourse2022/progetto-monopoly-1-gangoffour2)

![Monopoly.jpg](Monopoly.jpg?raw=true)

## Come eseguire il progetto

### Prerequisiti

- [Java 11](https://www.oracle.com/it/java/technologies/javase/jdk11-archive-downloads.html)
- [Maven](https://maven.apache.org/install.html)
- [Git](https://git-scm.com/downloads)

### Passi

- `git clone https://github.com/UnimibSoftEngCourse2022/progetto-monopoly-1-gangoffour2.git`: Esegue la clone del repository localmente.

*I seguenti comandi sono da eseguire all'interno della root del repository*

- `mvn package`: Esegue la build del server e i relativi test. 
Se hanno successo, esegue anche la build del client all'interno di `src/main/app` e copia i file generati
in `target/static`, dove `target/` è la cartella in cui risiedono i file compilati del server.
Infine, crea il pacchetto .jar completo.

- `java -jar target/monopoly-VERSION.jar`: Esegue il file .jar generato in precedenza.
`VERSION` è il numero di versione presente nel file `pom.xml` alla path `project.version`.
Attualmente è `1.0.0`.

- Il server è ora accessibile all'indirizzo `localhost:8080`.

Per avviare una partita:
- Dalla pagina principale, è possibile creare una partita selezionando le impostazioni preferite.
- Una volta creata, questa sarà visibile dalla scheda a destra. Cliccando sull'id, apparirà una schermata tramite la quale sarà possibile avviare la partita.
- Se vi sono più giocatori selezionati, la partita non inizierà fino a che non verrà raggiunto il numero prestabilito.

*made with ❤️ by Gang Of Four 2*
