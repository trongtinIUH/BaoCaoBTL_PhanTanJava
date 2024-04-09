package connectDB;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
    public static Connection con = null;
    private static final ConnectDB instance = new ConnectDB();

    public static ConnectDB getInstance() throws SQLException {
        return instance;
    }

    public void connect() throws SQLException {
        String url = "jdbc:sqlserver://localhost:1433;databasename=BaiTapLonPTUD_NHOM4";
        con = DriverManager.getConnection(url, "sa", "sapassword");
    }

    public void connect(String user, String password) throws SQLException {
        String url = "jdbc:sqlserver://localhost:1433;databasename=BaiTapLonPTUD_NHOM4";
        con = DriverManager.getConnection(url, user, password);
    }

    public void disconnect() {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static Connection getConnection() {
        return con;
    }
}