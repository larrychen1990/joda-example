@echo off

@ REM
@ REM Set this property to the location of your copy of the sample code
@ REM
@set PROJECT_ROOT=C:\home\workspace\joda-example

@set CP=%CLASSPATH%;%PROJECT_ROOT%\target\classes;%PROJECT_ROOT%\lib\log4j-1.2.15.jar;%PROJECT_ROOT%/lib/joda-time-1.6.jar
@rem ===========================================
@rem = You will need to modify these according =
@rem = to where your JDK and Ant are installed =
@rem ===========================================
@set JAVA_HOME=C:\home\jdk1.6.0_14
@set ANT_HOME=C:\home\apache-ant-1.7.1

@echo Starting Build ...
@call %ANT_HOME%\bin\ant -f %PROJECT_ROOT%\build.xml all

if NOT "%ERRORLEVEL%"=="0" goto DONE

%JAVA_HOME%\bin\java %SYSTEM_VARIABLES% -classpath %CP% com.makotogroup.joda.KeyConcepts
%JAVA_HOME%\bin\java %SYSTEM_VARIABLES% -classpath %CP% com.makotogroup.joda.CreatingJodaTimeObjects
%JAVA_HOME%\bin\java %SYSTEM_VARIABLES% -classpath %CP% com.makotogroup.joda.ManipulatingTime
%JAVA_HOME%\bin\java %SYSTEM_VARIABLES% -classpath %CP% com.makotogroup.joda.FormattingTime

goto DONE

:DONE
