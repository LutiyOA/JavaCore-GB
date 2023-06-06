package workers;

/**
 *  ласс работника с фиксированной мес€чной зарплатой
 */
public class WorkerFixSalary extends BaseWorker {
    @Override
    double calcSalary() {
        return salary;
    }

    public WorkerFixSalary(String name, String surname, int age) {
        super(name, surname, age);
    }

    public WorkerFixSalary(String name, String surname, int age, double salary) {
        super(name, surname, age, salary);
    }
}
