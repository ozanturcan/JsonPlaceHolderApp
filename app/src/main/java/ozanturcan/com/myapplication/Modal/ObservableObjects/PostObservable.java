package ozanturcan.com.myapplication.Modal.ObservableObjects;

import java.util.List;
import java.util.Observable;

import ozanturcan.com.myapplication.Modal.Comment;
import ozanturcan.com.myapplication.Modal.Post;
import ozanturcan.com.myapplication.Modal.User;

public class PostObservable extends Observable {
    private static PostObservable USER_REPO_INSTANCE = null;
    private static Object mLock = new Object();
    private List<Post> postList;
    private List<User> userList;
    private List<Comment> commentList;
    private UserObservable userObservable = UserObservable.getInstance();
    private CommentObservable commentObservable = CommentObservable.getInstance();

    public PostObservable() {

    }

    /* get Singleton instance of UserRepository  */
    public static PostObservable getInstance() {
        synchronized (mLock) {
            if (USER_REPO_INSTANCE == null)
                USER_REPO_INSTANCE = new PostObservable();
            return USER_REPO_INSTANCE;
        }
    }

    private void mergerUserFromPost() {
        userList = userObservable.getUserList();
        for (Post post : postList) {
            for (User user : userList) {
                if (post.getUserId() == user.id) {
                    post.userName = user.name;
                    break;
                }
            }
        }
    }

    private void mergeCommentCountFromPost() {
        commentList = commentObservable.getCommentList();
        for (Post post : postList) {
            int count = 0;
            for (Comment comment : commentList) {
                if (post.getId() == comment.postId) {
                    count++;
                }
            }
            post.setCommentCount(count);
        }
    }

    public List<Post> getPostList() {
        return postList;
    }

    public void setPostList(List<Post> postList) {
        this.postList = postList;
        mergeCommentCountFromPost();
        mergerUserFromPost();
        setChanged();
        notifyObservers();
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }
}
