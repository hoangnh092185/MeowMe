package n8.meowme.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

import n8.meowme.Constants;
import n8.meowme.R;
import n8.meowme.models.Petfinder;
import n8.meowme.ui.PetfinderDetailActivity;

/**
 * Created by Guest on 12/8/16.
 */
public class FirebasePetfinderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private static final int MAX_WIDTH = 400;
    private static final int MAX_HEIGHT = 300;

    View mView;
    Context mContext;

    public FirebasePetfinderViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);

    }
    public void bindPetfinder(Petfinder petfinder){
        ImageView petfinderImageView = (ImageView) mView.findViewById(R.id.petfinderImageView);
        TextView petfinderNameTextView = (TextView) mView.findViewById(R.id.petfinderNameTextView);
        TextView lastUpdateTextView = (TextView) mView.findViewById(R.id.breedTextView);
        TextView ageTextView = (TextView) mView.findViewById(R.id.ageTextView);

        Picasso.with(mContext)
                .load(petfinder.getImageUrl())
                .resize(MAX_WIDTH, MAX_HEIGHT)
                .centerCrop()
                .into(petfinderImageView);

        petfinderNameTextView.setText(petfinder.getName());
        lastUpdateTextView.setText("Last listed: "+ petfinder.getLastUpdate());
        ageTextView.setText("Age: " + petfinder.getAge());
    }

    @Override
    public void onClick(View view){
        final ArrayList<Petfinder> petfinders = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_PETFINDERS);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    petfinders.add(snapshot.getValue(Petfinder.class));
                }
                int itemPoition = getLayoutPosition();

                Intent intent = new Intent(mContext, PetfinderDetailActivity.class);
                intent.putExtra("position", itemPoition);
                intent.putExtra("petfinders", Parcels.wrap(petfinders));

                mContext.startActivity(intent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}


