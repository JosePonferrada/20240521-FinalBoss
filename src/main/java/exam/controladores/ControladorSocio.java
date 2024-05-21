package exam.controladores;

import javax.persistence.EntityManager;

import exam.entities.Socio;

public class ControladorSocio extends SuperControladorJPA {

private static ControladorSocio instance = null;
	
	
	public ControladorSocio() {
		super("socio", Socio.class);
	}
	
	
	/**
	 * Singleton
	 * @return
	 */
	public static ControladorSocio getInstance() {
		if (instance == null) {
			instance = new ControladorSocio();
		}
		return instance;
	}
	
	
	public void deleteSocio(int id) {
		EntityManager em = getEntityManager();
		
		Socio tc = (Socio) findById(id);
		
		em.getTransaction().begin();
		// Volvemos a enlazar nuestra entidad con nuestro manager.
		tc = em.merge(tc);
		em.remove(tc);
		em.getTransaction().commit();
	}
	
	
}
