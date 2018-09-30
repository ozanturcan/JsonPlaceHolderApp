package ozanturcan.com.myapplication.Fragment;

import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.Observable;
import java.util.Observer;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ozanturcan.com.myapplication.Adapter.PostRVAdapter;
import ozanturcan.com.myapplication.Listener.CustomItemClickListener;
import ozanturcan.com.myapplication.Modal.ObservableObjects.PostObservable;
import ozanturcan.com.myapplication.Modal.Post;
import ozanturcan.com.myapplication.Network.RetrofitCallOperation;
import ozanturcan.com.myapplication.R;

public class PostFragment extends BaseFragment implements Observer {
    private RecyclerView recyclerviewFeed;
    private PostRVAdapter recyclerViewAdapter;
    private View rootView;
    private PostObservable postObservable;
    private RetrofitCallOperation retrofitCallOperation = new RetrofitCallOperation();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_post_stream, container, false);
        rootView.findViewById(R.id.loading_album).setVisibility(View.VISIBLE);
        recyclerviewFeed = (RecyclerView) rootView.findViewById(R.id.recyclerview_feed_post);
        recyclerviewFeed.setLayoutManager(new GridLayoutManager(rootView.getContext(), 1));
        checkConnection();
        return rootView;
    }

    public void fillPost(final Context context, PostObservable lstPost) {
        recyclerviewFeed.setAdapter(recyclerViewAdapter);
        recyclerViewAdapter = new PostRVAdapter(lstPost.getPostList(), new CustomItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                CommentFragment postDetailFragment = new CommentFragment();
                postDetailFragment.setArguments(moveToPostDetail(postObservable.getPostList().get(position)));
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                Toast.makeText(context, "Clicked Item: " + position, Toast.LENGTH_SHORT).show();
                retrofitCallOperation.getCommentListFromPost(postObservable.getPostList().get(position).getId().toString());
                transaction.replace(R.id.container, postDetailFragment).addToBackStack(null).commit();

            }
        });
        recyclerviewFeed.setAdapter(recyclerViewAdapter);
        rootView.findViewById(R.id.loading_album).setVisibility(View.GONE);
        postObservable.deleteObserver(this);

    }


    @Override
    public void update(Observable observable, Object o) {
        if (observable != null && observable instanceof PostObservable) {
            /* Typecast to UserRepository */
            PostObservable userRepository = (PostObservable) observable;
            fillPost(rootView.getContext(), postObservable);
        }
    }

    private Bundle moveToPostDetail(Post post) {
        Bundle cardViewBundle = new Bundle();
        cardViewBundle.putString("Body", post.getBody());
        cardViewBundle.putString("CommentCount", post.getCommentCount().toString());
        cardViewBundle.putString("Title", post.getTitle());
        cardViewBundle.putString("Username", post.getUserName());
        return cardViewBundle;
    }

    @Override
    public void checkConnection() {
        super.checkConnection();
        if (isOnline()){
            getPostFunction();
        }
    }

    private void getPostFunction() {
        postObservable = PostObservable.getInstance();
        postObservable.addObserver(this);

        if (postObservable.getPostList() == null) {
            retrofitCallOperation.getPost();
        } else {
            fillPost(getContext(), postObservable);
        }
    }
}
