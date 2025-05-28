import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MdbToMariaMigrator {
    private static final String MDB_PATH = ConfigUtil.getMdbPath();
    private static final String MARIADB_URL = ConfigUtil.getMariadbUrl();
    private static final String MARIADB_USER = ConfigUtil.getMariadbUser();
    private static final String MARIADB_PASSWORD = ConfigUtil.getMariadbPassword();

    public static void main(String[] args) {
        Connection mdbConn = null;
        Connection mariaConn = null;

        try {
            // mdb 연결
            String mdb_url = "jdbc:ucanaccess://" + MDB_PATH;
            mdbConn = DriverManager.getConnection(mdb_url);
            System.out.println("Mdb connection successful");

            // mariadb 연결
            mariaConn = DriverManager.getConneciton(MARIADB_URL,MARIADB_USER, MARIADB_PASSWORD);
            System.out.println("Mariadb connection successful");

            // MDB의 테이블 가져오기
            DatabaseMetaData metaData = mdbConn.getMetaData();
            ResultSet tables = metaData.getTables(null, null, "%", new String[]{"TABLE"});
            List<String> tableNames = new ArrayList<>();

            while (tables.next()) {
                String tableName = tables.getString("TABLE_NAME");
                tableNames.add(tableName);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (mdbConn != null) mdbConn.close();
                if (mariaConn != null) mariaConn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}