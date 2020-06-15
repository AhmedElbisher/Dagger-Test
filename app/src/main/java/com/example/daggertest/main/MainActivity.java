package com.example.daggertest.main;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.example.daggertest.BaseActivity;
import com.example.daggertest.R;
import com.example.daggertest.main.posts.PostsFragment;
import com.google.android.material.navigation.NavigationView;

import dagger.Provides;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "MainActivity";
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        getSupportFragmentManager().beginTransaction()
//                .replace(R.id.container,new PostsFragment())
//                .commit();
        navigationView = findViewById(R.id.nav_view);
        drawerLayout = findViewById(R.id.drawer_layout);
        NavController navController = Navigation.findNavController(this,R.id.nav_host);
        NavigationUI.setupActionBarWithNavController(this,navController,drawerLayout);
        NavigationUI.setupWithNavController(navigationView,navController);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menua,menu);
        return super.onCreateOptionsMenu(menu);
    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.logOut:
                sessionManager.logout();
                break;
            case android.R.id.home:
                if(drawerLayout.isDrawerOpen(GravityCompat.START)){
                    drawerLayout.closeDrawer(GravityCompat.START);
                    return true;
                }else{
                    return false;
                }

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.nav_posts:
                if(Navigation.findNavController(this,R.id.nav_host).getCurrentDestination().getId() != R.id.nav_posts){
                    Navigation.findNavController(this,R.id.nav_host).navigate(R.id.postsFragment);
                }
                break;
            case R.id.nav_profile:
                NavOptions navOptions = new NavOptions.Builder()
                        .setPopUpTo(R.id.navigationgraph,true).build();
                Navigation.findNavController(this,R.id.nav_host).navigate(R.id.profileFragment,null,navOptions);
                break;
        }
        menuItem.setChecked(true);
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(Navigation.findNavController(this,R.id.nav_host),drawerLayout);
    }
}