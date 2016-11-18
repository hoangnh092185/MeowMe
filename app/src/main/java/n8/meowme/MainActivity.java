package n8.meowme;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    @Bind(R.id.mainActivityTextView) TextView mMainActivityTextView;
    @Bind(R.id.mainActivityButton) Button mMainActivityButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mMainActivityButton.setOnClickListener(this);


        Typeface mainFontTextView = Typeface.createFromAsset(getAssets(), "fonts/anomalyDemo.ttf");
            mMainActivityTextView.setTypeface(mainFontTextView);
    }

    @Override
    public void onClick(View v) {
        if(v == mMainActivityButton){
//           Toast.makeText(MainActivity.this, "Hello World!", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(MainActivity.this, UserInputActivity.class);
            startActivity(intent);
        }
    }
}
