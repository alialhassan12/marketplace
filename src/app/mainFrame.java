package app;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class mainFrame extends JFrame{
    int client_id;
    private CardLayout cardLayout;
    private JPanel mainPanel; //container of all pages

    public mainFrame(int client_id){
        this.client_id=client_id;

        //create cardLayout and container
        cardLayout=new CardLayout();
        mainPanel=new JPanel(cardLayout);
        
        //create the pages
        homePanel homePage=new homePanel(this,this.client_id);
        profilePanel profilePage=new profilePanel(this,this.client_id);
        searchPanel searchPage=new searchPanel(this,this.client_id);

        //add panels to mainPanel
        mainPanel.add(homePage,"Home");
        mainPanel.add(profilePage,"Profile");
        mainPanel.add(searchPage,"Search");
        
        getContentPane().add(mainPanel);
        
        //force show home panel at the first
        cardLayout.show(mainPanel,"Home");

        //frame setup
        // setTitle("S&A MOTORS");
        // setDefaultCloseOperation(EXIT_ON_CLOSE);
        // setLocationRelativeTo(null);
        // setExtendedState(JFrame.MAXIMIZED_BOTH);
        // setVisible(true);
    }
    //added function to switch between panels in panel classes
    public void switchPages(String pageName){
        cardLayout.show(mainPanel,pageName);
    }
}
