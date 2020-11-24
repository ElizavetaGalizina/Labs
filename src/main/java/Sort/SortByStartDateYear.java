package Sort;

import Contracts.Contract;

import java.util.Comparator;

public class SortByStartDateYear implements Comparator<Contract> {

    @Override
    public int compare(Contract contract, Contract contract1) {
        if (contract.getStartDate().getYear() > contract1.getStartDate().getYear() )
            return 1;
        else if (contract.getStartDate().getYear() < contract1.getStartDate().getYear())
            return -1;
        else
            return 0;
    }
}
