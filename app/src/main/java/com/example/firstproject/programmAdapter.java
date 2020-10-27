package com.example.firstproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.firstproject.api.PetResponse;
import com.example.firstproject.api.TagsItem;

import java.util.List;

public class programmAdapter extends RecyclerView.Adapter<programmAdapter.ViewHolder> {
    Context context;
    List<PetResponse> pets;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView rowCategoryName;
        TextView rowPetName;
        ImageView rowImage;
        TextView rowStatus;
        TextView rowTags;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rowCategoryName=itemView.findViewById(R.id.tvCategory);
            rowPetName=itemView.findViewById(R.id.tvPetName);
            rowImage=itemView.findViewById(R.id.imageView);
            rowStatus=itemView.findViewById(R.id.tvStatus);
            rowTags=itemView.findViewById(R.id.tvTags);
        }
    }
    public programmAdapter(Context context, List<PetResponse> pets ){
        this.context=context;
        this.pets = pets;

    }

    @NonNull
    @Override
    public programmAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.single_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.rowCategoryName.setText("Category: "+pets.get(position).getCategory().getName());
        holder.rowPetName.setText("Name: "+pets.get(position).getName());
        //holder.rowImage.setImageResource(R.mipmap.ic_launcher_round);
        //Glide.with(Context).load(mImageAddress).into(mImageView);
        //Glide.with(context).load(pets.get(position).getPhotoUrls().get(0)).into(holder.rowImage); //почти

        //setTooltipText("@tools:sample/avatars[2]");
        Glide.with(context).asBitmap().
                load(pets.get(position).getPhotoUrls().get(0))
                .apply(new RequestOptions().error(R.mipmap.ic_launcher_round).override(250,250)).into(holder.rowImage);

        holder.rowStatus.setText("Status: "+pets.get(position).getStatus());
        final List<TagsItem> tags=pets.get(position).getTags();
        String allTags="";
        for (TagsItem item : tags) {
            allTags+='#'+item.getName();
        }
        holder.rowTags.setText("tags: "+allTags);
    }

    @Override
    public int getItemCount() {
        return pets.size();
    }
}
