package EntornoGrafico;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

public class PaginaDeRegistro extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PaginaDeRegistro frame = new PaginaDeRegistro();
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
	public PaginaDeRegistro() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(PaginaDeRegistro.class.getResource("/Imagenes/DigitalDungeonlogo.png")));
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
		
		JLabel lblNewLabel = new JLabel("Bienvenido al registro de Digital Dungeon");
		lblNewLabel.setFont(new Font("Segoe UI Black", Font.PLAIN, 17));
		lblNewLabel.setBounds(32, 11, 372, 24);
		panel.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(10, 46, 86, 20);
		panel.add(textField);
		textField.setColumns(10);
	}

}
