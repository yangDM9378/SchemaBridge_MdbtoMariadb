import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigUtil {
    private static final String CONFIG_FILE = "./mdbtomaria.config";
    private static Properties prop = new Properties();

    static {
        try (FileInputStream fis = new FileInputStream(CONFIG_FILE)) {
            prop.load(fis);
        } catch (IOException e) {
            System.err.println("⚠ 설정 파일 읽기 실패: " + CONFIG_FILE);
            e.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        return prop.getProperty(key);
    }

    public static String getMdbPath() {
        return prop.getProperty("mdb.path");
    }

    public static String getMariadbUrl() {
        return prop.getProperty("mariadb.url");
    }

    public static String getMariadbUser() {
        return prop.getProperty("mariadb.user");
    }

    public static String getMariadbPassword() {
        return prop.getProperty("mariadb.password");
    }
}