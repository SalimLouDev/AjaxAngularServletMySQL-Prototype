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

public class myservlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest req,
                          HttpServletResponse resp)
            throws ServletException, IOException
    {
        String str = null;
        StringBuffer sb = null;
        org.json.JSONObject jObj = null;
        BufferedReader br = null;

        try {
            Connection con = DatabaseConnection.initializeDatabase();

            br = req.getReader();
            sb = new StringBuffer();

            while ((str = br.readLine()) != null) {
                sb.append(str);
            }
            jObj = new org.json.JSONObject(sb.toString());
            String user_name = jObj.getString("username");

           // String user_name = req.getParameter("username");

           /* StringBuilder buffer = new StringBuilder();
            BufferedReader reader = req.getReader();
            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            String data = buffer.toString();*/

            PreparedStatement st = con.prepareStatement("insert into t values ('"+user_name+"')");

            st.executeUpdate();

            // Close all the connections
            st.close();
            con.close();
            /**** Preparing The Output Response ****/
            resp.setContentType("text/html");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write("Welcome " + user_name + " !!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
