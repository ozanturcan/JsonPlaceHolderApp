package ozanturcan.com.myapplication.Modal.ObservableObjects;

import java.util.List;
import java.util.Observable;

import ozanturcan.com.myapplication.Modal.User;

public class UserObservable extends Observable {
    private static UserObservable USER_REPO_INSTANCE = null;
    private static Object mLock = new Object();
    private List<User> userList;

    public UserObservable() {

    }

    /* get Singleton instance of UserRepository  */
    public static UserObservable getInstance() {
        synchronized (mLock) {
            if (USER_REPO_INSTANCE == null)
                USER_REPO_INSTANCE = new UserObservable();
            return USER_REPO_INSTANCE;
        }
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
        setChanged();
        notifyObservers();
    }

}
