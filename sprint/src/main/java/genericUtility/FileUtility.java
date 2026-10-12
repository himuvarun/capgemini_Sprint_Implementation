package genericUtility;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FileUtility {
    public String getDataFromProperties(String key) throws IOException {
        FileInputStream file = new FileInputStream("src/main/resources/CommonData.properties");
    	Properties p = new Properties();
        p.load(file);
        String value = p.getProperty(key);
        return value;
    }
}
