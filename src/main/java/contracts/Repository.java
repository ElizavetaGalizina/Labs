package contracts;

import reflection.MyInject;
import sort.BubbleSort;
import sort.ISorted;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;
import java.util.logging.Logger;

/**
 * класс репозитория.
 */
public class Repository {
    private static Logger logger = Logger.getLogger(Repository.class.getName());
    /**
     * размерность.
     */
    private int defaultCapacity = 1;
    /**
     * контрактыю хранящиеся в репозитории.
     */
    private Contract[] contract = new Contract[defaultCapacity];
    /**
     * количество контрактов.
     */
    private int index = 0;
    /**
     * сортировка.
     */
    @MyInject
    private ISorted sorter;    // = new BubbleSort();

    /**
     * конструктор без параметров.
     */
    public Repository() {
    }

    /**
     * увеличение длины репозитория.
     *
     * @param newLength новая длина
     */
    private void arrayCompression(final int newLength) {
        Contract[] newContractArray = new Contract[newLength];
        System.arraycopy(contract, 0, newContractArray, 0, index);
        contract = newContractArray;
        logger.info("Произошло расширение репозитория");
    }

    /**
     * добавление нового контракта в репозиторий.
     *
     * @param t контракт
     */
    public final void add(final Contract t) {
        if (index == contract.length) {
            arrayCompression(contract.length * 2);
        }
        contract[index++] = t;
        logger.info("Контракт был добавлен в репозиторий");
    }

    /**
     * сортировка по выбранным параметрам.
     *
     * @param comparator параметры
     */
    public final void sortBy(final Comparator<Contract> comparator) {
        sorter.sort(contract, comparator);
        logger.info("Репозиторий был отсортирован");
    }

    /**
     * получение контракта по id.
     *
     * @param ind id искомого контракта
     * @return искомый контракт
     */
    public final Contract get(final int ind) {
        for (int i = 0; i < contract.length; i++) {
            if (contract[i].id == ind) {
                System.out.println(contract[i].toString());
                logger.info("Контракт с идентификатором " + ind + " был получен");
                return contract[i];
            }
        }
        logger.info("Контракта с идентификатором " + ind + " не нашлось");
        return null;
    }

    /**
     * удаление контракта по id.
     *
     * @param ind идентификатор
     * @return contract
     */
    public final Contract[] delete(final int ind) {
        for (int i = 0; i < contract.length; i++) {
            if (contract[i].id == ind) {
                for (int j = i; j < contract.length - 1; j++) {
                    contract[j] = contract[j + 1];
                }
                index--;
                contract[contract.length - 1] = null;
                logger.info("Контракт с идентификатором " + ind + " был удален");
                return contract;
            }
        }
        logger.info("Контракт с идентификатором " + ind + " не удален, так как его нет в репозитории");
        return null;
    }

    /**
     * проверка на пустоту репозитория.
     * @return истина, если нет элементов, иначе - ложь
     */
    public boolean empty() {
        if (index == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * поиск контрактов по параметрам.
     *
     * @param predicate параметры
     * @return список контрактов, удовлетворяющих условию
     */
    public final Repository searh(final Predicate<Contract> predicate) {
        Repository list = new Repository();
        for (int i = 0; i < index; i++) {
            if (predicate.test(contract[i])) {
                list.add(contract[i]);
            }
        }
        if (!list.empty()) {
            logger.info("По заданным параметрам нашлись контракты");
        } else {
            logger.info("По заданным параметрам котрактов не нашлось");
        }
        return list;
    }

    public Contract[] getContract() {
        return contract;
    }

    @Override
    public final String toString() {
        return "Repository{"
                + " contract=" + Arrays.toString(contract)
                + ", index=" + index
                + '}';
    }
}
