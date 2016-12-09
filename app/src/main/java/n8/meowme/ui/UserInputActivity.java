package n8.meowme.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import n8.meowme.Constants;
import n8.meowme.R;
import n8.meowme.adapters.PetfinderListAdapter;
import n8.meowme.models.Petfinder;

public class UserInputActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = UserInputActivity.class.getSimpleName();

//    private SharedPreferences mSharedPreferences;
//    private SharedPreferences.Editor mEditor;

    private DatabaseReference mSearchedLocationReference;
    private ValueEventListener mSearchedLocationReferenceListener;


//    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;

    private PetfinderListAdapter mAdapter;
    public ArrayList<Petfinder> mPetfinders = new ArrayList<>();

    @Bind(R.id.userInputButton) Button mUserInputButton;
    @Bind(R.id.userInputTextView) TextView mUserInputTextView;
    @Bind(R.id.zipCodeEditText) EditText mZipCodeEditText;

    private String userInputName = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_input);
        ButterKnife.bind(this);

        mSearchedLocationReference = FirebaseDatabase
                .getInstance()
                .getReference()
                .child(Constants.FIREBASE_CHILD_SEARCHED_LOCATION);

        mSearchedLocationReferenceListener = mSearchedLocationReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot locationSnapshot : dataSnapshot.getChildren()){
                    String location = locationSnapshot.getValue().toString();
                    Log.d("Locations updated", "location: " + location);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mUserInputButton.setOnClickListener(this);

        Typeface userInputButtonView = Typeface.createFromAsset(getAssets(), "fonts/Calligraffiti.ttf");
        mUserInputButton.setTypeface(userInputButtonView);

        Typeface userInputTextView = Typeface.createFromAsset(getAssets(), "fonts/Calligraffiti.ttf");
        mUserInputTextView.setTypeface(userInputTextView);

        Intent intent = getIntent();
        String inputName = intent.getStringExtra("inputName");
        userInputName = inputName;
        if(userInputName == null){
            mUserInputTextView.setText("Welcome!! Enter your area code and I show you cute Cat!");
        }else {
            mUserInputTextView.setText("Welcome "+ userInputName + ". Enter your area code and I show you cute Cat!!");
        }
    }

    @Override
    public void onClick(View v) {
        if(v == mUserInputButton){
            String location = mZipCodeEditText.getText().toString();
            saveLocationToFirebase(location);

            Intent intent = new Intent(UserInputActivity.this, PetfinderListActivity.class);
            intent.putExtra("location", location);
            startActivity(intent);
        }

    }


    public void saveLocationToFirebase(String location){
        mSearchedLocationReference.push().setValue(location);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSearchedLocationReference.removeEventListener(mSearchedLocationReferenceListener);
    }
}
