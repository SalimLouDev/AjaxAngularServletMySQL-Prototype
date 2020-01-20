import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.*;
import java.io.BufferedReader;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.util.logging.Logger;
import org.json.simple.parser.*;

public class delete extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {

        String str = null;
        StringBuffer sb = null;
        org.json.JSONObject jObj = null;
        BufferedReader br = null;
        try
        {

            // Initialize the database
            Connection con = DatabaseConnection.initializeDatabase();


            br = request.getReader();
            sb = new StringBuffer();

            while ((str = br.readLine()) != null) {
                sb.append(str);
            }
            jObj = new org.json.JSONObject(sb.toString());

            String expediteur = jObj.getString("exp");

            String sql="";

                sql = "delete from mails where expediteur = '" + expediteur +"'";

            PreparedStatement st = con.prepareStatement(sql);

            // Execute the insert command using executeUpdate()
            // to make changes in database
            st.executeUpdate();

            // Close all the connections
            st.close();
            con.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}