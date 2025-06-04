package EntornoGrafico;

import javax.swing.*;
import java.awt.*;

public class MenuClientes extends JFrame {

    public MenuClientes() {
        setTitle("Menú de Clientes");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(0, 0, 85));
        panel.setLayout(new GridLayout(4, 1, 15, 15));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        JButton btnAlquilar = new JButton("Alquilar Videojuego");
        JButton btnComprar = new JButton("Comprar Videojuego");
        JButton btnMostrar = new JButton("Mostrar Videojuegos");
        JButton btnInicio = new JButton("Volver al Inicio de Sesión");

        btnAlquilar.addActionListener(e -> {
            dispose();
            new AlquilarVideojuego().setVisible(true);
        });

        btnComprar.addActionListener(e -> {
            dispose();
            new ComprarVideojuego().setVisible(true);
        });

        btnMostrar.addActionListener(e -> {
            dispose();
            new MostarVideojuegos().setVisible(true);
        });

        btnInicio.addActionListener(e -> {
            dispose();
            new InicioDeSesion().setVisible(true);
        });

        panel.add(btnAlquilar);
        panel.add(btnComprar);
        panel.add(btnMostrar);
        panel.add(btnInicio);

        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MenuClientes().setVisible(true));
    }
}
