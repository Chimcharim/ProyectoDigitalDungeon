package EntornoGrafico;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AlquilarVideojuego extends JFrame {

    private static final long serialVersionUID = 1L;

    private JTextField campoNombre;
    private JDateChooser calendario;
    private JTable tabla;
    private DefaultTableModel modelo;

    public AlquilarVideojuego() {
        setTitle("Alquilar Videojuego");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(0, 0, 85));
        add(panel);

        JPanel superior = new JPanel(new GridLayout(3, 2, 10, 10));
        superior.setBackground(new Color(0, 0, 85));
        superior.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        superior.add(crearLabel("Nombre del videojuego:"));
        campoNombre = new JTextField();
        superior.add(campoNombre);

        superior.add(crearLabel("Selecciona fecha de fin:"));
        calendario = new JDateChooser();
        calendario.setDate(new Date()); // valor por defecto
        superior.add(calendario);

        JButton btnAlquilar = new JButton("Alquilar");
        btnAlquilar.addActionListener(e -> alquilarVideojuego());
        superior.add(btnAlquilar);

        JButton btnVolver = new JButton("Volver al Menú Clientes");
        btnVolver.addActionListener(e -> {
            dispose();
            new MenuClientes().setVisible(true);
        });
        superior.add(btnVolver);

        panel.add(superior, BorderLayout.NORTH);

        modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(new String[] {"Título", "Fecha Inicio", "Fecha Fin"});
        tabla = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tabla);
        panel.add(scroll, BorderLayout.CENTER);
    }

    private JLabel crearLabel(String texto) {
        JLabel lbl = new JLabel(texto);
        lbl.setForeground(Color.WHITE);
        return lbl;
    }

    private void alquilarVideojuego() {
        String titulo = campoNombre.getText().trim();

        if (titulo.isEmpty() || calendario.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Introduce el nombre del videojuego y selecciona una fecha.");
            return;
        }

        String fechaInicio = "2025-06-05"; // formato compatible con MySQL
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String fechaFin = sdf.format(calendario.getDate());

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/proyecto_cyber", "root", "")) {

            // Obtener el ID del videojuego
            int idVideojuego = -1;
            String consulta = "SELECT id_videojuego FROM videojuegos WHERE titulo = ?";
            try (PreparedStatement ps = conn.prepareStatement(consulta)) {
                ps.setString(1, titulo);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    idVideojuego = rs.getInt("id_videojuego");
                } else {
                    JOptionPane.showMessageDialog(this, "Videojuego no encontrado.");
                    return;
                }
            }

            // Insertar en la tabla alquilan
            String insert = "INSERT INTO alquilan (id_cliente, id_videojuego, fecha_inicio, fecha_fin) VALUES (?, ?, ?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(insert)) {
                ps.setInt(1, Sesion.idUsuario);
                ps.setInt(2, idVideojuego);
                ps.setString(3, fechaInicio);
                ps.setString(4, fechaFin);
                ps.executeUpdate();
            }

            modelo.addRow(new Object[] {titulo, fechaInicio, fechaFin});
            JOptionPane.showMessageDialog(this, "Videojuego alquilado correctamente.");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al alquilar: " + e.getMessage());
        }
    }
}
