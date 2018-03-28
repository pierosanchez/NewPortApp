package com.newport.app.ui.questions;

import android.app.Fragment;
import android.app.FragmentManager;

import com.newport.app.ui.directory.DirectoryFragment;
import com.newport.app.ui.internalpolicies.InternalPoliciesFragment;

/**
 * Created by tohure on 11/11/17.
 */

public class FragmentPagerAdapter extends android.support.v13.app.FragmentPagerAdapter {

    FragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        if (position == 0) {
            return new InternalPoliciesFragment();
        } else {
            return new DirectoryFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    // This determines the title for each tab
    @Override
    public CharSequence getPageTitle(int position) {

        if (position == 0) {
            return "Políticas internas";
        } else {
            return "Directorio";
        }
    }
}