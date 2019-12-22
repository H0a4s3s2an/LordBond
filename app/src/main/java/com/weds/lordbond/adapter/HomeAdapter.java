package com.weds.lordbond.adapter;

import android.content.Context;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.weds.lordbond.R;
import com.weds.lordbond.helper.GlideHelper;
import com.weds.lordbond.model.SignUp;

import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    private Context context;
    private ItemClickListener itemClickListener;
    private ArrayList<SignUp> arrayList;
    
    public HomeAdapter(ArrayList<SignUp> arrayList, ItemClickListener itemClickListener) {
    	this.arrayList = arrayList;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_profile_list, null);
        context = parent.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
    	
    	holder.userNameTv.setText(arrayList.get(position).getUserName());
    	holder.figureTv.setText(arrayList.get(position).getHeight().concat(" ").concat("Ft"));
    	holder.educationTv.setText(arrayList.get(position).getOccupation());
        GlideHelper.loadImage(context, arrayList.get(position).getProfileImage(), holder.userAvatar);
    	
        holder.profileLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
    	
    	private LinearLayout profileLay;
        private ImageView userAvatar;
        private TextView userNameTv;
        private TextView figureTv;
        private TextView educationTv;
        private TextView languageTv;
        private ImageView markFavorite;
        private ImageView markLike;
        private ImageView markDislike;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            
            profileLay = itemView.findViewById(R.id.profile_ll);
            userAvatar = itemView.findViewById(R.id.avatar_img);
            userNameTv = itemView.findViewById(R.id.name_tv);
            figureTv = itemView.findViewById(R.id.figure_tv);
            educationTv = itemView.findViewById(R.id.education_tv);
            languageTv = itemView.findViewById(R.id.language_tv);
            markFavorite = itemView.findViewById(R.id.favorite_img);
            markLike = itemView.findViewById(R.id.like_img);
            markDislike = itemView.findViewById(R.id.dislike_img);
        }
    }

    public interface ItemClickListener {
        void onItemClick(int position);
    }
}
