package ozanturcan.com.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import ozanturcan.com.myapplication.Modal.TaskTodo;
import ozanturcan.com.myapplication.R;

/**
 * Created by Legend on 10.05.2018.
 */

public class RecyclerViewAdapterToDo extends RecyclerView.Adapter<RecyclerViewAdapterToDo.ViewHolder> {

    private Context mContext;
    private List<TaskTodo> taskTodoList;
    public RecyclerViewAdapterToDo(Context mContext, List<TaskTodo> taskTodoList) {
        this.mContext = mContext;
        this.taskTodoList = taskTodoList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.cardview_item_holder_task,parent,false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.ph_title.setText(taskTodoList.get(position).getTitle());
//        holder.ph_thumbnail_img.setImageURI( GetImageBitmapFromUrl(photoList.get(position).getUrlM()));
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });
    }
    @Override
    public int getItemCount() {
        return taskTodoList.size();
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
   public void clear() {
        notifyDataSetChanged();
    }
    // Add a list of items -- change to type used
}
