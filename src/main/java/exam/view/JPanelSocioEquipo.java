package exam.view;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import exam.controladores.ControladorEquipo;
import exam.controladores.ControladorSocio;
import exam.entities.Equipo;
import exam.entities.Socio;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JPanelSocioEquipo extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private Socio selectedSocio = null;

	private List<Socio> socios = 
			(List<Socio>) ControladorSocio.getInstance().findAll();
	
	private DefaultTableModel dtm;
	
	private JComboBox jcbEquipo;
	private JRadioButton rdbtnOrdenarPorNombre;
	private JRadioButton rdbtnOrdenarPorPrimer;
	private JRadioButton rdbtnOrdenarPorSegundo;
	private JRadioButton rdbtnOrdenarPorFecha;
	
	
	
	/**
	 * Create the panel.
	 */
	public JPanelSocioEquipo() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 269, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblSociosDeEquipo = new JLabel("Socios de Equipo");
		GridBagConstraints gbc_lblSociosDeEquipo = new GridBagConstraints();
		gbc_lblSociosDeEquipo.gridwidth = 3;
		gbc_lblSociosDeEquipo.insets = new Insets(0, 0, 5, 5);
		gbc_lblSociosDeEquipo.gridx = 1;
		gbc_lblSociosDeEquipo.gridy = 0;
		add(lblSociosDeEquipo, gbc_lblSociosDeEquipo);
		
		JLabel lblEquipo = new JLabel("Equipo:");
		GridBagConstraints gbc_lblEquipo = new GridBagConstraints();
		gbc_lblEquipo.insets = new Insets(0, 0, 5, 5);
		gbc_lblEquipo.gridx = 1;
		gbc_lblEquipo.gridy = 1;
		add(lblEquipo, gbc_lblEquipo);
		
		jcbEquipo = new JComboBox();
		jcbEquipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// If we change the team we reload the table info
				loadSociosFiltered();
			}
		});
		GridBagConstraints gbc_jcbEquipo = new GridBagConstraints();
		gbc_jcbEquipo.gridwidth = 2;
		gbc_jcbEquipo.insets = new Insets(0, 0, 5, 5);
		gbc_jcbEquipo.fill = GridBagConstraints.HORIZONTAL;
		gbc_jcbEquipo.gridx = 2;
		gbc_jcbEquipo.gridy = 1;
		add(jcbEquipo, gbc_jcbEquipo);
		
		rdbtnOrdenarPorNombre = new JRadioButton("Ordenar por nombre");
		GridBagConstraints gbc_rdbtnOrdenarPorNombre = new GridBagConstraints();
		gbc_rdbtnOrdenarPorNombre.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnOrdenarPorNombre.anchor = GridBagConstraints.WEST;
		gbc_rdbtnOrdenarPorNombre.gridx = 2;
		gbc_rdbtnOrdenarPorNombre.gridy = 2;
		add(rdbtnOrdenarPorNombre, gbc_rdbtnOrdenarPorNombre);
		
		rdbtnOrdenarPorPrimer = new JRadioButton("Ordenar por primer apellido");
		GridBagConstraints gbc_rdbtnOrdenarPorPrimer = new GridBagConstraints();
		gbc_rdbtnOrdenarPorPrimer.anchor = GridBagConstraints.WEST;
		gbc_rdbtnOrdenarPorPrimer.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnOrdenarPorPrimer.gridx = 3;
		gbc_rdbtnOrdenarPorPrimer.gridy = 2;
		add(rdbtnOrdenarPorPrimer, gbc_rdbtnOrdenarPorPrimer);
		
		rdbtnOrdenarPorSegundo = new JRadioButton("Ordenar por segundo apellido");
		GridBagConstraints gbc_rdbtnOrdenarPorSegundo = new GridBagConstraints();
		gbc_rdbtnOrdenarPorSegundo.anchor = GridBagConstraints.WEST;
		gbc_rdbtnOrdenarPorSegundo.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnOrdenarPorSegundo.gridx = 2;
		gbc_rdbtnOrdenarPorSegundo.gridy = 3;
		add(rdbtnOrdenarPorSegundo, gbc_rdbtnOrdenarPorSegundo);
		
		rdbtnOrdenarPorFecha = new JRadioButton("Ordenar por fecha de nacimiento");
		GridBagConstraints gbc_rdbtnOrdenarPorFecha = new GridBagConstraints();
		gbc_rdbtnOrdenarPorFecha.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnOrdenarPorFecha.gridx = 3;
		gbc_rdbtnOrdenarPorFecha.gridy = 3;
		add(rdbtnOrdenarPorFecha, gbc_rdbtnOrdenarPorFecha);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 3;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 4;
		add(scrollPane, gbc_scrollPane);
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(rdbtnOrdenarPorNombre);
		bg.add(rdbtnOrdenarPorPrimer);
		bg.add(rdbtnOrdenarPorSegundo);
		bg.add(rdbtnOrdenarPorFecha);
		
		// Selected by default
		rdbtnOrdenarPorNombre.setSelected(true);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		this.dtm = getDefaultTableModel();
		// Creo la tabla con el DefaultTableModel del método más abajo
		this.table = new JTable(dtm);
		
		// Limitamos el modo selección de filas a una única selección.
		this.table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		// Agregamos la tabla al ScrollPane.
		scrollPane.setViewportView(table);
		
		// Demostración de como acceder al clic del ratón sobre la tabla y sobrescribir un valor en la misma
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					selectedSocio = getSocioFromTable();
					if(selectedSocio != null) {
						JOptionPane.showMessageDialog(null, "Has seleccionado a " + 
							selectedSocio.getNombre() + " con id: " + selectedSocio.getId());
					}
				}
			}
		});
		
		
		loadAllEquipos();
		
		loadSociosFiltered();
		
	}

	private DefaultTableModel getDefaultTableModel () {
		DefaultTableModel dtm = new DefaultTableModel(DatosDeTabla.getDatosDeTabla(socios),
				DatosDeTabla.getTitulosColumnas()) {
			
			/**
			 * La sobreescritura de este método nos permite controlar qué celdas queremos que sean editables
			 */
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		return dtm;
	}
	
	private Socio getSocioFromTable() {
		int indexRow = this.table.getSelectedRow();
		if (indexRow != -1) {
			Integer idTc = (Integer) selectedSocio.getId();
			return (Socio) ControladorSocio
					.getInstance().findById(idTc);
		}
		return null;
	}
	
	private void loadAllEquipos() {
		
		List<Equipo> equipos = (List<Equipo>) ControladorEquipo.getInstance().findAll();
		
		for (Equipo equipo : equipos) {
			this.jcbEquipo.addItem(equipo);
		}
		
	}
	
	private void loadSociosFiltered() {
		StringBuffer sb = new StringBuffer();
		
		
		if (rdbtnOrdenarPorNombre.isSelected()) {
			sb.append("nombre");
		} else if (rdbtnOrdenarPorPrimer.isSelected()) {
			sb.append("apellido1");
		} else if (rdbtnOrdenarPorSegundo.isSelected()) {
			sb.append("apellido2");
		} else if (rdbtnOrdenarPorFecha.isSelected()) {
			sb.append("fechaNacimiento");
		}
		
		
		List<Socio> filteredSocios = (List<Socio>) ControladorSocio.getInstance().
				findByLikeOperator2("idEquipo", 
						((Equipo)(this.jcbEquipo.getSelectedItem())).getId(),
						sb.toString());
		
//		filteredSocios = (List<Socio>) ControladorSocio.getInstance().orderByOperator("socio", sb.toString());
		
		// Actualizamos los datos de la tabla. De esta manera, mantenemos
				// el mouseListener de la tabla.
				// Importante mirar el método getDatosDeTabla y modificarlo con la lista.
				this.dtm.setDataVector(DatosDeTabla.getDatosDeTabla(filteredSocios),
						DatosDeTabla.getTitulosColumnas());
				// Se notifican los posibles cambios de las celdas de la tabla.
				this.dtm.fireTableDataChanged();
		
	}
	
	
	
}
