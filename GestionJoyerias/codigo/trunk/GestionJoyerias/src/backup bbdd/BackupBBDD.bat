echo off
set FECHA= %date%
set FECHA=%FECHA:/=%
set FECHA=%FECHA: =%
set FECHA=%FECHA::=%
set FECHA=%FECHA:,=%
set FICHERO=c:\backup%FECHA%.sql
"C:\Archivos de programa\MySQL\MySQL Server 5.0\bin\mysqldump" --opt --password=123 --user=root gestionjoyerias > %FICHERO%