package ozanturcan.com.myapplication.Modal.ObservableObjects;

import java.util.List;
import java.util.Observable;

import ozanturcan.com.myapplication.Modal.Comment;
import ozanturcan.com.myapplication.Modal.Post;
import ozanturcan.com.myapplication.Modal.User;

public class CommentObservable extends Observable {
    private static CommentObservable USER_REPO_INSTANCE = null;
    private static Object mLock = new Object();
    private List<Post> postList;
    private List<User> userList;
    private List<Comment> commentList;

    public CommentObservable() {

    }

    /* get Singleton instance of UserRepository  */
    public static CommentObservable getInstance() {
        synchronized (mLock) {
            if (USER_REPO_INSTANCE == null)
                USER_REPO_INSTANCE = new CommentObservable();
            return USER_REPO_INSTANCE;
        }
    }

//    private void mergePhotosToAlbum() {
//        for (Album album : albumList) {
//            for (Photo photo : photoList) {
//                if (album.id == photo.albumId) {
//                    album.albumPhoto = photo;
//                    break;
//                }
//            }
//        }
//    }

    public List<Post> getPostList() {
        return postList;
    }

    public void setPostList(List<Post> postList) {
        this.postList = postList;

    }


    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
        setChanged();
        notifyObservers();
    }
}
