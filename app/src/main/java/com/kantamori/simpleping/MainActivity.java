package com.kantamori.simpleping;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class MainActivity extends AppCompatActivity {

    ImageButton home, history, settings;
    TextView title;
    RelativeLayout main;
    AdView adView;
    AdRequest adRequest;
    Bundle bundle;
    FragmentTransaction fragmentTransaction;

    HomeFragment homeFragment;
    HistoryFragment historyFragment;
    SettingsFragment settingsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        home = (ImageButton)findViewById(R.id.HOME);
        history = (ImageButton)findViewById(R.id.HISTORY);
        settings = (ImageButton)findViewById(R.id.SETTINGS);
        title = (TextView)findViewById(R.id.TITLE);
        main = (RelativeLayout)findViewById(R.id.MAIN);

        bundle = savedInstanceState;

        homeFragment = new HomeFragment();
        historyFragment = new HistoryFragment();
        settingsFragment = new SettingsFragment();

        HomeBtnClicked(bundle);


        MobileAds.initialize(getApplicationContext(), getString(R.string.banner_ad_unit_id));

        adView = (AdView)findViewById(R.id.adView);
        //adView.setAdUnitId(getString(R.string.banner_ad_unit_id));
        //adView.setAdSize(AdSize.BANNER);
        //adRequest = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();
        adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
    }

    public void DockBtnClicked (View v) {
        Integer id = v.getId();

        if (id == home.getId()) {
            HomeBtnClicked(bundle);

        } else if (id == history.getId()) {
            HistoryBtnClicked(bundle);

        } else if (id == settings.getId()) {
            SettingsBtnClicked(bundle);

        }
    }

    public void HomeBtnClicked (Bundle bundle) {
        home.setAlpha(1.0f);
        history.setAlpha(0.5f);
        settings.setAlpha(0.5f);
        title.setText(R.string.app_name);

        if (bundle == null && main.getChildCount() == 0) {
            homeFragment = new HomeFragment();
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.MAIN, homeFragment, HomeFragment.FRAGMENT_NAME);
            fragmentTransaction.commit();
        } else {
            if (homeFragment == null) homeFragment = new HomeFragment();
            getFragmentManager().beginTransaction().remove(settingsFragment).commit();
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.MAIN, homeFragment, HomeFragment.FRAGMENT_NAME);
            fragmentTransaction.commit();
        }
    }

    public void HistoryBtnClicked (Bundle bundle) {
        home.setAlpha(0.5f);
        history.setAlpha(1.0f);
        settings.setAlpha(0.5f);
        title.setText(R.string.history);

        if (bundle == null && main.getChildCount() == 0) {
            historyFragment = new HistoryFragment();
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.MAIN, historyFragment, HistoryFragment.FRAGMENT_NAME);
            fragmentTransaction.commit();
        } else {
            if (historyFragment == null) historyFragment = new HistoryFragment();
            getFragmentManager().beginTransaction().remove(settingsFragment).commit();
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.MAIN, historyFragment, HistoryFragment.FRAGMENT_NAME);
            fragmentTransaction.commit();
        }
    }

    public void SettingsBtnClicked (Bundle bundle) {
        home.setAlpha(0.5f);
        history.setAlpha(0.5f);
        settings.setAlpha(1.0f);
        title.setText(R.string.settings);

        if (bundle == null && main.getChildCount() == 0) {
            settingsFragment = new SettingsFragment();

            getFragmentManager().beginTransaction().add(R.id.MAIN, settingsFragment).commit();

            //fragmentTransaction = getSupportFragmentManager().beginTransaction();
            //fragmentTransaction.add(R.id.MAIN, settingsFragment, SettingsFragment.FRAGMENT_NAME);
            //fragmentTransaction.commit();
        } else {
            if (settingsFragment == null) settingsFragment = new SettingsFragment();
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.remove(homeFragment);
            fragmentTransaction.remove(historyFragment);
            fragmentTransaction.commit();

            getFragmentManager().beginTransaction().replace(R.id.MAIN, settingsFragment).commit();

            //fragmentTransaction = getSupportFragmentManager().beginTransaction();
            //fragmentTransaction.replace(R.id.MAIN, settingsFragment, SettingsFragment.FRAGMENT_NAME);
            //fragmentTransaction.commit();
        }
    }
}
