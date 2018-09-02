package ozanturcan.com.myapplication.Modal;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class JsonPlaceholderClient {

    private static Retrofit retrofit = null;
    private static String base_URL = "https://jsonplaceholder.typicode.com/";
    private static Retrofit GetRetrofitBase() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(base_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(new OkHttpClient())
                    .build();
            return retrofit;

        }
        return retrofit;
    }


    ///Post Activity
    public static void GetAllPost() {
        GetRetrofitBase();
        JSONPlaceholderService service = retrofit.create(JSONPlaceholderService.class);
        Call<List<Post>> repos = service.GetAllPosts();
        repos.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (response != null) {
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

            }
        });
    }
    public static void GetAllPostFromUser(String UserID) {
        GetRetrofitBase();
        JSONPlaceholderService service = retrofit.create(JSONPlaceholderService.class);
        Call<List<Post>> repos = service.getAllPostFromUser(UserID);
        repos.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (response != null) {
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

            }
        });
    }
    public static void GetSelectedPost(String postID) {
        GetRetrofitBase();
        JSONPlaceholderService service = retrofit.create(JSONPlaceholderService.class);
        Call<Post> repos = service.getSelectedPost(postID);
        repos.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (response != null) {
                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {

            }
        });
    }
    public static void GetAllCommentFromPostID(String UserID) {
        GetRetrofitBase();
        JSONPlaceholderService service = retrofit.create(JSONPlaceholderService.class);
        Call<List<Comment>> repos = service.getCommentsFromPostID("1");
        repos.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                if (response != null) {
                }
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {

            }
        });
    }



}
