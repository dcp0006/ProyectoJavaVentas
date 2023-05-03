package Ventas;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Inicio {
	JFrame frame;
	private final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	private static JTextField usuario;
	private static JPasswordField contraseña;
	static int n = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					Inicio window = new Inicio();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Inicio() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(Inicio.class.getResource("/Ventas/Isolated-Logo.png")));
		frame.setBounds(600, 200, 600, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		tabbedPane.setBounds(-16, -37, 612, 612);
		frame.getContentPane().add(tabbedPane);
		frame.setTitle("BeeKeepIt");

		JPanel Intro = new JPanel();
		Intro.setBackground(new Color(255, 250, 205));
		tabbedPane.addTab("New tab", null, Intro, null);
		Intro.setLayout(null);
		usuario = new JTextField();
		usuario.setBounds(182, 274, 258, 39);
		Intro.add(usuario);
		usuario.setColumns(10);

		JLabel lblNewLabel = new JLabel("Usuario");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblNewLabel.setBounds(254, 223, 100, 50);
		Intro.add(lblNewLabel);

		contraseña = new JPasswordField();
		contraseña.setBounds(182, 411, 258, 39);
		Intro.add(contraseña);

		JLabel lblNewLabel_1 = new JLabel("Contraseña");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblNewLabel_1.setBounds(234, 350, 163, 50);
		Intro.add(lblNewLabel_1);
		JLabel Error = new JLabel(
				"<html>\r\nError, el usuario o contraseña introducidos ha sido incorrecto o no existe\r\n</html>");
		Error.setForeground(Color.RED);
		Error.setBounds(182, 301, 258, 70);
		Intro.add(Error);
		Error.setVisible(false);

		JButton btnNewButton = new JButton("ENTRAR");
		usuario.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				 if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						if (Conexion.loginUsuario(usuario.getText(), contraseña.getText())) {
							Interface a = new Interface();
							a.frame.setVisible(true);
							frame.setVisible(false);
						} else {
							Error.setVisible(true);
							System.out.println("fallo");
						}
				 }
			}
		});
		contraseña.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				 if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						if (Conexion.loginUsuario(usuario.getText(), contraseña.getText())) {
							Interface a = new Interface();
							a.frame.setVisible(true);
							frame.setVisible(false);
						} else {
							Error.setVisible(true);
							System.out.println("fallo");
						}
				 }
			}
		});
		btnNewButton.setBackground(new Color(222, 184, 135));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (Conexion.loginUsuario(usuario.getText(), contraseña.getText())) {
					Interface a = new Interface();
					a.frame.setVisible(true);
					frame.setVisible(false);
				} else {
					Error.setVisible(true);
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.setBounds(79, 485, 151, 50);
		Intro.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("REGISTRAR");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					if (Conexion.loginUsuario(usuario.getText(), contraseña.getText())) {
						JOptionPane.showMessageDialog(null,
								"Este usuario ya existe,introduzca otros datos o inicie sesion");
					} else {
						Conexion.registroUsuario(usuario.getText(), contraseña.getText());
						JOptionPane.showMessageDialog(null, "Usuario creado");
					}

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1.setBackground(new Color(222, 184, 135));
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_1.setBounds(381, 485, 151, 50);
		Intro.add(btnNewButton_1);

		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(Inicio.class.getResource("/Ventas/LogoName001.png")));
		lblNewLabel_3.setBounds(72, 0, 652, 231);
		Intro.add(lblNewLabel_3);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setIcon(new ImageIcon(Inicio.class.getResource("/Ventas/BG-reducedTile.png")));
		lblNewLabel_2.setBounds(10, 11, 587, 562);
		Intro.add(lblNewLabel_2);

	}
}
