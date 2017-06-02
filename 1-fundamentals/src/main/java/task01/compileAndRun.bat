@: ѕредполагаетс€ запуск из текущей директории,
@: при этом компил€ци€ выполн€етс€ в соответствии со структурой текущего прокекта
cd ../../../..
javac -cp src/main/java;. -d build/classes/main src/main/java/task01/main/Main.java
java -cp ./build/classes/main task01.main.Main

@: ¬озврат в текущую директорию (≈сли запускаем из командной строки и хотим запускать несколько раз подр€д)
cd src/main/java/task01