package workers;

import java.util.Comparator;

/**
 * Comparator дл€ сортировки по мес€чной зарплате
 */
public class BaseWorkerComparator implements Comparator<BaseWorker> {
    @Override
    public int compare(BaseWorker o1, BaseWorker o2) {

        double result = o1.calcSalary() - o2.calcSalary();
        if (result < 0) return -1;
        else if (result > 0) return 1;

        return 0;
    }
}
