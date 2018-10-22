package ozanturcan.com.myapplication.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import ozanturcan.com.myapplication.Modal.Post;
import ozanturcan.com.myapplication.R;
import ozanturcan.com.myapplication.Listener.CustomItemClickListener;

/**
 * Created by Legend on 10.05.2018.
 */

public class PostRVAdapter extends RecyclerView.Adapter<PostRVAdapter.ViewHolder> {
    CustomItemClickListener listener;
    private List<Post> postList;

    public PostRVAdapter(List<Post> postList, CustomItemClickListener listener) {
        this.postList = postList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_item_holder_post, parent, false);
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
        //holder.textBoxTextViewCount.setText(postList.get(position).getCommentCount().toString());
        holder.textView_username.setText(postList.get(position).getUserName());
        holder.textView_description.setText(postList.get(position).getBody());
    }

    @Override
    public int getItemCount() {
        return postList.size();
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

    public void clear() {
        notifyDataSetChanged();
    }

}
