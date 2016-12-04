package n8.meowme.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import n8.meowme.R;
import n8.meowme.adapters.ImageAdapter;

public class ImageGridViewActivity extends AppCompatActivity {
    @Bind(R.id.imageGridView) GridView mImageGridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_grid_view);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String inputSelection = intent.getStringExtra("inputSelection");
        Log.v("imageGridViewActivity", inputSelection);

        mImageGridView.setAdapter(new ImageAdapter(this, inputSelection));


        mImageGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(ImageGridViewActivity.this, "" + position,
                        Toast.LENGTH_SHORT).show();
            }
        });

    }

}
