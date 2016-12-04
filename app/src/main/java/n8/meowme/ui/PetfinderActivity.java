package n8.meowme.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.ButterKnife;
import n8.meowme.R;
import n8.meowme.models.Petfinder;
import n8.meowme.services.PetfinderService;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class PetfinderActivity extends AppCompatActivity {
    public static final String TAG = PetfinderActivity.class.getSimpleName();

//    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
//    @Bind(R.id.recyclerForcastView) RecyclerView mRecyclerForcastView;

    public ArrayList<Petfinder> mPetfinders = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_petfinder);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String location = "97217";
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


                   if (response.isSuccessful()){
                       mPetfinders = petfinderService.processResults(response);
                   }

            }
        });
    }
}