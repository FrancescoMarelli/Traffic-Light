import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.FloatControl;
import javax.swing.*;
import javax.sound.sampled.Clip;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Random;


public class EstadoVerdeParpadea implements Estado {
    Semaforo semaforo;
    Clip clip;


    public EstadoVerdeParpadea(){
        semaforo = new Semaforo();
    }
    public void ejecutarAccion() {
        Random random = new Random();
        int randomInt = random.nextInt(2);
        try {
            if (randomInt == 0) {
                sound();
                System.out.println("Estado Verde Parpadeando");
                Thread.sleep(3000);
                clip.close();
                EstadoAzul estadoAzul = new EstadoAzul();
                estadoAzul.setEstadoAnterior(this);
                semaforo.setEstado(estadoAzul);
            } else {
                sound();
                System.out.println("Estado Verde Parpadeando");
                Thread.sleep(3000);
                clip.close();
                semaforo.setEstado(new EstadoRojo());
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public ImageIcon getImage() {
        return new ImageIcon("src/img/negro.png");
    }

    public int getTime(){ return 3000;}

    public void setSemaforo(Semaforo semaforo) {this.semaforo = semaforo;}

    public void sound() {
        String soundName = "src/sound/blinksound.wav";
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