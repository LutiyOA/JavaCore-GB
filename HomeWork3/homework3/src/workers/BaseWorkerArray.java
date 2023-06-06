package workers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * �����, ���������� ������ ����������
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
     * ������ ������� ����������
     */
    public void print() {
        for (BaseWorker worker : workers) {
            System.out.println(worker);
        }
    }

    /**
     * ���������� �������� � ������
     * @param element
     */
    public void addElement(BaseWorker element) {

        List<BaseWorker> list = new ArrayList<>(Arrays.asList(workers));
        list.add(element);
        workers = list.toArray(new BaseWorker[0]);

    }

}
