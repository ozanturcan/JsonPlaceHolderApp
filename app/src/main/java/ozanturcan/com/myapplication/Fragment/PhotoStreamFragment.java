package ozanturcan.com.myapplication.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.Observable;
import java.util.Observer;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ozanturcan.com.myapplication.Adapter.RecyclerViewAdapterPhotoDetail;
import ozanturcan.com.myapplication.Modal.ObservableObjects.AlbumObservable;
import ozanturcan.com.myapplication.Modal.Photo;
import ozanturcan.com.myapplication.Modal.ObservableObjects.PhotosObservable;
import ozanturcan.com.myapplication.Network.RetrofitCallOperation;
import ozanturcan.com.myapplication.R;

public class PhotoStreamFragment extends Fragment implements Observer {
    private RecyclerView recyclerviewFeed;
    public Photo photos;
    private RecyclerViewAdapterPhotoDetail recyclerViewAdapter;
    private ShimmerFrameLayout shimmerFrameLayout;
    private View RootView;
    private PhotosObservable photosObservable;
    private RetrofitCallOperation retrofitCallOperation = new RetrofitCallOperation();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        RootView = inflater.inflate(R.layout.fragment_photo_stream, container, false);
        shimmerFrameLayout = RootView.findViewById(R.id.shimmer_view_container_photo);
        recyclerviewFeed = (RecyclerView) RootView.findViewById(R.id.recyclerview_feed_photo);
        recyclerviewFeed.setLayoutManager(new GridLayoutManager(RootView.getContext(), 1));
        photosObservable = PhotosObservable.getInstance();
        photosObservable.addObserver(this);


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

    public void fillAlbums(Context context, PhotosObservable lstPhoto) {
        recyclerViewAdapter = new RecyclerViewAdapterPhotoDetail( lstPhoto.getPhotoList());
        recyclerviewFeed.setAdapter(recyclerViewAdapter);

    }

    @Override
    public void update(Observable observable, Object o) {
        if (observable != null && observable instanceof PhotosObservable) {
            /* Typecast to UserRepository */
            AlbumObservable userRepository = (AlbumObservable) observable;
            fillAlbums(RootView.getContext(), photosObservable);
            shimmerFrameLayout.stopShimmerAnimation();
        }
    }
}
