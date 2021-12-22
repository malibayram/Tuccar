package come491.hanibana.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import come491.hanibana.Model.categoryModel;
import come491.hanibana.Model.subCategoryModel;
import come491.hanibana.R;
import come491.hanibana.Screen.Category.produstList;

public class subCategoryAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<subCategoryModel> categorys;
    private Activity mActivity;

    public subCategoryAdapter(Context mContext, List<subCategoryModel> categorys, Activity mActivity) {
        this.categorys = categorys;
        this.mContext = mContext;
        this.mActivity = mActivity;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        View v = LayoutInflater.from(mContext).inflate(R.layout.category_item, parent, false);
        vh = new subCategoryAdapter.UViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        subCategoryModel category = categorys.get(position);
        //kategori resmini ekrana basıyoruz
        Glide.with(mContext).load(category.getSubCategoryImage()).into(((subCategoryAdapter.UViewHolder) holder).categoryImage);
        //kategory isminin ekrana basıldıgı alan
        ((subCategoryAdapter.UViewHolder) holder).categoryName.setText(category.getSubCategoryName());
        // katogoriye tıklanma durumu
        ((subCategoryAdapter.UViewHolder) holder).root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mActivity, produstList.class);
                i.putExtra("subCategoryId",category.getId());
                i.putExtra("categoryId",category.getTopCategoryid());
                i.putExtra("categoryName",category.getSubCategoryName());
                mContext.startActivity(i);

            }
        });
    }

    @Override
    public int getItemCount() {
        return categorys.size();
    }


    public class UViewHolder extends RecyclerView.ViewHolder {

        public TextView categoryName;
        public ImageView categoryImage;
        public FrameLayout root;

        public UViewHolder(View itemView) {
            super(itemView);

            categoryName = itemView.findViewById(R.id.categoryName);
            categoryImage = itemView.findViewById(R.id.categoryImage);
            root = itemView.findViewById(R.id.root);

        }

    }

}
