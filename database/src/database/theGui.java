package theGutenSearch;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


/*
 * theGui
 * A class that creates the gui
 * 
 * @author group 7
 */
public class theGUI implements ActionListener {
   //Frames
   private JFrame mainFrame;
   private JFrame adminFrame;
   private JFrame userFrame;
   private JFrame displayFrame;
   ArrayList<JTextField> list = new ArrayList<JTextField>();
   
   
   database db = new database();
   
   //File Chooser
   JFileChooser jfc;
   Container contentPane;
   private JFileChooser uploadFile;
   private JFrame theFile;
   int retval;
   
   //Panels
   private JPanel controlPanel;
   
   //Labels
   private JLabel headerLabel;
   private JLabel statusLabel;

   
	/*
	 * A gui constructor
	 * 
	 */
   public theGUI() 
   {
      initialGUI();
   }

	/*
	 * main
	 * 
	 */
   public static void main(String[] args)
   {
      theGUI gutenGUI = new theGUI();  
      gutenGUI.initialGUIDetails();     
   }
   
   
	/*
	 * creates initial gui
	 * 
	 */
   private void initialGUI()
   {
      mainFrame = new JFrame("GutenSearch");
      mainFrame.setSize(200,400);
      mainFrame.setLayout(new GridLayout(3, 1));

      //Listener for top frame
      mainFrame.addWindowListener(new WindowAdapter() 
      {
    	 //Closes the window based off of WindowsAdapter method windowClosing()
         public void windowClosing(WindowEvent windowEvent)
         {
	        System.exit(0);
         }        
      });   
      
      headerLabel = new JLabel("",JLabel.CENTER);
      
      //Close/Minimize/Maximize
      statusLabel = new JLabel("",JLabel.CENTER);        
      statusLabel.setSize(350,100);
     
      //The layout itself
      controlPanel = new JPanel();
      controlPanel.setLayout(new FlowLayout());

      mainFrame.add(headerLabel);
      mainFrame.add(controlPanel);
      mainFrame.add(statusLabel);
      mainFrame.setVisible(true);  
   }
   
	/*
	 * creates initial gui #2
	 * 
	 */
   private void initialGUIDetails()
   {
      headerLabel.setText("Admin or User?");      

      //The initial panel
      final JPanel choicePanel = new JPanel();
      choicePanel.setSize(300,300);

      CardLayout layout = new CardLayout();
      layout.setHgap(10);
      layout.setVgap(10);
      choicePanel.setLayout(layout);        
	             
      JPanel choiceButtonPanel = new JPanel();
      
      JButton adminButton = new JButton("Admin");
      choiceButtonPanel.add(adminButton);
      adminButton.addActionListener(this);
      
      JButton userButton = new JButton("User");
      choiceButtonPanel.add(userButton);
      userButton.addActionListener(this);
      
      JPanel buttonPanel = new JPanel();
      
      JButton cancelButton = new JButton("Cancel");
      buttonPanel.add(cancelButton);  
      
      cancelButton.addActionListener(this);

      choicePanel.add(buttonPanel);

      controlPanel.add(choiceButtonPanel);
	  controlPanel.add(choicePanel);

      mainFrame.setVisible(true);  

   }
   
   //-----------------------------------------------------------
	/*
	 * creates admin gui
	 * 
	 */
   private void adminGUI()
   {
	   adminFrame = new JFrame("Admin"); 
	   adminFrame.setSize(400,400);
	   adminFrame.setLayout(new GridLayout(6, 1));	   
	   
	   headerLabel = new JLabel("",JLabel.CENTER);
	   	   
       JPanel adminButtonPanel = new JPanel();
      
       JButton uploadFile = new JButton("Upload File");
       adminButtonPanel.add(uploadFile);
       uploadFile.addActionListener(this);

       JPanel oldTitleBoxPanel = new JPanel(new FlowLayout());
       oldTitleBoxPanel.add(new JLabel("Old Title*: " ));
       JTextField ottf = new JTextField(20);

   	   oldTitleBoxPanel.add(ottf);
   	   
       JPanel oldAuthorBoxPanel = new JPanel(new FlowLayout());
       oldAuthorBoxPanel.add(new JLabel("Old Author*: " ));
       JTextField oatf = new JTextField(20);

   	   oldAuthorBoxPanel.add(oatf);
   	   
       JPanel newTitleBoxPanel = new JPanel(new FlowLayout());
       newTitleBoxPanel.add(new JLabel("New Title: " ));
       JTextField nttf = new JTextField(20);

   	   newTitleBoxPanel.add(nttf);
   	   
//       JPanel oldAuthorBoxPanel = new JPanel(new FlowLayout());
//       oldAuthorBoxPanel.add(new JLabel("Old Author*: " ));
//   	   oldAuthorBoxPanel.add(new JTextField(20));
   	   
       JPanel newAuthorBoxPanel = new JPanel(new FlowLayout());
       newAuthorBoxPanel.add(new JLabel("New Author: " ));
       JTextField natf = new JTextField(20);
   	   newAuthorBoxPanel.add(natf);
   	   
       JButton editSource = new JButton("Edit Source");
       adminButtonPanel.add(editSource);
       editSource.addActionListener(this);
       
       list.add(ottf);
       list.add(nttf);
       list.add(oatf);
       list.add(natf);


       
       
       //System.out.println("oatf == " + oatf.getText());
       //db.editBook(list.get(0).getText(), list.get(1).getText(), list.get(2).getText(), list.get(3).getText());
   	   
       adminFrame.add(adminButtonPanel);
       adminFrame.add(oldTitleBoxPanel);
       adminFrame.add(newTitleBoxPanel);
       adminFrame.add(oldAuthorBoxPanel);
       adminFrame.add(newAuthorBoxPanel);
       //adminFrame.add(editSource);
      
	   adminFrame.setVisible(true);
   }
   
