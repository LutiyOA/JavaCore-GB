package workers;

/**
 * ����� ��������� � ��������� ������� �����
 */
public class WorkerHourSalary extends BaseWorker {
    /**
     * ��������� �������� �������� ������ �� �������� ��������� ������
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
