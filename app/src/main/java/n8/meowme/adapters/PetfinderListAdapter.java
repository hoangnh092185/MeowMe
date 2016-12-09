package n8.meowme.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import n8.meowme.R;
import n8.meowme.models.Petfinder;
import n8.meowme.ui.PetfinderDetailActivity;

/**
 * Created by Guest on 12/4/16.
 */
public class PetfinderListAdapter extends RecyclerView.Adapter<PetfinderListAdapter.PetfinderViewHolder> {
    private static final int MAX_WIDTH = 400;
    private static final int MAX_HEIGHT = 300;

    private ArrayList<Petfinder> mPetfinders = new ArrayList<>();
    private Context mContext;

    public PetfinderListAdapter(Context context, ArrayList<Petfinder> petfinders){
        mContext = context;
        mPetfinders = petfinders;
    }

    @Override
    public PetfinderListAdapter.PetfinderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.petfinder_list_item, parent, false);
        PetfinderViewHolder viewHolder = new PetfinderViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PetfinderListAdapter.PetfinderViewHolder holder, int position) {
        holder.bindPetfinder(mPetfinders.get(position));
    }

    @Override
    public int getItemCount() {
        return mPetfinders.size();
    }

    public class PetfinderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @Bind(R.id.petfinderImageView) ImageView mPetfinderImageView;
        @Bind(R.id.petfinderNameTextView) TextView mPetfinderNameTextView;
        @Bind(R.id.lastUpdateTextView) TextView mLastUpdateTextView;
        @Bind(R.id.ageTextView) TextView mAgeTextView;

        private Context mContext;

        public PetfinderViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
            itemView.setOnClickListener(this);
        }
        public void bindPetfinder(Petfinder petfinder){
            Picasso.with(mContext)
                    .load(petfinder.getImageUrl())
                    .resize(MAX_WIDTH, MAX_HEIGHT)
                    .centerCrop()
                    .into(mPetfinderImageView);

            mPetfinderNameTextView.setText(petfinder.getName());
            mLastUpdateTextView.setText("Last listed: "+ petfinder.getLastUpdate());
            mAgeTextView.setText("Age: " + petfinder.getAge());
        }

        @Override
        public void onClick(View v){
            int itemPosition = getLayoutPosition();
            Intent intent = new Intent(mContext, PetfinderDetailActivity.class);
            intent.putExtra("position", itemPosition);
            intent.putExtra("petfinders", Parcels.wrap(mPetfinders));
            mContext.startActivity(intent);
        }
    }

}
