package n8.meowme.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
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
import n8.meowme.Constants;
import n8.meowme.R;
import n8.meowme.models.Petfinder;
import n8.meowme.ui.PetfinderDetailActivity;
import n8.meowme.ui.PetfinderDetailFragment;
import n8.meowme.util.OnPetfinderSelectedListener;

/**
 * Created by Guest on 12/4/16.
 */
public class PetfinderListAdapter extends RecyclerView.Adapter<PetfinderListAdapter.PetfinderViewHolder> {
    private static final int MAX_WIDTH = 400;
    private static final int MAX_HEIGHT = 300;

    private ArrayList<Petfinder> mPetfinders = new ArrayList<>();
    private Context mContext;
    private OnPetfinderSelectedListener mOnPetfinderSelectedListener;


    public PetfinderListAdapter(Context context, ArrayList<Petfinder> petfinders, OnPetfinderSelectedListener petfinderSelectedListener){
        mContext = context;
        mPetfinders = petfinders;
        mOnPetfinderSelectedListener = petfinderSelectedListener;
    }

    @Override
    public PetfinderListAdapter.PetfinderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.petfinder_list_item, parent, false);
        PetfinderViewHolder viewHolder = new PetfinderViewHolder(view, mPetfinders, mOnPetfinderSelectedListener);
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
        @Bind(R.id.breedTextView) TextView mBreedTextView;
//        @Bind(R.id.lastUpdatedTextView) TextView mLastUpdatedTextView;
        @Bind(R.id.ageTextView) TextView mAgeTextView;

        private Context mContext;
        private int mOrientation;
        private ArrayList<Petfinder> mPetfinders = new ArrayList<>();
        private OnPetfinderSelectedListener mPetfinderSelectedListener;

        public PetfinderViewHolder(View itemView, ArrayList<Petfinder> petfinders, OnPetfinderSelectedListener petfinderSelectedListener){
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
            mOrientation = itemView.getResources().getConfiguration().orientation;
            mPetfinders = petfinders;
            mPetfinderSelectedListener = petfinderSelectedListener;

            if (mOrientation == Configuration.ORIENTATION_LANDSCAPE){
                createDetailFragment(0);
            }
            itemView.setOnClickListener(this);
        }
        private void createDetailFragment(int position){
            PetfinderDetailFragment detailFragment = PetfinderDetailFragment.newInstance(mPetfinders, position, Constants.SOURCE_FIND);
            FragmentTransaction ft = ((FragmentActivity) mContext).getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.petfinderDetailContainer, detailFragment);
            ft.commit();
        }
        public void bindPetfinder(Petfinder petfinder){
            Picasso.with(mContext)
                    .load(petfinder.getImageUrl())
                    .resize(MAX_WIDTH, MAX_HEIGHT)
                    .centerCrop()
                    .into(mPetfinderImageView);

            mPetfinderNameTextView.setText(petfinder.getName());
//            mLastUpdatedTextView.setText("Last listed: "+ petfinder.getLastUpdate());
            mBreedTextView.setText("Breed: " + android.text.TextUtils.join(" /", petfinder.getBreed()));
            mAgeTextView.setText("Age: " + petfinder.getAge());
        }

        @Override
        public void onClick(View v) {
            int itemPosition = getLayoutPosition();
            mPetfinderSelectedListener.onPetfinderSelected(itemPosition, mPetfinders, Constants.SOURCE_FIND);
            if (mOrientation == Configuration.ORIENTATION_LANDSCAPE) {
                createDetailFragment(itemPosition);
            } else {
                Intent intent = new Intent(mContext, PetfinderDetailActivity.class);
                intent.putExtra(Constants.EXTRA_KEY_POSITION, itemPosition);
                intent.putExtra(Constants.EXTRA_KEY_PETFINDERS, Parcels.wrap(mPetfinders));
                intent.putExtra(Constants.KEY_SOURCE, Constants.SOURCE_FIND);
                mContext.startActivity(intent);
            }
        }

    }

}
