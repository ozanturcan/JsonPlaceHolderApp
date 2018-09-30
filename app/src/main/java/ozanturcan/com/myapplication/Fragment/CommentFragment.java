package ozanturcan.com.myapplication.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Observable;
import java.util.Observer;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ozanturcan.com.myapplication.Adapter.CommentRVAdapter;
import ozanturcan.com.myapplication.Modal.ObservableObjects.CommentObservable;
import ozanturcan.com.myapplication.R;
import ozanturcan.com.myapplication.Util.StringUtilities;

public class CommentFragment extends BaseFragment implements Observer {
    private RecyclerView recyclerviewFeed;
    private CommentRVAdapter recyclerViewAdapter;
    private View rootView;
    private CommentObservable commentObservable;
    private TextView textViewUsername;
    private TextView textViewCount;
    private TextView textViewBody;
    private TextView textViewTitle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_post_detail, container, false);
        rootView.findViewById(R.id.loading_album).setVisibility(View.VISIBLE);
        View includeView = rootView.findViewById(R.id.cardview_post_main);
        textViewUsername = includeView.findViewById(R.id.textView_username);
        textViewCount = includeView.findViewById(R.id.textView_count);
        textViewBody = includeView.findViewById(R.id.textView_description);
        textViewTitle = includeView.findViewById(R.id.textView_title);


        recyclerviewFeed = (RecyclerView) rootView.findViewById(R.id.recyclerview_feed_post_detail);
        recyclerviewFeed.setLayoutManager(new GridLayoutManager(rootView.getContext(), 1));
        commentObservable = CommentObservable.getInstance();
        commentObservable.addObserver(this);

        fillCardView();
        return rootView;
    }

    private void fillCardView() {
        textViewBody.setText(StringUtilities.convertToFirtIndexUpperCase(getArguments().getString("Body")));
        textViewCount.setText(StringUtilities.convertToFirtIndexUpperCase(getArguments().getString("CommentCount")));
        textViewTitle.setText(StringUtilities.convertToFirtIndexUpperCase(getArguments().getString("Title")));
        textViewUsername.setText(StringUtilities.convertToFirtIndexUpperCase(getArguments().getString("Username")));
    }

    public void fillComment(CommentObservable commentObservable) {
        recyclerViewAdapter = new CommentRVAdapter(commentObservable.getCommentList());
        recyclerviewFeed.setAdapter(recyclerViewAdapter);
        rootView.findViewById(R.id.loading_album).setVisibility(View.GONE);
        commentObservable.deleteObserver(this);

    }

    @Override
    public void update(Observable observable, Object o) {
        if (observable != null && observable instanceof CommentObservable) {
            fillComment(commentObservable);
        }
    }
    @Override
    public void checkConnection() {
        super.checkConnection();
    }

    private void getCommentsFunction(){

    }
}
