@if "%DEBUG%" == "" @echo off
@rem ##########################################################################
@rem
@rem  takehome startup script for Windows
@rem
@rem ##########################################################################

@rem Set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" setlocal

set DIRNAME=%~dp0
if "%DIRNAME%" == "" set DIRNAME=.
set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%..

@rem Add default JVM options here. You can also use JAVA_OPTS and TAKEHOME_OPTS to pass JVM options to this script.
set DEFAULT_JVM_OPTS=

@rem Find java.exe
if defined JAVA_HOME goto findJavaFromJavaHome

set JAVA_EXE=java.exe
%JAVA_EXE% -version >NUL 2>&1
if "%ERRORLEVEL%" == "0" goto init

echo.
echo ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:findJavaFromJavaHome
set JAVA_HOME=%JAVA_HOME:"=%
set JAVA_EXE=%JAVA_HOME%/bin/java.exe

if exist "%JAVA_EXE%" goto init

echo.
echo ERROR: JAVA_HOME is set to an invalid directory: %JAVA_HOME%
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:init
@rem Get command-line arguments, handling Windows variants

if not "%OS%" == "Windows_NT" goto win9xME_args

:win9xME_args
@rem Slurp the command line arguments.
set CMD_LINE_ARGS=
set _SKIP=2

:win9xME_args_slurp
if "x%~1" == "x" goto execute

set CMD_LINE_ARGS=%*

:execute
@rem Setup the command line

set CLASSPATH=%APP_HOME%\lib\ifc-app-0.0.1-SNAPSHOT.jar;%APP_HOME%\lib\log4j-api-2.8.2.jar;%APP_HOME%\lib\log4j-core-2.8.2.jar;%APP_HOME%\lib\cxf-rt-frontend-jaxrs-3.1.0.jar;%APP_HOME%\lib\javax.inject-1.jar;%APP_HOME%\lib\jackson-jaxrs-json-provider-2.6.3.jar;%APP_HOME%\lib\jackson-databind-2.6.3.jar;%APP_HOME%\lib\cglib-nodep-2.2.2.jar;%APP_HOME%\lib\spring-core-3.2.15.RELEASE.jar;%APP_HOME%\lib\spring-context-3.2.15.RELEASE.jar;%APP_HOME%\lib\spring-web-3.2.15.RELEASE.jar;%APP_HOME%\lib\jetty-server-8.1.18.v20150929.jar;%APP_HOME%\lib\jetty-webapp-8.1.18.v20150929.jar;%APP_HOME%\lib\cxf-core-3.1.0.jar;%APP_HOME%\lib\javax.ws.rs-api-2.0.1.jar;%APP_HOME%\lib\javax.annotation-api-1.2.jar;%APP_HOME%\lib\cxf-rt-transports-http-3.1.0.jar;%APP_HOME%\lib\jackson-jaxrs-base-2.6.3.jar;%APP_HOME%\lib\jackson-core-2.6.3.jar;%APP_HOME%\lib\jackson-module-jaxb-annotations-2.6.3.jar;%APP_HOME%\lib\jackson-annotations-2.6.0.jar;%APP_HOME%\lib\commons-logging-1.1.3.jar;%APP_HOME%\lib\spring-aop-3.2.15.RELEASE.jar;%APP_HOME%\lib\spring-beans-3.2.15.RELEASE.jar;%APP_HOME%\lib\spring-expression-3.2.15.RELEASE.jar;%APP_HOME%\lib\javax.servlet-3.0.0.v201112011016.jar;%APP_HOME%\lib\jetty-continuation-8.1.18.v20150929.jar;%APP_HOME%\lib\jetty-http-8.1.18.v20150929.jar;%APP_HOME%\lib\jetty-xml-8.1.18.v20150929.jar;%APP_HOME%\lib\jetty-servlet-8.1.18.v20150929.jar;%APP_HOME%\lib\woodstox-core-asl-4.4.1.jar;%APP_HOME%\lib\xmlschema-core-2.2.1.jar;%APP_HOME%\lib\aopalliance-1.0.jar;%APP_HOME%\lib\jetty-io-8.1.18.v20150929.jar;%APP_HOME%\lib\jetty-util-8.1.18.v20150929.jar;%APP_HOME%\lib\jetty-security-8.1.18.v20150929.jar;%APP_HOME%\lib\stax2-api-3.1.4.jar

@rem Execute takehome
"%JAVA_EXE%" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %TAKEHOME_OPTS%  -classpath "%CLASSPATH%" com.crowcompass.takehome.InternationalFixedCalendarApp %CMD_LINE_ARGS%

:end
@rem End local scope for the variables with windows NT shell
if "%ERRORLEVEL%"=="0" goto mainEnd

:fail
rem Set variable TAKEHOME_EXIT_CONSOLE if you need the _script_ return code instead of
rem the _cmd.exe /c_ return code!
if  not "" == "%TAKEHOME_EXIT_CONSOLE%" exit 1
exit /b 1

:mainEnd
if "%OS%"=="Windows_NT" endlocal

:omega
