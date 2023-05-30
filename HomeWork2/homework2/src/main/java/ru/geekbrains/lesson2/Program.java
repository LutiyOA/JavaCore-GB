package ru.geekbrains.lesson2;

import java.util.Random;
import java.util.Scanner;

public class Program {


    private static final int WIN_COUNT = 4;
    private static final char DOT_HUMAN = 'X';
    private static final char DOT_AI = 'O';
    private static final char DOT_EMPTY = '•';

    private static final int SIZE_X = 5;
    private static final int SIZE_Y = 5;


    private static final Scanner SCANNER = new Scanner(System.in);

    private static char[][] field; // Двумерный массив хранит текущее состояние игрового поля

    private static final Random random = new Random();

    private static int fieldSizeX; // Размерность игрового поля
    private static int fieldSizeY; // Размерность игрового поля


    public static void main(String[] args) {
        while (true){
            initialize();
            printField();
            while (true){
                humanTurn();
                printField();
                if (gameCheck(DOT_HUMAN, "Вы победили!"))
                    break;
                aiTurn();
                printField();
                if (gameCheck(DOT_AI, "Компьютер победил!"))
                    break;
            }
            System.out.println("Желаете сыграть еще раз? (Y - да)");
            if (!SCANNER.next().equalsIgnoreCase("Y"))
                break;
        }
    }

    /**
     * Инициализация игрового поля
     */
    private static void initialize(){
        // Установим размерность игрового поля
        fieldSizeX = SIZE_X;
        fieldSizeY = SIZE_Y;


        field = new char[fieldSizeX][fieldSizeY];
        // Пройдем по всем элементам массива
        for (int x = 0; x < fieldSizeX; x++) {
            for (int y = 0; y < fieldSizeY; y++) {
                // Проинициализируем все элементы массива DOT_EMPTY (признак пустого поля)
                field[x][y] = DOT_EMPTY;
            }
        }
    }

    /**
     * Отрисовка игрового поля
     * //TODO: Поправить отрисовку игрового поля
     */
    private static void printField(){
        // печатаем шапку поля
        System.out.print("+");
        for (int i = 0; i < fieldSizeY * 2 + 1; i++){
            System.out.print((i % 2 == 0) ? "-" : i / 2 + 1);
        }
        System.out.println();

        // выводим само поле
        for (int i = 0; i < fieldSizeX; i++){
            System.out.print(i + 1 + "|");

            for (int j = 0; j <  fieldSizeY; j++)
                System.out.print(field[i][j] + "|");

            System.out.println();
        }

        // отрисовываем подвал
        for (int i = 0; i < fieldSizeX * 2 + 2; i++){
            System.out.print("-");
        }
        System.out.println();

    }

    /**
     * Обработка хода игрока (человек)
     */
    private static void humanTurn(){
        int x, y;
        do
        {
            System.out.print("Введите координаты хода X и Y (от 1 до 3) через пробел >>> ");
            x = SCANNER.nextInt() - 1;
            y = SCANNER.nextInt() - 1;
        }
        while (!isCellValid(x, y) || !isCellEmpty(x, y));
        field[x][y] = DOT_HUMAN;
    }

    /**
     * Проверка, ячейка является пустой
     * @param x количество строк поля
     * @param y количество столбцов поля
     * @return проверка на незаполненность ячейки поля
     */
    static boolean isCellEmpty(int x, int y){
        return field[x][y] == DOT_EMPTY;
    }

    /**
     * Проверка корректности ввода
     * (координаты хода не должны превышать размерность массива, игрового поля)
     * @param x количество строк поля
     * @param y количество столбцов поля
     * @return признак незаполненной ячейки
     */
    static boolean isCellValid(int x, int y){
        return x >= 0 &&  x < fieldSizeX && y >= 0 && y < fieldSizeY;
    }

    /**
     * Ход компьютера
     */
    private static void aiTurn(){
        int x, y;
        do
        {
            x = random.nextInt(fieldSizeX);
            y = random.nextInt(fieldSizeY);
        }
        while (!isCellEmpty(x, y));
        field[x][y] = DOT_AI;
    }

    /**
     * Проверка победы
     * TODO: Переработать метод в домашнем задании
     * @param c
     * @return
     */
    static boolean checkWin(char c){
        // Проверка по трем горизонталям
        if (field[0][0] == c && field[0][1] == c && field[0][2] == c) return true;
        if (field[1][0] == c && field[1][1] == c && field[1][2] == c) return true;
        if (field[2][0] == c && field[2][1] == c && field[2][2] == c) return true;

        // Проверка по диагоналям
        if (field[0][0] == c && field[1][1] == c && field[2][2] == c) return true;
        if (field[0][2] == c && field[1][1] == c && field[2][0] == c) return true;

        // Проверка по трем вертикалям
        if (field[0][0] == c && field[1][0] == c && field[2][0] == c) return true;
        if (field[0][1] == c && field[1][1] == c && field[2][1] == c) return true;
        if (field[0][2] == c && field[1][2] == c && field[2][2] == c) return true;

        return false;
    }

    /**
     * Проверка на ничью
     * @return
     */
    static boolean checkDraw(){
        for (int x = 0; x < fieldSizeX; x++){
            for (int y = 0; y < fieldSizeY; y++)
                if (isCellEmpty(x, y)) return false;
        }
        return true;
    }

    /**
     * Метод проверки состояния игры
     * @param c
     * @param str
     * @return
     */
    static boolean gameCheck(char c, String str){
        if (checkWin(c)){
            System.out.println(str);
            return true;
        }
        if (checkDraw()){
            System.out.println("Ничья!");
            return true;
        }

        return false; // Игра продолжается
    }

}
