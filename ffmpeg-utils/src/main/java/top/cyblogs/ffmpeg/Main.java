package top.cyblogs.ffmpeg;

import top.cyblogs.ffmpeg.exec.MergeVideoAndAudio;

import java.io.File;

public class Main {

    public static void main(String[] args) {
        MergeVideoAndAudio.exec(
                new File("C:\\Users\\dnydi\\Desktop\\video.mp4"),
                new File("C:\\Users\\dnydi\\Desktop\\audio.m4a"),
                new File("C:\\Users\\dnydi\\Desktop\\合并.mp4"),
                null
        );
    }
}
