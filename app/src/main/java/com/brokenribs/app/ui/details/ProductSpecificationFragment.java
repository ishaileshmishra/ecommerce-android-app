package com.brokenribs.app.ui.details;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.brokenribs.app.R;

import java.util.ArrayList;
import java.util.List;

public class ProductSpecificationFragment extends Fragment {

    public ProductSpecificationFragment() { }

    private RecyclerView productSpecRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_specification, container, false);

        productSpecRecyclerView = view.findViewById(R.id.product_specification_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        productSpecRecyclerView.setLayoutManager(linearLayoutManager);


        List<ProductSpecificationModel> models = new ArrayList<>();
        models.add(new ProductSpecificationModel(0,"General"));
        models.add(new ProductSpecificationModel(1,"RAM","4GB"));
        models.add(new ProductSpecificationModel(1,"RAM","2GB"));
        models.add(new ProductSpecificationModel(1,"RAM","8GB"));
        models.add(new ProductSpecificationModel(1,"RAM","6GB"));
        models.add(new ProductSpecificationModel(1,"RAM","3GB"));

        models.add(new ProductSpecificationModel(0,"Display"));
        models.add(new ProductSpecificationModel(1,"RAM","2GB"));
        models.add(new ProductSpecificationModel(1,"RAM","8GB"));
        models.add(new ProductSpecificationModel(1,"RAM","6GB"));
        models.add(new ProductSpecificationModel(1,"RAM","3GB"));

        models.add(new ProductSpecificationModel(0,"Other"));
        models.add(new ProductSpecificationModel(1,"RAM","2GB"));
        models.add(new ProductSpecificationModel(1,"RAM","8GB"));
        models.add(new ProductSpecificationModel(1,"RAM","6GB"));
        models.add(new ProductSpecificationModel(1,"RAM","3GB"));

        ProductSpecificationAdapter specificationAdapter = new ProductSpecificationAdapter(models);
        productSpecRecyclerView.setAdapter(specificationAdapter);
        specificationAdapter.notifyDataSetChanged();
        return view;
    }

}
