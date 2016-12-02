package n8.meowme;

import android.util.Log;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

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

        Log.v(TAG, url);

        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }
}
