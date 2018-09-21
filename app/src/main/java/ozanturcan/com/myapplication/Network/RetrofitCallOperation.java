package ozanturcan.com.myapplication.Network;

import java.util.List;

import androidx.annotation.NonNull;
import ozanturcan.com.myapplication.Modal.Album;
import ozanturcan.com.myapplication.Modal.Comment;
import ozanturcan.com.myapplication.Modal.ObservableObjects.AlbumObservable;
import ozanturcan.com.myapplication.Modal.ObservableObjects.CommentObservable;
import ozanturcan.com.myapplication.Modal.ObservableObjects.TodoObervable;
import ozanturcan.com.myapplication.Modal.Photo;
import ozanturcan.com.myapplication.Modal.ObservableObjects.PhotosObservable;
import ozanturcan.com.myapplication.Modal.Post;
import ozanturcan.com.myapplication.Modal.ObservableObjects.PostObservable;
import ozanturcan.com.myapplication.Modal.TaskTodo;
import ozanturcan.com.myapplication.Modal.User;
import ozanturcan.com.myapplication.Modal.ObservableObjects.UserObservable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitCallOperation {
    private JSONPlaceholderApi service = JsonPlaceholderClient.getRetrofitBase().create(JSONPlaceholderApi.class);
    AlbumObservable albumOperation = AlbumObservable.getInstance();
    PhotosObservable photosObservable = PhotosObservable.getInstance();
    PostObservable postObservable = PostObservable.getInstance();
    UserObservable userObservable = UserObservable.getInstance();
    CommentObservable commentObservable = CommentObservable.getInstance();
    TodoObervable todoObervable = TodoObervable.getInstance();
    private void getPhoto() {
        Call<List<Photo>> call = service.GetAllPhotos();
        call.enqueue(new Callback<List<Photo>>() {
            @Override
            public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {
                if (response.isSuccessful()) {
                    if (response.code() == 200) {
                        if (checkResponse(response)) {
                            albumOperation.setPhotoList(response.body());
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Photo>> call, Throwable t) {
//                Toast.makeText(getContext(), "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getAlbums() {

        Call<List<Album>> call = service.GetAllAlbums();
        call.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                if (checkResponse(response)) {
                    albumOperation.setAlbumList(response.body());
                    getPhoto();
                }
            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {
//                Toast.makeText(getContext(), "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getPost() {

        Call<List<Post>> call = service.GetAllPosts();
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (checkResponse(response)) {
                    postObservable.setPostList(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
//                Toast.makeText(getContext(), "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getUser() {

        Call<List<User>> call = service.GetAllUsers();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (checkResponse(response)) {
                    userObservable.setUserList(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
//                Toast.makeText(getContext(), "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getComment() {

        Call<List<Comment>> call = service.GetAllComment();
        call.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                if (checkResponse(response)) {
                    commentObservable.setCommentList(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
//                Toast.makeText(getContext(), "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getTasks() {

        Call<List<TaskTodo>> call = service.GetAllTaskTodo();
        call.enqueue(new Callback<List<TaskTodo>>() {
            @Override
            public void onResponse(Call<List<TaskTodo>> call, Response<List<TaskTodo>> response) {
                if (checkResponse(response)) {
                    todoObervable.setTaskTodoList(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<TaskTodo>> call, Throwable t) {
//                Toast.makeText(getContext(), "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getPhotoListFromAlbum(String albumID) {
        Call<List<Photo>> call = service.getPhotosFromAlbum(albumID);
        call.enqueue(new Callback<List<Photo>>() {
            @Override
            public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {
                if (checkResponse(response)) {
                    photosObservable.setPhotoList(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Photo>> call, Throwable t) {
//                Toast.makeText(getContext(), "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getCommentListFromPost(String postID) {
        Call<List<Comment>> call = service.getCommentsFromPostID(postID);
        call.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                if (checkResponse(response)) {
                    commentObservable.setCommentList(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
//                Toast.makeText(getContext(), "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean checkResponse(@NonNull Response response) {
        boolean result = false;
        if (response.isSuccessful()) {
            if (response.code() == 200) {
                result = true;
            }
        }
        return result;
    }
}
