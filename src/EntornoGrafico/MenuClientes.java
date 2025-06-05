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
        panel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        JButton btnAlquilar = new JButton("Alquilar Videojuego");
        btnAlquilar.setBackground(new Color(128, 128, 255));
        btnAlquilar.setForeground(new Color(255, 255, 255));
        btnAlquilar.setBounds(50, 30, 284, 39);
        JButton btnComprar = new JButton("Comprar Videojuego");
        btnComprar.setForeground(new Color(255, 255, 255));
        btnComprar.setBackground(new Color(128, 128, 255));
        btnComprar.setBounds(50, 84, 284, 39);
        JButton btnMostrar = new JButton("Mostrar Videojuegos");
        btnMostrar.setForeground(new Color(255, 255, 255));
        btnMostrar.setBackground(new Color(128, 128, 255));
        btnMostrar.setBounds(50, 138, 284, 39);
        JButton btnInicio = new JButton("Volver al Inicio de Sesión");
        btnInicio.setIcon(new ImageIcon(MenuClientes.class.getResource("/Imagenes/salir redimensionado (1).png")));
        btnInicio.setBounds(60, 188, 260, 62);

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
        panel.setLayout(null);

        panel.add(btnAlquilar);
        panel.add(btnComprar);
        panel.add(btnMostrar);
        panel.add(btnInicio);

        getContentPane().add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MenuClientes().setVisible(true));
    }
}
