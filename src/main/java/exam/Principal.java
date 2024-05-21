package exam;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import exam.utils.Apariencia;


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
		super("Gestión de contratos - Bankonter");
		
		this.setBounds(100, 100, 800, 680);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		
		// Añadimos el panel
		JTabbedPane jtp = new JTabbedPane();

		
		jtp.add(jtp);
		
//		this.getContentPane().add(panelContrato);
	}

	/**
	 * Método Principal.
	 * @param args
	 */
	public static void main(String[] args) {
		getInstance().setVisible(true);
	}
}