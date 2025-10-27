package in.anupam;

import in.anupam.config.ConfigLoader;
import in.anupam.demos.demo1;
import in.anupam.models.Container;

import in.anupam.models.Parcel;
import in.anupam.models.Receipient;
import in.anupam.processors.ParcelProcessor;
import in.anupam.rules.RuleEntry;

import jakarta.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static in.anupam.parsers.ParcelXMLParser.parseXml;

public class Main {
    public static void main(String[] args) throws JAXBException, FileNotFoundException {
        demo1.main(new String[]{});
    }
}