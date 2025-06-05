package EntornoGrafico;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.sql.*;

public class EliminarVideojuego extends JFrame {

    JTable tabla;
    DefaultTableModel modelo;

    public EliminarVideojuego() {
        setTitle("Eliminar Videojuego");
        setSize(650, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setBackground(new Color(0, 0, 85));

        modelo = new DefaultTableModel(new String[]{"ID", "Título", "Plataforma", "Género", "Precio"}, 0);
        tabla = new JTable(modelo);
        tabla.setFillsViewportHeight(true);

        // Ocultar la columna de ID
        tabla.getColumnModel().getColumn(0).setMinWidth(0);
        tabla.getColumnModel().getColumn(0).setMaxWidth(0);
        tabla.getColumnModel().getColumn(0).setWidth(0);

        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setPreferredSize(new Dimension(600, 300));

        JPanel botones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        botones.setBackground(new Color(0, 0, 85));
        JButton btnEliminar = new JButton("Eliminar Seleccionado");
        JButton btnVolver = new JButton("Volver");

        btnEliminar.addActionListener(e -> eliminarSeleccionado());
        btnVolver.addActionListener(e -> {
            dispose();
            new MenuEmpleados().setVisible(true);
        });

        botones.add(btnVolver);
        botones.add(btnEliminar);

        panel.add(scroll, BorderLayout.CENTER);
        panel.add(botones, BorderLayout.SOUTH);

        add(panel);
        cargarVideojuegos();
    }

    private void cargarVideojuegos() {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/proyecto_cyber", "root", "");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM videojuegos")) {

            modelo.setRowCount(0);
            while (rs.next()) {
                modelo.addRow(new Object[]{
                    rs.getInt("id_videojuego"),
                    rs.getString("titulo"),
                    rs.getString("plataforma"),
                    rs.getString("genero"),
                    rs.getDouble("precio")
                });
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar videojuegos: " + ex.getMessage());
        }
    }

    private void eliminarSeleccionado() {
        int fila = tabla.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona un videojuego para eliminar.");
            return;
        }
        int id = (int) modelo.getValueAt(fila, 0);

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/proyecto_cyber", "root", "")) {

            // Verificar en compran
            PreparedStatement psCompran = conn.prepareStatement("SELECT COUNT(*) FROM compran WHERE id_videojuego = ?");
            psCompran.setInt(1, id);
            ResultSet rsCompran = psCompran.executeQuery();
            rsCompran.next();
            int ventas = rsCompran.getInt(1);

            // Verificar en alquilan
            PreparedStatement psAlquilan = conn.prepareStatement("SELECT COUNT(*) FROM alquilan WHERE id_videojuego = ?");
            psAlquilan.setInt(1, id);
            ResultSet rsAlquilan = psAlquilan.executeQuery();
            rsAlquilan.next();
            int alquileres = rsAlquilan.getInt(1);

            if (ventas > 0 || alquileres > 0) {
                JOptionPane.showMessageDialog(this, "No se puede eliminar el videojuego porque tiene ventas o alquileres registrados.");
                return;
            }

            // Si no hay registros, eliminar
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM videojuegos WHERE id_videojuego = ?");
            stmt.setInt(1, id);
            int eliminado = stmt.executeUpdate();
            if (eliminado > 0) {
                JOptionPane.showMessageDialog(this, "Videojuego eliminado.");
                modelo.removeRow(fila);
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al eliminar: " + ex.getMessage());
        }
    }
}