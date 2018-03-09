package org.cord4handai.amechan.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.cord4handai.amechan.Model.ClaimService;
import org.cord4handai.amechan.Model.Sex;
import org.cord4handai.amechan.MyApplication;
import org.cord4handai.amechan.R;
import org.json.JSONException;

import java.io.IOException;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;

public class DetailActivity extends AppCompatActivity {

    public static String EXTRA_KEY = "extra_key";
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private DetailAdapter mAdapter;
    private ClaimService.Claim mItem;
    private ProgressBar mProgressBar;


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
        sexText.setText(Sex.judgeSexFromInt(mItem.sex).name());
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

        mProgressBar = (ProgressBar)findViewById(R.id.detail_progress_bar);

        Button button = findViewById(R.id.sendButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailActivity.this, SubmitActivity.class);
                startActivity(intent);
            }
        });


        loadComments();
    }

    private void loadComments() {

        FrameLayout layout = findViewById(R.id.detail_container);

        Single<ClaimService.ListComment> comment = ((MyApplication)getApplication()).getClaimService().getComments(7);

        comment
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        commentList -> {
                            mProgressBar.setVisibility(View.GONE);
                            mAdapter.setItemAndReflesh(commentList.items);

                        },
                        throwable -> {
                            if (throwable instanceof HttpException) {
                                // We had non-2XX http error
                                Log.d("TAG", "httpExeption" + throwable.getMessage());
                            }
                            if (throwable instanceof IOException) {
                                // A network or conversion error happened
                                Log.d("TAG", "ioExeption" + throwable.getMessage());
                            }
                            if(throwable instanceof JSONException){
                                Log.d("TAG", "JSONExeption" + throwable.getMessage());
                            }

                            Log.d("TAG",  throwable.getMessage());

                            Snackbar.make(layout, "読み込めませんでした。", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        }


                );
    }

}
