@echo off

rem Компиляция класса Implementor.java

rem note -- пути можно было вынести
javac -d . ^
    -cp ..\..\java-advanced-2025\modules\info.kgeorgiy.java.advanced.implementor;..\..\java-advanced-2025\modules\info.kgeorgiy.java.advanced.implementor.tools ^
    ..\java-solutions\info\kgeorgiy\ja\stolbov\implementor\Implementor.java

rem Создание JAR файла с манифестом
jar cfm Impler.jar MANIFEST.MF info

rem Очистка временных файлов, если нужно
rmdir /s /q info

echo JAR файл успешно создан: Impler.jar

pause

rem note -- вы уж старайтесь как-то менее палевно юзать чатгпт....
