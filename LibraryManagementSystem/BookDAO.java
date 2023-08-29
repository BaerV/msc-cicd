


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;



public class BookDAO {

    public BookDAO() {
    }
    public boolean addBook (Book book) {
//Insert the book object into the book table of the database
        Connection connection = null;
        PreparedStatement statement = null;//sql prepared statement
        try {

             connection = JDBC.getConnection();

            String sql = "insert into book values(?,?,?,?,?,?,?,?,?,?,?,?)";
            statement = connection.prepareStatement (sql);//sql prepared statement
            statement.setInt(1, book.getBookId());
            statement.setString(2, book.getTitle());
            statement.setString(3, book.getAuthor());
            statement.setInt(4, book.getYear());
            statement.setInt(5, book.getEdition());
            statement.setString(6, book.getPublisher());
            statement.setString(7, book.getIsbn());
            statement.setString(8, book.getCover());
            statement.setString(9, book.getCondition());
            statement.setInt(10, book.getPrice());
            statement.setString(11, book.getNotes());
            statement.setInt(12, book.getIsBorrowed());

            int ret = statement.executeUpdate ();
            if (ret != 1) {

                return false;
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace ();
        } finally {
            JDBC.close(connection, statement, null);
        }
        return false;
    }
    public void insertBook(Book book) throws SQLException {
        String insert = "insert into books values (?,?,?,?,?,?,?,?,?,?,?,?);";

        try (Connection connection = JDBC.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insert)) {//sql prepared statement
            preparedStatement.setInt(1, book.getBookId());
            preparedStatement.setString(2, book.getTitle());
            preparedStatement.setString(3, book.getAuthor());
            preparedStatement.setInt(4, book.getYear());
            preparedStatement.setInt(5, book.getEdition());
            preparedStatement.setString(6, book.getPublisher());
            preparedStatement.setString(7, book.getIsbn());
            preparedStatement.setString(8, book.getCover());
            preparedStatement.setString(9, book.getCondition());
            preparedStatement.setInt(10, book.getPrice());
            preparedStatement.setString(11, book.getNotes());
            preparedStatement.setInt(12, book.getIsBorrowed());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            JDBC.printSQLException(exception);
        }
    }
    public ArrayList<Book> selectAllBooks() {
        ArrayList<Book> books = new ArrayList<> ();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = JDBC.getConnection ();

            String sql = "select * from books";
            statement = connection.prepareStatement (sql);//sql prepared statement

            resultSet = statement.executeQuery ();

            while (resultSet.next ()) {

                int bookId = resultSet.getInt("Id");
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                int year = resultSet.getInt("year");
                int edition = resultSet.getInt("edition");
                String publisher = resultSet.getString("publisher");
                String isbn = resultSet.getString("isbn");
                String cover = resultSet.getString("cover");
                String condition = resultSet.getString("condition");
                int price = resultSet.getInt("price");
                String notes = resultSet.getString("notes");
                int isBorrowed = resultSet.getInt("isBorrowed");
                books.add(new Book(bookId,title,author,year,edition,publisher,isbn,cover,condition,price,notes,isBorrowed));
            }
        } catch (SQLException e) {
            e.printStackTrace ();
        } finally {
            JDBC.close (connection, statement, resultSet);
        }
        return books;
    }
    public boolean deleteBook(int bookId) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            //1. Establish link
            connection = JDBC.getConnection ();
            //2. Assemble SQL
            String sql = "delete from books where id= ?";
            statement = connection.prepareStatement (sql);
            statement.setInt (1, bookId);
            //3. Execute SQL
            int ret = statement.executeUpdate ();
            if (ret != 1) {
                return false;
            }
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace ();
        } finally {
            JDBC.close (connection, statement, null);
        }
        return false;
    }
    public boolean borrowBook (int bookId) {
        Connection connection = null;
        PreparedStatement statement1 = null;
        PreparedStatement statement2 = null;
        ResultSet resultSet = null;
        try {
            String sql = "select * from books where id= ?";
            statement1 = connection.prepareStatement (sql);
            statement1.setInt (1, bookId);

            resultSet = statement1.executeQuery ();

            if (resultSet.next ()) {

                boolean isBorrowed = (resultSet.getInt ("isBorrowed") == 1);
                if (isBorrowed) {
                    System.out.println ("Book with ID = " + bookId + " has been lent out! It can't be borrowed.");
                    return false;
                }
            } else {

                System.out.println ("Book with ID" + bookId + "not found");
                return false;
            }
            sql = "update books set isBorrowed = 1 where Id = ?";
            statement2 = connection.prepareStatement (sql);
            statement2.setInt (1, bookId);
            System.out.println("Book with ID: "+bookId + " is now borrowed. Thank you");
            int ret = statement2.executeUpdate ();
            if (ret != 1) {
                return false;

            }
            return true;
        }catch (SQLException e){
            e.printStackTrace ();
        }finally {
            if (resultSet != null) {
                try {
                    resultSet.close ();
                } catch (SQLException e) {
                    e.printStackTrace ();
                }
            }
            if (statement2 != null) {
                try {
                    statement2.close ();
                } catch (SQLException e) {
                    e.printStackTrace ();
                }
            }
            if (statement1 != null) {
                try {
                    statement1.close ();
                } catch (SQLException e) {
                    e.printStackTrace ();
                }
            }
        }
        return  false;
    }
    public boolean returnBook(int bookId) throws SQLException {
        Connection connection = null;
        PreparedStatement statement1 = null;
        PreparedStatement statement2 = null;
        ResultSet resultSet = null;
        try {
            connection = JDBC.getConnection();
            String sql = "select * from books where id = ?";
            statement1 = connection.prepareStatement(sql);
            statement1.setInt (1, bookId);
            resultSet = statement1.executeQuery();
            if (resultSet.next()) {

                boolean isBorrowed = (resultSet.getInt ("isBorrowed") == 1);
                if (!isBorrowed) {
                    System.out.println ("Book with ID = " + bookId + " is yet to be borrowed. Cannot be returned");
                    return false;
                }
            }else {

                System.out.println ("Book with ID" + bookId + " not found");
            }

            sql = "update books set isBorrowed = 0 where id = ?";
            statement2=connection.prepareStatement(sql);
            statement2.setInt (1, bookId);
            int ret=statement2.executeUpdate();
            System.out.println("Book with ID: "+bookId + " has been returned. Thank you");
            if(ret!=1){
                return  false;
            }
            return true;

        }catch (SQLException e){
            e.printStackTrace ();
        }finally {
            if (resultSet != null) {
                try {
                    resultSet.close ();
                } catch (SQLException e) {
                    e.printStackTrace ();
                }
            }
            if (statement2 != null) {
                try {
                    statement2.close ();
                } catch (SQLException e) {
                    e.printStackTrace ();
                }
            }
            if (statement1 != null) {
                try {
                    statement1.close ();
                } catch (SQLException e) {
                    e.printStackTrace ();
                }
            }
        }
        return  false;
    }

    public boolean updateBook(Book book) throws SQLException {
        boolean rowUpdated;
     Connection connection = null;
     PreparedStatement statement = null;
        try {
              connection = JDBC.getConnection();
             //String sql = "update books set title = ? ,author =? , year = ? ,edition = ? , publisher =? ,isbn = ? , cover = ? ,condition = ? , price = ? , notes=?, isBorrowed =? where id = ? ;";
            String sql = "update books set price = ? where id = ? ;";
             statement = connection.prepareStatement(sql);

        {
            System.out.println("Book updated :"+statement);

            statement.setInt(1, book.getPrice());

            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }catch (SQLException throwables) {
            throwables.printStackTrace ();
        } finally {
            JDBC.close (connection, statement, null);
        }
        return false;
    }
}