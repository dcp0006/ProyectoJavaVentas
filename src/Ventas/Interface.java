package Ventas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicTabbedPaneUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.JTextArea;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JTextPane;
import javax.swing.border.MatteBorder;
import javax.swing.JEditorPane;
import javax.swing.table.TableModel;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.JComboBox;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

public class Interface {

	JFrame frame;
	private final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	private final JPanel Ventas = new JPanel();
	private final JPanel Clientes = new JPanel();
	private final JPanel Proveedores = new JPanel();
	private final JPanel Configuracion = new JPanel();
	static DefaultTableModel modelo = new DefaultTableModel();;
	private JTable table=new JTable(modelo);
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTable table_1;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTable table_2;
	private JTextField textField_5;
	private static Clip clip;
    private static boolean playing;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					
					Interface window = new Interface();
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
	public Interface() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	  public synchronized void playAudio(String audioFilePath) throws Exception {
	        try {
				if (playing) {
				    return;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        try {
				playing = true;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	        // Cargar el archivo de audio
	        File audioFile = new File(audioFilePath);
	        AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);

	        // Obtener el objeto Clip para reproducir el audio
	        clip = AudioSystem.getClip();
	        clip.open(audioStream);

	        // Reproducir el audio en un hilo separado
	        Thread playThread = new Thread(new Runnable() {
	            public void run() {
	                try {
	                    clip.start();
	                    clip.drain();
	                } finally {
	                    playing = false;
	                    clip.close();
	                }
	            }
	        });
	        playThread.start();
	    }
	
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 250, 205));
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(Interface.class.getResource("/Ventas/Isolated-Logo.png")));
		frame.setResizable(false);
		frame.setBounds(400, 100, 1000, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		tabbedPane.setBackground(new Color(255, 250, 205));
		tabbedPane.setBounds(-5, 41, 995, 757);
		frame.getContentPane().add(tabbedPane);
		
		JPanel Inventario = new JPanel();
		Inventario.setBackground(new Color(255, 248, 220));
		tabbedPane.addTab("Inventario", null, Inventario, null);
		Inventario.setLayout(null);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setFillsViewportHeight(true);
		table.setBorder(new MatteBorder(0, 1, 1, 1, (Color) new Color(0, 0, 0)));
		table.setBounds(56, 85, 450, 575);
		modelo.addColumn("Cantidad");
		modelo.addColumn("Producto");
		modelo.addColumn("Precio");
		try {
			playAudio(null);
		} catch (Exception e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		try {
			Conexion.cargarTablaInven();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		
		Inventario.add(table);
		
		textField = new JTextField();
		textField.setBounds(553, 79, 111, 32);
		Inventario.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(674, 79, 162, 32);
		Inventario.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(843, 79, 111, 32);
		Inventario.add(textField_2);
		
		JLabel lblNewLabel = new JLabel("     Cantidad           Producto             Precio");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBackground(new Color(240, 240, 240));
		lblNewLabel.setBounds(535, 29, 402, 32);
		Inventario.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Añadir");
		btnNewButton.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnNewButton.setBackground(new Color(222, 184, 135));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					
					modelo.addRow(new Object[] {Integer.valueOf(textField.getText()), textField_1.getText(), Double.valueOf(textField_2.getText())});
					Conexion.añadirProducto(Integer.valueOf(textField.getText()), textField_1.getText(), Double.valueOf(textField_2.getText()));
				
					
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "campos no completos o incorrectos");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.setBounds(659, 153, 190, 65);
		Inventario.add(btnNewButton);
		
		JTextArea textArea = new JTextArea();
		textArea.setBorder(new LineBorder(new Color(0, 0, 0)));
		textArea.setBounds(760, 294, 198, 366);
		Inventario.add(textArea);
		
		JButton btnNewButton_1 = new JButton("Actualizar\r\n");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table.removeAll();
				try {
					
					Conexion.actualizarProducto(Integer.valueOf(textField.getText()),textField_1.getText(),Double.valueOf(textField_2.getText()));
					
				} catch (NumberFormatException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
			}
		});
		btnNewButton_1.setBackground(new Color(222, 184, 135));
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_1.setBounds(535, 298, 198, 65);
		Inventario.add(btnNewButton_1);
		
		JButton btnEliminar = new JButton("Eliminar\r\n");
		btnEliminar.setBackground(new Color(222, 184, 135));
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String valor=(String) table.getValueAt(table.getSelectedRow(),1);
				int n=table.getSelectedRow();
				modelo.removeRow(n);
				try {
					Conexion.eliminarProducto(valor);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}catch(Exception j) {
					JOptionPane.showMessageDialog(null,"Por favor seleccione una columna");
				}
			}
		});
		btnEliminar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnEliminar.setBounds(535, 395, 198, 65);
		Inventario.add(btnEliminar);
		
		JButton btnSeleccionar = new JButton("Seleccionar\r\n");
		btnSeleccionar.setBackground(new Color(222, 184, 135));
		btnSeleccionar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnSeleccionar.setBounds(535, 596, 198, 65);
		Inventario.add(btnSeleccionar);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(56, 79, 450, 582);
		Inventario.add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBackground(UIManager.getColor("Button.highlight"));
		panel_1.setBounds(56, 29, 151, 53);
		Inventario.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_7 = new JLabel("Cantidad");
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblNewLabel_7.setBounds(22, 11, 105, 31);
		panel_1.add(lblNewLabel_7);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1_1.setBackground(UIManager.getColor("Button.highlight"));
		panel_1_1.setBounds(206, 29, 151, 53);
		Inventario.add(panel_1_1);
		panel_1_1.setLayout(null);
		
		JLabel lblNewLabel_7_1 = new JLabel("Producto");
		lblNewLabel_7_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7_1.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblNewLabel_7_1.setBounds(20, 11, 100, 31);
		panel_1_1.add(lblNewLabel_7_1);
		
		JPanel panel_1_2 = new JPanel();
		panel_1_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1_2.setBackground(UIManager.getColor("Button.highlight"));
		panel_1_2.setBounds(355, 29, 151, 53);
		Inventario.add(panel_1_2);
		panel_1_2.setLayout(null);
		
		JLabel lblNewLabel_7_2 = new JLabel("Precio");
		lblNewLabel_7_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7_2.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblNewLabel_7_2.setBounds(20, 11, 100, 31);
		panel_1_2.add(lblNewLabel_7_2);
		
		JButton btnEliminarTodo = new JButton("Eliminar\r\n todo");
		btnEliminarTodo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Conexion.eliminarTodosProducto();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				for (int i = table.getRowCount() - 1; i >= 0; i--) {
			        modelo.removeRow(i);
			    }
			}
		});
		btnEliminarTodo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnEliminarTodo.setBackground(new Color(222, 184, 135));
		btnEliminarTodo.setBounds(535, 496, 198, 65);
		Inventario.add(btnEliminarTodo);
		Ventas.setBackground(new Color(255, 248, 220));
		
		tabbedPane.addTab("Ventas", null, Ventas, null);
		Ventas.setLayout(null);
		
		table_1 = new JTable((TableModel) null);
		table_1.setFillsViewportHeight(true);
		table_1.setCellSelectionEnabled(true);
		table_1.setBorder(new MatteBorder(0, 1, 1, 1, (Color) new Color(0, 0, 0)));
		table_1.setBounds(56, 85, 419, 575);
		Ventas.add(table_1);
		
		JLabel lblNewLabel_2 = new JLabel("Vende:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(597, 28, 150, 50);
		Ventas.add(lblNewLabel_2);
		
		textField_3 = new JTextField();
		textField_3.setBounds(597, 67, 288, 38);
		Ventas.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Mensaje de error");
		lblNewLabel_3.setForeground(new Color(255, 0, 0));
		lblNewLabel_3.setBounds(597, 116, 150, 50);
		Ventas.add(lblNewLabel_3);
		
		JLabel lblNewLabel_2_1 = new JLabel("Cant:");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2_1.setBounds(597, 152, 150, 50);
		Ventas.add(lblNewLabel_2_1);
		
		textField_4 = new JTextField();
		textField_4.setBounds(661, 162, 86, 38);
		Ventas.add(textField_4);
		textField_4.setColumns(10);
		
		JButton btnNewButton_2 = new JButton("Vender");
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_2.setBounds(597, 268, 187, 50);
		Ventas.add(btnNewButton_2);
		
		JButton btnNewButton_2_1 = new JButton("Info Ventas");
		btnNewButton_2_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_2_1.setBounds(597, 374, 187, 50);
		Ventas.add(btnNewButton_2_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.setBounds(56, 21, 210, 63);
		Ventas.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_8 = new JLabel("Producto");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_8.setBounds(10, 15, 175, 31);
		panel_2.add(lblNewLabel_8);
		
		JPanel panel_2_1 = new JPanel();
		panel_2_1.setBackground(Color.WHITE);
		panel_2_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2_1.setBounds(266, 21, 209, 63);
		Ventas.add(panel_2_1);
		panel_2_1.setLayout(null);
		
		JLabel lblNewLabel_8_1 = new JLabel("P.V.P");
		lblNewLabel_8_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_8_1.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblNewLabel_8_1.setBounds(10, 15, 175, 31);
		panel_2_1.add(lblNewLabel_8_1);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Precio:");
		lblNewLabel_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2_1_1.setBounds(597, 211, 63, 50);
		Ventas.add(lblNewLabel_2_1_1);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(661, 213, 86, 38);
		Ventas.add(textField_5);
		Clientes.setBackground(new Color(255, 248, 220));
		
		tabbedPane.addTab("Clientes", null, Clientes, null);
		Clientes.setLayout(null);
		
		table_2 = new JTable();
		table_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		table_2.setBounds(179, 125, 616, 518);
		Clientes.add(table_2);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_3.setBounds(179, 65, 206, 60);
		Clientes.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblNewLabel_9 = new JLabel("Cliente");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblNewLabel_9.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_9.setBounds(10, 11, 186, 38);
		panel_3.add(lblNewLabel_9);
		
		JPanel panel_3_1 = new JPanel();
		panel_3_1.setBackground(Color.WHITE);
		panel_3_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_3_1.setBounds(384, 65, 206, 60);
		Clientes.add(panel_3_1);
		panel_3_1.setLayout(null);
		
		JLabel lblNewLabel_9_1 = new JLabel("Venta");
		lblNewLabel_9_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_9_1.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblNewLabel_9_1.setBounds(10, 11, 186, 38);
		panel_3_1.add(lblNewLabel_9_1);
		
		JPanel panel_3_1_1 = new JPanel();
		panel_3_1_1.setBackground(Color.WHITE);
		panel_3_1_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_3_1_1.setBounds(590, 65, 205, 60);
		Clientes.add(panel_3_1_1);
		panel_3_1_1.setLayout(null);
		
		JLabel lblNewLabel_9_2 = new JLabel("Contacto");
		lblNewLabel_9_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_9_2.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblNewLabel_9_2.setBounds(10, 11, 186, 38);
		panel_3_1_1.add(lblNewLabel_9_2);
		Proveedores.setBackground(new Color(255, 248, 220));
		
		tabbedPane.addTab("Proveedores", null, Proveedores, null);
		Proveedores.setLayout(null);
		Configuracion.setBackground(new Color(255, 248, 220));
		
		tabbedPane.addTab("Configuracion", null, Configuracion, null);
		Configuracion.setLayout(null);
		String atle=String.valueOf((int)(Math.random()*(100-1+1)+1));
		JLabel lblNewLabel_4 = new JLabel("Capacidad:   "+atle+"%");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_4.setBounds(100, 121, 271, 75);
		Configuracion.add(lblNewLabel_4);
		
		atle=String.valueOf((int)(Math.random()*(365-1+1)+1));
		JLabel lblNewLabel_5 = new JLabel("Dias restantes de servicio:   "+atle);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_5.setBounds(100, 272, 342, 85);
		Configuracion.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Usuario:    "+Inicio.usuarioFinal);
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_6.setBounds(100, 432, 342, 75);
		Configuracion.add(lblNewLabel_6);
		
		JButton btnNewButton_3 = new JButton("Cambiar contraseña");
		btnNewButton_3.setBackground(new Color(222, 184, 135));
		btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_3.setBounds(427, 437, 459, 64);
		Configuracion.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Prolongar subscripción");
		btnNewButton_4.setBackground(new Color(222, 184, 135));
		btnNewButton_4.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_4.setBounds(427, 277, 459, 75);
		Configuracion.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("Aumentar capacidad");
		btnNewButton_5.setBackground(new Color(222, 184, 135));
		btnNewButton_5.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_5.setBounds(427, 121, 466, 75);
		Configuracion.add(btnNewButton_5);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(Interface.class.getResource("/Ventas/Isolated-Logo.png")));
		lblNewLabel_1.setBounds(931, 0, 59, 64);
		frame.getContentPane().add(lblNewLabel_1);
		frame.setTitle("BeeKeepIt");
		
		
		//tabbedPane.setUI(new CustomTabbedPaneUI());			//Ecplise no lo detecta bien
	}
	public class CustomTabbedPaneUI extends BasicTabbedPaneUI {

	    @Override
	    protected void paintTabBackground(Graphics g, int tabPlacement, int tabIndex, int x, int y, int w, int h, boolean isSelected) {
	        Graphics2D g2d = (Graphics2D) g.create();
	        g2d.setColor(Color.WHITE);
	        g2d.fillRect(x, y, w, h);
	        g2d.setColor(Color.GRAY);
	        g2d.drawRect(x, y, w - 1, h - 1);
	        if (isSelected) {
	            g2d.setColor(Color.BLUE);
	            g2d.drawLine(x, y + h - 1, x + w - 1, y + h - 1);
	        }
	        g2d.dispose();
	    }

	    @Override
	    protected void paintTabBorder(Graphics g, int tabPlacement, int tabIndex, int x, int y, int w, int h, boolean isSelected) {
	        super.paintTabBorder(g, tabPlacement, tabIndex, x, y, w, h, isSelected);
	       
	        	g.setColor(Color.black);
	            g.drawLine(x, y, x + w - 1, y);
	        
	    }
	  
}
}
