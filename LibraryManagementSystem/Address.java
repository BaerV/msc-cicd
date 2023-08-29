public class Address {
    String house;
    String addressLine1;
    String addressLine2;
    public String county;
    String postCode;

    public Address(String house, String addressLine1, String addressLine2, String county, String postCode) {
        this.house = house;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.county = county;
        this.postCode = postCode;
    }
    public Address() {

    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getCountry() {
        return county;
    }

    public void setCountry(String country) {
        this.county = country;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }


    @Override
    public String toString() {
        return String.format("House: %s\nAddress Line 1: %s\nAddress Line 2: %s\nCounty: %s\nPostCode: %s", house, addressLine1, addressLine2, county, postCode);

    }
}

