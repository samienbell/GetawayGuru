/*
  AddRemove.java
  The AddRemove class models a question, specifficaly, about if the user wants to add or remove a guruship
  Instruction to fellow coder: (The golden star) "⭐" Represents the different sections of the code "⭐" (this makes it easier to find different sections; they act like subheadings)

  Programmed by: Oskar Preiswerk
  Date Created: june 16, 2023
  Last Modified By: Oskar Preiswerk
  Date Modified: june 16, 2023
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

public class AddRemove implements ActionListener{

  //color scheme and fonts used
  private Color green = new Color(115,192,123);
  private Color blue = new Color(181,207,236);
  private Color darkBlue = new Color(53,63,158);
  private Color darkGreen = new Color(81, 166, 115);
  private Font title = new Font("Arial", Font.PLAIN, 48);
  private Font defaultF = new Font("Arial", Font.PLAIN, 16);
  private Font subTitle = new Font("Arial", Font.PLAIN, 24);

  private JFrame addRemoveF;
  private JButton add, remove, back;
  private JLabel searchBack, results;
  private JTextField search;
  private JLayeredPane layer;
  private User user;


// ⭐Contrustor methods

  /**
  * Contructor that sets the fields By: Oskar
  */
  public AddRemove(User u){

    user = new User(u);
    addRemoveF = new JFrame();
    addRemoveF.setSize(800, 500);
    addRemoveF.getContentPane().setBackground(blue);

    add = new JButton();
    add.setBounds(705,75,90,50);
    add.setText("Add");
    add.setBackground(darkBlue);
    add.setForeground(Color.WHITE);
    add.addActionListener(this);

    remove = new JButton();
    remove.setBounds(705, 135, 90, 50);
    remove.setText("Remove");
    remove.setBackground(darkBlue);
    remove.setForeground(Color.WHITE);
    remove.addActionListener(this);
    

    back = new JButton();
    back.setBounds(12,100,75,50);
    back.setText("Back");
    back.setBackground(darkBlue);
    back.setForeground(Color.WHITE);
    back.setFont(defaultF);
    back.addActionListener(this);

    searchBack = new JLabel();
    searchBack.setBounds(100,75,600,100);
    searchBack.setBackground(darkGreen);
    searchBack.setOpaque(true);

    search = new JTextField();
    search.setBounds(125, 100, 550, 50);

    results = new JLabel();
    results.setBounds(350, 250, 600, 100);
    results.setBackground(blue);
    results.setForeground(darkBlue);
    results.setFont(subTitle);
    results.setOpaque(true);
    results.setVisible(false);
  
    
    layer = new JLayeredPane();
    layer.setBounds(0,0, 800, 500);
    layer.add(searchBack, new Integer(1));
    layer.add(search, new Integer(2));
    
    addRemoveF.add(layer);addRemoveF.add(add);addRemoveF.add(back);addRemoveF.add(results);addRemoveF.add(remove);
    addRemoveF.setLayout(null);
    addRemoveF.setVisible(true);
  }

// ⭐Public instance methods

  /**
  * Method that performs after user action By: Oskar
  * @param e The ActionEvent of user
  */
  public void actionPerformed(ActionEvent e){
    
    
    if(e.getSource() == add){

      if(!userExists(search.getText())){
        results.setText("User Added!");
        results.setVisible(true);
        add(search.getText());
      }

      else if(userExists(search.getText())){
        results.setText("No User Found");
        results.setVisible(true);
      }
    }

    if(e.getSource() == remove){

      if(friendExists(search.getText())){
        results.setText("Friend Removed!");
        results.setVisible(true);
        remove(search.getText());
      }

      else if(!friendExists(search.getText())){
        results.setText("You Are Not Friends With That User");
        results.setVisible(true);
      }
    }

    if(e.getSource() == back){
      addRemoveF.setVisible(false);
      addRemoveF.dispose();
      new Profile(user);
    }
    
  }//end actionPerformed

// ⭐Public instance methods
  /**
  * Method that checks if a userExists By: Oskar
  * @param s The string of username being checked
  */
 public boolean userExists(String s){

    boolean username;
    
    try{
      username = user.isValidUsername(s);
      
      return username;
    }
    catch(IOException e){
      throw new RuntimeException(e);
    }
    
  }//end userExists

  /**
  * Method that checks if a friend exists
  * @param s The name of the friend
  * @return username the boolean
  */
  public boolean friendExists(String s){

    boolean username = false;
    
    try{
      ArrayList<String> friends = user.listFriend();

      for(int i = 0; i<friends.size(); i++){
        if(s.equalsIgnoreCase(friends.get(i))){
          return true;
        }
      }
      return username;
    }
    catch(IOException e){
      throw new RuntimeException(e);
    }
    
  }//end friendExists
  
  /**
  * Method that adds a friend
  * @param s The friends name
  * @return user The friend of the User
  */
  public String add(String s){

    String error = null;
    
    try{
      user.addFriend(s);
    }
    catch(IOException e){
      throw new RuntimeException(e);
    }
    return error;
    
  }//end add

  /**
  * Method that removes a friend
  * @param s The name of the friend
  * @return user The friend of the user
  */
  public String remove(String s){

    String error = null;
    
    try{
      user.removeFriend(s);
    }
    catch(IOException e){
      throw new RuntimeException(e);
    }
    return error;
    
  }//end remove
  
}//end Rate.java