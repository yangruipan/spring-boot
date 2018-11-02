package com.buy.util.mybatis;

import com.buy.util.mybatis.manual.BuilderXmlMapping;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class GeneratorBuilderDelete {

    public static void main(String[] args) throws SAXException, IOException {

        String filenames = "u_cinema_hall";

        InputStream is = System.in;
        Scanner scan = new Scanner(is);
        System.out.println("Are your sure delete [" + filenames + "] ? ");
        System.out.println("Please enter the [yes] to continue [exit] quit .");
        String key = null;
        while (true) {
            key = scan.nextLine();
            if (key.equals("yes")) {
                break;
            } else if (key.equals("exit")) {
                System.exit(0);
            }
        }

        BuilderXmlMapping xml = new BuilderXmlMapping();
        xml.delete(filenames);

    }
}
