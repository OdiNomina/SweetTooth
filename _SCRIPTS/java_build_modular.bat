:: Anführungszeichen stellen sicher, dass
:: - keine unbeabsichtigten Leerzeichen am Ende oder Anfang in die Variable gelangen,
:: - keine Probleme mit Sonderzeichen oder Umgebungsvariablen auftreten,
:: - die Zuweisung klar abgeschlossen ist.

:: === Projekt-Wurzelverzeichnis setzen (Verzeichnis des Skripts)
:: d: directory
:: p: path
set "BASEDIR=%~dp0"

:: === Globale Einstellungen
set "LOGFILE_DIR=%BASEDIR%_LOGFILES"
if not exist "%LOGFILE_DIR%" (
	mkdir "%LOGFILE_DIR%"
)
set "LOGFILE=%LOGFILE_DIR%\java_build.log"
set "ENCODING=UTF-8"
set "JAVA_VERSION=21"
:: "Liste" der Module (Abhängigkeitsreihenfolge beachten)
set "MODULES=model controller view launcher"
set "SRC_DIR=src"
set "BIN_DIR=bin\main\java"
set "LIB=%BASEDIR%lib"
set "TEMP_DIR=%BASEDIR%_TEMP"

:: Lokaler Scope der Variablen und verzögerte Auswertung aktivieren (Mit !Variable! erfolgt die Auswertung zur Laufzeit, nicht beim Parsen)
setlocal EnableDelayedExpansion
	>> "%LOGFILE%" (
		echo === Build started =================
		echo Date: %DATE% Time: %TIME%
		echo ===================================
	)
	
	if not exist "%TEMP_DIR%" (
		mkdir "%TEMP_DIR%"
		if errorlevel 1 (
			echo Error: Could not create TEMP\" >> "%LOGFILE%"
			exit /b 1
		)
	)
	:: /b: Spezifiziert, dass nur das aktuelle Batch-Skript bzw. Unterroutine beendet wird
	if not exist "%LIB%" (
		>> "%LOGFILE%" (
			echo Error: "%LIB%" does not exist.
			echo You may need to create "%LIB% and download the Lanterna.jar library.
		)
		exit /b 1
	)

	:: === Clean - Output-Ordner vorbereiten
	for %%B in (%MODULES%) do (
		set "MOD_DIR=%BASEDIR%%%B"
		if not exist "!MOD_DIR!" (
			echo Error: Module directory "!MOD_DIR!" does not exist. >> "%LOGFILE%"
			exit /b 1
		)
		
		set "MOD_BIN_PATH=!MOD_DIR!\%BIN_DIR%"
		if exist "!MOD_BIN_PATH!" (
			echo Cleaning old build of %%B >> "%LOGFILE%"
			:: /s: Rekursives Löschen
			:: /q: Quiet mode, kein Nachfragen, keine Ausgaben.
			rmdir /s /q "!MOD_BIN_PATH!"
		)
		mkdir "!MOD_BIN_PATH!"
		if errorlevel 1 (
			echo Error: Could not create %BIN_DIR% for "%%B" >> "%LOGFILE%"
			exit /b 1
		)
	)
	
	:: === Module-path aufbauen
	:: In Batch-Skripten (.bat) muss die Schleifenvariable mit %% beginnen
	:: %MODULES%: Elemente, über die iteriert wird (getrennt durch Leerzeichen)
	set "MODULE_PATH=#"
	for %%A in (%MODULES%) do (
		set "MOD_DIR=%BASEDIR%%%A"
		set "MOD_BIN_PATH=!MOD_DIR!\%BIN_DIR%"
		set "MODULE_PATH=!MODULE_PATH!;!MOD_BIN_PATH!"
	)
	set "MODULE_PATH=!MODULE_PATH:#;=!;%LIB%"
	
	:: === Module kompilieren
	for %%C in (%MODULES%) do (
		set "MOD_DIR=%BASEDIR%%%C"
		set "MOD_BIN_PATH=!MOD_DIR!\%BIN_DIR%"
		set "MOD_SRC_PATH=!MOD_DIR!\%SRC_DIR%"
		if not exist "!MOD_SRC_PATH!" (
			echo Error: ...%SRC_DIR% of Module "%%C" does not exist. >> "%LOGFILE%"
			exit /b 1
		)
		call :compileModule "%%C" "!MOD_SRC_PATH!" "!MOD_BIN_PATH!" "%MODULE_PATH%"
		if errorlevel 1 exit /b 1
	)
	
	>> "%LOGFILE%" (
		echo === Build finished successfully ===
		echo Date: %DATE% Time: %TIME%
		echo ===================================
		echo.
	)
