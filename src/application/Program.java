package application;


import db.DB;
import db.DbIntegrityException;

import javax.xml.transform.Result;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Program {
    // ('sirio123', 'Sirio', 'ThirdParty', 'sirio@gmail.com', '2015-12-20 10:01:00', '2015-12-20 10:01:00')
    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Connection conn = null;
        PreparedStatement st = null;

        try {
            conn = DB.getConnection();
            st = conn.prepareStatement(
                    "DELETE FROM user "
                    + "WHERE Id = ?");
            st.setString(1, "sirio45678");

            int affectedRows = st.executeUpdate();
            System.out.println("Done! " + affectedRows);

        } catch (SQLException e) {
            throw new DbIntegrityException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeConnection();
        }
    }
}