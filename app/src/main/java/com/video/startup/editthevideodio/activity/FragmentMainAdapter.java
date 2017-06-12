package com.video.startup.editthevideodio.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Diogo on 11/06/2017.
 */

public class FragmentMainAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public FragmentMainAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }
    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                TabProfissionalFragment tabProfissionalFragment = new TabProfissionalFragment();
                return tabProfissionalFragment;
            case 1:
                TabEmpresaFragment tabEmpresaFragment = new TabEmpresaFragment();
                return tabEmpresaFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
