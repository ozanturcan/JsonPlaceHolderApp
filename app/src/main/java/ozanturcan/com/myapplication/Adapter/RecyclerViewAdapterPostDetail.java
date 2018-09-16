package ozanturcan.com.myapplication.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import ozanturcan.com.myapplication.Modal.Comment;
import ozanturcan.com.myapplication.R;
import ozanturcan.com.myapplication.Util.StringUtilities;
import ozanturcan.com.myapplication.Views.CustomItemClickListener;

/**
 * Created by Legend on 10.05.2018.
 */

public class RecyclerViewAdapterPostDetail extends RecyclerView.Adapter<RecyclerViewAdapterPostDetail.ViewHolder> {
    CustomItemClickListener listener;
    private List<Comment> commentList;
    private boolean loading;

    private OnLoadMoreListener onLoadMoreListener;
    public RecyclerViewAdapterPostDetail(List<Comment> commentList) {
        this.commentList = commentList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_item_holder_post, parent, false);
        final ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.photo_title_id.setText(StringUtilities.convertToFirtIndexUpperCase(commentList.get(position).getName()));
        holder.textBoxTextViewCount.setText("");
        holder.textView_username.setText(commentList.get(position).getEmail());
        holder.textView_description.setText(StringUtilities.convertToFirtIndexUpperCase(commentList.get(position).getBody()));
    }

    @Override
    public int getItemCount() {
        return commentList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView photo_title_id;
        TextView textView_username;
        TextView textView_description;
        TextView textBoxTextViewCount;

        public ViewHolder(View ItemView) {
            super(ItemView);
            photo_title_id = (TextView) itemView.findViewById(R.id.textView_title);
            textBoxTextViewCount = (TextView) itemView.findViewById(R.id.textView_count);
            textView_username = (TextView) itemView.findViewById(R.id.textView_username);
            textView_description = (TextView) itemView.findViewById(R.id.textView_description);
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