endlocal
exit /b 0


:: ====== Funktion: Modul kompilieren
:: :label
:compileModule
setlocal
	:: %1, %2: Platzhalter für Argumente der Funktion
	:: ~: Entfernt umgebende Quotes, aber nur bei Parametern (Positionsparameter wie %0) oder for-Variablen
	set "MOD_NAME=%~1"
	set "MOD_SRC=%~2"
	set "MOD_BIN=%~3"
	set "MODULE_PATH=%~4"

	:: Entfernt ländertypische Sonderzeichen:
	set "CLEAN_DATE=%DATE:.=%"
	set "CLEAN_DATE=%CLEAN_DATE:/=%"
	set "CLEAN_DATE=%CLEAN_DATE:-=%"
	set "CLEAN_DATE=%CLEAN_DATE: =%"
	set "CLEAN_TIME=%TIME::=%"
	set "CLEAN_TIME=%CLEAN_TIME: =%"
	set "CLEAN_TIME=%CLEAN_TIME:,=-%"

	echo Compiling module: "%MOD_NAME%" >> "%LOGFILE%"

	:: === Argumentdatei (@argfile) erstellen, Source-Dateien sammeln
	set "SRC_LIST=%TEMP_DIR%\%MOD_NAME%_SrcList_%CLEAN_DATE%-%CLEAN_TIME%.txt"
	:: KEIN rekursives Finden von Unterordner-Dateien bei javac mit %MOD_SRC%\*.java
	:: /s: Durchsucht das angegebene Verzeichnis und alle Unterverzeichnisse rekursiv nach Dateien.
	:: /b: Aktiviert das "Bare Format", nur die vollständigen Pfade der gefundenen Dateien werden angezeigt.
	:: >: Ausgabeumleitung
	dir /s /b "%MOD_SRC%\*.java" > "%SRC_LIST%"

	:: === Prüfen ob Datei leer ist
	findstr . "%SRC_LIST%" >nul
	if errorlevel 1 (
		echo Error: No Java files in "%SRC_LIST%" >> "%LOGFILE%"
		::del "%SRC_LIST%"
		exit /b 1
	)

	:: === Kompilierungsoptionen festlegen
	:: Xlint: eXtended linting (statische Codeprüfung)
	:: -Xlint:all aktiviert alle verfügbaren Compiler-Warnungen.
	:: -Werror: Behandelt alle Warnungen als Fehler.
	set "JAVAC_FLAGS=-Xlint:all -Werror -encoding %ENCODING% --release %JAVA_VERSION%"

	:: === Kompilieren
	where javac || (
			echo Error: 'javac' not found in PATH >> "%LOGFILE%"
			exit /b 1
		)
		
	:: -d: stellt sicher, dass die Paketstruktur erhalten bleibt
	:: %MOD_BIN%: Zielverzeichnis für .class-Dateien
	:: Der @-Operator in javac ermöglicht es, Argumente aus einer Datei zu lesen.
	javac %JAVAC_FLAGS% -d "%MOD_BIN%" --module-path "%MODULE_PATH%" @%SRC_LIST% >> "%LOGFILE%" 2>&1
	if %ERRORLEVEL% neq 0 (
		>> "%LOGFILE%" (
			echo Error javac compiling module: "%MOD_NAME%"
			echo Module-path: "%MODULE_PATH%"
		)
		::del "%SRC_LIST%"
		exit /b %ERRORLEVEL%
	)
	del "%SRC_LIST%"
endlocal & exit /b 0