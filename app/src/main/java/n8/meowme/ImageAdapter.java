package n8.meowme;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

/**
 * Created by Guest on 11/22/16.
 */
public class ImageAdapter extends BaseAdapter {

    private Context mContext;
    private String mInputSelection;

    public ImageAdapter(Context c, String inputSelection) {
        Log.v("imageAdapter", inputSelection);
        mInputSelection = inputSelection;
        mContext = c;
    }

    public int getCount() {
        if(mInputSelection.equals("killingTime")){
            return mThumbIds.length;
        }else if (mInputSelection.equals("introvertMode")) {
            return mIntrovertImageGridView.length;
        }else {
            return mCutenessImageGridView.length;
        }
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;

        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(800, 400));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(1,1, 1,1);

            switch (mInputSelection){
                case "killingTime":
                    Log.v("imageViewGetVIew", mInputSelection);
                    imageView.setImageResource(mThumbIds[position]);
                    break;
                case "introvertMode":
                    Log.v("imageViewGetVIew",  mInputSelection);
                    imageView.setImageResource(mIntrovertImageGridView[position]);
                    break;
                case "deathByCuteness":
                    Log.v("imageViewGetVIew", "deathByCuteness was sellected");
                    imageView.setImageResource(mCutenessImageGridView[position]);
                    break;
            }

        } else {
            imageView = (ImageView) convertView;
        }

        return imageView;
    }

    private Integer[] mThumbIds = {
            R.drawable.option1image01, R.drawable.option1image02,
            R.drawable.option1image03, R.drawable.option1image04,
            R.drawable.option1image05, R.drawable.option1image06,
            R.drawable.option1image07, R.drawable.option1image08,
            R.drawable.option1image09, R.drawable.option1image10,
    };
    private Integer[] mIntrovertImageGridView = {
            R.drawable.option2image01, R.drawable.option2image02,
            R.drawable.option2image03, R.drawable.option2image04,
            R.drawable.option2image05, R.drawable.option2image06,
            R.drawable.option2image07, R.drawable.option2image08,
            R.drawable.option2image09, R.drawable.option2image10,
    };
    private Integer[] mCutenessImageGridView = {
            R.drawable.option3image01, R.drawable.option3image02,
            R.drawable.option3image03, R.drawable.option3image04,
            R.drawable.option3image05, R.drawable.option3image06,
            R.drawable.option3image07, R.drawable.option3image08,
            R.drawable.option3image09, R.drawable.option3image10,
    };

}