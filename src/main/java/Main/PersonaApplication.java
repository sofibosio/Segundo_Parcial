package Main;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

import entities.Autor;
import entities.Domicilio;
import entities.Libro;
import entities.Localidad;
import entities.Persona;

public class PersonaApplication {
	public static void main(String[] args) {
	
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersonaAppPU");
		EntityManager em = emf.createEntityManager();
		
		try {
			em.getTransaction().begin();
			Persona persona = new Persona();
			persona.setNombre("Sofia");
			persona.setApellido("Guarnieri");
			persona.setDni(43485860);
			
			
			Domicilio domicilio = new Domicilio();
			domicilio.setCalle("Roque Sáenz Peña");
			domicilio.setNumero(1265);

			Localidad localidad = new Localidad();
			localidad.setDenominacion("Godoy cruz");
			domicilio.setLocalidad(localidad);

			Libro libro1 = new Libro();
			libro1.setTitulo("Aprender a programar en 24hs");
			libro1.setGenero("Tecnologia");
			libro1.setFecha(12/12/21);
			libro1.setPaginas(1200);

			Autor autor = new Autor();
			autor.setNombre("Juan");
			autor.setApellido("Lopez");
			
		
		
			autor.setBiografia("Nacido en 1990. Residente de Buenos Aires");

			libro1.getAutores().add(autor);
			persona.getLibros().add(libro1);
			persona.setDomicilio(domicilio);
			
			em.persist(autor);
			em.persist(libro1);
			em.persist(localidad);
			em.persist(domicilio);
			em.persist(persona);
			em.flush();
			
			em.getTransaction().commit();
			em.flush();
			em.getTransaction().commit();
		}catch(Exception e) {
			
			em.getTransaction().rollback();
			JOptionPane.showConfirmDialog(null, e);
						
		}
		em.close();
		emf.close();
	}
}

