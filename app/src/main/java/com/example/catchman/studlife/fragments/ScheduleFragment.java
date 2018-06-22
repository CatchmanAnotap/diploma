package com.example.catchman.studlife.fragments;



import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.catchman.studlife.Arguments;
import com.example.catchman.studlife.R;
import com.example.catchman.studlife.components.DynamicHeightViewPager;
import com.example.catchman.studlife.components.ViewPagerCircleIndicator;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;


public class ScheduleFragment extends BaseFragment implements ViewPager.OnPageChangeListener {

    @BindView(R.id.meal_linear)
    LinearLayout mealLinear;
    @BindView(R.id.viewPager)
    DynamicHeightViewPager viewPager;
    @BindView(R.id.circleIndicator)
    ViewPagerCircleIndicator circleIndicator;
    private LessonPagerAdapter adapter;
    private Realm realm;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_schedule, container, false);
        ButterKnife.bind(this, view);
        init();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        realm.close();
    }

    private void init() {
        realm = Realm.getDefaultInstance();
        if (adapter == null) {
            adapter = new LessonPagerAdapter(getFragmentManager(), 5);
            Log.e("4#@", "init: " );
        }
        circleIndicator.withViewPager(viewPager).withAdapter(adapter).init();
        viewPager.addOnPageChangeListener(ScheduleFragment.this);
        viewPager.post(new Runnable() {
            @Override
            public void run() {
                adapter.updateViews(viewPager.getCurrentItem());
            }
        });



//        recyclerView.setHasFixedSize(true);
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
//        recyclerView.setLayoutManager(layoutManager);
//        bottleAdapter = new BottleContentAdapter(bottles);
//        recyclerView.setAdapter(addToMealSearchAdapter);

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        viewPager.measureCurrentView(adapter.fragments.get(position).getView());
        adapter.updateViews(position);
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private static class LessonPagerAdapter extends FragmentPagerAdapter {
        private ArrayList<LessonFragment> fragments = new ArrayList<>();

        public LessonPagerAdapter(FragmentManager fm, int count) {
            super(fm);
            for (int i = 0; i < count; i++) {
                fragments.add(LessonFragment.newInstance(i));
            }
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        public void updateViews(int pos) {
            fragments.get(pos).init();
        }
    }

    public static class LessonFragment extends Fragment {

        private int position;

        @BindView(R.id.meal_time)
        TextView description;
        @BindView(R.id.lesson_item_list)
        LinearLayout lessonContainer;

        public static LessonFragment newInstance(int position) {
            LessonFragment fragment = new LessonFragment();
            Bundle args = new Bundle();
            args.putInt(Arguments.ARG_POSITION, position);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            position = getArguments().getInt(Arguments.ARG_POSITION);

        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_lesson_content, container, false);
            ButterKnife.bind(this, view);
            init();
            return view;
        }

        public void init() {
            //Log.i("TAG", "init food");

            lessonContainer.removeAllViews();
            //if (dailyMeal == null) return;
            switch (position){
                case 0:
                    description.setText(R.string.monday);
                    break;
                case 1:
                    description.setText(R.string.tuesday);
                    break;
                case 2:
                    description.setText(R.string.wednesday);
                    break;
                case 3:
                    description.setText(R.string.thursday);
                    break;
                case 4:
                    description.setText(R.string.friday);
                    break;
            }
            for (int i = 1; i<2; i++) {
                View view = LayoutInflater.from(getActivity()).inflate(R.layout.item_nutrition_food, lessonContainer, false);

                TextView foodName = view.findViewById(R.id.testText);
                TextView countLesson = view.findViewById(R.id.countLesson);
                if(position == 0 || position == 4){
                    countLesson.setText("" +(i));
                    foodName.setText("Історія");
                } else {
                    countLesson.setText("" +(i));
                    foodName.setText("Математика");
                }
                //foodName.setText("Page " + i);

                lessonContainer.addView(view);
            }
        }

//        private Meal getDailyMeal() {
//            return ((NutritionProvider) getParentFragment()).getDailyMeal(position);
//        }

    }

}
