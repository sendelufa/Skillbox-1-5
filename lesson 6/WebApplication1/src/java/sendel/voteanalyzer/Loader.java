package sendel.voteanalyzer;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;

/**
 * Created by Danya on 24.02.2016.
 */
public class Loader
{
    public static long start;

    public static void main(String[] args) throws Exception
    {

        String fileName = "res/data-1572M.xml";
        start = System.currentTimeMillis();
        parseFile(fileName);
        System.out.println((System.currentTimeMillis() - start) + "ms");
    }

    private static void parseFile(String fileName) throws Exception
    {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        Handler handler = new Handler();
        parser.parse(new File(fileName), handler);
        handler.printResults();
    }

}