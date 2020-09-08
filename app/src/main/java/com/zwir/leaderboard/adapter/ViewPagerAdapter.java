package com.zwir.leaderboard.adapter;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.zwir.leaderboard.fragment.BoardingFragment;

public class ViewPagerAdapter extends FragmentStateAdapter {
    private static final int CARD_ITEM_SIZE = 2;
    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }
    @NonNull @Override public Fragment createFragment(int position) {
        return BoardingFragment.newInstance(position == 0 ? "hours" : "skilliq");
    }
    @Override public int getItemCount() {
        return CARD_ITEM_SIZE;
    }
}