package n8.meowme.ui;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import n8.meowme.Constants;
import n8.meowme.R;
import n8.meowme.models.Petfinder;
import n8.meowme.util.OnPetfinderSelectedListener;

public class PetfinderListActivity extends AppCompatActivity implements OnPetfinderSelectedListener {
    public static final String TAG = PetfinderListActivity.class.getSimpleName();
    private Integer mPosition;
    ArrayList<Petfinder> mPetfinders;
    String mSource;

    @Bind(R.id.homeTextView) TextView mHomeTextView;
    @Bind(R.id.searchTextView) TextView mSearchTextView;
    @Bind(R.id.listTextView) TextView mListTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_petfinder);
        ButterKnife.bind(this);

        if(savedInstanceState != null){
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
                mPosition = savedInstanceState.getInt(Constants.EXTRA_KEY_POSITION);
                mPetfinders = Parcels.unwrap(savedInstanceState.getParcelable(Constants.EXTRA_KEY_PETFINDERS));
                mSource = savedInstanceState.getString(Constants.KEY_SOURCE);

                if (mPosition != null && mPetfinders != null){
                    Intent intent = new Intent(this, PetfinderDetailActivity.class);
                    intent.putExtra(Constants.EXTRA_KEY_POSITION, mPosition);
                    intent.putExtra(Constants.EXTRA_KEY_PETFINDERS, Parcels.wrap(mPetfinders));
                    intent.putExtra(Constants.KEY_SOURCE, mSource);
                    startActivity(intent);
                }
            }
        }


        mHomeTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PetfinderListActivity.this, UserInputActivity.class);
                startActivity(intent);
            }
        });
        mSearchTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PetfinderListActivity.this, PetfinderListActivity.class);
                startActivity(intent);
            }
        });
        mListTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PetfinderListActivity.this, SavedPetfinderListActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        if (mPosition != null && mPetfinders != null){
            outState.putInt(Constants.EXTRA_KEY_POSITION, mPosition);
            outState.putParcelable(Constants.EXTRA_KEY_PETFINDERS, Parcels.wrap(mPetfinders));
            outState.putString(Constants.KEY_SOURCE, mSource);
        }
    }

    @Override
    public void onPetfinderSelected(Integer position, ArrayList<Petfinder> petfinders, String source){
        mPosition = position;
        mPetfinders = petfinders;
        mSource = source;
    }

}
