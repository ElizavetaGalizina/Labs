package Sort;

import Contracts.Contract;

import java.util.Comparator;

public class SortByOwner implements Comparator<Contract> {

    @Override
    public int compare(Contract contract, Contract contract1) {
        return contract.getOwner().getFio().compareTo(contract1.getOwner().getFio());
    }
}
