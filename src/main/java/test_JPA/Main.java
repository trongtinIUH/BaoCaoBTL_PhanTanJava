package test_JPA;

import jakarta.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        Persistence.createEntityManagerFactory("jpa-mssql");
        System.out.println("Done");
    }
}
