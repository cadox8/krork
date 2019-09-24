package net.athonedevs.krork.sound;

import net.athonedevs.krork.utils.Log;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.File;

public class Sound {

    private final File file;
    private float volume;
    private Clip clip;

    /**
     * Loads a file
     *
     * @param file The sound file
     * @param volume The volume of the file
     */
    public Sound(File file, float volume) {
        this.file = file;
        this.volume = volume;
    }

    /**
     * Starts playing the file selected
     */
    public void startSound() {
        try {
            clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(file));
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(volume);
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
     *
     * WARNING: if this method is used, you must start the sound again with startSound()
     *
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
