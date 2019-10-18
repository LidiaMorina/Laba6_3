package com.company;

import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;

public class Marshall {
    //метод  с маршелизацией
    public void  marshaller(){
        try {
            JAXBContext context = JAXBContext.newInstance(Schedule.class);
            Marshaller m = context.createMarshaller();

            // анонимный класс
            Schedule schedule = new Schedule() {
                {
                    //добавление первый предмет
                    MarshallSchedule.Object obj = new MarshallSchedule.Object("Programming", "Friday", "Г312");
                    MarshallSchedule mS = new MarshallSchedule("Petrov P.P", "12", "30", obj);
                    this.add(mS);

                    //добавление  второй предмет
                    obj = new MarshallSchedule.Object("Database", "Wednesday", "А502");
                    mS = new MarshallSchedule("Ivanov I.I.", "5", "18", obj);
                    this.add(mS);
                    //добавление третий предмет
                    obj = new MarshallSchedule.Object("Math", "Tuesday", "Д202");
                    mS = new MarshallSchedule("Sidirov S.S.", "7", "20", obj);
                    this.add(mS);
                }
            };

            //маршелизация
            m.marshal(schedule, new FileOutputStream("example.xml"));
            System.out.println("XML-файл создан");

        } catch (FileNotFoundException e) {
            System.out.println("XML-файл не может быть создан: " + e);
        } catch (JAXBException e) {
            System.out.println("JAXB-контекст ошибочен " + e);
        }
    }

    //метод  с демаршелизацией
    public void  unMarshaller(){
        try {
            JAXBContext context = JAXBContext.newInstance(Schedule.class);
            System.out.println("Демаршелизация XML:");
            Unmarshaller um = context.createUnmarshaller();
            String schemaName = "schedule_XSD.xsd";
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            File schemaLocation = new File(schemaName);
            // создание схемы и перадача ее демарашаллизатору
            Schema schema = factory.newSchema(schemaLocation);
            um.setSchema(schema);
            Schedule sch = (Schedule) um.unmarshal(new File("example.xml"));
            System.out.println(sch);

        } catch (JAXBException e) {
            System.out.println("JAXB-контекст ошибочен " + e);
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }

}
