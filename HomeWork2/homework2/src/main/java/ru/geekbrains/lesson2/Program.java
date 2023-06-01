/**
 * Программа реализует игру крестики-нолики
 * произвольного размера, в т.ч. прямоугольное поле
 * и настраиваемое количество символов,
 * которые необходимо поставить подряд
 */

package ru.geekbrains.lesson2;

import java.util.Random;
import java.util.Scanner;

public class Program {


    private static final int WIN_COUNT = 4; // кол-во символов для выигрыша
    private static final char DOT_HUMAN = 'X';
    private static final char DOT_AI = 'O';
    private static final char DOT_EMPTY = '•';

    private static final int SIZE_ROW = 5; // количество строк поля
    private static final int SIZE_COL = 9; // количество столбцов поля


    private static final Scanner SCANNER = new Scanner(System.in);

    private static char[][] field; // Двумерный массив хранит текущее состояние игрового поля

    private static final Random random = new Random();

    private static int fieldSizeRow; // Размерность игрового поля
    private static int fieldSizeCol; // Размерность игрового поля


    public static void main(String[] args) {
        while (true) {
            initialize();
            printField();
            while (true) {
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
    private static void initialize() {
        // Установим размерность игрового поля
        fieldSizeRow = SIZE_ROW;
        fieldSizeCol = SIZE_COL;


        field = new char[fieldSizeRow][fieldSizeCol];
        // Пройдем по всем элементам массива
        for (int x = 0; x < fieldSizeRow; x++) {
            for (int y = 0; y < fieldSizeCol; y++) {
                // Проинициализируем все элементы массива DOT_EMPTY (признак пустого поля)
                field[x][y] = DOT_EMPTY;
            }
        }
    }

    /**
     * Отрисовка игрового поля
     * //TODO: Поправить отрисовку игрового поля
     */
    private static void printField() {
        // печатаем шапку поля
        System.out.print("+");
        for (int i = 0; i < fieldSizeCol * 2 + 1; i++) {
            System.out.print((i % 2 == 0) ? "-" : i / 2 + 1);
        }
        System.out.println();

        // выводим само поле
        for (int i = 0; i < fieldSizeRow; i++) {
            System.out.print(i + 1 + "|");

            for (int j = 0; j < fieldSizeCol; j++)
                System.out.print(field[i][j] + "|");

            System.out.println();
        }

        // отрисовываем подвал
        for (int i = 0; i < fieldSizeCol * 2 + 2; i++) {
            System.out.print("-");
        }
        System.out.println();

    }

    /**
     * Обработка хода игрока (человек)
     */
    private static void humanTurn() {
        int x, y;
        do {
            System.out.printf("Введите координаты хода X (от 1 до %d) и Y (от 1 до %d) через пробел >>> ",fieldSizeRow,fieldSizeCol);
            x = SCANNER.nextInt() - 1;
            y = SCANNER.nextInt() - 1;
        }
        while (!isCellValid(x, y) || !isCellEmpty(x, y));
        field[x][y] = DOT_HUMAN;
    }

    /**
     * Проверка, ячейка является пустой
     *
     * @param x количество строк поля
     * @param y количество столбцов поля
     * @return проверка на незаполненность ячейки поля
     */
    static boolean isCellEmpty(int x, int y) {
        return field[x][y] == DOT_EMPTY;
    }

    /**
     * Проверка корректности ввода
     * (координаты хода не должны превышать размерность массива, игрового поля)
     *
     * @param x количество строк поля
     * @param y количество столбцов поля
     * @return признак незаполненной ячейки
     */
    static boolean isCellValid(int x, int y) {
        return x >= 0 && x < fieldSizeRow && y >= 0 && y < fieldSizeCol;
    }

    /**
     * Ход компьютера
     */
    private static void aiTurn() {
        int x, y;
        do {
            x = random.nextInt(fieldSizeRow);
            y = random.nextInt(fieldSizeCol);
        }
        while (!isCellEmpty(x, y));
        field[x][y] = DOT_AI;
    }

    /**
     * Проверка максимального количества подряд идущих символов в поле
     *
     * @param symbol   проверяемый символ
     * @param startRow стартовая строка
     * @param startCol стартовый столбец
     * @param direct   направление поиска (1 - по горизонтали, 2 - по вертикали, 3 - по диагонали, 4 - по обратной диагонали)
     * @return макисмальное количество подряд идущих символов в поле по направлению direct
     */
    static int checkCountSymbolNew(char symbol, int startRow, int startCol, int direct) {
        int incrementRow = 0;
        int incrementCol = 0;
        int result = 0;
        int curRow = startRow;
        int curCol = startCol;
        int countSymbol = 0;
        char curSymbol;

        switch (direct) {
            case 1: // горизонталь
                incrementRow = 0;
                incrementCol = 1;
                break;

            case 2: // вертикаль
                incrementRow = 1;
                incrementCol = 0;
                break;
            case 3: // прямая диагональ
                incrementRow = 1;
                incrementCol = 1;
                break;
            case 4: // обратная диагональ
                incrementRow = 1;
                incrementCol = -1;
                break;
        }

        while (curRow >= 0 && curRow < fieldSizeRow && curCol >= 0 && curCol < fieldSizeCol) {
            curSymbol = field[curRow][curCol];
            if (curSymbol == symbol) {
                countSymbol++;
                if (countSymbol > result) {
                    result = countSymbol;
                }
            } else {
                countSymbol = 0;
            }
            curRow += incrementRow;
            curCol += incrementCol;
        }

        return result;
    }

    static boolean checkWin(char c) {


        // Проверка по горизонталям
        for (int i = 0; i < fieldSizeRow; i++) {
            if (checkCountSymbolNew(c, i, 0, 1) == WIN_COUNT) {
                // || checkCountSymbolNew(DOT_AI, i, 0, 1) == WIN_COUNT) {
                return true;
            }
        }

        // Проверка по вертикалям
        for (int j = 0; j < fieldSizeCol; j++) {
            if (checkCountSymbolNew(c, 0, j, 2) == WIN_COUNT) {
                // || checkCountSymbolNew(DOT_AI, 0, j, 2) == WIN_COUNT)
                return true;
            }
        }


        // Проверка по прямым диагоналям
        // часть 1
        for (int j = fieldSizeCol - 1; j >= 0; j--) {
            if (checkCountSymbolNew(c, 0, j, 3) == WIN_COUNT) {
                // || checkCountSymbolNew(DOT_AI, 0, j, 3) == WIN_COUNT) {
                return true;
            }
        }

        // часть 2
        for (int i = 0; i < fieldSizeRow; i++) {
            if (checkCountSymbolNew(c, i, 0, 3) == WIN_COUNT) {
                // || checkCountSymbolNew(DOT_AI, i, 0, 3) == WIN_COUNT) {
                return true;
            }
        }

        // Проверка по обратным диагоналям
        // часть 1
        for (int j = 0; j < fieldSizeCol; j++) {
            if (checkCountSymbolNew(c, 0, j, 4) == WIN_COUNT) {
                // || checkCountSymbolNew(DOT_AI, 0, j, 4) == WIN_COUNT) {
                return true;
            }
        }

        // часть 2
        for (int i = 1; i < fieldSizeRow; i++) {
            if (checkCountSymbolNew(c, i, fieldSizeCol - 1, 4) == WIN_COUNT) {
                // || checkCountSymbolNew(DOT_AI, i, fieldSizeCol - 1, 4) == WIN_COUNT) {
                return true;
            }
        }

        return false;
    }

    /**
     * Проверка на ничью
     *
     * @return признак заполненности поля - т.е. ничьей
     */
    static boolean checkDraw() {
        for (int x = 0; x < fieldSizeRow; x++) {
            for (int y = 0; y < fieldSizeCol; y++)
                if (isCellEmpty(x, y)) return false;
        }
        return true;
    }

    /**
     * Метод проверки состояния игры
     *
     * @param c символ для проверки (или DOT_HUMAN или DOT_AI
     * @param str выводимая строка в случае чьей-то победы
     * @return признак того, что есть выигравший игрок
     */
    static boolean gameCheck(char c, String str) {
        if (checkWin(c)) {
            System.out.println(str);
            return true;
        }
        if (checkDraw()) {
            System.out.println("Ничья!");
            return true;
        }

        return false; // Игра продолжается
    }

}
