package org.cord4handai.amechan.Activity;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import org.cord4handai.amechan.Model.ClaimAdapter;
import org.cord4handai.amechan.Model.ClaimService;
import org.cord4handai.amechan.MyApplication;
import org.cord4handai.amechan.R;
import org.json.JSONException;

import java.io.IOException;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;

public class MainActivity extends AppCompatActivity implements ClaimAdapter.OnClaimItemClickListener{

    private FragmentPagerAdapter mPagerAdapter;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private ProgressBar mProgressBar;
    private ClaimAdapter mClaimAdapter;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpViews();
    }

    private void setUpViews() {

        mProgressBar = (ProgressBar)findViewById(R.id.progress_bar);
        mClaimAdapter = new ClaimAdapter(this, this);
        mRecyclerView = (RecyclerView)findViewById(R.id.main_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mRecyclerView.setAdapter(mClaimAdapter);

        loadClaims();
    }

    private void loadClaims() {


        // 読込中なのでプログレスバーを表示する
        mProgressBar.setVisibility(View.VISIBLE);

        FrameLayout layout = findViewById(R.id.container);


        // Retrofitを利用してサーバーにアクセスする
        final MyApplication application = (MyApplication) getApplication();
        // 過去一週間で作られて、言語がlanguageのものをクエリとして渡す
        Single<ClaimService.ListClaim> single = application.getClaimService().getClaims();
        // 入出力(IO)用のスレッドで通信を行い、メインスレッドで結果を受け取るようにする
        single
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        listResult -> {
                            mProgressBar.setVisibility(View.GONE);
                            // 取得したアイテムを表示するために、RecyclerViewにアイテムをセットして更新する
                            mClaimAdapter.setItemAndRefresh(listResult.items);
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

    @Override
    public void onClaimItemClick(ClaimService.Claim item) {
        Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
        intent.putExtra(DetailActivity.EXTRA_KEY, item);
        startActivity(intent);
    }
}
