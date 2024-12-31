package DAO;

import java.sql.Connection;
import java.sql.DriverManager;

public class GenericDAO {

    private Connection c;
    private static final String URLDB = "jdbc:mariadb://localhost:3307/dbClinicaMedica";
	private static final String USUARIO = "root";
	private static final String PASSWORD = "m0r20Q5)";
	
    public Connection getConnection(){
	        try {
	            Class.forName("org.mariadb.jdbc.Driver");
	            c = DriverManager.getConnection(URLDB, USUARIO, PASSWORD);
	        } catch (Exception e){
	            e.printStackTrace();
	        }
	        return c;
	    }	
}
    	//System.out.println(java.lang.System.getProperty("java.library.path"));
        //String hostname = "127.0.0.1";
        //String dbName = "dbClinicaMedica";
        //String user = "saClinic";
        //String senha = "123456";

        //try {
			//Class.forName("net.sourceforge.jtds.jdbc.Driver");
			 // try {
			//	c = DriverManager.getConnection(String.format("jdbc:jtds:sqlserver://localhost:1433;DatabaseName=dbClinicaMedica;namedPipes=true", "%s", "%s", 
			//	           "Higur"));
			//} catch (SQLException e) {
				//System.out.println("Conection lost");
				//e.printStackTrace();
			//}
		//} catch (ClassNotFoundException e) {
			//System.out.println("Class not found");
			//e.printStackTrace();
		//}

