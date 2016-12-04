package n8.meowme.models;

/**
 * Created by Guest on 12/2/16.
 */
public class Petfinder {
    private String mName;
    private String mLastUpdate;
    private String mAge;
    private String mBreed;
    private String mPhotoUrl;


    public Petfinder (String name, String lastUpdate, String age, String breed, String photoUrl){
        this.mName = name;
        this.mLastUpdate = lastUpdate;
        this.mAge = age;
        this.mBreed = breed;
        this.mPhotoUrl = photoUrl;
    }

    public String getName(){
        return mName;
    }
    public String getLastUpdate(){
        return mLastUpdate;
    }
    public String getAge(){
        return mAge;
    }
    public String getBreed(){
        return mBreed;
    }
    public String getPhotoUrl(){
        return mPhotoUrl;
    }
}
