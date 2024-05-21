package exam.view;

import java.util.List;

import exam.entities.Socio;

public class DatosDeTabla {
	
	/** 
	 * 
	 * @return
	 */
	public static String[] getTitulosColumnas() {
		return new String[] {"Nombre", "Primer Apellido", "Segundo Apellido", "Fecha de nacimiento"};
	}

	/**
	 * 
	 * @return
	 */
	public static Object[][] getDatosDeTabla(List<Socio> listSocio) {
		// Obtengo todos los tipoContrato.
		List<Socio> socios = listSocio;
		
		
		// Preparo una estructura para pasar al constructor de la JTable
		Object[][] datos = new Object[socios.size()][4];
		// Cargo los datos de la lista de estudiantes en la matriz de los datos
		for (int i = 0; i < socios.size(); i++) {
			Socio tc = socios.get(i);
			datos[i][0] = tc.getNombre();
			datos[i][1] = tc.getApellido1();
			datos[i][2] = tc.getApellido2();
			datos[i][3] = tc.getFechaNacimiento();
		}
		
		return datos;
	}
	
	
}
