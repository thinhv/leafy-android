package com.leafy.ui.addplant

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import androidx.appcompat.app.AppCompatActivity
import com.leafy.R
import kotlinx.android.synthetic.main.activity_add_plant.*

class AddPlantActivity : AppCompatActivity() {
    private val picId = 123

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_plant)
        val camera_intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(camera_intent, picId);
        button_add.setOnClickListener{

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        var photo = data?.getExtras()?.get("data") as Bitmap
        plant_image.setImageBitmap(photo)
    }

}