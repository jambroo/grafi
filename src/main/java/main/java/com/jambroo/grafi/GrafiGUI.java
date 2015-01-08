package main.java.com.jambroo.grafi;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

/*
TODO:
- Fix heights:
- Fix errors on run to stdout
*/

public class GrafiGUI extends Frame implements WindowListener, ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1150950659955313271L;
	private Dimension dimensions;
	private PhotoThumb[] thumbs;
	//private PhotoFile[] files;
	private File path;

	@SuppressWarnings("deprecation")
	public GrafiGUI () {
            setLayout(new FlowLayout());

		    addWindowListener(new WindowAdapter(){
		        public void windowClosing(WindowEvent we) {
		        	System.exit(0);
		        }
            });

            addMouseListener(new MouseAdapter() { 
                public void mousePressed(MouseEvent me) { 
                    System.out.println(me); 
                } 
            });

	    this.dimensions = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
	    resize(this.dimensions);

	    this.path = new File("/home/jamie/workspace/grafi/test_images");
	    this.thumbs = this.loadPhotos(0, 10, 0, 0); //pf.getThumbs(0, 0);

	    setTitle("Test Form");
	    setVisible(true);
	}

	@SuppressWarnings("unused")
	private void outputMemUsage() {
            long maxMemory = Runtime.getRuntime().maxMemory();
            long freeMemory = Runtime.getRuntime().freeMemory();

	    System.out.println((maxMemory-freeMemory));
	}

    private PhotoThumb[] loadPhotos(int offset, int limit, int x, int y) {
        PhotoFiles pf = new PhotoFiles(this.path, offset, limit, this.dimensions);
        
        return pf.getThumbs(x, y);
    }

	public void paint(Graphics g) {
		for (int i=0; i<this.thumbs.length; i++) {
                try {
                    PhotoThumb thumb = this.thumbs[i];
                    
                    g.drawImage(thumb.getImage(), thumb.getX(), thumb.getY(), thumb.getWidth(), thumb.getHeight(), this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            // Need to watch this rendering elsewhere and detect when screen is full of thumbs
            //this.thumbs = this.loadPhotos(10, 10, 6, 6); //pf.getThumbs(0, 0);
        }

	public static void main(String[] args) {
		GrafiGUI app = new GrafiGUI();
	}
 
	@Override
	public void actionPerformed(ActionEvent evt) {
		// TODO: process event
	}

	@Override
	public void windowClosing(WindowEvent e) {
		System.exit(0);
	}

	@Override
	public void windowOpened(WindowEvent e) { }
	@Override
	public void windowClosed(WindowEvent e) { }
	@Override
	public void windowIconified(WindowEvent e) { }
	@Override
	public void windowDeiconified(WindowEvent e) { }
	@Override
	public void windowActivated(WindowEvent e) { }
	@Override
	public void windowDeactivated(WindowEvent e) { }
}
