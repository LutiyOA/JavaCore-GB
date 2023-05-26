import additional.*;

public class Main {
    public static void main(String[] args) {
        int val1=10;
        int val2=17;

        System.out.println(Decorator.decorate(Operations.add(val1, val2)));
        System.out.println(Decorator.decorate(Operations.sub(val1, val2)));
        System.out.println(Decorator.decorate(Operations.mul(val1, val2)));
        System.out.println(Decorator.decorate(Operations.div(val1, val2)));

    }
}
