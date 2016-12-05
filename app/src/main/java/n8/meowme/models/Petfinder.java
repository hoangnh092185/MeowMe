package n8.meowme.models;

import android.util.Log;

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

    public Petfinder(){}

    public Petfinder (String name, String lastUpdate, String age, String imageUrl, String website, String phoneNumber){
        this.name = name;
        this.lastUpdate = lastUpdate;
        this.age = age;
//        this.breed = breed;
        this.imageUrl = imageUrl;
        this.website = website;
        this.phoneNumber = phoneNumber;
//        imageUrl = getLargeImageUrl(imageUrl);
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
//    public String getBreed(){
//        return mBreed;
//    }
    public String getImageUrl(){
        return imageUrl;
    }
    public String getWebsite(){ return website;}
    public String getPhoneNumber(){return phoneNumber;}

//    public String getLargeImageUrl(String ImageUrl) {
////        String largeImageUrl = imageUrl.substring(0, imageUrl.length() -6).concat("o.jpg");
////        String largeImageUrl = imageUrl;
//        return imageUrl;
//    }
}
