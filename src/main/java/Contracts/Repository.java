package Contracts;

import Sort.BubbleSort;
import Sort.ISorted;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;


/**
 * класс репозитория
 *
 */
public class Repository {

    private int defaultCapacity = 1;
    private Contract[] contract = new Contract[defaultCapacity];
    private int index = 0;

    private ISorted sorter = new BubbleSort();

    /**
     * конструктор без параметров
     */
    public Repository() {
    }

    /**
     * увеличение длины репозитория
     * @param newLength новая длина
     */
    private void arrayCompression(int newLength) {
        Contract[] newContractArray = new Contract[newLength];
        System.arraycopy(contract, 0, newContractArray, 0, index);
        contract = newContractArray;
    }

    /**
     * добавление нового контракта в репозиторий
     * @param t контракт
     */
    public void add(Contract t) {
        if (index == contract.length)
            arrayCompression(contract.length*2);
        contract[index++] = t;
    }

    /**
     * сортировка по выбранным параметрам
     * @param comparator
     */
    public void sortBy(Comparator<Contract> comparator) {
        sorter.sort(contract, comparator);
    }


    /**
     * получение контракта по id
     * @param ind id искомого контракта
     * @return искомый контракт
     */
    public Contract get(int ind){
        for (int i = 0; i < contract.length; i++) {
            if (contract[i].id == ind) {
                System.out.println(contract[i].toString());
                return  contract[i];
            }
        }
        return null;
    }

    /**
     * удаление контракта по id
     * @param ind идентификатор
     * @return contract
     */
    public Contract[] delete(int ind){
        for (int i = 0; i < contract.length; i++) {
            if (contract[i].id == ind) {
                for (int j = i; j < contract.length - 1; j++) {
                    contract[j] = contract[j + 1];
                }
                index--;
                contract[contract.length-1]=null;
                return contract;
            }
        }
        return null;
    }

    /**
     * поиск контрактов по параметрам
     * @param predicate параметры
     * @return список контрактов, удовлетворяющих условию
     */
    public Repository searh(Predicate<Contract> predicate){
        Repository list=new Repository();
        for(int i=0;i<index;i++){
            if (predicate.test(contract[i])){
                list.add(contract[i]);
            }
        }
        return list;
    }

    @Override
    public String toString() {
        return "Repository{" +
                " contract=" + Arrays.toString(contract) +
                ", index=" + index +
                '}';
    }
}
