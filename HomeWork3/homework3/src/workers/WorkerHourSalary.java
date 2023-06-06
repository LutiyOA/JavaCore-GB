package workers;

/**
 * Класс работника с почасовой оплатой труда
 */
public class WorkerHourSalary extends BaseWorker {
    /**
     * Вычисляем месячную зарплату исходя из хранимой почасовой ставки
     * @return
     */
    @Override
    double calcSalary() {
        return 20.8 * 8 * salary;
    }

    public WorkerHourSalary(String name, String surname, int age) {
        super(name, surname, age);
    }

    public WorkerHourSalary(String name, String surname, int age, double salary) {
        super(name, surname, age, salary);
    }

}
