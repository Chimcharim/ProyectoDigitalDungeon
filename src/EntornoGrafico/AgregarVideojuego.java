package EntornoGrafico;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class AgregarVideojuego extends JFrame {

    private JTextField txtTitulo;
    private JComboBox<String> cbPlataforma;
    private JComboBox<String> cbGenero;
    private JTextField txtPrecio;

    public AgregarVideojuego() {
        setTitle("Agregar Videojuego");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        panel.setBackground(new Color(0, 0, 85));
        panel.setLayout(null);

        JLabel lblTitulo = new JLabel("Título:");
        lblTitulo.setBounds(30, 20, 154, 41);
        lblTitulo.setForeground(Color.WHITE);
        txtTitulo = new JTextField();
        txtTitulo.setBounds(199, 20, 154, 41);

        JLabel lblPlataforma = new JLabel("Plataforma:");
        lblPlataforma.setBounds(30, 76, 154, 41);
        lblPlataforma.setForeground(Color.WHITE);
        cbPlataforma = new JComboBox<>(new String[]{"Consola", "PC", "Consola/PC"});
        cbPlataforma.setBounds(199, 76, 154, 41);

        JLabel lblGenero = new JLabel("Género:");
        lblGenero.setBounds(30, 132, 154, 41);
        lblGenero.setForeground(Color.WHITE);
        cbGenero = new JComboBox<>(new String[]{"Accion", "Supervivencia", "Deportes", "Estrategia", "RPG", "Aventura", "Shooter"});
        cbGenero.setBounds(199, 132, 154, 41);

        JLabel lblPrecio = new JLabel("Precio:");
        lblPrecio.setBounds(30, 188, 154, 41);
        lblPrecio.setForeground(Color.WHITE);
        txtPrecio = new JTextField();
        txtPrecio.setBounds(199, 188, 154, 41);

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(199, 244, 154, 41);

        JButton btnVolver = new JButton("");
        btnVolver.setIcon(new ImageIcon(AgregarVideojuego.class.getResource("/Imagenes/salir redimensionado.png")));
        btnVolver.setBackground(new Color(240, 240, 240));
        btnVolver.setForeground(new Color(0, 0, 0));
        btnVolver.setBounds(30, 244, 154, 45);

        btnGuardar.addActionListener(e -> {
            String titulo = txtTitulo.getText().trim();
            String plataforma = (String) cbPlataforma.getSelectedItem();
            String genero = (String) cbGenero.getSelectedItem();
            String precioStr = txtPrecio.getText().trim();

            if (titulo.isEmpty() || precioStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.");
                return;
            }

            double precio;
            try {
                precio = Double.parseDouble(precioStr);
                if (precio <= 0) {
                    JOptionPane.showMessageDialog(this, "El precio debe ser un número positivo mayor que cero.");
                    return;
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "El precio debe ser un número válido.");
                return;
            }

            // Verificar que no exista otro videojuego con el mismo título
            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/proyecto_cyber", "root", "");
                 PreparedStatement checkStmt = conn.prepareStatement("SELECT COUNT(*) FROM videojuegos WHERE titulo = ?")) {

                checkStmt.setString(1, titulo);
                ResultSet rs = checkStmt.executeQuery();
                if (rs.next() && rs.getInt(1) > 0) {
                    JOptionPane.showMessageDialog(this, "Ya existe un videojuego con ese título.");
                    return;
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error al verificar el título: " + ex.getMessage());
                return;
            }

            // Insertar el videojuego
            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/proyecto_cyber", "root", "");
                 PreparedStatement stmt = conn.prepareStatement("INSERT INTO videojuegos (titulo, plataforma, genero, precio) VALUES (?, ?, ?, ?)")) {

                stmt.setString(1, titulo);
                stmt.setString(2, plataforma);
                stmt.setString(3, genero);
                stmt.setDouble(4, precio);

                stmt.executeUpdate();

                JOptionPane.showMessageDialog(this, "Videojuego añadido correctamente.");

                // Limpiar campos
                txtTitulo.setText("");
                cbPlataforma.setSelectedIndex(0);
                cbGenero.setSelectedIndex(0);
                txtPrecio.setText("");
                txtTitulo.requestFocus();

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error al guardar: " + ex.getMessage());
            }
        });

        btnVolver.addActionListener(e -> {
            dispose();
            new MenuEmpleados().setVisible(true);
        });

        panel.add(lblTitulo);
        panel.add(txtTitulo);
        panel.add(lblPlataforma);
        panel.add(cbPlataforma);
        panel.add(lblGenero);
        panel.add(cbGenero);
        panel.add(lblPrecio);
        panel.add(txtPrecio);
        panel.add(btnVolver);
        panel.add(btnGuardar);

        getContentPane().add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new AgregarVideojuego().setVisible(true);
        });
    }
}
