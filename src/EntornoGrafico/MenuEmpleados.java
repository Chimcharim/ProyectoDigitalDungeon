package EntornoGrafico;

import javax.swing.*;
import java.awt.*;

public class MenuEmpleados extends JFrame {

    public MenuEmpleados() {
        setTitle("Menú de Empleados");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(0, 0, 85));
        panel.setLayout(new GridLayout(3, 1, 15, 15));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        JButton btnAgregar = new JButton("Añadir Videojuego");
        JButton btnEliminar = new JButton("Eliminar Videojuego");
        JButton btnInicio = new JButton("Volver al Inicio de Sesión");

        btnAgregar.addActionListener(e -> {
            dispose();
            new AgregarVideojuego().setVisible(true);
        });

        btnEliminar.addActionListener(e -> {
            dispose();
            new EliminarVideojuego().setVisible(true);
        });

        btnInicio.addActionListener(e -> {
            dispose();
            new InicioDeSesion().setVisible(true);
        });

        panel.add(btnAgregar);
        panel.add(btnEliminar);
        panel.add(btnInicio);

        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MenuEmpleados().setVisible(true));
    }
}
