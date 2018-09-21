package ozanturcan.com.myapplication.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import ozanturcan.com.myapplication.Modal.TaskTodo;
import ozanturcan.com.myapplication.R;
import ozanturcan.com.myapplication.Util.SharedPreferenceUtilities;
import ozanturcan.com.myapplication.Util.StringUtilities;

public class TaskRVAdapter extends RecyclerView.Adapter<TaskRVAdapter.ViewHolder> {
    private CustomItemCheckListener checkListener;
    private CustomItemUnCheckListener unCheckListener;
    private List<TaskTodo> taskTodoList;
    private SharedPreferenceUtilities preferenceUtilities;

    public TaskRVAdapter(List<TaskTodo> taskTodoList) {
        this.taskTodoList = taskTodoList;
    }

    public interface CustomItemCheckListener {
        void onCheckListener(int position);
    }

    public interface CustomItemUnCheckListener {
        void onUnCheckListener(int position);
    }

    public void setUnCheckListener(CustomItemUnCheckListener unCheckListener) {
        this.unCheckListener = unCheckListener;
    }

    public void setCheckListener(CustomItemCheckListener checkListener) {
        this.checkListener = checkListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_item_holder_task, parent, false);
        preferenceUtilities = new SharedPreferenceUtilities(parent.getContext());
        final ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.textView_title.setText(StringUtilities.convertToFirtIndexUpperCase(taskTodoList.get(position).getTitle()));
        holder.textView_userName.setText(StringUtilities.convertToFirtIndexUpperCase(taskTodoList.get(position).getUserName()));
        boolean sharedResult = preferenceUtilities.getBoolenShared(taskTodoList.get(position).getId().toString());
        if (sharedResult) {
            holder.checkBox_todo.setChecked(true);
        } else {
            holder.checkBox_todo.setChecked(taskTodoList.get(position).getCompleted());
        }

        holder.checkBox_todo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    checkListener.onCheckListener(position);
                } else {
                    unCheckListener.onUnCheckListener(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return taskTodoList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView_title;
        TextView textView_userName;
        CheckBox checkBox_todo;

        public ViewHolder(View ItemView) {
            super(ItemView);
            textView_title = (TextView) itemView.findViewById(R.id.textView_title);
            textView_userName = (TextView) itemView.findViewById(R.id.textView_username);
            checkBox_todo = ItemView.findViewById(R.id.checkBox);
        }
    }

    public void clear() {
        notifyDataSetChanged();
    }
    // Add a list of items -- change to type used
}
