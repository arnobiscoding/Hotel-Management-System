import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;

class HotelManagementSystem11 {
    FileWriter out = null;
    int updateIndex=-1;
    private Entry[] allEntries = new Entry[100];
    static int a = 0, count=0, foundPosition;
    JFrame frame;
    JPanel p1,p2,p3,p4,p5,p6;
    JLabel label, welcomeLabel, label1, label2, label3, label4, label5, nameLabel, passwordLabel;
    JTextField nameTextField, tf1, tf2, tf3, tf4, tf5;
    JPasswordField passwordTextField;
    JButton button, b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, bkb1, upb2, deb3, seb4, sab5;
    private String name = "", password = "";

    HotelManagementSystem11() {
        String name = "", password = "";
        frame = new JFrame("Continental Hotel Management System");
        frame.setSize(1080, 750);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);

        // labels
        nameLabel = new JLabel("Name: ");
        nameLabel.setFont(new Font("Serif", Font.BOLD, 18));
        nameLabel.setBounds(300, 200, 100, 100);
        passwordLabel = new JLabel("Password: ");
        passwordLabel.setFont(new Font("Serif", Font.BOLD, 18));
        passwordLabel.setBounds(300, 250, 100, 100);

        label = new JLabel();
        welcomeLabel = new JLabel();
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        label4 = new JLabel();
        label5 = new JLabel();

        // TextFields
        nameTextField = new JTextField();
        nameTextField.setBounds(400, 225, 300, 50);
        nameTextField.setFont(new Font("Serif", Font.BOLD, 18));
        passwordTextField = new JPasswordField();
        passwordTextField.setBounds(400, 280, 300, 50);
        passwordTextField.setFont(new Font("Serif", Font.BOLD, 18));
        tf1 = new JTextField();
        tf1.setSize(300, 50);
        tf1.setFont(new Font("Serif", Font.BOLD, 18));
        tf2 = new JTextField();
        tf2.setSize(300, 50);
        tf2.setFont(new Font("Serif", Font.BOLD, 18));
        tf3 = new JTextField();
        tf3.setSize(300, 50);
        tf3.setFont(new Font("Serif", Font.BOLD, 18));
        tf4 = new JTextField();
        tf4.setSize(300, 50);
        tf4.setFont(new Font("Serif", Font.BOLD, 18));
        tf5 = new JTextField();
        tf5.setSize(300, 50);
        tf5.setFont(new Font("Serif", Font.BOLD, 18));

        // Buttons
        button = new JButton("Login");
        button.setBounds(450, 400, 200, 80);
        button.setFont(new Font("Serif", Font.BOLD, 18));
        b1 = new JButton();
        b2 = new JButton();
        b3 = new JButton();
        b4 = new JButton();
        b5 = new JButton();
        b6 = new JButton();
        b7 = new JButton();
        b8 = new JButton();
        b9 = new JButton();
        b10 = new JButton();
        bkb1 = new JButton();
        upb2 = new JButton();
        deb3 = new JButton();
        seb4 = new JButton();
        sab5 = new JButton();


