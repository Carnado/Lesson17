package uz.fozilbek.lesson17.ui

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import uz.fozilbek.lesson17.R
import uz.fozilbek.lesson17.core.data.UserModel
import uz.fozilbek.lesson17.core.adapter.UsersAdapter
import uz.fozilbek.lesson17.core.cache.UserCache
import uz.fozilbek.lesson17.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val adapter by lazy {
        UsersAdapter()
    }

    private var userLists = ArrayList<UserModel>()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


         binding.userList.adapter = adapter


        binding.addUser.setOnClickListener {
            startActivity(Intent(this, UserAddActivity::class.java))
        }
        adapter.onItemMenuClick=this::onItem

    }

    @SuppressLint("SuspiciousIndentation")
    private fun onItem(usermodel:UserModel, view: View) {
//        Toast.makeText(this, "${usermodel.userName}", Toast.LENGTH_SHORT).show()
        val popupMenu=PopupMenu(this,view)
        popupMenu.menuInflater.inflate(R.menu.item_menu,popupMenu.menu)
        popupMenu.show()
        popupMenu.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.delete_item->{

                    val dialog=AlertDialog.Builder(this)
                        .setTitle("O'chirish")
                        .setMessage("Rostanham ma'lumotni o'chirmoqchimisz?")
                        .setNegativeButton("Xa"){dialog,which->
                            userLists.remove(usermodel)
                         adapter.userList=userLists

                            UserCache.getSharedID(usermodel.userName)
                        }
                        .setPositiveButton("Yoq"){dialog,which->
                            dialog.dismiss()
                        }.create()
                    dialog.show()

                }
                R.id.edit_item->{
                    val dialog=AlertDialog.Builder(this)
                        .setTitle("O'zgartirish")
                        .setMessage("Rostanham ma'lumotni o'zgartirmoqchimisiz?")
                        .setNegativeButton("Xa"){dialog,which->
                            val intent=Intent(this,EditActivity::class.java)
                            intent.putExtra("username",usermodel.userName)
                            intent.putExtra("user_followers",usermodel.userFollowers)
                            startActivity(intent)
                        }
                        .setPositiveButton("Yoq"){dialog,which->
                            dialog.dismiss()
                        }.create()
                    dialog.show()




                }
            }



            return@setOnMenuItemClickListener true
        }
    }

//    private fun removeCache(usermodel: UserModel) {
//        getSharedId()
//
//    }

//    private fun getSharedId(usermodel: UserModel):Int {
//
//    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.activity_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.sort_by_name->{
                userLists.sortBy {
                    it.userName
                }
                adapter.userList=userLists
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        super.onResume()
        loadData()
    }

    private fun loadData() {
        var users=ArrayList<UserModel>()
        userLists=UserCache.getUsers()
        for (i in 0 until userLists.size){
            if (userLists.get(i).userName!="No name" || userLists.get(i).userFollowers!=0){
                users.add(UserModel(imageIndex = userLists.get(i).imageIndex,
                    backgroundColor = userLists.get(i).backgroundColor,
                    userName = userLists.get(i).userName,
                    userFollowers =userLists.get(i).userFollowers ))
            }
        }
        userLists.clear()
        userLists=users

        adapter.userList=userLists
    }
fun showDialogDelete(){


}
}