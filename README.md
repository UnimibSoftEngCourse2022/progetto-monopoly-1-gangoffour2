# Monopoly Game

![Version](https://img.shields.io/badge/version-1.0.0-success)
![License](https://img.shields.io/github/license/UnimibSoftEngCourse2022/progetto-monopoly-1-gangoffour2)
![Stars](https://img.shields.io/github/stars/UnimibSoftEngCourse2022/progetto-monopoly-1-gangoffour2)
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=UnimibSoftEngCourse2022_progetto-monopoly-1-gangoffour2&metric=bugs)](https://sonarcloud.io/dashboard?id=UnimibSoftEngCourse2022_progetto-monopoly-1-gangoffour2)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=UnimibSoftEngCourse2022_progetto-monopoly-1-gangoffour2&metric=code_smells)](https://sonarcloud.io/dashboard?id=UnimibSoftEngCourse2022_progetto-monopoly-1-gangoffour2)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=UnimibSoftEngCourse2022_progetto-monopoly-1-gangoffour2&metric=coverage)](https://sonarcloud.io/dashboard?id=UnimibSoftEngCourse2022_progetto-monopoly-1-gangoffour2)
[![Duplicated Lines (%)](https://sonarcloud.io/api/project_badges/measure?project=UnimibSoftEngCourse2022_progetto-monopoly-1-gangoffour2&metric=duplicated_lines_density)](https://sonarcloud.io/dashboard?id=UnimibSoftEngCourse2022_progetto-monopoly-1-gangoffour2)
[![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=UnimibSoftEngCourse2022_progetto-monopoly-1-gangoffour2&metric=ncloc)](https://sonarcloud.io/dashboard?id=UnimibSoftEngCourse2022_progetto-monopoly-1-gangoffour2)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=UnimibSoftEngCourse2022_progetto-monopoly-1-gangoffour2&metric=sqale_rating)](https://sonarcloud.io/dashboard?id=UnimibSoftEngCourse2022_progetto-monopoly-1-gangoffour2)
[![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=UnimibSoftEngCourse2022_progetto-monopoly-1-gangoffour2&metric=reliability_rating)](https://sonarcloud.io/dashboard?id=UnimibSoftEngCourse2022_progetto-monopoly-1-gangoffour2)
[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=UnimibSoftEngCourse2022_progetto-monopoly-1-gangoffour2&metric=security_rating)](https://sonarcloud.io/dashboard?id=UnimibSoftEngCourse2022_progetto-monopoly-1-gangoffour2)
[![Technical Debt](https://sonarcloud.io/api/project_badges/measure?project=UnimibSoftEngCourse2022_progetto-monopoly-1-gangoffour2&metric=sqale_index)](https://sonarcloud.io/dashboard?id=UnimibSoftEngCourse2022_progetto-monopoly-1-gangoffour2)
[![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=UnimibSoftEngCourse2022_progetto-monopoly-1-gangoffour2&metric=vulnerabilities)](https://sonarcloud.io/dashboard?id=UnimibSoftEngCourse2022_progetto-monopoly-1-gangoffour2)

![Monopoly.jpg](Monopoly.jpg?raw=true)

## Come eseguire il progetto

### Prerequisiti

- [Java 11](https://www.oracle.com/it/java/technologies/javase/jdk11-archive-downloads.html)
- [Maven](https://maven.apache.org/install.html) (3.1 or >)
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
