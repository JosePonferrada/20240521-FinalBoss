package exam;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import exam.utils.Apariencia;
import exam.view.JPanelClasificacion;
import exam.view.JPanelGestionSocios;
import exam.view.JPanelSocioEquipo;


public class Principal extends JFrame {

	private static final long serialVersionUID = 1L;
	private static Principal instance = null;
	
	static {
		Apariencia.setAparienciasOrdenadas(Apariencia.aparienciasOrdenadas);
	}
	
	/**
	 * Singleton.
	 * @return
	 */
	public static Principal getInstance() {
		if (instance == null) {
			instance = new Principal();
		}
		return instance;
	}
	
	/**
	 * Constructor.
	 */
	public Principal() {
		super("Ventana Principal");
		
		this.setBounds(100, 100, 800, 680);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		
		// Añadimos el panel
		JTabbedPane jtp = new JTabbedPane();

		JPanelGestionSocios jpgs = new JPanelGestionSocios();
		JPanelSocioEquipo jpse = new JPanelSocioEquipo();
		JPanelClasificacion jpc = new JPanelClasificacion();
		
		jtp.add("Datos del socio", jpgs);
		jtp.add("Socios por equipo", jpse);
		jtp.add("Clasificación", jpc);
		
		this.getContentPane().add(jtp);
	}

	/**
	 * Método Principal.
	 * @param args
	 */
	public static void main(String[] args) {
		getInstance().setVisible(true);
	}
}