package com.example.sample_video

import android.graphics.drawable.Drawable
import com.airbnb.lottie.LottieDrawable

class FrameCreator(private val lottieDrawable: LottieDrawable) {

  init {
    lottieDrawable.scale = VIDEO_WIDTH_PX / lottieDrawable.intrinsicWidth
  }

  /** 视频总帧数 */
  private val durationInFrames: Int = lottieDrawable.composition.durationFrames.toInt()

  /** 当前帧 */
  private var currentFrame: Int = 0

  fun generateFrame(): Drawable {
    lottieDrawable.frame = currentFrame //设置进度到当前帧
    ++currentFrame  //当前帧加1
    return lottieDrawable
  }

  fun hasEnded() = currentFrame > durationInFrames
}

/** 视频宽度 720px */
private const val VIDEO_WIDTH_PX = 720f