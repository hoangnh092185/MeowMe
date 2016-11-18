package n8.meowme;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

/**
 * Created by Guest on 11/18/16.
 */
public class ImageAdapter extends BaseAdapter {

    // references to our images
    private Integer[] mThumbIds = {
            R.drawable.option1image01, R.drawable.option1image02,
            R.drawable.option1image03, R.drawable.option1image04,
            R.drawable.option1image05, R.drawable.option1image06,
            R.drawable.option1image07, R.drawable.option1image08,
            R.drawable.option1image09, R.drawable.option1image10,
    };

    private Context mContext;

    public ImageAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return mThumbIds.length;
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
            imageView.setLayoutParams(new GridView.LayoutParams(120, 120));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(2,2, 2,2);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(mThumbIds[position]);
        return imageView;
    }

//    private Integer[] mOption2ImageView = {
//            R.drawable.option2image01, R.drawable.option2image02,
//            R.drawable.option2image03, R.drawable.option2image04,
//            R.drawable.option2image05, R.drawable.option2image06,
//            R.drawable.option2image07, R.drawable.option2image08,
//            R.drawable.option2image09, R.drawable.option2image10,
//    };
//    private Integer[] mOption3ImageView = {
//            R.drawable.option3image01, R.drawable.option3image02,
//            R.drawable.option3image03, R.drawable.option3image04,
//            R.drawable.option3image05, R.drawable.option3image06,
//            R.drawable.option3image07, R.drawable.option3image08,
//            R.drawable.option3image09, R.drawable.option3image10,
//    };


}