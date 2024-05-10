import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class SecretFileGUI extends JFrame implements ActionListener {

    private final JButton encodeButton, decodeButton, exitButton, selectFileButton;
    private final JTextArea messageField, encodeResultMessageField = new JTextArea(), codeResultMessageField = new JTextArea();

    public SecretFileGUI(GUI_Type guiType){
        this.setLayout(new FlowLayout());
        encodeButton= new JButton("Encode new message to a file");
        encodeButton.addActionListener(this);

        decodeButton= new JButton("Decode message from a file");
        decodeButton.addActionListener(this);

        exitButton= new JButton("Exit");
        exitButton.addActionListener(this);

        selectFileButton=new JButton("Select a File");
        selectFileButton.addActionListener(this);

        messageField=new JTextArea("Enter message (Can only contain capital letters, periods, and spaces) ");

        encodeResultMessageField.setEditable(false);
        codeResultMessageField.setEditable(false);

        switch (guiType){
            case MAIN -> {
                this.add(encodeButton);
                this.add(decodeButton);
                this.add(exitButton);
                this.pack();
                this.setDefaultCloseOperation(EXIT_ON_CLOSE);

            }

            case TYPE_MESSAGE -> {
                this.setSize(500,350);
                this.add(messageField);
                this.add(selectFileButton);
                this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            }

            case CODE_RESULT -> {
                this.setSize(500,350);
                this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

            }
        }
        this.setVisible(true);
    }

    public enum GUI_Type{
        MAIN, TYPE_MESSAGE, CODE_RESULT
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        SecretFileGUI result;
        if (e.getSource()==encodeButton){
            new SecretFileGUI(GUI_Type.TYPE_MESSAGE);

        } else if (e.getSource() == decodeButton) {
            JFileChooser fileChooser= new JFileChooser();
            fileChooser.setCurrentDirectory(new File("."));
            int response= fileChooser.showOpenDialog(null);
            if (response == JFileChooser.APPROVE_OPTION) {

                File chosenFile=new File(fileChooser.getSelectedFile().getAbsolutePath());
                result=new SecretFileGUI(GUI_Type.CODE_RESULT);
                if (!isTxtFile(chosenFile)){
                    encodeResultMessageField.setText("File type is not supported. Only select .txt files");
                    result.add(encodeResultMessageField);
                    return;
                }
                Integer[] messageCode=SecretCode.encode(SecretWriter.decodeFromFile(chosenFile));
                String message= SecretCode.decode(messageCode);
                encodeResultMessageField.setText("Encode message: " + message);
                result.add(encodeResultMessageField);
                JScrollPane scrollPane= new JScrollPane(encodeResultMessageField);
                scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
                scrollPane.setPreferredSize(new Dimension(250,50));
                result.add(scrollPane);
            }

        } else if (e.getSource() == exitButton) {
            this.dispose();

        } else if (e.getSource() == selectFileButton) {
            String messageInput= messageField.getText();
            JFileChooser fileChooser= new JFileChooser();
            fileChooser.setCurrentDirectory(new File("."));
            int response= fileChooser.showOpenDialog(null);
            if (response== JFileChooser.APPROVE_OPTION){
                File chosenFile= new File(fileChooser.getSelectedFile().getAbsolutePath());
                result= new SecretFileGUI(GUI_Type.CODE_RESULT);

                if (!isTxtFile(chosenFile)){
                    encodeResultMessageField.setText("File type is not supported. Only select .txt files");
                    result.add(encodeResultMessageField);
                    return;
                }
                Integer[] messageCode= SecretCode.encode(messageInput);
                SecretWriter.encodeToFile(chosenFile,messageCode);
                encodeResultMessageField.setText("Encoded message \"" + messageInput + "\" to file " + chosenFile.getName());
                result.add(encodeResultMessageField);
                JScrollPane scrollPane= new JScrollPane(encodeResultMessageField);
                scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
                scrollPane.setPreferredSize(new Dimension(250,50));
                result.add(scrollPane);

            }


        }


    }

    private boolean isTxtFile(File file){
        return file.getName().contains(".txt");

    }
}
