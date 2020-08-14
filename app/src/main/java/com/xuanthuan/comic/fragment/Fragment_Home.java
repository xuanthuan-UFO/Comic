package com.xuanthuan.comic.fragment;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.xuanthuan.comic.R;
import com.xuanthuan.comic.adapter.ViewPagerAdapter;

public class Fragment_Home extends Fragment {
    TabLayout tabLayout;
    ViewPager viewPager1;
    TextView tv;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container,false);
        tabLayout = view.findViewById(R.id.tablayout);
        viewPager1 = view.findViewById(R.id.viewPagerhome);

        //getActivity().findViewById(R.id.container).setVisibility(View.INVISIBLE);

        FragmentManager fragmentManager = null;
        tabLayout.setupWithViewPager(viewPager1);
        tabLayout.setTabTextColors(getResources().getColor(R.color.colortabbar), getResources().getColor(R.color.colortabbar));

            Fragment_Hotupdate fragment_hotupdate = new Fragment_Hotupdate();
            Fragment_Completed fragment_completed = new Fragment_Completed();
            Fragment_Genres fragment_genres = new Fragment_Genres();
            Fragment_Top fragment_top = new Fragment_Top();

            ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager(), 0);
            viewPagerAdapter.addfragment(fragment_hotupdate, "Hot Update");
            viewPagerAdapter.addfragment(fragment_top, "Top");
            viewPagerAdapter.addfragment(fragment_completed, "Completed");
            viewPagerAdapter.addfragment(fragment_genres, "Genres");
            viewPager1.setAdapter(viewPagerAdapter);


            for (int i = 0; i < tabLayout.getTabCount(); i++) {
            //noinspection ConstantConditions
            TextView tv = (TextView)LayoutInflater.from(getContext()).inflate(R.layout.custom_tab,null);
            tv.setTypeface(Typeface.DEFAULT);
            tabLayout.getTabAt(i).setCustomView(tv);
        }

            viewPager1.setOffscreenPageLimit(4);
        return view;
    }

}
