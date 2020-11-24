package Sort;

import Contracts.Contract;

import java.util.Comparator;

public class InsertSort implements ISorted {

    @Override
    public Object[] sort(Object[] array, Comparator<Contract> comparator) {
        for (int i = 1; i < array.length; i++) {
            Contract current = (Contract)array[i];
            int j = i - 1;
            while (j >= 0 && comparator.compare(current, (Contract)array[j]) < 0) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = current;
        }
        return array;
    }
}
