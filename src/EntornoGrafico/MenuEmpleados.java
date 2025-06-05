package EntornoGrafico;

import javax.swing.*;
import java.awt.*;

public class MenuEmpleados extends JFrame {

    public MenuEmpleados() {
        setTitle("Menú de Empleados");
        setSize(400, 320);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(0, 0, 85));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
        panel.setLayout(null);

        JButton btnAgregar = new JButton("Añadir Videojuego");
        btnAgregar.setForeground(new Color(255, 255, 255));
        btnAgregar.setBackground(new Color(128, 128, 255));
        btnAgregar.setBounds(50, 20, 284, 40);

        JButton btnEliminar = new JButton("Eliminar Videojuego");
        btnEliminar.setForeground(Color.WHITE);
        btnEliminar.setBackground(new Color(128, 128, 255));
        btnEliminar.setBounds(50, 70, 284, 40);

        JButton btnVentas = new JButton("Ver todas las ventas");
        btnVentas.setForeground(Color.WHITE);
        btnVentas.setBackground(new Color(128, 128, 255));
        btnVentas.setBounds(50, 120, 284, 40);

        JButton btnInicio = new JButton("Volver al Inicio de Sesión");
        btnInicio.setIcon(new ImageIcon(MenuEmpleados.class.getResource("/Imagenes/salir redimensionado (1).png")));
        btnInicio.setBounds(60, 176, 259, 50);

        btnAgregar.addActionListener(e -> {
            dispose();
            new AgregarVideojuego().setVisible(true);
        });

        btnEliminar.addActionListener(e -> {
            dispose();
            new EliminarVideojuego().setVisible(true);
        });

        btnVentas.addActionListener(e -> {
            dispose();
            new Ventas().setVisible(true);
        });

        btnInicio.addActionListener(e -> {
            dispose();
            new InicioDeSesion().setVisible(true);
        });

        panel.add(btnAgregar);
        panel.add(btnEliminar);
        panel.add(btnVentas);
        panel.add(btnInicio);

        getContentPane().add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MenuEmpleados().setVisible(true));
    }
}
