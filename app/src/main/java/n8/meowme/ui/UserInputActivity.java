package n8.meowme.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import n8.meowme.R;

public class UserInputActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = UserInputActivity.class.getSimpleName();
    @Bind(R.id.userInputGroup) RadioGroup mRadioGroup;
    @Bind(R.id.userInputButton) Button mUserInputButton;
    @Bind(R.id.userInputTextView) TextView mUserInputTextView;

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
            mUserInputTextView.setText("Welcome!! Choose and I'll meow you there!");
        }else {
            mUserInputTextView.setText("Welcome "+ userInputName + ". Choose and I'll meow you there!");
        }

    }

    @Override
    public void onClick(View v) {
        Intent intent;

        if(v == mUserInputButton){
            int selectId = mRadioGroup.getCheckedRadioButtonId();
            switch (selectId){
                case R.id.KillingTimeRadioButton:

                    String locationZip = "97217";
                    intent = new Intent(UserInputActivity.this, PetfinderActivity.class);
                    intent.putExtra("locationZip", locationZip);
                    startActivity(intent);
                    break;
                case R.id.IntrovertModeRadioButton:
                    String introvertMode = "introvertMode";
                    intent = new Intent(UserInputActivity.this, ImageGridViewActivity.class);
                    intent.putExtra("inputSelection", introvertMode);
                    startActivity(intent);
                    break;
                case R.id.DeathByCutenessRadioButton:
                    String deathByCuteness = "deathByCuteness";
                    intent = new Intent(UserInputActivity.this, ImageGridViewActivity.class);
                    intent.putExtra("inputSelection", deathByCuteness);
                    startActivity(intent);
                    break;
            }
        }


    }
}
