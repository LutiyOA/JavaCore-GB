package workers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Класс, содержащий массив работников
 */
public class BaseWorkerArray {
    private BaseWorker[] workers;

    public BaseWorker[] getWorkers() {
        return workers;
    }

    public BaseWorkerArray() {
        this.workers = new BaseWorker[0];
    }

    /**
     * Печать массива работников
     */
    public void print() {
        for (BaseWorker worker : workers) {
            System.out.println(worker);
        }
    }

    /**
     * добавление элемента в массив
     * @param element
     */
    public void addElement(BaseWorker element) {

        List<BaseWorker> list = new ArrayList<>(Arrays.asList(workers));
        list.add(element);
        workers = list.toArray(new BaseWorker[0]);

    }

}
