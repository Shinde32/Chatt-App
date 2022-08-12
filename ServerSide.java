import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.net.*;
import java.awt.event.*;

class ServerSide extends JFrame implements ActionListener
{
    static ServerSocket server;
    static Socket conn;
    JPanel panel;
    JTextField msg;
    JTextArea chatt;
    JButton send;

    public ServerSide() throws UnknownHostException,IOException 
    {
        panel = new JPanel();
        msg = new JTextField();
        chatt = new JTextArea();
        send = new JButton("Send");
        this.setSize(500, 500);
        this.setVisible(true);
        panel.setLayout(null);
        this.add(panel);
        chatt.setBounds(20, 20, 450, 360);
        panel.add(chatt);
        msg.setBounds(20, 400, 340, 30);
        panel.add(msg);
        send.setBounds(375, 400, 95, 30);
        panel.add(send);
        this.setTitle("Prathamesh");
        send.addActionListener(this);
        server = new ServerSocket(2000, 1, InetAddress.getLocalHost());
        conn = server.accept();
        chatt.setText(chatt.getText() + '\n' + "");
        while (true) 
        {
            try 
            {
                DataInputStream din = new DataInputStream(conn.getInputStream());
                String string = din.readUTF();
                chatt.setText(chatt.getText() + '\n' + "Onkar "+ string);
            } catch (Exception e1) 
            {
                chatt.setText(chatt.getText() + '\n' + "Your Friend is OFFLINE");
                try 
                {
                    Thread.sleep(3000);
                    System.exit(0);
                } catch (InterruptedException e)
                {

                    e.printStackTrace();

                }

            }

        }

    }
    public void actionPerformed(ActionEvent e)
    {

        if ((e.getSource() == send) && (msg.getText() != "")) 
        {
            chatt.setText(chatt.getText() + '\n' + "ME:" + msg.getText());
            try {
                DataOutputStream dout = new DataOutputStream(
                conn.getOutputStream());
                dout.writeUTF(msg.getText());
            }catch (Exception e1) 
            {
                try
                {
                    Thread.sleep(3000);
                    System.exit(0);
                } catch (InterruptedException e2) {

                    e2.printStackTrace();
                }
            }
            msg.setText("");
        }
    }
    public static void main(String args[]) throws UnknownHostException,IOException
    {
        new ServerSide();
    }
}