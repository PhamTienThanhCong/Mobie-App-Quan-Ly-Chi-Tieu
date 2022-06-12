package com.example.qlct.activity;

import android.Manifest;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.qlct.adapter.PagerAdapter;
import com.example.qlct.fragment.AddFragment;
import com.example.qlct.fragment.OverViewFragment;
import com.example.qlct.R;
import com.example.qlct.fragment.SubFragment;
import com.example.qlct.realm.RealmController;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity{

    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;

    private RealmController realmController;

    private PagerAdapter adapter;
    private int MY_PERMISSIONS_REQUEST_SMS_RECEIVE = 10;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        realmController = new RealmController();
        setupViewPager();
        tabLayout.setupWithViewPager(viewPager);

        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.RECEIVE_SMS},
                MY_PERMISSIONS_REQUEST_SMS_RECEIVE);
    }

    private void setupViewPager() {
        adapter = new PagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new OverViewFragment(), "Thống kê");
        adapter.addFragment(new AddFragment(), "Thu");
        adapter.addFragment(new SubFragment(), "Chi");
        viewPager.setAdapter(adapter);
    }
}
