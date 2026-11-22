@echo off
powershell.exe -NoProfile -ExecutionPolicy Bypass -File "%~dp0.mvn\wrapper\run.ps1" %*
exit /b %ERRORLEVEL%
