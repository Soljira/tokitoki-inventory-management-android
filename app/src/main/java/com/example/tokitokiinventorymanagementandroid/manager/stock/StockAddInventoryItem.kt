package com.example.tokitokiinventorymanagementandroid.manager.stock

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageView
import androidx.activity.result.ActivityResultLauncher
import com.example.tokitokiinventorymanagementandroid.R
import androidx.activity.result.contract.ActivityResultContracts

class StockAddInventoryItem : AppCompatActivity() {

    lateinit var addImage: ImageView
    var selectedImageUri: Uri? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.manager_stock_add_inventory_item)


        val btnBack = findViewById<ImageView>(R.id.btnBack)
        var addImage = findViewById<ImageView>(R.id.stockItemImage)

        val selectImageLauncher: ActivityResultLauncher<String> =
            registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
                uri?.let {
                    selectedImageUri = it
                    addImage.setImageURI(it) // Set image to ImageView
                }
            }

        /* From https://stackoverflow.com/questions/44148883/select-image-from-gallery-using-kotlin */
        // opens gallery when the plus button (technically an ImageView) is clicked
        // TODO: Have the image fill the container
        addImage.setOnClickListener {
            selectImageLauncher.launch("image/*")
        }

        btnBack.setOnClickListener {
            finish()
        }
    }

    // handles the selected image result
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_SELECT_IMAGE_IN_ALBUM && resultCode == Activity.RESULT_OK) {
            val selectedImageUri: Uri? = data?.data
            selectedImageUri?.let {
                addImage.setImageURI(it) // Set the selected image to ImageView
            }
        }
    }

    fun takePhoto() {
        val intent1 = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (intent1.resolveActivity(packageManager) != null) {
            startActivityForResult(intent1, REQUEST_TAKE_PHOTO)
        }
    }

    companion object {
        private val REQUEST_TAKE_PHOTO = 0
        private val REQUEST_SELECT_IMAGE_IN_ALBUM = 1
    }
}