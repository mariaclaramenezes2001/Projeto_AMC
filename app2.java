import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Arrays;

import javax.swing.JLabel;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class app2 extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private BN bayes;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					app2 frame = new app2();
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
	public app2() {
		setTitle("Classificador");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(56, 179, 335, 76);
		contentPane.add(textArea);
		
		
		JFileChooser filechooser = new JFileChooser ();
		JButton choose_btn = new JButton("Choose File");
		choose_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int r = filechooser.showOpenDialog((Component)e.getSource());
				if ( r == JFileChooser.APPROVE_OPTION) {
					
					System.out.println(filechooser.getSelectedFile());
					
					try {
						FileInputStream fileInput;
						fileInput = new FileInputStream( new File(filechooser.getSelectedFile().getAbsolutePath()));
						ObjectInputStream oi = new ObjectInputStream(fileInput);
						bayes = (BN)oi.readObject();
						//textArea.setText(bayes.toString());
						oi.close();
						fileInput.close();
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
					
				}
			}
		});
		choose_btn.setBounds(23, 32, 117, 29);
		contentPane.add(choose_btn);
		
		
		
		
		textField = new JTextField();
		textField.setBounds(91, 100, 267, 26);
		contentPane.add(textField);
		textField.setColumns(10);
		
		
		JButton class_btn = new JButton("Classificar");
		class_btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					
					String regex = ", ";
					String line = textField.getText();
					String[] input  = line.split(regex);
					int[] input_vec = new int[input.length];
					for (int i = 0; i < input.length; i++)
						input_vec[i] = Integer.parseInt(input[i]);	
					System.out.println(Arrays.toString(input_vec));
					textArea.setText(Arrays.toString(input_vec));
					System.out.println(BN.classifica(input_vec, bayes));
					textArea.setText("\n" + "\n" + "\t Classificação = " +String.valueOf(BN.classifica(input_vec, bayes)));
					
				}
				catch ( Exception ee){
					 textArea.setText("ERROR: Pease Insert Valid Vector( eg: 0, 0, 1)");
				}
				
			}
		});
		class_btn.setBounds(158, 138, 117, 29);
		contentPane.add(class_btn);
		
	
	}
}
