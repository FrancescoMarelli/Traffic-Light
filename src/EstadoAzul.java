import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;

public class EstadoAzul implements Estado {
    private Semaforo semaforo;
    Clip clip;
    private Estado estadoAnterior;


    public void ejecutarAccion() {
        try {
            sound();
            System.out.println("Estado Azul");
            Thread.sleep(3000);
            clip.close();
            if (estadoAnterior != null) {
                semaforo.setEstado(estadoAnterior);
            } else {
                semaforo.setEstado(new EstadoRojo());
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void setEstadoAnterior(Estado estadoAnterior) {
       this.estadoAnterior = estadoAnterior;
    }

    public ImageIcon getImage() {
        return new ImageIcon("src/img/azul.png");
    }

    public int getTime() {
        return 10000;
    }

    public void setSemaforo(Semaforo semaforo) {
        this.semaforo = semaforo;
    }

    public void sound() {
        String soundName = "src/sound/bluesound.wav";
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
