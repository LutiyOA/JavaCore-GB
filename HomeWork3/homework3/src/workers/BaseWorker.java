package workers;

/**
 * Абстрактный базовый класс работника
 */
public abstract class BaseWorker implements Comparable {
    protected final String name;
    protected final String surname;
    protected int age;
    protected double salary;

    abstract double calcSalary();

    /**
     * для сравнения по "фамилия+имя"
     * @param o the object to be compared.
     * @return
     */
    @Override
    public int compareTo(Object o) {
        BaseWorker b = (BaseWorker) o;
        return (surname + name).compareTo(b.surname + b.name);
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public BaseWorker(String name, String surname, int age) {
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    public BaseWorker(String name, String surname, int age, double salary) {
        this(name, surname, age);
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "BaseWorker{" +
                "Фамилия = '" + surname + '\'' +
                ", Имя = '" + name + '\'' +
                ", возраст = " + age +
                ", мес.зарплата = " + calcSalary() +
                '}';
    }
}
