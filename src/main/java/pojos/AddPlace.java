package pojos;

import java.util.List;

public class AddPlace {
    public int accuracy;
    public String name;
    public String phone_number;
    public String address;
    public String website;
    public String language;
    public Location location;
    public List<String> types;

    public void setAccuracy(int accuracy) {
        this.accuracy = accuracy;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }
}
