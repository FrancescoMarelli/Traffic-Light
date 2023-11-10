import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import java.util.Random;

public class EstadoVerde implements Estado {
    private Semaforo semaforo;
    Clip clip;

    public void ejecutarAccion() {
        Random random = new Random();
        int randomInt = random.nextInt(2);

        try {
            sound();
            System.out.println("Estado Verde");

            if (randomInt == 0) {
                // Transition to EstadoVerdeParpadea
                Thread.sleep(7000);
                clip.close();
                semaforo.setEstado(new EstadoVerdeParpadea());
            } else {
                // Transition to EstadoAzulP
                Thread.sleep(7000);
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
        return new ImageIcon("src/img/verde.png");
    }

    public int getTime() {
        return 7000;
    }

    public void setSemaforo(Semaforo semaforo) {
        this.semaforo = semaforo;
    }

    public void sound() {
        String soundName = "src/sound/greenSound.wav";
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
