@echo off

rem ��¼��ǰλ��
set "CURRENT_DIR=%cd%"

rem jdkԴ��Ŀ¼
cd ..
set "SOURCE_DIR=%cd%\src"
cd /d %CURRENT_DIR%

rem ����rt_debug.jar��ŵ�Ŀ¼
set "RT_DEBUG_DIR=%CURRENT_DIR%\jdk_debug"

rem jdklibĿ¼���������Ҫ�õģ�
rem set "LIB_PATH=%JAVA_HOME%\jre\lib\rt.jar;%JAVA_HOME%\lib\tools.jar"
set "LIB_PATH=%JAVA_HOME%\jre\lib\rt.jar %JAVA_HOME%\lib\tools.jar"

rem rt_debug.jar��Ҫ��ŵ�λ��
set "RT_DEBUG_ENDORSED_DIR=%JAVA_HOME%\jre\lib\endorsed"

rem ��ʾJAVA_HOME����
rem echo "%JAVA_HOME%"

rem ���jdk_debug�����ڣ�����д���
if not exist "%RT_DEBUG_DIR%" mkdir "%RT_DEBUG_DIR%"

rem ������Ҫ������ļ��б�
dir /B /S /X "%SOURCE_DIR%\*.java" > "%CURRENT_DIR%\filelist.txt"

rem ִ�б������
rem javac���ܲ�֧����ָ��cp��ʱ��д��ͬ·����jar��������������ֱ�Ӱ�rt.jar��tools.jar���Ƶ���ǰĿ¼��
rem javac -J-Xms16m -J-Xmx1024m -encoding UTF-8 -sourcepath %SOURCE_DIR% -cp %LIB_PATH% -d %RT_DEBUG_DIR% -g @filelist.txt >> log.txt 2>&1

rem ������jar�����Ƶ���ǰĿ¼�£���������ڣ����ƹ�ȥ��
rem ��ʱ���·���ı���
set "my_path="
setlocal EnableDelayedExpansion
for %%i in (%LIB_PATH%) do (
rem setlocal
call:getFileName "%%i"
if not exist "!my_path!" copy /y "%%i" "%CURRENT_DIR%"
rem endlocal
)
setlocal DisableDelayedExpansion

javac -encoding UTF-8 -J-Xms16m -J-Xmx1024m -sourcepath %SOURCE_DIR% -cp rt.jar;tools.jar -d %RT_DEBUG_DIR% -g @filelist.txt >> log.txt 2>&1

rem ����rt_debug.jar
cd /d "%RT_DEBUG_DIR%"&&jar cf0 rt_debug.jar *

rem �������ɵ�jar���ŵ�JDK_HOME\jre\lib\endorsed�У����û��endorsed�ļ��У����ֶ�������
if not exist "%RT_DEBUG_ENDORSED_DIR%" mkdir "%RT_DEBUG_ENDORSED_DIR%"
copy /y "%RT_DEBUG_DIR%\rt_debug.jar" "%RT_DEBUG_ENDORSED_DIR%\rt_debug.jar"

pause&goto:eof


rem �Զ��庯����ͨ��ȫ·������ļ���
:getFileName
rem for %%a in ("%~1") do (echo %%~nxa)
rem for %%a in ("%~1") do (echo %CURRENT_DIR%\%%~nxa)
for %%a in ("%~1") do (
set "my_path=%CURRENT_DIR%\%%~nxa"
)
goto:eof

rem �ο�����
rem �����bat�ű��ж��庯���� https://www.jb51.net/article/53016.htm
rem ��δ��ļ�ȫ·������ȡ�ļ����� https://blog.csdn.net/techfield/article/details/83061295
rem ����ƥ��ģʽ https://www.jb51.net/article/97588.htm
rem forѭ�����޷��ı������ֵ https://zhidao.baidu.com/question/140583844767053805.html
rem https://www.cnblogs.com/mq0036/p/3478108.html