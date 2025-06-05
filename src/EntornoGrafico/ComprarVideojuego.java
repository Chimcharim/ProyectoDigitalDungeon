package EntornoGrafico;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ComprarVideojuego extends JFrame {

    private JTable tablaVideojuegos;
    private DefaultTableModel modeloTabla;

    public ComprarVideojuego() {
        setTitle("Comprar Videojuego");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(0, 0, 85));
        add(panel);

        modeloTabla = new DefaultTableModel();
        modeloTabla.setColumnIdentifiers(new String[] { "ID", "Título", "Plataforma", "Género", "Precio (€)" });
        tablaVideojuegos = new JTable(modeloTabla);

     // Ocultar la columna ID visualmente
     tablaVideojuegos.getColumnModel().getColumn(0).setMinWidth(0);
     tablaVideojuegos.getColumnModel().getColumn(0).setMaxWidth(0);
     tablaVideojuegos.getColumnModel().getColumn(0).setWidth(0);

     JScrollPane scroll = new JScrollPane(tablaVideojuegos);
     panel.add(scroll, BorderLayout.CENTER);
     
        JPanel botonesPanel = new JPanel();
        botonesPanel.setBackground(new Color(0, 0, 85));
        JButton btnComprar = new JButton("Comprar");
        JButton btnVolver = new JButton("Volver al Menú Clientes");
        botonesPanel.add(btnComprar);
        botonesPanel.add(btnVolver);
        panel.add(botonesPanel, BorderLayout.SOUTH);

        btnComprar.addActionListener(e -> comprarVideojuego());
        btnVolver.addActionListener(e -> {
            dispose();
            new MenuClientes().setVisible(true);
        });

        cargarVideojuegos();
    }

    private void cargarVideojuegos() {
        modeloTabla.setRowCount(0);
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/proyecto_cyber", "root", "");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM videojuegos")) {

            while (rs.next()) {
                modeloTabla.addRow(new Object[] {
                        rs.getInt("id_videojuego"),
                        rs.getString("titulo"),
                        rs.getString("plataforma"),
                        rs.getString("genero"),
                        rs.getDouble("precio")
                });
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar videojuegos: " + ex.getMessage());
        }
    }
//hola mundo
    private void comprarVideojuego() {
        int filaSeleccionada = tablaVideojuegos.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona un videojuego para comprar.");
            return;
        }

        int idVideojuego = (int) modeloTabla.getValueAt(filaSeleccionada, 0);

        // Obtener fecha actual en formato yyyy-MM-dd para MySQL
        String fechaCompra = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/proyecto_cyber", "root", "");
             PreparedStatement ps = conn.prepareStatement(
                     "INSERT INTO compran (id_cliente, id_videojuego, fecha_compra) VALUES (?, ?, ?)")) {

            ps.setInt(1, Sesion.idUsuario);
            ps.setInt(2, idVideojuego);
            ps.setString(3, fechaCompra);

            int filas = ps.executeUpdate();
            if (filas > 0) {
                JOptionPane.showMessageDialog(this, "Compra realizada con éxito.");
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo realizar la compra.");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al realizar la compra: " + ex.getMessage());
        }
    }
}
