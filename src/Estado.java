import javax.swing.*;

public interface Estado {
    void ejecutarAccion();
    ImageIcon getImage();
    int getTime();
    void sound();
    void setSemaforo(Semaforo semaforo);
}
