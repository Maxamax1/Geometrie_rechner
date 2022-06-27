
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;



public class Datenbank {
	//Variables
	private static Connection myCon;
	private static PreparedStatement myPrepStmnt;
	private static ResultSet myRS;
	private static String url;
	private static String user;
	private static String pwd;
	private static String type;
	
	
	
	/**
	 * test for Database Connection and Save the Login data 
	 * @param url
	 * @param user
	 * @param pwd
	 * @return
	 */
	
	public static boolean testDB(String url, String user, String pwd, String type) {
		boolean ret = false;
		
		if (type.equals("MySQL") ) {
		try {
			
			myCon = DriverManager.getConnection(url, user, pwd);
			ret = true;
			Datenbank.url = url;
			Datenbank.user = user;
			Datenbank.pwd = pwd;
			Datenbank.type = type;
			myCon.close();
			myCon = null;
			
		} catch (SQLException e) {
			ret = false;
			e.printStackTrace();
		} finally {
			Datenbank.doFinally();
		}
		
		
		} else if (type.equals("SQLite")) {
			try {
				url = "jdbc:sqlite::resource:Geometrie.db";
				myCon = DriverManager.getConnection(url);
				ret = true;
				Datenbank.url = url;
				Datenbank.type = type;
				
				myCon.close();
				myCon = null;
				
			} catch (SQLException e) {
				ret = false;
				e.printStackTrace();
			} finally {
				Datenbank.doFinally();
			}
		}
		return ret;
	}

	
@SuppressWarnings("serial")
public static DefaultTableModel getall(String figur, DefaultTableModel model) {
	switch (figur) {
	case "Rechteck":
		try {
			if (type.equals("MySQL")) {
				myCon = DriverManager.getConnection(url, user, pwd);
				
				} else if (type.equals("SQLite")) {
				myCon = DriverManager.getConnection(url);
				}
			myPrepStmnt = myCon.prepareStatement("SELECT * FROM rechteck", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			myRS = myPrepStmnt.executeQuery();
			myRS.last(); 
			String columns[] = { "ID", "Seite A", "Seite B", "Diagonale" , "Fläche", "Umfang", "Alpha", "Beta", "Gamma", "Delta" };
		      String data[][] = new String[myRS.getRow()][10];
		    
		      int i = 0;
		      myRS.first();
		      do {
		        int id = myRS.getInt("rechteckID");
		        Double sidea = myRS.getDouble("Sidea");
		        Double sideb = myRS.getDouble("Sideb");
		        Double diag = myRS.getDouble("diag");
		        Double area = myRS.getDouble("area");
		        Double umfang = myRS.getDouble("Umfang");
		        Double alpha = myRS.getDouble("alpha");
		        Double beta = myRS.getDouble("beta");
		        Double gamma = myRS.getDouble("gamma");
		        Double delta = myRS.getDouble("delta");
		        data[i][0] = id + "";
		        data[i][1] = sidea + "";
		        data[i][2] = sideb + "";
		        data[i][3] = diag + "";
		        data[i][4] = area + "";
		        data[i][5] = umfang + "";
		        data[i][6] = alpha + "";
		        data[i][7] = beta + "";
		        data[i][8] = gamma + "";
		        data[i][9] = delta + "";
		        i++;
		      } while (myRS.next());
		      model = new DefaultTableModel(data, columns) {
		    	    @Override
		    	    public boolean isCellEditable(int row, int column) {
		    	       //all cells false
		    	       return false;
		    	    }
		      };
		      
		} catch(SQLException e) {
		e.printStackTrace();
		}
		break;
	case "Dreieck":
		try {
			if (type.equals("MySQL")) {
				myCon = DriverManager.getConnection(url, user, pwd);
				
				} else if (type.equals("SQLite")) {
				myCon = DriverManager.getConnection(url);
				}
			myPrepStmnt = myCon.prepareStatement("SELECT * FROM dreieck", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			myRS = myPrepStmnt.executeQuery();
			myRS.last(); 
			
			//database = null;
			System.out.println(myRS.getRow());
			String columns[] = { "ID", "Seite A", "Seite B", "Seite C" , "Alpha", "Beta", "Gamma", "Höhe A", "Höhe B", "Höhe C", "Fläche", "Umfang", "Inradius", "Umradius"};
		      String data[][] = new String[myRS.getRow()][14];
		    
		      int i = 0;
		      myRS.first();
		      do {
		        int id = myRS.getInt("DreieckID");
		        Double sidea = myRS.getDouble("sidea");
		        Double sideb = myRS.getDouble("sideb");
		        Double sidec = myRS.getDouble("sidec");
		        Double alpha = myRS.getDouble("alpha");
		        Double beta = myRS.getDouble("beta");
		        Double gamma = myRS.getDouble("gamma");
		        Double ha = myRS.getDouble("ha");
		        Double hb = myRS.getDouble("hb");
		        Double hc = myRS.getDouble("hc");
		        Double area = myRS.getDouble("flaeche");
		        Double umfang = myRS.getDouble("Umfang");
		        Double inradius = myRS.getDouble("inradius");
		        Double umradius = myRS.getDouble("umradius");
		        
		        data[i][0] = id + "";
		        data[i][1] = sidea + "";
		        data[i][2] = sideb + "";
		        data[i][3] = sidec + "";
		        data[i][4] = alpha + "";
		        data[i][5] = beta + "";
		        data[i][6] = gamma + "";
		        data[i][7] = ha + "";
		        data[i][8] = hb + "";
		        data[i][9] = hc + "";
		        data[i][10] = area + "";
		        data[i][11] = umfang + "";
		        data[i][12] = inradius + "";
		        data[i][13] = umradius + "";
		        i++;
		      } while (myRS.next());
		      model = new DefaultTableModel(data, columns) {
		    	    @Override
		    	    public boolean isCellEditable(int row, int column) {
		    	       //all cells false
		    	       return false;
		    	    }
		      };
		     
		      
		} catch(SQLException e) {
		e.printStackTrace();
		}
		break;
	case "Kreis":
		try {
			if (type.equals("MySQL")) {
				myCon = DriverManager.getConnection(url, user, pwd);
				
				} else if (type.equals("SQLite")) {
				myCon = DriverManager.getConnection(url);
				}
			myPrepStmnt = myCon.prepareStatement("SELECT * FROM kreis", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			myRS = myPrepStmnt.executeQuery();
			myRS.last(); 
			String columns[] = { "KreisID", "Durchmesser", "Umfang", "Radius" , "Fläche"};
		      String data[][] = new String[myRS.getRow()][10];
		    
		      int i = 0;
		      myRS.first();
		      do {
		        int id = myRS.getInt("KreisID");
		        Double d = myRS.getDouble("d");
		        Double U = myRS.getDouble("U");
		        Double r = myRS.getDouble("r");
		        Double A = myRS.getDouble("A");
		        data[i][0] = id + "";
		        data[i][1] = d + "";
		        data[i][2] = U + "";
		        data[i][3] = r + "";
		        data[i][4] = A + "";
		        i++;
		      } while (myRS.next());
		      model = new DefaultTableModel(data, columns) {
		    	    @Override
		    	    public boolean isCellEditable(int row, int column) {
		    	       //all cells false
		    	       return false;
		    	    }
		      };
		      
		} catch(SQLException e) {
		e.printStackTrace();
		}
		break;
	case "Quadrat":
		try {
			if (type.equals("MySQL")) {
				myCon = DriverManager.getConnection(url, user, pwd);
				
				} else if (type.equals("SQLite")) {
				myCon = DriverManager.getConnection(url);
				}
			myPrepStmnt = myCon.prepareStatement("SELECT * FROM quadrat", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			myRS = myPrepStmnt.executeQuery();
			myRS.last(); 
			String columns[] = { "QuadratID", "Seite A", "Fläche", "Umfang" , "Diagonale"};
		      String data[][] = new String[myRS.getRow()][10];
		    
		      int i = 0;
		      myRS.first();
		      do {
		        int id = myRS.getInt("QuadratID");
		        Double sidea = myRS.getDouble("sidea");
		        Double A = myRS.getDouble("A");
		        Double U = myRS.getDouble("U");
		        Double d = myRS.getDouble("diag");
		        data[i][0] = id + "";
		        data[i][1] = sidea + "";
		        data[i][2] = A + "";
		        data[i][3] = U + "";
		        data[i][4] = d + "";
		        i++;
		      } while (myRS.next());
		      model = new DefaultTableModel(data, columns) {
		    	    @Override
		    	    public boolean isCellEditable(int row, int column) {
		    	       //all cells false
		    	       return false;
		    	    }
		      };
		      
		} catch(SQLException e) {
		e.printStackTrace();
		}
		break;

	default:
		break;
	}
	
	
	
	return model;
}
	
/**
 * 
 * Sends a Query to the Database and gets the parameters if it exists
 * @param figur
 * @param array
 * @param array1
 * @return
 */
public static boolean getDB(Figur figur,ArrayList<String> array, ArrayList<Double> array1) {
	boolean ret = false;
	//System.out.println(figur.getClass().getName());
	switch (figur.getClass().getName()) {
	
	case "Rechteck":
		try {
			if (type.equals("MySQL")) {
			myCon = DriverManager.getConnection(url, user, pwd);
			} else if (type.equals("SQLite")) {
			myCon = DriverManager.getConnection(url);
			}
			String query = "SELECT * FROM Rechteck WHERE "+array.get(0)+"="+array1.get(0)+" AND "+array.get(1)+"="+array1.get(1);
			//System.out.println(query);
			Statement st = myCon.createStatement();
			myRS = st.executeQuery(query); 
			if(myRS.next()) {
			    //System.out.println("test");
				((Rechteck) figur).setSidea(myRS.getDouble(2));
				((Rechteck) figur).setSideb(myRS.getDouble(3));
				((Rechteck) figur).setD(myRS.getDouble(4));
				figur.setA(myRS.getDouble(5));
				figur.setU(myRS.getDouble(6));
				((Rechteck) figur).setAlpha(myRS.getDouble(7));
				((Rechteck) figur).setBeta(myRS.getDouble(8));
				((Rechteck) figur).setGamma(myRS.getDouble(9));
				((Rechteck) figur).setDelta(myRS.getDouble(10));
				ret = true;
				}
			
			myCon.close();
			myCon = null;
			st=null;
			query = null;
			//ret = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Datenbank.doFinally();
		}
		break;
	case "Dreieck":
		try {
			if (type.equals("MySQL")) {
				myCon = DriverManager.getConnection(url, user, pwd);
				} else if (type.equals("SQLite")) {
				myCon = DriverManager.getConnection(url);
				}
			String query = "SELECT * FROM Dreieck WHERE "+array.get(0)+"="+array1.get(0)+" AND "+array.get(1)+"="+array1.get(1)+" AND "+array.get(2)+"="+array1.get(2);
			//System.out.println(query);
			Statement st = myCon.createStatement();
			myRS = st.executeQuery(query); 
			if(myRS.next()) {
			    //System.out.println("test");
				((Dreieck) figur).setSidea(myRS.getDouble(2));
				((Dreieck) figur).setB(myRS.getDouble(3));
				((Dreieck) figur).setC(myRS.getDouble(4));
				((Dreieck) figur).setAlpha(myRS.getDouble(5));
				((Dreieck) figur).setBeta(myRS.getDouble(6));
				((Dreieck) figur).setGamma(myRS.getDouble(7));
				figur.setU(myRS.getDouble(8));
				figur.setA(myRS.getDouble(9));
				((Dreieck) figur).setHeighta(myRS.getDouble(10));
				((Dreieck) figur).setHeightb(myRS.getDouble(11));
				((Dreieck) figur).setHeightc(myRS.getDouble(12));
				((Dreieck) figur).setInradius(myRS.getDouble(13));
				((Dreieck) figur).setUmradius(myRS.getDouble(14));
				ret = true;
				}
			
			myCon.close();
			myCon = null;
			st=null;
			query = null;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Datenbank.doFinally();
		}
		break;
	case "Kreis":
		try {
			if (type.equals("MySQL")) {
				myCon = DriverManager.getConnection(url, user, pwd);
				} else if (type.equals("SQLite")) {
				myCon = DriverManager.getConnection(url);
				}
			String query = "SELECT * FROM Kreis WHERE "+array.get(0)+"="+array1.get(0);
			//System.out.println(query);
			Statement st = myCon.createStatement();
			myRS = st.executeQuery(query); 
			if(myRS.next()) {
			    //System.out.println("test");
				((Kreis) figur).setD(myRS.getDouble(2));
				figur.setU(myRS.getDouble(3));
				((Kreis) figur).setR(myRS.getDouble(4));
				figur.setA(myRS.getDouble(5));
				ret = true;
				}
			
			myCon.close();
			myCon = null;
			st=null;
			query = null;
			//ret = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Datenbank.doFinally();
		}
		break;
	case "Quadrat":
		try {
			if (type.equals("MySQL")) {
				myCon = DriverManager.getConnection(url, user, pwd);
				} else if (type.equals("SQLite")) {
				myCon = DriverManager.getConnection(url);
				}
			String query = "SELECT * FROM Quadrat WHERE "+array.get(0)+"="+array1.get(0);
			//System.out.println(query);
			Statement st = myCon.createStatement();
			myRS = st.executeQuery(query); 
			if(myRS.next()) {
			    //System.out.println("test");
				((Quadrat) figur).setSidea(myRS.getDouble(2));
				figur.setA(myRS.getDouble(3));
				figur.setU(myRS.getDouble(4));
				((Quadrat) figur).setD(myRS.getDouble(5));
				ret = true;
				}
			
			myCon.close();
			myCon = null;
			st=null;
			query = null;
			//ret = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Datenbank.doFinally();
		}
		break;
	default:
		break;
	}
			
	return ret;
}


/**
 * Export a resource embedded into a Jar file to the local file path.
 *
 * @param resourceName ie.: "/SmartLibrary.dll"
 * @return The path to the exported resource
 * @throws Exception
 */


/**
 * Saves the Object into the Database.
 * @param figur
 * @return
 */
public static boolean save_DB(Figur figur) {
	boolean ret = false;
	switch (figur.getClass().getName()) {
	case "Quadrat":
		try {
			if (type.equals("MySQL")) {
				myCon = DriverManager.getConnection(url, user, pwd);
				myCon.setAutoCommit(true);
				} else if (type.equals("SQLite")) {
				myCon = DriverManager.getConnection(url);
				}
			myPrepStmnt = myCon.prepareStatement("INSERT INTO Quadrat(sidea, A, U, diag) VALUES (?,?,?,?)");
			myPrepStmnt.setDouble(1, ((Quadrat) figur).getSidea());
			myPrepStmnt.setDouble(2, figur.getA());
			myPrepStmnt.setDouble(3, figur.getU());
			myPrepStmnt.setDouble(4, ((Quadrat) figur).getD());
			myPrepStmnt.executeUpdate();
			ret = true;
		} catch (SQLException e) {
			ret = false;
			e.printStackTrace();
		} finally {
			Datenbank.doFinally();
		}
		break;
	case "Dreieck":
		try {
			//System.out.println("test");
			//System.out.println(figur.getSidea());
			if (type.equals("MySQL")) {
				myCon = DriverManager.getConnection(url, user, pwd);
				} else if (type.equals("SQLite")) {
				myCon = DriverManager.getConnection(url);
				}
			myPrepStmnt = myCon.prepareStatement("INSERT INTO Dreieck(Sidea, Sideb, Sidec, alpha, beta, gamma, ha, hb, hc, flaeche, umfang, inradius, umradius) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)");
			myPrepStmnt.setDouble(1, ((Dreieck) figur).getSidea());
			myPrepStmnt.setDouble(2, ((Dreieck) figur).getB());
			myPrepStmnt.setDouble(3, ((Dreieck) figur).getC());
			myPrepStmnt.setDouble(4, ((Dreieck) figur).getAlpha());
			myPrepStmnt.setDouble(5, ((Dreieck) figur).getBeta());
			myPrepStmnt.setDouble(6, ((Dreieck) figur).getGamma());
			myPrepStmnt.setDouble(7, ((Dreieck) figur).getHeighta());
			myPrepStmnt.setDouble(8, ((Dreieck) figur).getHeightb());
			myPrepStmnt.setDouble(9, ((Dreieck) figur).getHeightc());
			myPrepStmnt.setDouble(10, figur.getA());
			myPrepStmnt.setDouble(11, figur.getU());
			myPrepStmnt.setDouble(12, ((Dreieck) figur).getInradius());
			myPrepStmnt.setDouble(13, ((Dreieck) figur).getUmradius());
			myPrepStmnt.executeUpdate();
			ret = true;
		} catch (SQLException e) {
			ret = false;
			e.printStackTrace();
		} finally {
			Datenbank.doFinally();
		}
		break;
	case "Rechteck":
		try {
			if (type.equals("MySQL")) {
				myCon = DriverManager.getConnection(url, user, pwd);
				} else if (type.equals("SQLite")) {
				myCon = DriverManager.getConnection(url);
				}
			myPrepStmnt = myCon.prepareStatement("INSERT INTO Rechteck(Sidea, Sideb, diag, area, Umfang, alpha, beta, gamma, delta) VALUES (?,?,?,?,?,?,?,?,?)");
			myPrepStmnt.setDouble(1, ((Rechteck) figur).getSidea());
			myPrepStmnt.setDouble(2, ((Rechteck) figur).getSideb());
			myPrepStmnt.setDouble(3, ((Rechteck) figur).getD());
			myPrepStmnt.setDouble(4, figur.getA());
			myPrepStmnt.setDouble(5, figur.getU());
			myPrepStmnt.setDouble(6, ((Rechteck) figur).getAlpha());
			myPrepStmnt.setDouble(7, ((Rechteck) figur).getBeta());
			myPrepStmnt.setDouble(8, ((Rechteck) figur).getGamma());
			myPrepStmnt.setDouble(9, ((Rechteck) figur).getDelta());
			myPrepStmnt.executeUpdate();
			ret = true;
		} catch (SQLException e) {
			ret = false;
			e.printStackTrace();
		} finally {
			Datenbank.doFinally();
		}
		break;
	case "Kreis":
		try {
			if (type.equals("MySQL")) {
				myCon = DriverManager.getConnection(url, user, pwd);
				} else if (type.equals("SQLite")) {
				myCon = DriverManager.getConnection(url);
				}
			myPrepStmnt = myCon.prepareStatement("INSERT INTO Kreis(d,U, r, A) VALUES (?,?,?,?)");
			myPrepStmnt.setDouble(1, ((Kreis) figur).getD());
			myPrepStmnt.setDouble(2, figur.getU());
			myPrepStmnt.setDouble(3, ((Kreis) figur).getR());
			myPrepStmnt.setDouble(4, figur.getA());
			myPrepStmnt.executeUpdate();
			ret = true;
		} catch (SQLException e) {
			ret = false;
			e.printStackTrace();
		} finally {
			Datenbank.doFinally();
		}
		break;
	}
	return ret;
}

	/**
	 * Does Close all Open Connections.
	 */
	private static void doFinally() {
		if(myRS != null) {
			try {
				myRS.close();
				myRS = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}

		if(myPrepStmnt != null) {
			try {
				myPrepStmnt.close();
				myPrepStmnt = null;
			} catch (SQLException e){
				e.printStackTrace();
			}
		}

		if(myCon != null) {
			try {
				myCon.close();
				myCon = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	};
	
}
