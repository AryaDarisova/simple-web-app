package data.managers;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import utils.PreferencesManagerConstants;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import javax.xml.xpath.XPathConstants;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class PreferencesManager {
    static PreferencesManager instanse;
    DocumentBuilderFactory factory;
    DocumentBuilder builder;
    Document document;
    XPath xPath;

    private PreferencesManager() {
        try {
            factory = DocumentBuilderFactory.newInstance();
            builder = factory.newDocumentBuilder();
            document = builder.parse(new File("C:\\Users\\Arya\\Desktop\\Web Application\\src\\resources\\configuration\\appConfig"));
            XPathFactory xPathFactory = XPathFactory.newInstance();
            this.xPath = xPathFactory.newXPath();
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    public static PreferencesManager getInstance() {
        if (instanse == null){
            instanse = new PreferencesManager();
        }
        return instanse;
    }

    public Node lastKey(String key) {
        try {
            String newKey = key.replace(".", "/");
            Node node = (Node) xPath.evaluate(newKey, document, XPathConstants.NODE);
            return node;
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setProperty(String key, String value) {
        lastKey(key).setTextContent(value);
        writeXml();
    }

    public String getProperty(String key) {
        return lastKey(key).getTextContent();
    }

    public void setProperties(Properties prop) {
        String[] keys = prop.stringPropertyNames().toArray(new String[0]);
        for (String key: keys) {
            setProperty(key, prop.getProperty(key));
        }
    }

    public Properties getProperties() {
        Properties properties = new Properties();
        String[] keys = {PreferencesManagerConstants.CLASS_NAME, PreferencesManagerConstants.DRIVER_TYPE,
                PreferencesManagerConstants.HOST_NAME, PreferencesManagerConstants.PORT,
                PreferencesManagerConstants.DB_NAME, PreferencesManagerConstants.USER,
                PreferencesManagerConstants.PASSWORD};
        for(String key : keys){
            properties.setProperty(key, getProperty(key));
        }
        return properties;
    }

    public void addBindedObject(String name, String className) {
        Element element = document.createElement("blindedobject");
        element.setAttribute("name", name);
        element.setAttribute("class", className);
        document.getElementsByTagName("server").item(0).appendChild(element);

    }

    public void removeBindedObject(String name) {
        NodeList node = document.getElementsByTagName("blindedobject");
        for (int i = 0; i < node.getLength(); i++) {
            Element element = (Element) node.item(i);
            if (element.getAttribute("name").equals(name)) {
                element.getParentNode().removeChild(element);
            }
        }
        writeXml();
    }

    @Deprecated
    public void setClassName(String className) {
        document.getElementsByTagName("classname").item(0).setTextContent(className);
        writeXml();
    }

    @Deprecated
    public String getClassName() {
        return document.getElementsByTagName("classname").item(0).getTextContent();
    }

    @Deprecated
    public void setDriverType(String driverType) {
        document.getElementsByTagName("drivertype").item(0).setTextContent(driverType);
        writeXml();
    }

    @Deprecated
    public String getDriverType() {
        return document.getElementsByTagName("drivertype").item(0).getTextContent();
    }

    @Deprecated
    public void setHostName(String hostName) {
        document.getElementsByTagName("hostName").item(0).setTextContent(hostName);
        writeXml();
    }

    @Deprecated
    public String getHostName() {
        return document.getElementsByTagName("hostName").item(0).getTextContent();
    }

    @Deprecated
    public void setPort(String port) {
        document.getElementsByTagName("port").item(0).setTextContent(port);
        writeXml();
    }

    @Deprecated
    public String getPort() {
        return document.getElementsByTagName("port").item(0).getTextContent();
    }

    @Deprecated
    public void setDBName(String DBName) {
        document.getElementsByTagName("DBName").item(0).setTextContent(DBName);
        writeXml();
    }

    @Deprecated
    public String getDBName() {
        return document.getElementsByTagName("DBName").item(0).getTextContent();
    }

    @Deprecated
    public void setUser(String user) {
        document.getElementsByTagName("user").item(0).setTextContent(user);
        writeXml();
    }

    @Deprecated
    public String getUser() {
        return document.getElementsByTagName("user").item(0).getTextContent();
    }

    @Deprecated
    public void setPassword(String password) {
        document.getElementsByTagName("pass").item(0).setTextContent(password);
        writeXml();
    }

    @Deprecated
    public String getPassword() {
        return document.getElementsByTagName("pass").item(0).getTextContent();
    }

    private void writeXml() {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File("C:\\Users\\Arya\\Desktop\\Web Application\\src\\resources\\configuration\\appConfig"));
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
