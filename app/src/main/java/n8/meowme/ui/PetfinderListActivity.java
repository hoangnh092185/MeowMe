package n8.meowme.ui;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import org.parceler.Parcels;

import java.util.ArrayList;

import n8.meowme.Constants;
import n8.meowme.R;
import n8.meowme.models.Petfinder;

public class PetfinderListActivity extends AppCompatActivity {
    public static final String TAG = PetfinderListActivity.class.getSimpleName();
    private Integer mPosition;
    ArrayList<Petfinder> mPetfinders;
    String mSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_petfinder);

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

//    @Override
//    public void onPetfinderSelected(Integer position, ArrayList<Petfinder> petfinders, String source){
//        mPosition = position;
//        mPetfinders = petfinders;
//        mSource = source;
//    }

}
