package ozanturcan.com.myapplication;

import android.os.Bundle;

import com.stfalcon.bottomtablayout.BottomTabLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import ozanturcan.com.myapplication.Modal.JsonPlaceholderClient;

public class MainActivity extends AppCompatActivity {

    private BottomTabLayout bottomTabLayout;
    private int container;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Setup button tab layout
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
        bottomTabLayout.setSelectedTab(R.id.menu_button2);
        //enable indicator
        bottomTabLayout.setIndicatorVisible(true);
        //indicator height
        bottomTabLayout.setIndicatorHeight(getResources().getDimension(R.dimen.indicator_height));
        //indicator color
        bottomTabLayout.setIndicatorColor(R.color.colorSecondary);
        //indicator line color
        bottomTabLayout.setIndicatorLineColor(R.color.colorPrimaryDark);

        //setup bubble style --- using for match todo action
//        bottomTabLayout.setTabBubbleColor(ContextCompat.getColor(this, R.color.blue));
//        bottomTabLayout.setTabBubblePadding(0, 0, 0, 0);
//        bottomTabLayout.setTabBubbleTextStyle(R.style.TextWhite);
//
//        //show bubble
//        bottomTabLayout.showTabBubbleCount(R.id.menu_button1, 3);


        //
        JsonPlaceholderClient.CreateJSONPlaceholderService(this);
    }
    /**
     * Show fragment in container
     *
     * @param id Menu item res id
     */
    public void switchFragment(int id) {
        Fragment fragment = null;
        switch (id) {
            case R.id.menu_button1:
//                fragment = ColoredFragment.newInstance(R.color.blue, "Fragment 1");
                break;
            case R.id.menu_button2:
//                fragment = ColoredFragment.newInstance(R.color.green, "Fragment 2");
                break;
            case R.id.menu_button3:
//                fragment = ColoredFragment.newInstance(R.color.pink, "Fragment 3");
                break;

        }
        if (fragment != null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(container, fragment);
            transaction.commit();
        }
    }
}
