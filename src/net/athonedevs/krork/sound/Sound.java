package net.athonedevs.krork.sound;

import net.athonedevs.krork.utils.Log;

import javax.sound.sampled.*;
import java.io.File;

public class Sound {

    private Clip clip;
    private boolean active = false;

    /**
     * Default Sound constructor
     *
     * @param path The path to the file
     */
    public Sound(String path){
        check(path);
    }

    /**
     * Default Sound constructor
     *
     * @param file The file to be played
     */
    public Sound(File file){
        check(file.toString());
    }

    private void check(String path){
        try {
            if(!path.equals("")){
                initiate(path);
            } else {
                throw new NullPointerException();
            }
        } catch (NullPointerException e){
            Log.danger("Destination Cannot be empty");
            throw e;
        }
    }

    private void initiate(String path){
        try {
            final AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClass().getResourceAsStream(path));
            final AudioFormat baseformat = audioInputStream.getFormat();
            final AudioFormat decodeFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, baseformat.getSampleRate(), 16, baseformat.getChannels(), baseformat.getChannels() * 2, baseformat.getSampleRate(), false);
            final AudioInputStream decodedAudioInputStream = AudioSystem.getAudioInputStream(decodeFormat, audioInputStream);
            clip = AudioSystem.getClip();
            clip.open(decodedAudioInputStream);
        } catch (Exception e) {
            Log.danger("Audio failed to initiate");
            e.printStackTrace();
        }
    }

    /**
     * Plays the Audio file
     */
    public void play(){
        try{
            if(clip == null) return;
            stop();
            clip.setFramePosition(0);
            clip.start();
            active = true;
        } catch (Exception e) {
            Log.danger("Audio failed to play");
            throw e;
        }
    }

    /**
     * Changes the volume
     *
     * @param volume The volume value
     */
    public void setVolume(float volume){
        final FloatControl v = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        v.setValue(volume);
    }

    /**
     * Stops the audio file
     */
    public void stop() {
        if (clip.isRunning()) clip.stop();
        active = false;
    }

    public void close(){
        stop();
        clip.close();
    }

    public boolean isActive(){
        return this.active;
    }
}
