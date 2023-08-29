public class Customer {

    public String firstName;
    public String lastName;
    public Address address;



    public Customer(String firstName, String secondName, Address address) {
        this.firstName = firstName;
        this.lastName = secondName;
        this.address = address;

    }
    public Customer(Address address){
        this.address = address;
    }

    public Customer() {

    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return lastName;
    }

    public void setSecondName(String lastName) {
        this.lastName = lastName;
    }



    @Override
    public String toString() {
        return String.format("First Name: %s\nLast Name: %s\n %s", firstName, lastName,address);
    }
}