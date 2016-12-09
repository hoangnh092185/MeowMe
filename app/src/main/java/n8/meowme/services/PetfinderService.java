package n8.meowme.services;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import n8.meowme.Constants;
import n8.meowme.models.Petfinder;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Guest on 12/2/16.
 */
public class PetfinderService {
    public static final String TAG = PetfinderService.class.getSimpleName();


    public void findPets(String location, Callback callback){
        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.PETFINDER_PET_FIND_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.PETFINDER_KEY_PARAMETER, Constants.PETFINDER_KEY);
        urlBuilder.addQueryParameter(Constants.PETFINDER_LOCATION_PARAMETER, location);
        urlBuilder.addQueryParameter(Constants.PETFINDER_ANIMAL_PARAMETER, "cat");
        urlBuilder.addQueryParameter(Constants.PETFINDER_COUNT_PARAMETER, "10");
        urlBuilder.addQueryParameter(Constants.PETFINDER_FORMAT_PARAMETER, "json");

        String url = urlBuilder.build().toString();
        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public ArrayList<Petfinder> processResults(Response response) {
        ArrayList<Petfinder> petfinders = new ArrayList<>();

        try {
            if(response.isSuccessful()){
                String jsonData = response.body().string();
                JSONObject petfinderJSON = new JSONObject(jsonData);
                JSONObject petsJSON = petfinderJSON.getJSONObject("petfinder");
                JSONObject petJSON = petsJSON.getJSONObject("pets");
                JSONArray petArrayJSON = petJSON.getJSONArray("pet");
                for (int i=0; i<petArrayJSON.length(); i++){
                    JSONObject petInfoJSON = petArrayJSON.getJSONObject(i);
                    String name = petInfoJSON.getJSONObject("name").getString("$t");
                    String age =  petInfoJSON.getJSONObject("age").getString("$t");
                    String imageURL =  petInfoJSON.getJSONObject("media").getJSONObject("photos").getJSONArray("photo").getJSONObject(2).getString("$t");
                    String lastUpdate =  petInfoJSON.getJSONObject("lastUpdate").getString("$t");
                    String website = petInfoJSON.getJSONObject("contact").getJSONObject("email").getString("$t");
                    String phoneNumber = petInfoJSON.getJSONObject("contact").getJSONObject("phone").optString("$t", "N/A");
                    String state = petInfoJSON.getJSONObject("contact").getJSONObject("state").getString("$t");
                    String city = petInfoJSON.getJSONObject("contact").getJSONObject("city").getString("$t");
                    String zip = petInfoJSON.getJSONObject("contact").getJSONObject("zip").getString("$t");
                    String address = petInfoJSON.getJSONObject("contact").getJSONObject("address1").optString("$t", "N/A");
                    String petId = petInfoJSON.getJSONObject("id").getString("$t");
                    String shelterPetId = petInfoJSON.getJSONObject("shelterPetId").optString("$t", "N/A");
                    String sex = petInfoJSON.getJSONObject("sex").getString("$t");
                    String desprciption = petInfoJSON.getJSONObject("description").getString("$t");
//                      ArrayList<String> breed = new ArrayList<>();
//                    JSONArray breedJSON = petInfoJSON.getJSONObject("breeds").getJSONArray("breed");
//                    for (int j=0; j<breedJSON.length(); j++){
//                        breed.add(breedJSON.getJSONObject(j).getString("$t"));
//                    }

//                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
//                    SimpleDateFormat output = new SimpleDateFormat("yyyy-MM-dd");
//                    Date d = sdf.parse(dateFormat);
//                    String lastUpdate = sdf.parse(dateFormat).toString();


                    Petfinder petfinder = new Petfinder(name, lastUpdate, age, imageURL, website, phoneNumber, state, city, zip, address, petId, shelterPetId, sex, desprciption);
                    petfinders.add(petfinder);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return petfinders;
    }
}
