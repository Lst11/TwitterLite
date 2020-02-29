package com.lst11.twitterlite

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.FirebaseDatabase
import com.lst11.twitterlite.recyclerView.PostItemAdapter
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

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    //TODO: move to dagger dependency
    private var database: FirebaseDatabase = FirebaseDatabase.getInstance()
    private var userService: UserService

    init {
        userService = UserService(database)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val accountHeader: AccountHeader = AccountHeaderBuilder()
            .withActivity(this)
            .withHeaderBackground(R.drawable.user_default)
            .withTranslucentStatusBar(true)
            .build()

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.setBackground(resources.getDrawable(R.color.colorAccent))

        val item1: PrimaryDrawerItem = PrimaryDrawerItem()
            .withName("Home")

        val item2: PrimaryDrawerItem = PrimaryDrawerItem()
            .withName("Awesome")

        val item3: SecondaryDrawerItem = SecondaryDrawerItem()
            .withName("Recent")
            .withIcon(FontAwesome.Icon.faw_hand_holding)

        val driver = DrawerBuilder().withActivity(this)
            .withToolbar(toolbar)
            .withAccountHeader(accountHeader)
            .addDrawerItems(
                item1,
                item2,
                DividerDrawerItem(),
                item3
            )
            .withOnDrawerItemClickListener(object : Drawer.OnDrawerItemClickListener {
                override fun onItemClick(
                    view: View?,
                    position: Int,
                    drawerItem: IDrawerItem<*>
                ): Boolean {
                    Log.d("DRAWER", "Clicked on position $position")
                    return true
                }

            })
            .build()

        driver.addStickyFooterItem(PrimaryDrawerItem().withName("StickyFooterItem"))


        userService.addUser("Test one")
        userService.addUser("Test two")
        userService.addUser("Test three")

        val myDataset = listOf(
            "First Item",
            "Second Item",
            "Third Item",
            "Forth Item",
            "Fifth Item",
            "The end"
        )

        viewManager = LinearLayoutManager(this)
        viewAdapter = PostItemAdapter(myDataset)

        recyclerView = findViewById<RecyclerView>(R.id.my_recycler_view).apply {
            setHasFixedSize(true)

            layoutManager = viewManager
            adapter = viewAdapter
        }


    }
}
