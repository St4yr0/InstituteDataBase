package login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dbUtil.dbConnection;

public class LoginModel {
    private Connection con;

    public LoginModel() {
        try {
            this.con = dbConnection.getConnection();
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }

        if (!is_db_connected()) {
            System.exit(1);
        }
    }

    public boolean is_db_connected() {
        return this.con != null;
    }

    public boolean is_login(String username, String password, String division)throws Exception{
        PreparedStatement prep_st = null;
        ResultSet res_st = null;
        String sql = "SELECT * FROM login WHERE username = ? AND password = ? AND division = ?";

        try {
            prep_st = this.con.prepareStatement(sql);
            prep_st.setString(1, username);
            prep_st.setString(2, password);
            prep_st.setString(3, division);

            res_st = prep_st.executeQuery();

            return res_st.next();
        }
        catch (SQLException ex) {
            return false;
        }
        finally {
            prep_st.close();
            res_st.close();
        }
    }
}
