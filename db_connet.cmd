@echo off
REM Se conecta a la DB Docker, usa el archivo .env para la contraseña

for /f "tokens=1,* delims==" %%A in ('findstr /r "^[A-Z_]*=" .env') do set %%A=%%B

echo Iniciando conexión al contenedor Docker...

docker exec -it %CONTAINER_NAME% sh -c "PGPASSWORD='%DB_USER_PASSWORD%' psql -U %DB_USER% -d %DB_NAME%"

echo Conexión finalizada.
pause