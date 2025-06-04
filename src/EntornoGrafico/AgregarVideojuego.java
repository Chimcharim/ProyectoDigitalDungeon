package EntornoGrafico;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class AgregarVideojuego extends JFrame {

    public AgregarVideojuego() {
        setTitle("Agregar Videojuego");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(6, 2, 15, 15));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        panel.setBackground(new Color(0, 0, 85));

        JLabel lblTitulo = new JLabel("Título:");
        JTextField txtTitulo = new JTextField();

        JLabel lblPlataforma = new JLabel("Plataforma:");
        JTextField txtPlataforma = new JTextField();

        JLabel lblGenero = new JLabel("Género:");
        JTextField txtGenero = new JTextField();

        JLabel lblPrecio = new JLabel("Precio:");
        JTextField txtPrecio = new JTextField();

        JButton btnGuardar = new JButton("Guardar");
        JButton btnVolver = new JButton("Volver");

        lblTitulo.setForeground(Color.WHITE);
        lblPlataforma.setForeground(Color.WHITE);
        lblGenero.setForeground(Color.WHITE);
        lblPrecio.setForeground(Color.WHITE);

        btnGuardar.addActionListener(e -> {
            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/proyecto_cyber", "root", "");
                 PreparedStatement stmt = conn.prepareStatement("INSERT INTO videojuegos (titulo, plataforma, genero, precio) VALUES (?, ?, ?, ?)")) {

                stmt.setString(1, txtTitulo.getText());
                stmt.setString(2, txtPlataforma.getText());
                stmt.setString(3, txtGenero.getText());
                stmt.setDouble(4, Double.parseDouble(txtPrecio.getText()));
                stmt.executeUpdate();

                JOptionPane.showMessageDialog(this, "Videojuego añadido correctamente.");

                // Limpiar campos para añadir otro juego
                txtTitulo.setText("");
                txtPlataforma.setText("");
                txtGenero.setText("");
                txtPrecio.setText("");
                txtTitulo.requestFocus();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al guardar: " + ex.getMessage());
            }
        });
        btnVolver.addActionListener(e -> {
            dispose(); // Cierra la ventana actual
            new MenuEmpleados().setVisible(true); // Abre el menú de empleados
        });

        panel.add(lblTitulo); panel.add(txtTitulo);
        panel.add(lblPlataforma); panel.add(txtPlataforma);
        panel.add(lblGenero); panel.add(txtGenero);
        panel.add(lblPrecio); panel.add(txtPrecio);
        panel.add(btnVolver); panel.add(btnGuardar);

        add(panel);
    }
}
