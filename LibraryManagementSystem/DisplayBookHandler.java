import java.io.OutputStream;
import java.io.OutputStreamWriter;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.util.ArrayList;
import java.sql.SQLException;
import java.io.BufferedWriter;
import java.io.IOException;

public class DisplayBookHandler implements HttpHandler{
    public void handle(HttpExchange he) throws IOException {

        he.sendResponseHeaders(200,0);
        BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(he.getResponseBody() ));

        BookDAO books = new BookDAO();
        ArrayList<Book> allBooks = books.selectAllBooks();

        out.write(
                "<html>" +
                        "<head> <title>Library Management System</title> "+
                        "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">" +
                        "</head>" +
                        "<body>" +
                        "<div class=\"container\">" +
                        "<h1> Collectors Book</h1>"+
                        "<table class=\"table\">" +
                        "<thead>" +
                        "  <tr>" +
                        "     <th>ID</th>" +
                        "    <th>Title</th>" +
                        "    <th>Author</th>" +
                        "    <th>Year</th>" +
                        "    <th>Edition</th>" +
                        "    <th>Publisher</th>" +
                        "    <th>ISBN</th>" +
                        "    <th>Cover</th>" +
                        "    <th>Condition</th>" +
                        "    <th>Price</th>" +
                        "    <th>Notes</th>" +
                        "    <th>isBorrowed</th>" +
                        "    <th>Delete</th" +
                        "    <th>Borrow</th" +
                        "    <th>Return</th" +
                        "  </tr>" +
                        "</thead>" +
                        "<tbody>");

        for (Book d : allBooks){
            out.write(
                    "  <tr>"       +
                            "    <td>"+ d.getBookId() + "</td>" +
                            "    <td>"+ d.getTitle() + "</td>" +
                            "    <td>"+ d.getAuthor() + "</td>" +
                            "    <td>"+ d.getYear() + "</td>" +
                            "    <td>"+ d.getEdition() + "</td>" +
                            "    <td>"+ d.getPublisher() + "</td>" +
                            "    <td>"+ d.getIsbn() + "</td>" +
                            "    <td>"+ d.getCover() + "</td>" +
                            "    <td>"+ d.getCondition() + "</td>" +
                            "    <td>"+ d.getPrice() + "</td>" +
                            "    <td>"+ d.getNotes() + "</td>" +
                            "    <td>"+ d.getIsBorrowed() + "</td>" +
                            "    <td> <a href=\"/delete?id=" + d.getBookId() + "\"> delete </a> </td>" +
                            "    <td> <a href=\"/borrow?id=" + d.getBookId() + "\"> borrow </a> </td>" +
                            "    <td> <a href=\"/return?id=" + d.getBookId() + "\"> return </a> </td>" +
                            "  </tr>"
            );
        }
        out.write(
                "</tbody>" +
                        "</table>" +
                        "<a href=\"/\">Back to List </a>"+
                        "</div>" +

                        "</body>" +
                        "</html>");
        out.close();

    }

}