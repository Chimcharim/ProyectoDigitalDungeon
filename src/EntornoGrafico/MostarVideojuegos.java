package EntornoGrafico;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class MostarVideojuegos extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTable tabla;
    private DefaultTableModel modelo;

    public MostarVideojuegos() {
        setTitle("Mis Videojuegos");
        setSize(700, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(0, 0, 85));
        add(panel);

        modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(new String[] {
                "Tipo", "Título", "Plataforma", "Género", "Precio", "Fecha"
        });

        tabla = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tabla);
        panel.add(scroll, BorderLayout.CENTER);

        JButton btnVolver = new JButton("Volver al Menú Clientes");
        btnVolver.addActionListener(e -> {
            dispose();
            new MenuClientes().setVisible(true);
        });

        JPanel abajo = new JPanel();
        abajo.setBackground(new Color(0, 0, 85));
        abajo.add(btnVolver);
        panel.add(abajo, BorderLayout.SOUTH);

        cargarComprasYAlquileres();
    }

    private void cargarComprasYAlquileres() {
        modelo.setRowCount(0);

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/proyecto_cyber", "root", "")) {

            // Consultar COMPRAS
            String sqlCompras = "SELECT v.titulo, v.plataforma, v.genero, v.precio, c.fecha_compra " +
                                "FROM compran c JOIN videojuegos v ON c.id_videojuego = v.id_videojuego " +
                                "WHERE c.id_cliente = ?";
            try (PreparedStatement ps = conn.prepareStatement(sqlCompras)) {
                ps.setInt(1, Sesion.idUsuario);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    modelo.addRow(new Object[] {
                            "Compra",
                            rs.getString("titulo"),
                            rs.getString("plataforma"),
                            rs.getString("genero"),
                            rs.getDouble("precio"),
                            rs.getString("fecha_compra")
                    });
                }
            }

            // Consultar ALQUILERES
            String sqlAlquileres = "SELECT v.titulo, v.plataforma, v.genero, v.precio, a.fecha_fin " +
                                   "FROM alquilan a JOIN videojuegos v ON a.id_videojuego = v.id_videojuego " +
                                   "WHERE a.id_cliente = ?";
            try (PreparedStatement ps = conn.prepareStatement(sqlAlquileres)) {
                ps.setInt(1, Sesion.idUsuario);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    modelo.addRow(new Object[] {
                            "Alquiler",
                            rs.getString("titulo"),
                            rs.getString("plataforma"),
                            rs.getString("genero"),
                            rs.getDouble("precio"),
                            rs.getString("fecha_fin")
                    });
                }
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar videojuegos: " + e.getMessage());
        }
    }
}
