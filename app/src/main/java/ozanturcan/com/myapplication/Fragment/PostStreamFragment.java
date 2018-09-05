package ozanturcan.com.myapplication.Fragment;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.Observable;
import java.util.Observer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ozanturcan.com.myapplication.Adapter.RecyclerViewAdapterPost;
import ozanturcan.com.myapplication.Modal.ObservableObjects.PostObservable;
import ozanturcan.com.myapplication.Modal.Photo;
import ozanturcan.com.myapplication.Network.RetrofitCallOperation;
import ozanturcan.com.myapplication.PhotoActivity;
import ozanturcan.com.myapplication.R;
import ozanturcan.com.myapplication.Views.CustomItemClickListener;

public class PostStreamFragment extends Fragment implements Observer {
    private RecyclerView recyclerviewFeed;
    public Photo photos;
    private RecyclerViewAdapterPost recyclerViewAdapter;
    private View RootView;
    private PostObservable postObservable;
    private RetrofitCallOperation retrofitCallOperation = new RetrofitCallOperation();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        RootView = inflater.inflate(R.layout.fragment_post_stream, container, false);
        recyclerviewFeed = (RecyclerView) RootView.findViewById(R.id.recyclerview_feed_post);
        recyclerviewFeed.setLayoutManager(new GridLayoutManager(RootView.getContext(), 1));
        postObservable = PostObservable.getInstance();
        postObservable.addObserver(this);
        retrofitCallOperation.getPost();

        return RootView;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    public void fillPost(final Context context, PostObservable lstPost) {
        recyclerviewFeed.setAdapter(recyclerViewAdapter);
        recyclerViewAdapter  = new RecyclerViewAdapterPost(lstPost.getPostList(), new CustomItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Toast.makeText(context, "Clicked Item: "+position,Toast.LENGTH_SHORT).show();
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                PhotoStreamFragment myFragment = new PhotoStreamFragment();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_post_stream, myFragment).addToBackStack(null).commit();

                Intent intent = new Intent(v.getContext(), PhotoActivity.class);
            }
        });
        recyclerviewFeed.setAdapter(recyclerViewAdapter);
    }


    @Override
    public void update(Observable observable, Object o) {
        if (observable != null && observable instanceof PostObservable) {
            /* Typecast to UserRepository */
            PostObservable userRepository = (PostObservable) observable;
            fillPost(RootView.getContext(), postObservable);
        }
    }
}
