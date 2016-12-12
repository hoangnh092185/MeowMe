package n8.meowme.ui;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;
import n8.meowme.Constants;
import n8.meowme.R;
import n8.meowme.models.Petfinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class PetfinderDetailFragment extends Fragment implements View.OnClickListener {
    private static final int MAX_WIDTH = 400;
    private static final int MAX_HEIGHT = 300;

    @Bind(R.id.homeTextView) TextView mhomeTextView;
    @Bind(R.id.petinderFragmentImageView) ImageView mPetfinderFragmentImageView;
    @Bind(R.id.petfinderNameTextView) TextView mPetfinderNameTextView;
    @Bind(R.id.ageTextView) TextView mAgeTextView;
    @Bind(R.id.breedTextView) TextView mLastUpdateTextView;
    @Bind(R.id.websiteTextView) TextView mWebsiteTextView;
    @Bind(R.id.phoneTextView) TextView mPhoneTextView;
    @Bind(R.id.addressTextView) TextView mAddressTextView;
    @Bind(R.id.saveTextView) TextView mSaveTextView;

//    List<String> mCategories = new ArrayList<>();
//    private DatabaseReference mCategoryReference;
//    private ValueEventListener mCategoryReferenceListener;
//    private SharedPreferences mSharedPreferences;
//    private SharedPreferences.Editor mEditor;

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


//        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//        mEditor = mSharedPreferences.edit();
//        mCategoryReference = FirebaseDatabase.getInstance().getReference().child(Constants.FIREBASE_CATEGORY_QUERY);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_petfinder_detail, container, false);
        ButterKnife.bind(this, view);

        Picasso.with(view.getContext())
                .load(mPetfinder.getImageUrl())
                .resize(MAX_WIDTH, MAX_HEIGHT)
                .centerCrop()
                .into(mPetfinderFragmentImageView);

        mPetfinderNameTextView.setText(mPetfinder.getName());
        mAgeTextView.setText("Age: " + mPetfinder.getAge());
        mLastUpdateTextView.setText("Breed: " + android.text.TextUtils.join(" /", mPetfinder.getBreed()));
        mWebsiteTextView.setText(mPetfinder.getWebsite());
        mPhoneTextView.setText(mPetfinder.getPhoneNumber());
        mAddressTextView.setText(mPetfinder.getAddress());


        mWebsiteTextView.setOnClickListener(this);
        mPhoneTextView.setOnClickListener(this);
        mAddressTextView.setOnClickListener(this);
        mSaveTextView.setOnClickListener(this);
        mhomeTextView.setOnClickListener(this);

        return view;
    }


    @Override
    public void onClick(View v) {
        if(v == mWebsiteTextView){
            Intent webIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse(Constants.PETFINDER_PET_DETAIL_URL + mPetfinder.getPetId()));
            startActivity(webIntent);
        }
        if (v == mPhoneTextView){
            Intent phoneIntent = new Intent(Intent.ACTION_DIAL,
                    Uri.parse("tel: " + mPetfinder.getPhoneNumber()));
            startActivity(phoneIntent);
        }
        if (v == mAddressTextView) {
            Intent mapIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("geo:0,0?q=" + mPetfinder.getAddress() + mPetfinder.getCity() + mPetfinder.getState() + mPetfinder.getZip() ));
            startActivity(mapIntent);
        }

//        if (v == mhomeTextView){
//            Intent intent = new Intent(PetfinderDetailFragment.this, UserInputActivity.class);
//            startActivity(intent);
//        }
//
        if (v == mSaveTextView){
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String uid = user.getUid();
            DatabaseReference petfinderRef = FirebaseDatabase
                    .getInstance()
                    .getReference(Constants.FIREBASE_CHILD_PETFINDERS)
                    .child(uid);

            DatabaseReference pushRef = petfinderRef.push();
            String pushId = pushRef.getKey();
            mPetfinder.setPushId(pushId);
            pushRef.setValue(mPetfinder);

//            petfinderRef.child("petId").addListenerForSingleValueEvent(new ValueEventListener() {
//                @Override
//                public void onDataChange(DataSnapshot dataSnapshot) {
//
//                    if (dataSnapshot.getValue() == null) {
//                        Toast.makeText(getContext(), "Doesn't Excist", Toast.LENGTH_SHORT).show();
//                        Log.d("dataSnapSHot", dataSnapshot.getValue().toString());
//                    } else {
//                        Toast.makeText(getContext(), "Exists", Toast.LENGTH_SHORT).show();
//                    }
//                }
//
//                @Override
//                public void onCancelled(DatabaseError databaseError) {
//
//                }
//            });
//            petfinderRef.push().setValue(mPetfinder);
            Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();
        }
    }
}
