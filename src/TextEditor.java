import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

/**
     * TextEditor
     */

    public class TextEditor implements ActionListener {
        //declaring properties of TextEditor
        JFrame frame;                               //This will represent the main window of the application.
        JMenuBar menuBar;                            //This is a menu bar that will contain menus.
        JMenu file, edit;                              //These are the menus themselves, one for "File" and one for "Edit."
        JMenuItem newFile, openFile, saveFile;
        JMenuItem cut, copy, paste, selectAll, close;
        JTextArea textArea;                             // This is a text area where you can edit and display text.

        TextEditor(){
            //initialize frame
            frame = new JFrame();

            //initialize menuBar
            menuBar = new JMenuBar();
            //initialize textArea
            textArea = new JTextArea("");

            //initialize menus
            file = new JMenu("File");
            edit = new JMenu("Edit");

            //initialize file menuItems
            newFile = new JMenuItem("New File");
            //  newFile = new JMenuItem("NewFile");
            openFile = new JMenuItem("OpenFile");
            saveFile = new JMenuItem("SaveFile");

            newFile.addActionListener(this); //Action listeners are added to menu items.
                                               // This means that when a user interacts with these menu items.
                                               // The actionPerformed method will be called, and the program can respond accordingly.
            openFile.addActionListener(this);
            saveFile.addActionListener(this);


            //add items to file menu
            file.add(newFile);
            file.add(openFile);
            file.add(saveFile);

            //initialize edit menuItems
            cut = new JMenuItem("Cut");
            copy = new JMenuItem("Copy");
            paste = new JMenuItem("Paste");
            selectAll = new JMenuItem("SelectAll");
            close = new JMenuItem("Close");


            //add items to edit menu
            edit.add(cut);
            edit.add(copy);
            edit.add(paste);
            edit.add(selectAll);
            edit.add(close);

            //adding actionlistner to edit menu items
            cut.addActionListener(this);
            copy.addActionListener(this);
            paste.addActionListener(this);
            selectAll.addActionListener(this);
            close.addActionListener(this);


            //add menus to menubar
            menuBar.add(file);
            menuBar.add(edit);


            // set menubar to frame
            frame.setJMenuBar(menuBar);

            // create content pane
            JPanel panel = new JPanel();
            panel.setBorder(new EmptyBorder(10,10,10,10));
            panel.setLayout(new BorderLayout(0,0));
            //add textarea to panel

            panel.add(textArea, BorderLayout.CENTER);

            //create scroll pane
            JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

            // add scrollpanel to panel
            panel.add(scrollPane);

            //ADD PANEL TO FRAME
            frame.add(panel);


            //set dimensions
            frame.setBounds(100, 100, 800, 400);
            frame.setTitle("Sapna's NotePad");
            frame.setVisible(true);
            frame.setLayout(null);

        }
        @Override
        public void actionPerformed(ActionEvent actionEvent){ //The actionPerformed method is triggered when a user interacts with one of the menu items
            if( actionEvent.getSource   () == cut){
                textArea.cut();
            }
            if(actionEvent .getSource() == copy){  //This action copies the selected text from the text area.
                textArea.copy();
            }
            if(actionEvent .getSource() == paste){
                textArea.paste();
            }
            if(actionEvent .getSource() == selectAll){
                textArea.selectAll();
            }
            if(actionEvent .getSource() == close){
                System.exit(0);
            }
            if(actionEvent.getSource() == openFile) {
                //open file chooser
                JFileChooser fileChooser = new JFileChooser("C:"); //direct for the file chooser to "C:"
                int chooseOption = fileChooser.showOpenDialog(null);        //line displays the file chooser dialog to the user.
                if (chooseOption == JFileChooser.APPROVE_OPTION) {                 //checks if the user clicked the "Open"
                    File file = fileChooser.getSelectedFile();                     //gets the selected file from the file chooser.
                    String filePath = file.getPath();                               // retrieves the path of the selected file.
                    try {
                        FileReader fileReader = new FileReader(filePath);       //The code reads the contents of the selected file using a FileReader
                        BufferedReader bufferedReader = new BufferedReader(fileReader); // and BufferedReader to read it line by line.
                        String intermediate = "", output = "";                          //used to read and store the file contents.
                        while ((intermediate = bufferedReader.readLine()) != null) { //This loop reads each line of the file until
                                                                                    // there are no more lines
                            output += intermediate + "\n";
                        }
                        textArea.setText(output);                            //displays the contents of the selected file in the GUI.
                        }
                    catch (IOException fileNotFoundException) {           // If any exceptions occur during the file reading process
                        fileNotFoundException.printStackTrace();            // (e.g., the file doesn't exist or there is an error reading it),
                                                                            // the code catches and prints the exception stack trace
                        // this code allows the user to open a file through a file chooser dialog and displays its contents
                        // in a GUI text area when the user selects and approves the file.
                    }

                }
            }
            if(actionEvent.getSource() ==saveFile){
                JFileChooser fileChooser = new JFileChooser("C:");
                int chooseOption = fileChooser.showSaveDialog(null);
                if (chooseOption == JFileChooser.APPROVE_OPTION) {  // check if its clicked on "save button"
                    File file = new File(fileChooser.getSelectedFile().getAbsolutePath()+".txt");
                    try{
                        FileWriter fileWriter = new FileWriter(file); // initialize filewriter
                        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);// initialize bufferedWriter
                        textArea.write(bufferedWriter);
                        bufferedWriter.close();
                    }
                    catch(IOException ioException){
                        ioException.printStackTrace();
                    }

                }
            }
            if(actionEvent.getSource() == newFile){
                TextEditor textEditor = new TextEditor();
            }

        }

        public static void main (String[]args){
            TextEditor textEditor = new TextEditor();
        }

    }



