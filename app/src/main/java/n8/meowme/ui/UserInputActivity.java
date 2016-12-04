package n8.meowme.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import n8.meowme.R;

public class UserInputActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = UserInputActivity.class.getSimpleName();

    @Bind(R.id.userInputButton) Button mUserInputButton;
    @Bind(R.id.userInputTextView) TextView mUserInputTextView;
    @Bind(R.id.zipCodeEditText) EditText mZipCodeEditText;

    private String userInputName = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_input);
        ButterKnife.bind(this);

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
        Intent intent;

        if(v == mUserInputButton){
            String location = mZipCodeEditText.getText().toString();
            intent = new Intent(UserInputActivity.this, PetfinderActivity.class);
            intent.putExtra("location", location);
            startActivity(intent);
        }


    }
}
