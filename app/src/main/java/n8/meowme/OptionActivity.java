package n8.meowme;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;

public class OptionActivity extends AppCompatActivity {
    @Bind(R.id.option1GridView)
    GridView mOption1GridView;
    String TAG = OptionActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option1);
        ButterKnife.bind(this);

//        mOption1GridView.setAdapter(new Option1Adapter(this));
//
//        mOption1GridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            public void onItemClick(AdapterView<?> parent, View v,
//                                    int position, long id) {
//                Toast.makeText(OptionActivity.this, "" + position,
//                        Toast.LENGTH_SHORT).show();
//            }
//        });

        Intent intent = getIntent();
        String inputSelection = intent.getStringExtra("inputSelection");

//        switch (inputSelection) {
//            case "killingTime":
//                Log.d(TAG, "killing time was selected");
//                imageView.setImageResource(mThumbIds[position]);
//                return imageView;
//            break;
//        }
//                case "introvertMode":
//                    Log.d(TAG, "introvert was selected");
//                    imageView.setImageResource(mIntrovertImageGridView[position]);
//                    return imageView;
//            break;
//            case "deathByCuteness":
//                    Log.d(TAG, "cuteness was selected");
//                    imageView.setImageResource(mCutenessImageGridView[position]);
//                    return imageView;
//            break;
//            default:
//            }



        //start our new activity here.
        //put as a stringextra the inputSelection

//        userInputSelection = inputName;
    }

//    public static class OptionAdapter extends BaseAdapter {
//        private Context mContext;
//
////        private String inputSelectionholder = getString(R.string.inputSelection);
//
//        public Option1Adapter(Context c) {
//            mContext = c;
//        }
//
//        public int getCount() {
//            return mThumbIds.length;
//        }
//
//        public Object getItem(int position) {
//            return null;
//        }
//
//        public long getItemId(int position) {
//            return 0;
//        }
//
//        // create a new ImageView for each item referenced by the Adapter
//        public View getView(int position, View convertView, ViewGroup parent) {
//            ImageView imageView;
////            String inputHolder = getString(R.string.inputSelection);
////            Log.d("Option1ActivityGetView", inputholder);
//            if (convertView == null) {
//                // if it's not recycled, initialize some attributes
//                imageView = new ImageView(mContext);
//                imageView.setLayoutParams(new GridView.LayoutParams(800, 400));
//                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//                imageView.setPadding(1, 1, 1, 1);
//            } else {
//                imageView = (ImageView) convertView;
//            }
////
//            imageView.setImageResource(mThumbIds[position]);
//            return imageView;
//        }
//
////        public String getInputSelection (int position, String inputSelection){
////           String imageView;
////            if(inputSelection == "killingTime"){
////                imageView.setImageResource(mThumbIds[position]);
////                return imageView;
////            } else if (inputSelection == "introvertMode"){
////                imageView.setImageResource(mIntrovertImageGridView[position]);
////                return imageView;
////            }else {
////                imageView.setImageResource(mCutenessImageGridView[position]);
////                return imageView;
////            }
////        }
//
//        // references to our images
//        private Integer[] mThumbIds = {
//                R.drawable.option1image01, R.drawable.option1image02,
//                R.drawable.option1image03, R.drawable.option1image04,
//                R.drawable.option1image05, R.drawable.option1image06,
//                R.drawable.option1image07, R.drawable.option1image08,
//                R.drawable.option1image09, R.drawable.option1image10,
//        };
//        private Integer[] mIntrovertImageGridView = {
//                R.drawable.option2image01, R.drawable.option2image02,
//                R.drawable.option2image03, R.drawable.option2image04,
//                R.drawable.option2image05, R.drawable.option2image06,
//                R.drawable.option2image07, R.drawable.option2image08,
//                R.drawable.option2image09, R.drawable.option2image10,
//        };
//        private Integer[] mCutenessImageGridView = {
//                R.drawable.option3image01, R.drawable.option3image02,
//                R.drawable.option3image03, R.drawable.option3image04,
//                R.drawable.option3image05, R.drawable.option3image06,
//                R.drawable.option3image07, R.drawable.option3image08,
//                R.drawable.option3image09, R.drawable.option3image10,
//        };
//
//    }
}

