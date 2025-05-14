@echo off

:: # Temporär Maven verfügbar machen (ohne PATH dauerhaft zu ändern)
:: --------------------------------------------------
set "PATH=D:\Maven\apache-maven-3.9.9\bin;%PATH%"
:: --------------------------------------------------

:: # Maven Wrapper erzeugen, falls noch nicht vorhanden:
:: --------------------------------------------------
:: if not exist mvnw (
::    echo Maven Wrapper wird erstellt...
::    mvn -N io.takari:maven:wrapper
::    if %ERRORLEVEL% neq 0 exit /b %ERRORLEVEL%
:: )
:: Wrapper verwenden > mvnw anstatt mvn
:: --------------------------------------------------

:: # Sicherstellen, dass das Skript im Projekt-Wurzelverzeichnis ausgeführt wird:
:: --------------------------------------------------
:: Der Schalter /d erlaubt es, das Verzeichnis über Laufwerksgrenzen hinweg zu wechseln.
:: %0 ist ein Platzhalter für den vollständigen Pfad und Dateinamen des aktuellen Skripts.
:: Die Modifikatoren ~dp > d: Gibt das Laufwerk des Skripts zurück, p: Gibt den Pfad des Skripts zurück
cd /d %~dp0
:: --------------------------------------------------

:: # Bereinigen und Kompilieren:
:: --------------------------------------------------
:: Baut jeweils ein Modul, wobei -pl das Modul festlegt, und -am alle abhängigen Module mit baut.
:: Wenn bei einem der Schritte ein Fehler auftritt, wird der Build abgebrochen und der Fehlercode ausgegeben.
::
:: call: Sorgt dafür, dass nach dem Befehl die Batch-Datei weiterläuft, selbst wenn der Befehl eine eigene .bat oder .exe aufruft.
:: 		Ohne call endet das Skript nach dem ersten erfolgreichen Maven-Aufruf.
:: mvn clean install, zwei Maven-Ziele:
::		clean:
::			Löscht das target-Verzeichnis des Projekts, das alle kompilierten Dateien und Artefakte enthält.
::			Sorgt dafür, dass alle vorherigen Builds entfernt werden, sodass der Build-Prozess von vorne beginnen kann.
::		install:
::			Nachdem der Code kompiliert und getestet wurde, wird das Ziel install aufgerufen.
::			Stellt sicher, dass das Projekt gebaut wird und das resultierende Artefakt (z.B. eine .jar-Datei) in das 
::			lokale Maven-Repository (~/.m2/repository) installiert wird, sodass es von anderen Projekten verwendet werden kann.
:: -pl (Project List): Argument wird verwendet, um anzugeben, welche Module in einem Multi-Modul-Projekt gebaut werden sollen.
:: -am (--also-make): Bedeutet, dass alle abhängigen Module, die vom angegebenen Modul abhängen, ebenfalls gebaut werden sollen.
::		Z.B. wenn das view-Modul vom model-Modul abhängt, wird durch -am auch das view-Modul gebaut, auch wenn es nicht explizit im -pl-Argument angegeben ist.
::
:: neq steht für "not equal"
:: exit /b beendet das Skript mit einem angegebenen Fehlercode, aber schließt das CMD-Fenster nicht.
echo Building module... - model
call mvn clean install -pl model -am
if %ERRORLEVEL% neq 0 exit /b %ERRORLEVEL%

echo Building module... - controller
call mvn clean install -pl controller -am
if %ERRORLEVEL% neq 0 exit /b %ERRORLEVEL%

echo Building module... - view
call mvn clean install -pl view -am
if %ERRORLEVEL% neq 0 exit /b %ERRORLEVEL%

echo Building module... - launcher
call mvn clean install -pl launcher -am
if %ERRORLEVEL% neq 0 exit /b %ERRORLEVEL%
:: --------------------------------------------------

echo Build finished successfully!
pause

:: Obwohl -am sicherstellt, dass alle vom angegebenen Modul abhängigen Module mitgebaut werden, 
:: ist es in bestimmten Szenarien sinnvoll, explizit die Module selbst mit "mvn clean install -pl <modul> -am" zu bauen, um:
::		Eine sichere und vollständige Build-Reihenfolge zu gewährleisten.
::		Alle spezifischen Abhängigkeiten und Modulspezifischen Anforderungen zu berücksichtigen.
::		Den Build von alten Artefakten zu befreien und den Build-Prozess sauber zu starten.
:: In einem komplexeren Multi-Modul-Projekt ist dies oft notwendig, um sicherzustellen, dass alle Module richtig und in der richtigen Reihenfolge gebaut werden.

:: [Error]
:: call mvn clean install -pl SweetTooth -am
::
:: SweetTooth wird in diesem Fall nicht als Modulname erkannt.
:: In der pom.xml-Datei für das Root-Projekt ist SweetTooth zwar als Projekt definiert, aber in einem 
:: Multi-Modul-Projekt ist das Root-Projekt selbst kein echtes Modul, das gebaut werden muss. 
:: Stattdessen werden die darunterliegenden Module (model, controller, view) in der Build-Reihenfolge behandelt.

:: Da alle Module über die vorherigen mvn-Befehle gebaut werden, wird das Root-Projekt (mit packaging = pom) ohnehin nicht direkt kompiliert.
:: Das Root-Projekt (SweetTooth als pom-Packaging) muss nicht gebaut werden.