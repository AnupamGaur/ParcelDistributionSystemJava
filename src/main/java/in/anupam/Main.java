package in.anupam;

import in.anupam.models.Container;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;

import jakarta.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.UncheckedIOException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws JAXBException, FileNotFoundException {
    Container container = parseXml("Container_68465468.xml");
        System.out.println(container.getId());
        System.out.println(container.getShippingDate());
        System.out.println(container.getParcels().size());
    }


    public static Container parseXml(String filename) throws JAXBException, FileNotFoundException
    {
        JAXBContext context = JAXBContext.newInstance(Container.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        try(FileReader reader = new FileReader(filename)) {
            return (Container) unmarshaller.unmarshal(reader);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

}