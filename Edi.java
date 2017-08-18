import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.awt.*;
import java.awt.event.*;
class Edi extends KeyAdapter implements ActionListener 
{
	Frame f; MenuBar mb; Menu m[] = new Menu[2];
	Frame wrn;
	MenuItem nw,opn, sve, ext, svea, fnd,fndr;
	TextArea ta;
	File fa;
	Frame fds;
	FileDialog fd;
	FileInputStream fis;
	FileOutputStream fos;
	boolean savem,saveasm,om=false,nm=false;
	public Edi()
	{
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
		m[0]= new Menu("File");
		m[1]= new Menu("Edit");
		nw = new MenuItem("New");
		opn = new MenuItem("Open");
		sve = new MenuItem("Save");
		svea = new MenuItem("Save As...");
		ext = new MenuItem("Exit");
		fnd = new MenuItem("Find");
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
	 	b1.addActionListener(this);
	 	wb1.addActionListener(this);
	 	b2.addActionListener(this);
	 	b3.addActionListener(this);
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
		f.add(ta);
		Wi w = new Wi();
		f.addWindowListener(w);
		f.setMenuBar(mb); f.setVisible(true);
	}
	public void actionPerformed(ActionEvent e)
	{
		String str = e.getActionCommand();
		String tas;
		int ch;
		char cha,car[];
		if(str.equals("New"))
		{
			if(savem||ta.getText().equals("\0"))
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
			fds.setVisible(false);
		}
	}
	
	public  static void main(String arg[])
	{
		Edi e = new Edi();
	}
	
}
