Ein Lern- und Experimentierprojekt rund um Java.

## Background

Das Projekt ist vom Spiel „Jawbreaker“ inspiriert, das als Übungsprojekt in *Java ist auch eine Insel* von Christian Ullenboom verwendet wird.

## Zweck

SweetTooth ist ein persönliches Lern- und Experimentierprojekt. Die Anwendung ist im Laufe der Zeit gewachsen und wurde genutzt, um verschiedene Aspekte der Java-Entwicklung praktisch auszuprobieren.

Im Mittelpunkt standen unter anderem:
* objektorientierte Programmierung mit Java
* Git und GitHub
* das Java-Modulsystem
* eine komponentenorientierte Strukturierung
* Maven und Eclipse
* unterschiedliche Ansätze zur Strukturierung einer Anwendung
* ein Versuch, eine MVC-orientierte Struktur umzusetzen

Das Projekt ist nicht als fertige oder produktionsreife Anwendung gedacht. Es dokumentiert vielmehr einen Teil meines praktischen Lernprozesses und die dabei gewonnenen Erfahrungen.

## Erkenntnisse

Eine besonders wertvolle Erfahrung war die nachträgliche Modularisierung der zunächst kleinen, nicht modularen Anwendung mit dem Java-Modulsystem.

Obwohl der Umfang der Anwendung überschaubar war, erwies sich die nachträgliche Modularisierung als ein aufwändiger und teilweise komplizierter Refactoring-Prozess. Dabei wurden die bestehenden Abhängigkeiten zwischen Klassen und Packages deutlich sichtbarer.

Gleichzeitig entstand ein besseres Verständnis dafür, welchen Wert klar definierte APIs und bewusst gestaltete Abhängigkeiten zwischen Komponenten haben. Das Java-Modulsystem unterstützt dabei, solche Abhängigkeiten explizit zu machen und dadurch zu einer klareren Struktur beizutragen.

Für mich war dies eine wichtige praktische Erfahrung: Eine modulare Struktur bereits bei der Entwicklung zu berücksichtigen ist wesentlich einfacher, als sie nachträglich durch Refactoring einzuführen.

## Repository-Struktur

Die Struktur dieses Remote-Repositories ist bewusst nicht vollständig standardisiert. Unter anderem enthält es Verzeichnisse wie `_POMS_forMavenNature`, die unterschiedliche lokale Projektkonfigurationen unterstützen.

Das Repository dient als Synchronisationspunkt für zwei lokale Varianten des Projekts:
* ein reines Java-Eclipse-Projekt
* ein Maven-basiertes Java-Projekt

Dadurch kann eine gemeinsame Codebasis in unterschiedlichen Entwicklungsumgebungen verwendet werden.

## Versionen

* **v2.1.0** – Verbesserungen an der Swing-Oberfläche und Einführung einer Persistenzschicht
* **v2.0.0** – erste Implementierung der Swing-Oberfläche
* **bis v1.0.3** – Implementierung mit einer Lanterna Text GUI
