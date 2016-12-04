package n8.meowme.services;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
        urlBuilder.addQueryParameter(Constants.PETFINDER_COUNT_PARAMETER, "2");
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
//                    ArrayList<String> breed = new ArrayList<>();
//                    JSONArray breedJSON = petInfoJSON.getJSONObject("breeds").getJSONArray("breed");
//                    for (int j=0; j<breedJSON.length(); j++){
//                        breed.add(breedJSON.getJSONObject(j).getString("$t"));
//                    }
                    Log.d(TAG, imageURL);
                    Petfinder petfinder = new Petfinder(name, lastUpdate, age, imageURL);
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
