import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.awt.*;
import java.awt.event.*;
class Edi extends KeyAdapter implements ActionListener 
{
	Frame f; MenuBar mb; Menu m[] = new Menu[2];
	Frame wrn; Frame fndf; Frame noma;Frame rep;
	TextField retf1,retf2;
	TextField ftf1;
	MenuItem nw,opn, sve, ext, svea, fnd,fndr;
	TextArea ta;
	Pattern p;
	Matcher matchr;
	File fa;
	int min=0,min1=0,end=0;
	Frame fds;
	FileDialog fd;
	FileInputStream fis;
	FileOutputStream fos;
	Button fb1;
	boolean savem,saveasm,om=false,nm=false,fndm,fm=false;
	public Edi()
	{
		noma =new Frame();
		savem = false;
		saveasm = false;
		f=new Frame();
		f.setSize(800,800);
		mb = new MenuBar();
		wrn = new Frame();
		wrn.setSize(400,100);
		Label wl1 = new Label("File not found",Label.CENTER);
		wl1.setFont(new Font("Arial",Font.BOLD,12));
		Button wb1= new Button("OK");
		Panel wp1 = new Panel(new FlowLayout(FlowLayout.CENTER));
		wp1.add(wb1);
		wrn.add("South",wp1);
		wrn.add(wl1);
		wrn.setResizable(false);
		m[0]= new Menu("File");
		m[1]= new Menu("Edit");
		nw = new MenuItem("New");
		opn = new MenuItem("Open");
		sve = new MenuItem("Save");
		svea = new MenuItem("Save As...");
		ext = new MenuItem("Exit");
		fnd = new MenuItem("Find");
		fndf=new Frame();
		fndf.setLayout(new GridLayout(2,2));
	 	fndf.setSize(400,100);
		Label fl1 = new Label("Find");
	 	ftf1 = new TextField("            ");
	 	//ftf1.setSize(50,25);
	 	Font ffon = new Font("Arial",Font.PLAIN,20);
	 	fb1= new Button("Find...");
	 	Button fb2 = new Button("Close");
	 	fl1.setFont(ffon);
	 	fb1.setFont(ffon);
	 	fb2.setFont(ffon);
	 	fndf.add(fl1);
	 	Panel fp1 = new Panel(new FlowLayout(FlowLayout.LEFT));
	 	Panel fp2 = new Panel(new FlowLayout(FlowLayout.LEFT));
	 	Panel fp3 = new Panel(new FlowLayout(FlowLayout.LEFT));
	 	Panel fp4 = new Panel(new FlowLayout(FlowLayout.LEFT));
	 	fp1.add(fb1);fp2.add(ftf1);fp3.add(fb2);fp4.add(fl1);
	 	fndf.add(fp4);
	 	fndf.add(fp2);fndf.add(fp1);fndf.add(fp3);
	 	fndf.setResizable(false);		
		fndr = new MenuItem("Find & Replace");
		m[0].add(nw); m[0].add(opn); m[0].add(sve);
		m[0].add(svea); m[0].addSeparator();m[0].add(ext);
		m[1].add(fnd); m[1].add(fndr);
		mb.add(m[0]); mb.add(m[1]);
		nw.addActionListener(this);
		opn.addActionListener(this);
		svea.addActionListener(this);
		sve.addActionListener(this);
		ext.addActionListener(this);
		fnd.addActionListener(this);
		fb2.addActionListener(this);
		fds=new Frame();
	 	fds.setSize(500,100);
	 	Label l1=new Label("Save changes to document “Untitled Document 1” before closing?",Label.CENTER);
	 	Label l2= new Label("If you don't save, changes will be permanently lost.",Label.CENTER);
	 	Panel p1 = new Panel(new FlowLayout(FlowLayout.CENTER));
	 	Panel p2 = new Panel(new GridLayout(2,1));
	 	l1.setFont(new Font("Arial",Font.BOLD,12));
	 	l2.setFont(new Font("Arial",Font.PLAIN,12));
	 	Button b1= new Button("Don't Save");
	 	Button b2= new Button("Cancel");
	 	Button b3= new Button("Save");
	 	noma.setSize(100,100);
		Label nol1 = new Label("No match");
		Button nob1 = new Button("Cancel");
		Panel nop1 = new Panel(new FlowLayout());
		Panel nop2 = new Panel(new FlowLayout());
		nop1.add(nol1);
		nop2.add(nob1);
		noma.add(nop1);
		rep =new Frame();
		rep.setSize(300,100);
		Label repl1 = new Label("Find");
		retf1 = new TextField("        ");
		retf2 = new TextField("        ");
		Label repl2 = new Label("Replace with");
		Button repb1,repb2,repb3,repb4;
		Panel repp1 = new Panel(new GridLayout(2,2));
		
		Panel repp2 = new Panel(new FlowLayout(FlowLayout.RIGHT));
		Panel repp3 = new Panel(new FlowLayout(FlowLayout.LEFT));
		Panel repp4 = new Panel(new FlowLayout(FlowLayout.RIGHT));
		Panel repp5 = new Panel(new FlowLayout(FlowLayout.LEFT));
		Panel repp6 = new Panel(new FlowLayout());
		repb1 = new Button("Find.");
		repb2 = new Button("Replace");
		repb3 = new Button("Replace all");
		repb4 = new Button("Close");
		repp2.add(repl1);repp3.add(retf1);repp4.add(repl2);
		repp5.add(retf2); repp6.add(repb1);  repp6.add(repb2);
		repp6.add(repb3); repp6.add(repb4);
		
		repp1.add(repp2);repp1.add(repp3);repp1.add(repp4);
		repp1.add(repp5);
		rep.add(repp1);rep.add("South",repp6);
		rep.setResizable(false);
		noma.add("South",nop2);
		noma.setResizable(false);
	 	b1.addActionListener(this);
	 	wb1.addActionListener(this);
	 	b2.addActionListener(this);
	 	b3.addActionListener(this);
	 	fb1.addActionListener(this);
	 	nob1.addActionListener(this);
	 	repb1.addActionListener(this);
	 	repb2.addActionListener(this);
	 	repb3.addActionListener(this);
	 	repb4.addActionListener(this);
	 	fndr.addActionListener(this);
	 	p1.add(b1);
	 	p1.add(b2);
	 	p1.add(b3);
	 	p2.add(l1);
	 	p2.add(l2);
	 	fds.add(p2);
	 	fds.add("South",p1);
	 	
	 	//fds.setVisible(true);
		ta= new TextArea();
		ta.addKeyListener(new KeyListener()
				 {
				 	public void keyTyped(KeyEvent ek)
				 	{
				 		savem=false;
				 		System.out.print("yo");
				 	}
				 	public void keyPressed(KeyEvent ek){}
				 	public void keyReleased(KeyEvent ek){}
				 		
				 });
		ftf1.addKeyListener(new KeyListener()
				 {
				 	public void keyTyped(KeyEvent ek)
				 	{
				 		min=0;
				 		//System.out.print("yo");
				 	}
				 	public void keyPressed(KeyEvent ek){}
				 	public void keyReleased(KeyEvent ek){}
				 		
				 });
		f.add(ta);
		p = Pattern.compile("");
		matchr = p.matcher(ta.getText());
		Wi w = new Wi();
		f.addWindowListener(w);
		f.setMenuBar(mb); f.setVisible(true);
	}
	public void actionPerformed(ActionEvent e)
	{
		String str = e.getActionCommand();
		String tas,replae,replaced;
		int ch;
		char cha,car[];
		String findwrd;
		
		/*if(str.equals("Find Next"))
		{
			
			if(ftf1.getText().equals(findwrd))
			{
				if(matchr.find())
				{
					ta.select(matchr.start(),matchr.end());
				}
				else
				{
					System.out.println("No Match");
				}
			}
			else
			{
				System.out.println("enter");
				str = "Find..." ;
			}
		}*/
		if(str.equals("Find & Replace"))
		{
			retf1.setText("");
			retf2.setText("");
			rep.setVisible(true);
		}
		if(str.equals("Find.")||str.equals("Replace all"))
		{
			
			findwrd = retf1.getText();
			p = Pattern.compile(findwrd);
			matchr = p.matcher(ta.getText());
			replae = retf2.getText();
			if(str.equals("Replace all"))
			{
				if(matchr.find())
				{
					replaced = matchr.replaceAll(replae);
					ta.setText(replaced);
					savem = false;
				}
			}
			try
			{	
				if(matchr.find(min))
				{
					ta.select(matchr.start(),matchr.end());
					min1 = matchr.start();
					min=matchr.end();
					fm = true;
					//if(str.equals("Replace"))
					//fb1.setLabel("Find Next");
				
				}
				else
				{
					min=0;
					fm = false;
					System.out.println("No Match");
					noma.setVisible(true);
				}
			}
			catch(IndexOutOfBoundsException tbe)
			{min=0;}
		}
		if(str.equals("Replace"))
		{
			replae = retf2.getText();
			if(fm)
			{
				ta.replaceText(replae,min1,min);
				fm = false;
				savem = false;
			}
			else
			{
				findwrd = retf1.getText();
				p = Pattern.compile(findwrd);
				matchr = p.matcher(ta.getText());
				try
				{
					if(matchr.find(min))
					{
						ta.select(matchr.start(),matchr.end());
						min1 = matchr.start();
						min=matchr.end();
						fm = true;
						//if(str.equals("Replace"))
						//fb1.setLabel("Find Next");
				
					}
					else
					{
						min=0;
						fm = false;
						System.out.println("No Match");
						noma.setVisible(true);
					}
				}
				catch(IndexOutOfBoundsException tbe)
				{min=0;}
			}
			
		}
		if(str.equals("Find..."))
		{
			
			findwrd = ftf1.getText();
			p = Pattern.compile(findwrd);
			matchr = p.matcher(ta.getText());
			
			if(matchr.find(min))
			{
				ta.select(matchr.start(),matchr.end());
				min=matchr.end();
				//fb1.setLabel("Find Next");
				
			}
			else
			{
				min=0;
				System.out.println("No Match");
				noma.setVisible(true);
			}
			
		}
		
		if(str.equals("Find"))
		{
			fndf.setVisible(true);
			
			//fndm = true;
			ftf1.setText("");
		}
		if(str.equals("Close"))
		{
			min=0;
			rep.setVisible(false);
			fndf.setVisible(false);
			fb1.setLabel("Find...");
		}
		if(str.equals("New"))
		{
			if(savem||ta.getText().equals(""))
			{
				ta.setText("\0");
			}
			else
			{
				nm=true;
				fds.setVisible(true);
			}
			
		}
		if(str.equals("Exit"))
		{
			System.exit(1);
		}
		if(str.equals("Don't Save"))
		{
			if(nm)
			{
				ta.setText("\0");
				nm=false;
				fds.setVisible(false);
			}
			if(om)
			{
				
				//ta.setText("\0");
				str="Open";
				fds.setVisible(false);
			}
		}
		if(str.equals("Save"))
		{
			if(!saveasm)
			{
				str = "Save As..." ;
			}
			else
			{
				if(!savem)
				{
					try
					{
						
						fos = new FileOutputStream(fa);
						tas = ta.getText();
						car = tas.toCharArray(); 
						for(int ic=0;ic<car.length;ic++)
						{
							fos.write(car[ic]);
						}
						fos.close();
						
						savem = true;
					}
					catch(IOException e1)
					{
				
					}
					if(om)
					{
				
						//ta.setText("\0");
						str="Open";
						fds.setVisible(false);
					}
					if(nm)
					{
						ta.setText("\0");
						nm=false;
						fds.setVisible(false);
					}
				}
			}
			
			fds.setVisible(false);
		
		}
		if(str.equals("OK"))
		{
			wrn.setVisible(false);
			str="Open";
		}
		if(str.equals("Open"))
		{
			if(savem||ta.getText().equals("")||om)
			{
				om=false;
				fd = new FileDialog(f,"Open",FileDialog.LOAD);
				fd.setVisible(true);
				try
				{
					if(fd.getFile().equals(null))
					{
						//System.out.println("fhdfhehbdfn");
						return;
					}
					fis = new FileInputStream(fd.getDirectory()+fd.getFile());
					
					//System.out.println(fd.getDirectory()+fd.getFile());
					ta.setText("\0");
					while((ch=fis.read())!=-1)
					{
						cha =(char)ch;
						ta.appendText(""+cha);
					}
					
					fa = new File(fd.getDirectory()+fd.getFile());
					fis.close();
				}
				catch(IOException e1)
				{
					System.out.println(e1.getClass().getName());
					//System.out.println(e1.getMessage());
					wrn.setVisible(true);
				}
				saveasm=true;
				savem=true;
			}
			else
			{
				om=true;
				fds.setVisible(true);
			}
		}
		
		
		if(str.equals("Save As..."))
		{
			try
			{
				fd = new FileDialog(f,"Save As",FileDialog.SAVE);
				fd.setVisible(true);
			}
			catch(NullPointerException e12)
			{
				System.out.println("ygvrefgvo");
			}
			try
			{
				if(fd.getFile().equals(null))
				{
					System.out.print("ygvrefgvo");
					return;
				}
				fa = new File(fd.getDirectory()+fd.getFile());
				
				fos = new FileOutputStream(fa);
				
				tas = ta.getText();
				car = tas.toCharArray(); 
				for(int ic=0;ic<car.length;ic++)
				{
					fos.write(car[ic]);
				}
				fos.close();
				
				saveasm = true;
				savem = true;
			}
			catch(IOException e1)
			{
				//System.out.print("ygvrefgvo");
			}
			
		}
		if(str.equals("Cancel"))
		{
			om=false;
			nm=false;
			fds.setVisible(false);
			noma.setVisible(false);
		}
	}
	
	public  static void main(String arg[])
	{
		Edi e = new Edi();
	}
	
}
