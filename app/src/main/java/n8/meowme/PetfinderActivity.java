package n8.meowme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;

import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class PetfinderActivity extends AppCompatActivity {
    public static final String TAG = PetfinderActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_petfinder);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String location = intent.getStringExtra("locationZip");
        Log.v(TAG, "get Location: " + location);
        getPets(location);
    }

    private void getPets(String location){
        final PetfinderService petfinderService = new PetfinderService();
        petfinderService.findPets(location, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    String jsonData = response.body().string();
                    Log.v(TAG, "jsonData : " + jsonData);
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        });
    }
}
