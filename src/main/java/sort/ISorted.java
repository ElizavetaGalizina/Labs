package sort;

import contracts.Contract;
import reflection.MyInject;

import java.util.Comparator;

/**
 * интерфейс сорторовок.
 */

public interface ISorted {
    /**
     * сортировка контрактов.
     * @param array контракты
     * @param comparator параметры
     * @return отсортированный список контрактов
     */
    Object[] sort(Object[] array, Comparator<Contract> comparator);
}
