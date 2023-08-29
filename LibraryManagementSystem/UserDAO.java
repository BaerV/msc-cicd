import java.sql.*;
import java.util.ArrayList;

public class UserDAO {

public UserDAO(){}

 public boolean validate( String username, int user_type){
        boolean status = false;

        Connection connection = JDBC.getConnection ();
        try {
        String sql = "select * from users where username = ? and user_type =?";
        PreparedStatement ps;

            ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, String.valueOf(user_type));
            ResultSet rs = ps.executeQuery();
            status = rs.next();

        } catch (SQLException e) {

            e.printStackTrace();
        }
        return status;
    }
 
 public void checkLoginCredentials(String username, int user_type) throws SQLException{
        Connection connection=null;
        PreparedStatement statement=null;//sql prepared statement
        ResultSet resultSet=null;
        try {
            connection= JDBC.getConnection ();  
    //dbmsconnect dbmsconnect = new dbmsconnect("jdbc:sqlite:books.sqlite");
       // Connection con = dbmsconnect.getConnection();

        String sql = "select * from users where username = ? and user_type = ?;";
        PreparedStatement stmt = connection.prepareStatement(sql);//sql prepared statement

        stmt.setString(1, username);
        //stmt.setString(3, password);
        stmt.setInt(2, Integer.parseInt(String.valueOf(user_type)));
        ResultSet rs = stmt.executeQuery();

        /*if (rs.next() == false) {
            System.out.println("No such record found in the database");

        } else {
            dbmsconnect.closeConnection(connection, stmt);

        }*/
    } catch (SQLException e) {
        e.printStackTrace ();
    }

}
   /* public void checkLoginCredentials(String username, int user_type) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        dbmsconnect dbmsconnect = new dbmsconnect("jdbc:sqlite:books.sqlite");
        Connection con = dbmsconnect.getConnection();

        String sql = "select * from users where username = ? and user_type = ?;";
        PreparedStatement stmt = con.prepareStatement(sql);//sql prepared statement

        stmt.setString(1, username);
        //stmt.setString(3, password);
        stmt.setInt(2, Integer.parseInt(String.valueOf(user_type)));
        ResultSet rs = stmt.executeQuery();

        if (rs.next() == false) {
            System.out.println("No such record found in the database");

        } else {
            dbmsconnect.closeConnection(con, stmt);

        }
    }*/

    public void checkUserPrivilege(String username){
        Connection connection=null;
        PreparedStatement statement=null;//sql prepared statement
        ResultSet resultSet=null;
        try {
            connection= JDBC.getConnection ();

            String sql="select * from users where username= ?";
            statement=connection.prepareStatement(sql);//sql prepared statement
            statement.setString (1, username);
            resultSet=statement.executeQuery ();//sql prepared statement
            if(resultSet.next ()){

                boolean isAdmin =resultSet.getInt ("user_type")==1;
                boolean isCustomer=resultSet.getInt ("user_type")==0;
                boolean notAny = !isAdmin && !isCustomer;


                if(isAdmin){
                    System.out.println("This user belongs to Administration");
                }else  {
                    System.out.println("This user belongs to the Customer base");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace ();
        }

    }

    public ArrayList<User> getAllUsers() throws SQLException {
        System.out.println("Retrieving all users ...");
        Connection connection = null;
        PreparedStatement preparedStatement = null;//sql prepared statement
        ResultSet result = null;
        String query = "SELECT * FROM users;";
        ArrayList<User> users = new ArrayList<>();

        try {
            connection = JDBC.getConnection();
            preparedStatement = connection.prepareStatement(query);//sql prepared statement
            result = preparedStatement.executeQuery();//sql prepared statement
            while (result.next()) {

                int id = result.getInt("ID");
                String username = result.getString("Username");
                String password = result.getString("Password");
                int user_type = result.getInt("User_Type");

                users.add(new User(id,username,password,user_type));
            }
        } catch(Exception e) {
            System.out.println("get all users: "+e);
        } finally {
            if (result != null) {
                result.close();
            }
            if (preparedStatement != null) {//sql prepared statement
                preparedStatement.close();//sql prepared statement
            }
            if (connection != null) {
                connection.close();
            }
        }
        return users;
    }

    public User getUser(int id) throws SQLException {

        User temp = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;//sql prepared statement
        ResultSet result = null;

        String query = "SELECT * FROM users WHERE ID =" + id + ";";

        try {
            connection = JDBC.getConnection();
            preparedStatement = connection.prepareStatement(query);//sql prepared statement
            result = preparedStatement.executeQuery();//sql prepared statement

            while (result.next()) {


                 id = result.getInt("ID");
                String username = result.getString("Username");
                String password = result.getString("Password");
                int user_type = result.getInt("User_type");

                temp = new User(id,username,password,user_type);

            }
        } finally {
            if (result != null) {
                result.close();
            }
            if (preparedStatement != null) {//sql prepared statement
                preparedStatement.close();//sql prepared statement
            }
            if (connection != null) {
                connection.close();
            }
        }
        return temp;
    }

    public Boolean deleteUser(int id) throws SQLException {
        System.out.println("Deleting User");
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int result = 0;
        String query = "DELETE FROM users WHERE ID = " + id + ";";
        try {
            connection = JDBC.getConnection();
            preparedStatement = connection.prepareStatement(query);//sql prepared statement
            System.out.println(query);
            result = preparedStatement.executeUpdate();//sql prepared statement
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        if (result == 1) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean updateUser(User user) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;//sql prepared statement

        String query = "UPDATE users SET ID = '" + user.getId() + "'," + "Username = '"
                + user.getUsername() + "'," + "Password= '" + user.getPassword() + "'," + "User_Type= " + user.getUser_type() + " WHERE ID = " + user.getId()
                + ";";
System.out.println(query);
        try {
            connection = JDBC.getConnection();
            preparedStatement = connection.prepareStatement(query);//sql prepared statement
            
            preparedStatement.executeUpdate();//sql prepared statement

        } catch (SQLException e) {

            System.out.println(e.getMessage());
            return false;

        } finally {

            if (preparedStatement != null) {//sql prepared statement
                preparedStatement.close();//sql prepared statement
            }
            if (connection != null) {
                connection.close();
            }
        }
        return true;
    }

    public boolean addUser(User user) throws SQLException{
        Connection connection = null;
        PreparedStatement preparedStatement = null;//sql prepared statement
        System.out.println("addUser called");
        String add = "INSERT INTO users (ID, Username, Password,user_type ) VALUES ("+user.getId()+",'"+ user.getUsername()+"','"+
          user.getPassword()+"',"+
          user.getUser_type()+ ");";
        System.out.println("Query about to be Called: " + add);
        boolean ok = false;
      
        try {
            connection = JDBC.getConnection();
            preparedStatement = connection.prepareStatement(add);//sql prepared statement
            System.out.println(add);
            preparedStatement.executeUpdate();//sql prepared statement
            ok = true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (preparedStatement != null) {//sql prepared statement
                preparedStatement.close();//sql prepared statement
            }
            if (connection != null) {
                connection.close();
            }

        }
        return ok;
    }
}


