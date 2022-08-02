call runcrud.bat
if "%ERRORLEVEL%" == "0" goto startChrome
echo.
echo RUNCRUD has errors - breaking work.
goto fail

:fail
echo.
echo There were errors

:startChrome
call start Chrome http://localhost:8080/crud/v1/tasks