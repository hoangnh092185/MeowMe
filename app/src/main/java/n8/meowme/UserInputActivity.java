package n8.meowme;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.BinderThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class UserInputActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.userInputGroup) RadioGroup mRadioGroup;
    @Bind(R.id.userInputButton) Button mUserInputButton;
    @Bind(R.id.userInputTextView) TextView mUserInputTextView;

    private String userInputName = "";

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
            mUserInputTextView.setText("Welcome "+ userInputName + ". Choose and I'll meow you there!");

    }


    @Override
    public void onClick(View v) {
        Intent intent;

        if(v == mUserInputButton){
            int selectId = mRadioGroup.getCheckedRadioButtonId();
            switch (selectId){
                case R.id.option1RadioButton:
                    intent = new Intent(UserInputActivity.this, Option1Activity.class);
                    startActivity(intent);
                    break;
                case R.id.option2RadioButton:
                    intent = new Intent(UserInputActivity.this, Option2Activity.class);
                    startActivity(intent);
                    break;
                case R.id.option3RadioButton:
                    intent = new Intent(UserInputActivity.this, Option3Activity.class);
                    startActivity(intent);
                    break;
            }
        }


    }
}
