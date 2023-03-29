package chx;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

class JDBCUtil{
    public static Connection getConnection() {
        return null;
    }
}

public class JDBC123312 {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtil.getConnection();
            String sql = "select password from userinfo where username like ?";
            ps = conn.prepareStatement(sql);

            ps.setString(1,"_o%");
            ps.execute(sql);

            while (rs.next()) {
                String pwd = rs.getString(1);
                System.out.println(pwd);
            }
        }catch (Exception e){

        }

    }
}
