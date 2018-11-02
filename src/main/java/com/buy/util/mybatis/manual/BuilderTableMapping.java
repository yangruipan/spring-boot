package com.buy.util.mybatis.manual;


import com.buy.util.mybatis.GeneratorBuilder;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.*;
import java.util.Map.Entry;

public class BuilderTableMapping {

    List<String> _tables = null;
    static Connection conn = null;
    static String _schema = null;
    static String projectFilePath = "generatorConfig.properties";

    public static Connection getConnectionByJDBC() {
        String driver = readPropertie(projectFilePath, "driverClass");
        String uid = readPropertie(projectFilePath, "userId");
        String pid = readPropertie(projectFilePath, "password");
        String conurl = readPropertie(projectFilePath, "connectionURL");
        splitSchema(conurl);
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            conn = DriverManager.getConnection(conurl, uid, pid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void splitSchema(String connectionUrl) {
        int star = connectionUrl.indexOf('/');
        star = connectionUrl.indexOf('/', star + 1);
        star = connectionUrl.indexOf('/', star + 1);
        int end = connectionUrl.indexOf('?');

        _schema = connectionUrl.substring(star + 1, end);
    }

    public void queryTables() {

        _tables = new ArrayList<String>();
        String sql = "show tables";
        getConnectionByJDBC();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String n = rs.getString(1);
                _tables.add(n);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public HashMap<String, String> queryFileds(String tableName) {
        HashMap<String, String> hm = new HashMap<String, String>();

        BuilderTableMapping.getConnectionByJDBC();
        String sql = "select column_name,data_type from information_schema.columns  where table_schema='" + BuilderTableMapping._schema + "' and table_name='" + tableName + "'";

        try {
            Statement stmt = BuilderTableMapping.conn.createStatement();
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
                if (BuilderTableMapping.conn != null)
                    BuilderTableMapping.conn.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
        return hm;
    }


    public void printXml() {
        System.out.print("<!--copy the xml into generatorConfig.xml-->\n");
        for (int i = 0; i < _tables.size(); i++) {
            String n = _tables.get(i);
            String d = this.getLongTextDefine(n);
            System.out.print("<table schema=\"" + _schema + "\" tableName=\"" + n + "\"\n");
            System.out.print(" enableUpdateByExample=\"false\" enableDeleteByExample=\"false\" selectByExampleQueryId=\"false\" >\n");
            System.out.print(d);
            System.out.print("</table>\n");
        }
    }

    public String getLongTextDefine(String tableName) {
        String def = "";
        HashMap<String, String> _fileds = this.queryFileds(tableName);

        if (_fileds != null) {
            Iterator<Entry<String, String>> it = _fileds.entrySet().iterator();
            while (it.hasNext()) {

                Entry<String, String> entry = (Entry<String, String>) it.next();
                Object key = entry.getKey();
                Object value = entry.getValue();

                if (value.equals("text") || value.equals("longtext") || value.equals("mediumtext")) {
                    def += "<columnOverride column=\"" + key.toString() + "\" javaType=\"java.lang.String\" jdbcType=\"VARCHAR\" />\n";
                }
            }
        }
        return def;
    }


    public static String readPropertie(String filePath, String key) {
        filePath = GeneratorBuilder.class.getResource("" + filePath)
                .toString();
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

}