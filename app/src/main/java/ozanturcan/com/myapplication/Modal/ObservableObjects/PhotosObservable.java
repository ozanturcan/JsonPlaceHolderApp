package ozanturcan.com.myapplication.Modal.ObservableObjects;

import java.util.List;
import java.util.Observable;

import ozanturcan.com.myapplication.Modal.Photo;

public class PhotosObservable extends Observable {
    private static PhotosObservable USER_REPO_INSTANCE = null;
    private static Object mLock = new Object();
    private List<Photo> photoList;

    public PhotosObservable() {

    }

    /* get Singleton instance of UserRepository  */
    public static PhotosObservable getInstance() {
        synchronized (mLock) {
            if (USER_REPO_INSTANCE == null)
                USER_REPO_INSTANCE = new PhotosObservable();
            return USER_REPO_INSTANCE;
        }
    }

    public List<Photo> getPhotoList() {
        return photoList;
    }

    public void setPhotoList(List<Photo> photoList) {
        this.photoList = photoList;
        setChanged();
        notifyObservers();
    }
}
