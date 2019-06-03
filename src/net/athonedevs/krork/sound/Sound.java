package net.athonedevs.krork.sound;

import net.athonedevs.krork.utils.Log;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class Sound {

    private File file;
    private Clip clip;

    /**
     * Loads a file
     *
     * @param file The sound file
     */
    public Sound(File file) {
        this.file = file;
    }

    /**
     * Starts playing the file selected
     */
    public void startSound() {
        try {
            clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(file));
            clip.start();
        } catch(Exception exc) {
            Log.log(Log.LogType.DANGER, exc.toString());
        }

    }

    /**
     * Pauses the sound
     */
    public void pauseSound() {
        if(clip.isOpen()){
            clip.stop();
        } else {
            startSound();
        }
    }

    /**
     * Starts the sound from where it was paused
     */
    public void resumeSound() {
        if(clip.isOpen()){
            clip.start();
        } else {
            startSound();
        }
    }

    /**
     * Stops the sound.
     * WARNING: if this method is used, you must start the sound again with startSound()
     * @see Sound#startSound()
     */
    public void stopSound() {
        if(!clip.isOpen()) return;

        clip.stop();
        clip.close();
    }

    /**
     * Gets if sound has finished
     * @return if sound has finished
     */
    public boolean isSoundFinished() {
        return clip.getFramePosition() == clip.getFrameLength();
    }
}
