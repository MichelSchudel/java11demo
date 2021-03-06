package nl.craftsmen.demo;

import org.junit.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 * JavaEE has been completely removed from jdk11, so you need to declare depdendencies the the JAXB runtime in your build system.
 */
public class JaxbDemo {

    @Test
    public void test() {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Person.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            Person person = new Person();
            person.setName("jan");
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(person, System.out);

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
