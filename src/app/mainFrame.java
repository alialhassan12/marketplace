package app;

import java.awt.*;
import javax.swing.*;

public class mainFrame extends JFrame {
    int client_id;
    private CardLayout cardLayout;
    private JPanel mainPanel; // container of all pages
    private String currentCard = "Home"; // track current panel

    public mainFrame(int client_id) {
        this.client_id = client_id;

        // create cardLayout and container
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // create the pages
        homePanel homePage = new homePanel(this, this.client_id);
        profilePanel profilePage = new profilePanel(this, this.client_id);
        searchPanel searchPage = new searchPanel(this, this.client_id);
        newListingPanel newListingPage = new newListingPanel(this, this.client_id);
        // supportPanel supportPage=new supportPanel(this.client_id);

        // add panels to mainPanel
        mainPanel.add(homePage, "Home");
        mainPanel.add(profilePage, "Profile");
        mainPanel.add(searchPage, "Search");
        mainPanel.add(newListingPage, "newListing");
        // mainPanel.add(supportPage,"Support");

        getContentPane().add(mainPanel);

        // force show home panel at the first
        cardLayout.show(mainPanel, "Home");

        // frame setup
        setTitle("S&A Motors");
        ImageIcon image = new ImageIcon(getClass().getResource("../layout/frameIcon.png"));
        Image Icon = image.getImage();
        setIconImage(Icon);
    }

    // switch pages 
    public void switchPages(String pageName) {
        cardLayout.show(mainPanel, pageName);
    }
}
