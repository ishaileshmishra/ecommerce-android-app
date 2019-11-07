package com.brokenribs.app.ui.details;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


public class ProductDetailsAdapter extends FragmentPagerAdapter {

    private int tabCount;

    public ProductDetailsAdapter(@NonNull FragmentManager fm, int tabCounter) {
        super(fm);
        tabCount = tabCounter;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new ProductDescriptionFragment();
            case 1:
                return new ProductSpecificationFragment();
            case 2:
                return new ProductDescriptionFragment();

        }
        return null;
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
