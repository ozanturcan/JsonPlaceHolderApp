package ozanturcan.com.myapplication;


import android.app.FragmentTransaction;
import android.os.Bundle;

import com.stfalcon.bottomtablayout.BottomTabLayout;

import androidx.appcompat.app.AppCompatActivity;
import ozanturcan.com.myapplication.Fragment.AlbumFragment;
import ozanturcan.com.myapplication.Fragment.PostFragment;
import ozanturcan.com.myapplication.Fragment.TaskFragment;
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
        final FragmentTransaction transaction  = getFragmentManager().beginTransaction();

        switch (id) {
            case R.id.menu_button1:
                transaction.replace(R.id.container, new AlbumFragment()).commit();
                transaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left);
                getSupportActionBar().setTitle("Albums");
                break;
            case R.id.menu_button2:
                transaction.replace(R.id.container, new PostFragment()).commit();
                transaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left);
                getSupportActionBar().setTitle("Posts");
                break;
            case R.id.menu_button3:
                transaction.replace(R.id.container, new TaskFragment()).commit();
                transaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left);
                getSupportActionBar().setTitle("Todos");
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
