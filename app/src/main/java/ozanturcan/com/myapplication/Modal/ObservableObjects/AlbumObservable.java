package ozanturcan.com.myapplication.Modal.ObservableObjects;

import java.util.List;
import java.util.Observable;

import ozanturcan.com.myapplication.Modal.Album;
import ozanturcan.com.myapplication.Modal.Photo;

public class AlbumObservable extends Observable {

    private List<Album> albumList;
    private List<Photo> photoList;
    private static Object mLock = new Object();
    private static AlbumObservable USER_REPO_INSTANCE = null;
    public AlbumObservable() {

    }

    /* get Singleton instance of UserRepository  */
    public static AlbumObservable getInstance() {
        synchronized (mLock) {
            if (USER_REPO_INSTANCE == null)
                USER_REPO_INSTANCE = new AlbumObservable();
            return USER_REPO_INSTANCE;
        }
    }
    public List<Album> getAlbumList() {
        return albumList;
    }

    public void setAlbumList(List<Album> albumList) {
        this.albumList = albumList;
    }

    public void setPhotoList(List<Photo> photoList) {
        this.photoList = photoList;
        mergePhotosToAlbum();
        setChanged();
        notifyObservers();
    }

    private void mergePhotosToAlbum() {
        for (Album album : albumList) {
            for (Photo photo : photoList) {
                if (album.id == photo.albumId) {
                    album.albumPhoto = photo;
                    break;
                }
            }
        }
    }

}

