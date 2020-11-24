package Validators;

import Contracts.Contract;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс, в котором реализованы методы для проверки корректности данных о контрактe
 */
public class ValidatorContract {

    /**
     * Итоговый результат для проверки контракта
     */
    static List<String> result=new ArrayList<String>();

    /**
     * Метод, проверяющий корректность данных о ID контаркта
     * @param id ID контаркта
     * @return корректная/некорректная запись
     */
    public static String checkIDContract(int id){
        if (id<=0){
            return "Ошибка: некорректно указан ID контракта";
        } else {
            return "Корректная запись";
        }
    }

    /**
     * Метод, проверяющий корректность данных о номере контаркта
     * @param number номер контракта
     * @return корректная/некорректная запись
     */
    public static String checkNumberContract(int number){
        if (number<0){
            return "Ошибка: некорректно указан номер контракта";
        } else {
            return "Корректная запись";
        }
    }

    /**
     * Метод, проверяющий корректность даты начала и даты завершения контракта. Необходимо, чтобы дата начала была меньше даты завершения контаркта
     * @param start дата начала контаркта
     * @param end дата завершения контаркта
     * @return корректная/некорректная запись
     */
    public static String checkDateContract(LocalDate start, LocalDate end){
        int startYear = start.getYear();
        int startMonth = start.getMonthValue();
        int startDay = start.getDayOfMonth();
        int endYear = end.getYear();
        int endMonth = end.getMonthValue();
        int endDay = end.getDayOfMonth();
        if (startYear<endYear || (startYear==endYear && startMonth<endMonth) || (startYear==endYear && startMonth==endMonth && startDay<endDay)){
            return "Корректная запись";
        } else{
            return "Ошибка: некорректная запись даты начала и завершения контракта";
        }
    }

    /**
     * Метод, проверяющий корректность пакета каналов в контракте на телевидение
     * @param addInfo
     * @return корректная/некорректная запись
     */
    public static String checkTVContracts(String[] addInfo){
        if (addInfo.length == 1){
            return "Корректная запись";
        } else{
            return "Ошибка: некорректная запись пакета каналов";
        }
    }

    /**
     * Метод, проверяющий корректность скорости интернета в контракте на интернет
     * @param addInfo скорость контракта
     * @return корректная/неккоректная запись
     */
    public static String checkInternetContract(String[] addInfo){
        if (addInfo.length==1 && Integer.parseInt(addInfo[0])>0){
            return "Корректная запись";
        }else{
            return "Ошибка: некорректная запись скорости интернета";
        }
    }

    /**
     * Метод, проверяющий корректность данных контаркта на мобильную связь
     * @param addInfo количество минут, смс и гигабайт
     * @return корректная/неккоректная запись
     */
    public static String checkMobileContract(String[] addInfo){
        if (addInfo.length==3 && Integer.parseInt(addInfo[0])>0 && Integer.parseInt(addInfo[1])>0 && Integer.parseInt(addInfo[2])>0){
            return "Корректная запись";
        }else{
            return "Ошибка: некорректная запись параметров контракта на мобильную связь";
        }
    }

    /**
     * Метод, проверяющий владельца контракта
     * @param bDay день рождения владельца контракта
     * @return сообщение о корректной/некорректной записи данных о владельце
     */
    public static String checkOwner(LocalDate bDay){
        int bithdayYear=bDay.getYear();
        LocalDate now = LocalDate.now();
        if (now.getYear()-bithdayYear>18 || (now.getYear()-bithdayYear==18)){
            return "Корректная запись";
        } else{
            return "Ошибка: клиент не достиг совершенолетия";
        }
    }

    /**
     * Метод, проверяющий контракт
     * @param contract контракт, который нужно проверить
     * @param contractType тип контракта
     * @param addInfo дополнительная информация по контракту
     * @return
     */
    public ArrayList<String> resultValidateContracts(Contract contract, String contractType, String[] addInfo) {
        result.clear();
        result.add(checkIDContract(contract.getId()));
        result.add(checkNumberContract(contract.getNumberContract()));
        result.add(checkDateContract(contract.getStartDate(), contract.getEndDate()));
        result.add(checkOwner(contract.getOwner().getBithday()));
        if (contractType.equals("TV Contract")) {
            result.add(checkTVContracts(addInfo));
        }
        else if (contractType.equals("Mobile Contract")) {
            result.add(checkMobileContract(addInfo));
        }
        else if (contractType.equals("Internet Contract")) {
            result.add(checkInternetContract(addInfo));
        }
        return (ArrayList<String>) result;
    }


}
