setlocal
:: === Einstellungen
:: Wenn der Maven Wrapper (mvnw) bereits im Verzeichnis existiert, ist keine lokale Installation von Maven erforderlich.
set "JAVA_HOME=D:\OracleJDK\jdk-21.0.3"
set "MAVEN_HOME=D:\Maven\apache-maven-3.9.9"
set "MVNW_VERSION=3.9.9"
set "SKRIPT_FOLDER=_SKRIPTS"

:: === Projekt-Wurzelverzeichnis setzen
:: ~: Entfernt umgebende Quotes, aber nur bei Parametern (Positionsparameter wie %0) oder for-Variablen
:: d: directory
:: p: path
set "CURRDIR=%~dp0"
:: Substring-Modifikation, abschließenden Backslash entfernen.
set "CURRDIR=%CURRDIR:~0,-1%"
:: n: Extrahiert den letzten Pfadbestandteil (Dateiname ohne Erweiterung)
for %%X in ("%CURRDIR%") do set "CURR_FOLDER=%%~nX"
:: /I: Case insensitive
if /I "%CURR_FOLDER%"=="%SKRIPT_FOLDER%" (
	:: f: Gibt den vollständig aufgelösten Pfad zurück (hier in das übergeordnete Verzeichnis \..)
    for %%Y in ("%~dp0..") do set "BASEDIR=%%~fY"
) else (
    set "BASEDIR=%CURRDIR%"
)
cd /d "%BASEDIR%"

:: === Logging Einstellungen
set "LOGFILE_DIR=%BASEDIR%\_LOGFILES"
set "LOGFILE=%LOGFILE_DIR%\maven_build_modular.log"

if not exist "%LOGFILE_DIR%" (
	mkdir "%LOGFILE_DIR%"
)
>> "%LOGFILE%" (
	echo === Maven build started =================
	echo Date: %DATE% Time: %TIME%
	echo =========================================
)

:: === Maven Wrapper erzeugen, falls noch nicht vorhanden
if not exist "%BASEDIR%\mvnw" (
	echo # Create Maven wrapper - version %MVNW_VERSION% >> "%LOGFILE%"
	if not exist "%MAVEN_HOME%\bin\mvn.cmd" if not exist "%MAVEN_HOME%\bin\mvn.bat" if not exist "%MAVEN_HOME%\bin\mvn.exe" (
		echo ERROR: Maven not found at %MAVEN_HOME% >> "%LOGFILE%"
		:: /b: Spezifiziert, dass nur das aktuelle Batch-Skript bzw. Unterroutine beendet wird
		exit /b 1
	)
	%MAVEN_HOME%\bin\mvn -N io.takari:maven:wrapper -Dmaven="%MVNW_VERSION%" >> "%LOGFILE%" 2>&1
	if errorlevel 1 (
		echo Error: Maven wrapper could not be created. (Maven installed and on PATH?) >> "%LOGFILE%"
		exit /b 1
	)
)

:: === Java Installation testen
echo # Test Java installation... >> "%LOGFILE%"
call "%JAVA_HOME%\bin\java" -version >> "%LOGFILE%" 2>&1

:: === Build
:: call: Sorgt dafür, dass nach dem Maven-Aufruf das Batch-Skript weiter ausgeführt wird.
:: Ein paar Maven-Ziele:
:: 		clean: Löscht das target-Verzeichnis des Projekts d.h. alle vorherigen Builds werden entfernt.
::		test-compile: Main- und Testcode kompilieren.
::		package: Kompilieren, Tests ausführen, packen (jar)
::		install: Kompilieren, Tests ausführen, packen und erzeugtes Artefakt in das lokale Maven-Repo installieren.
::				 Das Artefakt ist dann lokal verfügbar und kann von anderen Projekten als Abhängigkeit verwendet werden.
:: -pl (Project List): Welche(s) Modul(e) gebaut werden soll(en).
:: -am (--also-make): Alle Abhängigkeiten ebenfalls bauen.
echo # Run Maven goals... >> "%LOGFILE%"
call .\mvnw clean test-compile >> "%LOGFILE%" 2>&1
if errorlevel 1 exit /b 1

>> "%LOGFILE%" (
	echo === Maven build finished successfully ===
	echo Date: %DATE% Time: %TIME%
	echo =========================================
	echo.
)
endlocal & exit /b 0