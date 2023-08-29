import java.sql.*;


public class AddColumnDAO {

    public static void main(String[] args) throws SQLException {
        Book book = new Book();
        Connection con = DriverManager.getConnection("jdbc:sqlite:books.sqlite");



        //String alter = "alter table books add isBorrowed integer;";
        //String alter = "alter table books drop isBorrowed;";
        String insert2 = "insert into books values (?,?,?,?,?,?,?,?,?,?,?,?);";
        //String delete = "delete from books where id =11";
        // PreparedStatement preparedStatement =  con.prepareStatement(alter);
        PreparedStatement preparedStatement2 =  con.prepareStatement(insert2);
        //PreparedStatement preparedStatement3 =  con.prepareStatement(delete);

        //preparedStatement2.setInt(1,11 );
        preparedStatement2.setString(2,"To Kill a Mockingbird" );
        preparedStatement2.setString(3, "Harper Lee");
        preparedStatement2.setInt(4, 1960);
        preparedStatement2.setInt(5, 2);
        preparedStatement2.setString(6, "Harper Modern");
        preparedStatement2.setString(7, "null");
        preparedStatement2.setString(8, "Softback");
        preparedStatement2.setString(9, "Not Good");
        preparedStatement2.setInt(10, 0);
        preparedStatement2.setString(11, "Coffee Stains");
        preparedStatement2.setInt(12, 0);
        //System.out.println(preparedStatement2);
        preparedStatement2.executeUpdate();
        preparedStatement2.close();
        //preparedStatement.executeUpdate();
        //preparedStatement.close();
        con.close();
        System.out.println("Query executed...");

    }

    //You should put a unique constraint on this line

    public boolean borrowBook (int bookId) {
        Connection connection = null;
        PreparedStatement statement1 = null;
        PreparedStatement statement2 = null;
        ResultSet resultSet = null;
        try {

            connection = JDBC.getConnection ();

            String sql = "select * from books where id= ?";
            statement1 = connection.prepareStatement (sql);
            statement1.setInt (1, bookId);

            resultSet = statement1.executeQuery ();

            if (resultSet.next ()) {

                boolean isBorrowed = (resultSet.getInt ("isBorrowed") == 1);
                if (isBorrowed) {
                    System.out.println ("Book has been lent out! It can't be borrowed. Book Id = " + bookId);
                    return false;
                }
            } else {

                System.out.println ("Book not found.");
                return false;
            }

            sql = "update book set isBorrowed =1 where Id = ?";
            statement2 = connection.prepareStatement (sql);
            statement2.setInt (1, bookId);

            int ret = statement2.executeUpdate ();
            if (ret != 1) {
                return false;
            }
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace ();
        } finally {
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
        return false;
    }



    public boolean returnBook (int bookId) throws SQLException {
        Connection connection = null;
        PreparedStatement statement1 = null;
        PreparedStatement statement2 = null;
        ResultSet resultSet = null;
        try {
            connection = JDBC.getConnection ();
            String sql = "select * from books where id = ?";
            statement1 = connection.prepareStatement (sql);
            statement1.setInt (1, bookId);
            resultSet = statement1.executeQuery ();
            if (resultSet.next ()) {

                boolean isBorrowed = (resultSet.getInt ("isBorrowed") == 1);
                if (!isBorrowed) {
                    System.out.println ("if the book is not lent, you don't have to return it. Book Id = " + bookId );
                    return false;
                } else {

                    System.out.println ("book does not exist Book Id =" + bookId);
                }

                sql = "update books set isBorrowed = 0 where id = ?";
                statement2=connection.prepareStatement (sql);
                statement2.setInt (1, bookId);
                int ret=statement2.executeUpdate ();
                if(ret!=1){
                    return  false;
                }
                return true;
            }
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
}