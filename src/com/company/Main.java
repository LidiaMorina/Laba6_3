package com.company;

import org.xml.sax.SAXException;
/*приложение, осуществляющее маршаллизацию/демаршаллизацию с использованием xml, xsd и pojo.*/
public class Main {

    public static void main(String[] args) throws SAXException {

        Marshall marshall = new Marshall();
        //вызов маршаллизации
        marshall.marshaller();
        //вызов валидатора
        Validator valid = new Validator();
        valid.validator();
        //вызов демаршаллизации
        marshall.unMarshaller();
    }
}
