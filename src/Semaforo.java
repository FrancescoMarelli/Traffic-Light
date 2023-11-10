import javax.swing.*;

public class Semaforo {
    private Estado estado;

    public Semaforo(){
        setEstado(new EstadoRojo());

    }

    public void setEstado(Estado estado) {
            this.estado = estado;
            this.estado.setSemaforo(this);
    }

    public Estado getEstado() { return this.estado; }

    public void ejecutarAccion() {
        estado.ejecutarAccion();
    }

    public ImageIcon getImage() {
        return estado.getImage();
    }
}
