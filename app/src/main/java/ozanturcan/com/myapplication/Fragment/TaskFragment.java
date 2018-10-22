package ozanturcan.com.myapplication.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ozanturcan.com.myapplication.Adapter.TaskRVAdapter;
import ozanturcan.com.myapplication.Modal.ObservableObjects.TodoObervable;
import ozanturcan.com.myapplication.Modal.TaskTodo;
import ozanturcan.com.myapplication.Network.RetrofitCallOperation;
import ozanturcan.com.myapplication.R;
import ozanturcan.com.myapplication.Util.SharedPreferenceUtilities;

public class TaskFragment extends BaseFragment implements Observer, TaskRVAdapter.CustomItemCheckListener, TaskRVAdapter.CustomItemUnCheckListener {
    private RecyclerView recyclerView;
    private TodoObervable todoObervable;
    private RetrofitCallOperation retrofitCallOperation = new RetrofitCallOperation();
    private SharedPreferenceUtilities preferenceUtilities;
    private List<TaskTodo> taskTodoList;
    private View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_todo_stream, container, false);
        recyclerView = rootView.findViewById(R.id.recyclerview_feed_todo);
        rootView.findViewById(R.id.loading_album).setVisibility(View.VISIBLE);
        recyclerView.setLayoutManager(new GridLayoutManager(rootView.getContext(), 1));
        checkConnection();
        return rootView;
    }

    public void fillTask(final List<TaskTodo> taskTodoList) {
        TaskRVAdapter taskRVAdapter = new TaskRVAdapter(taskTodoList);
        recyclerView.setAdapter(taskRVAdapter);
        taskRVAdapter.setCheckListener(this);
        taskRVAdapter.setUnCheckListener(this);
        rootView.findViewById(R.id.loading_album).setVisibility(View.GONE);
        todoObervable.deleteObserver(this);

    }

    @Override
    public void update(Observable observable, Object o) {
        if (observable instanceof TodoObervable) {
            /* Typecast to UserRepository */
            preferenceUtilities.setTasksToSharedPrefs(todoObervable.getTaskTodoList());
            taskTodoList = preferenceUtilities.getTasksFromSharedPrefs();
            fillTask(taskTodoList);
        }
    }

    @Override
    public void onCheckListener(int position) {
        taskTodoList.get(position).setCompleted(true);
        preferenceUtilities.setTasksToSharedPrefs(taskTodoList);


    }

    @Override
    public void onUnCheckListener(int position) {
        taskTodoList.get(position).setCompleted(false);
        preferenceUtilities.setTasksToSharedPrefs(taskTodoList);

    }

    @Override
    public void checkConnection() {
        super.checkConnection();
        if (isOnline()) {
            getTaskFunction();
        }
    }

    private void getTaskFunction() {
        preferenceUtilities = new SharedPreferenceUtilities(rootView.getContext());
        taskTodoList = preferenceUtilities.getTasksFromSharedPrefs();
        todoObervable = TodoObervable.getInstance();
        todoObervable.addObserver(this);
        if (todoObervable.getTaskTodoList() == null && taskTodoList == null) {
            retrofitCallOperation.getTasks();
        } else if (taskTodoList != null) {
            fillTask(taskTodoList);
            todoObervable.setTaskTodoList(taskTodoList);
        } else {
            fillTask(todoObervable.getTaskTodoList());
        }
    }
}
