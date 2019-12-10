package Paquete;

import java.time.LocalTime;
import java.util.Date;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.Transaction;


import Paquete.Utilidades;
import Paquete.Seguro;
import Paquete.Seguro.TipoSeguro;

public class Prueba
{
	
	public static void main(String[] args) 
	{
		
		
		Seguro seg = new Seguro();
		seg.setNif("444444");
		seg.setNombre("Infierno");
		seg.setApe1("De");
		seg.setApe2("Dante");
		seg.setEdad(33);
		seg.setSexo(1);
		seg.setCasado("N");
		seg.setNumHijos(2);
		seg.setFechaCreacion(new Date());
		seg.setTipoSeguro(TipoSeguro.COCHE);
		seg.setMayorEdad(seg.MayoriaEdad(seg.getEdad()));
		
		seg.setFechaNacimiento(new Date());
		seg.setHoraContacto(LocalTime.now());
		
		seg.setCodigo(new char[] {'a','e'});
		seg.setComentarios("Cobrar y no pagar es nuestro lema");
		
		GuardaSeguro(seg);
		
		
		Seguro seguroRecuperado = recuperaSeguro(seg.getIdSeguro());
		seguroRecuperado.setNombre("Mator dedad Actualizada.");
		actualizaSeguro(seguroRecuperado);
	
		Seguro s1 = new Seguro();
		s1.setNif("66688666");
		borraSeguro(s1);
		
		Utilidades.getSessionFactory().close();
	}
		
	public static void borraSeguro(Seguro s) 
	{
		Session session = Utilidades.getSessionFactory().openSession();
		Transaction tx = null;
		try 
		{

			tx = session.beginTransaction();
			session.delete(s);
			session.getTransaction().commit();

		} 
		catch (RuntimeException e) 
		{
			if (tx != null)
				tx.rollback();
			System.out.println("Error");
		} 
		finally
		{
			
			session.close();
		}

	}

	public static void actualizaSeguro(Seguro s) 
	{
		// Conseguimos un objeto sesión para comunicarnos con la BD
		Session session = Utilidades.getSessionFactory().openSession();
		Transaction tx = null;
		try 
		{

			// abrimos una transacción
			tx = session.beginTransaction();
			// Guardamos el objeto en la sesión
			session.update(s);
			// Commit de la transacción
			session.getTransaction().commit();

		} 
		catch (RuntimeException e) 
		{
			if (tx != null)
				tx.rollback();
			System.out.println("Error al actualizar");
		}
		finally
		{
			session.close();
		}

	}

	public static void GuardaSeguro(Seguro s) {
		// Conseguimos un objeto sesión para comunicarnos con la BD
		Session session = Utilidades.getSessionFactory().openSession();
		Transaction tx = null;
		try {

			// abrimos una transacción
			tx = session.beginTransaction();
			// Guardamos el objeto en la sesión
			session.save(s);
			// Commit de la transacción
			session.getTransaction().commit();

		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			System.out.println("Error al guardar el seguro");
		} finally {
			session.close();
		}

	}

	public static Seguro recuperaSeguro(int id) {
		// Conseguimos un objeto sesión para comunicarnos con la BD
		Session session = Utilidades.getSessionFactory().openSession();
		Seguro s = new Seguro();

		// abrimos una transacción
		session.beginTransaction();
		// Recuperamos el Seguro
		try
		{
			s = (Seguro) session.load(Seguro.class, id);
			System.out.println("Los datos son:"
					+ s.toString());
			// Commit de la transacción
			session.getTransaction().commit();
		}catch (ObjectNotFoundException e) {
				System.out.println("El seguro no existe");
		} finally {
			session.close();
		}
		
		
		return s;
	}

}
