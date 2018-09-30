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
import ozanturcan.com.myapplication.Adapter.AlbumRVAdapter;
import ozanturcan.com.myapplication.Listener.CustomItemClickListener;
import ozanturcan.com.myapplication.Modal.ObservableObjects.AlbumObservable;
import ozanturcan.com.myapplication.Network.RetrofitCallOperation;
import ozanturcan.com.myapplication.R;

public class AlbumFragment extends BaseFragment implements Observer {
    private RecyclerView recyclerViewAlbum;
    private AlbumRVAdapter rvAdapter;
    private View rootView;
    private AlbumObservable albumObservable;
    private RetrofitCallOperation retrofitCallOperation = new RetrofitCallOperation();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_album_stream, container, false);
        rootView.findViewById(R.id.loading_album).setVisibility(View.VISIBLE);
        recyclerViewAlbum = (RecyclerView) rootView.findViewById(R.id.recyclerview_feed_album);
        recyclerViewAlbum.setLayoutManager(new GridLayoutManager(rootView.getContext(), 3));
        checkConnection();


        return rootView;
    }

    @Override
    public void update(Observable observable, Object o) {
        if (observable != null && observable instanceof AlbumObservable) {
            /* Typecast to UserRepository */
            fillAlbums(rootView.getContext(), albumObservable);

        }
    }

    @Override
    public void checkConnection() {
        super.checkConnection();
        if(isOnline()){
            getAlbumsFunction();
        }
    }

    private void fillAlbums(final Context context, AlbumObservable lstAlbum) {
        recyclerViewAlbum.setAdapter(rvAdapter);
        rvAdapter = new AlbumRVAdapter(lstAlbum.getAlbumList(), new CustomItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Toast.makeText(context, "Clicked Item: " + position, Toast.LENGTH_SHORT).show();
                retrofitCallOperation.getPhotoListFromAlbum(albumObservable.getAlbumList().get(position).getId().toString());
                PhotoFragment photoFragment = new PhotoFragment();
                FragmentTransaction transaction  = getFragmentManager().beginTransaction();
                transaction.replace(R.id.container, photoFragment).addToBackStack(null).commit();
            }
        });
        recyclerViewAlbum.setAdapter(rvAdapter);
        rootView.findViewById(R.id.loading_album).setVisibility(View.GONE);
        albumObservable.deleteObserver(this);
    }

    private void getAlbumsFunction(){
        albumObservable = AlbumObservable.getInstance();
        albumObservable.addObserver(this);
        if (albumObservable.getAlbumList() == null) {
            retrofitCallOperation.getAlbums();
        }else{
            fillAlbums(getContext(),albumObservable);
        }
    }

}
