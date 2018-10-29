package ozanturcan.com.myapplication;


import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.stfalcon.bottomtablayout.BottomTabLayout;

import androidx.appcompat.app.AppCompatActivity;
import ozanturcan.com.myapplication.fragment.AlbumFragment;
import ozanturcan.com.myapplication.fragment.PostFragment;
import ozanturcan.com.myapplication.fragment.TaskFragment;
import ozanturcan.com.myapplication.network.RetrofitCallOperation;

public class MainActivity extends AppCompatActivity {

    private BottomTabLayout bottomTabLayout;
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
        switch (id) {
            case R.id.menu_button1:
                replaceFragment(new AlbumFragment(), "Albums");
                break;
            case R.id.menu_button2:
                replaceFragment(new PostFragment(), "Posts");
                break;
            case R.id.menu_button3:
                replaceFragment(new TaskFragment(), "Todos");
                break;
        }
    }

    public void replaceFragment(Fragment fragment, String title){
        final FragmentTransaction transaction  = getFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment).commit();
        transaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left);
        getSupportActionBar().setTitle(title);
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
