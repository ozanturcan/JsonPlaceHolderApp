package ozanturcan.com.myapplication.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Observable;
import java.util.Observer;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ozanturcan.com.myapplication.Adapter.RecyclerViewAdapterPostDetail;
import ozanturcan.com.myapplication.Modal.ObservableObjects.CommentObservable;
import ozanturcan.com.myapplication.R;
import ozanturcan.com.myapplication.Util.StringUtilities;

public class PostDetailFragment extends Fragment implements Observer {
    private RecyclerView recyclerviewFeed;
    private RecyclerViewAdapterPostDetail recyclerViewAdapter;
    private View RootView;
    private CommentObservable commentObservable;
    private TextView textViewUsername;
    private TextView textViewCount;
    private TextView textViewBody;
    private TextView textViewTitle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        RootView = inflater.inflate(R.layout.fragment_post_detail, container, false);

        View includeView = RootView.findViewById(R.id.cardview_post_main);
        textViewUsername = includeView.findViewById(R.id.textView_username);
        textViewCount = includeView.findViewById(R.id.textView_count);
        textViewBody = includeView.findViewById(R.id.textView_description);
        textViewTitle = includeView.findViewById(R.id.textView_title);


        recyclerviewFeed = (RecyclerView) RootView.findViewById(R.id.recyclerview_feed_post_detail);
        recyclerviewFeed.setLayoutManager(new GridLayoutManager(RootView.getContext(), 1));
        commentObservable = CommentObservable.getInstance();
        commentObservable.addObserver(this);

        fillCardView();
        return RootView;
    }

    private void fillCardView() {
        textViewBody.setText(StringUtilities.convertToFirtIndexUpperCase(getArguments().getString("Body")));
        textViewCount.setText(StringUtilities.convertToFirtIndexUpperCase(getArguments().getString("CommentCount")));
        textViewTitle.setText(StringUtilities.convertToFirtIndexUpperCase(getArguments().getString("Title")));
        textViewUsername.setText(StringUtilities.convertToFirtIndexUpperCase(getArguments().getString("Username")));
    }

    public void fillComment(CommentObservable commentObservable) {
        recyclerViewAdapter = new RecyclerViewAdapterPostDetail(commentObservable.getCommentList());
        recyclerviewFeed.setAdapter(recyclerViewAdapter);

    }

    @Override
    public void update(Observable observable, Object o) {
        if (observable != null && observable instanceof CommentObservable) {
            fillComment(commentObservable);
        }
    }
}
