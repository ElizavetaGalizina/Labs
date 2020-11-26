package sort;

import contracts.Contract;

import java.util.Comparator;

/**
 * сортировка контрактов по владельцам.
 */
public class SortByOwner implements Comparator<Contract> {

    @Override
     public int compare(final Contract contract, final Contract contract1) {
        return contract.getOwner().getFio().compareTo(contract1.getOwner().getFio());
    }
}
