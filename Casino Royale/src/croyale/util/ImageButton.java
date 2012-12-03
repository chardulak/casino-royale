package croyale.util;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class ImageButton extends JButton {

	  private java.awt.Image img;
	  {setOpaque(false);} 

	  public ImageButton(String img) {
	    this(new ImageIcon(img).getImage());
	  }

	  public ImageButton(Image img) {
	    this.img = img;
	    Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
	    setPreferredSize(size);
	    setMinimumSize(size);
	    setMaximumSize(size);
	    setSize(size);
	    setLayout(null);
	  }

	  public void paintComponent(Graphics g) {
	    g.drawImage(img, 0, 0, null);
	    //super.paintComponent(g);
	  }

	}