        bkb1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                a=1;
                panelHandler();
            }
        });

        upb2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                a=2;
                panelHandler();
            }
        });

        deb3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                a=3;
                panelHandler();
            }
        });

        seb4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                a=4;
                panelHandler();
            }
        });

        sab5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                a=5;
                panelHandler();
            }
        });

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                a = 1;
                panelHandler();
            }
        });

        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                a = 0;
                panelHandler();
            }
        });

        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {     //book room
                // Check if any of the fields is empty
                if (tf1.getText().isEmpty() || tf2.getText().isEmpty() || tf3.getText().isEmpty() ||
                        tf4.getText().isEmpty() || tf5.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please fill in all fields.");
                    return; // Exit the method without booking
                }
                if(dateFormatChecker(tf4.getText(),tf5.getText())==0){
                    JOptionPane.showMessageDialog(frame, "The entered date is not valid");
                    return;
                }
                if(checkDate(tf4.getText(),tf5.getText())==0){
                    JOptionPane.showMessageDialog(frame, "The entered date is not valid");
                    return;
                }


                // Proceed with booking if all fields are filled
                try {
                    b3.setEnabled(false);
                    String n = tf1.getText(), p = tf2.getText(), r = tf3.getText(), i = tf4.getText(), o = tf5.getText();
                    allEntries[count++] = new Entry(n, p, r, i, o);
                    out = new FileWriter("Records.txt", true);
                    out.write("Name: " + n + "\n");
                    out.write("Phone: " + p + "\n");
                    out.write("Room: " + r + "\n");
                    out.write("Check in: " + i + "\n");
                    out.write("Check out: " + o + "\n");
                    out.write("----------------------------------" + "\n");
                    out.close();
                    System.out.println("Successfully wrote to the file.");
                    a = 0;
                    JOptionPane.showMessageDialog(frame, "Room booked successfully");
                    recorder();
                    writeEntriesToFile();
                    panelHandler();

                } catch (IOException ioe) {
                    System.out.println("An error occurred.");
                    ioe.printStackTrace();
                    panelHandler();
                    b3.setEnabled(true);
                }
            }
        });



       b4.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {              //search
               String searchName = tf1.getText();
               String searchPhone = tf2.getText();
               String searchRoom = tf3.getText();
               String searchIn = tf4.getText();
               String searchOut = tf5.getText();

               searchFunction(searchName,searchPhone,searchRoom,searchIn,searchOut);

           }
       });

        b5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                a=5;
                panelHandler();
            }
        });

        b6.addActionListener(new ActionListener() {             // Search function for delete
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement search logic
                String searchName = tf1.getText();
                String searchPhone = tf2.getText();
                String searchRoom = tf3.getText();
                String searchIn = tf4.getText();
                String searchOut = tf5.getText();
                int i = searchFunction(searchName,searchPhone,searchRoom,searchIn,searchOut);

                if(i!=-1){
                    deleteEntryAtIndex(i);
                    JOptionPane.showMessageDialog(frame, "Entry deleted");
                }
            }
        });

        b7.addActionListener(new ActionListener() {             // Search function for update
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement search logic
                String searchName = tf1.getText();
                String searchPhone = tf2.getText();
                String searchRoom = tf3.getText();
                String searchIn = tf4.getText();
                String searchOut = tf5.getText();
                updateIndex = searchFunction(searchName,searchPhone,searchRoom,searchIn,searchOut);

                if(updateIndex!=-1){
                    p6.remove(b7);
                    b8.setText("Update");
                    b8.setFont(new Font("Serif", Font.BOLD, 18));
                    b8.setBounds(250, 550 + 25, 200, 80);
                    p6.add(b8);
                    b8.setEnabled(true);
                    frame.revalidate();
                    frame.repaint();
                }
            }
        });

        b8.addActionListener(new ActionListener() {             // Update function
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.revalidate();
                frame.repaint();
                String n = tf1.getText();
                String p = tf2.getText();
                String r = tf3.getText();
                String i = tf4.getText();
                String o = tf5.getText();

                allEntries[updateIndex].name = n;
                allEntries[updateIndex].phone = p;
                allEntries[updateIndex].room = r;
                allEntries[updateIndex].in = i;
                allEntries[updateIndex].out = o;
                writeEntriesToFile();
                recorder();
                b8.setEnabled(false);
                JOptionPane.showMessageDialog(frame, "Entry updated successfully");
            }
        });

        frame.add(nameLabel);
        frame.add(nameTextField);
        frame.add(passwordLabel);
        frame.add(passwordTextField);
        frame.add(button);
        frame.setVisible(true);

        b10.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                a = 0;
                panelHandler();
            }
        });


        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputName = nameTextField.getText();
                String inputPassword = new String(passwordTextField.getPassword());

                if (inputName.equals(name) && inputPassword.equals(password)) {
                    JOptionPane.showMessageDialog(frame, "Login Successful");
                    a = 0;
                    panelHandler();
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid username or password. Please try again");
                }
            }
        });
    }

    void panelHandler(){

        recorder();

        switch (a) {
            case 0:
                //Welcome Screen Option Panel
                frame.getContentPane().removeAll();
                recorder();
                p4 = new ImagePanel("panel.jpg");
                p4.setSize(1080,730);
                p1 = new JPanel();
                frame.add(welcomeLabel);
                p1.add(bkb1);
                p1.add(upb2);
                p1.add(deb3);
                p1.add(seb4);
                p1.add(sab5);

                welcomeLabel.setText("Welcome to the Continental Hotel Management System");
                welcomeLabel.setForeground(Color.WHITE);
                welcomeLabel.setFont(new Font("Serif", Font.BOLD, 30));
                welcomeLabel.setBounds(200, 200, 700, 50);

                bkb1.setText("Book room");
                upb2.setText("Update entry");
                deb3.setText("Delete entry");
                seb4.setText("Search entry");
                sab5.setText("Show all");

                bkb1.setFont(new Font("Serif", Font.BOLD, 18));
                upb2.setFont(new Font("Serif", Font.BOLD, 18));
                deb3.setFont(new Font("Serif", Font.BOLD, 18));
                seb4.setFont(new Font("Serif", Font.BOLD, 18));
                sab5.setFont(new Font("Serif", Font.BOLD, 18));

                frame.add(p1);
                p1.setBackground(new Color(0,0,0,0));
                frame.add(p4);

                p1.setLayout(new FlowLayout(FlowLayout.CENTER));
                p1.setBounds(0, 350, 1080, 200);

                frame.revalidate();
                frame.repaint();
                break;

            case 1:
                frame.getContentPane().removeAll();   // book room
                recorder();
                p2 = new JPanel();
                p2.setLayout(null);
                label.setText("Please enter the following information");
                label.setFont(new Font("Serif", Font.BOLD, 18));
                label.setBounds(400, 10, 600, 50);
                label1.setText("Name: ");
                label1.setFont(new Font("Serif", Font.BOLD, 18));
                label2.setFont(new Font("Serif", Font.BOLD, 18));
                label3.setFont(new Font("Serif", Font.BOLD, 18));
                label4.setFont(new Font("Serif", Font.BOLD, 18));
                label5.setFont(new Font("Serif", Font.BOLD, 18));
                label2.setText("Phone: ");
                label3.setText("Room: ");
                label4.setText("Check in: ");
                label5.setText("Check out: ");

                tf1.setBounds(200, 50 + 25, 700, 50);
                tf2.setBounds(200, 150 + 25, 700, 50);
                tf3.setBounds(200, 250 + 25, 700, 50);
                tf4.setBounds(200, 350 + 25, 700, 50);
                tf5.setBounds(200, 450 + 25, 700, 50);
                tf1.setText(null);
                tf2.setText(null);
                tf3.setText(null);
                tf4.setText(null);
                tf5.setText(null);

                label1.setBounds(50, 50, 600, 100);
                label2.setBounds(50, 150, 600, 100);
                label3.setBounds(50, 250, 600, 100);
                label4.setBounds(50, 350, 600, 100);
                label5.setBounds(50, 450, 600, 100);

                p2.add(label);
                p2.add(label1);
                p2.add(tf1);
                p2.add(label2);
                p2.add(tf2);
                p2.add(label3);
                p2.add(tf3);
                p2.add(label4);
                p2.add(tf4);
                p2.add(label5);
                p2.add(tf5);

                p2.add(b3);
                b3.setBounds(250, 550 + 25, 200, 80);
                b3.setText("Book");
                b3.setFont(new Font("Serif", Font.BOLD, 18));


                p2.add(b2);
                b2.setBounds(650, 550 + 25, 200, 80);
                b2.setText("Menu");
                b2.setFont(new Font("Serif", Font.BOLD, 18));

                frame.add(p2);
                p2.setBounds(0, 0, 1080, 750);
                p2.setVisible(true);
                frame.revalidate();
                frame.repaint();
                break;

            case 2:
                frame.getContentPane().removeAll();   //update entry
                recorder();
                p6 = new JPanel();
                p6.setLayout(null);
                label.setText("Please enter the following information");
                label.setFont(new Font("Serif", Font.BOLD, 18));
                label.setBounds(400, 10, 600, 50);
                label1.setText("Name: ");
                label1.setFont(new Font("Serif", Font.BOLD, 18));
                label2.setFont(new Font("Serif", Font.BOLD, 18));
                label3.setFont(new Font("Serif", Font.BOLD, 18));
                label4.setFont(new Font("Serif", Font.BOLD, 18));
                label5.setFont(new Font("Serif", Font.BOLD, 18));
                label2.setText("Phone: ");
                label3.setText("Room: ");
                label4.setText("Check in: ");
                label5.setText("Check out: ");

                tf1.setBounds(200, 50 + 25, 700, 50);
                tf2.setBounds(200, 150 + 25, 700, 50);
                tf3.setBounds(200, 250 + 25, 700, 50);
                tf4.setBounds(200, 350 + 25, 700, 50);
                tf5.setBounds(200, 450 + 25, 700, 50);

                tf1.setText(null);
                tf2.setText(null);
                tf3.setText(null);
                tf4.setText(null);
                tf5.setText(null);

                label1.setBounds(50, 50, 600, 100);
                label2.setBounds(50, 150, 600, 100);
                label3.setBounds(50, 250, 600, 100);
                label4.setBounds(50, 350, 600, 100);
                label5.setBounds(50, 450, 600, 100);

                p6.add(label);
                p6.add(label1);
                p6.add(tf1);
                p6.add(label2);
                p6.add(tf2);
                p6.add(label3);
                p6.add(tf3);
                p6.add(label4);
                p6.add(tf4);
                p6.add(label5);
                p6.add(tf5);

                p6.add(b7);
                b7.setBounds(250, 550 + 25, 200, 80);
                b7.setText("Search");
                b7.setFont(new Font("Serif", Font.BOLD, 18));
                p6.add(b2);
                b2.setBounds(650, 550 + 25, 200, 80);
                b2.setText("Menu");
                b2.setFont(new Font("Serif", Font.BOLD, 18));

                frame.add(p6);
                p6.setBounds(0, 0, 1080, 750);
                p6.setVisible(true);
                frame.revalidate();
                frame.repaint();
                b7.setEnabled(true);
                break;


            case 3:                     // Delete Entry
                frame.getContentPane().removeAll();
                recorder();
                p3 = new JPanel();
                p3.setLayout(null);
                label.setText("Please enter the following information");
                label.setFont(new Font("Serif", Font.BOLD, 18));
                label.setBounds(400, 10, 600, 50);
                label1.setText("Name: ");
                label1.setFont(new Font("Serif", Font.BOLD, 18));
                label2.setFont(new Font("Serif", Font.BOLD, 18));
                label3.setFont(new Font("Serif", Font.BOLD, 18));
                label4.setFont(new Font("Serif", Font.BOLD, 18));
                label5.setFont(new Font("Serif", Font.BOLD, 18));
                label2.setText("Phone: ");
                label3.setText("Room: ");
                label4.setText("Check in: ");
                label5.setText("Check out: ");

                tf1.setBounds(200, 50 + 25, 700, 50);
                tf2.setBounds(200, 150 + 25, 700, 50);
                tf3.setBounds(200, 250 + 25, 700, 50);
                tf4.setBounds(200, 350 + 25, 700, 50);
                tf5.setBounds(200, 450 + 25, 700, 50);
                tf1.setText(null);
                tf2.setText(null);
                tf3.setText(null);
                tf4.setText(null);
                tf5.setText(null);

                label1.setBounds(50, 50, 600, 100);
                label2.setBounds(50, 150, 600, 100);
                label3.setBounds(50, 250, 600, 100);
                label4.setBounds(50, 350, 600, 100);
                label5.setBounds(50, 450, 600, 100);


                p3.add(label);
                p3.add(label1);
                p3.add(tf1);
                p3.add(label2);
                p3.add(tf2);
                p3.add(label3);
                p3.add(tf3);
                p3.add(label4);
                p3.add(tf4);
                p3.add(label5);
                p3.add(tf5);

                p3.add(b6);
                b6.setBounds(250, 550 + 25, 200, 80);
                b6.setText("Delete");
                b6.setFont(new Font("Serif", Font.BOLD, 18));
                p3.add(b2);
                b2.setBounds(650, 550 + 25, 200, 80);
                b2.setText("Menu");
                b2.setFont(new Font("Serif", Font.BOLD, 18));

                frame.add(p3);
                p3.setBounds(0, 0, 1080, 750);
                p3.setVisible(true);
                frame.revalidate();
                frame.repaint();
                b6.setEnabled(true);
                break;

            case 4:                        //Search Entry
                frame.getContentPane().removeAll();
                recorder();
                p4 = new JPanel();
                p4.setLayout(null);
                label.setText("Please enter the following information");
                label.setFont(new Font("Serif", Font.BOLD, 18));
                label.setBounds(400, 10, 600, 50);
                label1.setText("Name: ");
                label1.setFont(new Font("Serif", Font.BOLD, 18));
                label2.setFont(new Font("Serif", Font.BOLD, 18));
                label3.setFont(new Font("Serif", Font.BOLD, 18));
                label4.setFont(new Font("Serif", Font.BOLD, 18));
                label5.setFont(new Font("Serif", Font.BOLD, 18));
                label2.setText("Phone: ");
                label3.setText("Room: ");
                label4.setText("Check in: ");
                label5.setText("Check out: ");

                tf1.setBounds(200, 50 + 25, 700, 50);
                tf2.setBounds(200, 150 + 25, 700, 50);
                tf3.setBounds(200, 250 + 25, 700, 50);
                tf4.setBounds(200, 350 + 25, 700, 50);
                tf5.setBounds(200, 450 + 25, 700, 50);
                tf1.setText(null);
                tf2.setText(null);
                tf3.setText(null);
                tf4.setText(null);
                tf5.setText(null);

                label1.setBounds(50, 50, 600, 100);
                label2.setBounds(50, 150, 600, 100);
                label3.setBounds(50, 250, 600, 100);
                label4.setBounds(50, 350, 600, 100);
                label5.setBounds(50, 450, 600, 100);


                p4.add(label);
                p4.add(label1);
                p4.add(tf1);
                p4.add(label2);
                p4.add(tf2);
                p4.add(label3);
                p4.add(tf3);
                p4.add(label4);
                p4.add(tf4);
                p4.add(label5);
                p4.add(tf5);

                p4.add(b4);
                b4.setBounds(250, 550 + 25, 200, 80);
                b4.setText("Search");
                b4.setFont(new Font("Serif", Font.BOLD, 18));
                p4.add(b2);
                b2.setBounds(650, 550 + 25, 200, 80);
                b2.setText("Menu");
                b2.setFont(new Font("Serif", Font.BOLD, 18));

                frame.add(p4);
                p4.setBounds(0, 0, 1080, 750);
                p4.setVisible(true);
                frame.revalidate();
                frame.repaint();
                b4.setEnabled(true);
                break;

            case 5:                          //Show all
                frame.getContentPane().removeAll();
                recorder();
                p5 = new JPanel();
                p5.setBounds(0, 0, 1080, 750);
                p5.setLayout(null);
                label.setText("Showing all entries");
                label.setFont(new Font("Serif", Font.BOLD, 18));
                label.setBounds(450, 10, 600, 50);

                JTextArea page = new JTextArea();
                page.setEditable(false); // To make it read-only
                page.setLineWrap(true);
                page.setWrapStyleWord(true);
                page.setFont(new Font("Serif", Font.BOLD, 18));

                JScrollPane scrollPane = new JScrollPane(page);
                scrollPane.setBounds(0, 70, 1070, 642);

                frame.add(p5);
                p5.add(label);
                p5.add(scrollPane);
                p5.add(b10);
                b10.setText("Menu");
                b10.setFont(new Font("Serif", Font.BOLD, 18));
                b10.setBounds(800, 10, 200, 50);
                p5.setVisible(true);
                frame.revalidate();
                frame.repaint();

                // Load contents from the file to the JTextArea
                try (BufferedReader reader = new BufferedReader(new FileReader("Records.txt"))) {
                    StringBuilder content = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        content.append(line).append("\n");
                    }
                    page.setText(content.toString());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                if (foundPosition >= 0) {
                    // Scroll to the found position
                    int maxPosition = page.getDocument().getLength();
                    page.setCaretPosition(Math.min((foundPosition + 390), maxPosition));
                }
                break;
            default:
                break;
        }
    }

    void recorder() {
        try (BufferedReader reader = new BufferedReader(new FileReader("Records.txt"))) {
            String line;
            boolean readingRecord = false; // To track whether we are currently reading a record
            String name = null, phone = null, room = null, checkin = null, checkout = null;
            count = 0; // Reset count before reading records

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Name:")) {
                    name = line.replace("Name: ", "").trim();
                } else if (line.startsWith("Phone:")) {
                    phone = line.replace("Phone: ", "").trim();
                } else if (line.startsWith("Room:")) {
                    room = line.replace("Room: ", "").trim();
                } else if (line.startsWith("Check in:")) {
                    checkin = line.replace("Check in: ", "").trim();
                } else if (line.startsWith("Check out:")) {
                    checkout = line.replace("Check out: ", "").trim();
                } else if (line.equals("----------------------------------")) {
                    // This indicates the end of a record
                    if (!readingRecord) {
                        // Process the collected data for the current record
                        allEntries[count++] = new Entry(name, phone, room, checkin, checkout);
                        // Reset variables for the next record
                        name = phone = room = checkin = checkout = null;
                        readingRecord = false;
                    }
                } else if (!line.trim().isEmpty()) {
                    // This indicates the start of a new record
                    readingRecord = true;
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    void deleteEntryAtIndex(int index) {
        if (index >= 0 && index < count) {
            // Shift elements to close the gap
            for (int i = index; i < count - 1; i++) {
                allEntries[i] = allEntries[i + 1];
            }
            allEntries[count - 1] = null;
            count--;
            writeEntriesToFile();
        }
    }

    void writeEntriesToFile() {
        try (FileWriter writer = new FileWriter("Records.txt", false)) {
            // Append the new entries to the file
            for (int i = 0; i < count; i++) {
                if (allEntries[i].name != null) {
                    writer.write("Name: " + allEntries[i].name + "\n");
                    writer.write("Phone: " + allEntries[i].phone + "\n");
                    writer.write("Room: " + allEntries[i].room + "\n");
                    writer.write("Check in: " + allEntries[i].in + "\n");
                    writer.write("Check out: " + allEntries[i].out + "\n");
                    writer.write("----------------------------------" + "\n");
                }
            }
            writer.close();
            System.out.println("Successfully wrote entries to the file.");
        } catch (IOException ex) {
            System.out.println("An error occurred while writing entries to the file.");
            ex.printStackTrace();
        }
    }

    int searchFunction(String searchName, String searchPhone, String searchRoom, String searchIn, String searchOut){
        int i;
        int flag=0; // 0 means not found, 1 means found
        for(i=0 ; i<count; i++)
        {
            if(searchName != null){
                if(searchName.equals(allEntries[i].name)){
                    JOptionPane.showMessageDialog(frame, "Entry found");
                    tf1.setText(allEntries[i].name);
                    tf2.setText(allEntries[i].phone);
                    tf3.setText(allEntries[i].room);
                    tf4.setText(allEntries[i].in);
                    tf5.setText(allEntries[i].out);
                    label.setText("Showing the target information");
                    flag = 1;
                    break;
                }
            }

            if(searchPhone != null){
                if(searchPhone.equals(allEntries[i].phone)){
                    JOptionPane.showMessageDialog(frame, "Entry found");
                    tf1.setText(allEntries[i].name);
                    tf2.setText(allEntries[i].phone);
                    tf3.setText(allEntries[i].room);
                    tf4.setText(allEntries[i].in);
                    tf5.setText(allEntries[i].out);
                    label.setText("Showing the target information");
                    flag = 1;
                    break;
                }
            }

            if(searchRoom != null){
                if(searchRoom.equals(allEntries[i].room)){
                    JOptionPane.showMessageDialog(frame, "Entry found");
                    tf1.setText(allEntries[i].name);
                    tf2.setText(allEntries[i].phone);
                    tf3.setText(allEntries[i].room);
                    tf4.setText(allEntries[i].in);
                    tf5.setText(allEntries[i].out);
                    label.setText("Showing the target information");
                    flag = 1;
                    break;
                }
            }

            if(searchIn != null){
                if(searchIn.equals(allEntries[i].in)){
                    JOptionPane.showMessageDialog(frame, "Entry found");
                    tf1.setText(allEntries[i].name);
                    tf2.setText(allEntries[i].phone);
                    tf3.setText(allEntries[i].room);
                    tf4.setText(allEntries[i].in);
                    tf5.setText(allEntries[i].out);
                    label.setText("Showing the target information");
                    flag = 1;
                    break;
                }
            }

            if(searchOut != null){
                if(searchOut.equals(allEntries[i].out)){
                    JOptionPane.showMessageDialog(frame, "Entry found");
                    tf1.setText(allEntries[i].name);
                    tf2.setText(allEntries[i].phone);
                    tf3.setText(allEntries[i].room);
                    tf4.setText(allEntries[i].in);
                    tf5.setText(allEntries[i].out);
                    label.setText("Showing the target information");
                    flag = 1;
                    break;
                }
            }
        }
        if(flag == 0){
            JOptionPane.showMessageDialog(frame, "Entry not found");
            return -1;
        }


        return i;
    }

    int checkDate(String in, String out){       //Backend Features
        String[] inDate = in.split("/");
        String[] outDate = out.split("/");

        if(Integer.parseInt(inDate[0])>Integer.parseInt(outDate[0]) ||
                Integer.parseInt(inDate[1])>Integer.parseInt(outDate[1]) ||
                Integer.parseInt(inDate[2])>Integer.parseInt(outDate[2]) ||
                Integer.parseInt(inDate[0]) == 0 ||
                Integer.parseInt(inDate[1]) == 0 ||
                Integer.parseInt(inDate[2]) == 0 ||
                Integer.parseInt(inDate[0]) <32  ||
                Integer.parseInt(inDate[1]) <13)
            return 0; // invalid date
        else
            return 1; //valid date
    }

    int dateFormatChecker(String in, String out){   //Backend Features
        int count1 = 0, count2 =0;
        char[] inArray = in.toCharArray();
        char[] outArray = out.toCharArray();

        for(char c:inArray)
        {
            if(c=='/')
                count1++;
        }
        for(char c:outArray)
        {
            if(c=='/')
                count2++;
        }

        if(count1!=2 && count2!=2)
            return 0; //invalid date format
        else
            return 1; //valid date format
    }

    public static void main(String[] args) {
        new HotelManagementSystem11();
    }
}

class Entry{
    String name, phone, room, in, out;
    Entry(String name, String phone, String room, String in, String out){
        this.name = name;
        this.phone = phone;
        this.room = room;
        this.in = in;
        this.out = out;
    }
}

class ImagePanel extends JPanel {
    private Image backgroundImage;

    public ImagePanel(String imagePath) {
        this.backgroundImage = new ImageIcon(imagePath).getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}


