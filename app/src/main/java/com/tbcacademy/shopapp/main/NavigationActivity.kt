package com.tbcacademy.shopapp.main

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.tbcacademy.shopapp.R
import com.tbcacademy.shopapp.databinding.ActivityNavigationBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class NavigationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNavigationBinding

    lateinit var bottomNav: BottomNavigationView
    lateinit var navController: NavController
    lateinit var drawerLayout: DrawerLayout
    private lateinit var appBarConfiguration: AppBarConfiguration


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bottomNav = binding.bottomNavigation

        navController = findNavController(R.id.hostFragment)
        setupBottomNavigation()

        //openDrawerMenu()


        drawerLayout = binding.drawerLayout

        setSupportActionBar(binding.toolbar)

        appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout)
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)

        //NavigationUI.setupWithNavController(navigation_view,navController)
        NavigationUI.setupWithNavController(binding.drawerNavigation, navController)

    }

    /* override fun onCreateOptionsMenu(menu: Menu?): Boolean {

         val inflater = menuInflater
         inflater.inflate(R.menu.toolbar_menu,menu)
         val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
         val searchItem = menu?.findItem(R.id.search)
         val searchView = searchItem?.actionView as SearchView

         searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
         searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
             override fun onQueryTextSubmit(query: String?): Boolean {
                 searchView.clearFocus()
                 searchView.setQuery("",false)
                 searchView.onActionViewCollapsed()

                 Toast.makeText(this@NavigationActivity,"Searching $query..",Toast.LENGTH_SHORT).show()

                 return true
             }

             override fun onQueryTextChange(newText: String?): Boolean {
                 Toast.makeText(this@NavigationActivity,"Searching $newText..",Toast.LENGTH_SHORT).show()
                 return false
             }

         })

         return true
     }
 */

    override fun onSupportNavigateUp(): Boolean {
        showBottomNavigationView()
        return NavigationUI.navigateUp(navController, appBarConfiguration)

    }

    private fun setupBottomNavigation() {
        bottomNav.setupWithNavController(navController)


    }


    fun hideBottomNavigationView() {
        binding.bottomNavigation.clearAnimation()
        binding.bottomNavigation.animate().translationY(binding.bottomNavigation.height.toFloat())
    }

    private fun showBottomNavigationView() {
        binding.bottomNavigation.clearAnimation()
        binding.bottomNavigation.animate().translationY(0f).duration = 300

    }

    fun openDrawerMenu() {
        binding.imageButton.setOnClickListener {
            binding.drawerLayout.openDrawer(Gravity.RIGHT)
        }


        /* val toggle = object : ActionBarDrawerToggle(
             this, binding.drawerLayout, R.string.drawer_open,
             R.string.drawer_close
         ) {
             override fun onOptionsItemSelected(item: MenuItem?): Boolean {
                 if (item != null && item.getItemId() === android.R.id.home) {
                     if (binding.drawerLayout.isDrawerOpen(Gravity.RIGHT)) {
                         binding.drawerLayout.closeDrawer(Gravity.RIGHT)
                     } else {
                         binding.drawerLayout.openDrawer(Gravity.RIGHT)
                     }
                 }
                 return false
             }
         }*/
    }


}