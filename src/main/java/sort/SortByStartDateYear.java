package sort;

import contracts.Contract;

import java.util.Comparator;

/**
 * сортировка подате заключения контрактов.
 */
public class SortByStartDateYear implements Comparator<Contract> {

    @Override
    final public int compare(final Contract contract, final Contract contract1) {
        if (contract.getStartDate().getYear() > contract1.getStartDate().getYear()) {
            return 1;
        } else if (contract.getStartDate().getYear() < contract1.getStartDate().getYear()) {
            return -1;
        } else {
            return 0;
        }
    }
}
