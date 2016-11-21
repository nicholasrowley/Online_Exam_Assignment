package online_exam;

import java.sql.*;

import java.rmi.RemoteException;

import  java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * RMI method declarations for online exam application.
 * @author Nick
 */
public class ServerMethods extends UnicastRemoteObject implements ServerInterface {
    
    public ServerMethods() throws RemoteException
    {
        //do nothing
    }
    
    /**
     * Gets the exam questions from database and returns them to client as an 
     * ArrayList.
     * @return list of exam questions.
     * @throws RemoteException 
     */
    @Override
    public ArrayList<String> getExamQuestions() throws RemoteException {
        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;
        ArrayList<String> questions = new ArrayList<>();
        
        try {
            // 1. Get a connection to database
            myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/online_exam", "root" , "Nick1");
            
            System.out.println("Database connection successful!\n");
            
            // 2. Create a statement
            myStmt = myConn.createStatement();
            
            // 3. Execute SQL query
            myRs = myStmt.executeQuery("select * from questions");
            
            // 4. Process the result set
            while (myRs.next()) {
                questions.add(myRs.getString("Question"));
            }
            return questions;
        }
        catch (Exception exc) {
            System.out.println("Could not connect to database please try again later.");
        }
        finally {
            if (myRs != null) {
                try {
                    myRs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ServerMethods.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if (myStmt != null) {
                try {
                    myStmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ServerMethods.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if (myConn != null) {
                try {
                    myConn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ServerMethods.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return null;
    }
    
    /**
     * Gets the list of answers for each question and returns them as an ArrayList
     * of an ArrayList.
     * @return List of choices for each question.
     * @throws RemoteException 
     */
    @Override
    public ArrayList<ArrayList<String>> getExamChoices() throws RemoteException {
        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;
        ArrayList<ArrayList<String>> choices = new ArrayList<>();
        
        try {
            // 1. Get a connection to database
            myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/online_exam", "root" , "Nick1");
            
            System.out.println("Database connection successful!\n");
            
            // 2. Create a statement
            myStmt = myConn.createStatement();
            
            // 3. Execute SQL query
            myRs = myStmt.executeQuery("select * from questions");
            
            // 4. Process the result set
            int i = 0;
            while (myRs.next()) {
                choices.add(new ArrayList<String>());
                choices.get(i).add(myRs.getString("OptionA"));
                choices.get(i).add(myRs.getString("OptionB"));
                choices.get(i).add(myRs.getString("OptionC"));
                choices.get(i).add(myRs.getString("OptionD"));
                i++;
            }
            return choices;
        }
        catch (Exception exc) {
            System.out.println("Could not connect to database please try again later.");
        }
        finally {
            if (myRs != null) {
                try {
                    myRs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ServerMethods.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if (myStmt != null) {
                try {
                    myStmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ServerMethods.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if (myConn != null) {
                try {
                    myConn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ServerMethods.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return null;
    }
    
    /**
     * Gets the exam answers for each question and returns them as an ArrayList.
     * @return List of Answers for each question.
     * @throws RemoteException 
     */
    @Override
    public ArrayList<String> getExamAnswers() throws RemoteException {
    Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;
        ArrayList<String> answers = new ArrayList<>();
        
        try {
            // 1. Get a connection to database
            myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/online_exam", "root" , "Nick1");
            
            System.out.println("Database connection successful!\n");
            
            // 2. Create a statement
            myStmt = myConn.createStatement();
            
            // 3. Execute SQL query
            myRs = myStmt.executeQuery("select * from questions");
            
            // 4. Process the result set
            while (myRs.next()) {
                answers.add(myRs.getString("Answer"));
            }
            return answers;
        }
        catch (Exception exc) {
            System.out.println("Could not connect to database please try again later.");
        }
        finally {
            if (myRs != null) {
                try {
                    myRs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ServerMethods.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if (myStmt != null) {
                try {
                    myStmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ServerMethods.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if (myConn != null) {
                try {
                    myConn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ServerMethods.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return null;
    }
    
    /**
     * Inserts a row into the Grades table in the database with the users score
     * in the exam.
     * @param name - user name.
     * @param Id - user id
     * @param score - user score as a percentage
     * @param grade - user grade
     * @throws RemoteException 
     */
    @Override
    public void storeResults(String name, String Id, float score, String grade) throws RemoteException {
        Connection myConn = null;
        Statement myStmt = null;
        String sql;
        
        try {
            // 1. Get a connection to database
            myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/online_exam", "root" , "Nick1");
            
            // 2. Create a statement
            myStmt = myConn.createStatement();
            
            // 3. Execute SQL insert
            sql = "insert into Grades " +
                                    "values ( '" + Id + "', '" + name + "', " + Float.toString(score) + ", '" + grade + "')";
            myStmt.executeUpdate(sql);
            
            System.out.println("Database insert successful!\n");
        }
        catch (Exception exc) {
            System.out.println("Could not connect to database please try again later.");
        }
        finally {            
            if (myStmt != null) {
                try {
                    myStmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ServerMethods.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if (myConn != null) {
                try {
                    myConn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ServerMethods.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
}