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
import ozanturcan.com.myapplication.Listener.CustomItemClickListener;

public class AlbumRVAdapter extends RecyclerView.Adapter<AlbumRVAdapter.ViewHolder> {
    private CustomItemClickListener listener;
    private List<Album> albumList;

    public AlbumRVAdapter(List<Album> albumList, CustomItemClickListener listener) {
        this.albumList = albumList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_item_holder_album, parent, false);
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
        holder.textView_title.setText(albumList.get(position).getTitle());
        Glide.with(holder.cardView.getContext()).load(albumList.get(position).getAlbumPhoto().getThumbnailUrl()).into(holder.imageView_photo);
    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView_title;
        ImageView imageView_photo;
        CardView cardView;

        ViewHolder(View ItemView) {
            super(ItemView);
            textView_title = (TextView) itemView.findViewById(R.id.album_title);
            imageView_photo = (ImageView) itemView.findViewById(R.id.album_photo);
            cardView = (CardView) itemView.findViewById(R.id.cardview_album);
        }
    }

    public void clear() {
        notifyDataSetChanged();
    }
}
