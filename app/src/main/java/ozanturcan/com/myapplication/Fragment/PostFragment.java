package ozanturcan.com.myapplication.Fragment;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import androidx.databinding.DataBindingUtil;
import io.reactivex.disposables.Disposable;
import ozanturcan.com.myapplication.Adapter.PostRVAdapter;
import ozanturcan.com.myapplication.Modal.Post;
import ozanturcan.com.myapplication.Network.RetrofitCallOperation;
import ozanturcan.com.myapplication.R;
import ozanturcan.com.myapplication.databinding.FragmentPostStreamBinding;

public class PostFragment extends BaseFragment {
    private PostRVAdapter recyclerViewAdapter;
    FragmentPostStreamBinding binding;
    private RetrofitCallOperation retrofitCallOperation = new RetrofitCallOperation();

    Disposable postDisposable;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_post_stream, container, false);
        binding.loadingAlbum.setVisibility(View.VISIBLE);
        checkConnection();
        return binding.getRoot();
    }

    @Override
    public void checkConnection() {
        super.checkConnection();
        if (isOnline()){
            postDisposable = retrofitCallOperation
                    .getPost()
                    .subscribe((posts, throwable) -> fillPost(posts));
        }
    }

    public void fillPost(List<Post> posts) {
        recyclerViewAdapter = new PostRVAdapter(posts, (v, position) -> initializeCommentFragment(posts.get(position)));
        binding.recyclerviewFeedPost.setAdapter(recyclerViewAdapter);
        binding.loadingAlbum.setVisibility(View.GONE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (postDisposable != null && !postDisposable.isDisposed()) {
            postDisposable.dispose();
        }
    }

    private void initializeCommentFragment(Post post){
        CommentFragment commentFragment = CommentFragment.newInstance(post);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        retrofitCallOperation.getCommentListFromPost(post.getId().toString());
        transaction.replace(R.id.container, commentFragment).addToBackStack(null).commit();
    }
}
