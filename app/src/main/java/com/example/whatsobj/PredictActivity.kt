package com.example.whatsobj

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import com.example.whatsobj.ml.MobilenetV110224Quant
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import java.util.*

class PredictActivity : AppCompatActivity() {

        lateinit var outputText : TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_predict)

        val actionbar = supportActionBar
        actionbar!!.title = "What'sObj"
        actionbar.setDisplayHomeAsUpEnabled(true)

        val labels = application.assets.open("labels.txt").bufferedReader().use { it.readText() }.split("\n")
        outputText = findViewById(R.id.outputText)

        val bitmap = intent.getParcelableExtra<Bitmap>("data")
        val ivImage = findViewById<AppCompatImageView>(R.id.iv_image)
        ivImage.setImageBitmap(bitmap)

        //var resized = bitmap?.let { Bitmap.createScaledBitmap(it, 224, 224, true) }
        val model = MobilenetV110224Quant.newInstance(this)

        var tBuffer = TensorImage.fromBitmap(bitmap)
        var byteBuffer = tBuffer.buffer
        // Creates inputs for reference.
        val inputFeature0 = TensorBuffer.createFixedSize(intArrayOf(1, 224, 224, 3), DataType.UINT8)
        inputFeature0.loadBuffer(byteBuffer)

        // Runs model inference and gets result.
        val outputs = model.process(inputFeature0)
        val outputFeature0 = outputs.outputFeature0AsTensorBuffer

        var max = getMax(outputFeature0.floatArray)

        //val obj = labels[max]
        //var countList: MutableList<Obj> = ArrayList()

        //val newObj = Obj(obj)
        //countList = Obj.list

        //for (item in countList.distinct()) {
           // val itemAndCount = item.toString().plus("    ").plus(Collections.frequency(countList, item).toString())

         //   outputText.append(itemAndCount)
        //}


        //display the object's name
        outputText.setText(labels[max])

        // Releases model resources if no longer used.
        model.close()
    }



    fun getMax(arr:FloatArray) : Int{
        var ind = 0
        var min = 0.0f

        for(i in 0..1000)
        {
            if(arr[i] > min)
            {
                min = arr[i]
                ind = i
            }
        }
        return ind
    }
}