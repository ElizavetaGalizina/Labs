package sort;

import contracts.Contract;

import java.util.Comparator;

/**
 * сортировка вставками.
 */
public class InsertSort implements ISorted {

    @Override
    public final Object[] sort(final Object[] array, final Comparator<Contract> comparator) {
        for (int i = 1; i < array.length; i++) {
            Contract current = (Contract) array[i];
            int j = i - 1;
            while (j >= 0 && comparator.compare(current, (Contract) array[j]) < 0) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = current;
        }
        return array;
    }
}
