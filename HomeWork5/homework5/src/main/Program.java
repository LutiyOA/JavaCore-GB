package main;
/*
        Java Core (��������)
        ���� 5. ������� ���

        1. �������� �������, ��������� ��������� ����� ���� ������ � ����������(��� �������������)
        �� ����� ��������� ����� ./backup

        2. ����������� ����� Tree � ����� print ������� �� ����������� �� ��������.
        ��� ����� ������ ����������� ����������� ������ ���������� � ������ ������������
        �������� ����������.

        3***. ������������, ��� ����� � �������� ������� �� 9 ��������� ����� ��������[0, 3],
        � ������������ �����, ��������, ��������� ����� ���� ��� ���� � ��������-������,
        ��� 0 � ��� ������ ����, 1 � ��� ���� � ���������, 2 � ��� ���� � �������,
        3 � ��������� ��������.

        ����� ������������� �������� ������� � ����� ����� ���� int �� ���� 3�3.
        �������� � ���� 9 �������� ���, ����� ��� ������ ��� �����
*/

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Program {


    public static void main(String[] args) {
        int[] arrayXO = {
                // 0 � ��� ������ ����,
                // 1 � ��� ���� � ���������,
                // 2 � ��� ���� � �������,
                // 3 � ��������� ��������
                3, 1, 3,
                2, 2, 0,
                1, 0, 3
        };

        System.out.println("----------------- ������� 1 --------------------");
        backupFilesWithoutDirectory(".", "/backup");

        System.out.println("\n----------------- ������� 2 --------------------");
        System.out.println("������������� ���� ���� � ����� � �������� ��� � ����: " + convertFieldToInt(arrayXO));
        writeToFileXO(convertFieldToInt(arrayXO), "test1.txt");
        System.out.println("������� �� ����� ����� ��� �������������� � ����: " + readFromFileXO("test1.txt"));

        // ������� ������ ����� � ������ ������ ����������
        System.out.println("\n----------------- ������� 3 --------------------");
        Tree.print(new File("."), "", true);
    }

    /**
     * ��������� ����������� ������ ������ �� ����� (��� �������������)
     *
     * @param path         ���� �������� ������
     * @param subDirToCopy ��� ������������� ���� ����� ����������� �����
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
                    System.out.printf("���� %s ���������� � %s\n", f.toPath(), dest.toPath());
                } catch (IOException e) {
                    System.out.printf("������ ����������� ����� %s!\n", dest.getName());
                }
            }
        }
    }

    /**
     * ����������� ���� ���������-������� � ����� ���� int
     *
     * @param array ������-���� ���������-�������
     * @return ���������� ����� ����� ��������������
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
     * ������ �� ����� 3-� ���� � �������������� �� � int-��������
     *
     * @param fileName ��� ����� ��� ��������� ������
     * @return ����� ���� int �������������� ����� ���� ���������-������� ��������
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
     * ������ int-�������� ��������� � ����
     *
     * @param number   ������������ ��������
     * @param fileName ��� �����
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

