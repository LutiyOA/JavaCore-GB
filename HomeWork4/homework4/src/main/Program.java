package main;
/*
Java Core (семинары)
Урок 4. Обработка исключений
Задача 1:

1 Напишите метод, на вход которого подаётся двумерный строковый массив размером 4х4. При
подаче массива другого размера необходимо бросить исключение MyArraySizeException.

2 Далее метод должен пройтись по всем элементам массива, преобразовать в int и
просуммировать. Если в каком-то элементе массива преобразование не удалось (например, в
ячейке лежит символ или текст вместо числа), должно быть брошено исключение
MyArrayDataException с детализацией, в какой именно ячейке лежат неверные данные.

3 В методе main() вызвать полученный метод, обработать возможные исключения
MyArraySizeException и MyArrayDataException и вывести результат расчета.
 */

public class Program {
    public static void main(String[] args) {
        String[][] array = {
                { "1","2","3","4"},
                { "5","6","7","8"},
                { "9","10","11","12"},
                {"13","14","15","16"},

        };
        try {
            System.out.printf("Сумма элементов массива равна: %s",Method(array));
        } catch (MyArraySizeException ex) {
            System.out.println(ex.getMessage());
//            ex.printStackTrace();
        }
    }

    /**
     * Мметод подсчета суммы цифр двумерного массива
     * @param array массив 4х4
     * @return сумма элементов
     * @throws MyArraySizeException исключение размерности массива
     */
    public static int Method(String[][] array) throws MyArraySizeException {
        if (array.length!=4)
            throw new MyArraySizeException("Количество строк массива не равна 4!");

        for (int i = 0; i < array.length; i++) {
            if (array[i].length!=4)
                throw new MyArraySizeException("Размерность "+(i+1)+"-й строки массива не равна 4!");
        }

        int result=0;
        for (int i=0; i<array.length; i++) {
            for (int j=0; j<array[i].length; j++) {
                try {
                    result += Integer.parseInt(array[i][j]);
                } catch(NumberFormatException ex) {
                    throw new MyArrayDataException("Некорректный элемент в ячейке ",i,j);
                }
            }

        }
        return result;
    }
}
