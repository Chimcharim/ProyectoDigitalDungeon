package EntornoGrafico;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.JLabel;
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
import java.awt.event.ActionEvent;

public class InicioDeSesion extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private InicioDeSesion RE;
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
	public InicioDeSesion() {
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
		
		JLabel lblNewLabel_1 = new JLabel("CONTRASEÑA:");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Segoe UI Black", Font.PLAIN, 11));
		lblNewLabel_1.setBounds(10, 67, 87, 14);
		panel.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(92, 26, 93, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(92, 64, 93, 20);
		panel.add(passwordField);
		
		Checkbox checkbox = new Checkbox("Soy empleado");
		checkbox.setBounds(10, 104, 175, 22);
		panel.add(checkbox);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setIcon(new ImageIcon(InicioDeSesion.class.getResource("/Imagenes/inicio_1.png")));
		btnNewButton.setBounds(10, 157, 175, 43);
		panel.add(btnNewButton);
		
		JButton btnRegistro = new JButton("");
		
			
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
