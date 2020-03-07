package com.lst11.twitterlite.view.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.lst11.twitterlite.R
import com.lst11.twitterlite.view.fragment.*
import com.mikepenz.iconics.typeface.library.fontawesome.FontAwesome
import com.mikepenz.materialdrawer.AccountHeader
import com.mikepenz.materialdrawer.AccountHeaderBuilder
import com.mikepenz.materialdrawer.Drawer
import com.mikepenz.materialdrawer.DrawerBuilder
import com.mikepenz.materialdrawer.model.DividerDrawerItem
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val createPostButton = findViewById<FloatingActionButton>(R.id.create_post_button)

        createPostButton.setOnClickListener {
            val manager = supportFragmentManager
            val transaction = manager.beginTransaction()
            val fragmentCreatePost = FragmentCreatePost()
            transaction.replace(R.id.layoutMain, fragmentCreatePost)
            transaction.commit()
        }

        val accountHeader: AccountHeader = AccountHeaderBuilder()
            .withActivity(this)
            .withHeaderBackground(R.drawable.user_default)
            .withTranslucentStatusBar(true)
            .build()

        val toolbar = findViewById<Toolbar>(R.id.toolbar)

        val itemProfile: PrimaryDrawerItem = PrimaryDrawerItem()
            .withName("Profile")

        val itemHome: SecondaryDrawerItem = SecondaryDrawerItem()
            .withName("Posts")
            .withIcon(FontAwesome.Icon.faw_comment_alt1)

        val itemFollowing: SecondaryDrawerItem = SecondaryDrawerItem()
            .withName("Following")
            .withIcon(FontAwesome.Icon.faw_user_friends)

        val itemFollowers: SecondaryDrawerItem = SecondaryDrawerItem()
            .withName("Followers")
            .withIcon(FontAwesome.Icon.faw_users)

        val driver = DrawerBuilder().withActivity(this)
            .withToolbar(toolbar)
            .withAccountHeader(accountHeader)
            .addDrawerItems(
                itemProfile,
                DividerDrawerItem(),
                itemHome,
                itemFollowing,
                itemFollowers
            )
            .withOnDrawerItemClickListener(object : Drawer.OnDrawerItemClickListener {
                override fun onItemClick(
                    view: View?,
                    position: Int,
                    drawerItem: IDrawerItem<*>
                ): Boolean {
                    Log.d("DRAWER", "Clicked on position $position")
                    startFragment(position)
                    return true
                }

            })
            .build()

        driver.addStickyFooterItem(PrimaryDrawerItem().withName("Support"))
    }

    private fun startFragment(position: Int) {

        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()

        when (position) {
            1 -> {
                val fragmentProfile = FragmentProfile()
                transaction.replace(R.id.layoutMain, fragmentProfile)
            }

            3 -> {
                val fragmentHome = FragmentPosts()
                transaction.replace(R.id.layoutMain, fragmentHome)
            }
            4 -> {
                val fragmentFollowing = FragmentFollowing()
                transaction.replace(R.id.layoutMain, fragmentFollowing)

            }
            5 -> {
                val fragmentFollowers = FragmentFollowers()
                transaction.replace(R.id.layoutMain, fragmentFollowers)
            }
        }

        transaction.commit()
    }
}
