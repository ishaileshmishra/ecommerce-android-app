package com.brokenribs.app.ui.details;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.brokenribs.app.R;

public class ProductDescriptionFragment extends Fragment {


    public ProductDescriptionFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_product_description, container, false);
    }

}
