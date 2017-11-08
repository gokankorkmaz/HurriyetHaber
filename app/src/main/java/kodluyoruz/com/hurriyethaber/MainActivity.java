package kodluyoruz.com.hurriyethaber;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Toolbar.OnMenuItemClickListener {

    //Fragmentları Listede Tutuyorum
    List<Fragment> fragmentList = new ArrayList<>();

    ViewPager viewPager;
    TabLayout tabLayout;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        //Gelen Veriyi Al
        Bundle bundle = getIntent().getExtras();
        toolbar.setTitle(bundle.getString("toolbarTitle"));


        toolbar.inflateMenu(R.menu.menu_main);
        toolbar.setOnMenuItemClickListener(this);


        toolbar.setTitleTextColor(Color.WHITE);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);

        tabLayout.setupWithViewPager(viewPager);

        setFragmentData(new FragmentOne());
        setFragmentData(new FragmentSecond());

        ViewPagerAdaptor viewPagerAdaptor = new ViewPagerAdaptor(getSupportFragmentManager(), fragmentList);

        viewPager.setAdapter(viewPagerAdaptor);

        setFragmentIcon();


    }


    public void setFragmentIcon() {
        tabLayout.getTabAt(0).setIcon(android.R.drawable.ic_input_get);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_favoriler);

    }

    public void setFragmentData(Fragment addFragment) {
        fragmentList.add(addFragment);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.edit:

                Intent intent = new Intent(MainActivity.this, NewActi.class);
                intent.putExtra("toolbar", toolbar.getTitle());
                startActivity(intent);

                break;
        }
        return false;
    }
}
