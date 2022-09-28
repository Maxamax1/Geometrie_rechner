import java.awt.EventQueue;
import java.awt.Graphics2D;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.CardLayout;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.awt.event.ActionEvent;
import javax.swing.JLayeredPane;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import java.awt.FlowLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JRadioButton;
import javax.swing.table.DefaultTableModel;

public class Main_Geometrie {
	// Variablen
	private JFrame frmGeometrie;
	private CardLayout myCL;
	public JTextField tf_Dreieck_a;
	public JTextField tf_Dreieck_b;
	public JTextField tf_Dreieck_c;
	public JTextField tf_Dreieck_alpha;
	public JTextField tf_Dreieck_beta;
	public JTextField tf_Dreieck_gamma;
	private JTextField tf_Dreieck_heighta;
	private JTextField tf_Dreieck_heightb;
	private JTextField tf_Dreieck_heightc;
	public JTextField tf_Dreieck_Flaeche;
	public JTextField tf_Dreieck_Umfang;
	public JTextField tf_Kreis_r;
	public JTextField tf_Kreis_d;
	public JTextField tf_Kreis_U;
	public JTextField tf_Kreis_A;
	public JTextField tf_Quadrat_sidea;
	public JTextField tf_Quadrat_U;
	public JTextField tf_Quadrat_A;
	public JTextField tf_Quadrat_diag;
	public JTable database;
	private DefaultTableModel tableModel;
	private JTextField tf_Rechteck_diag;
	private JTextField tf_Rechteck_delta;
	private JTextField tf_Rechteck_gamma;
	private JTextField tf_Rechteck_sideb;
	private JTextField tf_Rechteck_umfang;
	private JTextField tf_Rechteck_beta;
	private JTextField tf_Rechteck_sidea;
	private JTextField tf_Rechteck_flaeche;
	private JTextField tf_Rechteck_alpha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					String configFilePath = "config.cfg";
					FileInputStream propsInput;
					try {
						propsInput = new FileInputStream(configFilePath);
					} catch (FileNotFoundException f) {
						try {
							List<String> lines = Arrays.asList("// MySQL oder SQLite", "DB_TYPE=SQLite",
									"DB_HOST=localhost", "DB_TABLE=tai21_geometrie", "DB_USER=root",
									"DB_PASSWORD=1111");
							Path file = Paths.get("config.cfg");
							Files.write(file, lines, StandardCharsets.UTF_8);
						} catch (IOException e) {
							e.printStackTrace();

						}
						JFrame frmerror = new JFrame();
						JOptionPane.showMessageDialog(frmerror,
								"Config not Found. \na new Config has been Created. \nUsing Default Config.", "Error",
								JOptionPane.INFORMATION_MESSAGE);
						propsInput = new FileInputStream(configFilePath);
					}
					Properties prop = new Properties();
					prop.load(propsInput);
					// System.out.println(prop.getProperty("DB_USER"));
					// for the Debug: jdbc:mysql://localhost/tai21_geometrie?profileSQL=true
					String url = "jdbc:mysql://" + prop.getProperty("DB_HOST") + "/" + prop.getProperty("DB_TABLE")
							+ "?profileSQL=true";
					String user = prop.getProperty("DB_USER");
					String pwd = prop.getProperty("DB_PASSWORD");
					String type = prop.getProperty("DB_TYPE");

