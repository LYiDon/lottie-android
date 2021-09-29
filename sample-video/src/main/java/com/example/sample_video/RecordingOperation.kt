package com.example.sample_video

import com.example.sample_video.FrameCreator
import com.example.sample_video.Recorder

class RecordingOperation(
    private val recorder: Recorder,
    private val frameCreator: FrameCreator,
    private val listener: () -> Unit
) {

    fun start() {

        recorder.printInit("开始 ")
        while (isRecording()) { //循环所有帧
            recorder.nextFrame(frameCreator.generateFrame())
            recorder.printInit("循环 ")
        }
        recorder.printInit("结束 ")
        recorder.encoderAudio()
        recorder.end()
        listener()
    }

    private fun isRecording() = !frameCreator.hasEnded()
}