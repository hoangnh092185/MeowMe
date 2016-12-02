package n8.meowme;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

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

    public ArrayList<Petfinder> processResults(Response response){
        ArrayList<Petfinder> petfinders = new ArrayList<>();

        try {
            String jsonData = response.body().string();
            Log.v(TAG, "get response: "+jsonData);
            if(response.isSuccessful()){
                JSONObject petfinderJSON = new JSONObject(jsonData);
                JSONArray petsJSON = petfinderJSON.getJSONArray("petfinder");
//                JSONArray petArray = petfinderJSON.getJSONObject("petfinder").getJSONObject("pets").getJSONArray("pet");

//                Petfinder petfinder = new Petfinder(name , lastUpdate, age, breed, photoUrl);
//                petfinders.add(petfinder);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return petfinders;
    }
}
