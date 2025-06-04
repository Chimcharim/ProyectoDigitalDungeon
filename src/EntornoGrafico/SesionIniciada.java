package EntornoGrafico;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SesionIniciada extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				SesionIniciada frame = new SesionIniciada();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public SesionIniciada() {
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(SesionIniciada.class.getResource("/Imagenes/DigitalDungeonlogo.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 500);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenuItem mntmVerJuegos = new JMenuItem("Ver Juegos");
		menuBar.add(mntmVerJuegos);

		JMenuItem mntmCyberGaming = new JMenuItem("Cyber Gaming");
		menuBar.add(mntmCyberGaming);

		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 85));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Acción al hacer clic en "Ver Juegos"
		mntmVerJuegos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarJuegos();
			}
		});

		// Acción al hacer clic en "Cyber Gaming"
		mntmCyberGaming.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarZonaCyber();
			}
		});
	}

	private void mostrarJuegos() {
		contentPane.removeAll(); // Limpia el panel

		String[] juegos = {
			"Pokémon Esmeralda", 
			"Elden Ring", 
			"Street Fighter",
			"Minecraft",
			"The Legend of Zelda",
			"Battlefield 2042",
			"FIFA 25"
		};

		int y = 30;
		for (String juego : juegos) {
			JLabel lblJuego = new JLabel(juego);
			lblJuego.setForeground(Color.WHITE);
			lblJuego.setBounds(30, y, 200, 20);
			contentPane.add(lblJuego);

			JButton btnComprar = new JButton("Comprar");
			btnComprar.setBounds(300, y, 100, 25);
			btnComprar.addActionListener(e -> 
				JOptionPane.showMessageDialog(this, "Has comprado " + juego));
			contentPane.add(btnComprar);

			y += 50;
		}

		contentPane.revalidate();
		contentPane.repaint();
	}

	private void mostrarZonaCyber() {
		contentPane.removeAll(); // Limpia el panel

		JLabel lblTitulo = new JLabel("Reserva en el Cyber");
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setBounds(30, 30, 200, 20);
		contentPane.add(lblTitulo);

		JLabel lblSelecciona = new JLabel("Selecciona la duración:");
		lblSelecciona.setForeground(Color.WHITE);
		lblSelecciona.setBounds(30, 70, 200, 20);
		contentPane.add(lblSelecciona);

		String[] opciones = { "1 hora", "2 horas", "3 horas" };
		JComboBox<String> comboDuracion = new JComboBox<>(opciones);
		comboDuracion.setBounds(200, 70, 150, 25);
		contentPane.add(comboDuracion);

		JButton btnReservar = new JButton("Reservar");
		btnReservar.setBounds(150, 120, 120, 30);
		contentPane.add(btnReservar);

		btnReservar.addActionListener(e -> {
			String seleccion = (String) comboDuracion.getSelectedItem();
			JOptionPane.showMessageDialog(this, "Reserva hecha con éxito para " + seleccion);
		});

		contentPane.revalidate();
		contentPane.repaint();
	}
}
