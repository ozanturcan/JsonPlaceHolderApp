package ozanturcan.com.myapplication.Fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Toast;

import java.util.Observable;
import java.util.Observer;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ozanturcan.com.myapplication.Adapter.RecyclerViewAdapterToDo;
import ozanturcan.com.myapplication.Modal.ObservableObjects.TodoObervable;
import ozanturcan.com.myapplication.Network.RetrofitCallOperation;
import ozanturcan.com.myapplication.R;
import ozanturcan.com.myapplication.Util.SharedPreferenceUtilities;
import ozanturcan.com.myapplication.Views.CustomItemClickListener;

public class TodoStreamFragment extends Fragment implements Observer {
    private RecyclerView recyclerviewFeed;
    private RecyclerViewAdapterToDo recyclerViewAdapter;
    private View RootView;
    private TodoObervable todoObervable;
    private RetrofitCallOperation retrofitCallOperation = new RetrofitCallOperation();
    private SharedPreferenceUtilities preferenceUtilities;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        RootView = inflater.inflate(R.layout.fragment_todo_stream, container, false);
        recyclerviewFeed = (RecyclerView) RootView.findViewById(R.id.recyclerview_feed_todo);
        recyclerviewFeed.setLayoutManager(new GridLayoutManager(RootView.getContext(), 1));

        todoObervable = TodoObervable.getInstance();
        todoObervable.addObserver(this);
        if (todoObervable.getTaskTodoList() == null) {
            retrofitCallOperation.getTodo();
        } else {
            fillTask(getContext(), todoObervable);
        }
        preferenceUtilities = new SharedPreferenceUtilities(RootView.getContext());
        return RootView;
    }

    public void fillTask(final Context context, final TodoObervable todoObervable) {
        recyclerViewAdapter = new RecyclerViewAdapterToDo(todoObervable.getTaskTodoList(), new CustomItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Toast.makeText(context, "Clicked Item saved on SharedPreference,  " + todoObervable.getTaskTodoList().get(position).getId(), Toast.LENGTH_SHORT).show();
                CheckBox checkBox = (CheckBox) v.findViewById(R.id.checkBox);
                if (checkBox.isChecked()) {
                    preferenceUtilities.setBooleanShared(todoObervable.getTaskTodoList().get(position).getId().toString(), true);
                }else{
                    preferenceUtilities.setBooleanShared(todoObervable.getTaskTodoList().get(position).getId().toString(), false);
                }
            }
        });
        recyclerviewFeed.setAdapter(recyclerViewAdapter);
    }

    @Override
    public void update(Observable observable, Object o) {
        if (observable != null && observable instanceof TodoObervable) {
            /* Typecast to UserRepository */
            fillTask(RootView.getContext(), todoObervable);
        }
    }

}
