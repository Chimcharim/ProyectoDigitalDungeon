package EntornoGrafico;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class Ventas extends JFrame {

    private JTable tablaVentas;
    private DefaultTableModel modelo;

    public Ventas() {
        setTitle("Ventas y Alquileres");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(new String[] {
            "Cliente", "Videojuego", "Tipo", "Precio", "Fecha Inicio", "Fecha Fin"
        });

        tablaVentas = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tablaVentas);
        add(scroll, BorderLayout.CENTER);

        JButton btnVolver = new JButton("Volver al Menú Empleados");
        btnVolver.addActionListener(e -> {
            dispose();
            new MenuEmpleados().setVisible(true);
        });

        JPanel panelBoton = new JPanel();
        panelBoton.add(btnVolver);
        add(panelBoton, BorderLayout.SOUTH);

        cargarVentas();
    }

    private void cargarVentas() {
        modelo.setRowCount(0);

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/proyecto_cyber", "root", "")) {

            // Compras
            String sqlCompras = """
                SELECT p.nombre, v.titulo, 'Compra' AS tipo, v.precio, NULL AS fecha_inicio, NULL AS fecha_fin
                FROM compran c
                JOIN personas p ON c.id_cliente = p.id_personas
                JOIN videojuegos v ON c.id_videojuego = v.id_videojuego
            """;
            try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sqlCompras)) {
                while (rs.next()) {
                    modelo.addRow(new Object[] {
                        rs.getString("nombre"),
                        rs.getString("titulo"),
                        rs.getString("tipo"),
                        rs.getDouble("precio"),
                        null,
                        null
                    });
                }
            }

            // Alquileres
            String sqlAlquileres = """
                SELECT p.nombre, v.titulo, 'Alquiler' AS tipo, NULL AS precio, a.fecha_inicio, a.fecha_fin
                FROM alquilan a
                JOIN personas p ON a.id_cliente = p.id_personas
                JOIN videojuegos v ON a.id_videojuego = v.id_videojuego
            """;
            try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sqlAlquileres)) {
                while (rs.next()) {
                    modelo.addRow(new Object[] {
                        rs.getString("nombre"),
                        rs.getString("titulo"),
                        rs.getString("tipo"),
                        null,
                        rs.getString("fecha_inicio"),
                        rs.getString("fecha_fin")
                    });
                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar ventas: " + ex.getMessage());
        }
    }
}
