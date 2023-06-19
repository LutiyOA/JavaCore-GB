package main;

/**
 * Класс исключения о неверной размерности массива
 */
public class MyArraySizeException extends IndexOutOfBoundsException {
    public MyArraySizeException(String s) {
        super(s);
    }
}