					if (Datenbank.testDB(url, user, pwd, type)) {
						Main_Geometrie window = new Main_Geometrie();
						window.frmGeometrie.setVisible(true);
					} else {
						JFrame frmerror = new JFrame();
						JOptionPane.showMessageDialog(frmerror, "Cannot Connect to Database.", "Error",
								JOptionPane.ERROR_MESSAGE);
					}

				} catch (Exception e) {
					e.printStackTrace();
					System.exit(1);
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main_Geometrie() {
		myCL = new CardLayout();
		initialize();
		myCL.show(frmGeometrie.getContentPane(), "panel_Main");
	}

	/**
	 * Initialize the contents of the frame.
	 */

	private void initialize() {
		frmGeometrie = new JFrame();
		frmGeometrie.setTitle("Geometrie");
		frmGeometrie.setResizable(false);
		frmGeometrie.setBounds(100, 100, 900, 600);
		frmGeometrie.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmGeometrie.getContentPane().setLayout(myCL);

		JLayeredPane layeredPane_Main = new JLayeredPane();
		frmGeometrie.getContentPane().add(layeredPane_Main, "panel_Main");

		JPanel panel_Main_1 = new JPanel();
		panel_Main_1.setBorder(null);
		panel_Main_1.setOpaque(false);

		JPanel panel_Main_2 = new JPanel();
		GroupLayout gl_layeredPane_Main = new GroupLayout(layeredPane_Main);
		gl_layeredPane_Main.setHorizontalGroup(
				gl_layeredPane_Main.createParallelGroup(Alignment.LEADING).addGap(0, 884, Short.MAX_VALUE)
						.addComponent(panel_Main_1, GroupLayout.PREFERRED_SIZE, 884, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_Main_2, GroupLayout.PREFERRED_SIZE, 884, GroupLayout.PREFERRED_SIZE));
		gl_layeredPane_Main.setVerticalGroup(
				gl_layeredPane_Main.createParallelGroup(Alignment.LEADING).addGap(0, 539, Short.MAX_VALUE)
						.addComponent(panel_Main_1, GroupLayout.PREFERRED_SIZE, 539, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_Main_2, GroupLayout.PREFERRED_SIZE, 539, GroupLayout.PREFERRED_SIZE));
		panel_Main_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblNewLabel = new JLabel("");
		// bild einf�gen
		try {
			BufferedImage image = ImageIO.read(getClass().getResourceAsStream("Dreieck.png"));
			lblNewLabel
					.setIcon(new ImageIcon(resizeImage(image, (int) Math.round(250 / 1), (int) Math.round(160 / 1))));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		panel_Main_2.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("");
		// bild einf�gen
		try {
			BufferedImage image = ImageIO.read(getClass().getResourceAsStream("Quadrat.png"));
			lblNewLabel_1
					.setIcon(new ImageIcon(resizeImage(image, (int) Math.round(295 / 1), (int) Math.round(284 / 1))));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		panel_Main_2.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("");
		// bild einf�gen
		try {
			BufferedImage image = ImageIO.read(getClass().getResourceAsStream("Rechteck.png"));
			lblNewLabel_2
					.setIcon(new ImageIcon(resizeImage(image, (int) Math.round(356 / 1), (int) Math.round(190 / 1))));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		panel_Main_2.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("");
		// bild einf�gen
		try {
			BufferedImage image = ImageIO.read(getClass().getResourceAsStream("Kreis.png"));
			lblNewLabel_3
					.setIcon(new ImageIcon(resizeImage(image, (int) Math.round(200 / 1), (int) Math.round(200 / 1))));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		panel_Main_2.add(lblNewLabel_3);

		JButton btnDreieck = new JButton("Dreieck");
		btnDreieck.setToolTipText("Hier kannst du ein Dreieck berechnen");
		btnDreieck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myCL.show(frmGeometrie.getContentPane(), "panel_Dreieck");
			}
		});
		FlowLayout fl_panel_Main_1 = new FlowLayout(FlowLayout.CENTER, 50, 250);
		panel_Main_1.setLayout(fl_panel_Main_1);
		panel_Main_1.add(btnDreieck);

		JButton btnQuadrat = new JButton("Quadrat");
		btnQuadrat.setToolTipText("Hier kannst du ein Quadrat berechnen");
		btnQuadrat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myCL.show(frmGeometrie.getContentPane(), "panel_Quadrat");
			}
		});
		panel_Main_1.add(btnQuadrat);

		JButton btnKreis = new JButton("Kreis");
		btnKreis.setToolTipText("Hier kannst du ein Kreis berechnen");
		btnKreis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myCL.show(frmGeometrie.getContentPane(), "panel_Kreis");
			}
		});
		panel_Main_1.add(btnKreis);

		JButton btnRechteck = new JButton("Rechteck");
		btnRechteck.setToolTipText("Hier kannst du ein Rechteck berechnen");
		btnRechteck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myCL.show(frmGeometrie.getContentPane(), "panel_Rechteck");
			}
		});
		panel_Main_1.add(btnRechteck);
		layeredPane_Main.setLayout(gl_layeredPane_Main);

		JLayeredPane layeredPane_Dreieck = new JLayeredPane();
		frmGeometrie.getContentPane().add(layeredPane_Dreieck, "panel_Dreieck");

		JPanel panel_Dreieck_1 = new JPanel();
		GridBagLayout gbl_panel_Dreieck_1 = new GridBagLayout();
		panel_Dreieck_1.setOpaque(false);
		gbl_panel_Dreieck_1.columnWidths = new int[] { 50, 0, 25, 0, 25, 0, 25, 50, 0, 0, 0 };
		gbl_panel_Dreieck_1.rowHeights = new int[] { 200, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel_Dreieck_1.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		gbl_panel_Dreieck_1.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		panel_Dreieck_1.setLayout(gbl_panel_Dreieck_1);

		JPanel panel_Dreieck_Bild = new JPanel();
		GroupLayout gl_layeredPane_Dreieck = new GroupLayout(layeredPane_Dreieck);
		gl_layeredPane_Dreieck.setHorizontalGroup(gl_layeredPane_Dreieck.createParallelGroup(Alignment.LEADING)
				.addComponent(panel_Dreieck_1, GroupLayout.PREFERRED_SIZE, 884, GroupLayout.PREFERRED_SIZE)
				.addComponent(panel_Dreieck_Bild, GroupLayout.PREFERRED_SIZE, 884, GroupLayout.PREFERRED_SIZE));
		gl_layeredPane_Dreieck.setVerticalGroup(gl_layeredPane_Dreieck.createParallelGroup(Alignment.LEADING)
				.addComponent(panel_Dreieck_1, GroupLayout.PREFERRED_SIZE, 539, GroupLayout.PREFERRED_SIZE)
				.addComponent(panel_Dreieck_Bild, GroupLayout.PREFERRED_SIZE, 539, GroupLayout.PREFERRED_SIZE));

		tf_Dreieck_Umfang = new JTextField();
		GridBagConstraints gbc_tf_Dreieck_Umfang = new GridBagConstraints();
		gbc_tf_Dreieck_Umfang.insets = new Insets(0, 0, 5, 5);
		gbc_tf_Dreieck_Umfang.fill = GridBagConstraints.HORIZONTAL;
		gbc_tf_Dreieck_Umfang.gridx = 8;
		gbc_tf_Dreieck_Umfang.gridy = 1;
		panel_Dreieck_1.add(tf_Dreieck_Umfang, gbc_tf_Dreieck_Umfang);
		tf_Dreieck_Umfang.setColumns(10);

		tf_Dreieck_Flaeche = new JTextField();
		GridBagConstraints gbc_tf_Dreieck_Flaeche = new GridBagConstraints();
		gbc_tf_Dreieck_Flaeche.insets = new Insets(0, 0, 5, 5);
		gbc_tf_Dreieck_Flaeche.fill = GridBagConstraints.HORIZONTAL;
		gbc_tf_Dreieck_Flaeche.gridx = 8;
		gbc_tf_Dreieck_Flaeche.gridy = 2;
		panel_Dreieck_1.add(tf_Dreieck_Flaeche, gbc_tf_Dreieck_Flaeche);
		tf_Dreieck_Flaeche.setColumns(10);

		JLabel lbl_Dreieck_Umradius_Ergebnis = new JLabel("");
		GridBagConstraints gbc_lbl_Dreieck_Umradius_Ergebnis = new GridBagConstraints();
		gbc_lbl_Dreieck_Umradius_Ergebnis.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_Dreieck_Umradius_Ergebnis.gridx = 8;
		gbc_lbl_Dreieck_Umradius_Ergebnis.gridy = 4;
		panel_Dreieck_1.add(lbl_Dreieck_Umradius_Ergebnis, gbc_lbl_Dreieck_Umradius_Ergebnis);

		JLabel lbl_Dreieck_Umradius = new JLabel("Umradius");
		GridBagConstraints gbc_lbl_Dreieck_Umradius = new GridBagConstraints();
		gbc_lbl_Dreieck_Umradius.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_Dreieck_Umradius.gridx = 7;
		gbc_lbl_Dreieck_Umradius.gridy = 4;
		panel_Dreieck_1.add(lbl_Dreieck_Umradius, gbc_lbl_Dreieck_Umradius);

		JLabel lbl_Dreieck_Inradius_Ergebnis = new JLabel("");
		GridBagConstraints gbc_lbl_Dreieck_Inradius_Ergebnis = new GridBagConstraints();
		gbc_lbl_Dreieck_Inradius_Ergebnis.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_Dreieck_Inradius_Ergebnis.gridx = 8;
		gbc_lbl_Dreieck_Inradius_Ergebnis.gridy = 3;
		panel_Dreieck_1.add(lbl_Dreieck_Inradius_Ergebnis, gbc_lbl_Dreieck_Inradius_Ergebnis);

		JLabel lbl_Dreieck_Inradius = new JLabel("Inradius");
		GridBagConstraints gbc_lbl_Dreieck_Inradius = new GridBagConstraints();
		gbc_lbl_Dreieck_Inradius.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_Dreieck_Inradius.gridx = 7;
		gbc_lbl_Dreieck_Inradius.gridy = 3;
		panel_Dreieck_1.add(lbl_Dreieck_Inradius, gbc_lbl_Dreieck_Inradius);

		tf_Dreieck_heightc = new JTextField();
		tf_Dreieck_heightc.setToolTipText("Hier musst du die H\u00F6he von c eingeben.");
		GridBagConstraints gbc_tf_Dreieck_heightc = new GridBagConstraints();
		gbc_tf_Dreieck_heightc.insets = new Insets(0, 0, 5, 5);
		gbc_tf_Dreieck_heightc.fill = GridBagConstraints.HORIZONTAL;
		gbc_tf_Dreieck_heightc.gridx = 6;
		gbc_tf_Dreieck_heightc.gridy = 3;
		panel_Dreieck_1.add(tf_Dreieck_heightc, gbc_tf_Dreieck_heightc);
		tf_Dreieck_heightc.setColumns(10);

		tf_Dreieck_heightb = new JTextField();
		tf_Dreieck_heightb.setToolTipText("Hier musst du die H\u00F6he von b eingeben.");
		GridBagConstraints gbc_tf_Dreieck_heightb = new GridBagConstraints();
		gbc_tf_Dreieck_heightb.insets = new Insets(0, 0, 5, 5);
		gbc_tf_Dreieck_heightb.fill = GridBagConstraints.HORIZONTAL;
		gbc_tf_Dreieck_heightb.gridx = 4;
		gbc_tf_Dreieck_heightb.gridy = 3;
		panel_Dreieck_1.add(tf_Dreieck_heightb, gbc_tf_Dreieck_heightb);
		tf_Dreieck_heightb.setColumns(10);

		JLabel lblHeightc = new JLabel("H\u00F6he c");
		GridBagConstraints gbc_lblHeightc = new GridBagConstraints();
		gbc_lblHeightc.insets = new Insets(0, 0, 5, 5);
		gbc_lblHeightc.gridx = 5;
		gbc_lblHeightc.gridy = 3;
		panel_Dreieck_1.add(lblHeightc, gbc_lblHeightc);

		JLabel lblHeightb = new JLabel("H\u00F6he b");
		GridBagConstraints gbc_lblHeightb = new GridBagConstraints();
		gbc_lblHeightb.insets = new Insets(0, 0, 5, 5);
		gbc_lblHeightb.gridx = 3;
		gbc_lblHeightb.gridy = 3;
		panel_Dreieck_1.add(lblHeightb, gbc_lblHeightb);

		JLabel lblDreieck_Umfang = new JLabel("Umfang");
		GridBagConstraints gbc_lblDreieck_Umfang = new GridBagConstraints();
		gbc_lblDreieck_Umfang.insets = new Insets(0, 0, 5, 5);
		gbc_lblDreieck_Umfang.gridx = 7;
		gbc_lblDreieck_Umfang.gridy = 1;
		panel_Dreieck_1.add(lblDreieck_Umfang, gbc_lblDreieck_Umfang);

		JLabel lblDreieck_Flaeche = new JLabel("Fl\u00E4che");
		GridBagConstraints gbc_lblDreieck_Flaeche = new GridBagConstraints();
		gbc_lblDreieck_Flaeche.insets = new Insets(0, 0, 5, 5);
		gbc_lblDreieck_Flaeche.gridx = 7;
		gbc_lblDreieck_Flaeche.gridy = 2;
		panel_Dreieck_1.add(lblDreieck_Flaeche, gbc_lblDreieck_Flaeche);

		JLabel lblDreieck_a = new JLabel("a");
		GridBagConstraints gbc_lblDreieck_a = new GridBagConstraints();
		gbc_lblDreieck_a.anchor = GridBagConstraints.EAST;
		gbc_lblDreieck_a.insets = new Insets(0, 0, 5, 5);
		gbc_lblDreieck_a.gridx = 1;
		gbc_lblDreieck_a.gridy = 1;
		panel_Dreieck_1.add(lblDreieck_a, gbc_lblDreieck_a);

		tf_Dreieck_a = new JTextField();
		tf_Dreieck_a.setToolTipText("Hier musst du die Seite a eingeben.\r\n");

		GridBagConstraints gbc_tf_Dreieck_a = new GridBagConstraints();
		gbc_tf_Dreieck_a.insets = new Insets(0, 0, 5, 5);
		gbc_tf_Dreieck_a.fill = GridBagConstraints.HORIZONTAL;
		gbc_tf_Dreieck_a.gridx = 2;
		gbc_tf_Dreieck_a.gridy = 1;
		panel_Dreieck_1.add(tf_Dreieck_a, gbc_tf_Dreieck_a);
		tf_Dreieck_a.setColumns(10);

		JLabel lblDreieck_b = new JLabel("b");
		GridBagConstraints gbc_lblDreieck_b = new GridBagConstraints();
		gbc_lblDreieck_b.anchor = GridBagConstraints.EAST;
		gbc_lblDreieck_b.insets = new Insets(0, 0, 5, 5);
		gbc_lblDreieck_b.gridx = 3;
		gbc_lblDreieck_b.gridy = 1;
		panel_Dreieck_1.add(lblDreieck_b, gbc_lblDreieck_b);

		tf_Dreieck_b = new JTextField();
		tf_Dreieck_b.setToolTipText("Hier musst du die Seite b eingeben.");
		GridBagConstraints gbc_tf_Dreieck_b = new GridBagConstraints();
		gbc_tf_Dreieck_b.insets = new Insets(0, 0, 5, 5);
		gbc_tf_Dreieck_b.fill = GridBagConstraints.HORIZONTAL;
		gbc_tf_Dreieck_b.gridx = 4;
		gbc_tf_Dreieck_b.gridy = 1;
		panel_Dreieck_1.add(tf_Dreieck_b, gbc_tf_Dreieck_b);
		tf_Dreieck_b.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("c");
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.gridx = 5;
		gbc_lblNewLabel_4.gridy = 1;
		panel_Dreieck_1.add(lblNewLabel_4, gbc_lblNewLabel_4);

		tf_Dreieck_c = new JTextField();
		tf_Dreieck_c.setToolTipText("Hier musst du die Seite c eingeben.");
		GridBagConstraints gbc_tf_Dreieck_c = new GridBagConstraints();
		gbc_tf_Dreieck_c.insets = new Insets(0, 0, 5, 5);
		gbc_tf_Dreieck_c.fill = GridBagConstraints.HORIZONTAL;
		gbc_tf_Dreieck_c.gridx = 6;
		gbc_tf_Dreieck_c.gridy = 1;
		panel_Dreieck_1.add(tf_Dreieck_c, gbc_tf_Dreieck_c);
		tf_Dreieck_c.setColumns(10);

		JLabel lblDreieck_Alpha = new JLabel("Alpha");
		GridBagConstraints gbc_lblDreieck_Alpha = new GridBagConstraints();
		gbc_lblDreieck_Alpha.anchor = GridBagConstraints.EAST;
		gbc_lblDreieck_Alpha.insets = new Insets(0, 0, 5, 5);
		gbc_lblDreieck_Alpha.gridx = 1;
		gbc_lblDreieck_Alpha.gridy = 2;
		panel_Dreieck_1.add(lblDreieck_Alpha, gbc_lblDreieck_Alpha);

		tf_Dreieck_alpha = new JTextField();
		tf_Dreieck_alpha.setToolTipText("Hier musst du den Winkel Alpha eingeben.");
		GridBagConstraints gbc_tf_Dreieck_alpha = new GridBagConstraints();
		gbc_tf_Dreieck_alpha.insets = new Insets(0, 0, 5, 5);
		gbc_tf_Dreieck_alpha.fill = GridBagConstraints.HORIZONTAL;
		gbc_tf_Dreieck_alpha.gridx = 2;
		gbc_tf_Dreieck_alpha.gridy = 2;
		panel_Dreieck_1.add(tf_Dreieck_alpha, gbc_tf_Dreieck_alpha);
		tf_Dreieck_alpha.setColumns(10);

		JLabel lblDreieck_Beta = new JLabel("Beta");
		GridBagConstraints gbc_lblDreieck_Beta = new GridBagConstraints();
		gbc_lblDreieck_Beta.anchor = GridBagConstraints.EAST;
		gbc_lblDreieck_Beta.insets = new Insets(0, 0, 5, 5);
		gbc_lblDreieck_Beta.gridx = 3;
		gbc_lblDreieck_Beta.gridy = 2;
		panel_Dreieck_1.add(lblDreieck_Beta, gbc_lblDreieck_Beta);

		tf_Dreieck_beta = new JTextField();
		tf_Dreieck_beta.setToolTipText("Hier musst du den Winkel Beta eingeben.");
		GridBagConstraints gbc_tf_Dreieck_beta = new GridBagConstraints();
		gbc_tf_Dreieck_beta.insets = new Insets(0, 0, 5, 5);
		gbc_tf_Dreieck_beta.fill = GridBagConstraints.HORIZONTAL;
		gbc_tf_Dreieck_beta.gridx = 4;
		gbc_tf_Dreieck_beta.gridy = 2;
		panel_Dreieck_1.add(tf_Dreieck_beta, gbc_tf_Dreieck_beta);
		tf_Dreieck_beta.setColumns(10);

		JLabel lblDreieck_Gamma = new JLabel("Gamma");
		GridBagConstraints gbc_lblDreieck_Gamma = new GridBagConstraints();
		gbc_lblDreieck_Gamma.anchor = GridBagConstraints.EAST;
		gbc_lblDreieck_Gamma.insets = new Insets(0, 0, 5, 5);
		gbc_lblDreieck_Gamma.gridx = 5;
		gbc_lblDreieck_Gamma.gridy = 2;
		panel_Dreieck_1.add(lblDreieck_Gamma, gbc_lblDreieck_Gamma);

		// Dreieck Berechnen beginn
		JButton btnDreieck_calc = new JButton("Berechnen");
		btnDreieck_calc.setToolTipText("Hiermit kannst du aus 3 Werten alle anderen errechnen lassen.");
		btnDreieck_calc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Dreieck dreieck = new Dreieck();

				int i = 0;
				ArrayList<String> arr = new ArrayList<String>();
				ArrayList<Double> arr1 = new ArrayList<Double>();
				// set sides and angles
				// getter
				if (!tf_Dreieck_a.getText().isEmpty()) {
					try {
						dreieck.setSidea(Double.parseDouble(tf_Dreieck_a.getText().replaceAll(",", ".")));
						i++;
						arr.add("sidea");
						arr1.add(dreieck.getSidea());
					} catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog(frmGeometrie, "Ung�ltiger Wert \"Seite A\" ", "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
				if (!tf_Dreieck_b.getText().isEmpty()) {
					try {
						dreieck.setB(Double.parseDouble(tf_Dreieck_b.getText().replaceAll(",", ".")));
						i++;
						arr.add("sideb");
						arr1.add(dreieck.getB());
					} catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog(frmGeometrie, "Ung�ltiger Wert \"Seite B\" ", "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
				if (!tf_Dreieck_c.getText().isEmpty()) {
					try {
						dreieck.setC(Double.parseDouble(tf_Dreieck_c.getText().replaceAll(",", ".")));
						i++;
						arr.add("sidec");
						arr1.add(dreieck.getC());
					} catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog(frmGeometrie, "Ung�ltiger Wert \"Seite c\" ", "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
				if (!tf_Dreieck_alpha.getText().isEmpty()) {
					try {
						dreieck.setAlpha(Double.parseDouble(tf_Dreieck_alpha.getText().replaceAll(",", ".")));
						i++;
						arr.add("alpha");
						arr1.add(dreieck.getAlpha());
					} catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog(frmGeometrie, "Ung�ltiger Wert \"Alpha\" ", "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
				if (!tf_Dreieck_beta.getText().isEmpty()) {
					try {
						dreieck.setBeta(Double.parseDouble(tf_Dreieck_beta.getText().replaceAll(",", ".")));
						i++;
						arr.add("beta");
						arr1.add(dreieck.getBeta());
					} catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog(frmGeometrie, "Ung�ltiger Wert \"Beta\" ", "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
				if (!tf_Dreieck_gamma.getText().isEmpty()) {
					try {
						dreieck.setGamma(Double.parseDouble(tf_Dreieck_gamma.getText().replaceAll(",", ".")));
						i++;
						arr.add("gamma");
						arr1.add(dreieck.getGamma());
					} catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog(frmGeometrie, "Ung�ltiger Wert \"Gamma\" ", "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
				if (!tf_Dreieck_heighta.getText().isEmpty()) {
					try {
						dreieck.setHeighta(Double.parseDouble(tf_Dreieck_heighta.getText().replaceAll(",", ".")));
						i++;
						arr.add("ha");
						arr1.add(dreieck.getHeighta());
					} catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog(frmGeometrie, "Ung�ltiger Wert \"H�he a\" ", "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
				if (!tf_Dreieck_heightb.getText().isEmpty()) {
					try {
						dreieck.setHeightb(Double.parseDouble(tf_Dreieck_heightb.getText().replaceAll(",", ".")));
						i++;
						arr.add("hb");
						arr1.add(dreieck.getHeightb());
					} catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog(frmGeometrie, "Ung�ltiger Wert \"H�he b\" ", "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
				if (!tf_Dreieck_heightc.getText().isEmpty()) {
					try {
						dreieck.setHeightc(Double.parseDouble(tf_Dreieck_heightc.getText().replaceAll(",", ".")));
						i++;
						arr.add("hc");
						arr1.add(dreieck.getHeightc());
					} catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog(frmGeometrie, "Ung�ltiger Wert \"H�he c\" ", "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
				if (!tf_Dreieck_Flaeche.getText().isEmpty()) {
					try {
						dreieck.setA(Double.parseDouble(tf_Dreieck_Flaeche.getText().replaceAll(",", ".")));
						i++;
						arr.add("flaeche");
						arr1.add(dreieck.getA());
					} catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog(frmGeometrie, "Ung�ltiger Wert \"Fl�che\" ", "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
				if (!tf_Dreieck_Umfang.getText().isEmpty()) {
					try {
						dreieck.setU(Double.parseDouble(tf_Dreieck_Umfang.getText().replaceAll(",", ".")));
						i++;
						arr.add("umfang");
						arr1.add(dreieck.getU());
					} catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog(frmGeometrie, "Ung�ltiger Wert \"Umfang\" ", "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
				if (i == 3) {
					if (!(Datenbank.getDB(dreieck, arr, arr1))) {
						// System.out.println("calc");
						dreieck.calculate();

					}
				} else {
					JOptionPane.showMessageDialog(frmGeometrie, "Bitte 3 Werte angeben.", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				// setter
				if (dreieck.getSidea() != null && dreieck.getB() != null && dreieck.getC() != null
						&& dreieck.getAlpha() != null && dreieck.getBeta() != null && dreieck.getGamma() != null
						&& dreieck.getA() != null && dreieck.getU() != null && dreieck.getInradius() != null
						&& dreieck.getUmradius() != null) {

				} else {
					JOptionPane.showMessageDialog(frmGeometrie, "Dreieck nicht Konstruierbar.", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (dreieck.getSidea() > 0 && dreieck.getB() > 0 && dreieck.getC() > 0 && dreieck.getAlpha() > 0
						&& dreieck.getBeta() > 0 && dreieck.getGamma() > 0 && dreieck.getA() > 0 && dreieck.getU() > 0
						&& dreieck.getInradius() > 0 && dreieck.getUmradius() > 0) {

				} else {
					JOptionPane.showMessageDialog(frmGeometrie, "Dreieck nicht Konstruierbar.", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				Datenbank.save_DB(dreieck);

				if (tf_Dreieck_a.getText().isEmpty()) {
					tf_Dreieck_a.setText(
							String.valueOf((double) Math.round(dreieck.getSidea() * 100) / 100).replace(".", ","));
				}
				if (tf_Dreieck_b.getText().isEmpty()) {
					tf_Dreieck_b
							.setText(String.valueOf((double) Math.round(dreieck.getB() * 100) / 100).replace(".", ","));
				}
				if (tf_Dreieck_c.getText().isEmpty()) {
					tf_Dreieck_c
							.setText(String.valueOf((double) Math.round(dreieck.getC() * 100) / 100).replace(".", ","));
				}
				if (tf_Dreieck_alpha.getText().isEmpty()) {
					tf_Dreieck_alpha.setText(
							String.valueOf((double) Math.round(dreieck.getAlpha() * 100) / 100).replace(".", ","));
				}
				if (tf_Dreieck_beta.getText().isEmpty()) {
					tf_Dreieck_beta.setText(
							String.valueOf((double) Math.round(dreieck.getBeta() * 100) / 100).replace(".", ","));
				}
				if (tf_Dreieck_gamma.getText().isEmpty()) {
					tf_Dreieck_gamma.setText(
							String.valueOf((double) Math.round(dreieck.getGamma() * 100) / 100).replace(".", ","));
				}
				if (tf_Dreieck_heighta.getText().isEmpty()) {
					tf_Dreieck_heighta.setText(
							String.valueOf((double) Math.round(dreieck.getHeighta() * 100) / 100).replace(".", ","));
				}
				if (tf_Dreieck_heightb.getText().isEmpty()) {
					tf_Dreieck_heightb.setText(
							String.valueOf((double) Math.round(dreieck.getHeightb() * 100) / 100).replace(".", ","));
				}
				if (tf_Dreieck_heightc.getText().isEmpty()) {
					tf_Dreieck_heightc.setText(
							String.valueOf((double) Math.round(dreieck.getHeightc() * 100) / 100).replace(".", ","));
				}
				if (tf_Dreieck_Umfang.getText().isEmpty()) {
					tf_Dreieck_Umfang
							.setText(String.valueOf((double) Math.round(dreieck.getU() * 100) / 100).replace(".", ","));
				}
				if (tf_Dreieck_Flaeche.getText().isEmpty()) {
					tf_Dreieck_Flaeche
							.setText(String.valueOf((double) Math.round(dreieck.getA() * 100) / 100).replace(".", ","));
				}
				lbl_Dreieck_Inradius_Ergebnis.setText(
						String.valueOf((double) Math.round(dreieck.getInradius() * 100) / 100).replace(".", ","));
				lbl_Dreieck_Umradius_Ergebnis.setText(
						String.valueOf((double) Math.round(dreieck.getUmradius() * 100) / 100).replace(".", ","));
				// Dreieck ende
			}
		});

		tf_Dreieck_gamma = new JTextField();
		tf_Dreieck_gamma.setToolTipText("Hier musst du den Winkel Gamma eingeben.");
		GridBagConstraints gbc_tf_Dreieck_gamma = new GridBagConstraints();
		gbc_tf_Dreieck_gamma.insets = new Insets(0, 0, 5, 5);
		gbc_tf_Dreieck_gamma.fill = GridBagConstraints.HORIZONTAL;
		gbc_tf_Dreieck_gamma.gridx = 6;
		gbc_tf_Dreieck_gamma.gridy = 2;
		panel_Dreieck_1.add(tf_Dreieck_gamma, gbc_tf_Dreieck_gamma);
		tf_Dreieck_gamma.setColumns(10);

		JLabel lblHeighta = new JLabel("H\u00F6he a");
		GridBagConstraints gbc_lblHeighta = new GridBagConstraints();
		gbc_lblHeighta.anchor = GridBagConstraints.EAST;
		gbc_lblHeighta.insets = new Insets(0, 0, 5, 5);
		gbc_lblHeighta.gridx = 1;
		gbc_lblHeighta.gridy = 3;
		panel_Dreieck_1.add(lblHeighta, gbc_lblHeighta);

		tf_Dreieck_heighta = new JTextField();
		tf_Dreieck_heighta.setToolTipText("Hier musst du die H\u00F6he von a eingeben.");
		GridBagConstraints gbc_tf_Dreieck_heighta = new GridBagConstraints();
		gbc_tf_Dreieck_heighta.insets = new Insets(0, 0, 5, 5);
		gbc_tf_Dreieck_heighta.fill = GridBagConstraints.HORIZONTAL;
		gbc_tf_Dreieck_heighta.gridx = 2;
		gbc_tf_Dreieck_heighta.gridy = 3;
		panel_Dreieck_1.add(tf_Dreieck_heighta, gbc_tf_Dreieck_heighta);
		tf_Dreieck_heighta.setColumns(10);
		GridBagConstraints gbc_btnDreieck_calc = new GridBagConstraints();
		gbc_btnDreieck_calc.insets = new Insets(0, 0, 5, 5);
		gbc_btnDreieck_calc.gridx = 4;
		gbc_btnDreieck_calc.gridy = 8;
		panel_Dreieck_1.add(btnDreieck_calc, gbc_btnDreieck_calc);

		JButton btnDreieck_clear = new JButton("L\u00F6schen");
		btnDreieck_clear.setToolTipText("Hiermit kannst du alle Eingaben leeren.");
		btnDreieck_clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tf_Dreieck_a.setText(null);
				tf_Dreieck_b.setText(null);
				tf_Dreieck_c.setText(null);
				tf_Dreieck_alpha.setText(null);
				tf_Dreieck_beta.setText(null);
				tf_Dreieck_gamma.setText(null);
				tf_Dreieck_heighta.setText(null);
				tf_Dreieck_heightb.setText(null);
				tf_Dreieck_heightc.setText(null);
				tf_Dreieck_Flaeche.setText(null);
				tf_Dreieck_Umfang.setText(null);
				lbl_Dreieck_Inradius_Ergebnis.setText(null);
				lbl_Dreieck_Umradius_Ergebnis.setText(null);

			}
		});
		GridBagConstraints gbc_btnDreieck_clear = new GridBagConstraints();
		gbc_btnDreieck_clear.insets = new Insets(0, 0, 5, 5);
		gbc_btnDreieck_clear.gridx = 6;
		gbc_btnDreieck_clear.gridy = 8;
		panel_Dreieck_1.add(btnDreieck_clear, gbc_btnDreieck_clear);
		GridBagLayout gbl_panel_Dreieck_Bild = new GridBagLayout();
		gbl_panel_Dreieck_Bild.columnWidths = new int[] { 250, 46, 0, 0 };
		gbl_panel_Dreieck_Bild.rowHeights = new int[] { 14, 0, 0 };
		gbl_panel_Dreieck_Bild.columnWeights = new double[] { 0.0, 1.0, 1.0, Double.MIN_VALUE };
		gbl_panel_Dreieck_Bild.rowWeights = new double[] { 1.0, 0.0, Double.MIN_VALUE };
		panel_Dreieck_Bild.setLayout(gbl_panel_Dreieck_Bild);

		JLabel lbl_Dreieck_Picture = new JLabel("");
		GridBagConstraints gbc_lbl_Dreieck_Picture = new GridBagConstraints();
		gbc_lbl_Dreieck_Picture.insets = new Insets(30, 0, 5, 5);
		// bild einf�gen
		try {
			BufferedImage image = ImageIO.read(getClass().getResourceAsStream("Dreieck.png"));
			lbl_Dreieck_Picture.setIcon(new ImageIcon(resizeImage(image, 250, 160)));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		gbc_lbl_Dreieck_Picture.anchor = GridBagConstraints.NORTHWEST;
		gbc_lbl_Dreieck_Picture.gridx = 1;
		gbc_lbl_Dreieck_Picture.gridy = 0;
		panel_Dreieck_Bild.add(lbl_Dreieck_Picture, gbc_lbl_Dreieck_Picture);
		layeredPane_Dreieck.setLayout(gl_layeredPane_Dreieck);

		JLayeredPane layeredPane_Kreis = new JLayeredPane();
		frmGeometrie.getContentPane().add(layeredPane_Kreis, "panel_Kreis");

		JPanel panel_Kreis_1 = new JPanel();
		panel_Kreis_1.setOpaque(false);
		GridBagLayout gbl_panel_Kreis_1 = new GridBagLayout();
		gbl_panel_Kreis_1.columnWidths = new int[] { 200, 0, 0, 25, 0, 0, 0 };
		gbl_panel_Kreis_1.rowHeights = new int[] { 200, 0, 0, 0, 0, 0 };
		gbl_panel_Kreis_1.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panel_Kreis_1.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel_Kreis_1.setLayout(gbl_panel_Kreis_1);

		JPanel panel_Kreis_Bild = new JPanel();
		panel_Kreis_Bild.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblNewLabel_81 = new JLabel("");
		// bild einf�gen
		try {
			BufferedImage image = ImageIO.read(getClass().getResourceAsStream("Kreis.png"));
			lblNewLabel_81.setIcon(new ImageIcon(resizeImage(image, 200, 200)));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		panel_Kreis_Bild.add(lblNewLabel_81);
		GroupLayout gl_layeredPane_Kreis = new GroupLayout(layeredPane_Kreis);
		gl_layeredPane_Kreis.setHorizontalGroup(
				gl_layeredPane_Kreis.createParallelGroup(Alignment.LEADING).addGap(0, 884, Short.MAX_VALUE)
						.addComponent(panel_Kreis_1, GroupLayout.PREFERRED_SIZE, 884, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_Kreis_Bild, GroupLayout.PREFERRED_SIZE, 884, GroupLayout.PREFERRED_SIZE));
		gl_layeredPane_Kreis.setVerticalGroup(
				gl_layeredPane_Kreis.createParallelGroup(Alignment.LEADING).addGap(0, 539, Short.MAX_VALUE)
						.addComponent(panel_Kreis_1, GroupLayout.PREFERRED_SIZE, 539, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_Kreis_Bild, GroupLayout.PREFERRED_SIZE, 539, GroupLayout.PREFERRED_SIZE));

		JLabel lblKreis_r = new JLabel("Radius");
		GridBagConstraints gbc_lblKreis_r = new GridBagConstraints();
		gbc_lblKreis_r.anchor = GridBagConstraints.EAST;
		gbc_lblKreis_r.insets = new Insets(0, 0, 5, 5);
		gbc_lblKreis_r.gridx = 1;
		gbc_lblKreis_r.gridy = 1;
		panel_Kreis_1.add(lblKreis_r, gbc_lblKreis_r);

		tf_Kreis_r = new JTextField();
		GridBagConstraints gbc_tf_Kreis_r = new GridBagConstraints();
		gbc_tf_Kreis_r.insets = new Insets(0, 0, 5, 5);
		gbc_tf_Kreis_r.fill = GridBagConstraints.HORIZONTAL;
		gbc_tf_Kreis_r.gridx = 2;
		gbc_tf_Kreis_r.gridy = 1;
		panel_Kreis_1.add(tf_Kreis_r, gbc_tf_Kreis_r);
		tf_Kreis_r.setColumns(10);

		JLabel lblKreis_U = new JLabel("Umfang");
		GridBagConstraints gbc_lblKreis_U = new GridBagConstraints();
		gbc_lblKreis_U.anchor = GridBagConstraints.EAST;
		gbc_lblKreis_U.insets = new Insets(0, 0, 5, 5);
		gbc_lblKreis_U.gridx = 4;
		gbc_lblKreis_U.gridy = 1;
		panel_Kreis_1.add(lblKreis_U, gbc_lblKreis_U);

		tf_Kreis_U = new JTextField();
		GridBagConstraints gbc_tf_Kreis_U = new GridBagConstraints();
		gbc_tf_Kreis_U.insets = new Insets(0, 0, 5, 0);
		gbc_tf_Kreis_U.fill = GridBagConstraints.HORIZONTAL;
		gbc_tf_Kreis_U.gridx = 5;
		gbc_tf_Kreis_U.gridy = 1;
		panel_Kreis_1.add(tf_Kreis_U, gbc_tf_Kreis_U);
		tf_Kreis_U.setColumns(10);

		JLabel lblKreis_d = new JLabel("Durchmesser");
		GridBagConstraints gbc_lblKreis_d = new GridBagConstraints();
		gbc_lblKreis_d.anchor = GridBagConstraints.EAST;
		gbc_lblKreis_d.insets = new Insets(0, 0, 5, 5);
		gbc_lblKreis_d.gridx = 1;
		gbc_lblKreis_d.gridy = 2;
		panel_Kreis_1.add(lblKreis_d, gbc_lblKreis_d);

		tf_Kreis_d = new JTextField();
		GridBagConstraints gbc_tf_Kreis_d = new GridBagConstraints();
		gbc_tf_Kreis_d.insets = new Insets(0, 0, 5, 5);
		gbc_tf_Kreis_d.fill = GridBagConstraints.HORIZONTAL;
		gbc_tf_Kreis_d.gridx = 2;
		gbc_tf_Kreis_d.gridy = 2;
		panel_Kreis_1.add(tf_Kreis_d, gbc_tf_Kreis_d);
		tf_Kreis_d.setColumns(10);

		JLabel lblKreis_A = new JLabel("Fl\u00E4che");
		GridBagConstraints gbc_lblKreis_A = new GridBagConstraints();
		gbc_lblKreis_A.anchor = GridBagConstraints.EAST;
		gbc_lblKreis_A.insets = new Insets(0, 0, 5, 5);
		gbc_lblKreis_A.gridx = 4;
		gbc_lblKreis_A.gridy = 2;
		panel_Kreis_1.add(lblKreis_A, gbc_lblKreis_A);

		tf_Kreis_A = new JTextField();
		GridBagConstraints gbc_tf_Kreis_A = new GridBagConstraints();
		gbc_tf_Kreis_A.insets = new Insets(0, 0, 5, 0);
		gbc_tf_Kreis_A.fill = GridBagConstraints.HORIZONTAL;
		gbc_tf_Kreis_A.gridx = 5;
		gbc_tf_Kreis_A.gridy = 2;
		panel_Kreis_1.add(tf_Kreis_A, gbc_tf_Kreis_A);
		tf_Kreis_A.setColumns(10);

		JButton btnKreis_calc = new JButton("Berechnen");
		btnKreis_calc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Kreis kreis = new Kreis();
				int i = 0;
				ArrayList<String> arr = new ArrayList<String>();
				ArrayList<Double> arr1 = new ArrayList<Double>();
				if (!tf_Kreis_r.getText().isEmpty()) {
					try {
						if (Double.parseDouble(tf_Kreis_r.getText().replaceAll(",", ".")) > 0) {
							kreis.setR(Double.parseDouble(tf_Kreis_r.getText().replaceAll(",", ".")));
							i++;
							arr.add("r");
							arr1.add(kreis.getR());
						} else {
							JOptionPane.showMessageDialog(frmGeometrie, "Bitte einen Positiven Wert angeben.", "Error",
									JOptionPane.ERROR_MESSAGE);
							return;
						}
					} catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog(frmGeometrie, "Ung�ltiger Wert \"Radius\" ", "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
				if (!tf_Kreis_d.getText().isEmpty()) {
					try {
						if (Double.parseDouble(tf_Kreis_d.getText().replaceAll(",", ".")) > 0) {
							kreis.setD(Double.parseDouble(tf_Kreis_d.getText().replaceAll(",", ".")));
							i++;
							arr.add("d");
							arr1.add(kreis.getD());
						} else {
							JOptionPane.showMessageDialog(frmGeometrie, "Bitte einen Positiven Wert angeben.", "Error",
									JOptionPane.ERROR_MESSAGE);
							return;
						}
					} catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog(frmGeometrie, "Ung�ltiger Wert \"Durchmesser\" ", "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
				if (!tf_Kreis_U.getText().isEmpty()) {
					try {
						if (Double.parseDouble(tf_Kreis_U.getText().replaceAll(",", ".")) > 0) {
							kreis.setU(Double.parseDouble(tf_Kreis_U.getText().replaceAll(",", ".")));
							i++;
							arr.add("U");
							arr1.add(kreis.getU());
						} else {
							JOptionPane.showMessageDialog(frmGeometrie, "Bitte einen Positiven Wert angeben.", "Error",
									JOptionPane.ERROR_MESSAGE);
							return;
						}
					} catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog(frmGeometrie, "Ung�ltiger Wert \"Umfang\" ", "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
				if (!tf_Kreis_A.getText().isEmpty()) {
					try {
						if (Double.parseDouble(tf_Kreis_A.getText().replaceAll(",", ".")) > 0) {
							kreis.setA(Double.parseDouble(tf_Kreis_A.getText().replaceAll(",", ".")));
							i++;
							arr.add("A");
							arr1.add(kreis.getA());
						} else {
							JOptionPane.showMessageDialog(frmGeometrie, "Bitte einen Positiven Wert angeben.", "Error",
									JOptionPane.ERROR_MESSAGE);
							return;
						}
					} catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog(frmGeometrie, "Ung�ltiger Wert \"Fl�che\" ", "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
				if (i == 1) {
					if (!Datenbank.getDB(kreis, arr, arr1)) {
						kreis.calculate();
						Datenbank.save_DB(kreis);
					}
				} else {
					JOptionPane.showMessageDialog(frmGeometrie, "Bitte einen Wert angeben.", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				if (tf_Kreis_r.getText().isEmpty()) {
					tf_Kreis_r.setText(String.valueOf((double) Math.round(kreis.getR() * 100) / 100).replace(".", ","));
				}
				if (tf_Kreis_d.getText().isEmpty()) {
					tf_Kreis_d.setText(String.valueOf((double) Math.round(kreis.getD() * 100) / 100).replace(".", ","));
				}
				if (tf_Kreis_U.getText().isEmpty()) {
					tf_Kreis_U.setText(String.valueOf((double) Math.round(kreis.getU() * 100) / 100).replace(".", ","));
				}
				if (tf_Kreis_A.getText().isEmpty()) {
					tf_Kreis_A.setText(String.valueOf((double) Math.round(kreis.getA() * 100) / 100).replace(".", ","));
				}

			}
		});
		GridBagConstraints gbc_btnKreis_calc = new GridBagConstraints();
		gbc_btnKreis_calc.insets = new Insets(0, 0, 0, 5);
		gbc_btnKreis_calc.gridx = 2;
		gbc_btnKreis_calc.gridy = 4;
		panel_Kreis_1.add(btnKreis_calc, gbc_btnKreis_calc);

		JButton btnKreis_clear = new JButton("L\u00F6schen");
		btnKreis_clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tf_Kreis_A.setText(null);
				tf_Kreis_d.setText(null);
				tf_Kreis_r.setText(null);
				tf_Kreis_U.setText(null);
			}
		});
		GridBagConstraints gbc_btnKreis_clear = new GridBagConstraints();
		gbc_btnKreis_clear.gridx = 5;
		gbc_btnKreis_clear.gridy = 4;
		panel_Kreis_1.add(btnKreis_clear, gbc_btnKreis_clear);
		layeredPane_Kreis.setLayout(gl_layeredPane_Kreis);

		JLayeredPane layeredPane_Rechteck = new JLayeredPane();
		frmGeometrie.getContentPane().add(layeredPane_Rechteck, "panel_Rechteck");

		JPanel panel_Rechteck_1 = new JPanel();
		panel_Rechteck_1.setOpaque(false);
		GridBagLayout gbl_panel_Rechteck_1 = new GridBagLayout();
		gbl_panel_Rechteck_1.columnWidths = new int[] { 170, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel_Rechteck_1.rowHeights = new int[] { 200, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel_Rechteck_1.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panel_Rechteck_1.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel_Rechteck_1.setLayout(gbl_panel_Rechteck_1);

		JPanel panel_Rechteck_Bild = new JPanel();

		JLabel lblNewLabel_8_1 = new JLabel("");
		// bild einf�gen
		try {
			BufferedImage image = ImageIO.read(getClass().getResourceAsStream("Rechteck.png"));
			lblNewLabel_8_1.setIcon(new ImageIcon(resizeImage(image, 356, 190)));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		panel_Rechteck_Bild.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panel_Rechteck_Bild.add(lblNewLabel_8_1);
		GroupLayout gl_layeredPane_Rechteck = new GroupLayout(layeredPane_Rechteck);
		gl_layeredPane_Rechteck.setHorizontalGroup(gl_layeredPane_Rechteck.createParallelGroup(Alignment.LEADING)
				.addGap(0, 884, Short.MAX_VALUE)
				.addComponent(panel_Rechteck_1, GroupLayout.PREFERRED_SIZE, 884, GroupLayout.PREFERRED_SIZE)
				.addComponent(panel_Rechteck_Bild, GroupLayout.PREFERRED_SIZE, 884, GroupLayout.PREFERRED_SIZE));
		gl_layeredPane_Rechteck.setVerticalGroup(gl_layeredPane_Rechteck.createParallelGroup(Alignment.LEADING)
				.addGap(0, 539, Short.MAX_VALUE)
				.addComponent(panel_Rechteck_1, GroupLayout.PREFERRED_SIZE, 539, GroupLayout.PREFERRED_SIZE)
				.addComponent(panel_Rechteck_Bild, GroupLayout.PREFERRED_SIZE, 539, GroupLayout.PREFERRED_SIZE));

		JLabel lblRechteck_sidea = new JLabel("Seite A");
		GridBagConstraints gbc_lblRechteck_sidea = new GridBagConstraints();
		gbc_lblRechteck_sidea.anchor = GridBagConstraints.EAST;
		gbc_lblRechteck_sidea.insets = new Insets(0, 0, 5, 5);
		gbc_lblRechteck_sidea.gridx = 2;
		gbc_lblRechteck_sidea.gridy = 2;
		panel_Rechteck_1.add(lblRechteck_sidea, gbc_lblRechteck_sidea);

		tf_Rechteck_sidea = new JTextField();
		GridBagConstraints gbc_tf_Rechteck_sidea = new GridBagConstraints();
		gbc_tf_Rechteck_sidea.insets = new Insets(0, 0, 5, 5);
		gbc_tf_Rechteck_sidea.fill = GridBagConstraints.HORIZONTAL;
		gbc_tf_Rechteck_sidea.gridx = 3;
		gbc_tf_Rechteck_sidea.gridy = 2;
		panel_Rechteck_1.add(tf_Rechteck_sidea, gbc_tf_Rechteck_sidea);
		tf_Rechteck_sidea.setColumns(10);

		JLabel lblRechteck_sideb = new JLabel("Seite B");
		GridBagConstraints gbc_lblRechteck_sideb = new GridBagConstraints();
		gbc_lblRechteck_sideb.anchor = GridBagConstraints.EAST;
		gbc_lblRechteck_sideb.insets = new Insets(0, 0, 5, 5);
		gbc_lblRechteck_sideb.gridx = 4;
		gbc_lblRechteck_sideb.gridy = 2;
		panel_Rechteck_1.add(lblRechteck_sideb, gbc_lblRechteck_sideb);

		tf_Rechteck_sideb = new JTextField();
		GridBagConstraints gbc_tf_Rechteck_sideb = new GridBagConstraints();
		gbc_tf_Rechteck_sideb.insets = new Insets(0, 0, 5, 5);
		gbc_tf_Rechteck_sideb.fill = GridBagConstraints.HORIZONTAL;
		gbc_tf_Rechteck_sideb.gridx = 5;
		gbc_tf_Rechteck_sideb.gridy = 2;
		panel_Rechteck_1.add(tf_Rechteck_sideb, gbc_tf_Rechteck_sideb);
		tf_Rechteck_sideb.setColumns(10);

		JLabel lblRechteck_diag = new JLabel("Diagonale");
		GridBagConstraints gbc_lblRechteck_diag = new GridBagConstraints();
		gbc_lblRechteck_diag.anchor = GridBagConstraints.EAST;
		gbc_lblRechteck_diag.insets = new Insets(0, 0, 5, 5);
		gbc_lblRechteck_diag.gridx = 6;
		gbc_lblRechteck_diag.gridy = 2;
		panel_Rechteck_1.add(lblRechteck_diag, gbc_lblRechteck_diag);

		tf_Rechteck_diag = new JTextField();
		GridBagConstraints gbc_tf_Rechteck_diag = new GridBagConstraints();
		gbc_tf_Rechteck_diag.insets = new Insets(0, 0, 5, 0);
		gbc_tf_Rechteck_diag.fill = GridBagConstraints.HORIZONTAL;
		gbc_tf_Rechteck_diag.gridx = 7;
		gbc_tf_Rechteck_diag.gridy = 2;
		panel_Rechteck_1.add(tf_Rechteck_diag, gbc_tf_Rechteck_diag);
		tf_Rechteck_diag.setColumns(10);

		JLabel lblRechteck_Flaeche = new JLabel("Fl\u00E4che");
		GridBagConstraints gbc_lblRechteck_Flaeche = new GridBagConstraints();
		gbc_lblRechteck_Flaeche.anchor = GridBagConstraints.EAST;
		gbc_lblRechteck_Flaeche.insets = new Insets(0, 0, 5, 5);
		gbc_lblRechteck_Flaeche.gridx = 2;
		gbc_lblRechteck_Flaeche.gridy = 3;
		panel_Rechteck_1.add(lblRechteck_Flaeche, gbc_lblRechteck_Flaeche);

		tf_Rechteck_flaeche = new JTextField();
		GridBagConstraints gbc_tf_Rechteck_flaeche = new GridBagConstraints();
		gbc_tf_Rechteck_flaeche.insets = new Insets(0, 0, 5, 5);
		gbc_tf_Rechteck_flaeche.fill = GridBagConstraints.HORIZONTAL;
		gbc_tf_Rechteck_flaeche.gridx = 3;
		gbc_tf_Rechteck_flaeche.gridy = 3;
		panel_Rechteck_1.add(tf_Rechteck_flaeche, gbc_tf_Rechteck_flaeche);
		tf_Rechteck_flaeche.setColumns(10);

		JLabel lblRechteck_U = new JLabel("Umfang");
		GridBagConstraints gbc_lblRechteck_U = new GridBagConstraints();
		gbc_lblRechteck_U.anchor = GridBagConstraints.EAST;
		gbc_lblRechteck_U.insets = new Insets(0, 0, 5, 5);
		gbc_lblRechteck_U.gridx = 4;
		gbc_lblRechteck_U.gridy = 3;
		panel_Rechteck_1.add(lblRechteck_U, gbc_lblRechteck_U);

		tf_Rechteck_umfang = new JTextField();
		GridBagConstraints gbc_tf_Rechteck_umfang = new GridBagConstraints();
		gbc_tf_Rechteck_umfang.insets = new Insets(0, 0, 5, 5);
		gbc_tf_Rechteck_umfang.fill = GridBagConstraints.HORIZONTAL;
		gbc_tf_Rechteck_umfang.gridx = 5;
		gbc_tf_Rechteck_umfang.gridy = 3;
		panel_Rechteck_1.add(tf_Rechteck_umfang, gbc_tf_Rechteck_umfang);
		tf_Rechteck_umfang.setColumns(10);

		JLabel lblRechteck_delta = new JLabel("Delta");
		GridBagConstraints gbc_lblRechteck_delta = new GridBagConstraints();
		gbc_lblRechteck_delta.anchor = GridBagConstraints.EAST;
		gbc_lblRechteck_delta.insets = new Insets(0, 0, 5, 5);
		gbc_lblRechteck_delta.gridx = 6;
		gbc_lblRechteck_delta.gridy = 3;
		panel_Rechteck_1.add(lblRechteck_delta, gbc_lblRechteck_delta);

		tf_Rechteck_delta = new JTextField();
		GridBagConstraints gbc_tf_Rechteck_delta = new GridBagConstraints();
		gbc_tf_Rechteck_delta.insets = new Insets(0, 0, 5, 0);
		gbc_tf_Rechteck_delta.fill = GridBagConstraints.HORIZONTAL;
		gbc_tf_Rechteck_delta.gridx = 7;
		gbc_tf_Rechteck_delta.gridy = 3;
		panel_Rechteck_1.add(tf_Rechteck_delta, gbc_tf_Rechteck_delta);
		tf_Rechteck_delta.setColumns(10);

		JLabel lblRechteck_alpha = new JLabel("Alpha");
		GridBagConstraints gbc_lblRechteck_alpha = new GridBagConstraints();
		gbc_lblRechteck_alpha.anchor = GridBagConstraints.EAST;
		gbc_lblRechteck_alpha.insets = new Insets(0, 0, 5, 5);
		gbc_lblRechteck_alpha.gridx = 2;
		gbc_lblRechteck_alpha.gridy = 4;
		panel_Rechteck_1.add(lblRechteck_alpha, gbc_lblRechteck_alpha);

		tf_Rechteck_alpha = new JTextField();
		GridBagConstraints gbc_tf_Rechteck_alpha = new GridBagConstraints();
		gbc_tf_Rechteck_alpha.insets = new Insets(0, 0, 5, 5);
		gbc_tf_Rechteck_alpha.fill = GridBagConstraints.HORIZONTAL;
		gbc_tf_Rechteck_alpha.gridx = 3;
		gbc_tf_Rechteck_alpha.gridy = 4;
		panel_Rechteck_1.add(tf_Rechteck_alpha, gbc_tf_Rechteck_alpha);
		tf_Rechteck_alpha.setColumns(10);

		JLabel lblRechteck_beta = new JLabel("Beta");
		GridBagConstraints gbc_lblRechteck_beta = new GridBagConstraints();
		gbc_lblRechteck_beta.anchor = GridBagConstraints.EAST;
		gbc_lblRechteck_beta.insets = new Insets(0, 0, 5, 5);
		gbc_lblRechteck_beta.gridx = 4;
		gbc_lblRechteck_beta.gridy = 4;
		panel_Rechteck_1.add(lblRechteck_beta, gbc_lblRechteck_beta);

		tf_Rechteck_beta = new JTextField();
		GridBagConstraints gbc_tf_Rechteck_beta = new GridBagConstraints();
		gbc_tf_Rechteck_beta.insets = new Insets(0, 0, 5, 5);
		gbc_tf_Rechteck_beta.fill = GridBagConstraints.HORIZONTAL;
		gbc_tf_Rechteck_beta.gridx = 5;
		gbc_tf_Rechteck_beta.gridy = 4;
		panel_Rechteck_1.add(tf_Rechteck_beta, gbc_tf_Rechteck_beta);
		tf_Rechteck_beta.setColumns(10);

		JLabel lblRechteck_gamma = new JLabel("Gamma");
		GridBagConstraints gbc_lblRechteck_gamma = new GridBagConstraints();
		gbc_lblRechteck_gamma.anchor = GridBagConstraints.EAST;
		gbc_lblRechteck_gamma.insets = new Insets(0, 0, 5, 5);
		gbc_lblRechteck_gamma.gridx = 6;
		gbc_lblRechteck_gamma.gridy = 4;
		panel_Rechteck_1.add(lblRechteck_gamma, gbc_lblRechteck_gamma);

		tf_Rechteck_gamma = new JTextField();
		GridBagConstraints gbc_tf_Rechteck_gamma = new GridBagConstraints();
		gbc_tf_Rechteck_gamma.insets = new Insets(0, 0, 5, 0);
		gbc_tf_Rechteck_gamma.fill = GridBagConstraints.HORIZONTAL;
		gbc_tf_Rechteck_gamma.gridx = 7;
		gbc_tf_Rechteck_gamma.gridy = 4;
		panel_Rechteck_1.add(tf_Rechteck_gamma, gbc_tf_Rechteck_gamma);
		tf_Rechteck_gamma.setColumns(10);

		JButton btnRechteck_calc = new JButton("Berechnen");
		btnRechteck_calc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Rechteck rechteck = new Rechteck();
				int i = 0;
				ArrayList<String> arr = new ArrayList<String>();
				ArrayList<Double> arr1 = new ArrayList<Double>();
				if (!tf_Rechteck_alpha.getText().isEmpty()) {
					try {
						if (Double.parseDouble(tf_Rechteck_alpha.getText().replaceAll(",", ".")) > 0) {
							rechteck.setAlpha(Double.parseDouble(tf_Rechteck_alpha.getText().replaceAll(",", ".")));
							i++;
							arr.add("alpha");
							arr1.add(rechteck.getAlpha());
						} else {
							JOptionPane.showMessageDialog(frmGeometrie, "Bitte einen Positiven Wert angeben.", "Error",
									JOptionPane.ERROR_MESSAGE);
							return;
						}
					} catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog(frmGeometrie, "Ung�ltiger Wert \"Alpha\" ", "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
				if (!tf_Rechteck_beta.getText().isEmpty()) {
					try {
						if (Double.parseDouble(tf_Rechteck_beta.getText().replaceAll(",", ".")) > 0) {
							rechteck.setBeta(Double.parseDouble(tf_Rechteck_beta.getText().replaceAll(",", ".")));
							i++;
							arr.add("beta");
							arr1.add(rechteck.getBeta());
						} else {
							JOptionPane.showMessageDialog(frmGeometrie, "Bitte einen Positiven Wert angeben.", "Error",
									JOptionPane.ERROR_MESSAGE);
							return;
						}
					} catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog(frmGeometrie, "Ung�ltiger Wert \"Beta\" ", "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
				if (!tf_Rechteck_gamma.getText().isEmpty()) {
					try {
						if (Double.parseDouble(tf_Rechteck_gamma.getText().replaceAll(",", ".")) > 0) {
							rechteck.setGamma(Double.parseDouble(tf_Rechteck_gamma.getText().replaceAll(",", ".")));
							i++;
							arr.add("gamma");
							arr1.add(rechteck.getGamma());
						} else {
							JOptionPane.showMessageDialog(frmGeometrie, "Bitte einen Positiven Wert angeben.", "Error",
									JOptionPane.ERROR_MESSAGE);
							return;
						}
					} catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog(frmGeometrie, "Ung�ltiger Wert \"Gamma\" ", "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
				if (!tf_Rechteck_delta.getText().isEmpty()) {
					try {
						if (Double.parseDouble(tf_Rechteck_delta.getText().replaceAll(",", ".")) > 0) {
							rechteck.setDelta(Double.parseDouble(tf_Rechteck_delta.getText().replaceAll(",", ".")));
							i++;
							arr.add("delta");
							arr1.add(rechteck.getDelta());
						} else {
							JOptionPane.showMessageDialog(frmGeometrie, "Bitte einen Positiven Wert angeben.", "Error",
									JOptionPane.ERROR_MESSAGE);
							return;
						}
					} catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog(frmGeometrie, "Ung�ltiger Wert \"Delta\" ", "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
				if (!tf_Rechteck_sidea.getText().isEmpty()) {
					try {
						if (Double.parseDouble(tf_Rechteck_sidea.getText().replaceAll(",", ".")) > 0) {
							rechteck.setSidea(Double.parseDouble(tf_Rechteck_sidea.getText().replaceAll(",", ".")));
							i++;
							arr.add("sidea");
							arr1.add(rechteck.getSidea());
						} else {
							JOptionPane.showMessageDialog(frmGeometrie, "Bitte einen Positiven Wert angeben.", "Error",
									JOptionPane.ERROR_MESSAGE);
							return;
						}
					} catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog(frmGeometrie, "Ung�ltiger Wert \"Seite A\" ", "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
				if (!tf_Rechteck_sideb.getText().isEmpty()) {
					try {
						if (Double.parseDouble(tf_Rechteck_sideb.getText().replaceAll(",", ".")) > 0) {
							rechteck.setSideb(Double.parseDouble(tf_Rechteck_sideb.getText().replaceAll(",", ".")));
							i++;
							arr.add("sideb");
							arr1.add(rechteck.getSideb());
						} else {
							JOptionPane.showMessageDialog(frmGeometrie, "Bitte einen Positiven Wert angeben.", "Error",
									JOptionPane.ERROR_MESSAGE);
							return;
						}
					} catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog(frmGeometrie, "Ung�ltiger Wert \"Seite B\" ", "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
				if (!tf_Rechteck_umfang.getText().isEmpty()) {
					try {
						if (Double.parseDouble(tf_Rechteck_umfang.getText().replaceAll(",", ".")) > 0) {
							rechteck.setU(Double.parseDouble(tf_Rechteck_umfang.getText().replaceAll(",", ".")));
							i++;
							arr.add("umfang");
							arr1.add(rechteck.getU());
						} else {
							JOptionPane.showMessageDialog(frmGeometrie, "Bitte einen Positiven Wert angeben.", "Error",
									JOptionPane.ERROR_MESSAGE);
							return;
						}
					} catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog(frmGeometrie, "Ung�ltiger Wert \"Umfang\" ", "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}

				}
				if (!tf_Rechteck_flaeche.getText().isEmpty()) {
					try {
						if (Double.parseDouble(tf_Rechteck_flaeche.getText().replaceAll(",", ".")) > 0) {
							rechteck.setA(Double.parseDouble(tf_Rechteck_flaeche.getText().replaceAll(",", ".")));
							i++;
							arr.add("flaeche");
							arr1.add(rechteck.getA());
						} else {
							JOptionPane.showMessageDialog(frmGeometrie, "Bitte einen Positiven Wert angeben.", "Error",
									JOptionPane.ERROR_MESSAGE);
							return;
						}
					} catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog(frmGeometrie, "Ung�ltiger Wert \"Fl�che\" ", "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
				if (!tf_Rechteck_diag.getText().isEmpty()) {
					try {
						if (Double.parseDouble(tf_Rechteck_diag.getText().replaceAll(",", ".")) > 0) {
							rechteck.setD(Double.parseDouble(tf_Rechteck_diag.getText().replaceAll(",", ".")));

							i++;
							arr.add("d");
							arr1.add(rechteck.getD());
						} else {
							JOptionPane.showMessageDialog(frmGeometrie, "Bitte einen Positiven Wert angeben.", "Error",
									JOptionPane.ERROR_MESSAGE);
							return;
						}
					} catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog(frmGeometrie, "Ung�ltiger Wert \"Diagonale\" ", "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
				if (i == 2) {
					if (!Datenbank.getDB(rechteck, arr, arr1)) {
						rechteck.calculate();

					}
				} else {
					JOptionPane.showMessageDialog(frmGeometrie, "Bitte 2 Werte angeben.", "Error",
							JOptionPane.ERROR_MESSAGE);
					arr.clear();
					arr1.clear();
					return;
				}
				if (rechteck.getAlpha() != null && rechteck.getAlpha() > 0.0) {
					Datenbank.save_DB(rechteck);
				} else {
					JOptionPane.showMessageDialog(frmGeometrie, "Rechteck ist nicht Konstruierbar!", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (tf_Rechteck_alpha.getText().isEmpty()) {

					tf_Rechteck_alpha.setText(
							String.valueOf((double) Math.round(rechteck.getAlpha() * 100) / 100).replace(".", ","));

				}
				if (tf_Rechteck_beta.getText().isEmpty()) {
					if (rechteck.getBeta() != null) {
						tf_Rechteck_beta.setText(
								String.valueOf((double) Math.round(rechteck.getBeta() * 100) / 100).replace(".", ","));
					} else {
						JOptionPane.showMessageDialog(frmGeometrie, "Rechteck ist nicht Konstruierbar!", "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
				if (tf_Rechteck_gamma.getText().isEmpty()) {
					if (rechteck.getGamma() != null) {
						tf_Rechteck_gamma.setText(
								String.valueOf((double) Math.round(rechteck.getGamma() * 100) / 100).replace(".", ","));
					} else {
						JOptionPane.showMessageDialog(frmGeometrie, "Rechteck ist nicht Konstruierbar!", "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
				if (tf_Rechteck_delta.getText().isEmpty()) {
					if (rechteck.getDelta() != null) {
						tf_Rechteck_delta.setText(
								String.valueOf((double) Math.round(rechteck.getDelta() * 100) / 100).replace(".", ","));
					} else {
						JOptionPane.showMessageDialog(frmGeometrie, "Rechteck ist nicht Konstruierbar!", "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
				if (tf_Rechteck_sidea.getText().isEmpty()) {
					if (rechteck.getSidea() != null) {
						tf_Rechteck_sidea.setText(
								String.valueOf((double) Math.round(rechteck.getSidea() * 100) / 100).replace(".", ","));
					} else {
						JOptionPane.showMessageDialog(frmGeometrie, "Rechteck ist nicht Konstruierbar!", "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
				if (tf_Rechteck_sideb.getText().isEmpty()) {
					if (rechteck.getSideb() != null) {
						tf_Rechteck_sideb.setText(
								String.valueOf((double) Math.round(rechteck.getSideb() * 100) / 100).replace(".", ","));
					} else {
						JOptionPane.showMessageDialog(frmGeometrie, "Rechteck ist nicht Konstruierbar!", "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
				if (tf_Rechteck_umfang.getText().isEmpty()) {
					if (rechteck.getU() != null) {
						tf_Rechteck_umfang.setText(
								String.valueOf((double) Math.round(rechteck.getU() * 100) / 100).replace(".", ","));
					} else {
						JOptionPane.showMessageDialog(frmGeometrie, "Rechteck ist nicht Konstruierbar!", "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
				if (tf_Rechteck_flaeche.getText().isEmpty()) {
					if (rechteck.getA() != null) {
						tf_Rechteck_flaeche.setText(
								String.valueOf((double) Math.round(rechteck.getA() * 100) / 100).replace(".", ","));
					} else {
						JOptionPane.showMessageDialog(frmGeometrie, "Rechteck ist nicht Konstruierbar!", "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
				if (tf_Rechteck_diag.getText().isEmpty()) {
					if (rechteck.getD() != null) {
						tf_Rechteck_diag.setText(
								String.valueOf((double) Math.round(rechteck.getD() * 100) / 100).replace(".", ","));
					} else {
						JOptionPane.showMessageDialog(frmGeometrie, "Rechteck ist nicht Konstruierbar!", "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
				}

			}
		});
		GridBagConstraints gbc_btnRechteck_calc = new GridBagConstraints();
		gbc_btnRechteck_calc.insets = new Insets(0, 0, 0, 5);
		gbc_btnRechteck_calc.gridx = 4;
		gbc_btnRechteck_calc.gridy = 6;
		panel_Rechteck_1.add(btnRechteck_calc, gbc_btnRechteck_calc);

		JButton btnRechteck_clear = new JButton("L\u00F6schen");
		btnRechteck_clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tf_Rechteck_alpha.setText(null);
				tf_Rechteck_beta.setText(null);
				tf_Rechteck_gamma.setText(null);
				tf_Rechteck_sidea.setText(null);
				tf_Rechteck_sideb.setText(null);
				tf_Rechteck_delta.setText(null);
				tf_Rechteck_diag.setText(null);
				tf_Rechteck_umfang.setText(null);
				tf_Rechteck_flaeche.setText(null);
			}
		});
		GridBagConstraints gbc_btnRechteck_clear = new GridBagConstraints();
		gbc_btnRechteck_clear.insets = new Insets(0, 0, 0, 5);
		gbc_btnRechteck_clear.gridx = 6;
		gbc_btnRechteck_clear.gridy = 6;
		panel_Rechteck_1.add(btnRechteck_clear, gbc_btnRechteck_clear);
		layeredPane_Rechteck.setLayout(gl_layeredPane_Rechteck);

		JLayeredPane layeredPane_Quadrat = new JLayeredPane();
		frmGeometrie.getContentPane().add(layeredPane_Quadrat, "panel_Quadrat");

		JPanel panel_Quadrat_1 = new JPanel();
		panel_Quadrat_1.setOpaque(false);
		GridBagLayout gbl_panel_Quadrat_1 = new GridBagLayout();
		gbl_panel_Quadrat_1.columnWidths = new int[] { 250, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel_Quadrat_1.rowHeights = new int[] { 300, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel_Quadrat_1.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panel_Quadrat_1.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel_Quadrat_1.setLayout(gbl_panel_Quadrat_1);

		JPanel panel_Quadrat_Bild = new JPanel();

		JLabel lblNewLabel_8_2 = new JLabel("");
		// bild einf�gen
		try {
			BufferedImage image = ImageIO.read(getClass().getResourceAsStream("Quadrat.png"));
			lblNewLabel_8_2.setIcon(new ImageIcon(resizeImage(image, 295, 284)));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		panel_Quadrat_Bild.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panel_Quadrat_Bild.add(lblNewLabel_8_2);
		GroupLayout gl_layeredPane_Quadrat = new GroupLayout(layeredPane_Quadrat);
		gl_layeredPane_Quadrat.setHorizontalGroup(
				gl_layeredPane_Quadrat.createParallelGroup(Alignment.LEADING).addGap(0, 884, Short.MAX_VALUE)
						.addComponent(panel_Quadrat_1, GroupLayout.PREFERRED_SIZE, 884, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_Quadrat_Bild, GroupLayout.PREFERRED_SIZE, 884, GroupLayout.PREFERRED_SIZE));
		gl_layeredPane_Quadrat.setVerticalGroup(
				gl_layeredPane_Quadrat.createParallelGroup(Alignment.LEADING).addGap(0, 539, Short.MAX_VALUE)
						.addComponent(panel_Quadrat_1, GroupLayout.PREFERRED_SIZE, 539, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_Quadrat_Bild, GroupLayout.PREFERRED_SIZE, 539, GroupLayout.PREFERRED_SIZE));

		JLabel lblQuadrat_sidea = new JLabel("Seite A");
		GridBagConstraints gbc_lblQuadrat_sidea = new GridBagConstraints();
		gbc_lblQuadrat_sidea.anchor = GridBagConstraints.EAST;
		gbc_lblQuadrat_sidea.insets = new Insets(0, 0, 5, 5);
		gbc_lblQuadrat_sidea.gridx = 4;
		gbc_lblQuadrat_sidea.gridy = 3;
		panel_Quadrat_1.add(lblQuadrat_sidea, gbc_lblQuadrat_sidea);

		tf_Quadrat_sidea = new JTextField();
		GridBagConstraints gbc_tf_Quadrat_sidea = new GridBagConstraints();
		gbc_tf_Quadrat_sidea.insets = new Insets(0, 0, 5, 5);
		gbc_tf_Quadrat_sidea.fill = GridBagConstraints.HORIZONTAL;
		gbc_tf_Quadrat_sidea.gridx = 5;
		gbc_tf_Quadrat_sidea.gridy = 3;
		panel_Quadrat_1.add(tf_Quadrat_sidea, gbc_tf_Quadrat_sidea);
		tf_Quadrat_sidea.setColumns(10);

		JLabel lblQuadrat_A = new JLabel("Fl\u00E4che");
		GridBagConstraints gbc_lblQuadrat_A = new GridBagConstraints();
		gbc_lblQuadrat_A.anchor = GridBagConstraints.EAST;
		gbc_lblQuadrat_A.insets = new Insets(0, 0, 5, 5);
		gbc_lblQuadrat_A.gridx = 6;
		gbc_lblQuadrat_A.gridy = 3;
		panel_Quadrat_1.add(lblQuadrat_A, gbc_lblQuadrat_A);

		tf_Quadrat_A = new JTextField();
		GridBagConstraints gbc_tf_Quadrat_A = new GridBagConstraints();
		gbc_tf_Quadrat_A.insets = new Insets(0, 0, 5, 0);
		gbc_tf_Quadrat_A.fill = GridBagConstraints.HORIZONTAL;
		gbc_tf_Quadrat_A.gridx = 7;
		gbc_tf_Quadrat_A.gridy = 3;
		panel_Quadrat_1.add(tf_Quadrat_A, gbc_tf_Quadrat_A);
		tf_Quadrat_A.setColumns(10);

		JLabel lblQuadrat_Umfang = new JLabel("Umfang");
		GridBagConstraints gbc_lblQuadrat_Umfang = new GridBagConstraints();
		gbc_lblQuadrat_Umfang.anchor = GridBagConstraints.EAST;
		gbc_lblQuadrat_Umfang.insets = new Insets(0, 0, 5, 5);
		gbc_lblQuadrat_Umfang.gridx = 4;
		gbc_lblQuadrat_Umfang.gridy = 4;
		panel_Quadrat_1.add(lblQuadrat_Umfang, gbc_lblQuadrat_Umfang);

		tf_Quadrat_U = new JTextField();
		GridBagConstraints gbc_tf_Quadrat_U = new GridBagConstraints();
		gbc_tf_Quadrat_U.insets = new Insets(0, 0, 5, 5);
		gbc_tf_Quadrat_U.fill = GridBagConstraints.HORIZONTAL;
		gbc_tf_Quadrat_U.gridx = 5;
		gbc_tf_Quadrat_U.gridy = 4;
		panel_Quadrat_1.add(tf_Quadrat_U, gbc_tf_Quadrat_U);
		tf_Quadrat_U.setColumns(10);

		JLabel lblQuadrat_Diagonale = new JLabel("Diagonale");
		GridBagConstraints gbc_lblQuadrat_Diagonale = new GridBagConstraints();
		gbc_lblQuadrat_Diagonale.anchor = GridBagConstraints.EAST;
		gbc_lblQuadrat_Diagonale.insets = new Insets(0, 0, 5, 5);
		gbc_lblQuadrat_Diagonale.gridx = 6;
		gbc_lblQuadrat_Diagonale.gridy = 4;
		panel_Quadrat_1.add(lblQuadrat_Diagonale, gbc_lblQuadrat_Diagonale);

		tf_Quadrat_diag = new JTextField();
		GridBagConstraints gbc_tf_Quadrat_diag = new GridBagConstraints();
		gbc_tf_Quadrat_diag.insets = new Insets(0, 0, 5, 0);
		gbc_tf_Quadrat_diag.fill = GridBagConstraints.HORIZONTAL;
		gbc_tf_Quadrat_diag.gridx = 7;
		gbc_tf_Quadrat_diag.gridy = 4;
		panel_Quadrat_1.add(tf_Quadrat_diag, gbc_tf_Quadrat_diag);
		tf_Quadrat_diag.setColumns(10);

		JButton btnQuadrat_calc = new JButton("Berechnen");
		btnQuadrat_calc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Quadrat quadrat1 = new Quadrat();
				Figur quadrat = quadrat1;
				int i = 0;
				ArrayList<String> arr = new ArrayList<String>();
				ArrayList<Double> arr1 = new ArrayList<Double>();
				if (!tf_Quadrat_sidea.getText().isEmpty()) {
					try {
						((Quadrat) quadrat)
								.setSidea(Double.parseDouble(tf_Quadrat_sidea.getText().replaceAll(",", ".")));
						i++;
						arr.add("sidea");
						arr1.add(((Quadrat) quadrat).getSidea());
					} catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog(frmGeometrie, "Ung�ltiger Wert \"Seite A\" ", "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
				if (!tf_Quadrat_diag.getText().isEmpty()) {
					try {
						((Quadrat) quadrat).setD(Double.parseDouble(tf_Quadrat_diag.getText().replaceAll(",", ".")));
						i++;
						arr.add("A");
						arr1.add(quadrat.getA());
					} catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog(frmGeometrie, "Ung�ltiger Wert \"Diagonale\" ", "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
				if (!tf_Quadrat_A.getText().isEmpty()) {
					try {
						quadrat.setA(Double.parseDouble(tf_Quadrat_A.getText().replaceAll(",", ".")));
						i++;
						arr.add("U");
						arr1.add(quadrat.getU());
					} catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog(frmGeometrie, "Ung�ltiger Wert \"Seite A\" ", "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
				if (!tf_Quadrat_U.getText().isEmpty()) {
					try {
						quadrat.setU(Double.parseDouble(tf_Quadrat_U.getText().replaceAll(",", ".")));
						i++;
						arr.add("diag");
						arr1.add(((Quadrat) quadrat).getD());
					} catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog(frmGeometrie, "Ung�ltiger Wert \"Umfang\" ", "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
				if (i == 1) {
					if (!Datenbank.getDB(quadrat, arr, arr1)) {
						quadrat1.calculate();
					}
				} else {
					JOptionPane.showMessageDialog(frmGeometrie, "Bitte einen Wert angeben.", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (((Quadrat) quadrat).getSidea() != null && (((Quadrat) quadrat).getSidea() > 0)
						&& ((Quadrat) quadrat).getD() != null && ((Quadrat) quadrat).getD() > 0) {
					Datenbank.save_DB(quadrat);
					if (tf_Quadrat_sidea.getText().isEmpty()) {
						tf_Quadrat_sidea
								.setText(String.valueOf((double) Math.round(((Quadrat) quadrat).getSidea() * 100) / 100)
										.replace(".", ","));
					}
					if (tf_Quadrat_diag.getText().isEmpty()) {
						tf_Quadrat_diag
								.setText(String.valueOf((double) Math.round(((Quadrat) quadrat).getD() * 100) / 100)
										.replace(".", ","));
					}
					if (tf_Quadrat_A.getText().isEmpty()) {
						tf_Quadrat_A.setText(
								String.valueOf((double) Math.round(quadrat.getA() * 100) / 100).replace(".", ","));
					}
					if (tf_Quadrat_U.getText().isEmpty()) {
						tf_Quadrat_U.setText(
								String.valueOf((double) Math.round(quadrat.getU() * 100) / 100).replace(".", ","));
					}
				} else {
					JOptionPane.showMessageDialog(frmGeometrie, "Quadrat ist nicht Konstruierbar!", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		GridBagConstraints gbc_btnQuadrat_calc = new GridBagConstraints();
		gbc_btnQuadrat_calc.insets = new Insets(0, 0, 0, 5);
		gbc_btnQuadrat_calc.gridx = 5;
		gbc_btnQuadrat_calc.gridy = 6;
		panel_Quadrat_1.add(btnQuadrat_calc, gbc_btnQuadrat_calc);

		JButton btnQuadrat_clear = new JButton("L\u00F6schen");
		btnQuadrat_clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tf_Quadrat_A.setText(null);
				tf_Quadrat_diag.setText(null);
				tf_Quadrat_sidea.setText(null);
				tf_Quadrat_U.setText(null);
			}
		});
		GridBagConstraints gbc_btnQuadrat_clear = new GridBagConstraints();
		gbc_btnQuadrat_clear.gridx = 7;
		gbc_btnQuadrat_clear.gridy = 6;
		panel_Quadrat_1.add(btnQuadrat_clear, gbc_btnQuadrat_clear);
		layeredPane_Quadrat.setLayout(gl_layeredPane_Quadrat);

		JPanel panel = new JPanel();
		frmGeometrie.getContentPane().add(panel, "panel_showall");

		tableModel = Datenbank.getall("Rechteck", tableModel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0, 0, 0, 0, 0 };
		gbl_panel.rowHeights = new int[] { 0, 100, 0 };
		gbl_panel.columnWeights = new double[] { 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);
		database = new JTable(tableModel);
		database.setShowGrid(true);
		database.setShowVerticalLines(true);
		ButtonGroup gruppe = new ButtonGroup();
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Rechteck");
		rdbtnNewRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tableModel.setRowCount(0);
				tableModel = Datenbank.getall("Rechteck", tableModel);
				database.setModel(tableModel);
				tableModel.setColumnCount(10);
				database.getTableHeader().resizeAndRepaint();
			}
		});

		GridBagConstraints gbc_rdbtnNewRadioButton = new GridBagConstraints();
		gbc_rdbtnNewRadioButton.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnNewRadioButton.gridx = 0;
		gbc_rdbtnNewRadioButton.gridy = 0;
		panel.add(rdbtnNewRadioButton, gbc_rdbtnNewRadioButton);
		rdbtnNewRadioButton.setSelected(true);
		gruppe.add(rdbtnNewRadioButton);

		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Quadrat");
		rdbtnNewRadioButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tableModel.setRowCount(0);
				tableModel = Datenbank.getall("Quadrat", tableModel);
				database.setModel(tableModel);
				tableModel.setColumnCount(5);
				database.getTableHeader().resizeAndRepaint();
			}
		});
		GridBagConstraints gbc_rdbtnNewRadioButton_1 = new GridBagConstraints();
		gbc_rdbtnNewRadioButton_1.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnNewRadioButton_1.gridx = 1;
		gbc_rdbtnNewRadioButton_1.gridy = 0;
		panel.add(rdbtnNewRadioButton_1, gbc_rdbtnNewRadioButton_1);
		gruppe.add(rdbtnNewRadioButton_1);

		JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("Kreis");
		rdbtnNewRadioButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tableModel.setRowCount(0);
				tableModel = Datenbank.getall("Kreis", tableModel);
				database.setModel(tableModel);
				tableModel.setColumnCount(5);
				database.getTableHeader().resizeAndRepaint();
			}
		});
		GridBagConstraints gbc_rdbtnNewRadioButton_2 = new GridBagConstraints();
		gbc_rdbtnNewRadioButton_2.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnNewRadioButton_2.gridx = 2;
		gbc_rdbtnNewRadioButton_2.gridy = 0;
		panel.add(rdbtnNewRadioButton_2, gbc_rdbtnNewRadioButton_2);
		gruppe.add(rdbtnNewRadioButton_2);

		JRadioButton rdbtnNewRadioButton_3 = new JRadioButton("Dreieck");
		rdbtnNewRadioButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tableModel.setRowCount(0);
				tableModel = Datenbank.getall("Dreieck", tableModel);
				database.setModel(tableModel);
				tableModel.setColumnCount(14);
				database.getTableHeader().resizeAndRepaint();
			}
		});
		GridBagConstraints gbc_rdbtnNewRadioButton_3 = new GridBagConstraints();
		gbc_rdbtnNewRadioButton_3.insets = new Insets(0, 0, 5, 0);
		gbc_rdbtnNewRadioButton_3.gridx = 3;
		gbc_rdbtnNewRadioButton_3.gridy = 0;
		panel.add(rdbtnNewRadioButton_3, gbc_rdbtnNewRadioButton_3);

		gruppe.add(rdbtnNewRadioButton_3);

		JScrollPane scrollPane = new JScrollPane(database);
		scrollPane.setViewportBorder(null);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 4;
		gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		panel.add(scrollPane, gbc_scrollPane);

		JMenuBar menuBar = new JMenuBar();
		frmGeometrie.setJMenuBar(menuBar);

		JMenu mnMenu = new JMenu("Menu");
		menuBar.add(mnMenu);

		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		JMenuItem mntmMainmenu = new JMenuItem("Hauptmen\u00FC");
		mntmMainmenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myCL.show(frmGeometrie.getContentPane(), "panel_Main");
			}
		});
		mnMenu.add(mntmMainmenu);

		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Alle Eintr\u00E4ge zeigen");
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//System.out.println(gruppe);
				myCL.show(frmGeometrie.getContentPane(), "panel_showall");
			}
		});
		mnMenu.add(mntmNewMenuItem_4);
		mntmExit.setHorizontalAlignment(SwingConstants.LEFT);
		mnMenu.add(mntmExit);

		JMenu mnNewMenu_2 = new JMenu("Formen");
		menuBar.add(mnNewMenu_2);

		JMenuItem mntmNewMenuItem = new JMenuItem("Kreis");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myCL.show(frmGeometrie.getContentPane(), "panel_Kreis");
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem);

		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Dreieck");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myCL.show(frmGeometrie.getContentPane(), "panel_Dreieck");
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_3);

		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Quadrat");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myCL.show(frmGeometrie.getContentPane(), "panel_Quadrat");
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_1);

		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Rechteck");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmGeometrie.setTitle("Rechteck");
				myCL.show(frmGeometrie.getContentPane(), "panel_Rechteck");
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_2);

	}

	/**
	 * Resizes the Image.
	 * 
	 * @param originalImage
	 * @param targetWidth
	 * @param targetHeight
	 * @return
	 * @throws IOException
	 */

	BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) throws IOException {
		BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_ARGB);
		Graphics2D graphics2D = resizedImage.createGraphics();
		graphics2D.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
		graphics2D.dispose();
		return resizedImage;
	}
}