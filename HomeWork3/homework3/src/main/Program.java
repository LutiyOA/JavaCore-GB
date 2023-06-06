/*
        Java Core (семинары)
        ”рок 3.  лассы и объекты

        1. ѕостроить три класса (базовый и 2 потомка), описывающих некоторых
        работников с почасовой оплатой (один из потомков) и фиксированной оплатой (второй потомок).

        а) ќписать в базовом классе абстрактный метод дл€ расчЄта среднемес€чной заработной платы.
        ƒл€ Ђповременщиковї формула дл€ расчета такова:
        Ђсреднемес€чна€ заработна€ плата = 20.8 * 8 * почасова€ ставкаї,
        дл€ работников с фиксированной оплатой
        Ђсреднемес€чна€ заработна€ плата = фиксированна€ мес€чна€ оплатаї.

        б) —оздать на базе абстрактного класса массив сотрудников и заполнить его.

        в) ** –еализовать интерфейсы дл€ возможности сортировки массива
        (обратите ваше внимание на интерфейсы Comparator, Comparable)

        г) ** —оздать класс, содержащий массив сотрудников,
        и реализовать возможность вывода данных с использованием foreach.
        */

package main;

import workers.*;

import java.util.Arrays;

public class Program {

    public static void main(String[] args) {

        BaseWorkerArray workers = new BaseWorkerArray();

        BaseWorker w = new WorkerFixSalary("ѕетр", "ѕетров", 30, 50000);
        workers.addElement(w);

        workers.addElement(new WorkerFixSalary("“имофей", "„есноков", 25, 40000));
        workers.addElement(new WorkerHourSalary("»ван", "»ванов", 45, 275));

        System.out.println("ѕервоначальный массив сотрудников:");
        workers.print();
        System.out.println("-------------------------------------------------------------");

        Arrays.sort(workers.getWorkers());
        System.out.println("—отрудники, отсортированные по фамилии:");
        workers.print();
        System.out.println("-------------------------------------------------------------");

        Arrays.sort(workers.getWorkers(), new BaseWorkerComparator());
        System.out.println("—отрудники, отсортированные по мес€чной зарплате:");
        workers.print();
    }
}
