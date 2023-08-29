import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;
import java.io.IOException;

public class Main {
    static final private int PORT = 8080;
    public static void main(String[] args) throws IOException {
        System.out.println("Hello world!");

        HttpServer server = HttpServer.create(new InetSocketAddress(PORT),0);
        server.createContext("/", new HomepageHandler() );
        //server.createContext("/login", new LoginHandler() );
        //server.createContext("/register", new RegisterHandler() );//add user => remove to make it easier
        //server.createContext("/", new LandingHandler() );

        server.createContext("/allBooks", new DisplayBookHandler() );
       // server.createContext("/allUsers", new DisplayUserHandler() );

       server.createContext("/delete", new DeleteBookHandler() );
        //server.createContext("/delete1", new DeleteUserHandler() );

      //  server.createContext("/addBook", new AddBookHandler() );
        //server.createContext("/addUser", new AddUserHandler() );

      //  server.createContext("/updateBook", new UpdateBookHandler());//similar to add
       // server.createContext("/updateUser", new UpdateUserHandler());

       // server.createContext("/borrow", new BorrowBookHandler());//similar to add
      //  server.createContext("/return", new ReturnBookHandler());


      //  server.createContext("/processAddBook", new ProcessAddBookHandler());
       // server.createContext("/processAddUser", new ProcessAddUserHandler());
       // server.createContext("/processUpdateBook", new ProcessAddBookHandler());
      //  server.createContext("/processUpdateUser", new ProcessAddUserHandler());
        //server.createContext("/processLogin", new ProcessLoginHandler());
        server.setExecutor(null);
        server.start();
        System.out.println("The server is listening on port " + PORT);
    }
}
