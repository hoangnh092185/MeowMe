package n8.meowme;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {
    @Bind(R.id.mainActivityTextView) TextView mMainActivityTextView;
//
//    private TextView mMainActivityTextView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

//        mMainActivityTextView = (TextView) findViewById(R.id.mainActivityTextView);
        Typeface mainFontTextView = Typeface.createFromAsset(getAssets(), "fonts/anomalyDemo.ttf");
            mMainActivityTextView.setTypeface(mainFontTextView);
    }
}
