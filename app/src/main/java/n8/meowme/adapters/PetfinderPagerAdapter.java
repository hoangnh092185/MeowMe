package n8.meowme.adapters;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

import n8.meowme.models.Petfinder;
import n8.meowme.ui.PetfinderDetailFragment;

/**
 * Created by Guest on 12/4/16.
 */
public class PetfinderPagerAdapter extends FragmentPagerAdapter{
    private ArrayList<Petfinder> mPetfinders;
    private String mSource;

    public PetfinderPagerAdapter(FragmentManager fm, ArrayList<Petfinder> petfinders, String source){
        super(fm);
        mPetfinders = petfinders;
        mSource = source;
    }

    @Override
    public Fragment getItem(int position){
        return PetfinderDetailFragment.newInstance(mPetfinders, position, mSource);
    }

    @Override
    public int getCount(){
        return mPetfinders.size();
    }
    @Override
    public CharSequence getPageTitle(int position){
        return mPetfinders.get(position).getName();
    }
}
