:: --module-path 	-> Alle kompilierten Abhängigkeiten
:: --add-modules	-> Root-Modul im Graphen (required modules are loaded automatically)
:: -sourcepath 		-> Pfad zum Quellcode, aus dem die Doku generiert wird
:: --module			-> Welches Modul dokumentiert werden soll
@echo off
setlocal
:: === Pfadnormalisierung mit ~f (relativen in absoluten Pfad)
for %%I in ("%~dp0..") do set PROJECT_ROOT=%%~fI
:: === Zielordner
set DOCS_DIR=%PROJECT_ROOT%\_DOCS\model\api

echo ### Generating Javadoc for com.github.sweettooth.model ...

javadoc ^
	--module-path %PROJECT_ROOT%\model\bin;%PROJECT_ROOT%\shared\bin ^
	--add-modules com.github.sweettooth.model ^
	-sourcepath %PROJECT_ROOT%\model\src\main\java ^
	--module com.github.sweettooth.model ^
	-d "%DOCS_DIR%"
	
set JAVADOC_ERROR=%ERRORLEVEL%
IF %JAVADOC_ERROR% NEQ 0 (
    echo [ERROR] Javadoc generation failed.
	pause
	endlocal & exit /b %JAVADOC_ERROR%
)

echo ### Javadoc generated in: "%DOCS_DIR%"
pause
endlocal