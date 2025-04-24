package dp.wang;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNav;
    Fragment selectedFragment = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNav = findViewById(R.id.bottom_nav);
        bottomNav.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                int mnuItem = item.getItemId();
                if(mnuItem == R.id.botnav_mnu_home)
                {
                    selectedFragment = new HomeFragment();
                    Toast.makeText(MainActivity.this, "Home", Toast.LENGTH_SHORT).show();
                }
                else
                if(mnuItem == R.id.botnav_mnu_profile)
                {
                    selectedFragment = new ProfileFragment();
                    Toast.makeText(MainActivity.this, "Profile", Toast.LENGTH_SHORT).show();
                }
                else
                if(mnuItem == R.id.botnav_mnu_search)
                {
                    selectedFragment = new SearchFragment();
                    Toast.makeText(MainActivity.this, "Search", Toast.LENGTH_SHORT).show();
                }
                if (selectedFragment != null) {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.frag, selectedFragment) // fragment_container là id của FrameLayout
                            .commit();
                }
            }
        });
    }

}