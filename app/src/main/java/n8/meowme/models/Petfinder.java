package n8.meowme.models;

import org.parceler.Parcel;

/**
 * Created by Guest on 12/2/16.
 */

@Parcel
public class Petfinder {
    String name;
    String lastUpdate;
    String age;
//   String breed;
    String imageUrl;
    String website;
    String phoneNumber;
    String state;
    String city;
    String zip;
    String address;

    String petId;
    String shelterPetId;
    String sex;
    String description;

    public Petfinder(){}

    public Petfinder(String name, String lastUpdate, String age, String imageUrl, String website, String phoneNumber,String state, String city, String zip, String address, String petId, String shelterPetId, String sex, String description) {
        this.name = name;
        this.lastUpdate = lastUpdate;
        this.age = age;
        this.imageUrl = imageUrl;
        this.website = website;
        this.phoneNumber = phoneNumber;
        this.state = state;
        this.city = city;
        this.zip = zip;
        this.address = address;
        this.petId = petId;
        this.shelterPetId = shelterPetId;
        this.sex = sex;
        this.description = description;
    }

    public String getName(){
        return name;
    }
    public String getLastUpdate(){
        return lastUpdate;
    }
    public String getAge(){
        return age;
    }
    public String getImageUrl(){
        return imageUrl;
    }
    public String getWebsite(){ return website;}
    public String getPhoneNumber(){return phoneNumber;}

    public String getState(){ return state; }

    public String getCity() {
        return city;
    }

    public String getZip() {
        return zip;
    }

    public String getAddress() {
        return address;
    }

    public String getPetId() {
        return petId;
    }

    public String getShelterPetId() {
        return shelterPetId;
    }

    public String getSex() {
        return sex;
    }

    public String getDescription() {
        return description;
    }

}
