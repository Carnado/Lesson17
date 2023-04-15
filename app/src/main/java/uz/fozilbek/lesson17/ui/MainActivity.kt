package uz.fozilbek.lesson17.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import uz.fozilbek.lesson17.core.data.UserModel
import uz.fozilbek.lesson17.core.adapter.UsersAdapter
import uz.fozilbek.lesson17.core.cache.UserCache
import uz.fozilbek.lesson17.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val adapter = UsersAdapter()

    private var userList = ArrayList<UserModel>()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.userList.adapter = adapter


        binding.addUser.setOnClickListener {
            startActivity(Intent(this, UserAddActivity::class.java))
        }

    }

    override fun onResume() {
        super.onResume()
        loadData()
    }

    private fun loadData() {

        userList=UserCache.getUsers()

//        userList.add(UserModel(5, "#ff00ff", "Ibragimov Sherzod", 935))
//        userList.add(UserModel(3, "#fff0ff", "Jo'rayev Bekzod", 200))
//        userList.add(UserModel(5, "#ff00ff", "Ibragimov Sherzod", 935))
//        userList.add(UserModel(3, "#fff0ff", "Jo'rayev Bekzod", 200))
//        userList.add(UserModel(5, "#ff00ff", "Ibragimov Sherzod", 935))
//        userList.add(UserModel(3, "#fff0ff", "Jo'rayev Bekzod", 200))
//        userList.add(UserModel(5, "#ff00ff", "Ibragimov Sherzod", 935))
//        userList.add(UserModel(3, "#fff0ff", "Jo'rayev Bekzod", 200))
//        userList.add(UserModel(5, "#ff00ff", "Ibragimov Sherzod", 935))
//        userList.add(UserModel(3, "#fff0ff", "Jo'rayev Bekzod", 200))
//        userList.add(UserModel(5, "#ff00ff", "Ibragimov Sherzod", 935))
//        userList.add(UserModel(3, "#fff0ff", "Jo'rayev Bekzod", 200))
//        userList.add(UserModel(5, "#ff00ff", "Ibragimov Sherzod", 935))
//        userList.add(UserModel(3, "#fff0ff", "Jo'rayev Bekzod", 200))
        adapter.userList=userList
    }

}