package com.buy.util.mybatis;

import com.buy.util.mybatis.manual.BuilderTableMapping;
import org.xml.sax.SAXException;

public class GeneratorBuilderXMLMapping {

    /**
     * @param args
     * @throws SAXException
     */
    public static void main(String[] args) {
        BuilderTableMapping t = new BuilderTableMapping();
        t.queryTables();
        t.printXml();
    }
}
