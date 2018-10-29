package ozanturcan.com.myapplication.fragment;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import io.reactivex.disposables.Disposable;
import ozanturcan.com.myapplication.Adapter.PostRVAdapter;
import ozanturcan.com.myapplication.Modal.Post;
import ozanturcan.com.myapplication.R;
import ozanturcan.com.myapplication.databinding.FragmentPostStreamBinding;
import ozanturcan.com.myapplication.network.RetrofitCallOperation;

public class PostFragment extends BaseFragment {
    private PostRVAdapter recyclerViewAdapter;
    FragmentPostStreamBinding binding;
    private RetrofitCallOperation retrofitCallOperation = new RetrofitCallOperation();

    Disposable postDisposable;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_post_stream, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerViewAdapter = new PostRVAdapter();
        recyclerViewAdapter.setListener((v, position) -> { });
        binding.recyclerviewFeedPost.setAdapter(recyclerViewAdapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        postDisposable = retrofitCallOperation.getPost().subscribe(binding::setViewState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (postDisposable != null && !postDisposable.isDisposed()) {
            postDisposable.dispose();
        }
    }

    private void initializeCommentFragment(Post post) {
        CommentFragment commentFragment = CommentFragment.newInstance(post);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        retrofitCallOperation.getCommentListFromPost(post.getId().toString());
        transaction.replace(R.id.container, commentFragment).addToBackStack(null).commit();
    }
}
