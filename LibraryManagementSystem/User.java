public class User {

    private int id;
    private String username;
    private String password;
    private int user_type;


    public User() {
        super();
    }

    public User(int id, String username, String password, int user_type) {
    }

    public User(int id, String username, String password, int user_type, Customer customer, Address address) {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUser_type() {
        return this.user_type;
    }

    public void setUser_type(int user_type) {
        this.user_type = user_type;
    }

    public String toString() {
        return String.format("ID: %s\nUsername: %s\nPassword: %d\nUser_type: %d",id,username,password,user_type);

    }

}


