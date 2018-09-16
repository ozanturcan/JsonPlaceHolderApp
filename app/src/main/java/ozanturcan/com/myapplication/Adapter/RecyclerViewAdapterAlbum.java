package ozanturcan.com.myapplication.Adapter;

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
import ozanturcan.com.myapplication.Modal.Album;
import ozanturcan.com.myapplication.R;
import ozanturcan.com.myapplication.Views.CustomItemClickListener;

/**
 * Created by Legend on 10.05.2018.
 */

public class RecyclerViewAdapterAlbum extends RecyclerView.Adapter<RecyclerViewAdapterAlbum.ViewHolder> {
    CustomItemClickListener listener;
    private List<Album> albumList;
    public RecyclerViewAdapterAlbum(List<Album> albumList, CustomItemClickListener listener) {
        this.albumList = albumList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_item_holder, parent, false);
        final ViewHolder viewHolder = new ViewHolder(view);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v, viewHolder.getAdapterPosition());
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.ph_title.setText(albumList.get(position).getTitle());
        Glide.with(holder.cardView.getContext()).load(albumList.get(position).getAlbumPhoto().getUrl()).into(holder.ph_thumbnail_img);
    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView ph_title;
        ImageView ph_thumbnail_img;
        CardView cardView;

        public ViewHolder(View ItemView) {
            super(ItemView);
            ph_title = (TextView) itemView.findViewById(R.id.photo_title_id);
            ph_thumbnail_img = (ImageView) itemView.findViewById(R.id.photo_thumb_id);
            cardView = (CardView) itemView.findViewById(R.id.cardview_id);
        }
    }

    public interface OnLoadMoreListener {
        void onLoadMore();
    }

    public void clear() {
        notifyDataSetChanged();
    }

    // Add a list of items -- change to type used
    public void addAll() {
        notifyDataSetChanged();
    }
}
