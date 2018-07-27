package com.pere.fileUtil;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadProperties {
    String filepath1;
    public static Properties returnProperty(String filepath1) {
        Properties prop = new Properties();
        InputStream input = null;
        String filepath = filepath1;
        try {
            input = new FileInputStream(filepath);
            // load a properties file
            prop.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return prop;
    }
    public static Properties returnProperties() {
        //new ReadProperties();
        Properties pro = ReadProperties
                .returnProperty("gstdata.properties");
        return pro;
    }
}
