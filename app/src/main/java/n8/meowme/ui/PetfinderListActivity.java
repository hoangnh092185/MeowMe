package n8.meowme.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import n8.meowme.Constants;
import n8.meowme.R;
import n8.meowme.adapters.PetfinderListAdapter;
import n8.meowme.models.Petfinder;
import n8.meowme.services.PetfinderService;
import n8.meowme.util.OnPetfinderSelectedListener;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class PetfinderListActivity extends AppCompatActivity implements OnPetfinderSelectedListener {
    public static final String TAG = PetfinderListActivity.class.getSimpleName();

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private String mRecentAddress;

    private Integer mPosition;
//    ArrayList<Petfinder> mPetfinders;

    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;

    private PetfinderListAdapter mAdapter;
    public ArrayList<Petfinder> mPetfinders = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_petfinder);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String location = intent.getStringExtra("location");

        getPets(location);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mRecentAddress = mSharedPreferences.getString(Constants.PREFERENCES_LOCATION_KEY, null);

        if (mRecentAddress != null) {
            getPets(mRecentAddress);
        }

    }
    @Override
    public void onPetfinderSelected(Integer position, ArrayList<Petfinder> petfinders){
        mPosition = position;
        mPetfinders = petfinders;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        ButterKnife.bind(this);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();

        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                addToSharedPreferences(query);
                getPets(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private void addToSharedPreferences(String location) {
        mEditor.putString(Constants.PREFERENCES_LOCATION_KEY, location).apply();
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
                mPetfinders = petfinderService.processResults(response);

                PetfinderListActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//                        mAdapter = new PetfinderListAdapter(getApplicationContext());

                        mRecyclerView.setAdapter(mAdapter);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(PetfinderListActivity.this);
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);

                    }
                });
            }
        });
    }

}
