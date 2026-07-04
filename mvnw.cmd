@REM ----------------------------------------------------------------------------
@REM Maven Start Up Batch script
@REM ----------------------------------------------------------------------------
@echo off
@setlocal

set ERROR_CODE=0

@rem Set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" goto win_nt

:win_nt
set MAVEN_PROJECTBASEDIR=%~dp0
set WRAPPER_JAR="%MAVEN_PROJECTBASEDIR%.mvn\wrapper\maven-wrapper.jar"
set WRAPPER_PROPERTIES="%MAVEN_PROJECTBASEDIR%.mvn\wrapper\maven-wrapper.properties"
set WRAPPER_LAUNCHER=org.apache.maven.wrapper.MavenWrapperMain

set DOWNLOAD_URL="https://repo.maven.apache.org/maven2/org/apache/maven/wrapper/maven-wrapper/3.3.2/maven-wrapper-3.3.2.jar"

FOR /F "usebackq tokens=1,2 delims==" %%A IN (%WRAPPER_PROPERTIES%) DO (
    IF "%%A"=="wrapperUrl" SET DOWNLOAD_URL=%%B
)

@rem Find the project base dir, i.e. the directory that contains the folder ".mvn".
@rem Fallback to current directory if not found.

set MAVEN_PROJECTBASEDIR=%MAVEN_WRAPPER_PROJECT_BASEDIR%
IF NOT "%MAVEN_PROJECTBASEDIR%"=="" goto valBasedir

find_maven_basedir:

if "D:%~dp0" == "" goto eof
pushd %~dp0
if exist ".mvn" goto valDir

:loop
for %%i in (..) do (
    set dir=%%~fsi
    if "D:%dir%" == "D:%~dp0" goto valBasedir
)
cd ..
set dir=%CD%
if exist ".mvn" goto valDir
goto loop

:valDir
set MAVEN_PROJECTBASEDIR=%CD%
popd
goto eof

:valBasedir
cd /D %MAVEN_PROJECTBASEDIR%
popd

:eof
if "%MAVEN_PROJECTBASEDIR%"=="" (
  echo Unable to find Maven base directory. Check your project structure.
  goto end
)

@rem Detect if Java is available on the PATH
set JAVA_HOME_OUT=%JAVA_HOME%
if not "%JAVA_HOME_OUT%"=="" goto javaFound
for /f "tokens=*" %%a in ('where java 2^>nul') do (
    set JAVA_EXEC=%%a
    goto javaExecFound
)
goto javaNotFound

:javaFound
set JAVA_EXEC=%JAVA_HOME_OUT%\bin\java.exe
goto checkWrapper

:javaExecFound
:checkWrapper

if exist %WRAPPER_JAR% (
    goto runWrapper
)

echo Downloading Maven wrapper...
"%JAVA_EXEC%" -Dmaven.multiModuleProjectDirectory="%MAVEN_PROJECTBASEDIR%" ^
  -Dmaven.wrapper.localRepositoryPath="%USERPROFILE%\.m2\repository" ^
  -jar %WRAPPER_JAR% %WRAPPER_LAUNCHER% %*
goto end

:runWrapper
"%JAVA_EXEC%" -Dmaven.multiModuleProjectDirectory="%MAVEN_PROJECTBASEDIR%" ^
  -Dmaven.wrapper.localRepositoryPath="%USERPROFILE%\.m2\repository" ^
  -jar %WRAPPER_JAR% %WRAPPER_LAUNCHER% %*
if ERRORLEVEL 1 goto error
goto end

:javaNotFound
echo Java not found on PATH.
exit /B 1

:error
set ERROR_CODE=1

:end
@endlocal & SET ERROR_CODE=%ERROR_CODE%
if "%MAVEN_SKIP_RC%"=="" (
  exit /B %ERROR_CODE%
)
