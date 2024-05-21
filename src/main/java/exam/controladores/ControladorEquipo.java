package exam.controladores;

import javax.persistence.EntityManager;

import exam.entities.Equipo;

public class ControladorEquipo extends SuperControladorJPA {

private static ControladorEquipo instance = null;
	
	
	public ControladorEquipo() {
		super("equipo", Equipo.class);
	}
	
	
	/**
	 * Singleton
	 * @return
	 */
	public static ControladorEquipo getInstance() {
		if (instance == null) {
			instance = new ControladorEquipo();
		}
		return instance;
	}
	
	
	public void deleteEquipo(int id) {
		EntityManager em = getEntityManager();
		
		Equipo tc = (Equipo) findById(id);
		
		em.getTransaction().begin();
		// Volvemos a enlazar nuestra entidad con nuestro manager.
		tc = em.merge(tc);
		em.remove(tc);
		em.getTransaction().commit();
	}
	
	
}
