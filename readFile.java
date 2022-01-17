import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class readFile extends JFrame {

	private JPanel contentPane;
	Amostra A;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					readFile frame = new readFile();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public readFile() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JTextArea textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setBounds(50, 111, 334, 37);
		contentPane.add(textArea);
		
		JScrollPane scroll = new JScrollPane(textArea,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		scroll.setBounds(50, 111, 334, 37);
		contentPane.add(scroll);
		
		
		
		JButton button_save = new JButton("Save Amostra");
		button_save.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileOutputStream fileOut;
				try {
					fileOut = new FileOutputStream("/Users/mariaclaramenezes/Desktop/Amostra/Amostra.bin");
					ObjectOutputStream objectOut = new ObjectOutputStream( fileOut);
					
					objectOut.writeObject(A);
					objectOut.close();
					fileOut.close();
					textArea.setText("Saved Successfully");
				}
				catch (Exception ee) {
					ee.printStackTrace();
				}
			}
		});
		button_save.setBounds(50, 194, 117, 29);
		contentPane.add(button_save);
		
		
				
		
		JButton button_read = new JButton("Read Amostra");
		button_read.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileInputStream fileInput;
				
				try {
					fileInput = new FileInputStream( new File("/Users/mariaclaramenezes/Desktop/Amostra/Amostra.bin"));
					ObjectInputStream oi = new ObjectInputStream(fileInput);
					Amostra B = (Amostra)oi.readObject();
					textArea.setText(B.toString());
					oi.close();
					fileInput.close();
					
				}catch(Exception ee) {
					ee.printStackTrace();
				}
			}
		});
		button_read.setBounds(266, 194, 117, 29);
		contentPane.add(button_read);
		
		
		JFileChooser filechooser = new JFileChooser ();
		
		JButton btnNewButton_2 = new JButton("Read CVS File");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int r = filechooser.showOpenDialog((Component)e.getSource());
				if ( r == filechooser.APPROVE_OPTION) {
					System.out.println(filechooser.getSelectedFile());
					Amostra A = new Amostra(filechooser.getSelectedFile().getAbsolutePath());
					textArea.setText(A.toString());
					
					
				}
			}
		});
		btnNewButton_2.setBounds(50, 39, 117, 29);
		contentPane.add(btnNewButton_2);
		
		
		
		
		

	}
}
