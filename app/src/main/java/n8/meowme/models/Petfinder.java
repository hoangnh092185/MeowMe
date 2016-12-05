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

    public Petfinder(){}

    public Petfinder (String name, String lastUpdate, String age, String imageUrl){
        this.name = name;
        this.lastUpdate = lastUpdate;
        this.age = age;
//        this.breed = breed;
        this.imageUrl = getLargeImageUrl(imageUrl);
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

    public String getLargeImageUrl(String ImageUrl) {
        String largeImageUrl = imageUrl.substring(0, imageUrl.length() -6).concat("o.jpg");
        return largeImageUrl;
    }
}
