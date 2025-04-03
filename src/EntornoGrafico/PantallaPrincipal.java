package EntornoGrafico;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PantallaPrincipal extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel ContentPanel;
	private JTextField txtA;
	private JButton btnSalir;
	private JButton btnEntrar;
	private InicioDeSesion IS;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PantallaPrincipal frame = new PantallaPrincipal();
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
	public PantallaPrincipal() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(PantallaPrincipal.class.getResource("/Imagenes/DigitalDungeonlogo.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		ContentPanel = new JPanel();
		ContentPanel.setBackground(new Color(0, 0, 85));
		ContentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(ContentPanel);
		ContentPanel.setLayout(null);
		
		txtA = new JTextField();
		txtA.setFont(new Font("Sitka Small", Font.PLAIN, 15));
		txtA.setEditable(false);
		txtA.setForeground(Color.WHITE);
		txtA.setBackground(new Color(0, 0, 43));
		txtA.setBounds(70, 0, 297, 32);
		txtA.setText("BIENVENIDO A DIGITAL DUNGEON");
		ContentPanel.add(txtA);
		txtA.setColumns(10);
		
		btnSalir = new JButton("");
		btnSalir.setIcon(new ImageIcon(PantallaPrincipal.class.getResource("/Imagenes/salir (1).png")));
		btnSalir.addActionListener(this);
		btnSalir.setBackground(new Color(0, 0, 43));
		btnSalir.setBounds(264, 221, 93, 29);
		ContentPanel.add(btnSalir);
		
	
		btnEntrar = new JButton("");
		btnEntrar.setIcon(new ImageIcon(PantallaPrincipal.class.getResource("/Imagenes/entrar (1).png")));
		btnEntrar.addActionListener(this);
		btnEntrar.setBounds(83, 221, 93, 29);
		ContentPanel.add(btnEntrar);
		
		JLabel lblNewLabel = new JLabel("fondo");
		lblNewLabel.setBackground(new Color(0, 0, 43));
		lblNewLabel.setIcon(new ImageIcon(PantallaPrincipal.class.getResource("/Imagenes/_cb5f8b2d-a215-4281-a9b8-4bc4fd1fd199 (1).jpeg")));
		lblNewLabel.setBounds(0, 0, 434, 261);
		ContentPanel.add(lblNewLabel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnSalir)) {
			System.exit(WIDTH);
		}
		
		if(e.getSource().equals(btnEntrar)) {
			IS = new InicioDeSesion();
			IS.setVisible(true);
			dispose();
	}
}      
	}