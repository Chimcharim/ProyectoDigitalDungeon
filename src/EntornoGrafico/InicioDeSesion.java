package EntornoGrafico;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import BaseDatos.ConexionBD;

import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Desktop;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JSlider;
import java.awt.Checkbox;
import java.awt.Button;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Panel;
import java.awt.event.ActionListener;

import java.net.URI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.awt.event.ActionEvent;

public class InicioDeSesion extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private InicioDeSesion RE;

	private Object btnInicio;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InicioDeSesion frame = new InicioDeSesion();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public  InicioDeSesion() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(InicioDeSesion.class.getResource("/Imagenes/DigitalDungeonlogo.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 85));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 414, 239);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("USUARIO:");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBackground(new Color(255, 0, 0));
		lblNewLabel.setFont(new Font("Segoe UI Black", Font.PLAIN, 11));
		lblNewLabel.setBounds(10, 29, 72, 14);
		panel.add(lblNewLabel);
		
		JLabel lblpassw = new JLabel("CONTRASEÑA:");
		lblpassw.setForeground(new Color(255, 255, 255));
		lblpassw.setFont(new Font("Segoe UI Black", Font.PLAIN, 11));
		lblpassw.setBounds(10, 67, 87, 14);
		panel.add(lblpassw);
		
		JTextField txtUsuario = new JTextField();
		txtUsuario.setBounds(92, 26, 93, 20);
		panel.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		JPasswordField txtpassw = new JPasswordField();
		txtpassw.setBounds(92, 64, 93, 20);
		panel.add(txtpassw);
		
		
		
		JButton btnInicio = new JButton("");
		btnInicio.setIcon(new ImageIcon(InicioDeSesion.class.getResource("/Imagenes/inicio_1.png")));
		btnInicio.setBounds(10, 157, 175, 43);
		panel.add(btnInicio);
		
	       // Evento para botón de iniciar sesión
		btnInicio.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String nombre = txtUsuario.getText().trim();
		        String contraseña = new String(txtpassw.getPassword());

		        if (nombre.isEmpty() || contraseña.isEmpty()) {
		            JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
		            return;
		        }

		        try (Connection con = ConexionBD.getConexion()) {
		            String query = "SELECT id_personas, rol FROM personas WHERE nombre = ? AND Contraseña = ?";
		            PreparedStatement ps = con.prepareStatement(query);
		            ps.setString(1, nombre);
		            ps.setString(2, contraseña);

		            ResultSet rs = ps.executeQuery();
		            if (rs.next()) {
		                Sesion.idUsuario = rs.getInt("id_personas");
		                String rol = rs.getString("rol");

		                JOptionPane.showMessageDialog(null, "Inicio de sesión exitoso");

		                if (rol.equalsIgnoreCase("cliente")) {
		                    MenuClientes menuCliente = new MenuClientes();
		                    menuCliente.setVisible(true);
		                } else if (rol.equalsIgnoreCase("empleado")) {
		                    MenuEmpleados menuEmpleados = new MenuEmpleados();
		                    menuEmpleados.setVisible(true);
		                } else {
		                    JOptionPane.showMessageDialog(null, "Rol no reconocido", "Error", JOptionPane.ERROR_MESSAGE);
		                    return;
		                }

		                dispose();
		            } else {
		                JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
		            }

		        } catch (Exception ex) {
		            JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
		            ex.printStackTrace();
		        }
		    }
		});

		JButton btnRegistro = new JButton("");
        btnRegistro.setIcon(new ImageIcon(InicioDeSesion.class.getResource("/Imagenes/Registro.png")));
        btnRegistro.setBounds(242, 132, 99, 32);
        panel.add(btnRegistro);
        
        // ActionListener para el botón btnRegistro
        btnRegistro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI("http://localhost/proyecto_cyber/code.php")); // Cambia la URL según sea necesario
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
		
	
		
			
			btnRegistro.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			        PaginaDeRegistro ventanaRegistro = new PaginaDeRegistro();
			        ventanaRegistro.setVisible(true);
			        dispose(); 
			    }
			
		});
		btnRegistro.setIcon(new ImageIcon(InicioDeSesion.class.getResource("/Imagenes/Registro.png")));
		btnRegistro.setBounds(242, 132, 99, 32);
		panel.add(btnRegistro);
		
		JLabel lblNewLabel_2 = new JLabel("¿Aún no tienes cuenta en Digital Dungeon? ");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setBounds(195, 18, 209, 63);
		panel.add(lblNewLabel_2);
		lblNewLabel_2.setFont(new Font("Segoe UI Black", Font.PLAIN, 9));
		
		JLabel lblNewLabel_3 = new JLabel("Empieza tu aventura hoy");
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_3.setBounds(231, 67, 153, 14);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("New label");
		lblNewLabel_4.setIcon(new ImageIcon(InicioDeSesion.class.getResource("/Imagenes/FondoRegistroreal.jpg")));
		lblNewLabel_4.setBounds(0, 0, 414, 239);
		panel.add(lblNewLabel_4);
	}
}
