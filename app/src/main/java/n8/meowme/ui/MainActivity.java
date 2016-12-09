package n8.meowme.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import n8.meowme.R;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    @Bind(R.id.mainActivityTextView) TextView mMainActivityTextView;
    @Bind(R.id.mainActivityButton) Button mMainActivityButton;
    @Bind(R.id.mainActivityEditText) EditText mMainActivityEditText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mMainActivityButton.setOnClickListener(this);


        Typeface mainFontTextView = Typeface.createFromAsset(getAssets(), "fonts/AnomalyDemo.ttf");
            mMainActivityTextView.setTypeface(mainFontTextView);

        Typeface mainFontButtonView = Typeface.createFromAsset(getAssets(), "fonts/Calligraffiti.ttf");
        mMainActivityButton.setTypeface(mainFontButtonView);
    }

    @Override
    public void onClick(View v) {
        if(v == mMainActivityButton){
          String inputName = mMainActivityEditText.getText().toString();
            Intent intent = new Intent(MainActivity.this, UserInputActivity.class);
            intent.putExtra("inputName", inputName);
            startActivity(intent);
        }
    }

}
