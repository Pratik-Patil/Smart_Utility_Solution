// Pratik Softwares
package pratik.applications;
import java.io.*;
import java.util.*;
import java.awt.*;
import javax.swing.*;
import javax.imageio.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.event.*;
import javax.swing.border.*;
import java.awt.datatransfer.*;
public class PratikApplications extends JPanel implements ActionListener
{
	public static void main(String...Pratik) 
	{
                new PratikApplications();
        }
        public PratikApplications() 
	{
		super(new BorderLayout());
		String s2="<HTML>Pratik Softwares<BR><BR>Select Your Favourite";
		JFrame f=new JFrame("Pratik Softwares (Copyright Protected)"); 
		f.setDefaultCloseOperation(3);
		f.setVisible(true); 
		img = new ImageIcon(getClass().getResource("PratikImg/z.jpg")).getImage(); 
		Dimension dm = new Dimension(img.getWidth(null), img.getHeight(null));
		d=Toolkit.getDefaultToolkit().getScreenSize(); 
		setPreferredSize(dm);
		f.setLocation(d.width/4,d.height/4); 
		JLabel jl=new JLabel(s2); 
		jl.setFont(new Font("Monotype Corsiva",0,60)); 
		jl.setForeground(new Color(255,0,155)); 
		add(jl,BorderLayout.NORTH);
		JPanel P=new JPanel(new GridLayout(2,3));
		for(int i=0;i<6;i++)
		{
			b[i]=new JButton(s[i]); 
			b[i].addActionListener(this); 
			b[i].setBackground(mycolor[i]); 
			b[i].setFont(new Font("TIMES NEW ROMAN",3,25)); 
			P.add(b[i]); 
		}
		add(P,BorderLayout.SOUTH); 
		f.add(this); 
		f.setSize(d.width/2,d.height/2); 
		f.setResizable(false);
    	}
    	public void actionPerformed(ActionEvent e)
    	{
    		Object source=e.getSource(); 
    		String command="";
 	  	if(source==b[0]) 
 	  	{
 	  		new JavaPad();
		}
 	  	if(source==b[1]) 
 	  	{	
 	  		new JCalculator();
		}
 	  	if(source==b[2]) 
 	  	{	
 	  		new JPaint();
		}
 	  	if(source==b[3]) 
 	  	{	
 	  		new AnalogClock();
		}
 	  	if(source==b[4]) 
 	  	{	
 	  		new ScrollBarMagic();
		}
 	  	if(source==b[5]) 
 	  	{	
 	  		new BouncingBall();
		}
   	}
    	public void paintComponent(Graphics g) 
    	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(img, 0, 0,d.width/2,d.height/2, null);
    	}
    	Image img ; 
    	JButton b[]=new JButton[6]; 
    	Dimension d;
    	String s[]={"JavaPad","JCalculator","JPaint","AnalogClock","ScrollBarMagic","BouncingBall"};
    	Color mycolor[]={Color.CYAN,Color.GREEN,Color.MAGENTA,Color.YELLOW,Color.PINK,Color.ORANGE};
 }
