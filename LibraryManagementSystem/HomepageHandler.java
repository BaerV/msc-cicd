import java.io.OutputStream;
import java.io.OutputStreamWriter;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.util.ArrayList;
import java.sql.SQLException;
import java.io.BufferedWriter;
import java.io.IOException;

public class HomepageHandler implements HttpHandler{
    public void handle(HttpExchange he) throws IOException {
    he.sendResponseHeaders(200,0);
    BufferedWriter out = new BufferedWriter(
            new OutputStreamWriter(he.getResponseBody() ));



    out.write(
            "<html>" +
                    "<head> <title>Library Management System</title> "+
                    "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">" +
                    "</head>" +
                    "<body>" +
                    "<div class=\"container\">" +

                    "<h5>Book Options</h5>"+
                    "<br>" +
                    "<a href=\"/allBooks\">Display Books</a> " +
                    "<br>" +
                    "<a href=\"/addBook\">Add Book</a> " +
                    "<br>" +
                    "<a href=\"/updateBook\">Update Book</a> " +
                    "<br>" +
                    "<br>" +
                    "<h5>User Options</h5>"+
                    "<br>" +
                    "<a href=\"/allUsers\">Display Users</a> " +
                    "<br>" +
                    "<a href=\"/addUser\">Add User</a> " +
                    "<br>" +
                    "<a href=\"/updateUser\">Update User</a> " +
                    "<br>" +
                    "<br>" +
                    "<a href=\"/landing\">Log Out</a>"+//href to  / landing page for log out
                    "</div>"+
                    "</div>" +
                    "</body>" +
                    "</html>");

    out.close();

}

}