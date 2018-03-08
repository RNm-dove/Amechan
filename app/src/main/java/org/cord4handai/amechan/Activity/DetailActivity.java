package org.cord4handai.amechan.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import org.cord4handai.amechan.Model.ClaimService;
import org.cord4handai.amechan.R;

public class DetailActivity extends AppCompatActivity {

    public static String EXTRA_KEY = "extra_key";
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private DetailAdapter mAdapter;
    private ClaimService.Claim mItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);

        Intent intent = getIntent();
        mItem = (ClaimService.Claim)intent.getSerializableExtra(EXTRA_KEY);
        
        setUpViews();

    }

    private void setUpViews() {

        TextView ageText = findViewById(R.id.detail_age);
        TextView sexText = findViewById(R.id.detail_sex);
        TextView contentText = findViewById(R.id.detail_content);
        TextView whereText = findViewById(R.id.detail_place);

        ageText.setText(String.valueOf(mItem.age));
        sexText.setText(String.valueOf(mItem.sex));
        contentText.setText(mItem.content);
        whereText.setText("吹田市");

        mRecyclerView = findViewById(R.id.detail_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manage
        mLayoutManager = new LinearLayoutManager(this);

        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new DetailAdapter();
        mRecyclerView.setAdapter(mAdapter);
    }

}
