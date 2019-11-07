package com.brokenribs.app.ui.details;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.brokenribs.app.R;


import java.util.List;


public class ProductSpecificationAdapter extends RecyclerView.Adapter<ProductSpecificationAdapter.ViewHolder> {

    private List<ProductSpecificationModel> specModel;

    public ProductSpecificationAdapter(List<ProductSpecificationModel> specModel) {
        this.specModel = specModel;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        switch (viewType){
            case ProductSpecificationModel.SPECIFICATION_TITLE:
                TextView title = new TextView(parent.getContext());
                title.setTypeface(null, Typeface.BOLD);
                title.setTextColor(Color.parseColor("#000000"));
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                layoutParams.setMargins(
                        setDP(16, parent.getContext()),
                        setDP(16, parent.getContext()),
                        setDP(16, parent.getContext()),
                        setDP(16, parent.getContext()));
                title.setLayoutParams(layoutParams);
                return new ViewHolder(title);

            case ProductSpecificationModel.SPECIFICATION_BODY:
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_specification_item, parent, false);
                return new ViewHolder(view);
            default: return null;
        }

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {



        switch (specModel.get(position).getType()){
            case ProductSpecificationModel.SPECIFICATION_TITLE:
                holder.setTitle(specModel.get(position).getTitle());
                break;
            case ProductSpecificationModel.SPECIFICATION_BODY:
                String featureTitle = specModel.get(position).getFeatureName();
                String featureDetail = specModel.get(position).getFeatureValue();
                holder.setFeatures(featureTitle, featureDetail);
                break;
            default:return;
        }
    }


    @Override
    public int getItemViewType(int position) {
        switch (specModel.get(position).getType()){
            case 0:
                return ProductSpecificationModel.SPECIFICATION_TITLE;
            case 1:
                return ProductSpecificationModel.SPECIFICATION_BODY;
            default:
                return -1;
        }
    }

    @Override
    public int getItemCount() {
        return specModel.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewFeatureName;
        private TextView textViewFeatureValue;
        private TextView title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

        }

        private void setTitle(String titleString){
            title = (TextView) itemView;
            title.setText(titleString);
        }

        private void setFeatures(String featureTitle, String featureDetails){
            textViewFeatureName = itemView.findViewById(R.id.tvFeatureName);
            textViewFeatureValue = itemView.findViewById(R.id.tvFeatureValue);
            textViewFeatureName.setText(featureTitle);
            textViewFeatureValue.setText(featureDetails);
        }
    }


    private int setDP(int dp, Context context){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
    }

}
