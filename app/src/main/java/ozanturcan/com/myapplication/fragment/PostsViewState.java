package ozanturcan.com.myapplication.fragment;

import android.view.View;

import java.util.ArrayList;
import java.util.List;

import ozanturcan.com.myapplication.Modal.Post;
import ozanturcan.com.myapplication.network.Resource;

public class PostsViewState {

    Resource<List<Post>> postListResource;

    public PostsViewState(Resource<List<Post>> postListResource) {
        this.postListResource = postListResource;
    }

    public Integer isPostListLoading(){
        return postListResource.getState() == Resource.State.LOADING ? View.VISIBLE : View.GONE;
    }

    public List<Post> getPostList(){
        return postListResource.getData() == null ? new ArrayList<>() : postListResource.getData();
    }
}
