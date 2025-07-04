@echo off
rem Генерация Javadoc для класса Implementor.java
javadoc -protected ..\java-solutions\info\kgeorgiy\ja\stolbov\implementor\implementor.java -cp ..\..\java-advanced-2025\modules\info.kgeorgiy.java.advanced.implementor;..\..\java-advanced-2025\modules\info.kgeorgiy.java.advanced.implementor.tools -d ..\javadoc\implementor

rem note -- вообще лучше выводить на латинице, ибо беды с кодировкой
echo Javadoc успешно сгенерирован в папке: ..\javadoc\implementor
pause
