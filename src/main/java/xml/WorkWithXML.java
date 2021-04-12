package xml;

import contracts.Contract;
import contracts.Repository;
import enity.People;
import enums.Gender;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class WorkWithXML {

    /**
     * Java file -> XML file
     * @param repository репозиторий с контрактами
     */
    public void toXml(Repository repository) {
        try {
            JAXBContext context = JAXBContext.newInstance(Contracts.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            Contracts contractsList = new Contracts();
            List<Contract> contractList = Arrays.asList(repository.getContract());
            for (Contract element : contractList) {
                contractsList.getContractList().add(element);
            }
            File output = new File("out.xml");
            marshaller.marshal(contractsList, output);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    /**
     * XML file -> Java file
     *
     * @return - Репозиторий с контрактами
     */
    public Repository fromXml(String file) {
        try {
            File xmlFile = new File(file);

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = factory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);

            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("contractList");

            Repository repository = new Repository();
            for (int i = 0; i < nList.getLength(); i++) {
                Node nNode = nList.item(i);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element elem = (Element) nNode;
                    String id = elem.getElementsByTagName("id").item(1).getTextContent();
                    String numberContract = elem.getElementsByTagName("numberContract").item(0).getTextContent();
                    String startDate = elem.getElementsByTagName("startDate").item(0).getTextContent();
                    String endDate = elem.getElementsByTagName("endDate").item(0).getTextContent();
                    NodeList list = doc.getElementsByTagName("people");
                    Node node = list.item(i);
                    Element elem1 = (Element) node;
                    String bithday = elem1.getElementsByTagName("bithday").item(0).getTextContent();
                    String fio = elem1.getElementsByTagName("fio").item(0).getTextContent();
                    String idPeople = elem1.getElementsByTagName("id").item(0).getTextContent();
                    String passportNumber = elem1.getElementsByTagName("passportNumber").item(0).getTextContent();
                    String passportSeries = elem1.getElementsByTagName("passportSeries").item(0).getTextContent();
                    String sex = elem1.getElementsByTagName("sex").item(0).getTextContent();

                    Contract contract = new Contract(Integer.parseInt(id),LocalDate.parse(startDate),LocalDate.parse(endDate),Integer.parseInt(numberContract),
                            new People(Integer.parseInt(idPeople),fio, LocalDate.parse(bithday),passportSeries,passportNumber, Gender.valueOf(sex)));
                    repository.add(contract);
                }
            }
            return repository;
        } catch (SAXException | IOException | IllegalArgumentException | ParserConfigurationException e) {
            e.printStackTrace();
        }

        return null;
    }
}

