package ozanturcan.com.myapplication.fragment;

import java.util.List;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;
import ozanturcan.com.myapplication.Adapter.PostRVAdapter;
import ozanturcan.com.myapplication.Modal.Post;

public class PostBindingAdapter {

    @BindingAdapter(value = {"data"})
    public static void bindPostList(RecyclerView recyclerView, List<Post> posts){
        PostRVAdapter postRVAdapter = (PostRVAdapter) recyclerView.getAdapter();
        if (postRVAdapter != null){
            postRVAdapter.setPostList(posts);
        }
    }
}
