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
import ozanturcan.com.myapplication.Listener.CustomItemClickListener;

public class CommentRVAdapter extends RecyclerView.Adapter<CommentRVAdapter.ViewHolder> {
    private CustomItemClickListener listener;
    private List<Comment> commentList;
    public CommentRVAdapter(List<Comment> commentList) {
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
        holder.textView_title.setText(StringUtilities.convertToFirtIndexUpperCase(commentList.get(position).getName()));
        holder.textView_count.setText("");
        holder.textView_username.setText(commentList.get(position).getEmail());
        holder.textView_description.setText(StringUtilities.convertToFirtIndexUpperCase(commentList.get(position).getBody()));
    }

    @Override
    public int getItemCount() {
        return commentList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView_title;
        TextView textView_username;
        TextView textView_description;
        TextView textView_count;

        public ViewHolder(View ItemView) {
            super(ItemView);
            textView_title = (TextView) itemView.findViewById(R.id.textView_title);
            textView_count = (TextView) itemView.findViewById(R.id.textView_count);
            textView_username = (TextView) itemView.findViewById(R.id.textView_username);
            textView_description = (TextView) itemView.findViewById(R.id.textView_description);
        }
    }
    public void clear() {
        notifyDataSetChanged();
    }

}
