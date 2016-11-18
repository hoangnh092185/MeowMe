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
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(Option1Activity.this, "Hello world!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
