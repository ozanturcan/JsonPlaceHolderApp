package ozanturcan.com.myapplication.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import ozanturcan.com.myapplication.Modal.Photo;
import ozanturcan.com.myapplication.PhotoActivity;
import ozanturcan.com.myapplication.R;

/**
 * Created by Legend on 10.05.2018.
 */

public class PhotoRVAdapter extends RecyclerView.Adapter<PhotoRVAdapter.ViewHolder> {

    private List<Photo> photoList;

    public PhotoRVAdapter(List<Photo> photoList) {
        this.photoList = photoList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(parent.getContext());
        view = mInflater.inflate(R.layout.cardview_item_holder_photo, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.textView_title.setText(photoList.get(position).getTitle());
        Glide.with(holder.cardView.getContext().getApplicationContext()).load(photoList.get(position).getUrl()).into(holder.imageView_photo);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(), PhotoActivity.class);
                // passing data to the book activity

                intent.putExtra("Title", photoList.get(position).getTitle());
                intent.putExtra("Thumbnail", photoList.get(position).getUrl());
                // start the activity
                v.getContext().startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return photoList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView_title;
        ImageView imageView_photo;
        CardView cardView;

        ViewHolder(View ItemView) {
            super(ItemView);
            textView_title = (TextView) itemView.findViewById(R.id.photo_title);
            imageView_photo = (ImageView) itemView.findViewById(R.id.photo_thumb_img);
            cardView = (CardView) itemView.findViewById(R.id.cardview_photo);
        }
    }


    public void clear() {
        notifyDataSetChanged();
    }
}
