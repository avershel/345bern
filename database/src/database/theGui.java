package gutenSearch;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class theGUI implements ActionListener {
   //Frames
   private JFrame mainFrame;
   private JFrame adminFrame;
   private JFrame userFrame;
   private JFrame displayFrame;
   
   //File Chooser
   JFileChooser jfc;
   Container contentPane;
   private JFileChooser chooseFile;
   private JFrame theFile;
   int retval;
   
   //Panels
   private JPanel controlPanel;
   
   //Labels
   private JLabel headerLabel;
   private JLabel statusLabel;

   public theGUI() 
   {
      initialGUI();
   }

   public static void main(String[] args)
   {
      theGUI gutenGUI = new theGUI();  
      gutenGUI.initialGUIDetails();     
   }
   
   //Initial GUI #1
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
   
   //Initial GUI #2
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
      
      //START CHOICES FOR ADMIN OR USER
      
      JComboBox listCombo = new JComboBox();
      listCombo.addItem("Admin");
      listCombo.addItem("User");
      listCombo.setSelectedIndex(0);
      listCombo.addActionListener(this);
	  
      JScrollPane listComboScrollPane = new JScrollPane(listCombo); 
      
      //END CHOICES FOR ADMIN OR USER
      
      //SUBMIT & CANCEL
      JPanel buttonPanel = new JPanel();
      
      JButton submitButton = new JButton("Submit");
      buttonPanel.add(submitButton);
      
      JButton cancelButton = new JButton("Cancel");
      buttonPanel.add(cancelButton);  
      
      submitButton.addActionListener(this);
      cancelButton.addActionListener(this);


//      submitButton.addActionListener(new ActionListener() 
//      {
//          public void actionPerformed(ActionEvent event) 
//          {
//              JComboBox listCombo = (JComboBox) event.getSource();
//              
//              String something = event.getActionCommand();
//              
//              //Object selected = listCombo.getSelectedItem();
//              
//              if(something.equals("Admin"))
//              {
//            	  mainFrame.setVisible(false);
//            	  adminGUI();
//              }
//              else if(something.equals("User"))
//              {
//            	  mainFrame.setVisible(false);
//            	  userGUI();
//              }
//          }
//      });
      
      //END SUBMIT & CANCEL

      choicePanel.add(buttonPanel);

      controlPanel.add(listComboScrollPane);
	  controlPanel.add(choicePanel);

      mainFrame.setVisible(true);  

   }
   
   //-----------------------------------------------------------
   private void adminGUI()
   {
	   adminFrame = new JFrame("Admin"); 
	   adminFrame.setSize(400,400);
	   adminFrame.setLayout(new GridLayout(3, 1));	   
	   
	   headerLabel = new JLabel("",JLabel.CENTER);
	   	   
       JPanel adminButtonPanel = new JPanel();
      
       JButton chooseFile = new JButton("Choose File");
       adminButtonPanel.add(chooseFile);
       chooseFile.addActionListener(this);
      
      
       //JPanel textBoxPanel = new JPanel(new FlowLayout());
       //textBoxPanel.add(new JLabel("File:" ));
   	   //textBoxPanel.add(new JTextField(20));
   	   
      
       JButton submitFile = new JButton("Submit File");
       adminButtonPanel.add(submitFile);
       submitFile.addActionListener(this);
	  
       adminFrame.add(adminButtonPanel);
       //adminFrame.add(textBoxPanel);
      
	   adminFrame.setVisible(true);
   }
   
   private void adminGUIdetails()
   {
	   JPanel textBoxPanel = new JPanel(new FlowLayout());
	      
	   textBoxPanel.add(new JLabel("Keywords:"));
	   textBoxPanel.add(new JTextField(20));
   }
   //------------------------------------------------------------
   
   private void displayPane()
   {
	   displayFrame = new JFrame("Output");
	   displayFrame.setSize(400,400);

	   
   }
   
   //-----------------------------------------------------------
   private void userGUI()
   {
	   userFrame = new JFrame("User");
	   userFrame.setSize(400,400);
	   userFrame.setLayout(new BorderLayout());
	      
	   headerLabel = new JLabel("",JLabel.CENTER);
	  
	   userFrame.setVisible(true);
	   userGUIdetails();
   }
   
   private void userGUIdetails()
   {
	   JPanel searchLabelPanel = new JPanel(new FlowLayout());
	   JLabel searchLabel = new JLabel("Enter Search Terms");
	   JPanel searchPanel = new JPanel(new GridLayout(2,2));
	   final database db = new database();
	   
	   final JTextField searchText = new JTextField(15);
	   final JTextArea resultText = new JTextArea();
	   resultText.setEditable(false);
	   
	   
	   searchLabelPanel.add(searchLabel);
	   searchPanel.add(searchLabelPanel);
	   searchPanel.add(searchText);
	   
	   JPanel resultPanel = new JPanel(new BorderLayout());
	   
	   JLabel resultLabel = new JLabel("Results");
	   
	   resultPanel.add(resultLabel, BorderLayout.NORTH);
	   resultPanel.add(resultText, BorderLayout.CENTER);
	   
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
	   
	   JPanel textBoxPanel = new JPanel(new BorderLayout());
	   textBoxPanel.add(searchPanel, BorderLayout.NORTH);
	   textBoxPanel.add(resultPanel, BorderLayout.CENTER);
	   userFrame.add(textBoxPanel, BorderLayout.CENTER);
	   userFrame.add(clearSearch, BorderLayout.SOUTH);
	   
   }
   @Override
   public void actionPerformed(ActionEvent e) {
	   String event_input;
	   String file_name;
	   event_input =  e.getActionCommand();
	   System.out.println("event_input is: \n" + event_input);
	   
	   //Works
	   if(event_input.equals("Submit"))
	   {
		   /*if(event_input.equals("Admin"))
		   {
			   mainFrame.setVisible(false);
			   adminGUI();
		   }
		   if(event_input.equals("User"))
		   {
			   mainFrame.setVisible(false);
			   userGUI();
		   }*/
		   System.out.println("Admin was selected\n");
		   mainFrame.setVisible(false);
		   userGUI();
	   }
	   //Done
	   if(event_input.equals("Cancel"))
	   {
		   mainFrame.setVisible(false);
	   }
	   
	   if(event_input.equals("Choose File"))
	   {
		   jfc = new JFileChooser();
		   retval = jfc.showOpenDialog(theFile);
		   
		   
	       if (retval == JFileChooser.APPROVE_OPTION) 
	       {
	    	  
	    	  File selectedFile = jfc.getSelectedFile();
	    	  System.out.println("File: " + selectedFile);
	    	  database db = new database();
	    	  try {
				db.addTxtFiles(selectedFile);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	    	  System.out.println(db.search("the"));
	    	  //file_name = selectedFile.getName();
	    	  //JLabel theName = new JLabel();
	    	  //theName.setText(file_name);
	          //JPanel textBoxPanel = new JPanel();
	          //textBoxPanel.add(new JLabel("File:" + selectedFile));
	      	  //textBoxPanel.add(new JTextField(20));	  
	      	  //System.out.print(theName);
	       }
	       
	       //JTextArea text = new JTextArea();
	       
	   }
	   
	   if(event_input.equals("Submit File"))
	   {
		   //Here the file should be fed to the code that parses it
		   displayPane();
		   
	   }
	   /*
	      showButton.addActionListener(new ActionListener() 
	      {
	         public void actionPerformed(ActionEvent e) 
	         { 
	            String data = "";
	            if (listCombo.getSelectedIndex() != -1) 
	            {  
	               CardLayout cardLayout = (CardLayout)(choicePanel.getLayout());
	               cardLayout.show(choicePanel, 
	               (String)listCombo.getItemAt(listCombo.getSelectedIndex()));	               
	            }              
	            statusLabel.setText(data);
	         }
	      }); 
	      */
	   
   	
   }


   
}
