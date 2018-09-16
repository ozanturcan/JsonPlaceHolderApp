package ozanturcan.com.myapplication.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import ozanturcan.com.myapplication.Modal.TaskTodo;
import ozanturcan.com.myapplication.R;
import ozanturcan.com.myapplication.Util.SharedPreferenceUtilities;
import ozanturcan.com.myapplication.Util.StringUtilities;
import ozanturcan.com.myapplication.Views.CustomItemClickListener;

/**
 * Created by Legend on 10.05.2018.
 */

public class RecyclerViewAdapterToDo extends RecyclerView.Adapter<RecyclerViewAdapterToDo.ViewHolder> {
    private CustomItemClickListener listener;
    private List<TaskTodo> taskTodoList;
    private SharedPreferenceUtilities preferenceUtilities;

    public RecyclerViewAdapterToDo(List<TaskTodo> taskTodoList, CustomItemClickListener listener) {
        this.taskTodoList = taskTodoList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_item_holder_task, parent, false);
        preferenceUtilities = new SharedPreferenceUtilities(parent.getContext());
        final ViewHolder viewHolder = new ViewHolder(view);

        view.findViewById(R.id.checkBox).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v, viewHolder.getAdapterPosition());
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.textViewTitle.setText(StringUtilities.convertToFirtIndexUpperCase(taskTodoList.get(position).getTitle()));
        holder.textViewUserName.setText(StringUtilities.convertToFirtIndexUpperCase(taskTodoList.get(position).getUserName()));
        Boolean sharedResult = preferenceUtilities.getBoolenShared(taskTodoList.get(position).getId().toString());
        if (sharedResult) {
            holder.checkBoxTodo.setChecked(true);
        } else {
            holder.checkBoxTodo.setChecked(taskTodoList.get(position).getCompleted());
        }

    }

    @Override
    public int getItemCount() {
        return taskTodoList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTitle;
        TextView textViewUserName;
        CheckBox checkBoxTodo;

        public ViewHolder(View ItemView) {
            super(ItemView);
            textViewTitle = (TextView) itemView.findViewById(R.id.textView_title);
            textViewUserName = (TextView) itemView.findViewById(R.id.textView_username);
            checkBoxTodo = ItemView.findViewById(R.id.checkBox);
        }
    }

    public void clear() {
        notifyDataSetChanged();
    }
    // Add a list of items -- change to type used
}
