/*
        Java Core (��������)
        ���� 3. ������ � �������

        1. ��������� ��� ������ (������� � 2 �������), ����������� ���������
        ���������� � ��������� ������� (���� �� ��������) � ������������� ������� (������ �������).

        �) ������� � ������� ������ ����������� ����� ��� ������� �������������� ���������� �����.
        ��� �������������� ������� ��� ������� ������:
        ��������������� ���������� ����� = 20.8 * 8 * ��������� ������,
        ��� ���������� � ������������� �������
        ��������������� ���������� ����� = ������������� �������� ������.

        �) ������� �� ���� ������������ ������ ������ ����������� � ��������� ���.

        �) ** ����������� ���������� ��� ����������� ���������� �������
        (�������� ���� �������� �� ���������� Comparator, Comparable)

        �) ** ������� �����, ���������� ������ �����������,
        � ����������� ����������� ������ ������ � �������������� foreach.
        */

package main;

import workers.*;

import java.util.Arrays;

public class Program {

    public static void main(String[] args) {

        BaseWorkerArray workers = new BaseWorkerArray();

        BaseWorker w = new WorkerFixSalary("����", "������", 30, 50000);
        workers.addElement(w);

        workers.addElement(new WorkerFixSalary("�������", "��������", 25, 40000));
        workers.addElement(new WorkerHourSalary("����", "������", 45, 275));

        System.out.println("�������������� ������ �����������:");
        workers.print();
        System.out.println("-------------------------------------------------------------");

        Arrays.sort(workers.getWorkers());
        System.out.println("����������, ��������������� �� �������:");
        workers.print();
        System.out.println("-------------------------------------------------------------");

        Arrays.sort(workers.getWorkers(), new BaseWorkerComparator());
        System.out.println("����������, ��������������� �� �������� ��������:");
        workers.print();
    }
}
