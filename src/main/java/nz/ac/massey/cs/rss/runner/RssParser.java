package nz.ac.massey.cs.rss.runner;

import nz.ac.massey.cs.sdc.parsers.Rss;
import nz.ac.massey.cs.sdc.parsers.RssItem;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;

public class RssParser {
    public static void main(String[] args) throws Exception {

        JAXBContext jc = JAXBContext.newInstance("nz.ac.massey.cs.sdc.parsers" );
        Unmarshaller unmarshaller = jc.createUnmarshaller();
        File file = new File("media-technology.xml");
        Rss rss = (Rss) unmarshaller.unmarshal(file);

        List<RssItem> items = rss.getChannel().getItem();

        for (RssItem item : items) {
            for (Object obj : item.getTitleOrDescriptionOrLink()) {
                if (obj instanceof JAXBElement<?>) {
                    JAXBElement<?> element = (JAXBElement<?>) obj;
                    String elementName = element.getName().getLocalPart();

                    switch (elementName) {
                        case "title":
                            System.out.println("Title: " + element.getValue());
                            break;
                        case "description":
                            System.out.println("Description: " + element.getValue());
                            break;
                        case "link":
                            System.out.println("Link: " + element.getValue());
                            break;
                        default:
                            break;
                    }
                }
            }
            System.out.println();
        }
    }
}