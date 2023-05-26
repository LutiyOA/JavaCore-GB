package additional;

/**
 * Класс декоратора, реализующего внешний вид выводимых значений
 */
public class Decorator {
    /**
     *
     * @param value - значение, которое следует закодекорировать
     * @return форматированный требуемый вид выводимых значений
     */
    public static String decorate(int value) {
        return String.format("Ваше число: %d", value);
    }
}
