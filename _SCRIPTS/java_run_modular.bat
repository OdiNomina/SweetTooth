:: === Projekt-Wurzelverzeichnis setzen
set "SCRIPT_FOLDER=_SCRIPTS"
set "CURRDIR=%~dp0"
set "CURRDIR=%CURRDIR:~0,-1%"
for %%X in ("%CURRDIR%") do set "CURR_FOLDER=%%~nX"
if /I "%CURR_FOLDER%"=="%SCRIPT_FOLDER%" (
    for %%Y in ("%~dp0..") do set "BASEDIR=%%~fY"
) else (
    set "BASEDIR=%CURRDIR%"
)

set "LOGFILE_DIR=%BASEDIR%\_LOGFILES"
if not exist "%LOGFILE_DIR%" (
	mkdir "%LOGFILE_DIR%"
)

:: ====== Globale Einstellungen
set "LOGFILE=%LOGFILE_DIR%\java_run.log"
set "BIN_DIR=bin"
set "LIB=%BASEDIR%\lib"
:: "Liste" der Module (Abhängigkeitsreihenfolge beachten)
set "MODULES=model controller view launcher"
:: Modulname, wie er in modul-info.java steht
set "MAIN_MODULE=com.github.sweettooth.launcher"
set "MAIN_MODULE_SHORT=launcher"
:: Vollqualifizierter Name der main class
set "MAIN_CLASS=com.github.sweettooth.launcher.app.SweetTooth"

:: Lokaler Scope der Variablen und verzögerte Auswertung aktivieren (Mit !Variable! erfolgt die Auswertung zur Laufzeit, nicht beim Parsen)
setlocal EnableDelayedExpansion
	:: === Zeitstempel im LOGFILE
	>> "%LOGFILE%" (
		echo(
		echo === Run started ===================
		echo Date: %DATE% Time: %TIME%
	)

	:: === Module-path aufbauen
	:: In Batch-Skripten (.bat) muss die Schleifenvariable mit %% beginnen
	:: %MODULES%: Elemente, über die iteriert wird (getrennt durch Leerzeichen)
	set "MODULE_PATH=#"
	for %%A in (%MODULES%) do (
		set "MOD_DIR=%BASEDIR%\%%A"
		set "MOD_BIN_PATH=!MOD_DIR!\%BIN_DIR%"
		set "MODULE_PATH=!MODULE_PATH!;!MOD_BIN_PATH!"
	)
	set "MODULE_PATH=!MODULE_PATH:#;=!;%LIB%"

	:: === Prüfen ob main class kompiliert wurde
	:: %MAIN_CLASS:.=\% Ersetzungssyntax: %Variable:Suchstring=Ersetzung% (Punkt im vollqualifizierten Namen durch Backslash ersetzen)
	if not exist "%BASEDIR%\%MAIN_MODULE_SHORT%\%BIN_DIR%\%MAIN_CLASS:.=\%.class" (
		echo Error: Main class not compiled! >> "%LOGFILE%"
		exit /b 1
	)

	:: === Anwendung ausführen
	where java || (
			echo Error: 'java' not found in PATH >> "%LOGFILE%"
			exit /b 1
		)
	
	java --module-path "%MODULE_PATH%" --module "%MAIN_MODULE%/%MAIN_CLASS%" >> "%LOGFILE%" 2>&1

endlocal & exit /b 0