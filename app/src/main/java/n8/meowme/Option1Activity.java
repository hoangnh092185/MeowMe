package n8.meowme;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Option1Activity extends AppCompatActivity {
    @Bind(R.id.option1GridView) GridView mOption1GridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option1);
        ButterKnife.bind(this);

        mOption1GridView.setAdapter(new Option1Adapter(this));

        mOption1GridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(Option1Activity.this, "" + position,
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static class Option1Adapter extends BaseAdapter {
        private Context mContext;

        public Option1Adapter(Context c) {
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
                imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageView.setPadding(2,2, 2,2);
            } else {
                imageView = (ImageView) convertView;
            }

            imageView.setImageResource(mThumbIds[position]);
            return imageView;

        }

        // references to our images
        private Integer[] mThumbIds = {
                R.drawable.option1image01, R.drawable.option1image02,
                R.drawable.option1image03, R.drawable.option1image04,
                R.drawable.option1image05, R.drawable.option1image06,
                R.drawable.option1image07, R.drawable.option1image08,
                R.drawable.option1image09, R.drawable.option1image10,
        };

    }
}
