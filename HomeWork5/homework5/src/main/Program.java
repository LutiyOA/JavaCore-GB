package main;
/*
        Java Core (семинары)
        Урок 5. Внешний мир

        1. Написать функцию, создающую резервную копию всех файлов в директории(без поддиректорий)
        во вновь созданную папку ./backup

        2. Доработайте класс Tree и метод print который мы разработали на семинаре.
        Ваш метод должен распечатать полноценное дерево директорий и файлов относительно
        корневой директории.

        3***. Предположить, что числа в исходном массиве из 9 элементов имеют диапазон[0, 3],
        и представляют собой, например, состояния ячеек поля для игры в крестики-нолики,
        где 0 – это пустое поле, 1 – это поле с крестиком, 2 – это поле с ноликом,
        3 – резервное значение.

        Такое предположение позволит хранить в одном числе типа int всё поле 3х3.
        Записать в файл 9 значений так, чтобы они заняли три байта
*/

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Program {


    public static void main(String[] args) {
        int[] arrayXO = {
                // 0 – это пустое поле,
                // 1 – это поле с крестиком,
                // 2 – это поле с ноликом,
                // 3 – резервное значение
                3, 1, 3,
                2, 2, 0,
                1, 0, 3
        };

        System.out.println("----------------- Задание 1 --------------------");
        backupFilesWithoutDirectory(".", "/backup");

        System.out.println("\n----------------- Задание 2 --------------------");
        System.out.println("Преобразовали поле игры в число и записали его в файл: " + convertFieldToInt(arrayXO));
        writeToFileXO(convertFieldToInt(arrayXO), "test1.txt");
        System.out.println("Считали из файла число для преобразования в поле: " + readFromFileXO("test1.txt"));

        // Выводим список папок и файлов текущй директории
        System.out.println("\n----------------- Задание 3 --------------------");
        Tree.print(new File("."), "", true);
    }

    /**
     * Резервное копирование только файлов из папки (без поддиректорий)
     *
     * @param path         Путь исходных файлов
     * @param subDirToCopy Имя поддиректории куда будут скопированы файлы
     */
    public static void backupFilesWithoutDirectory(String path, String subDirToCopy) {
        File[] currentDirectory = (new File(path)).listFiles();
        new File(path + subDirToCopy).mkdir();
        File pathBackup = new File(path + subDirToCopy);
        File dest = null;

        for (File f : currentDirectory) {
            if (f.isFile()) {
                try {
                    dest = new File(path + subDirToCopy + "/" + f.getName());
                    Files.copy(f.toPath(), dest.toPath());
                    System.out.printf("Файл %s скопирован в %s\n", f.toPath(), dest.toPath());
                } catch (IOException e) {
                    System.out.printf("Ошибка копирования файла %s!\n", dest.getName());
                }
            }
        }
    }

    /**
     * Конвертация поля крестиков-ноликов в число типа int
     *
     * @param array Массив-поле крестиков-ноликов
     * @return полученное число путем преобразования
     */
    public static int convertFieldToInt(int[] array) {
        int number = 0;

        for (int i = 0; i < array.length; i++) {
            if (i != 0)
                number = number << 2;
            number = number | array[i];
        }
        return number;
    }

    /**
     * Чтение из файла 3-х байт и преобразование их в int-значение
     *
     * @param fileName имя файла где находятся данные
     * @return число типа int представляющее собой поле крестиков-ноликов побитово
     */
    public static int readFromFileXO(String fileName) {
        int result = 0;
        try (FileInputStream fileInputStream = new FileInputStream(fileName)) {
            byte[] arrayFromRead = new byte[3];
            fileInputStream.read(arrayFromRead);
            result = (Byte.toUnsignedInt(arrayFromRead[0]) << 16) | Byte.toUnsignedInt(arrayFromRead[1]) << 8 | Byte.toUnsignedInt(arrayFromRead[2]);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    /**
     * Запись int-значения побайтово в файл
     *
     * @param number   записываемое значение
     * @param fileName имя файла
     */
    public static void writeToFileXO(int number, String fileName) {

        try (FileOutputStream fileOutputStream = new FileOutputStream(fileName)) {

            // String s = Integer.toBinaryString(number);
            // System.out.println(s);
            byte[] arrayToWrite = {
                    (byte) ((number & 0xFF0000) >> 16),
                    (byte) ((number & 0xFF00) >> 8),
                    (byte) (number & 0xFF)
            };

            fileOutputStream.write(arrayToWrite);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

