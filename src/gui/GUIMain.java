package gui;

import input.Event;
import input.InputParser;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import output.GenerateCalendar;
import output.SortedList;

import javax.swing.JLabel;
import javax.swing.event.HyperlinkEvent;

import processor.EndTimeSorter;
import processor.KeyWordFinder;
import processor.NameSorter;
import processor.Processor;
import processor.StartTimeSorter;
import processor.TVEventFinder;


import java.awt.Button;
import java.awt.TextField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;


public class GUIMain {

	private JFrame frame;
	private List<String> inputFiles;
	private Processor processor;
	private TextField keywordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIMain window = new GUIMain();
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
	public GUIMain() {
		initialize();
		inputFiles = new ArrayList<String>();
				
		
	}
	
	public List<Event> getEvents()
	{
		List<InputParser> inputParsers = new ArrayList<InputParser>();
		for(String filename : inputFiles){
			inputParsers.add(InputParser.ParserFactory.generate(filename));
		}
		List<Event> eventList = new ArrayList<Event>();
		for(InputParser p : inputParsers){
			eventList.addAll(p.getListOfEvents());
		}
		
		return eventList;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 633, 436);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JButton btnChooseInputFile = new JButton("Load Input File");
		btnChooseInputFile.setBounds(179, 0, 171, 25);
		btnChooseInputFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser = new JFileChooser();
				// Note: source for ExampleFileFilter can be found in FileChooserDemo,
				// under the demo/jfc directory in the Java 2 SDK, Standard Edition.
				int returnVal = chooser.showOpenDialog(frame);
				if(returnVal == JFileChooser.APPROVE_OPTION) {

					inputFiles.add(chooser.getSelectedFile().getName());
				}
			}
		});
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(btnChooseInputFile);

		keywordField = new TextField();
		keywordField.setBounds(202, 90, 186, 20);
		frame.getContentPane().add(keywordField);
		
		JButton btnSortByName = new JButton("Sort By Name");
		btnSortByName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				List<Event> ev = getEvents();
				NameSorter name = new NameSorter(true);
				SortedList s = new SortedList(name.search(ev));
				s.generate();				
			}
		});
		btnSortByName.setBounds(10, 156, 202, 25);
		frame.getContentPane().add(btnSortByName);

		JButton btnKeyWordFilter = new JButton("Search for Keyword");
		btnKeyWordFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				List <String> s = new ArrayList<String> ();
				s.add(keywordField.getText());
				List<Event> ev = getEvents();
				TVEventFinder name = new TVEventFinder(s,true);
				GenerateCalendar o = new GenerateCalendar(name.search(ev));
				o.generate();
			}
		});
		btnKeyWordFilter.setBounds(10, 85, 186, 25);
		frame.getContentPane().add(btnKeyWordFilter);

		JButton btnReverseNameSort = new JButton("Reverse Name Sort");
		btnReverseNameSort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				List<Event> ev = getEvents();
				NameSorter name = new NameSorter(false);
				SortedList s = new SortedList(name.search(ev));
				s.generate();
			}
		});
		btnReverseNameSort.setBounds(12, 193, 200, 25);
		frame.getContentPane().add(btnReverseNameSort);

		JButton btnSortByStart = new JButton("Sort By Start Time");
		btnSortByStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				List<Event> ev = getEvents();
				StartTimeSorter name = new StartTimeSorter(true);
				SortedList s = new SortedList(name.search(ev));
				s.generate(); 
			}
		});
		btnSortByStart.setBounds(10, 230, 202, 25);
		frame.getContentPane().add(btnSortByStart);

		JButton btnReverseStartTime = new JButton("Reverse Start Time Sort");
		btnReverseStartTime.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				List<Event> ev = getEvents();
				StartTimeSorter name = new StartTimeSorter(false);
				SortedList s = new SortedList(name.search(ev));
				s.generate();
			}
		});
		btnReverseStartTime.setBounds(10, 269, 202, 25);
		frame.getContentPane().add(btnReverseStartTime);

		JButton btnEndTime = new JButton("Sort By End Time");
		btnEndTime.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				List<Event> ev = getEvents();
				EndTimeSorter name = new EndTimeSorter(true);
				SortedList s = new SortedList(name.search(ev));
				s.generate(); 
			}
		});
		btnEndTime.setBounds(249, 156, 213, 25);
		frame.getContentPane().add(btnEndTime);

		JButton btnReverseEndTime = new JButton("Reverse Sort By End Time");
		btnReverseEndTime.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				List<Event> ev = getEvents();
				EndTimeSorter name = new EndTimeSorter(false);
				SortedList s = new SortedList(name.search(ev));
				s.generate(); 
			}
		});
		btnReverseEndTime.setBounds(249, 193, 213, 25);
		frame.getContentPane().add(btnReverseEndTime);

		JButton btnWithoutKey = new JButton("Search Without Keyword");
		btnWithoutKey.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				List <String> s = new ArrayList<String> ();
				s.add(keywordField.getText());
				List<Event> ev = getEvents();
				TVEventFinder name = new TVEventFinder(s,false);
				GenerateCalendar o = new GenerateCalendar(name.search(ev));
				if(name.search(ev).size() >0)
					o.generate();
				else
					o.gen();
			}
		});
		btnWithoutKey.setBounds(396, 85, 235, 25);
		frame.getContentPane().add(btnWithoutKey);

		JButton btnHyper = new JButton("Preview");
		btnHyper.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				File file = new File("Calendar.html");
				try {
					Preview foo = new Preview(file.toURI().toString());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnHyper.setBounds(249, 230, 213, 25);
		frame.getContentPane().add(btnHyper);
		
			
	}
}
