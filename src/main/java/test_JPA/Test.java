package test_JPA;

import java.rmi.RemoteException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

public class Test {
	public static void main(String[] args) throws RemoteException {
		EntityManager em;
		em = Persistence.createEntityManagerFactory("jpa-mssql").createEntityManager();
	}
}
