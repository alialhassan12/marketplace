import java.awt.Font;
import java.awt.GridBagLayout;
import java.sql.*;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class home extends JFrame{
    Connection connect=null;
    JPanel panel1;
    JLabel name;
    JLabel title;
    JButton profileBtn;
    JButton postBtn;
    JButton browseBtn;
    home(int Uid){  
        String query="Select name From users where Uid="+Uid;
        connect=config.getConnection();

        try{
            Statement stmt=connect.createStatement();
            ResultSet rs=stmt.executeQuery(query);
            if(rs.next()){
                name=new JLabel(rs.getString("name"));
            }else{
                name=new JLabel("Name not Found");
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

        
        setLayout(new GridBagLayout());

        title=new JLabel("Home");
        title.setFont(new Font("Arial",Font.BOLD,24));

        profileBtn=new JButton("Profile");
        postBtn=new JButton("Post");
        browseBtn=new JButton("Browse");

        panel1=new JPanel();
        // panel1.setPreferredSize(new Dimension(300,500));
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
        panel1.add(title);
        panel1.add(Box.createVerticalStrut(10));
        panel1.add(profileBtn);
        panel1.add(Box.createVerticalStrut(10));
        panel1.add(postBtn);
        panel1.add(Box.createVerticalStrut(10));
        panel1.add(browseBtn);

        add(panel1);
        add(name);
        pack();
    }
}
