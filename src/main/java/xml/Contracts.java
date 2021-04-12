package xml;


import contracts.Contract;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class Contracts {

    List<Contract> contractList = new ArrayList<>();

    @XmlElement
    public List<Contract> getContractList() {
        return contractList;
    }

    public Contracts() {
    }

    public void setContractList(List<Contract> contractList) {
        this.contractList = contractList;
    }

    @Override
    public String toString() {
        return "Contracts {" +
                "contractList=" + contractList +
                '}';
    }
}
