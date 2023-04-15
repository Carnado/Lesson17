package uz.fozilbek.lesson17.ui

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.util.Base64
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.forEachIndexed
import com.google.android.material.imageview.ShapeableImageView
import uz.fozilbek.lesson17.core.cache.UserCache
import uz.fozilbek.lesson17.core.data.UserModel
import uz.fozilbek.lesson17.databinding.ActivityUserAddBinding
import java.io.ByteArrayOutputStream


class UserAddActivity : AppCompatActivity() {
    lateinit var binding: ActivityUserAddBinding
    var choseImage=1;
    var choseColor="#1dd1a1"
    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityUserAddBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        loadActions()
    }

    private fun loadActions() {
        binding.imageGroup.forEachIndexed { index, view ->
            view.setOnClickListener {
               binding.userIcon.setImageDrawable((view as ShapeableImageView).drawable)
              when(view){
                  binding.userIcon1->{
                      choseImage=1
                  }
                  binding.userIcon2->{
                    choseImage=2
                  }
                  binding.userIcon3->{
                    choseImage=3
                  }
                  binding.userIcon4->{
                choseImage=4
                  }
                  binding.userIcon5->{
                choseImage=5
                  }
                  binding.userIcon6->{
                    choseImage=6
                  }
              }
            }
        }
        binding.colorGroup.forEachIndexed { index, view ->
            view.setOnClickListener {
                binding.userColor.setImageDrawable((view as ShapeableImageView).drawable)
                when(view){
                    binding.color1->{
                        choseColor="#48dbfb"
                    }
                       binding.color2->{
                           choseColor="#10ac84"
                    }
                       binding.color3->{
                           choseColor="#ff9f43"
                    }
                       binding.color4->{
                           choseColor="#5f27cd"
                    }
                       binding.color5->{
                           choseColor="#576574"
                    }
                       binding.color6->{
                           choseColor="#2e86de"
                    }

                }
            }
        }



        binding.saveUser.setOnClickListener {
            var name=""
            var follower=0
            if (binding.userName.text!=null){
               name= binding.userName.text.toString()
            }else{
                name="dsad"
            }
            if (binding.userFollowers!=null){
                follower= binding.userFollowers.text.toString().toInt()
            }else{
                follower=0
            }
            val usermodel=UserModel(imageIndex = choseImage, backgroundColor = choseColor,
                userName = name,
                userFollowers = follower
            )
            Log.d("MyLOG","${usermodel.userName}")
            Log.d("MyLOG","${usermodel.backgroundColor}")
            Log.d("MyLOG","${usermodel.userFollowers}")
            Log.d("MyLOG","${usermodel.imageIndex}")
            UserCache.addUser(usermodel)
            finish()

        }
    }


}