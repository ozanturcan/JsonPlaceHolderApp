package ozanturcan.com.myapplication;


import android.app.FragmentManager;
import android.os.Bundle;

import com.stfalcon.bottomtablayout.BottomTabLayout;

import androidx.appcompat.app.AppCompatActivity;
import ozanturcan.com.myapplication.Fragment.AlbumStreamFragment;
import ozanturcan.com.myapplication.Fragment.PostStreamFragment;
import ozanturcan.com.myapplication.Fragment.TodoStreamFragment;
import ozanturcan.com.myapplication.Network.RetrofitCallOperation;

public class MainActivity extends AppCompatActivity {

    private BottomTabLayout bottomTabLayout;
    private int container;
    private RetrofitCallOperation retrofitCallOperation = new RetrofitCallOperation();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setBottomTabLayout();
        retrofitCallOperation.getUser();
        retrofitCallOperation.getComment();
    }


    public void switchFragment(int id) {
        final FragmentManager fragmentManager = getFragmentManager();

        switch (id) {
            case R.id.menu_button1:
                fragmentManager.beginTransaction().replace(R.id.container, new AlbumStreamFragment()).commit();
                getSupportActionBar().setTitle("Album Stream");
                break;
            case R.id.menu_button2:
                fragmentManager.beginTransaction().replace(R.id.container, new PostStreamFragment()).commit();
                getSupportActionBar().setTitle("Post Stream");
                break;
            case R.id.menu_button3:
                fragmentManager.beginTransaction().replace(R.id.container, new TodoStreamFragment()).commit();
                getSupportActionBar().setTitle("Todo Stream");
                break;

        }
    }
    public void setBottomTabLayout(){
        // Setup button tab layout
        bottomTabLayout = (BottomTabLayout) findViewById(R.id.bottomTabLayout);
        //set button text style
        // set buttons from menu resource
        bottomTabLayout.setItems(R.menu.menu_bottom_layout);
        //set on selected tab listener.
        //set button that will be select on start activity
        bottomTabLayout.setListener(new BottomTabLayout.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int id) {
                switchFragment(id);
            }
        });
        //set button that will be select on start activity
        bottomTabLayout.setSelectedTab(R.id.menu_button1);
        //enable indicator
        bottomTabLayout.setIndicatorVisible(true);
        //indicator height
        bottomTabLayout.setIndicatorHeight(getResources().getDimension(R.dimen.indicator_height));
        //indicator color
        bottomTabLayout.setIndicatorColor(R.color.colorSecondary);
        //indicator line color
        bottomTabLayout.setIndicatorLineColor(R.color.colorPrimaryDark);
    }

}