	/*
	 * creates display pane
	 * 
	 */
   private void displayPane()
   {
	   displayFrame = new JFrame("Output");
	   displayFrame.setSize(400,400);  
   }
   
   //-----------------------------------------------------------
	/*
	 * creates user gui
	 * 
	 */
   private void userGUI()
   {
	   userFrame = new JFrame("User");
	   userFrame.setSize(400,400);
	   userFrame.setLayout(new BorderLayout());
	      
	   headerLabel = new JLabel("",JLabel.CENTER);
	  
	   userFrame.setVisible(true);
	   userGUIdetails();
   }
   
	/*
	 * creates user gui details
	 * 
	 */
   private void userGUIdetails()
   {
	   JPanel searchLabelPanel = new JPanel(new FlowLayout());
	   JPanel searchInputPanel = new JPanel(new FlowLayout());
	   JLabel searchLabel = new JLabel("Enter Search Terms");
	   JPanel searchPanel = new JPanel(new BorderLayout());
//	   final database db = new database(); 
	   
	   
	   final JTextField searchText = new JTextField(25);
	   final JTextArea resultText = new JTextArea();
	   resultText.setLineWrap(true);
	   resultText.setEditable(false);
	   
	   
	   searchLabelPanel.add(searchLabel);
	   searchInputPanel.add(searchText);
	   searchPanel.add(searchLabelPanel, BorderLayout.NORTH);
	   searchPanel.add(searchInputPanel, BorderLayout.CENTER);
	   
	   
	   JPanel resultLabelPanel = new JPanel(new FlowLayout());
	   JLabel resultLabel = new JLabel("Results");
	   JPanel resultPanel = new JPanel(new BorderLayout());
	   JScrollPane scrollPane = new JScrollPane(resultText);
	   
	   resultLabelPanel.add(resultLabel);
	   resultPanel.add(resultLabelPanel, BorderLayout.NORTH);
	   resultPanel.add(scrollPane, BorderLayout.CENTER);
	   
	   searchText.addActionListener(new ActionListener(){
		   public void actionPerformed(ActionEvent e){
			   resultText.setText(db.search(searchText.getText()));
           }});
	   
	   JButton clearSearch = new JButton("Clear Search");
	   clearSearch.addActionListener(new ActionListener(){
		   public void actionPerformed(ActionEvent e){
			   searchText.setText("");
			   resultText.setText("");
           }});
	   
	   JPanel clearPanel = new JPanel(new FlowLayout());
	   clearPanel.add(clearSearch);
	   resultPanel.add(clearPanel, BorderLayout.SOUTH);
	   
	   JPanel info = new JPanel(new FlowLayout());
	   
	   JLabel triviaLabel = new JLabel("Number of sources parsed: " + db.pomap.size());   
	   	   
	   info.add(triviaLabel);
	   
	   JPanel textBoxPanel = new JPanel(new BorderLayout());
	   textBoxPanel.add(searchPanel, BorderLayout.NORTH);
	   textBoxPanel.add(resultPanel, BorderLayout.CENTER);
	   userFrame.add(textBoxPanel, BorderLayout.CENTER);
	   userFrame.add(info, BorderLayout.SOUTH);
	   
   }
   
	/*
	 * action performed listener
	 * 
	 */
   @Override
   public void actionPerformed(ActionEvent e) {
	   String event_input;
	   String file_name;
	   event_input =  e.getActionCommand();
	   System.out.println("event_input is: \n" + event_input);
	   
	   if(event_input.equals("Admin"))
	   {
		   mainFrame.setVisible(false);
		   adminGUI();
		   //userGUI();
	   }
	   
	   if(event_input.equals("User"))
	   {
		   mainFrame.setVisible(false);
		   userGUI();
	   }
	   
	   if(event_input.equals("Cancel"))
	   {
		   mainFrame.setVisible(false);
		   System.exit(1);
	   }
	   
	   if(event_input.equals("Upload File"))
	   {
		   jfc = new JFileChooser();
		   retval = jfc.showOpenDialog(theFile);
		   
		   
	       if (retval == JFileChooser.APPROVE_OPTION) 
	       {
	    	  
	    	  File selectedFile = jfc.getSelectedFile();
	    	  System.out.println("File: " + selectedFile);
	    	  //database db = new database();
	    	  try 
	    	  {
				db.addTxtFiles(selectedFile);
	    	  } catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	    	  //System.out.println(db.search("the"));
	       }
	       adminFrame.setVisible(false);
	       initialGUI();
	       initialGUIDetails();
	   }
	   
	   if(event_input.equals("Edit Source"))
	   {
	       db.editBook(list.get(0).getText(), list.get(1).getText(), list.get(2).getText(), list.get(3).getText());
	   }
	   
	   if(event_input.equals("Submit File"))
	   {
		   //Here the file should be fed to the code that parses it
		   displayPane();
	   }
   } 
}
