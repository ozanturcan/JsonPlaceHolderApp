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

public class RecyclerViewAdapterPhotoDetail extends RecyclerView.Adapter<RecyclerViewAdapterPhotoDetail.ViewHolder> {

    private List<Photo> photoList;
    private boolean loading;
    private int visibleThreshold = 5;
    private int lastVisibleItem, totalItemCount;

    private OnLoadMoreListener onLoadMoreListener;
    public RecyclerViewAdapterPhotoDetail(List<Photo> photoList) {
        this.photoList = photoList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(parent.getContext());
        view = mInflater.inflate(R.layout.cardview_item_holder_photo,parent,false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.ph_title.setText(photoList.get(position).getTitle());
        Glide.with(holder.cardView.getContext().getApplicationContext()).load(photoList.get(position).getUrl()).into(holder.ph_thumbnail_img);
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
    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView ph_title;
        ImageView ph_thumbnail_img;
        CardView cardView ;
        public ViewHolder(View ItemView){
            super(ItemView);
            ph_title = (TextView) itemView.findViewById(R.id.photo_title_id);
            ph_thumbnail_img = (ImageView) itemView.findViewById(R.id.photo_thumb_id);
            cardView  = (CardView) itemView.findViewById(R.id.cardview_id);
        }
    }

    public void setLoad() {
        loading = false;
    }

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener;
    }

    public interface OnLoadMoreListener {
        void onLoadMore();
    }

    public void setLoaded() {
        loading = false;
    }

    public void clear() {
        notifyDataSetChanged();
    }

    // Add a list of items -- change to type used
    public void addAll() {
        notifyDataSetChanged();
    }
}
