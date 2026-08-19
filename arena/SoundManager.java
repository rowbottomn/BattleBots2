package arena;

import java.net.URL;
import java.util.*;
import javax.sound.sampled.*;

/**
 * SoundManager is a utility class for managing audio clips in the BattleBots game.
 * It allows for loading and playing multiple instances of the same sound simultaneously.
 * Although currently not using that functionality\
 * @author Nathan Rowbottom
 * @version 1.0 Jul 09 2026
 */

public class SoundManager {
    private static final Map<String, ArrayList<Clip>> clipCache = new HashMap<>();
    private static final int MAX_SIMULTANEOUS_CLIPS = 10; // adjust as needed

    /**
     * Loads and returns an audio clip that can be played.
     * Supports multiple simultaneous plays of the same sound.
     * @param resourcePath path relative to the sounds/ folder
     * @return a Clip ready to play, or null if loading fails
     */

    public static Clip getAudioClip(String resourcePath) {
        try {
            // Get or create pool of clips for this sound
            List<Clip> clips = clipCache.computeIfAbsent(resourcePath, k -> new ArrayList<>());
            
            // Find a clip that's not currently playing
            for (Clip clip : clips) {
                if (!clip.isRunning()) {
                    // Reset the clip to the beginning
                    clip.setFramePosition(0);
                    return clip;
                }
            }
            
            // If all clips are playing and we haven't hit the limit, create a new one
            if (clips.size() < MAX_SIMULTANEOUS_CLIPS) {
                Clip newClip = createClip(resourcePath);
                if (newClip != null) {
                    clips.add(newClip);
                    return newClip;
                }
            }
            
            // If we're maxed out, reuse the first clip (will restart it)
            if (!clips.isEmpty()) {
                Clip clip = clips.get(0);
                clip.setFramePosition(0);
                return clip;
            }
            
        } catch (Exception e) {
            System.err.println("Failed to get audio clip: " + resourcePath);
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * Creates a new Clip from the specified resource path.
     * @param resourcePath path relative to the sounds/ folder
     * @return a new Clip, or null if loading fails
     * @throws Exception if an error occurs during loading
     */

    private static Clip createClip(String resourcePath) throws Exception {
        URL url = SoundManager.class.getClassLoader().getResource("sounds/" + resourcePath);
        if (url == null) {
            System.err.println("Sound file not found: " + resourcePath);
            return null;
        }
        
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(url);
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);
        return clip;
    }


}