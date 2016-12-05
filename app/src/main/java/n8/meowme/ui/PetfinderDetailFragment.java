package n8.meowme.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;
import n8.meowme.R;
import n8.meowme.models.Petfinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class PetfinderDetailFragment extends Fragment {
    @Bind(R.id.petinderFragmentImageView) ImageView mPetfinderFragmentImageView;
    @Bind(R.id.petfinderNameTextView) TextView mPetfinderNameTextView;
    @Bind(R.id.ageTextView) TextView mAgeTextView;
    @Bind(R.id.lastUpdateTextView) TextView mLastUpdateTextView;
    @Bind(R.id.websiteTextView) TextView mWebsiteTextView;
    @Bind(R.id.phoneTextView) TextView mPhoneTextView;
    @Bind(R.id.addressTextView) TextView mAddressTextView;

    private Petfinder mPetfinder;

    public static PetfinderDetailFragment newInstance(Petfinder petfinder){
        PetfinderDetailFragment petfinderDetailFragment = new PetfinderDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("petfinder", Parcels.wrap(petfinder));
        petfinderDetailFragment.setArguments(args);
        return petfinderDetailFragment;
    }

    @Override
    public void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        mPetfinder = Parcels.unwrap(getArguments().getParcelable("petfinder"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_petfinder_detail, container, false);
        ButterKnife.bind(this, view);

        Picasso.with(view.getContext()).load(mPetfinder.getImageUrl()).into(mPetfinderFragmentImageView);

        mPetfinderNameTextView.setText(mPetfinder.getName());
        mAgeTextView.setText("Age: " + mPetfinder.getAge());
        mLastUpdateTextView.setText("Last Updated: " + mPetfinder.getLastUpdate());
        mWebsiteTextView.setText(mPetfinder.getWebsite());
        mPhoneTextView.setText(mPetfinder.getPhoneNumber());
        mAddressTextView.setText("1067 NE Columbia Blvd, Portland, OR 97211");

        return view;
    }

}
