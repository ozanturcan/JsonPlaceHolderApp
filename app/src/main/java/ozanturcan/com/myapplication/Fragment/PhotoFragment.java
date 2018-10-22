package ozanturcan.com.myapplication.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Observable;
import java.util.Observer;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ozanturcan.com.myapplication.Adapter.PhotoRVAdapter;
import ozanturcan.com.myapplication.Modal.ObservableObjects.PhotosObservable;
import ozanturcan.com.myapplication.R;

public class PhotoFragment extends BaseFragment implements Observer {
    private RecyclerView recyclerviewFeed;
    private PhotoRVAdapter recyclerViewAdapter;
    private View rootView;
    private PhotosObservable photosObservable;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_photo_stream, container, false);
//        rootView.findViewById(R.id.loading_album).setVisibility(View.VISIBLE);
        recyclerviewFeed = (RecyclerView) rootView.findViewById(R.id.recyclerview_feed_photo);
        recyclerviewFeed.setLayoutManager(new GridLayoutManager(rootView.getContext(), 1));
        photosObservable = PhotosObservable.getInstance();
        photosObservable.addObserver(this);
        return rootView;
    }

    public void fillAlbums( PhotosObservable lstPhoto) {
        recyclerViewAdapter = new PhotoRVAdapter( lstPhoto.getPhotoList());
        recyclerviewFeed.setAdapter(recyclerViewAdapter);
       // rootView.findViewById(R.id.loading_album).setVisibility(View.GONE);
        photosObservable.deleteObserver(this);

    }

    @Override
    public void update(Observable observable, Object o) {
        if (observable != null && observable instanceof PhotosObservable) {
            fillAlbums(photosObservable);
        }
    }
    @Override
    public void checkConnection() {
        super.checkConnection();
        if (isOnline()){

        }
    }
}
