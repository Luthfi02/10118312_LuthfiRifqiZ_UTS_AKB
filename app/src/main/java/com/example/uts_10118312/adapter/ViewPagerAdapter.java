package com.example.uts_10118312.adapter;
//NAMA  : LUTHFI RIFQI ZULFIQAR
//NIM   : 10118312
//KELAS : IF-8
//TGL   : 3-6 JUNI 2021

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.uts_10118312.fragment.InfoFragment;
import com.example.uts_10118312.fragment.NoteFragment;
import com.example.uts_10118312.fragment.ProfileFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new ProfileFragment();
            case 1:
                return new NoteFragment();
            case 2:
                return new InfoFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
