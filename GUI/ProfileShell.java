/*
  ProfileShell.java
  The ProfileShell class models the admin only page.
  Instruction to fellow coder: (The golden star) "⭐" Represents the different sections of the code "⭐" (this makes it easier to find different sections; they act like subheadings)

  Programmed by: Krish Patel
  Date Created: April 14, 2023
  Last Modified By: Krish Patel
  Date Modified: April 19, 2023
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;  
import java.util.*;
import java.io.*;
import java.awt.FlowLayout;  
public class ProfileShell implements ActionListener{//Alex messed around a bit and removed the code added by him - Alex
  //color scheme and fonts used
  private Color green = new Color(115,192,123);
  private Color blue = new Color(181,207,236);
  private Color darkBlue = new Color(53,63,158);
  private Color darkGreen = new Color(81, 166, 115);
  private Font title = new Font("Arial", Font.BOLD, 48);
  private Font defaultF = new Font("Arial", Font.BOLD, 16);
  private Font defaultF2 = new Font("Arial", Font.BOLD, 12);
  private Font subTitle = new Font("Arial", Font.BOLD, 24);

  private JFrame aboutF;
  private JButton back;
  private JLabel nameBack, descriptionBack, name, rated, ratedTitle, friendTitle, noFriends;
  private JLayeredPane layer;
  private JScrollPane ratedScroll, friendScroll;
  private JPanel panel, panelFriend;
  private ArrayList<JButton> friends;
  int friendCounter = 0;
  
  private User user, user2;
  private Ratings r;
  private ArrayList<String> locations;
  
// ⭐Contrustor methods
  /**
   * A constructor that creates the frame By: Krish, Oskar
   */
  public ProfileShell(User u, User u2){

    user = new User(u);
    user2 = new User(u2);
    aboutF = new JFrame();
    aboutF.setSize(800, 500);
    aboutF.setLayout(null);
    aboutF.getContentPane().setBackground(blue);
    
    back = new JButton();
    nameBack = new JLabel();
    name = new JLabel(user2.getUsername());
    descriptionBack = new JLabel();
    layer = new JLayeredPane();
    panel = new JPanel();
    ratedTitle = new JLabel("Previous Ratings", SwingConstants.CENTER);
    friendTitle = new JLabel("Friends", SwingConstants.CENTER);
    noFriends = new JLabel("This User Has No Friends");

    noFriends.setBounds(100, 225, 200, 50);
    noFriends.setBackground(green);
    noFriends.setForeground(darkBlue);
    noFriends.setVisible(false);



    friendTitle.setBounds(38, 150, 338, 50);
    friendTitle.setFont(subTitle);
    friendTitle.setBackground(green);
    friendTitle.setForeground(darkBlue);
    friendTitle.setOpaque(true);

    nameBack.setBounds(100, 50, 590, 75);
    nameBack.setOpaque(true);
    nameBack.setBackground(darkGreen);

    name.setBounds(355, 62, 400, 50);
    name.setFont(subTitle);
    name.setForeground(darkBlue);
    name.setOpaque(false);

    descriptionBack.setBounds(12, 150, 775, 300);
    descriptionBack.setOpaque(true);
    descriptionBack.setBackground(green);

    back.setBounds(12, 62, 75, 50);
    back.setText("Back");
    back.setBackground(darkGreen);
    back.setForeground(darkBlue);
    back.setOpaque(true);
    back.addActionListener(this);

    

    String ratedLoc = "<html>";

    ratings();
    cities();
    
    int counter = 0;
    String stars = "";

    for(int i = 0; i < locations.size(); i++){
      
      String rating = r.getRating(i)+"/10";
      
      ratedLoc += locations.get(i) + "     " + rating + "<br/><br/>";
      counter++;
      stars = "";
    }

    ratedLoc += "</html>";

    int y = counter * 50;
    
    rated = new JLabel();
    rated.setBounds(412, 200, 338, y);
    rated.setFont(defaultF);
    rated.setText(ratedLoc);

    panel.setBounds(412, 200, 338, y);
    panel.setBackground(green);
    panel.add(rated);

    ratedScroll = new JScrollPane(panel);
    ratedScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    ratedScroll.setBounds(425, 200, 338, 225);

    ratedTitle.setBounds(425, 150, 338, 50);
    ratedTitle.setFont(subTitle);
    ratedTitle.setBackground(green);
    ratedTitle.setForeground(darkBlue);
    ratedTitle.setOpaque(true);

    y = 0;

    friendArray();

    y = 75*friendCounter;

    JPanel panel2 = new JPanel();
    panel2.setBounds(100, 150, 600, y);

    panelFriend = new JPanel();
    panelFriend.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 25));
    panelFriend.setPreferredSize(new Dimension(600, y));
    panelFriend.setBackground(green);
    panel2.add(panelFriend);

    for(int i = 0; i < friendCounter; i++){
      panelFriend.add(friends.get(i));
    }

    friendScroll = new JScrollPane(panelFriend);
    friendScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    friendScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    friendScroll.setBounds(38, 200, 338, 175);    
    
    layer.setBounds(0,0, 800, 570);
    layer.add(nameBack, new Integer(1));
    layer.add(descriptionBack, new Integer(2));
    layer.add(name, new Integer(3));
    layer.add(ratedScroll, new Integer(4));
    layer.add(ratedTitle, new Integer(4));
    layer.add(friendScroll, new Integer(4));
    layer.add(friendTitle, new Integer(4));
    layer.add(noFriends, new Integer(5));    

    layer.setVisible(true);
    
    aboutF.add(layer);aboutF.add(back);
    
    aboutF.setVisible(true);
      
  }
  
// ⭐Public Instance methods
  /**
   * Catches the user's click By: Krish, Oskar
   * @param e The button being clicked
   */
  public void actionPerformed(ActionEvent e){
    if(e.getSource() == back){
      System.out.println("working");
      aboutF.setVisible(false);
      aboutF.dispose();
      new Profile(new User(user));
    }
    
  }//end actionPerformed

  /**
  * Method that returns ratings as a string By: Oskar, Krish
  * @return r The string of ratings
  */
  public String ratings(){

    String error = null;
    
    try{
      r = user2.userRatings();
    }
    catch(IOException e){
      throw new RuntimeException(e);
    }
    return error;
    
  }//end ratings
  
  /**
  * Method that returns cities as a string By: Oskar, Krish
  * @return locations The string of cities
  */
  public String cities(){

    String error = null;
    
    try{
      locations = user2.userCities();
    }
    catch(IOException e){
      throw new RuntimeException(e);
    }
    return error;
    
  }//end cities
  
  /**
  * Method that returns an array as a string By: Oskar, Krish
  * @return friends The string of friends
  */
  public String friendArray(){

    String error = null;
    
    try{
      ArrayList<String> test = user2.listFriend();
      friends = new ArrayList<JButton>(test.size());
      for(int i = 0; i < test.size(); i++){
        friends.add(i, new JButton());
        friends.get(i).setText(test.get(i));
        
        friends.get(i).setBackground(green);
        friends.get(i).setForeground(darkBlue);
        friends.get(i).setBorder(BorderFactory.createLineBorder(darkBlue));
        friends.get(i).setFont(defaultF);
        friends.get(i).setPreferredSize(new Dimension(338,50));
        friends.get(i).addActionListener(this);
        
        friendCounter++;
      }

      if(friendCounter == 0){
        noFriends.setVisible(true);
      }
    }
    catch(IOException e){
      throw new RuntimeException(e);
    }
    return error;
    
  }//end friendArray
  
}//end ProfileShell.java