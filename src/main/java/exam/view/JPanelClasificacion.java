package exam.view;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JScrollPane;


import exam.controladores.ControladorEquipo;
import exam.entities.Equipo;

import java.awt.Insets;
import java.util.List;

import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JPanelClasificacion extends JPanel {
	
	// Modelo del elemento JList, necesario para que podamos c�modamente agregar y eliminar elementos
		private DefaultListModel<Equipo> listModelEquipos;
		// Lista de todos los usuarios de la BBDD, para incluir en el elemento JList
		private List<Equipo> equipos = 
				(List<Equipo>) ControladorEquipo.getInstance().findAll();
		
		private JList<Equipo> listaEquipos;
		private JScrollPane scrollPane;

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public JPanelClasificacion() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblClasificacin = new JLabel("Clasificación");
		GridBagConstraints gbc_lblClasificacin = new GridBagConstraints();
		gbc_lblClasificacin.insets = new Insets(0, 0, 5, 5);
		gbc_lblClasificacin.gridx = 0;
		gbc_lblClasificacin.gridy = 0;
		add(lblClasificacin, gbc_lblClasificacin);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetList();
			}
		});
		GridBagConstraints gbc_btnReset = new GridBagConstraints();
		gbc_btnReset.insets = new Insets(0, 0, 5, 0);
		gbc_btnReset.gridx = 1;
		gbc_btnReset.gridy = 2;
		add(btnReset, gbc_btnReset);
		
		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 4;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 2;
		add(scrollPane, gbc_scrollPane);
		
		JList list = new JList();
		scrollPane.setViewportView(list);
		
		JButton btnArriba = new JButton("Arriba");
		btnArriba.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					subePos();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		GridBagConstraints gbc_btnArriba = new GridBagConstraints();
		gbc_btnArriba.insets = new Insets(0, 0, 5, 0);
		gbc_btnArriba.gridx = 1;
		gbc_btnArriba.gridy = 3;
		add(btnArriba, gbc_btnArriba);
		
		JButton btnAbajo = new JButton("Abajo");
		btnAbajo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					bajaPos();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		GridBagConstraints gbc_btnAbajo = new GridBagConstraints();
		gbc_btnAbajo.insets = new Insets(0, 0, 5, 0);
		gbc_btnAbajo.gridx = 1;
		gbc_btnAbajo.gridy = 4;
		add(btnAbajo, gbc_btnAbajo);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					removeFromList();
				} catch (Exception e2) {
						// TODO: handle exception
				}
			}
		});
		GridBagConstraints gbc_btnEliminar = new GridBagConstraints();
		gbc_btnEliminar.insets = new Insets(0, 0, 5, 0);
		gbc_btnEliminar.gridx = 1;
		gbc_btnEliminar.gridy = 5;
		add(btnEliminar, gbc_btnEliminar);
		
		this.listModelEquipos = getDefaultListModel();
		this.listaEquipos = new JList<Equipo>(listModelEquipos);
		this.scrollPane.setViewportView(listaEquipos);
		
		loadAllUsuariosInJList(equipos);

	}
	
	private DefaultListModel getDefaultListModel () {
		this.listModelEquipos = new DefaultListModel<Equipo>();
		return this.listModelEquipos;
	}

	private void loadAllUsuariosInJList(List<Equipo> listUsuarios) {
		this.listModelEquipos.clear();
		
		for (Equipo usuario : listUsuarios) {
			this.listModelEquipos.addElement(usuario);
		}
		
	}
	
	public void resetList() {
		this.listModelEquipos.clear();
		
		loadAllUsuariosInJList(equipos);
	}
	
	public void subePos() {
		int index = this.listaEquipos.getSelectedIndex();
		
		Equipo previous = this.listModelEquipos.getElementAt(index - 1);
		
		listModelEquipos.setElementAt(listaEquipos.getSelectedValue(), index - 1);
		listModelEquipos.setElementAt(previous, index);
		
		// To keep selected the one we moving
		
		listaEquipos.setSelectedIndex(index - 1);
	}
	
	public void bajaPos() {
		int index = this.listaEquipos.getSelectedIndex();
		
		Equipo previous = this.listModelEquipos.getElementAt(index + 1);
		
		listModelEquipos.setElementAt(listaEquipos.getSelectedValue(), index + 1);
		listModelEquipos.setElementAt(previous, index);
		
		// To keep selected the one we moving
		
		listaEquipos.setSelectedIndex(index + 1);
	}
	
	
	private void removeFromList() {
		
		int index = this.listaEquipos.getSelectedIndex();
		
		listModelEquipos.remove(index);
		
	}
	
}
