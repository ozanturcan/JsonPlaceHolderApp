package ozanturcan.com.myapplication.Fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ozanturcan.com.myapplication.Adapter.RecyclerViewAdapterToDo;
import ozanturcan.com.myapplication.Modal.TaskTodo;
import ozanturcan.com.myapplication.R;

public class TodoStreamFragment extends Fragment {
    RecyclerView recyclerviewFeed;
    List<TaskTodo> savedTask;
    RecyclerViewAdapterToDo recyclerViewAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View RootView = inflater.inflate(R.layout.fragment_todo_stream, container, false);
        recyclerviewFeed = (RecyclerView) RootView.findViewById(R.id.recyclerview_feed_todo);
        recyclerviewFeed.setLayoutManager(new GridLayoutManager(RootView.getContext(), 1));
        return RootView;
    }

    public void fillTask(Context context, List<TaskTodo> lstAlbum) {
        recyclerViewAdapter = new RecyclerViewAdapterToDo(context, lstAlbum);
        recyclerviewFeed.setAdapter(recyclerViewAdapter);
    }
}
