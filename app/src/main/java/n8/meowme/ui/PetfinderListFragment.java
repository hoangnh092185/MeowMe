package n8.meowme.ui;


import android.app.usage.ConfigurationStats;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import n8.meowme.Constants;
import n8.meowme.R;
import n8.meowme.adapters.PetfinderListAdapter;
import n8.meowme.models.Petfinder;
import n8.meowme.services.PetfinderService;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class PetfinderListFragment extends Fragment {
    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;

    private PetfinderListAdapter mAdapter;
    public ArrayList<Petfinder> mPetfinders = new ArrayList<>();
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private String mRecentAddress;

    public PetfinderListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        mEditor = mSharedPreferences.edit();

        setHasOptionsMenu(true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_petfinder_list, container, false);
        ButterKnife.bind(this, view);

        mRecentAddress = mSharedPreferences.getString(Constants.PREFERENCES_LOCATION_KEY, null);

        if (mRecentAddress != null){
            getPets(mRecentAddress);
        }
        return view;
    }

    private void getPets(String location){
        final PetfinderService petfinderService = new PetfinderService();
        petfinderService.findPets(location, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response){
                mPetfinders = petfinderService.processResults(response);

               getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter = new PetfinderListAdapter(getActivity(), mPetfinders);

                        mRecyclerView.setAdapter(mAdapter);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);

                    }
                });
            }
        });
    }
    private void addToSharedPreferences(String location){
        mEditor.putString(Constants.PREFERENCES_LOCATION_KEY, location).apply();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_search, menu);

        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){
            @Override
            public boolean onQueryTextSubmit(String query){
                addToSharedPreferences(query);
                getPets(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText){
                return false;
            }

        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        return super.onOptionsItemSelected(item);
    }

}
