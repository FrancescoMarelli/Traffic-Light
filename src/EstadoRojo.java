import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import java.util.Random;

public class EstadoRojo implements Estado {
    private Semaforo semaforo;
    Clip clip;
    private Estado estadoAnterior;

    public void ejecutarAccion() {
        Random random = new Random();
        int randomInt = random.nextInt(2);

        try {
            if (randomInt == 0) {
                sound();
                System.out.println("Estado Rojo");
                Thread.sleep(10000);
                clip.close();
                semaforo.setEstado(new EstadoAmbar());
            } else {
                sound();
                System.out.println("Estado Rojo");
                Thread.sleep(10000);
                clip.close();
                EstadoAzul estadoAzul = new EstadoAzul();
                estadoAzul.setEstadoAnterior(this);
                semaforo.setEstado(estadoAzul);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public ImageIcon getImage() {
        return new ImageIcon("src/img/rojo.png");
    }

    public int getTime() {
        return 10000;
    }

    public void setSemaforo(Semaforo semaforo) {
        this.semaforo = semaforo;
    }
    public void sound() {
        String soundName = "src/sound/redSound.wav";
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new java.io.File(soundName).getAbsoluteFile());
             clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
            clip.drain();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
