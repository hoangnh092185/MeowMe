package n8.meowme.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.Bind;
import butterknife.ButterKnife;
import n8.meowme.R;

public class UserInputActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = UserInputActivity.class.getSimpleName();

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;


    @Bind(R.id.userInputTextView) TextView mUserInputTextView;

    @Bind(R.id.homeTextView) TextView mHomeTextView;
    @Bind(R.id.searchTextView) TextView mSearchTextView;
    @Bind(R.id.listTextView) TextView mListTextView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_input);
        ButterKnife.bind(this);

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

//        mUserInputButton.setOnClickListener(this);
//        mSavedListsButton.setOnClickListener(this);

        mHomeTextView.setOnClickListener(this);
        mSearchTextView.setOnClickListener(this);
        mListTextView.setOnClickListener(this);

//        Typeface userInputButtonView = Typeface.createFromAsset(getAssets(), "fonts/Calligraffiti.ttf");
//        mUserInputButton.setTypeface(userInputButtonView);

        Typeface userInputTextView = Typeface.createFromAsset(getAssets(), "fonts/Calligraffiti.ttf");
        mUserInputTextView.setTypeface(userInputTextView);

        mUserInputTextView.setText("Welcome to MeowMe, where we end your felinelessnes!!");

    }

    @Override
    public void onClick(View v) {
        if(v == mSearchTextView){
            Intent intent = new Intent(UserInputActivity.this, PetfinderListActivity.class);
            startActivity(intent);
        }
        if(v == mListTextView){
            Intent intent = new Intent(UserInputActivity.this, SavedPetfinderListActivity.class);
            startActivity(intent);
        }
        if(v == mHomeTextView){
            Intent intent = new Intent(UserInputActivity.this, UserInputActivity.class);
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
