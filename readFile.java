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

@SuppressWarnings({ "serial", "unused" })
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
		
		scroll.setBounds(50, 80, 334, 123);
		contentPane.add(scroll);
		
		
		JFileChooser filechooser = new JFileChooser ();
		
		JButton btnNewButton_2 = new JButton("Read CSV File");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int r = filechooser.showOpenDialog((Component)e.getSource());
				if ( r == JFileChooser.APPROVE_OPTION) {
					System.out.println(filechooser.getSelectedFile());
					Amostra A = new Amostra(filechooser.getSelectedFile().getAbsolutePath());
					textArea.setText(A.toString());
					
					
				}
			}
		});
		 
		
		JButton button_save = new JButton("Save Bayes");
		button_save.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					Amostra A = new Amostra(filechooser.getSelectedFile().getAbsolutePath());
					FileOutputStream fileOut = new FileOutputStream("/Users/mariaclaramenezes/Desktop/Amostra/BN.bin");
					ObjectOutputStream objectOut = new ObjectOutputStream( fileOut );
					
					
					Grafo A_grafo = Grafo.grafoP(A);
					Forest A_mst = MST.maximumSpanningTree(A_grafo);		
					BN B = new BN(A_mst, A,0.5);
					System.out.println(B);
	
					objectOut.writeObject(B);
					objectOut.close();
					fileOut.close();
					textArea.setText("Saved Successfully");
				}
				catch (Exception ee) {
					ee.printStackTrace();
				}
			}
		});
		button_save.setBounds(50, 215, 117, 29);
		contentPane.add(button_save);
		
		
				
		
		JButton button_read = new JButton("Read Bayes");
		button_read.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				try {
					FileInputStream fileInput;
					fileInput = new FileInputStream( new File("/Users/mariaclaramenezes/Desktop/Amostra/BN.bin"));
					ObjectInputStream oi = new ObjectInputStream(fileInput);
					BN B = (BN)oi.readObject();
					textArea.setText(B.toString());
					oi.close();
					fileInput.close();
					
				}catch(Exception ee) {
					ee.printStackTrace();
				}
			}
		});
		button_read.setBounds(267, 215, 117, 29);
		contentPane.add(button_read);
		
	
		btnNewButton_2.setBounds(50, 39, 117, 29);
		contentPane.add(btnNewButton_2);
		
		
		
		
		

	}
}