class JavaPad extends JPanel
{
	public static void main(String...Pratik)
	{
		new JavaPad();
	}
	public JavaPad()
	{
		super(new GridLayout(1,1)); 
		tx = new JTextArea();
		f=new JFrame("UnTitled"+s); 
		add(new JScrollPane(tx));
		tx.setFont(new Font(f_name,f_style,f_size));
		
		JMenuBar mb = new JMenuBar(); 
		JMenu FM = new JMenu("File");
		JMenuItem n = new JMenuItem("New"); 
			FM.add(n);
		JMenuItem o = new JMenuItem("Open"); 
			FM.add(o);
		JMenuItem s = new JMenuItem("Save"); 
			FM.add(s);
		JMenuItem e = new JMenuItem("Exit"); 
			FM.add(e);
			mb.add(FM);
		
		JMenu EM = new JMenu("Edit");
		JMenuItem cut = new JMenuItem("Cut"); 
			EM.add(cut);
		JMenuItem copy = new JMenuItem("Copy"); 
			EM.add(copy);
		JMenuItem paste = new JMenuItem("Paste"); 
			EM.add(paste);
		JMenuItem selectALL = new JMenuItem("Select All"); 
			EM.add(selectALL);
			mb.add(EM);
		
		JMenu Format = new JMenu("Format");
		word=new JCheckBoxMenuItem("Word Wrap",false); 
			Format.add(word);
		text_color=new JMenuItem("Set Text Color"); 
			Format.add(text_color); 
		pad_color=new JMenuItem("Set Pad Color"); 
			Format.add(pad_color);
			mb.add(Format);
		
		JMenu help_menu=new JMenu("Help");
		help=new JMenuItem("About JavaPad");
			help_menu.add(help); 
			mb.add(help_menu); 

		mb.add(new JLabel("              Select Font__"));
		cb=new JComboBox<String>(face); 
		cb.setSelectedIndex(149); 
			mb.add(cb);
		
		bold=new JCheckBox("Bold"); 
			mb.add(bold);
		italic=new JCheckBox("Italic"); 
			mb.add(italic);
				
		size_button=new JButton("Set Font Size"); 
			mb.add(size_button); 
		
		f.setJMenuBar(mb);
		// Registration of Components with their respective listeners
		n.addActionListener(new New());	
		o.addActionListener(new Open());
		s.addActionListener(new Save());	
		e.addActionListener(new Exit());
		cut.addActionListener(new Cut()); 
		copy.addActionListener(new Copy());
		paste.addActionListener(new Paste()); 
		selectALL.addActionListener(new SelectAll());
		text_color.addActionListener(new FormatSet()); 
		pad_color.addActionListener(new FormatSet()); 
		help.addActionListener(new FormatSet()); 
		bold.addActionListener(new FontSet()); 
		italic.addActionListener(new FontSet()); 
		cb.addActionListener(new FontSet());
		size_button.addActionListener(new FontSet()); 
		word.addItemListener(new FormatSet());
				
		f.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				int result=JOptionPane.showConfirmDialog((Component)null,
				"Save File?","Select Yes or No",JOptionPane.YES_NO_OPTION);
				if(result==JOptionPane.YES_OPTION)
				{
					FileDialog fd = new FileDialog((Dialog)null,"Save File",FileDialog.SAVE);
					fd.setVisible(true); Save s=new Save();
					if (fd.getFile()!=null)
					{
						filename=fd.getDirectory()+fd.getFile(); s.WriteFile(); 
						JOptionPane.showMessageDialog(null,"File is Saved");
					}
				}
			}//windowClosing() method closed
		}); /* addWindowListener() method closed */ 
		f.setVisible(true); 
		f.add(this); 
		f.setSize(700,500);
	}
	class FontSet implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			f_name=(String)cb.getSelectedItem(); 
			f_style=0;
			if(e.getSource()==bold||e.getSource()==italic)
			{	
				if(bold.isSelected()) 
				{	
					f_style=Font.BOLD;
				}
				if(italic.isSelected()) 
				{	
					f_style=Font.ITALIC;
				}
				if(bold.isSelected() && italic.isSelected())
				{	
					f_style=Font.BOLD+Font.ITALIC;
				}
			}
			if(e.getSource()==size_button)
			{
				try
				{
					f_size=Integer.parseInt(JOptionPane.showInputDialog("Enter Font Size"));
				}
				catch(Exception Pratik_Patil){}
			}
			tx.setFont(new Font(f_name,f_style,f_size)); 
		}
	}// FontSet closed
	class FormatSet implements ActionListener, ItemListener
	{
		public void itemStateChanged(ItemEvent e1) 
		{
			tx.setLineWrap(word.getState());
		}			
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource()==text_color)
			{
				Color c1=JColorChooser.showDialog(JavaPad.this,"Select Text Color",Color.BLACK);
				tx.setForeground(c1);
			}	
			if(e.getSource()==pad_color)
			{
				Color c2=JColorChooser.showDialog(JavaPad.this,"Select Pad Color",Color.WHITE);
				tx.setBackground(c2); 
			}
			if(e.getSource()==help)
			{
				JOptionPane.showMessageDialog(JavaPad.this,s1,s2,JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}// FormatSet closed
	class New implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			tx.setText(" "); 
			f.setTitle("UnTitled"+s);
		}
	}
	class Open implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			FileDialog fd = new FileDialog((Dialog)null, "Select File",FileDialog.LOAD);
			fd.setVisible(true);
			if (fd.getFile()!=null)
			{
				filename=fd.getDirectory()+fd.getFile(); 
				f.setTitle(filename+s); 
				ReadFile();
			}
			tx.requestFocus();
		}
		void ReadFile()
		{
			StringBuffer sb = new StringBuffer();
			try(Scanner sc = new Scanner(new File(filename));)
			{
				String line="";
				while(sc.hasNextLine()) 
				{
					line=sc.nextLine(); 
					sb.append(line + "\n");
				} 
				tx.setText(sb.toString()); 
			}
			catch(Exception Pratik){}
		} // ReadFile() method closed
	}// Open closed
	class Save implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			FileDialog fd = new FileDialog((Dialog)null,"Save File",FileDialog.SAVE); 
			fd.setVisible(true);
			if (fd.getFile()!=null) 
			{
				filename=fd.getDirectory()+fd.getFile(); 
				WriteFile();
			}
		}
		void WriteFile()
		{
			try(DataOutputStream d = new DataOutputStream(new FileOutputStream(filename));)
			{
				f.setTitle(filename+s); 
				String line = tx.getText(); 
				Scanner br = new Scanner(line);
				while(br.hasNextLine())
				{
					line = br.nextLine(); 
					d.writeBytes(line + "\r\n");
				}
			}
			catch(Exception Pratik){} 
			tx.requestFocus();
		}
	}// Save closed
	class Exit implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			f.dispose();
		}
	}
	class Cut implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			String sel = tx.getSelectedText();
			StringSelection ss = new StringSelection(sel); 
			clip.setContents(ss,ss);
			tx.replaceRange(" ",tx.getSelectionStart(),tx.getSelectionEnd());
		}
	}
	class Copy implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			String sel = tx.getSelectedText();
			StringSelection clipString = new StringSelection(sel);
			clip.setContents(clipString,clipString);
		}
	}
	class Paste implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			Transferable cliptran = clip.getContents(JavaPad.this);
			try
			{
				String sel=(String)cliptran.getTransferData(DataFlavor.stringFlavor);
				tx.replaceRange(sel,tx.getSelectionStart(),tx.getSelectionEnd());
			}
			catch(Exception Pratik){}
		}
	} // paste closed
	class SelectAll implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			tx.selectAll();
		}
	}// SelectAll closed
	// Declaration of Required Fields
	JCheckBox bold,italic; 
	JButton size_button; 
	JComboBox<String>cb; 
	JFrame f;
	JTextArea tx; 
	JMenuItem text_color, pad_color, help; 
	int f_style=0, f_size=25; 
	Clipboard clip = getToolkit().getSystemClipboard(); 
	JCheckBoxMenuItem word; 
	String filename, s="  - JavaPad_by_Pratik.Patil", 
	f_name="TIMES NEW ROMAN",
	s1="<html><big>JavaPad by Pratik_Patil</big><hr><hr>"
		+"Your Comments as well as bug reports are very welcome..<p align=center><br>"
		+"<strong>For any Kind of help & Information</strong><br>Kindly Contact at<br><br>"
		+"<hr><em><big>pratik05051991@gmail.com</big></em><hr><html>"
		+"<strong>Thanx for using Javapad</strong><br>",
	s2="Dedicated to Prof. K. D. Sonawane..!",
	face[]=GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
} // JavaPad closed
class JCalculator extends JFrame implements ActionListener
{
	public static void main(String...Pratik)
	{
		new JCalculator();
	}
	public JCalculator()
	{
		setResizable(false); 
		setVisible(true);  
		setTitle("Java Calculator by Pratik_Patil"); 
		setLayout(new BorderLayout()); 
		add(t1,"North"); t1.setText("0"); 
		JPanel CalculatorPanel=new JPanel(new GridLayout(4,4));
		for(int i=0;i<16;i++)
		{
			imagefile=Image_Directory+Image_Name[i]+Image_Extension;
			image_icon[i]=new ImageIcon(getClass().getResource(imagefile));
			b[i]=new JButton(Button_Label[i],image_icon[i]); 
			b[i].setFont(new Font("Arial",1,1));
			CalculatorPanel.add(b[i]); 
			b[i].addActionListener(this);
		}
		add(CalculatorPanel,"Center"); 
		setSize(525,525);
	}
	public void actionPerformed(ActionEvent e)
	{
		String key=e.getActionCommand(); 
		current_value=Integer.parseInt(t1.getText());
		if(key.equals("Clear"))
		{
			t1.setText("0"); 
			previous_value=0; 
			current_value=0; 
			result=0; 
			operator="";
		}
		else if(key.equals("="))
		{
			result=0;
			if(operator=="+")		
			{
				result=previous_value+current_value;
			}
			else if(operator=="-")	
			{
				result=previous_value-current_value;
			}
			else if(operator=="*")	
			{
				result=previous_value*current_value;
			}
			else if(operator=="/")	
			{
				result=previous_value/current_value;
			}
			t1.setText(String.valueOf(result));
		}
		else if(key.equals("+")||key.equals("-")||key.equals("*")||key.equals("/"))
		{
			previous_value=current_value; 
			operator=key; 
			t1.setText("0");
		}
		else 
		{
			t1.setText(String.valueOf(current_value*10+Integer.parseInt(key)));
		}
	}
	// Declaration of Required Fields
	int result=0,previous_value=0,current_value=0; 
	JButton b[]=new JButton[16];
	ImageIcon image_icon[]=new ImageIcon[16];  
	JTextField t1=new JTextField(10);
	String Image_Directory ="PratikImg/", Image_Extension=".jpg", imagefile,
	Button_Label[]={"0","1","2","3","4","5","6","7","8","9", "+","-","*","/","=","Clear"},operator="",
	Image_Name[]={"0","1","2","3","4","5","6","7","8","9","plus", "minus","mult","div","equal","clear"};
}
class JPaint extends JPanel implements MouseListener, MouseMotionListener, ActionListener
{  		
	public static void main(String...Pratik)
	{
		new JPaint();
	}
	public JPaint()  // Constructor
	{	
		JPanel main_panel=new JPanel(new BorderLayout());
		Border matte=new MatteBorder(5, 5, 5, 5, Color.GREEN);
		JFrame f=new JFrame("Paint Application"); 
		Dimension d=Toolkit.getDefaultToolkit().getScreenSize(); 
		int w=d.width, h=d.height-30;
		String title="Paint Application By PRATIK PATIL";
		main_panel.setBorder(BorderFactory.createTitledBorder(matte,title)); 
		f.setVisible(true);
				
		setLayout(new BorderLayout()); 
		center_panel = new JPanel();
		center_panel.setBackground(Color.BLACK); 
		add(center_panel);
		south_panel= new Panel(); 
		south_panel.setBackground(Color.PINK);
		cords=new JLabel("Co-Ordinates   "+String.valueOf(x)+" , "+String.valueOf(y)); 
		south_panel.add(cords); 
		JLabel select = new JLabel("   Select Tool"); 
		south_panel.add(select);
		// ComboBox Section
		shapes = new JComboBox<String>(myshape);
		shapes.addActionListener(this); 
		south_panel.add(shapes);
		// RadioButton Section	
		ButtonGroup group= new ButtonGroup();
		draw = new JRadioButton("Draw",true);
		fill = new JRadioButton("Fill");
		eraser = new JRadioButton("Eraser");
		group.add(draw);
		group.add(fill); 
		group.add(eraser);
		draw.addActionListener(this); 
		south_panel.add(draw); 
		fill.addActionListener(this); 
		south_panel.add(fill);
		eraser.addActionListener(this); 
		south_panel.add(eraser);
		// CheckBox Section
		CB=new JCheckBox("XOR Paint Mode");
		south_panel.add(CB); 
		CB.addActionListener(this);
		// Drawing Text
		ds=new JButton("Insert Text"); 
		south_panel.add(ds); 
		ds.addActionListener(this);
		 // west_panel with Color Selection Buttons
		west_panel=new Panel(new GridLayout(10,1));
		JLabel j=new JLabel("Select Color",JLabel.CENTER); 
		west_panel.add(j);
		String mycolor[]={"Red","Green","Blue","Yellow","Cyan","Orange","Magenta","White","My Favourite"};
		Color c1[]={Color.RED,Color.GREEN,Color.BLUE,Color.YELLOW,Color.CYAN,
					Color.ORANGE,Color.MAGENTA,Color.WHITE,Color.RED};
		for(int i=0;i<9;i++)
		{
			but[i+2]=new JButton(mycolor[i]); 
			west_panel.add(but[i+2]);
			but[i+2].setBackground(c1[i]); 
			but[i+2].addActionListener(this);
		}
		add(west_panel,"West");
		// Clear All & Save Drawing Button
		but[0]=new JButton("Clear All"); 
		south_panel.add(but[0]);
		but[1]=new JButton("Save Drawing & Exit"); 
		south_panel.add(but[1]);
		but[0].addActionListener(this); 
		but[1].addActionListener(this);
		add(south_panel,"South"); 
		center_panel.addMouseMotionListener(this);
		center_panel.addMouseListener(this); 
		south_panel.addMouseListener(this);
		// JSlider Section for Thickness Variation
		JSlider slider=new JSlider(2,17,2);	
		slider.setPaintTicks(true); 
		slider.setSnapToTicks(true); 
		slider.setMajorTickSpacing(5); 
		south_panel.add(new JLabel("Thickness")); 
		south_panel.add(slider); 
		ChangeListener slider_action=new ChangeListener()
		{
			public void stateChanged(ChangeEvent Pratik)
			{
				JSlider jsl=(JSlider)Pratik.getSource(); 
				width=jsl.getValue(); 
				paint(center_panel.getGraphics());
			}
		};
		slider.addChangeListener(slider_action);
		// Set BufferSize & Initialize BufferedImage	
		Dimension ScreenSize=Toolkit.getDefaultToolkit().getScreenSize(); 
		setSize(ScreenSize);
		bf = new BufferedImage(this.getWidth(),this.getHeight()-35,BufferedImage.TYPE_INT_RGB);
		// Set cursorimage of center_panel
		cursor=new Cursor(Cursor.CROSSHAIR_CURSOR);
		center_panel.setCursor(cursor); 
		main_panel.add(this); 
		f.setContentPane(main_panel); 
		f.setSize(w,h); 
		validate();
	}
	public void paint(Graphics g)
	{
		w=this.getWidth(); 
		h=this.getHeight();
		if(clear)  // Clear Screen Code
		{
			g.setColor(Color.BLACK); 
			g.fillRect(0, 0, w, h);
			bf = new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB); 
		}
		else // Code to restore the Previous Drawing
		{
			try
			{
				if(bf.getHeight()!=h-35 || bf.getWidth()!= w)
				{
					r=bf.getData(); 
					bf=new BufferedImage(w,h-35,BufferedImage.TYPE_INT_RGB);
					bf.getGraphics().setColor(Color.BLACK); 
					bf.getGraphics().fillRect(0, 0, w, h); 
					bf.setData(r);
				}
				draw(bf.getGraphics()); 
				g.drawImage(bf, 0, 0, null);
			}
			catch(Exception Pratik){}
		}
	}
	public void draw(Graphics g) throws Exception
	{
		int x3=Math.abs(x2-x1), y3=Math.abs(y2-y1);
		Graphics2D g1=(Graphics2D)g; 
		g1.setStroke(new BasicStroke(width)); 
		if(!point && !eraser.isSelected())
		{
			g.setColor(Color.BLACK); 
			g.fillRect(0, 0, this.getWidth(), this.getWidth()); 
			bf.setData(r);
		}
		else
		{
			r=bf.getData(); 
		}
		if(CB.isSelected() && !eraser.isSelected()&&!point&&!line) 
		{
			g.setXORMode(c); 
		}
		else 
		{	
			g.setColor(c);
		}
		if(fix)
		{
			g.setFont(new Font("TIMES NEW ROMAN",0,f_size)); 
			g.drawString(text,x,y); 
		}
		if(draw.isSelected())
		{
			if(circle)
			{
				if(x1<x2 && y1<y2) 		
				{
					g.drawOval(x1, y1, x3, y3);
				}
				else if(x1>x2 && y1> y2) 	
				{	
					g.drawOval(x2, y2, x3, y3);
				}
				else if (y1<y2)			
				{	
					g.drawOval(x2, y1, x3, y3);
				}
				else if (y1>y2)			
				{	
					g.drawOval(x1, y2, x3, y3);
				}
			}
			else if(rect)
			{
				if(x1<x2 && y1<y2)		
				{
					g.drawRect(x1, y1, x3, y3);
				}
				else if(x1>x2 && y1> y2)	
				{
					g.drawRect(x2, y2, x3, y3);
				}
				else if (y1<y2)			
				{
					g.drawRect(x2, y1, x3, y3);
				}
				else if (y1>y2)			
				{
					g.drawRect(x1, y2, x3, y3);
				}
			}
			else if(roundrect)
			{
				if(x1<x2 && y1<y2)		
				{
					g.drawRoundRect(x1, y1, x3, y3,20,20);
				}
				else if(x1>x2 && y1> y2)	
				{
					g.drawRoundRect(x2, y2, x3, y3,20,20);
				}
				else if (y1<y2)			
				{
					g.drawRoundRect(x2, y1, x3, y3,20,20);
				}
				else if (y1>y2)			
				{
					g.drawRoundRect(x1, y2, x3, y3,20,20);
				}
			}
			else 
			{
				g.drawLine(x1,y1, x2,y2 ); 
			}
		}
		else if(fill.isSelected())
		{			
			if(circle)
			{
				if(x1<x2 && y1<y2)		
				{
					g.fillOval(x1, y1, x3, y3);
				}
				else if(x1>x2 && y1> y2)	
				{
					g.fillOval(x2, y2, x3, y3);
				}
				else if (y1<y2)			
				{
					g.fillOval(x2, y1, x3, y3);
				}
				else if (y1>y2)			
				{
					g.fillOval(x1, y2, x3, y3);
				}
			}
			else if(rect)
			{
				if(x1<x2 && y1<y2)		
				{
					g.fillRect(x1, y1, x3, y3);
				}
				else if(x1>x2 && y1> y2)	
				{
					g.fillRect(x2, y2, x3, y3);
				}
				else if (y1<y2)			
				{
					g.fillRect(x2, y1, x3, y3);
				}
				else if (y1>y2)			
				{
					g.fillRect(x1, y2, x3, y3);
				}
			}
			else if(roundrect)
			{
				if(x1<x2 && y1<y2)		
				{	
					g.fillRoundRect(x1, y1, x3, y3,20,20);
				}
				else if(x1>x2 && y1> y2)	
				{
					g.fillRoundRect(x2, y2, x3, y3,20,20);
				}
				else if (y1<y2)			
				{
					g.fillRoundRect(x2, y1, x3, y3,20,20);
				}
				else if (y1>y2)			
				{
					g.fillRoundRect(x1, y2, x3, y3,20,20);
				}
			}
			else 
			{
				g.drawLine(x1,y1, x2,y2 );
			}
		}//end if for fill option selected
		else if(eraser.isSelected())
		{
			g.setColor(Color.BLACK); 
			g.fillRect(x2-15,y2-15,30,30);
		}
		if(point) 
		{
			x1=x2; 
			y1=y2; 
		} 
	}//end of draw
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource()==ds) // Insert Text
		{
			boolean b=true; 
			x=0; 
			y=0;
			try
			{
				fix=true; 
				text=JOptionPane.showInputDialog("Enter the Text: ");
			}
			catch(Exception Pratik)
			{
				b=false;
			}
			try
			{
				if(b&&text!=null)
				{
					f_size=Integer.parseInt(JOptionPane.showInputDialog("Enter Font Size: "));
				}
			}
			catch(Exception Patil)
			{
				b=false;
			}
			try
			{
				if(b&&text!=null) 
				{
					x=Integer.parseInt(JOptionPane.showInputDialog("Enter X-CoOrdinate: "));
				}
			}
			catch(Exception Pratik)
			{
				b=false;
			}
			try
			{
				if(b&&text!=null) 
				{
					y=Integer.parseInt(JOptionPane.showInputDialog("Enter Y-CoOrdinate: "));
				}
			}
			catch(Exception Pratik){}
			paint(center_panel.getGraphics()); 
			r=bf.getData(); 
			fix=false; 
			paint(center_panel.getGraphics());
		}
		if(e.getSource()==but[0]) // Clear All
		{
			clear=true; 
			paint(center_panel.getGraphics()); 
			clear=false;
		}
		if(e.getSource()==but[2])
		{ 
			c=Color.RED;
		}
		if(e.getSource()==but[3])
		{
			c=Color.GREEN;
		}
		if(e.getSource()==but[4])
		{
			c=Color.BLUE;
		}
		if(e.getSource()==but[5])
		{
			c=Color.YELLOW;
		}
		if(e.getSource()==but[6])
		{
			c=Color.CYAN;
		}
		if(e.getSource()==but[7])
		{
			c=Color.ORANGE;
		}
		if(e.getSource()==but[8])
		{
			c=Color.MAGENTA;
		}
		if(e.getSource()==but[9])
		{
			c=Color.WHITE;
		}
		if(e.getSource()==but[10]) // My Favourite Color
		{
			c=JColorChooser.showDialog(JPaint.this,"Select Any Color",c); 
		}
		but[10].setBackground(c);
		if(e.getSource()==but[1]) // Save Drawing & Exit
		{
			FileDialog fd = new FileDialog((Dialog)null,"Save File ",FileDialog.SAVE);
			fd.setVisible(true); 
			String filename=fd.getDirectory()+fd.getFile();
			if(fd.getFile()!=null)
			{
				try
				{
					FileOutputStream out=new FileOutputStream(filename);
					ImageIO.write(bf, "gif", out);
					JOptionPane.showMessageDialog(null,"Drawing is Saved To File "+fd.getFile());
					JOptionPane.showMessageDialog(null,"Now the Application will be Terminated"); 
					System.exit(0);
				}
				catch(Exception Pratik){}
			}
		}
		if(e.getSource()==shapes)
		{
			if(shapes.getSelectedItem()==myshape[0])
			{
				line=false; 
				circle=false; 
				rect=false; 
				roundrect=false;
				point=true;
			}
			if(shapes.getSelectedItem()==myshape[1])
			{
				point=false; 
				circle=false; 
				rect=false; 
				roundrect=false;
				line=true;
			}
			if(shapes.getSelectedItem()==myshape[2])
			{
				point=false; 
				line=false; 
				rect=false; 
				roundrect=false;
				circle=true;
			}
			if(shapes.getSelectedItem()==myshape[3])
			{
				point=false; 
				line=false; 
				circle=false; 
				roundrect=false;
				rect=true;
			}
			if(shapes.getSelectedItem()==myshape[4])
			{
				point=false; 
				line=false; 
				circle=false; 
				rect=false; 
				roundrect=true;
			}
		}//end of if statement for shapes
		if(e.getSource()==eraser)
		{
			cursorimage = new BufferedImage(30,30,BufferedImage.TYPE_INT_RGB);
			cursorimage.getGraphics().drawRect(0, 0, 30, 30); 
			Toolkit t = Toolkit.getDefaultToolkit();
			cursor = t.createCustomCursor(cursorimage, new Point(15,15), "mycursor");
		}
		if(e.getSource()==fill || e.getSource()==draw) 
		{
			cursor = new Cursor( Cursor.CROSSHAIR_CURSOR); 
		}
		center_panel.requestFocus();
	}
	public void mouseEntered(MouseEvent me) 
	{
		if(me.getSource()==south_panel)
		{
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}
		if(me.getSource()==center_panel)
		{
			center_panel.setCursor(cursor);
		}
	}
	public void mouseDragged(MouseEvent me) 
	{
		x2=me.getX(); 
		y2= me.getY(); 
		paint(center_panel.getGraphics());
		cords.setText("Co-Ordinates   "+String.valueOf(me.getX())+" , "+String.valueOf(me.getY()));
	}
	public void mousePressed(MouseEvent me) 
	{
		x1=me.getX(); 
		y1= me.getY(); 
		x2=x1; 
		y2=y1; 
		paint(center_panel.getGraphics());
	}
	public void mouseMoved(MouseEvent e) 
	{
		cords.setText("Co-Ordinates   "+String.valueOf(e.getX())+" , "+String.valueOf(e.getY()));
	} 
	public void mouseReleased(MouseEvent e)
	{
		if(!point)
		{
			r=bf.getData();
		}
	}
	public void mouseClicked(MouseEvent e) {}
	public void mouseExited(MouseEvent me) {}
	//  Declaration of Required Fields
	String myshape[]={"Point","Line","Circle","Rectangle","Round Rect"},text;
	JButton but[]=new JButton[11], ds; 
	JRadioButton fill, draw, eraser; Cursor cursor;
	BufferedImage bf, cursorimage; 
	JPanel center_panel; 
	Panel south_panel, west_panel; 
	boolean point=true,circle,line, rect ,roundrect, clear,fix; 
	Color c=Color.RED; 
	JCheckBox CB;
	Raster r; 
	int x=0,y=0,x1,y1,x2,y2,w,h,width=2,f_size; 
	JComboBox<String> shapes; 
	JLabel cords;
}
class AnalogClock extends JPanel
{
	public static void main(String...Pratik) 
	{
		new AnalogClock();
	}
	public AnalogClock() 
	{
		JFrame f=new JFrame("Analog Clock... BY PRATIK PATIL");
		f.setVisible(true); 
		setPreferredSize(new Dimension(450,450));
		ActionListener listener=new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				repaint();
			}
		};
		new  javax.swing.Timer(1000,listener).start();
		f.setBackground(Color.yellow); 
		f.setResizable(false); 
		f.add(this); 
		f.pack(); 
	}
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g); 
		Graphics2D g2 = (Graphics2D)g; 
		g2.setColor(Color.white);
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		int w = getWidth(), h = getHeight(); 
		size = ((w<h) ? w : h) - 2*spacing;
		centerX = size/2 + spacing; 
		centerY = size/2 + spacing;
		if (clockImage == null || clockImage.getWidth() != w|| clockImage.getHeight() != h)
		{
			clockImage = (BufferedImage)(this.createImage(w, h));
			Graphics2D gc = clockImage.createGraphics();
			gc.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
			RenderingHints.VALUE_ANTIALIAS_ON); 
			drawClockFace(gc);
			gc.setFont(new Font("Monotype Corsiva",0,30)); 
			gc.drawString("Pratik Times",150,110);
		}
		Calendar now = Calendar.getInstance(); 
		hours = now.get(10); 
		minutes = now.get(12); 
		seconds = now.get(13); 
		millis = now.get(14); 
		g2.drawImage(clockImage, null, 0, 0); 
		drawClockHands(g);
	}
	public void drawClockHands(Graphics g) 
	{
		int secondRadius = size/2, minuteRadius = secondRadius * 3/4, hourRadius = secondRadius/2;
		float fseconds = seconds + (float)millis/1000, secondAngle = threePi - (radPerSecMin * fseconds);
		drawRadius(g, centerX, centerY, secondAngle, 0, secondRadius);
		float fminutes = (float)(minutes + fseconds/60.0), minuteAngle = threePi - (radPerSecMin * fminutes);
		drawRadius(g, centerX, centerY, minuteAngle, 0, minuteRadius);
		float fhours = (float)(hours + fminutes/60.0), hourAngle = threePi - (5 * radPerSecMin * fhours);
		drawRadius(g, centerX, centerY, hourAngle, 0, hourRadius);
	}
	public void drawClockFace(Graphics g)
	{
		g.setColor(Color.black); 
		g.fillOval(spacing, spacing, size, size);
		g.setColor(Color.white); 
		g.drawOval(spacing, spacing, size, size);
		for (int sec = 0; sec<60; sec++)
		{
			int ticStart; 
			if (sec%5 == 0)
			{
				ticStart = size/2-10;
			}
			else
			{
				ticStart = size/2-5;
			}
			drawRadius(g, centerX, centerY, radPerSecMin*sec, ticStart , size/2);
		}
	}
	public void drawRadius(Graphics g, int x, int y, double angle, int minRadius, int maxRadius)
	{
		float sine = (float)Math.sin(angle), cosine = (float)Math.cos(angle);
		int dxmin = (int)(minRadius * sine), dymin = (int)(minRadius * cosine);
		int dxmax = (int)(maxRadius * sine), dymax = (int)(maxRadius * cosine);
		g.drawLine(x+dxmin, y+dymin, x+dxmax, y+dymax);
	}
	BufferedImage clockImage;
	int hours=0,minutes=0,seconds=0,millis=0,spacing=10,size,centerX, centerY;
	float twoPi = (float)(2.0 * Math.PI), threePi = (float)(3.0 * Math.PI), radPerSecMin = (float)(Math.PI / 30.0);
}
class ScrollBarMagic extends JFrame implements AdjustmentListener
{
	public static void main(String...Pratik)
	{
		new ScrollBarMagic();
	}
	public ScrollBarMagic()
	{
		super("ScrollBars Magic by Pratik_Patil");
		setVisible(true); 
		Toolkit tk=Toolkit.getDefaultToolkit();
		Dimension d=tk.getScreenSize();
		MediaTracker mt = new MediaTracker(this);
		img = tk.getImage(getClass().getResource("PratikImg/IM1.gif"));
		mt.addImage(img,0);
		class mypanel extends JPanel
		{
			public void paintComponent(Graphics g)
			{
				super.paintComponent(g); 
				g.drawImage(img, 5*x, 2*y, this);
			}
		}
		JPanel p=new mypanel(); 
		p.setLayout(new BorderLayout());
		p.add(s1=new JScrollBar(0,100,0,0,255),"North");
		p.add(s2=new JScrollBar(1,100,0,0,255),"East");
		p.add(s3=new JScrollBar(0,100,0,0,255),"South");
		p.add(s4=new JScrollBar(1,100,0,0,255),"West");
		s1.addAdjustmentListener(this);
		s2.addAdjustmentListener(this);
		s3.addAdjustmentListener(this); 
		s4.addAdjustmentListener(this);
		p.setBackground(Color.black); 
		add(p); 
		setSize(d.width,d.height-50);
	}
	public void adjustmentValueChanged(AdjustmentEvent e)
	{
		a=s1.getValue(); 
		b=s2.getValue(); 
		c=s3.getValue(); 
		d=s4.getValue();
		if(e.getSource()==s1||e.getSource()==s3)
		{
			x=-a+c;	
		}
		if(e.getSource()==s2||e.getSource()==s4)
		{
			y=-b+d;
		}
		repaint();
	}
	JScrollBar s1,s2,s3,s4;
	int x=100,y=100,a,b,c,d;
	Image img;
}
class BouncingBall extends JFrame
{
	public static void main(String...Pratik)
	{
		new BouncingBall();
	}
	public BouncingBall()
	{
		Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
		frame_width=d.width; frame_height=d.height;
		setTitle("Bouncing Ball by Pratik_Patil"); 
		setVisible(true);
		setResizable(false); 
		add(new ballpanel());
		setSize(d); 
	}
	class ballpanel extends JPanel //Inner Class
	{
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g); 
			setBackground(Color.black);
	        	g.setColor(Color.green);	
	        	g.fillOval(x,y,size,size); 
	        	g.setColor(Color.red);
        		Font f=new Font("TIMES NEW ROMAN",Font.BOLD,25);
        		setFont(f); 
        		g.drawString("PRATIK",x+60,y+100);
		}
		public ballpanel()
		{
			ActionListener listener = new ActionListener()
			{
				public void actionPerformed(ActionEvent evt)
				{
					update_xy(); 
					repaint();
				}
			};
      			new javax.swing.Timer(100,listener).start();
		}
	}
	public void update_xy() 
	{	
		x += xSpeed; 
		y += ySpeed;
      		if (x >(frame_width -size) || x < 0)
      		{
      			xSpeed = -xSpeed;
		}
      		if (y >(frame_height -size) || y < 0)
      		{
      			ySpeed = -ySpeed;
		}
    	}
    	int frame_width,frame_height,x=100,y=100,size=200,xSpeed=20,ySpeed=80;
}