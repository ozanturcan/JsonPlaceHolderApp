package ozanturcan.com.myapplication.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import ozanturcan.com.myapplication.Modal.Post;
import ozanturcan.com.myapplication.R;
import ozanturcan.com.myapplication.Views.CustomItemClickListener;

/**
 * Created by Legend on 10.05.2018.
 */

public class RecyclerViewAdapterPost extends RecyclerView.Adapter<RecyclerViewAdapterPost.ViewHolder> {
    CustomItemClickListener listener;
    private List<Post> postList;
    private boolean loading;

    private OnLoadMoreListener onLoadMoreListener;

    public RecyclerViewAdapterPost(List<Post> postList, CustomItemClickListener listener) {
        this.postList = postList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_item_holder_task, parent, false);
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
        holder.photo_title_id.setText(postList.get(position).getTitle());
        holder.textBoxTextViewCount.setText(postList.get(position).getCommentCount().toString());
        holder.textView_username.setText(postList.get(position).getUserName());
        holder.checkBox.setVisibility(View.INVISIBLE);
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView photo_title_id;
        TextView textView_username;
        CheckBox checkBox;
        TextView textBoxTextViewCount;
        CardView cardView;

        public ViewHolder(View ItemView) {
            super(ItemView);
            photo_title_id = (TextView) itemView.findViewById(R.id.photo_title);
            textBoxTextViewCount = (TextView) itemView.findViewById(R.id.textBoxCount);
            textView_username = (TextView) itemView.findViewById(R.id.textView_username);
            cardView = (CardView) itemView.findViewById(R.id.cardview_id_small);
             checkBox = (CheckBox) itemView.findViewById(R.id.checkBox);

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
