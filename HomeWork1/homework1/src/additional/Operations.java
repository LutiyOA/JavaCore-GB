package additional;

/**
 * Вспомогательные классы, реализующие основные
 * математические операции
 */
public class Operations {
    /**
     *
     * @param value1 - первое слагаемое
     * @param value2 - второе слагаемое
     * @return сумма принимаемых слагаемых
     */
    public static int add(int value1, int value2) {
        return value1+value2;
    }

    /**
     *
     * @param value1 уменьшаемое значение
     * @param value2 вычитаемое значение
     * @return разница value1 - value2
     */
    public static int sub(int value1, int value2) {
        return value1-value2;
    }

    /**
     *
     * @param value1 - первый множитель
     * @param value2 - второй множитель
     * @return результат умножения принимаемых на вход множителей
     */
    public static int mul(int value1, int value2) {
        return value1 * value2;
    }

    /**
     *
     * @param value1 - делимое
     * @param value2 - делитель
     * @return частное - результат value1 / value2
     */
    public static int div(int value1, int value2) {
        return value1 / value2;
    }


}
