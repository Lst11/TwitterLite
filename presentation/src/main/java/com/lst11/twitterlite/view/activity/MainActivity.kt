package com.lst11.twitterlite.view.activity

import android.os.Bundle
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

    // Menu positions of buttons on the toolbar
    private val createPost = 0
    private val profileMenuPosition = 1
    private val postsMeuPosition = 3
    private val followingMenuPosition = 4
    private val followersMenuPosition = 5


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val createPostButton = findViewById<FloatingActionButton>(R.id.create_post_button)

        createPostButton.setOnClickListener {
            setListenerToTheCreatePostButton()
        }

        setUpToolbar()
    }

    private fun setListenerToTheCreatePostButton() {
        findViewById<Toolbar>(R.id.toolbar).title = changeTitleOnClick(createPost)

        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        val fragmentCreatePost = FragmentCreatePost()
        transaction.replace(R.id.layoutMain, fragmentCreatePost)
        transaction.commit()
    }

    private fun setUpToolbar() {
        val accountHeader: AccountHeader = AccountHeaderBuilder()
            .withActivity(this)
            .withHeaderBackground(R.drawable.user_default)
            .withTranslucentStatusBar(true)
            .build()

        val toolbar = findViewById<Toolbar>(R.id.toolbar)

        setUpMenu(toolbar, accountHeader)
    }

    private fun setUpMenu(
        toolbar: Toolbar,
        accountHeader: AccountHeader
    ) {
        val itemProfile: PrimaryDrawerItem =
            createPrimaryDrawerItem(resources.getString(R.string.profile_menu_item))

        val itemHome: SecondaryDrawerItem =
            createSecondaryDrawerItem(
                resources.getString(R.string.posts_menu_item),
                FontAwesome.Icon.faw_comment_alt1
            )

        val itemFollowing: SecondaryDrawerItem =
            createSecondaryDrawerItem(
                resources.getString(R.string.following_menu_item),
                FontAwesome.Icon.faw_user_friends
            )

        val itemFollowers: SecondaryDrawerItem =
            createSecondaryDrawerItem(
                resources.getString(R.string.followers_menu_item),
                FontAwesome.Icon.faw_users
            )

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
                    startFragmentOnClick(position)
                    toolbar.title = changeTitleOnClick(position)
                    return true
                }

            })
            .build()

        driver.addStickyFooterItem(PrimaryDrawerItem().withName("Support"))
    }

    private fun changeTitleOnClick(position: Int): String {

        return when (position) {
            createPost -> resources.getString(R.string.new_post_item)
            profileMenuPosition -> resources.getString(R.string.profile_menu_item)
            postsMeuPosition -> resources.getString(R.string.posts_menu_item)
            followingMenuPosition -> resources.getString(R.string.following_menu_item)
            followersMenuPosition -> resources.getString(R.string.followers_menu_item)
            else -> resources.getString(R.string.app_name)
        }
    }

    private fun createSecondaryDrawerItem(
        text: String,
        image: FontAwesome.Icon
    ): SecondaryDrawerItem {
        return SecondaryDrawerItem()
            .withName(text)
            .withIcon(image)
    }

    private fun createPrimaryDrawerItem(text: String) = PrimaryDrawerItem()
        .withName(text)

    private fun startFragmentOnClick(position: Int) {
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()

        when (position) {
            profileMenuPosition -> {
                val fragmentProfile = FragmentProfile()
                transaction.replace(R.id.layoutMain, fragmentProfile)
            }

            postsMeuPosition -> {
                val fragmentHome = FragmentPosts()
                transaction.replace(R.id.layoutMain, fragmentHome)
            }
            followingMenuPosition -> {
                val fragmentFollowing = FragmentFollowing()
                transaction.replace(R.id.layoutMain, fragmentFollowing)

            }
            followersMenuPosition -> {
                val fragmentFollowers = FragmentFollowers()
                transaction.replace(R.id.layoutMain, fragmentFollowers)
            }
        }

        transaction.commit()
    }
}
