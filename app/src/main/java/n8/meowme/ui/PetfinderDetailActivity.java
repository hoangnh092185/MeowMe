
package n8.meowme.ui;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import n8.meowme.R;
import n8.meowme.adapters.PetfinderPagerAdapter;
import n8.meowme.models.Petfinder;

public class PetfinderDetailActivity extends AppCompatActivity {
    @Bind(R.id.viewPager) ViewPager mViewPager;
    private PetfinderPagerAdapter adapterViewPager;
    ArrayList<Petfinder> mPetfinders = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_petfinder_detail);
        ButterKnife.bind(this);

        mPetfinders = Parcels.unwrap(getIntent().getParcelableExtra("petfinders"));
        int startingPosition = getIntent().getIntExtra("position", 0);

        adapterViewPager = new PetfinderPagerAdapter(getSupportFragmentManager(), mPetfinders);

        mViewPager.setAdapter(adapterViewPager);
        mViewPager.setCurrentItem(startingPosition);

    }
}
