package sort;

import contracts.Contract;
import reflection.MyInject;

import java.util.Comparator;

/**
 * пузырбковая сортировка.
 */
public class BubbleSort implements ISorted {
    @Override
    public final Object[] sort(final Object[] array, final Comparator<Contract> comparator) {
        boolean isSorted = false;
        Contract buf;
        while (!isSorted) {
            isSorted = true;
            for (int i = 0; i < array.length - 1; i++) {
                if (comparator.compare((Contract) array[i], (Contract) array[i + 1]) >= 1) {
                    isSorted = false;
                    buf = (Contract) array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = buf;

                }
            }
        }
        return array;
    }
}
