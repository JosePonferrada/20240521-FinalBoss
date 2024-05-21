package exam.view;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JToolBar;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import exam.controladores.ControladorEquipo;
import exam.controladores.ControladorSocio;
import exam.entities.Equipo;
import exam.entities.Socio;

import java.awt.Color;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Insets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JSlider;
import javax.swing.JFormattedTextField;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JPanelGestionSocios extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField jtfNombre;
	private JTextField jtfPrimerApellido;
	private JTextField jtfSegundoApellido;
	private JLabel lblAniosAntiguedad;
	private JSlider sliderAntiguedad;
	private JComboBox jcbEquipo;
	private JFormattedTextField jftfFechaNacimiento;
	private JCheckBox chckbxSocioEnActivo;
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	//Saving flag
	private int flag = 0;
	
	private Socio current = null;
	
	/**
	 * Create the panel.
	 */
	public JPanelGestionSocios() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{112, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JToolBar toolBar = new JToolBar();
		GridBagConstraints gbc_toolBar = new GridBagConstraints();
		gbc_toolBar.gridwidth = 3;
		gbc_toolBar.insets = new Insets(0, 0, 5, 0);
		gbc_toolBar.fill = GridBagConstraints.HORIZONTAL;
		gbc_toolBar.gridx = 0;
		gbc_toolBar.gridy = 0;
		add(toolBar, gbc_toolBar);
		
		JButton btnFirst = new JButton("");
		btnFirst.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					showFirstSocio();
				} catch (Exception e2) {
					
				}
			}
		});
		btnFirst.setIcon(new ImageIcon(JPanelGestionSocios.class.getResource("/exam/res/gotostart.png")));
		toolBar.add(btnFirst);
		
		JButton btnPrevious = new JButton("");
		btnPrevious.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					showPreviousSocio();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		btnPrevious.setIcon(new ImageIcon(JPanelGestionSocios.class.getResource("/exam/res/previous.png")));
		toolBar.add(btnPrevious);
		
		JButton btnNext = new JButton("");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					showNextSocio();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		btnNext.setIcon(new ImageIcon(JPanelGestionSocios.class.getResource("/exam/res/next.png")));
		toolBar.add(btnNext);
		
		JButton btnLast = new JButton("");
		btnLast.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					showLastSocio();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		btnLast.setIcon(new ImageIcon(JPanelGestionSocios.class.getResource("/exam/res/gotoend.png")));
		toolBar.add(btnLast);
		
		JButton btnNew = new JButton("");
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newSocio();
			}
		});
		btnNew.setIcon(new ImageIcon(JPanelGestionSocios.class.getResource("/exam/res/nuevo.png")));
		toolBar.add(btnNew);
		
		JButton btnSave = new JButton("");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					saveSocio();
				} catch (ParseException e1) {
					JOptionPane.showMessageDialog(null, "No se ha guardado");
				}
			}
		});
		btnSave.setIcon(new ImageIcon(JPanelGestionSocios.class.getResource("/exam/res/guardar.png")));
		toolBar.add(btnSave);
		
		JButton btnDelete = new JButton("");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteSocio();
			}
		});
		btnDelete.setIcon(new ImageIcon(JPanelGestionSocios.class.getResource("/exam/res/eliminar.png")));
		toolBar.add(btnDelete);
		
		JLabel lblGestinDeSocios = new JLabel("Gestión de Socios");
		GridBagConstraints gbc_lblGestinDeSocios = new GridBagConstraints();
		gbc_lblGestinDeSocios.insets = new Insets(0, 0, 5, 0);
		gbc_lblGestinDeSocios.gridwidth = 3;
		gbc_lblGestinDeSocios.gridx = 0;
		gbc_lblGestinDeSocios.gridy = 1;
		add(lblGestinDeSocios, gbc_lblGestinDeSocios);
		
		JLabel lblNombre = new JLabel("Nombre:");
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.anchor = GridBagConstraints.EAST;
		gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombre.gridx = 0;
		gbc_lblNombre.gridy = 2;
		add(lblNombre, gbc_lblNombre);
		
		jtfNombre = new JTextField();
		GridBagConstraints gbc_jtfNombre = new GridBagConstraints();
		gbc_jtfNombre.insets = new Insets(0, 0, 5, 0);
		gbc_jtfNombre.gridwidth = 2;
		gbc_jtfNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_jtfNombre.gridx = 1;
		gbc_jtfNombre.gridy = 2;
		add(jtfNombre, gbc_jtfNombre);
		jtfNombre.setColumns(10);
		
		JLabel lblPrimerApellido = new JLabel("Primer Apellido:");
		GridBagConstraints gbc_lblPrimerApellido = new GridBagConstraints();
		gbc_lblPrimerApellido.anchor = GridBagConstraints.EAST;
		gbc_lblPrimerApellido.insets = new Insets(0, 0, 5, 5);
		gbc_lblPrimerApellido.gridx = 0;
		gbc_lblPrimerApellido.gridy = 3;
		add(lblPrimerApellido, gbc_lblPrimerApellido);
		
		jtfPrimerApellido = new JTextField();
		GridBagConstraints gbc_jtfPrimerApellido = new GridBagConstraints();
		gbc_jtfPrimerApellido.gridwidth = 2;
		gbc_jtfPrimerApellido.insets = new Insets(0, 0, 5, 5);
		gbc_jtfPrimerApellido.fill = GridBagConstraints.HORIZONTAL;
		gbc_jtfPrimerApellido.gridx = 1;
		gbc_jtfPrimerApellido.gridy = 3;
		add(jtfPrimerApellido, gbc_jtfPrimerApellido);
		jtfPrimerApellido.setColumns(10);
		
		JLabel lblSegundoApellido = new JLabel("Segundo Apellido:");
		GridBagConstraints gbc_lblSegundoApellido = new GridBagConstraints();
		gbc_lblSegundoApellido.anchor = GridBagConstraints.EAST;
		gbc_lblSegundoApellido.insets = new Insets(0, 0, 5, 5);
		gbc_lblSegundoApellido.gridx = 0;
		gbc_lblSegundoApellido.gridy = 4;
		add(lblSegundoApellido, gbc_lblSegundoApellido);
		
		jtfSegundoApellido = new JTextField();
		GridBagConstraints gbc_jtfSegundoApellido = new GridBagConstraints();
		gbc_jtfSegundoApellido.gridwidth = 2;
		gbc_jtfSegundoApellido.insets = new Insets(0, 0, 5, 5);
		gbc_jtfSegundoApellido.fill = GridBagConstraints.HORIZONTAL;
		gbc_jtfSegundoApellido.gridx = 1;
		gbc_jtfSegundoApellido.gridy = 4;
		add(jtfSegundoApellido, gbc_jtfSegundoApellido);
		jtfSegundoApellido.setColumns(10);
		
		JLabel lblFechaDeNacimiento = new JLabel("Fecha de nacimiento:");
		GridBagConstraints gbc_lblFechaDeNacimiento = new GridBagConstraints();
		gbc_lblFechaDeNacimiento.anchor = GridBagConstraints.EAST;
		gbc_lblFechaDeNacimiento.insets = new Insets(0, 0, 5, 5);
		gbc_lblFechaDeNacimiento.gridx = 0;
		gbc_lblFechaDeNacimiento.gridy = 5;
		add(lblFechaDeNacimiento, gbc_lblFechaDeNacimiento);
		
		jftfFechaNacimiento = new JFormattedTextField(
				new JFormattedTextField.AbstractFormatter() {
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

					@Override
					public String valueToString(Object value) throws ParseException {
						if (value != null && value instanceof Date) {
							return sdf.format(((Date) value));
						}
						return "";
					}

					@Override
					public Object stringToValue(String text) throws ParseException {
						try {
							jftfFechaNacimiento.setBackground(null);
//							JOptionPane.showMessageDialog(null, "Fecha correcta");
							
							
							return sdf.parse(text);							
						} catch (Exception e) {
//							JOptionPane.showMessageDialog(null, "Error en la fecha");
							jftfFechaNacimiento.setBackground(Color.RED);
							
							JOptionPane.showMessageDialog(null, "Error en la fecha. Fórmato válido: dd/MM/yyyy");
							
							return null;
						}
					}
				});
		GridBagConstraints gbc_jftfFechaNacimiento = new GridBagConstraints();
		gbc_jftfFechaNacimiento.gridwidth = 2;
		gbc_jftfFechaNacimiento.insets = new Insets(0, 0, 5, 5);
		gbc_jftfFechaNacimiento.fill = GridBagConstraints.HORIZONTAL;
		gbc_jftfFechaNacimiento.gridx = 1;
		gbc_jftfFechaNacimiento.gridy = 5;
		add(jftfFechaNacimiento, gbc_jftfFechaNacimiento);
		
		JLabel lblAntigedadaos = new JLabel("Antigüedad (años):");
		GridBagConstraints gbc_lblAntigedadaos = new GridBagConstraints();
		gbc_lblAntigedadaos.anchor = GridBagConstraints.EAST;
		gbc_lblAntigedadaos.insets = new Insets(0, 0, 5, 5);
		gbc_lblAntigedadaos.gridx = 0;
		gbc_lblAntigedadaos.gridy = 6;
		add(lblAntigedadaos, gbc_lblAntigedadaos);
		
		sliderAntiguedad = new JSlider(0, 200, 1);
		sliderAntiguedad.setValue(0);
		sliderAntiguedad.setMaximum((int) 200);
		sliderAntiguedad.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				lblAniosAntiguedad.setText(sliderAntiguedad.getValue() + " años");
				
			}
		});
		GridBagConstraints gbc_sliderAntiguedad = new GridBagConstraints();
		gbc_sliderAntiguedad.fill = GridBagConstraints.HORIZONTAL;
		gbc_sliderAntiguedad.insets = new Insets(0, 0, 5, 5);
		gbc_sliderAntiguedad.gridx = 1;
		gbc_sliderAntiguedad.gridy = 6;
		add(sliderAntiguedad, gbc_sliderAntiguedad);
		
		lblAniosAntiguedad = new JLabel("XXX años");
		GridBagConstraints gbc_lblAniosAntiguedad = new GridBagConstraints();
		gbc_lblAniosAntiguedad.insets = new Insets(0, 0, 5, 0);
		gbc_lblAniosAntiguedad.gridx = 2;
		gbc_lblAniosAntiguedad.gridy = 6;
		add(lblAniosAntiguedad, gbc_lblAniosAntiguedad);
		
		chckbxSocioEnActivo = new JCheckBox("Socio en activo");
		GridBagConstraints gbc_chckbxSocioEnActivo = new GridBagConstraints();
		gbc_chckbxSocioEnActivo.anchor = GridBagConstraints.WEST;
		gbc_chckbxSocioEnActivo.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxSocioEnActivo.gridx = 1;
		gbc_chckbxSocioEnActivo.gridy = 7;
		add(chckbxSocioEnActivo, gbc_chckbxSocioEnActivo);
		
		JLabel lblEquipo = new JLabel("Equipo:");
		GridBagConstraints gbc_lblEquipo = new GridBagConstraints();
		gbc_lblEquipo.anchor = GridBagConstraints.EAST;
		gbc_lblEquipo.insets = new Insets(0, 0, 0, 5);
		gbc_lblEquipo.gridx = 0;
		gbc_lblEquipo.gridy = 8;
		add(lblEquipo, gbc_lblEquipo);
		
		jcbEquipo = new JComboBox();
		GridBagConstraints gbc_jcbEquipo = new GridBagConstraints();
		gbc_jcbEquipo.gridwidth = 2;
		gbc_jcbEquipo.fill = GridBagConstraints.HORIZONTAL;
		gbc_jcbEquipo.gridx = 1;
		gbc_jcbEquipo.gridy = 8;
		add(jcbEquipo, gbc_jcbEquipo);

		loadAllEquipos();
		
		showFirstSocio();
		
	}
	
	public void showFirstSocio() {
		current = (Socio) ControladorSocio.getInstance().findFirst();
		
		if (current != null) showSocioData(current);
	}
	
	public void showNextSocio() {
		current = (Socio) ControladorSocio.getInstance().findNext(current.getId());
		
		if (current != null) showSocioData(current);
	}
	
	public void showPreviousSocio() {
		current = (Socio) ControladorSocio.getInstance().findPrevious(current.getId());
		
		if (current != null) showSocioData(current);
	}
	
	public void showLastSocio() {
		current = (Socio) ControladorSocio.getInstance().findLast();
		
		if (current != null) showSocioData(current);
	}
	
	public void showSocioData(Socio c) {
		if (c != null) {
			this.jtfNombre.setText(c.getNombre());
			this.jtfPrimerApellido.setText(c.getApellido1());
			this.jtfSegundoApellido.setText(c.getApellido2());
			//Here goes JFormattedTextField
			this.jftfFechaNacimiento.setValue(c.getFechaNacimiento());
			
			for (int i = 0; i < this.jcbEquipo.getItemCount(); i++) {
				if (this.jcbEquipo.getItemAt(i).equals(c.getEquipo())) {
					this.jcbEquipo.setSelectedIndex(i);
				}
			}
			
			this.sliderAntiguedad.setValue((int) c.getAntiguedadAnios());
			this.chckbxSocioEnActivo.setSelected(c.getActivo());
			
			this.jcbEquipo.setSelectedItem(c.getId());
			
		}
	}
	
	private void loadAllEquipos() {
		
		List<Equipo> equipos = (List<Equipo>) ControladorEquipo.getInstance().findAll();
		
		for (Equipo equipo : equipos) {
			this.jcbEquipo.addItem(equipo);
		}
		
	}

	private void newSocio() {
		this.jtfNombre.setText("");
		this.jtfPrimerApellido.setText("");
		this.jtfSegundoApellido.setText("");
		this.jftfFechaNacimiento.setText("");
		this.sliderAntiguedad.setValue(0);
		this.chckbxSocioEnActivo.setSelected(false);
		this.jcbEquipo.setSelectedIndex(1);
		
	}
	
	public void deleteSocio() {
		String respuestas[] = new String[] { "Sí", "No" };
		int opcionElegida = JOptionPane.showOptionDialog(null,
				"¿Realmente desea eliminar el registro actual?",
				"Eliminación de Registro", JOptionPane.DEFAULT_OPTION,
				JOptionPane.WARNING_MESSAGE, null,
				respuestas, respuestas[1]);
		
		// Puntero para seleccionar el posible siguiente o anterior
		// registro a mostrar.
		Socio actual = null;

		if (opcionElegida == 0) {	// Si la opción es 0 (= Si).

				int idActual = current.getId();
				// Eliminamos el registro.
				ControladorSocio.getInstance().deleteSocio(idActual);
				
				JOptionPane.showMessageDialog(null, "Contrato borrado correctamente");
				
				// A continuación, mostraremos en pantalla el registro
				// siguiente.
				actual = (Socio) ControladorSocio
						.getInstance().findNext(idActual);
				
				// Si hay registro, es decir, el registro borrado es
				// ocupado por su siguiente registro (id).
				if (actual != null) {
					showSocioData(actual);
				} else {
					// Si hay no registro, miramos si hay registro anterior
					// al registro borrado.
					actual = (Socio) ControladorSocio
							.getInstance().findPrevious(idActual);
					if (actual != null) {
						
						// Sobreescribimos el puntero de current.
						this.current = actual;
						
						showSocioData(current);
					} else {
						// Llegados a este punto, no hay registros previos
						// ni posteriores.
						newSocio();
					}
					
				}
			}
	}
	
	public void saveSocio() throws ParseException {
		
		if (this.jtfNombre.getText().isBlank()) {
			JOptionPane.showMessageDialog(null, "Introduzca un nombre");
			return;
		}
		
		if (this.jtfPrimerApellido.getText().isBlank()) {
			JOptionPane.showMessageDialog(null, "Introduzca un primer apellido");
			return;
		}
		
		if (this.jtfSegundoApellido.getText().isBlank()) {
			JOptionPane.showMessageDialog(null, "Introduzca un segundo apellido");
			return;
		}
		
		Socio c = new Socio();
		
		c.setNombre(this.jtfNombre.getText());
		
		if (this.jftfFechaNacimiento.getText().trim().equals(""))
			c.setFechaNacimiento(null);
		else {
			c.setFechaNacimiento(sdf.parse(this.jftfFechaNacimiento.getText()));
		}
		c.setActivo(this.chckbxSocioEnActivo.isSelected());
		
		c.setAntiguedadAnios(this.sliderAntiguedad.getValue());
		
		// Here goes Equipo
		
		c.setEquipo(this.current.getEquipo());
		
		
		
		if (this.flag == -1) {
			ControladorSocio.getInstance().insertEntidad(c);
			JOptionPane.showMessageDialog(null, "Se ha realizado una inserción");
		} else {
			c.setId(this.current.getId());
			ControladorSocio.getInstance().update2(c);
			JOptionPane.showMessageDialog(null, "Contrato modificado");
					
		}
		
	}
	
}
