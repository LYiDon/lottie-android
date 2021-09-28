package com.example.sample_video

import android.content.Intent
import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.example.sample_video.databinding.ActivityMainBinding
import java.io.File

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        mBinding.lottie.imageAssetsFolder = "images"
        mBinding.lottie.setAnimation("data.json")
        mBinding.lottie.playAnimation()

        mBinding.save.setOnClickListener {
            val videoFile = getPath(false)
            Log.e("lyd", videoFile.toString())
            val recorder = Recorder(videoOutput = videoFile)
            val frameCreator = FrameCreator(mBinding.lottie.lottieDrawable)
            val operation = RecordingOperation(recorder,frameCreator){
                openCreatedVideo(videoFile)
            }
            operation.start()
        }
    }

    private fun openCreatedVideo(videoFile: File) {
        val intent = Intent()
        intent.action = Intent.ACTION_VIEW
        val uriForFile = FileProvider.getUriForFile(this, "com.sample_video.provider", videoFile)
        intent.setDataAndType(uriForFile, "video/*")
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        startActivity(intent)
    }

    private fun getPath(isSD:Boolean):File{
        return if(isSD){
            File(Environment.getExternalStorageDirectory().toString() + File.separator + "lottie_video.mp4")
        }else{
            val path = getExternalFilesDir(Environment.DIRECTORY_PICTURES) ?: File(
                cacheDir,
                Environment.DIRECTORY_PICTURES
            ).apply { mkdirs() }
            File(path, "lottie_video.mp4")
        }
    }

}