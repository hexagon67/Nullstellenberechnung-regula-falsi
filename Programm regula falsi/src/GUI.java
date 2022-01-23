import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.swing.UIManager;

import javax.swing.border.TitledBorder;

import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries.XYSeriesRenderStyle;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JRadioButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.Font;

import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class GUI extends JFrame {


	final XYChart chart = new XYChartBuilder().width(600).height(400).title("Graph der Funktion").xAxisTitle("X").yAxisTitle("Y").build();
	private String funktion = "";
	private double[][] tabelle;
	private JTextField jTBFunktion;
	private JTextField jTBFunktionXMin;
	private JTextField jTBFunktionXMax;
	private JTable tRegulaFalsi;
	private JTextField jTBFunktionIntervall;
	private JTextField jTBNullstellenXMin;
	private JTextField jTBNullstellenXMax;
	private JTextField jTBNullstellenIterationen;
	private JTextField jTFErgebnis;
	
	/**
	 * Launch the application.
	 */
	public static void guiStart() {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
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
	@SuppressWarnings("serial")
	public GUI() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(GUI.class.getResource("/resources/Titel Icon.png")));
		setTitle("Regula Falsi - Nullstellenberechnung");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 901, 568);
		getContentPane().setLayout(null);
		getContentPane().setLayout(null);
		getContentPane().setLayout(null);
		getContentPane().setLayout(null);
		getContentPane().setLayout(null);
		
		// Customize Chart
		chart.getStyler().setLegendVisible(false);
		chart.getStyler().setDefaultSeriesRenderStyle(XYSeriesRenderStyle.Line);
		chart.getStyler().setChartBackgroundColor(new Color(240, 240, 240));
		chart.getStyler().setMarkerSize(0);

		// Series
		chart.addSeries("funktion", new double[] {0}, new double[] {0});
				
				JPanel reloadPane = new JPanel();
				reloadPane.setBounds(0, 0, 472, 527);
				getContentPane().add(reloadPane);
				reloadPane.setLayout(null);
				
				JLabel lblNewLabel_5 = new JLabel("");
				lblNewLabel_5.setBounds(0, 483, 145, 44);
				reloadPane.add(lblNewLabel_5);
				lblNewLabel_5.setIcon(new ImageIcon(GUI.class.getResource("/resources/Logo-XChart.png")));
		
				JPanel chartPanel = new XChartPanel<XYChart>(chart);
				chartPanel.setBounds(0, 0, 472, 501);
				reloadPane.add(chartPanel);
				chartPanel.setLayout(null);
				
				JLabel lblNewLabel_4 = new JLabel("Graph gezeichnet mit:");
				lblNewLabel_4.setBounds(10, 476, 105, 14);
				chartPanel.add(lblNewLabel_4);
		
		JLabel lblNewLabel = new JLabel("Funktion:");
		lblNewLabel.setBounds(482, 11, 56, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_3 = new JLabel("Dies ist keine g\u00FCltige Funktion!");
		lblNewLabel_3.setForeground(Color.RED);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_3.setBounds(531, 11, 176, 14);
		lblNewLabel_3.setVisible(false);
		getContentPane().add(lblNewLabel_3);
		
		jTBFunktion = new JTextField();
		jTBFunktion.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				funktion = jTBFunktion.getText();
				if (!funktion.trim().isEmpty()) {
					try {
						tabelle = FunktionSplitten.funktionSplitten(funktion);
						lblNewLabel_3.setVisible(false);
					} catch (Exception e1) {
						if (e1.getMessage().equals("Die Funktion wurde nicht richtig angegeben")) {
							lblNewLabel_3.setVisible(true);
							final Runnable runnable =
								     (Runnable) Toolkit.getDefaultToolkit().getDesktopProperty("win.sound.exclamation");
							if (runnable != null) runnable.run();
						} else {
							e1.printStackTrace();
						}
					}
				}
			}
		});
		jTBFunktion.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				funktion = jTBFunktion.getText();
				lblNewLabel_3.setVisible(false);
			}
		});
		jTBFunktion.setBounds(492, 25, 383, 20);
		getContentPane().add(jTBFunktion);
		jTBFunktion.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Funktion zeichnen:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(502, 67, 176, 151);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("X-Min:");
		lblNewLabel_1.setBounds(10, 26, 46, 14);
		panel.add(lblNewLabel_1);
		
		jTBFunktionXMin = new JTextField();
		jTBFunktionXMin.setBounds(50, 23, 116, 20);
		panel.add(jTBFunktionXMin);
		jTBFunktionXMin.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("X-Max:");
		lblNewLabel_1_1.setBounds(10, 48, 46, 14);
		panel.add(lblNewLabel_1_1);
		
		jTBFunktionXMax = new JTextField();
		jTBFunktionXMax.setBounds(50, 45, 116, 20);
		panel.add(jTBFunktionXMax);
		jTBFunktionXMax.setColumns(10);
		
		JButton btnFunktionBerechnen = new JButton("Funktion zeichnen");
		btnFunktionBerechnen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				double xMin = 0;
				double xMax = 0;
				double Intervallgr = 0;
				
				try {
					xMin = Double.parseDouble(kommaErsetzen(jTBFunktionXMin.getText().trim()));
					xMax = Double.parseDouble(kommaErsetzen(jTBFunktionXMax.getText().trim()));
					Intervallgr = Double.parseDouble(kommaErsetzen(jTBFunktionIntervall.getText().trim()));
				} catch (NumberFormatException e1) {
					String s = e1.getMessage();
					s = s.substring(s.indexOf('"') + 1, s.length() - 1);
					falscheZahl(s);
					return;
				}
				
				double [][] xyWerte = getGraphPoints(tabelle, xMin, xMax, Intervallgr);
				
				EventQueue.invokeLater(new Runnable() {
					@Override
					public void run() {
						chart.updateXYSeries("funktion", xyWerte[0], xyWerte[1], null);
						reloadPane.repaint();
					}
				});
			}
		});
		btnFunktionBerechnen.setBounds(10, 117, 156, 23);
		panel.add(btnFunktionBerechnen);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("Intervallgr\u00F6\u00DFe:");
		lblNewLabel_1_1_2.setBounds(10, 70, 80, 14);
		panel.add(lblNewLabel_1_1_2);
		
		jTBFunktionIntervall = new JTextField();
		jTBFunktionIntervall.setText("0.1");
		jTBFunktionIntervall.setToolTipText("Das Programm st\u00FCrzt ab ist dieser Wert zu klein!");
		jTBFunktionIntervall.setColumns(10);
		jTBFunktionIntervall.setBounds(50, 86, 116, 20);
		panel.add(jTBFunktionIntervall);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(492, 229, 383, 202);
		getContentPane().add(scrollPane);
		
		tRegulaFalsi = new JTable();
		tRegulaFalsi.setColumnSelectionAllowed(true);
		tRegulaFalsi.setCellSelectionEnabled(true);
		tRegulaFalsi.setEnabled(false);
		tRegulaFalsi.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"x0", "x1", "x2"
			}
		) {
			Class[] columnTypes = new Class[] {
				Double.class, Double.class, Double.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		scrollPane.setViewportView(tRegulaFalsi);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Nullstellen berechnen:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(688, 67, 176, 151);
		getContentPane().add(panel_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("X-Min:");
		lblNewLabel_1_2.setBounds(10, 26, 46, 14);
		panel_1.add(lblNewLabel_1_2);
		
		jTBNullstellenXMin = new JTextField();
		jTBNullstellenXMin.setColumns(10);
		jTBNullstellenXMin.setBounds(50, 23, 116, 20);
		panel_1.add(jTBNullstellenXMin);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("X-Max:");
		lblNewLabel_1_1_1.setBounds(10, 48, 46, 14);
		panel_1.add(lblNewLabel_1_1_1);
		
		jTBNullstellenXMax = new JTextField();
		jTBNullstellenXMax.setColumns(10);
		jTBNullstellenXMax.setBounds(50, 45, 116, 20);
		panel_1.add(jTBNullstellenXMax);
		
		JRadioButton rdbtBeendet = new JRadioButton("Das Verfahren wurde beendet.");
		rdbtBeendet.setBounds(673, 438, 202, 23);
		getContentPane().add(rdbtBeendet);
		
		JRadioButton rdbtnAbgebrochen = new JRadioButton("Das Verfahren wurde abgebrochen.");
		rdbtnAbgebrochen.setBounds(673, 463, 202, 23);
		getContentPane().add(rdbtnAbgebrochen);
		
		JButton btnNullstellenBerechnen = new JButton("Nullstellen berechnen");
		btnNullstellenBerechnen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object[] ergebnisRegulaFalsi = {};
				
				String xmin ="";
				String xmax ="";
				String maxIntervalle ="";
				
				try {
					xmin = kommaErsetzen(jTBNullstellenXMin.getText());
					xmax = kommaErsetzen(jTBNullstellenXMax.getText());
					maxIntervalle = jTBNullstellenIterationen.getText();
				} catch (NumberFormatException e1) {
					String s = e1.getMessage();
					s = s.substring(s.indexOf('"') + 1, s.length() - 1);
					falscheZahl(s);
					return;
				}
				
				if(xmin.trim().isEmpty() || xmax.trim().isEmpty() || maxIntervalle.trim().isEmpty()) {
					falscheZahl(new String());
					return;
				}
				
				try {
					ergebnisRegulaFalsi = RegulaFalsi.getRegulaFalsi(new String[] {funktion, xmax, xmin, maxIntervalle});
				} catch (Exception e1) {
					if (e1.getMessage().equals("Die Funktion wurde nicht richtig angegeben")) {
						lblNewLabel_3.setVisible(true);
						final Runnable runnable =
							     (Runnable) Toolkit.getDefaultToolkit().getDesktopProperty("win.sound.exclamation");
						if (runnable != null) runnable.run();
					} else {
						e1.printStackTrace();
					}
				}
				
				jTFErgebnis.setText(""+(Double)ergebnisRegulaFalsi[0]);
				
				if ((boolean) ergebnisRegulaFalsi[1]) {
					rdbtBeendet.setSelected(true);
					rdbtnAbgebrochen.setSelected(false);
				} else {
					rdbtBeendet.setSelected(false);
					rdbtnAbgebrochen.setSelected(true);
				}
				
				for (Double[] dl : (Double[][])ergebnisRegulaFalsi[2]) {
					for (Double d : dl) {
						System.out.print(d + ", ");
					}
					System.out.println("");
				}
				
				tRegulaFalsi.setModel(new DefaultTableModel((Double[][])(ergebnisRegulaFalsi[2]),
						new String[] {
							"x0", "x1", "x2"
						}
					) {
						Class[] columnTypes = new Class[] {
							Double.class, Double.class, Double.class
						};
						public Class getColumnClass(int columnIndex) {
							return columnTypes[columnIndex];
						}
					});
			}
		});
		btnNullstellenBerechnen.setBounds(10, 117, 156, 23);
		panel_1.add(btnNullstellenBerechnen);
		
		JLabel lblNewLabel_1_1_2_1 = new JLabel("Max. Iterationen:");
		lblNewLabel_1_1_2_1.setBounds(10, 70, 96, 14);
		panel_1.add(lblNewLabel_1_1_2_1);
		
		jTBNullstellenIterationen = new JTextField();
		jTBNullstellenIterationen.setText("200");
		jTBNullstellenIterationen.setColumns(10);
		jTBNullstellenIterationen.setBounds(50, 86, 116, 20);
		panel_1.add(jTBNullstellenIterationen);
		
		jTFErgebnis = new JTextField();
		jTFErgebnis.setEditable(false);
		jTFErgebnis.setBounds(545, 451, 122, 20);
		getContentPane().add(jTFErgebnis);
		jTFErgebnis.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Ergebnis:");
		lblNewLabel_2.setBounds(492, 454, 64, 14);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblAufbau = new JLabel("<html><u>Wie wird die Funktion aufgebaut?</u></<html>");
		lblAufbau.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				showFunctionInformation();
			}
		});
		lblAufbau.setForeground(Color.BLUE);
		lblAufbau.setBounds(492, 45, 175, 14);
		getContentPane().add(lblAufbau);
		
		JLabel lblNewLabel_6 = new JLabel("\u00A9 by Paul Suhr 2022");
		lblNewLabel_6.setBounds(775, 513, 100, 14);
		getContentPane().add(lblNewLabel_6);
	}
	
	private void showFunctionInformation() {
		JOptionPane.showMessageDialog(getContentPane(), "Jeder Summand wird durch ein + oder - von dem nächsten Summanden getrennt.\r\n"
				+ "Die Summanden sind so aufgebaut:\r\n"
				+ "<Vorfaktor>*x^<Exponent> - Beispiel: 3*x^5\r\n"
				+ "Sollte kein x vorhanden sein, muss der Exponent als x^0 angegeben werden. - Beispiel: 5*x^0", 
				"Information zum Aufbau der Funktion", JOptionPane.INFORMATION_MESSAGE);
	}
	
	private double[][] getGraphPoints(double[][] tabelle, double Intervallx1, double Intervallx2, double genauigkeit) {
		if (Intervallx1 > Intervallx2) {
			double temp = Intervallx1;
			Intervallx1 = Intervallx2;
			Intervallx2 = temp;
		}
		int länge = (int)((Intervallx2-Intervallx1)/genauigkeit);
		double[][] punkte = new double[2][länge];
		
		for(int i=0; i<länge; i++) {
			double x = i*genauigkeit+Intervallx1;
			punkte[0][i] = x;
			punkte[1][i] = RegulaFalsi.funktionBerechnen(x, tabelle);
		}
				
		return punkte;
	}
	
	private String kommaErsetzen(String doubleZahl) {
		char[] doubleZahlArray = doubleZahl.toCharArray();
		String ergebnis = "";
		
		for (int i = 0; i<doubleZahlArray.length; i++) {
			if (doubleZahlArray[i] == ',') {
				doubleZahlArray[i] = '.';
			}
			ergebnis += doubleZahlArray[i];
		}
		return ergebnis;
	}
	
	private void falscheZahl(String zahl) {
		JOptionPane.showMessageDialog(null, "Der folgende Wert kann nicht verarbeitet werden und muss geändert werden:\r\n" 
	+ zahl, "Falscher Wert", JOptionPane.INFORMATION_MESSAGE);
	}
}
