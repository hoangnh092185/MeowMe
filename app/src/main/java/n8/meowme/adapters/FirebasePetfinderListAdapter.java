package n8.meowme.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.view.View;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.Collections;

import n8.meowme.Constants;
import n8.meowme.R;
import n8.meowme.models.Petfinder;
import n8.meowme.ui.PetfinderDetailActivity;
import n8.meowme.ui.PetfinderDetailFragment;
import n8.meowme.util.ItemTouchHelperAdapter;
import n8.meowme.util.OnStartDragListener;

/**
 * Created by Guest on 12/16/16.
 */
public class FirebasePetfinderListAdapter extends FirebaseRecyclerAdapter<Petfinder, FirebasePetfinderViewHolder>  implements ItemTouchHelperAdapter {

        private DatabaseReference mRef;
        private OnStartDragListener mOnStartDragListener;
        private Context mContext;
        private ChildEventListener mChildEventListener;
        private ArrayList<Petfinder> mPetfinders = new ArrayList<>();
        private int mOrientation;

        public FirebasePetfinderListAdapter(Class<Petfinder> modelClass, int modelLayout,
                                            Class<FirebasePetfinderViewHolder> viewHolderClass,
                                            Query ref, OnStartDragListener onStartDragListener, Context context){
            super(modelClass, modelLayout, viewHolderClass, ref);
            mRef = ref.getRef();
            mOnStartDragListener = onStartDragListener;
            mContext = context;

            mChildEventListener = mRef.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    mPetfinders.add(dataSnapshot.getValue(Petfinder.class));
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
    @Override
    protected void populateViewHolder(final FirebasePetfinderViewHolder viewHolder, Petfinder model, int position) {
        viewHolder.bindPetfinder(model);
        mOrientation = viewHolder.itemView.getResources().getConfiguration().orientation;

        if (mOrientation == Configuration.ORIENTATION_LANDSCAPE){
            createDetailFragment(0);
        }
        viewHolder.mPetfinderImageView.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event){
                if(MotionEventCompat.getActionMasked(event)== MotionEvent.ACTION_DOWN){
                    mOnStartDragListener.onStartDrag(viewHolder);
                }
                return false;
            }
        });
        viewHolder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                int itemPosition = viewHolder.getAdapterPosition();
                if(mOrientation == Configuration.ORIENTATION_LANDSCAPE){
                    createDetailFragment(itemPosition);
                } else {
                    Intent intent = new Intent(mContext, PetfinderDetailActivity.class);
                    intent.putExtra(Constants.EXTRA_KEY_POSITION, itemPosition);
                    intent.putExtra(Constants.EXTRA_KEY_PETFINDERS, Parcels.wrap(mPetfinders));
                    intent.putExtra(Constants.KEY_SOURCE, Constants.SOURCE_SAVED);
                    mContext.startActivity(intent);
                }

            }

        });
    }
    private void createDetailFragment(int position) {
        PetfinderDetailFragment detailFragment = PetfinderDetailFragment.newInstance(mPetfinders, position, Constants.SOURCE_SAVED);
        FragmentTransaction ft = ((FragmentActivity) mContext).getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.petfinderDetailContainer, detailFragment);
        ft.commit();
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(mPetfinders, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return false;
    }

    @Override
    public void onItemDismiss(int position) {
        mPetfinders.remove(position);
        getRef(position).removeValue();

    }

    private void setIndexInFirebase(){
        for(Petfinder petfinder : mPetfinders){
            int index = mPetfinders.indexOf(petfinder);
            DatabaseReference ref = getRef(index);
            ref.child("index").setValue(Integer.toString(index));
        }
    }
    @Override
    public void  cleanup(){
        super.cleanup();
        setIndexInFirebase();
        mRef.removeEventListener(mChildEventListener);
    }

}
