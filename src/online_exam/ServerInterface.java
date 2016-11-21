/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package online_exam;

import java.rmi.Remote;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * @author Nick
 */
interface ServerInterface  extends Remote{

public ArrayList<String> getExamQuestions() throws RemoteException;
public ArrayList<String> getExamAnswers() throws RemoteException;
public ArrayList<ArrayList<String>> getExamChoices() throws RemoteException;
public void storeResults(String name, String Id, float score, String grade) throws RemoteException;

}