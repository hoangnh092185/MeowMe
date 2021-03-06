package n8.meowme.ui;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import n8.meowme.Constants;
import n8.meowme.R;
import n8.meowme.models.Petfinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class PetfinderDetailFragment extends Fragment implements View.OnClickListener {
    private static final int MAX_WIDTH = 600;
    private static final int MAX_HEIGHT = 500;

    @Bind(R.id.petfinderImageView) ImageView mPetfinderImageLabel;
    @Bind(R.id.homeTextView) TextView mHomeLabel;
    @Bind(R.id.petfinderNameTextView) TextView mPetfinderNameLabel;
    @Bind(R.id.ageTextView) TextView mAgeLabel;
    @Bind(R.id.breedTextView) TextView mBreedLabel;
    @Bind(R.id.websiteTextView) TextView mWebsiteLabel;
    @Bind(R.id.phoneTextView) TextView mPhoneLabel;
    @Bind(R.id.addressTextView) TextView mAddressLabel;
    @Bind(R.id.saveTextView) TextView mSavePetLabel;
    @Bind(R.id.listTextView) TextView mListTextView;

    private Petfinder mPetfinder;

    private ArrayList<Petfinder> mPetfinders;
    private int mPosition;
    private String mSource;
    private static final int REQUEST_IMAGE_CAPTURE = 111;

    public static PetfinderDetailFragment newInstance(ArrayList<Petfinder> petfinders, Integer position, String source){
        PetfinderDetailFragment petfinderDetailFragment = new PetfinderDetailFragment();
        Bundle args = new Bundle();

        args.putParcelable(Constants.EXTRA_KEY_PETFINDERS, Parcels.wrap(petfinders));
        args.putInt(Constants.EXTRA_KEY_POSITION, position);
        args.putString(Constants.KEY_SOURCE, source);

        petfinderDetailFragment.setArguments(args);
        return petfinderDetailFragment;
    }
    public static PetfinderDetailFragment newInstance(Petfinder petfinder) {
        PetfinderDetailFragment petfinderDetailFragment = new PetfinderDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("petfinder", Parcels.wrap(petfinder));
        petfinderDetailFragment.setArguments(args);
        return petfinderDetailFragment;
    }

    @Override
    public void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        mPetfinders = Parcels.unwrap(getArguments().getParcelable(Constants.EXTRA_KEY_PETFINDERS));
        mPosition = getArguments().getInt(Constants.EXTRA_KEY_POSITION);
        mPetfinder = mPetfinders.get(mPosition);
        mSource = getArguments().getString(Constants.KEY_SOURCE);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        if (mSource.equals(Constants.SOURCE_SAVED)) {
            inflater.inflate(R.menu.menu_photo, menu);
        } else {
            inflater.inflate(R.menu.menu_save, menu);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_photo:
                onLaunchCamera();
            default:
                break;
        }
        return false;
    }
    public void onLaunchCamera() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == getActivity().RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            mPetfinderImageLabel.setImageBitmap(imageBitmap);
            encodeBitmapAndSaveToFirebase(imageBitmap);
        }
    }
    public void encodeBitmapAndSaveToFirebase(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        String imageEncoded = Base64.encodeToString(baos.toByteArray(), Base64.DEFAULT);
        DatabaseReference ref = FirebaseDatabase.getInstance()
                .getReference(Constants.FIREBASE_CHILD_PETFINDERS)
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .child(mPetfinder.getPushId())
                .child("imageUrl");
        ref.setValue(imageEncoded);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_petfinder_detail, container, false);
        ButterKnife.bind(this, view);

        if (!mPetfinder.getImageUrl().contains("http")) {
            try {
                Bitmap image = decodeFromFirebaseBase64(mPetfinder.getImageUrl());
                mPetfinderImageLabel.setImageBitmap(image);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            Picasso.with(view.getContext())
                    .load(mPetfinder.getImageUrl())
                    .resize(MAX_WIDTH, MAX_HEIGHT)
                    .centerCrop()
                    .into(mPetfinderImageLabel);
        }

        if (mSource.equals(Constants.SOURCE_SAVED)) {
            mSavePetLabel.setVisibility(View.GONE);
        } else {
            mSavePetLabel.setOnClickListener(this);
        }


        mPetfinderNameLabel.setText(mPetfinder.getName());
        mAgeLabel.setText("Age: " + mPetfinder.getAge());
        mBreedLabel.setText("Breed: " + android.text.TextUtils.join(" /", mPetfinder.getBreed()));
        mWebsiteLabel.setText(mPetfinder.getWebsite());
        mPhoneLabel.setText(mPetfinder.getPhoneNumber());
        mAddressLabel.setText(mPetfinder.getAddress());


        mWebsiteLabel.setOnClickListener(this);
        mPhoneLabel.setOnClickListener(this);
        mAddressLabel.setOnClickListener(this);
        mHomeLabel.setOnClickListener(this);
        mListTextView.setOnClickListener(this);


        return view;
    }
    public static Bitmap decodeFromFirebaseBase64(String image) throws IOException {
        byte[] decodedByteArray = android.util.Base64.decode(image, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedByteArray, 0, decodedByteArray.length);
    }


    @Override
    public void onClick(View v) {
        if(v == mWebsiteLabel){
            Intent webIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse(Constants.PETFINDER_PET_DETAIL_URL + mPetfinder.getPetId()));
            startActivity(webIntent);
        }
        if (v == mPhoneLabel){
            Intent phoneIntent = new Intent(Intent.ACTION_DIAL,
                    Uri.parse("tel: " + mPetfinder.getPhoneNumber()));
            startActivity(phoneIntent);
        }
        if (v == mAddressLabel) {
            Intent mapIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("geo:0,0?q=" + mPetfinder.getAddress() + mPetfinder.getCity() + mPetfinder.getState() + mPetfinder.getZip() ));
            startActivity(mapIntent);
        }

        if (v == mHomeLabel){
            Intent intent = new Intent(getActivity(), UserInputActivity.class);
            startActivity(intent);
        }
        if(v == mListTextView){
            Intent intent = new Intent(getActivity(), SavedPetfinderListActivity.class);
            startActivity(intent);
        }

        if (v == mSavePetLabel){
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String uid = user.getUid();

            final DatabaseReference petIdRef = FirebaseDatabase
                    .getInstance()
                    .getReference(Constants.FIREBASE_CHILD_PETFINDERS)
                    .child(uid);

            petIdRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    boolean match = false;
                    for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                        String currentId = snapshot.getValue(Petfinder.class).getPetId();
                        if(currentId.equals(mPetfinder.getPetId())){
                            match = true;
                            Toast.makeText(getContext(), "You already have " + mPetfinder.getName() + " on your list.", Toast.LENGTH_SHORT).show();
                            break;
                        }
                    }
                    if (!match) {
                        DatabaseReference pushRef = petIdRef.push();
                        String pushId = pushRef.getKey();
                        mPetfinder.setPushId(pushId);
                        pushRef.setValue(mPetfinder);
                        Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();
                    }
//                    else {Toast.makeText(getContext(), "You already have " + mPetfinder.getName() + " on your list.", Toast.LENGTH_SHORT).show();}
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            DatabaseReference petfinderRef = FirebaseDatabase
                    .getInstance()
                    .getReference(Constants.FIREBASE_CHILD_PETFINDERS)
                    .child(uid);

        }
    }
}
