package tp2;

import java.awt.Color;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InterfazSecundaria {

	private JFrame frmClusteringHumanoV;
	private JTable table;
	private JTable table_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfazSecundaria window = new InterfazSecundaria();
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
	public InterfazSecundaria() {
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
		frmClusteringHumanoV.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmClusteringHumanoV.setVisible(true);
		frmClusteringHumanoV.setResizable(false);
		frmClusteringHumanoV.setTitle("Clustering humano v1.0");
		frmClusteringHumanoV.setBounds(100, 100, 842, 500);
		frmClusteringHumanoV.setLocationRelativeTo(null);
		frmClusteringHumanoV.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 54, 301, 396);
		frmClusteringHumanoV.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setRowHeight(30);
		scrollPane.setViewportView(table);
		table.setFont(new Font("Tahoma", Font.BOLD, 16));
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setDefaultEditor(Object.class, null);
		@SuppressWarnings("unused")
		DefaultTableModel model;
		table.setModel(model = new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nombre"
			}
		) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			/**
			 * 
			 */
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] {
				String.class
			};
			@SuppressWarnings({ "rawtypes", "unchecked" })
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.setBounds(38, 11, 138, 219);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(334, 54, 301, 396);
		frmClusteringHumanoV.getContentPane().add(scrollPane_1);
		
		table_1 = new JTable();
		@SuppressWarnings("unused")
		DefaultTableModel model2;
		table_1.setModel(model2 = new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nombre"
			}
		) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			/**
			 * 
			 */
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] {
				String.class
			};
			@SuppressWarnings({ "rawtypes", "unchecked" })
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table_1.getColumnModel().getColumn(0).setResizable(false);
		table_1.setRowHeight(30);
		table_1.setDefaultEditor(Object.class, null);
		table_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		table_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane_1.setViewportView(table_1);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmClusteringHumanoV.dispose();
			}
		});
		btnVolver.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnVolver.setBounds(653, 388, 153, 62);
		frmClusteringHumanoV.getContentPane().add(btnVolver);
		
		JLabel lblGrupo1 = new JLabel("Grupo 1");
		lblGrupo1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblGrupo1.setHorizontalAlignment(SwingConstants.CENTER);
		lblGrupo1.setBounds(120, 11, 72, 32);
		frmClusteringHumanoV.getContentPane().add(lblGrupo1);
		
		JLabel lblGrupo2 = new JLabel("Grupo 2");
		lblGrupo2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblGrupo2.setHorizontalAlignment(SwingConstants.CENTER);
		lblGrupo2.setBounds(446, 11, 72, 32);
		frmClusteringHumanoV.getContentPane().add(lblGrupo2);
		
	}

	public JTable getTable() {
		return table;
	}

	public JTable getTable_1() {
		return table_1;
	}
	
}
