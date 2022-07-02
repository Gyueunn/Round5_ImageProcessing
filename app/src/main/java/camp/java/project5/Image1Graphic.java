package camp.java.project5;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Image1Graphic extends JPanel {
	private  BufferedImage image;
	private boolean isImageLoaded = false;
	
	public Image1Graphic() {
		this.setBounds(0, 0, 650, 650);
		this.setBackground(Color.WHITE);
	}
	
	public void setImage(BufferedImage image) {
		this.image = image;
		isImageLoaded = true;
	}
	
	public BufferedImage getImage() {
		return image;
	}
	
	@Override
	public void paintComponent(Graphics graphic) {
		super.paintComponent(graphic);
		Graphics2D g2 = (Graphics2D) graphic;
		
		if(isImageLoaded) {
			double ratio = 0.0;
	        int w = 0;
	        int h = 0;
	        if(image.getWidth(null)>image.getHeight(null)) {
	           ratio = ((double)650)/((double)image.getWidth(null));
	           w = (int)(image.getWidth(null) * ratio);
	           h = (int)(image.getHeight(null) * ratio);
	        }
	        else {
	           ratio = ((double)650)/((double)image.getHeight(null));
	           w = (int)(image.getWidth(null) * ratio);
	           h = (int)(image.getHeight(null) * ratio);
	        }
	        Image resizeImage = image.getScaledInstance(w, h, Image.SCALE_SMOOTH);
	        if(image.getWidth(null)>image.getHeight(null)) {
	        	g2.drawImage(resizeImage,0,650/2 - h/2,this);            
	        }
	        else {
	        	g2.drawImage(resizeImage,650/2 - w/2,0,this);   
	        }
		}
	}
}
