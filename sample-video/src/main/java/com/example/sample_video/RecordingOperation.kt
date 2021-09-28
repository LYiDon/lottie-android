package com.example.sample_video

import com.example.sample_video.FrameCreator
import com.example.sample_video.Recorder

class RecordingOperation(
    private val recorder: Recorder,
    private val frameCreator: FrameCreator,
    private val listener: () -> Unit
) {

  fun start() {
    while (isRecording()) {
      recorder.nextFrame(frameCreator.generateFrame())
    }
    recorder.end()
    listener()
  }

  private fun isRecording() = !frameCreator.hasEnded()
}