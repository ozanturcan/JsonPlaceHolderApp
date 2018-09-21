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
import ozanturcan.com.myapplication.Adapter.PhotoRVAdapter;
import ozanturcan.com.myapplication.Modal.ObservableObjects.PhotosObservable;
import ozanturcan.com.myapplication.Modal.Photo;
import ozanturcan.com.myapplication.Network.RetrofitCallOperation;
import ozanturcan.com.myapplication.R;

public class PhotoFragment extends Fragment implements Observer {
    private RecyclerView recyclerviewFeed;
    public Photo photos;
    private PhotoRVAdapter recyclerViewAdapter;
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
        shimmerEffectAction();
        super.onResume();
    }

    @Override
    public void onPause() {
        shimmerEffectAction();
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        shimmerEffectAction();
        super.onDestroyView();
    }

    public void fillAlbums(Context context, PhotosObservable lstPhoto) {
        recyclerViewAdapter = new PhotoRVAdapter( lstPhoto.getPhotoList());
        recyclerviewFeed.setAdapter(recyclerViewAdapter);

    }

    @Override
    public void update(Observable observable, Object o) {
        if (observable != null && observable instanceof PhotosObservable) {
            PhotosObservable userRepository = (PhotosObservable) observable;
            fillAlbums(RootView.getContext(), photosObservable);
            shimmerEffectAction();
        }
    }

    private void shimmerEffectAction() {
        if (photosObservable != null && photosObservable.getPhotoList() != null) {
            shimmerFrameLayout.stopShimmerAnimation();
            shimmerFrameLayout.setVisibility(View.GONE);
        } else {
            shimmerFrameLayout.startShimmerAnimation();
        }
    }
}
