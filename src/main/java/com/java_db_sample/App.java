package com.java_db_sample;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.java_db_sample.domain.Users;

/**
 * JPA Hello world!
 *
 */
public class App {

	public static void main(String[] args) {

		EntityManagerFactory emf = null;
		EntityManager entityManager = null;
		EntityTransaction transaction = null;

		try {
			emf = Persistence.createEntityManagerFactory("mysql_test");
			entityManager = emf.createEntityManager();
			transaction = entityManager.getTransaction();
			transaction.begin();

			Users user = new Users();
			user.setName("Ender");
			user.setSurname("Erdihan");


			entityManager.persist(user);

			transaction.commit();

			Query q = entityManager.createQuery("select u from Users u");

			List<Users> resultList = q.getResultList();
			System.out.println("num of users:" + resultList.size());
			for (Users next : resultList) {
				System.out.println("next user: " + next);
			}

		} catch (Exception e) {
			System.out.println(e);
			transaction.rollback();
		} finally {
			entityManager.close();
			emf.close();
		}
	}
}
