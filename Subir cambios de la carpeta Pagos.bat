@echo off
set REPO_DIR="C:\Users\Gfmt\Desktop\Todo\Univalle Gean\6-Univalle Sexto Semestre\Eventos\Github\ProyectosEventos"
set CARPETA="Pagos"

:: Verificar si el repositorio existe
if not exist %REPO_DIR% (
    echo El repositorio no existe. Ejecuta primero el script de clonado.
    exit /b
)

:: Ir al repositorio
cd %REPO_DIR%

:: Obtener los últimos cambios del repositorio remoto
git pull origin HEAD

:: Moverse a la carpeta específica
cd %CARPETA%

:: Añadir solo los cambios en la carpeta especificada
git add .

:: Hacer commit con mensaje personalizado
git commit -m "Actualización automática de la carpeta %CARPETA%"

:: Subir solo los cambios de esta carpeta al repositorio
git push origin HEAD

echo Actualización completada

