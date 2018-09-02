package ozanturcan.com.myapplication.Modal;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JSONPlaceholderService {

    ///Post Servises

    @GET("posts")
    Call<List<Post>> GetAllPosts();

    @GET("posts/{postID}")
    Call<Post> getSelectedPost(@Path("postID") String postID);

    @GET("posts")
    Call<List<Post>> getAllPostFromUser(@Query("userId") String userID);

    /// Comment Services

    @GET("comments")
    Call<List<Comment>> GetAllComment();

    @GET("albums/{AlbumID}")
    Call<Comment> getSelectedComment(@Path("CommentID") String CommentID);

    @GET("albums")
    Call<List<Comment>> getCommentsFromPostID(@Query("postId") String postID);

    ///Album Servises
    @GET("albums")
    Call<List<Album>> GetAllAlbums();

    @GET("albums/{AlbumID}")
    Call<Album> getSelectedAlbum(@Path("AlbumID") String AlbumID);

    @GET("albums")
    Call<List<Album>> getAlbumsFromUser(@Query("userId") String userID);

    @GET("/albums/{albumId}/photos")
    Call<List<Comment>> getAllPhotosFromAlbum(@Path("albumId") String albumId);

    ///Photo Servises
    @GET("photos")
    Call<List<Photo>> GetAllPhotos();

    @GET("photos/{photosID}")
    Call<Photo> getSelectedPhoto(@Path("photosID") String AlbumID);

    @GET("photos")
    Call<List<Photo>> getPhotosFromAlbum(@Query("albumId") String albumID);

    ///TaskTodo Servises

    @GET("todos")
    Call<List<TaskTodo>> GetAllTaskTodo();

    @GET("todos/{todoId}")
    Call<TaskTodo> getSelectedTaskTodo(@Path("todoId") String todosID);

    @GET("todos")
    Call<List<TaskTodo>> getAllTaskTodoFromUser(@Query("userId") String userID);


    ///User Servises

    @GET("users")
    Call<List<TaskTodo>> GetAllusers();

    @GET("users/{usersId}")
    Call<TaskTodo> getSelectedUser(@Path("usersId") String usersID);

}
