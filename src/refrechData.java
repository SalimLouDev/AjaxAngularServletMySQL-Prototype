import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

public class refrechData extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)

            throws ServletException, IOException
    {
        try {

            // Initialize the database
            Connection con = DatabaseConnection.initializeDatabase();

            PreparedStatement st = con.prepareStatement("select * from mails ");

            ResultSet rs=st.executeQuery();

            PrintWriter out = response.getWriter();
            response.setContentType("application/json");

            JSONObject jsonObject = new JSONObject();

            //creating a json array
            JSONArray array = new JSONArray();

            while (rs.next()) {

                JSONObject record = new JSONObject();

                record.put("expediteur", rs.getString("expediteur"));

                record.put("destinatair", rs.getString("destinatair"));

                record.put("sujet", rs.getString("sujet"));

                record.put("objet", rs.getString("objet"));

                record.put("mail", rs.getString("mail"));

                array.add(record);
            }
            jsonObject.put("Emails_data", array);
            out.print(jsonObject.toJSONString());
            out.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}