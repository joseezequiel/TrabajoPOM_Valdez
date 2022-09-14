package utilidades;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesDriven {
    private Properties prop;

    public String getProperty(String key){
        prop = new Properties();

        try{
            InputStream input = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\Setup.properties");
            prop.load(input);
        }catch (Exception e){
            System.out.println("No fue posible llamar al archivo properties.");
            e.printStackTrace();
        }

        return prop.getProperty(key);
    }
}
