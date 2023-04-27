package uz.fozilbek.lesson17.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.forEachIndexed
import com.google.android.material.imageview.ShapeableImageView
import uz.fozilbek.lesson17.R
import uz.fozilbek.lesson17.core.cache.UserCache
import uz.fozilbek.lesson17.core.data.UserModel
import uz.fozilbek.lesson17.databinding.ActivityEditBinding

class EditActivity : AppCompatActivity() {
    lateinit var binding: ActivityEditBinding
    var choseColor=""
    var name=""
    var followers=0
    var choseImage=1
    var nameUser="No Name"
    var followerUser=0
    var imageUser=1;
    var backgroundUser="#48dbfb"
    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityEditBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        loadActions()
        loadData()
    }

    private fun loadData() {
        name=intent.getStringExtra("username")!!
        followers=intent.getIntExtra("user_followers",0)!!
        binding.userName.setText(name)
        binding.userFollowers.setText(followers.toString())
    }

    private fun loadActions() {

        binding.imageGroup.forEachIndexed { index, view ->
            view.setOnClickListener {
                binding.userIcon.setImageDrawable((view as ShapeableImageView).drawable)
                when (view) {
                    binding.userIcon1 -> {
                        choseImage = 1
                    }
                    binding.userIcon2 -> {
                        choseImage = 2
                    }
                    binding.userIcon3 -> {
                        choseImage = 3
                    }
                    binding.userIcon4 -> {
                        choseImage = 4
                    }
                    binding.userIcon5 -> {
                        choseImage = 5
                    }
                    binding.userIcon6 -> {
                        choseImage = 6
                    }
                }
            }
        }
        binding.colorGroup.forEachIndexed { index, view ->
            view.setOnClickListener {
                binding.userColor.setImageDrawable((view as ShapeableImageView).drawable)
                when (view) {
                    binding.color1 -> {
                        choseColor = "#48dbfb"
                    }

                    binding.color2 -> {
                        choseColor = "#10ac84"
                    }

                    binding.color3 -> {
                        choseColor = "#ff9f43"
                    }

                    binding.color4 -> {
                        choseColor = "#5f27cd"
                    }

                    binding.color5 -> {
                        choseColor = "#576574"
                    }

                    binding.color6 -> {
                        choseColor = "#2e86de"
                    }

                }
            }
        }
        binding.saveUser.setOnClickListener {
           val intIndex=UserCache.getSharedEditId(name)
            backgroundUser=choseColor
            imageUser=choseImage
            nameUser= binding.userName.text.toString()
            followers=binding.userFollowers.text.toString().toInt()
            Toast.makeText(this, "$followers", Toast.LENGTH_SHORT).show()

            var usermodel=UserModel(imageIndex = imageUser, backgroundColor = backgroundUser, userName = nameUser, userFollowers = followers)

            UserCache.editUserSaver(usermodel,intIndex)
            finish()
        }
    }
}