package ozanturcan.com.myapplication.Fragment;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.Observable;
import java.util.Observer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ozanturcan.com.myapplication.Adapter.RecyclerViewAdapterAlbum;
import ozanturcan.com.myapplication.Modal.ObservableObjects.AlbumObservable;
import ozanturcan.com.myapplication.Modal.Photo;
import ozanturcan.com.myapplication.Network.RetrofitCallOperation;
import ozanturcan.com.myapplication.PhotoActivity;
import ozanturcan.com.myapplication.R;
import ozanturcan.com.myapplication.Views.CustomItemClickListener;

public class AlbumStreamFragment extends Fragment implements Observer {
    private RecyclerView recyclerviewFeed;
    public Photo photos;
    private RecyclerViewAdapterAlbum recyclerViewAdapter;
    private ShimmerFrameLayout shimmerFrameLayout;
    private View RootView;
    private AlbumObservable albumObservable;
    private RetrofitCallOperation retrofitCallOperation = new RetrofitCallOperation();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        RootView = inflater.inflate(R.layout.fragment_album_stream, container, false);
        shimmerFrameLayout = RootView.findViewById(R.id.shimmer_view_container_album);
        recyclerviewFeed = (RecyclerView) RootView.findViewById(R.id.recyclerview_feed_album);
        recyclerviewFeed.setLayoutManager(new GridLayoutManager(RootView.getContext(), 1));
        albumObservable = AlbumObservable.getInstance();
        albumObservable.addObserver(this);
        retrofitCallOperation.getAlbums();

        return RootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        shimmerFrameLayout.startShimmerAnimation();
    }

    @Override
    public void onPause() {
        shimmerFrameLayout.stopShimmerAnimation();
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    public void fillAlbums(final Context context, AlbumObservable lstAlbum) {
        recyclerviewFeed.setAdapter(recyclerViewAdapter);
        recyclerViewAdapter  = new RecyclerViewAdapterAlbum(lstAlbum.getAlbumList(), new CustomItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Toast.makeText(context, "Clicked Item: "+position,Toast.LENGTH_SHORT).show();
                retrofitCallOperation.getPhotoListFromAlbum(albumObservable.getAlbumList().get(position).getId().toString());
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                PhotoStreamFragment myFragment = new PhotoStreamFragment();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.relativeLayout_album, myFragment).addToBackStack(null).commit();

                Intent intent = new Intent(v.getContext(), PhotoActivity.class);
            }
        });
        recyclerviewFeed.setAdapter(recyclerViewAdapter);
    }


    @Override
    public void update(Observable observable, Object o) {
        if (observable != null && observable instanceof AlbumObservable) {
            /* Typecast to UserRepository */
            AlbumObservable userRepository = (AlbumObservable) observable;
            fillAlbums(RootView.getContext(), albumObservable);
            shimmerFrameLayout.stopShimmerAnimation();
        }
    }
}
