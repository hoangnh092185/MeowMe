package n8.meowme.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import n8.meowme.R;

public class SavedPetfinderListActivity extends AppCompatActivity implements View.OnClickListener{
    @Bind(R.id.homeTextView) TextView mHomeTextView;
    @Bind(R.id.searchTextView) TextView mSearchTextView;
    @Bind(R.id.listTextView) TextView mListTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_petfinder_list);
        ButterKnife.bind(this);

        mHomeTextView.setOnClickListener(this);
        mSearchTextView.setOnClickListener(this);
        mListTextView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == mHomeTextView){
            Intent intent = new Intent(SavedPetfinderListActivity.this, UserInputActivity.class);
            startActivity(intent);
        }
        if(v == mSearchTextView){
            Intent intent = new Intent(SavedPetfinderListActivity.this, PetfinderListActivity.class);
            startActivity(intent);
        }
        if(v == mListTextView){
            Intent intent = new Intent(SavedPetfinderListActivity.this, SavedPetfinderListActivity.class);
            startActivity(intent);
        }
    }

}
