package n8.meowme.adapters;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;

import n8.meowme.R;
import n8.meowme.models.Petfinder;
import n8.meowme.util.ItemTouchHelperViewHolder;

/**
 * Created by Guest on 12/8/16.
 */
public class FirebasePetfinderViewHolder extends RecyclerView.ViewHolder implements ItemTouchHelperViewHolder {
    private static final int MAX_WIDTH = 600;
    private static final int MAX_HEIGHT = 500;

    View mView;
    Context mContext;
    public ImageView mPetfinderImageView;

    public FirebasePetfinderViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();

    }
    public void bindPetfinder(Petfinder petfinder){
        mPetfinderImageView = (ImageView) mView.findViewById(R.id.petfinderImageView);

        TextView petfinderNameTextView = (TextView) mView.findViewById(R.id.petfinderNameTextView);
        TextView lastUpdateTextView = (TextView) mView.findViewById(R.id.lastUpdatedTextView);
        TextView breedTextView = (TextView) mView.findViewById(R.id.breedTextView);
        TextView ageTextView = (TextView) mView.findViewById(R.id.ageTextView);

        if(!petfinder.getImageUrl().contains("http")){
            try{
                Bitmap imageBitmap = decodeFromFirebaseBase64(petfinder.getImageUrl());
                mPetfinderImageView.setImageBitmap(imageBitmap);
            }catch (IOException e){
                e.printStackTrace();
            }
        }else {
            Picasso.with(mContext)
                    .load(petfinder.getImageUrl())
                    .resize(MAX_WIDTH, MAX_HEIGHT)
                    .centerCrop()
                    .into(mPetfinderImageView);

            petfinderNameTextView.setText(petfinder.getName());
            lastUpdateTextView.setText("Last listed: "+ petfinder.getLastUpdate());
            breedTextView.setText("Breed: " + android.text.TextUtils.join(" /", petfinder.getBreed()));
            ageTextView.setText("Age: " + petfinder.getAge());

        }
        petfinderNameTextView.setText(petfinder.getName());
        lastUpdateTextView.setText("Last listed: "+ petfinder.getLastUpdate());
        breedTextView.setText("Breed: " + android.text.TextUtils.join(" /", petfinder.getBreed()));
        ageTextView.setText("Age: " + petfinder.getAge());
    }

    public static Bitmap decodeFromFirebaseBase64(String image) throws IOException {
        byte[] decodedByteArray = android.util.Base64.decode(image, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedByteArray, 0, decodedByteArray.length);
    }

    @Override
    public void onItemSelected(){
        AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(mContext, R.animator.drag_scale_on);
        set.setTarget(itemView);
        set.start();
    }
    @Override
    public void onItemClear(){
        AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(mContext,
                R.animator.drag_scale_off);
        set.setTarget(itemView);
        set.start();
    }

}


