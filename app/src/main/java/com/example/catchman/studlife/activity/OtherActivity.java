package com.example.catchman.studlife.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.catchman.studlife.fragments.FindRoomFragment;
import com.example.catchman.studlife.FragmentUtil;
import com.example.catchman.studlife.fragments.InfoFragment;
import com.example.catchman.studlife.R;
import com.example.catchman.studlife.fragments.ScheduleFragment;

public class OtherActivity extends BaseActivity {


    public static final String SCHEDULE_INDEX = "schedule";
    public static final String INFO_INDEX = "info";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);
        switch (getIntent().getStringExtra("index")) {
            case SCHEDULE_INDEX:
                Toast.makeText(this, SCHEDULE_INDEX, Toast.LENGTH_SHORT).show();
                FragmentUtil.replaceFragment(getSupportFragmentManager(), new ScheduleFragment(), false);
                break;
            case INFO_INDEX:
                Toast.makeText(this, INFO_INDEX, Toast.LENGTH_SHORT).show();
                FragmentUtil.replaceFragment(getSupportFragmentManager(), new InfoFragment(), false);
                break;
        }
    }
}
