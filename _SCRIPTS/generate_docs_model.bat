:: --module-path        -> Kompilierte Abhängigkeiten
:: --module-source-path -> Zuordnung: Modulname = Modulordner; notwendig weil Ordner- von Modulname abweicht.
:: --module             -> Modul, für das die Javadoc erzeugt werden soll

@echo off
setlocal

:: === Projektwurzel bestimmen
:: %~dp0 ist der Ordner dieses Skripts.
:: %%~fI wandelt den relativen Pfad in einen absoluten Pfad um.
for %%I in ("%~dp0..") do set "PROJECT_ROOT=%%~fI"

:: === Zielordner für Javadoc
set "DOCS_DIR=%PROJECT_ROOT%\_DOCS\MODULE_model"

echo ### Generating Javadoc for com.github.sweettooth.model ...

javadoc ^
	--module-path "%PROJECT_ROOT%\persistence\bin;%PROJECT_ROOT%\shared\bin" ^
	--module-source-path "com.github.sweettooth.model=%PROJECT_ROOT%\model\src\main\java" ^
	--module com.github.sweettooth.model ^
	-d "%DOCS_DIR%"

:: === Rückgabecode von javadoc sichern
:: ERRORLEVEL muss direkt nach dem javadoc-Aufruf ausgewertet werden,
:: damit kein späterer Befehl den Wert überschreibt.
set "JAVADOC_ERROR=%ERRORLEVEL%"

if %JAVADOC_ERROR% neq 0 (
	echo [ERROR] Javadoc generation failed.
	pause
	endlocal & exit /b %JAVADOC_ERROR%
)

echo ### Javadoc generated in: "%DOCS_DIR%"
pause
endlocal