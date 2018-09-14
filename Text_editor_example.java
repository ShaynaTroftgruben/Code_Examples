import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.File;//to open and write to the file -st
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;//to open and write to the file -st
import java.io.LineNumberReader;
import java.awt.Font;
import java.util.Random;

public class GUI {

	private JFrame frame;
	private JLabel inFileLBL;
	private JLabel outFileLBL;
	private JTextField inFileTXT;
	private JTextField outFileTXT;
	private JButton inFileBTN;
	private JButton outFileBTN;
	private JButton runBTN;
	private JTextField wordsProcessedLBL;
	private JTextField numLinesLBL;
	private JTextField numBlankLinesLBL;
	private JTextField avgWordLineLBL;
	private JTextField avgLineLengthLBL;
	private JTextField spacesAddedLBL;
	private JRadioButton rightAlign;
	private JRadioButton leftAlign;
	private JRadioButton middleAlign;
	private JRadioButton singleSpace;
	private JRadioButton doubleSpace;
	private JTextField spacesAddLBL;
	private JTextField lineLengthTXT;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
	}

	public static final String WORDS_PROCESSED = "Number of words processed: ";
	public static final String NUM_LINES = "Number of lines: ";
	public static final String BLANK_LINES = "Number of blank lines removed: ";
	public static final String WORDS_PER_LINE = "Average words per line: ";
	public static final String LINE_LENGTH = "Average line length: ";
	public static final String ADDED_SPACES = "Number of spaces added: ";
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 18));
		frame.setBounds(449, 793, 449, 854);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		inFileLBL = new JLabel("Input File:");
		inFileLBL.setFont(new Font("Tahoma", Font.PLAIN, 18));
		inFileLBL.setBounds(10, 28, 99, 25);
		frame.getContentPane().add(inFileLBL);

		inFileTXT = new JTextField();
		inFileTXT.setBounds(115, 18, 183, 35);
		frame.getContentPane().add(inFileTXT);
		inFileTXT.setColumns(10);

		inFileBTN = new JButton("Browse...");
		inFileBTN.setBounds(304, 16, 108, 35);
		inFileBTN.setFont(new Font("Tahoma", Font.PLAIN, 18));
		frame.getContentPane().add(inFileBTN);

		inFileBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JFileChooser inFile = new JFileChooser();

				inFile.setFileSelectionMode(JFileChooser.FILES_ONLY);

				inFile.setAcceptAllFileFilterUsed(true);

				int rVal = inFile.showOpenDialog(null);
				if (rVal == JFileChooser.APPROVE_OPTION) {
					inFileTXT.setText(inFile.getSelectedFile().toString());
				}

			}

		});

		outFileLBL = new JLabel("Output File:");
		outFileLBL.setFont(new Font("Tahoma", Font.PLAIN, 18));
		outFileLBL.setBounds(10, 73, 99, 25);
		frame.getContentPane().add(outFileLBL);

		outFileTXT = new JTextField();
		outFileTXT.setBounds(115, 65, 183, 35);
		frame.getContentPane().add(outFileTXT);
		outFileTXT.setColumns(10);

		outFileBTN = new JButton("Browse...");
		outFileBTN.setBounds(304, 67, 108, 35);
		outFileBTN.setFont(new Font("Tahoma", Font.PLAIN, 18));
		frame.getContentPane().add(outFileBTN);

		outFileBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JFileChooser inFile = new JFileChooser();

				inFile.setFileSelectionMode(JFileChooser.FILES_ONLY);

				inFile.setAcceptAllFileFilterUsed(false);

				int rVal = inFile.showOpenDialog(null);
				if (rVal == JFileChooser.APPROVE_OPTION) {
					outFileTXT.setText(inFile.getSelectedFile().toString());
				}

			}
		});
		
		JLabel alignmentLBL = new JLabel("Choose Alignment:");
		alignmentLBL.setFont(new Font("Tahoma", Font.PLAIN, 18));
		alignmentLBL.setBounds(15, 134, 191, 20);
		frame.getContentPane().add(alignmentLBL);

		leftAlign = new JRadioButton("Align Left");
		leftAlign.setBounds(15, 166, 133, 25);
		leftAlign.setMnemonic(KeyEvent.VK_L);
		leftAlign.setSelected(true);
		leftAlign.setFont(new Font("Tahoma", Font.PLAIN, 18));
		frame.getContentPane().add(leftAlign);
		
		middleAlign = new JRadioButton("Full Justification");
		middleAlign.setBounds(145, 166, 133, 25);
		middleAlign.setMnemonic(KeyEvent.VK_L);
		middleAlign.setSelected(true);
		middleAlign.setFont(new Font("Tahoma", Font.PLAIN, 18));
		frame.getContentPane().add(middleAlign);

		rightAlign = new JRadioButton("Align Right");
		rightAlign.setBounds(288, 166, 133, 25);
		rightAlign.setMnemonic(KeyEvent.VK_R);
		rightAlign.setFont(new Font("Tahoma", Font.PLAIN, 18));
		frame.getContentPane().add(rightAlign);

		ButtonGroup alignGroup = new ButtonGroup();
		alignGroup.add(leftAlign);
		alignGroup.add(rightAlign);
		alignGroup.add(middleAlign);
		
		JLabel spacingLBL = new JLabel("Choose Spacing:");
		spacingLBL.setFont(new Font("Tahoma", Font.PLAIN, 18));
		spacingLBL.setBounds(15, 229, 145, 20);
		frame.getContentPane().add(spacingLBL);
		
		singleSpace = new JRadioButton("Single Spaced");
		singleSpace.setBounds(10, 261, 145, 25);
		singleSpace.setMnemonic(KeyEvent.VK_R);
		singleSpace.setFont(new Font("Tahoma", Font.PLAIN, 18));
		frame.getContentPane().add(singleSpace);
		
		doubleSpace = new JRadioButton("Double Spaced");
		doubleSpace.setBounds(162, 261, 161, 25);
		doubleSpace.setMnemonic(KeyEvent.VK_R);
		doubleSpace.setFont(new Font("Tahoma", Font.PLAIN, 18));
		frame.getContentPane().add(doubleSpace);
		
		ButtonGroup spaceGroup = new ButtonGroup();
		spaceGroup.add(singleSpace);
		spaceGroup.add(doubleSpace);
		
		runBTN = new JButton("Run");
		runBTN.setBounds(304, 325, 108, 57);
		runBTN.setFont(new Font("Tahoma", Font.PLAIN, 18));
		frame.getContentPane().add(runBTN);		

		JLabel lineLengthLBL = new JLabel("Choose Line Length:");
		lineLengthLBL.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lineLengthLBL.setBounds(10, 336, 167, 35);
		frame.getContentPane().add(lineLengthLBL);
		
		lineLengthTXT = new JTextField();
		lineLengthTXT.setBounds(192, 341, 86, 26);
		frame.getContentPane().add(lineLengthTXT);
		lineLengthTXT.setColumns(10);

		wordsProcessedLBL = new JTextField(WORDS_PROCESSED);
		wordsProcessedLBL.setBounds(10, 412, 399, 57);
		wordsProcessedLBL.setFont(new Font("Tahoma", Font.PLAIN, 18));
		frame.getContentPane().add(wordsProcessedLBL);

		numLinesLBL = new JTextField(NUM_LINES);
		numLinesLBL.setBounds(10, 475, 399, 57);
		numLinesLBL.setFont(new Font("Tahoma", Font.PLAIN, 18));
		frame.getContentPane().add(numLinesLBL);

		numBlankLinesLBL = new JTextField(BLANK_LINES);
		numBlankLinesLBL.setBounds(10, 538, 399, 57);
		numBlankLinesLBL.setFont(new Font("Tahoma", Font.PLAIN, 18));
		frame.getContentPane().add(numBlankLinesLBL);

		avgWordLineLBL = new JTextField(WORDS_PER_LINE);
		avgWordLineLBL.setBounds(10, 601, 399, 57);
		avgWordLineLBL.setFont(new Font("Tahoma", Font.PLAIN, 18));
		frame.getContentPane().add(avgWordLineLBL);

		avgLineLengthLBL = new JTextField(LINE_LENGTH);
		avgLineLengthLBL.setBounds(10, 664, 399, 57);
		avgLineLengthLBL.setFont(new Font("Tahoma", Font.PLAIN, 18));
		frame.getContentPane().add(avgLineLengthLBL);
		
		spacesAddLBL = new JTextField(ADDED_SPACES);
		spacesAddLBL.setFont(new Font("Tahoma", Font.PLAIN, 18));
		spacesAddLBL.setBounds(10, 725, 399, 57);
		frame.getContentPane().add(spacesAddLBL);

		runBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (!inFileTXT.getText().toLowerCase().endsWith(".txt")) {
					JOptionPane.showMessageDialog(frame, "Input is not a Text File!", "Error",
							JOptionPane.ERROR_MESSAGE);
				} else if (!outFileTXT.getText().toLowerCase().endsWith(".txt")) {
					JOptionPane.showMessageDialog(frame, "Output is not a Text File!", "Error",
							JOptionPane.ERROR_MESSAGE);
				} else if (outFileTXT.getText().toLowerCase().equals(inFileTXT.getText().toLowerCase())) {
					JOptionPane.showMessageDialog(frame, "Output cannot be the same as Input!", "Error",
							JOptionPane.ERROR_MESSAGE);
				} else {
					
					String outputfile = outFileTXT.getText();
					BufferedWriter writer = null;
					String infile = inFileTXT.getText();
					
					int counter = 0;
					int lineCount = 0;
					int blankLineCounterOriginal = 0;
					int blankLineCounterFinal =0;
					int totalCharactersWritten = 0;
					int addedSpaces = 0;
					int spaceCounter = 0; 	//Total space added variable
				    int lineLength = 80;		//Variable to store inputed custom line length
					
					try {
						lineLength = Integer.parseInt(lineLengthTXT.getText());
					}
					catch (IllegalArgumentException ee){
						ee.printStackTrace();
						
					}
					
					File f = new File(outputfile);
					List<String> lines = new LinkedList<String>();
					int longestString = 0;

					// determine the number of blank lines removed
					Scanner fileIn;
					try {
						fileIn = new Scanner(new File(infile));
						while (fileIn.hasNext()) {
							String line = fileIn.nextLine();
							if (line.length() == 0) {
								blankLineCounterOriginal++;
							}
						}
						fileIn.close();

					} catch (FileNotFoundException e4) {
						// TODO Auto-generated catch block
						e4.printStackTrace();
					}

					// read everything into a linked list
					List<String> s = new LinkedList<String>();
					try {
						fileIn = new Scanner(new File(infile));
						while (fileIn.hasNext()) {
							String word = fileIn.next();
							s.add(word);
							counter++;
						}
						fileIn.close();

					} catch (FileNotFoundException e4) {
						// TODO Auto-generated catch block
						e4.printStackTrace();
					}

					// writer = new BufferedWriter(new FileWriter(f));
					Iterator<String> i = s.iterator();
					String currentLine = "";

					while (i.hasNext()) {
						String currentWord = i.next().toString();
						if (currentLine.length() + currentWord.length() <= lineLength) {
							currentLine = currentLine + currentWord + " ";
						} else 
						{
							currentLine.trim();
							// writer.write(currentLine + "\n");
							if(doubleSpace.isSelected())
								{
									lines.add(currentLine + "\n\n");
									lineCount++;
								}
							else
								{
									lines.add(currentLine + "\n");
								}
							
							if(currentLine.length()>longestString)
							{
								longestString = currentLine.length();
							}
							totalCharactersWritten = totalCharactersWritten + currentLine.length();
							lineCount++;
							currentLine = currentWord + " ";
						}
					}
					// flush the rest
					if (currentLine.length() > 0) {
						// writer.write(currentLine + "\n");
						lines.add(currentLine + "\n");
						if(currentLine.length()>longestString)
						{
							longestString = currentLine.length();
						}
						totalCharactersWritten = totalCharactersWritten + currentLine.length();
						lineCount++;
					}

					// writer.close();

					try {
						writer = new BufferedWriter(new FileWriter(f));
						Iterator<String> j = lines.iterator();
						while(j.hasNext())
						{
							if(leftAlign.isSelected())
							{
								writer.write(j.next());
							}
							else if(rightAlign.isSelected())
							{
								String lineToWrite = j.next();
								lineToWrite.trim();
								String spacesToWrite = "";
								int numberSpaces = longestString - lineToWrite.length();
								for(int k = 0; k<=numberSpaces; k++)
								{
									spacesToWrite = spacesToWrite + " ";
									addedSpaces++;
								}
								writer.write(spacesToWrite + lineToWrite);
							}
							else if(middleAlign.isSelected())
							{
								String lineToWrite = j.next();
								lineToWrite = lineToWrite.trim();
								int spaces =0;
								int spacesToAdd = 0;
								Random rand = new Random();
								int r = 0;
								for(int k = 0; k <= lineToWrite.length()-1;k++)
								{
									if(lineToWrite.charAt(k) == ' ' )
									{
										spaces++;
									}
								}
								if(spaces == 0)
								{
									spacesToAdd = 0;
								}
								else
								{
								
									
									int l = lineToWrite.length();
									spacesToAdd = lineLength-l;
									addedSpaces = addedSpaces + spacesToAdd;
								}
								//loop number of spaces missing
								for(int n = 1; n<=spacesToAdd;n++)
								{
									if(spaces == 1)
									{
										r = 1;
									}
									else
									{
									  r = rand.nextInt(spaces - 1) + 1;
									}
									
									int countSpaces = 0;
									int currentIndex = 0;
									while( currentIndex<=lineToWrite.length()-1)
									{
										//finding a space
										if(lineToWrite.charAt(currentIndex)== ' ')
										{
											countSpaces++;
											//the space we want, moving index two to account for space added
											if(countSpaces == r)
											{
												String s1 = lineToWrite.substring(0, currentIndex + 1);
												String s2 = lineToWrite.substring(currentIndex+1, lineToWrite.length());
												
												lineToWrite = s1 + ' ' + s2;
												break;
											}
											//not the space we want
											else
											{
												while(lineToWrite.charAt(currentIndex)== ' ')
												{
													currentIndex++;
												}
												
												
											}
											
										}
										//finding a normal character
										else 
										{
											currentIndex++;
										}
										
									}
									//writer.write(r);
								}
								if(doubleSpace.isSelected())
								{
									writer.write(lineToWrite + "\n\n");
								}
								else
								{
									writer.write(lineToWrite + "\n");
								}
								
							}
						
						}
						writer.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					Scanner fileInTwo;
					try {
						fileInTwo = new Scanner(new File(outputfile));
						while (fileInTwo.hasNext()) {
							String line = fileInTwo.nextLine();
							if (line.length() == 0) {
								blankLineCounterFinal++;
							}
						}
						fileInTwo.close();

					} catch (FileNotFoundException e4) {
						// TODO Auto-generated catch block
						e4.printStackTrace();
					}

					wordsProcessedLBL.setText(WORDS_PROCESSED + Integer.toString(counter));
					numLinesLBL.setText(NUM_LINES + Integer.toString(lineCount));
					numBlankLinesLBL.setText(BLANK_LINES + Integer.toString(blankLineCounterOriginal- blankLineCounterFinal));
					avgWordLineLBL.setText(WORDS_PER_LINE + Integer.toString(s.size() / lineCount));
					avgLineLengthLBL.setText(LINE_LENGTH + Integer.toString(totalCharactersWritten / lineCount));
					spacesAddLBL.setText(ADDED_SPACES + Integer.toString(addedSpaces));
				}

			}
		});

	}
}