@echo off
echo ==========================================
echo Instalador de PostgreSQL + Base de Datos
echo ==========================================
echo.

set POSTGRES_VERSION=17
set INSTALLER_NAME=postgresql-%POSTGRES_VERSION%-windows-x64.exe
set INSTALL_DIR="C:\PostgreSQL"
set DB_NAME=granja_avicola
set DB_USER=postgres
set DB_PASS=postgres

echo Descargando PostgreSQL %POSTGRES_VERSION%...
powershell -Command "Invoke-WebRequest -Uri 'https://get.enterprisedb.com/products/postgresql-%POSTGRES_VERSION%-windows-x64.exe' -OutFile '%TEMP%\%INSTALLER_NAME%'"

if %errorlevel% neq 0 (
    echo Descarga fallida. Probando otra fuente...
    powershell -Command "Invoke-WebRequest -Uri 'https://sbp.enterprisedb.com/get-binary/download.do?filename=postgresql-%POSTGRES_VERSION%-windows-x64.exe' -OutFile '%TEMP%\%INSTALLER_NAME%'"
)

echo.
echo Instalando PostgreSQL (silencioso)...
echo La instalacion puede tardar varios minutos...
echo.

"%TEMP%\%INSTALLER_NAME%" --mode unattended --unattendedmodeui minimal --superpassword %DB_PASS% --serverport 5432 --installoptions "COMPONENT=psql,COMPONENT=powershell,COMPONENT=pgadmin" --installcheck --prefix %INSTALL_DIR%

echo.
echo Esperando instalacion completa...
timeout /t 30 /nobreak >nul

echo.
echo Creando base de datos '%DB_NAME%'...
"%INSTALL_DIR%\17\bin\psql.exe" -U %DB_USER% -c "CREATE DATABASE %DB_NAME%;" -p 5432

if %errorlevel% equ 0 (
    echo.
    echo ==========================================
    echo INSTALACION COMPLETA
    echo ==========================================
    echo Base de datos: %DB_NAME%
    echo Usuario: %DB_USER%
    echo Puerto: 5432
    echo.
    echo Para conectar usar:
    echo Host: localhost
    echo Puerto: 5432
    echo Base de datos: %DB_NAME%
    echo Usuario: %DB_USER%
    echo Contraseña: %DB_PASS%
) else (
    echo.
    echo Advertencia: No se pudo crear la base de datos.
    echo Verifica que PostgreSQL se instalo correctamente.
)

echo.
pause