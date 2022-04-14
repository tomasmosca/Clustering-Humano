package tp2;

import java.awt.EventQueue;



import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;

import grafo.Vertice;

import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.SpinnerNumberModel;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class InterfazPrincipal {

	private JFrame frmClusteringHumanoV;
	private JTable table;
	private JButton btnEliminar;
	private JButton btnBuscarSimilaridad;
	private JButton btnSalir;
	private JTextField textFieldPersona;
	private JSpinner spinnerDeportes;
	private JSpinner spinnerMusica;
	private JSpinner spinnerEspectaculos;
	private JSpinner spinnerCiencia;
	private InterfazSecundaria frame2;
	private AlgoritmoSimilaridad l = new AlgoritmoSimilaridad();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfazPrincipal window = new InterfazPrincipal();
					window.frmClusteringHumanoV.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public InterfazPrincipal() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmClusteringHumanoV = new JFrame();
		frmClusteringHumanoV.setResizable(false);
		frmClusteringHumanoV.setTitle("Clustering humano v1.0");
		frmClusteringHumanoV.setBounds(100, 100, 715, 500);
		frmClusteringHumanoV.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmClusteringHumanoV.setLocationRelativeTo(null);
		frmClusteringHumanoV.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 474, 439);
		frmClusteringHumanoV.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setRowHeight(30);
		scrollPane.setViewportView(table);
		table.setFont(new Font("Tahoma", Font.BOLD, 16));
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setDefaultEditor(Object.class, null);
		DefaultTableModel model;
		table.setModel(model = new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Persona", "Deportes", "Musica", "Espectaculos", "Ciencia"
			}
		));
		
		JButton btnAñadir = new JButton("A\u00F1adir");
		btnAñadir.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnAñadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//crea nueva fila con cada valor
				Object[] filas = new Object[5];
				filas[0] = textFieldPersona.getText();
				filas[1] = spinnerDeportes.getValue();
				filas[2] = spinnerMusica.getValue();
				filas[3] = spinnerEspectaculos.getValue();
				filas[4] = spinnerCiencia.getValue();
				if(textFieldPersona.getText().length()>=1) {
					int fila = table.getRowCount();
					Object[] contenido = new Object[fila];
					for(int i=0;i<fila;i++) {
						contenido[i] = table.getValueAt(i, 0);
					}
					//verifica si la persona ya se encuentra en la tabla
					boolean existe = Arrays.asList(contenido).contains(textFieldPersona.getText());
					if(!existe) {
						model.addRow(filas);
					}else {
						JOptionPane.showMessageDialog(frmClusteringHumanoV, "Esta persona ya esta en la tabla", "Clustering humano", JOptionPane.INFORMATION_MESSAGE);
					}
				}else {
					JOptionPane.showMessageDialog(frmClusteringHumanoV, "Ingrese nombre de la persona", "Clustering humano", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnAñadir.setBounds(522, 206, 141, 41);
		frmClusteringHumanoV.getContentPane().add(btnAñadir);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int i = table.getSelectedRow();
				if (i >= 0) {
					model.removeRow(i);
				}
			}
		});
		btnEliminar.setBounds(522, 268, 141, 41);
		frmClusteringHumanoV.getContentPane().add(btnEliminar);
		
		btnBuscarSimilaridad = new JButton("Buscar Similaridad");
		btnBuscarSimilaridad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int fila = table.getRowCount();
				int columna = table.getColumnCount();
				if(fila>=2) {
					for(int i=0;i<fila;i++) {
						List<Integer> list = new ArrayList<Integer>(); //obtiene datos de la tabla
						for(int j=1;j<columna;j++) {
							l.obtenerData(table.getValueAt(i, j),list);
						}
						l.obtenerData(table.getValueAt(i, 0), list);
					}
					Vertice[] v = new Vertice[fila];
					v = l.algoritmoSimilaridad(fila); //obtiene arreglo vertices resultado y lo asigna al arreglo de vertices v
					frame2 = new InterfazSecundaria(); //crea interfaz secundaria con ambos grupos para visualizar similaridad
					DefaultTableModel grupo1 = new DefaultTableModel(
							new Object[][] {
							},
							new String[] {
								"Nombre"
							}
						);
					frame2.getTable().setModel(grupo1);
					DefaultTableModel grupo2 = new DefaultTableModel(
							new Object[][] {
							},
							new String[] {
								"Nombre"
							}
						);
					frame2.getTable_1().setModel(grupo2);
					for(int i=0;i<fila;i++) { //añade los vertices a sus grupos correspondientes
						Object[] filas = new Object[1];
						filas[0] = v[i].nombre;
						if(v[i].padre == -2) {
							grupo2.addRow(filas);
						}else {
							grupo1.addRow(filas);
						}
					}
				}else {
					JOptionPane.showMessageDialog(frmClusteringHumanoV, "Debe haber al menos 2 personas en la tabla", "Clustering humano", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnBuscarSimilaridad.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnBuscarSimilaridad.setBounds(513, 393, 158, 57);
		frmClusteringHumanoV.getContentPane().add(btnBuscarSimilaridad);
		
		btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmClusteringHumanoV = new JFrame("Salir");
				if(JOptionPane.showConfirmDialog(frmClusteringHumanoV, "¿Desea salir del programa?", "Clustering humano", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
					System.exit(0);
				}
			}
		});
		btnSalir.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnSalir.setBounds(522, 330, 141, 41);
		frmClusteringHumanoV.getContentPane().add(btnSalir);
		
		spinnerDeportes = new JSpinner();
		spinnerDeportes.setModel(new SpinnerNumberModel(1, 1, 5, 1));
		spinnerDeportes.setBounds(624, 52, 47, 20);
		frmClusteringHumanoV.getContentPane().add(spinnerDeportes);
		
		JLabel lblPersona = new JLabel("Persona:");
		lblPersona.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPersona.setBounds(514, 11, 74, 25);
		frmClusteringHumanoV.getContentPane().add(lblPersona);
		
		JLabel lblDeportes = new JLabel("Deportes:");
		lblDeportes.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDeportes.setBounds(514, 48, 74, 25);
		frmClusteringHumanoV.getContentPane().add(lblDeportes);
		
		JLabel lblMusica = new JLabel("Musica:");
		lblMusica.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMusica.setBounds(514, 88, 74, 25);
		frmClusteringHumanoV.getContentPane().add(lblMusica);
		
		JLabel lblEspectaculos = new JLabel("Espectaculos:");
		lblEspectaculos.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEspectaculos.setBounds(512, 124, 88, 25);
		frmClusteringHumanoV.getContentPane().add(lblEspectaculos);
		
		JLabel lblCiencia = new JLabel("Ciencia:");
		lblCiencia.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCiencia.setBounds(512, 160, 74, 25);
		frmClusteringHumanoV.getContentPane().add(lblCiencia);
		
		spinnerMusica = new JSpinner();
		spinnerMusica.setModel(new SpinnerNumberModel(1, 1, 5, 1));
		spinnerMusica.setBounds(624, 92, 47, 20);
		frmClusteringHumanoV.getContentPane().add(spinnerMusica);
		
		spinnerEspectaculos = new JSpinner();
		spinnerEspectaculos.setModel(new SpinnerNumberModel(1, 1, 5, 1));
		spinnerEspectaculos.setBounds(624, 128, 47, 20);
		frmClusteringHumanoV.getContentPane().add(spinnerEspectaculos);
		
		spinnerCiencia = new JSpinner();
		spinnerCiencia.setModel(new SpinnerNumberModel(1, 1, 5, 1));
		spinnerCiencia.setBounds(624, 164, 47, 20);
		frmClusteringHumanoV.getContentPane().add(spinnerCiencia);
		
		textFieldPersona = new JTextField();
		textFieldPersona.setBounds(598, 13, 86, 20);
		frmClusteringHumanoV.getContentPane().add(textFieldPersona);
		textFieldPersona.setColumns(10);
		((DefaultEditor) spinnerDeportes.getEditor()).getTextField().setEditable(false);
		((DefaultEditor) spinnerMusica.getEditor()).getTextField().setEditable(false);
		((DefaultEditor) spinnerEspectaculos.getEditor()).getTextField().setEditable(false);
		((DefaultEditor) spinnerCiencia.getEditor()).getTextField().setEditable(false);
	}
}
