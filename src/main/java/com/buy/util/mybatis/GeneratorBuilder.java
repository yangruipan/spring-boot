package com.buy.util.mybatis;


import com.buy.util.mybatis.manual.BuilderXmlMapping;
import com.buy.util.mybatis.plugin.EclipseShellCallback;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.api.ProgressCallback;
import org.mybatis.generator.api.VerboseProgressCallback;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;

import java.io.File;
import java.util.*;

public class GeneratorBuilder {

    public static void main(String[] args) throws Exception {

        Scanner scan = new Scanner(System.in);
        System.out.println("Are your sure run this ? ");
        System.out.println("Please enter the [yes] to continue [exit] quit .");
        String key = null;
//        while (true) {
//            key = scan.nextLine();
//            if (key.equals("yes")) {
//                break;
//            } else if (key.equals("exit")) {
//                System.exit(0);
//            }
//        }

        /*目标路径*/
        String classesPath = new File(GeneratorBuilder.class.getResource("").getPath()).toString();
        /*配置文件目标路径*/
        String targetXmlPath = classesPath + "\\generatorConfig.xml";
        /*初始文件对象*/
        File targetXmlFile = new File(targetXmlPath);
        if (targetXmlFile.exists()) {
            Map arguments = new HashMap<String, Object>();
            arguments.put("-configfile", targetXmlFile.getAbsolutePath());
            ConfigurationParser cp = new ConfigurationParser(new ArrayList<String>());
            Configuration config = cp.parseConfiguration(targetXmlFile);
            EclipseShellCallback shellCallback = new EclipseShellCallback(arguments.containsKey("-overwrite"));
            MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, shellCallback, new ArrayList<String>());
            ProgressCallback progressCallback = arguments.containsKey("-verbose") ?
                    new VerboseProgressCallback() : null;
            myBatisGenerator.generate(progressCallback, new HashSet(), new HashSet());
            BuilderXmlMapping bud = new BuilderXmlMapping();
            bud.render();
            System.out.println("Builder api successfully !");
        }
    }
}