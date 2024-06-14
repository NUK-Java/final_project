import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;

public class Music {
    
    private AdvancedPlayer player;
    private FileInputStream file;
    private String path;
    public Boolean stop = false;
    
    public Music(String path) {
        this.path = path;
    }
    
    public void play() {
        stop = false;
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!stop) {
                    try {
                        file = new FileInputStream(path);
                        player = new AdvancedPlayer(file);
                        player.play();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                        break;
                    } catch (JavaLayerException e) {
                        e.printStackTrace();
                        break;
                    }
                }
            }
        }).start();
    }
    
    public void stop() {
        stop = true;
        if (player != null) {
            player.close();
        }
    }
    

}
