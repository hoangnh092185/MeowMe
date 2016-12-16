package n8.meowme.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import n8.meowme.R;
import n8.meowme.models.Petfinder;

/**
 * Created by Guest on 12/8/16.
 */
public class FirebasePetfinderViewHolder extends RecyclerView.ViewHolder {
    private static final int MAX_WIDTH = 600;
    private static final int MAX_HEIGHT = 500;

    public ImageView mPetfinderImageView;

    View mView;
    Context mContext;

    public FirebasePetfinderViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
//        itemView.setOnClickListener(this);

    }
    public void bindPetfinder(Petfinder petfinder){
        mPetfinderImageView = (ImageView) mView.findViewById(R.id.petfinderImageView);

        TextView petfinderNameTextView = (TextView) mView.findViewById(R.id.petfinderNameTextView);
        TextView lastUpdateTextView = (TextView) mView.findViewById(R.id.lastUpdatedTextView);
        TextView breedTextView = (TextView) mView.findViewById(R.id.breedTextView);
        TextView ageTextView = (TextView) mView.findViewById(R.id.ageTextView);

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

//    @Override
//    public void onClick(View view){
//        final ArrayList<Petfinder> petfinders = new ArrayList<>();
//        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_PETFINDERS);
//        ref.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
//                    petfinders.add(snapshot.getValue(Petfinder.class));
//                }
//                int itemPoition = getLayoutPosition();
//
//                Intent intent = new Intent(mContext, PetfinderDetailActivity.class);
//                intent.putExtra("position", itemPoition);
//                intent.putExtra("petfinders", Parcels.wrap(petfinders));
//
//                mContext.startActivity(intent);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//    }

}


