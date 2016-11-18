package n8.meowme;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Option1Activity extends AppCompatActivity {
    @Bind(R.id.gridView) GridView mGridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option1);
        ButterKnife.bind(this);

        mGridView.setAdapter(new ImageAdapter(this));

        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(Option1Activity.this, "" + position,
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
