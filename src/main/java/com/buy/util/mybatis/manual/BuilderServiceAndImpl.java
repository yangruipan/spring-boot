package com.buy.util.mybatis.manual;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Properties;

public class BuilderServiceAndImpl {

    public BuilderServiceAndImpl() {

    }

    /**
     * @param args
     * @throws SAXException
     */
    public void render() throws SAXException {
        String projectFilePath = "generatorConfig.properties";
        String classPath = BuilderServiceAndImpl.class.getResource("/").toString();
        String generatorConfigPath = classPath.substring(6)
                + "com/pt/mybatis/generatorConfig.xml";
        this.renderFile(projectFilePath, generatorConfigPath);
    }

    public void delete(String filenames) throws IOException {

        if (filenames == null)
            return;

        String projectFilePath = "com/pt/mybatis/generatorConfig.properties";
        BuilderJavaClassFile bf = new BuilderJavaClassFile(projectFilePath);

        String[] fs = filenames.split(",");

        for (String f : fs) {
            f = f.trim();
            if (f.length() > 0) {
                bf.delete(com.buy.util.mybatis.manual.FileType.BEAN, f);
                bf.delete(com.buy.util.mybatis.manual.FileType.BEANSEARCH, f);
                bf.delete(com.buy.util.mybatis.manual.FileType.MAPPER, f);
                bf.delete(com.buy.util.mybatis.manual.FileType.XML, f);
                bf.delete(com.buy.util.mybatis.manual.FileType.SERVICE, f);
                bf.delete(com.buy.util.mybatis.manual.FileType.IMPL, f);
                bf.delete(com.buy.util.mybatis.manual.FileType.JUNITTEST, f);
            }
        }
    }

    public void renderFile(String projectFilePath, String generatorConfigPath)
            throws SAXException {
        Document doc = readDocument(generatorConfigPath);
        NodeList tables = this.selectNodes(
                "/generatorConfiguration/context/table", doc);
        BuilderJavaClassFile bf = new BuilderJavaClassFile(projectFilePath);

        if (tables != null) {
            for (int i = 0; i < tables.getLength(); i++) {
                NamedNodeMap as = tables.item(i).getAttributes();
                String name = as.getNamedItem("tableName").getNodeValue();
                bf._fileds = this.queryFileds(name);
                try {
                    bf.save(com.buy.util.mybatis.manual.FileType.JUNITTEST, name);
                    bf.save(com.buy.util.mybatis.manual.FileType.IMPL, name);
                    bf.save(com.buy.util.mybatis.manual.FileType.SERVICE, name);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

    public HashMap<String, String> queryFileds(String tableName) {
        HashMap<String, String> hm = new HashMap<String, String>();
        com.buy.util.mybatis.manual.BuilderTableMapping.getConnectionByJDBC();
        String sql = "select column_name,data_type from information_schema.columns  where table_schema='"
                + com.buy.util.mybatis.manual.BuilderTableMapping._schema
                + "' and table_name='"
                + tableName + "'";

        try {
            Statement stmt = com.buy.util.mybatis.manual.BuilderTableMapping.conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String n = rs.getString(1);
                String t = rs.getString(2);
                if (!hm.containsKey(n)) {
                    hm.put(n, t);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (com.buy.util.mybatis.manual.BuilderTableMapping.conn != null)
                    com.buy.util.mybatis.manual.BuilderTableMapping.conn.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
        return hm;
    }

    public Document readDocument(String filePath) throws SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException pce) {
            System.err.println(pce);
            System.exit(1);
        }

        InputStream in = null;

        try {

            in = new FileInputStream(filePath);
        } catch (FileNotFoundException e) {

            e.printStackTrace();
        }

        Document doc = null;
        try {
            builder.setEntityResolver(new IgnoreDTDEntityResolver());
            doc = builder.parse(in);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return doc;
    }

    public Node selectSingleNode(String express, Object source) {

        Node result = null;
        XPathFactory xpathFactory = XPathFactory.newInstance();
        XPath xpath = xpathFactory.newXPath();
        try {
            result = (Node) xpath
                    .evaluate(express, source, XPathConstants.NODE);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        return result;
    }

    public NodeList selectNodes(String express, Object source) {
        NodeList result = null;
        XPathFactory xpathFactory = XPathFactory.newInstance();
        XPath xpath = xpathFactory.newXPath();
        try {
            result = (NodeList) xpath.evaluate(express, source,
                    XPathConstants.NODESET);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void saveXml(String fileName, Document doc) {

        TransformerFactory transFactory = TransformerFactory.newInstance();

        try {
            Transformer transformer = transFactory.newTransformer();
            transformer.setOutputProperty("indent", "yes");
            DOMSource source = new DOMSource();
            source.setNode(doc);
            StreamResult result = new StreamResult();
            result.setOutputStream(new FileOutputStream(fileName));
            transformer.transform(source, result);

        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public String readPropertie(String filePath, String key) {
        // ��ȡ���·��
        filePath = BuilderServiceAndImpl.class.getResource("/" + filePath)
                .toString();
        // �ص�·���ġ�file:��ǰ׺
        filePath = filePath.substring(6);
        Properties props = new Properties();
        try {
            InputStream in = new BufferedInputStream(new FileInputStream(
                    filePath));
            props.load(in);
            in.close();
            String value = props.getProperty(key);
            return value;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public class IgnoreDTDEntityResolver implements EntityResolver {
        public InputSource resolveEntity(String arg0, String arg1)
                throws SAXException, IOException {
            return new InputSource(new ByteArrayInputStream(
                    "<?xml version='1.0' encoding='UTF-8'?>".getBytes()));
        }
    }

}