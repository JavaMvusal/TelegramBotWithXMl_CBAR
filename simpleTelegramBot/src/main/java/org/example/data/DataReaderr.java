package org.example.data;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DataReaderr {

    public List<String> getResponse() throws IOException, ParserConfigurationException, SAXException {
        String url = "https://cbar.az/currencies/05.11.2022.xml";
        String nominal = null;
        String name = null;
        String value = null;

            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            int responseCode = con.getResponseCode();
            System.out.println("Response Code:" + responseCode);
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }in.close();


            // System.out.println(response.toString());
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().
                    parse(new InputSource(new StringReader(response.toString())));
            NodeList list = doc.getElementsByTagName("Valute");
            List<String> valuteList = new ArrayList<String>();
            for (int temp = 0; temp < list.getLength(); temp++) {

                Node node = list.item(temp);

                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    Element element = (Element) node;

                    // get staff's attribute

                    // get text
                    nominal = element.getElementsByTagName("Nominal").item(0).getTextContent();
                    name = element.getElementsByTagName("Name").item(0).getTextContent();
                    value = element.getElementsByTagName("Value").item(0).getTextContent();
                    System.out.println("Nominal : " + nominal);
                    System.out.println("Name : " + name);
                    System.out.println("Value : " + value);

                     valuteList.add("Nominal: " + nominal + "\n" +
                             "Name: " + name + "\n" +
                             "Value: " + value);


                }


            }
        return valuteList;



    }
}
