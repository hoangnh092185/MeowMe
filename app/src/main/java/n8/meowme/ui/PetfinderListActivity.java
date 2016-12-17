package n8.meowme.ui;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import org.parceler.Parcels;

import java.util.ArrayList;

import n8.meowme.Constants;
import n8.meowme.R;
import n8.meowme.models.Petfinder;
import n8.meowme.util.OnPetfinderSelectedListener;

public class PetfinderListActivity extends AppCompatActivity implements OnPetfinderSelectedListener {
    public static final String TAG = PetfinderListActivity.class.getSimpleName();

//    private SharedPreferences mSharedPreferences;
//    private SharedPreferences.Editor mEditor;
//    private String mRecentAddress;

    private Integer mPosition;
    ArrayList<Petfinder> mPetfinders;
    String mSource;

//    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
//
//    private PetfinderListAdapter mAdapter;
//    public ArrayList<Petfinder> mPetfinders = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_petfinder);
//        ButterKnife.bind(this);

        if(savedInstanceState != null){
            if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
                mPosition = savedInstanceState.getInt(Constants.EXTRA_KEY_POSITION);
                mPetfinders = Parcels.unwrap(savedInstanceState.getParcelable(Constants.EXTRA_KEY_PETFINDERS));
                mSource = savedInstanceState.getString(Constants.KEY_SOURCE);

                if(mPosition != null && mPetfinders != null) {
                    Intent intent = new Intent(this, PetfinderDetailActivity.class);
                    intent.putExtra(Constants.EXTRA_KEY_POSITION, mPosition);
                    intent.putExtra(Constants.EXTRA_KEY_PETFINDERS, Parcels.wrap(mPetfinders));
                    intent.putExtra(Constants.KEY_SOURCE, mSource);
                    startActivity(intent);
                }
            }
        }

//        Intent intent = getIntent();
//        String location = intent.getStringExtra("location");
//
//        getPets(location);
//
//        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//        mRecentAddress = mSharedPreferences.getString(Constants.PREFERENCES_LOCATION_KEY, null);
//
//        if (mRecentAddress != null) {
//            getPets(mRecentAddress);
//        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);

        if (mPosition != null && mPetfinders != null){
            outState.putInt(Constants.EXTRA_KEY_POSITION, mPosition);
            outState.putParcelable(Constants.EXTRA_KEY_PETFINDERS, Parcels.wrap(mPetfinders));
            outState.putString(Constants.KEY_SOURCE, mSource);
        }
    }

    @Override
    public void onPetfinderSelected(Integer position, ArrayList<Petfinder> petfinders, String source){
        mPosition = position;
        mPetfinders = petfinders;
        mSource = source;
    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.menu_search, menu);
//        ButterKnife.bind(this);
//
//        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//        mEditor = mSharedPreferences.edit();
//
//        MenuItem menuItem = menu.findItem(R.id.action_search);
//        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
//
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                addToSharedPreferences(query);
//                getPets(query);
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                return false;
//            }
//        });
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        return super.onOptionsItemSelected(item);
//    }
//
//    private void addToSharedPreferences(String location) {
//        mEditor.putString(Constants.PREFERENCES_LOCATION_KEY, location).apply();
//    }
//
//    private void getPets(String location){
//        final PetfinderService petfinderService = new PetfinderService();
//        petfinderService.findPets(location, new Callback() {
//
//            @Override
//            public void onFailure(Call call, IOException e) {
//                e.printStackTrace();
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                mPetfinders = petfinderService.processResults(response);
//
//                PetfinderListActivity.this.runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        mAdapter = new PetfinderListAdapter(getApplicationContext());
//
//                        mRecyclerView.setAdapter(mAdapter);
//                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(PetfinderListActivity.this);
//                        mRecyclerView.setLayoutManager(layoutManager);
//                        mRecyclerView.setHasFixedSize(true);
//
//                    }
//                });
//            }
//        });
//    }

}
