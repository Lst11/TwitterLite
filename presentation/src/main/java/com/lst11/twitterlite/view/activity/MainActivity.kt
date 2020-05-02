package com.lst11.twitterlite.view.activity

import android.content.*
import android.net.wifi.WifiManager
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.lst11.twitterlite.R
import com.lst11.twitterlite.service.WifiService
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
    private val postsMenuPosition = 3
    private val followingMenuPosition = 4
    private val followersMenuPosition = 5
    private val supportMenuPosition = -1

    var mService: WifiService? = null
    var isBound = false
    private val sConn: ServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(className: ComponentName, binder: IBinder) {
            val localBinder: WifiService.LocalBinder = binder as WifiService.LocalBinder
            mService = localBinder.getService()
            isBound = true
            Log.d("AAA", "onServiceConnected")
        }

        override fun onServiceDisconnected(className: ComponentName) {
            Log.d("AAA", "onServiceDisconnected")
            isBound = false
        }
    }

    lateinit var localBroadcastManager: LocalBroadcastManager
    var wifiStatusButton: ImageButton? = null

    private val listener: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            if (intent.action == "NETWORK_AVAILABLE_YES") {
                changeWifiStatus("ON")
                Log.d("AAA", "Status changed to: YES")
            } else {
                changeWifiStatus("OFF")
            }
        }
    }

    fun changeWifiStatus(message: String?) {
        val image: Int = when (message) {
            "ON" -> R.drawable.ic_wifi_on
            "OFF" -> R.drawable.ic_wifi_off
            else -> R.drawable.ic_wifi_off
        }

        wifiStatusButton?.setImageResource(image)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val createPostButton = findViewById<FloatingActionButton>(R.id.post_action_button)

        createPostButton.setOnClickListener {
            setListenerToTheCreatePostButton()
        }

        wifiStatusButton = findViewById(R.id.wifi_button)

        localBroadcastManager = LocalBroadcastManager.getInstance(this)

        wifiStatusButton?.setOnClickListener {
            val wifiManager: WifiManager =
                applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
            mService?.changeWifiStatus(wifiManager)
            Log.d("AAA", "onClick changeWifiStatus")
        }

        setUpToolbar()
        startFragment(postsMenuPosition)
    }

    private fun setListenerToTheCreatePostButton() {
        findViewById<Toolbar>(R.id.toolbar).title = changeTitleOnClick(createPost)

        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()

        startFragment(createPost)
        transaction.commit()
    }

    private fun setUpToolbar() {
        val accountHeader: AccountHeader = AccountHeaderBuilder()
            .withActivity(this)
            .withHeaderBackground(R.drawable.user_default)
            .withTranslucentStatusBar(true)
            .build()

        setUpWifiButton()
        setUpMenu(findViewById(R.id.toolbar), accountHeader)
    }

    private fun setUpWifiButton() {
        wifiStatusButton?.setOnClickListener {
            val wifiManager: WifiManager =
                applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
            mService?.changeWifiStatus(wifiManager)
            Log.d("aaa", "onClick changeWifiStatus")
        }
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
                    startFragment(position)
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
            postsMenuPosition -> resources.getString(R.string.posts_menu_item)
            followingMenuPosition -> resources.getString(R.string.following_menu_item)
            followersMenuPosition -> resources.getString(R.string.followers_menu_item)
            supportMenuPosition -> resources.getString(R.string.support_menu_item)
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

    private fun startFragment(position: Int) {
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()

        when (position) {
            createPost -> {
                val fragmentCreatePost = FragmentCreatePost()
                transaction.replace(R.id.layoutMain, fragmentCreatePost)
            }

            profileMenuPosition -> {
                val fragmentProfile = FragmentProfile()
                transaction.replace(R.id.layoutMain, fragmentProfile)
            }

            postsMenuPosition -> {
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

            supportMenuPosition -> {
                val fragmentSupport = FragmentSupport()
                transaction.replace(R.id.layoutMain, fragmentSupport)
            }
        }
        resetActionButton(position)

        transaction.commit()
    }

    private fun resetActionButton(position: Int) {
        val button = findViewById<View>(R.id.post_action_button)

        when (position) {
            createPost -> button.visibility = View.GONE
            else -> button.visibility = View.VISIBLE
        }
    }

    fun backToMain() {
        findViewById<Toolbar>(R.id.toolbar).title = changeTitleOnClick(postsMenuPosition)

        val transaction = supportFragmentManager.beginTransaction()
        startFragment(postsMenuPosition)
        transaction.commit()
    }

    override fun onResume() {
        super.onResume()
        var intent: Intent = object : Intent(this, WifiService::class.java) {}
        bindService(intent, sConn, BIND_AUTO_CREATE)

        val intentFilter = IntentFilter()
        intentFilter.addAction("NETWORK_AVAILABLE_YES")
        intentFilter.addAction("NETWORK_AVAILABLE_NO")

        LocalBroadcastManager.getInstance(this).registerReceiver(
            listener,
            intentFilter
        )
    }

    override fun onPause() {
        super.onPause()
        if (!isBound) return
        localBroadcastManager.unregisterReceiver(listener)
        unbindService(sConn)
        isBound = false
    }
}
