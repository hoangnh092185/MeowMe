package n8.meowme.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import n8.meowme.R;

public class SavedPetfinderListActivity extends AppCompatActivity {
    @Bind(R.id.homeTextView)TextView mHomeTextView;
    @Bind(R.id.searchTextView) TextView mSearchTextView;
    @Bind(R.id.listTextView) TextView mListTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_petfinder_list);
        ButterKnife.bind(this);

        mHomeTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SavedPetfinderListActivity.this, UserInputActivity.class);
                        startActivity(intent);
                    }
                });
        mSearchTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(SavedPetfinderListActivity.this, PetfinderListActivity.class);
                    startActivity(intent);
                }
            });
        mListTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(SavedPetfinderListActivity.this, SavedPetfinderListActivity.class);
                    startActivity(intent);
                }
            });
    }

}
