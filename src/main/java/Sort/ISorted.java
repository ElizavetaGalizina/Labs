package Sort;

import Contracts.Contract;

import java.util.Comparator;

public interface ISorted {

    Object[] sort(Object[] array, Comparator<Contract> comparator);
}
