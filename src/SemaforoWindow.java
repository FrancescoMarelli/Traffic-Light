import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SemaforoWindow extends JFrame {

    private JLabel label;
    private Semaforo semaforo;
    private Timer timer;

    public SemaforoWindow() {
        setTitle("Simulador de Semáforo");
        setSize(200, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout());

        label = new JLabel(new ImageIcon("src/negro.png"));

        add(label, BorderLayout.CENTER);

        // Empieza la simulación del programa
        startSimulation();
        stopSimulation();

        setVisible(true);
    }

    public void startSimulation() {
        timer = new Timer(0, e -> update());
        semaforo = new Semaforo();
        timer.start();
    }

    public void stopSimulation() {
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                // No es necesario implementar este método
            }

            @Override
            public void keyPressed(KeyEvent e) {
                // Detiene la simulación al presionar una tecla
                if (timer != null && timer.isRunning()) {
                    timer.stop();
                }
                // Cierra el JFrame
                dispose();            }

            @Override
            public void keyReleased(KeyEvent e) {
                // No es necesario implementar este método
            }
        });
    }

    private void update(){
        label.setIcon(semaforo.getEstado().getImage());
        SwingUtilities.invokeLater(() -> semaforo.ejecutarAccion());
    }
}