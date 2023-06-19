package main;

/**
 *  ласс исключени€ о некорректности €чейки массива к преобразованию в число
 */
public class MyArrayDataException extends NumberFormatException {
    private int row;
    private int col;

    public MyArrayDataException(String s, int row, int col) {
        super(s);
        this.row = row;
        this.col = col;
    }

    @Override
    public String getMessage() {
        return super.getMessage() + "["+(row+1)+"]["+(col+1)+"]";
    }
}
