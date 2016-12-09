package n8.meowme.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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

    private DatabaseReference mSearchedLocationReference;
    private ValueEventListener mSearchedLocationReferenceListener;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private PetfinderListAdapter mAdapter;
    public ArrayList<Petfinder> mPetfinders = new ArrayList<>();

    @Bind(R.id.findPetfindersButton) Button mUserInputButton;
    @Bind(R.id.userInputTextView) TextView mUserInputTextView;
    @Bind(R.id.zipCodeEditText) EditText mZipCodeEditText;
    @Bind(R.id.savedListsButton) Button mSavedListsButton;

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

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    getSupportActionBar().setTitle("Welcome, " + user.getDisplayName() + " !");
                } else {

                }
            }
        };

        mUserInputButton.setOnClickListener(this);
        mSavedListsButton.setOnClickListener(this);

        Typeface userInputButtonView = Typeface.createFromAsset(getAssets(), "fonts/Calligraffiti.ttf");
        mUserInputButton.setTypeface(userInputButtonView);

        Typeface userInputTextView = Typeface.createFromAsset(getAssets(), "fonts/Calligraffiti.ttf");
        mUserInputTextView.setTypeface(userInputTextView);

        mUserInputTextView.setText("Enter your area code and I show you cute Cat!!");

//        Intent intent = getIntent();
//        String inputName = intent.getStringExtra("inputName");


//        userInputName = inputName;
//        if(userInputName == null){
//            mUserInputTextView.setText("Welcome!! Enter your area code and I show you cute Cat!");
//        }else {
//
//        }
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
        if(v == mSavedListsButton){
            Intent intent = new Intent(UserInputActivity.this, SavedPetfinderListActivity.class);
            startActivity(intent);
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            logout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(UserInputActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }


    public void saveLocationToFirebase(String location){
        mSearchedLocationReference.push().setValue(location);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSearchedLocationReference.removeEventListener(mSearchedLocationReferenceListener);
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}
