package camp.java.project5;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MainFrame extends JFrame {
	private ButtonPanel buttonPanel;
	private ImagePanel imagePanel;
	private BufferedImage image1, image2, saveImage;
	 
	JFileChooser fileChooser = new JFileChooser();
	int x,y,h,w;
	
	public MainFrame() {
		setTitle("Image Processing");
		setLayout(null);
		setBounds(0, 0, 1440, 800);
		setLocationRelativeTo(null);
		createFrame();
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void createFrame() {
		buttonPanel = new ButtonPanel();
		imagePanel = new ImagePanel();
		
		buttonPanel.getOpen().addActionListener(listener);
		buttonPanel.getSave().addActionListener(listener);
		buttonPanel.getGrayScale().addActionListener(listener);
		buttonPanel.getBrightness().addActionListener(listener);
		buttonPanel.getCrop().addActionListener(listener);
		buttonPanel.getInversion().addActionListener(listener);
		buttonPanel.getOrigin().addActionListener(listener);
		
		this.add(buttonPanel);
		this.add(imagePanel);
	}
	
	public static void main(String[] args) {
		new MainFrame();
	}
	
	ActionListener listener = new ActionListener() {
		@Override
        public void actionPerformed(ActionEvent e) {
        	
        	String input = e.getActionCommand();
        	
        	if(input.equals("Open")) {
                if(fileChooser.showOpenDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION){
                   	System.out.println("filePath : " + fileChooser.getSelectedFile().toString());
					try {
						image1 = ImageIO.read(fileChooser.getSelectedFile());
						image2 = ImageIO.read(fileChooser.getSelectedFile());
						imagePanel.getLeftPanel().setImage(image1);
						imagePanel.getRightPanel().setImage(image2);
						
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					imagePanel.getLeftPanel().repaint();
					imagePanel.getRightPanel().repaint();
                }
	       	}
	       	
	       	else if(input.equals("Save")) {
	       		try {
	       			saveImage = new Robot().createScreenCapture(new Rectangle(imagePanel.getRightPanel().getLocationOnScreen().x, imagePanel.getRightPanel().getLocationOnScreen().y, imagePanel.getRightPanel().getWidth(), imagePanel.getRightPanel().getHeight()));
	                } catch (AWTException e1) {
	                e1.printStackTrace();
	                } 
	       		fileChooser = new JFileChooser();
	       		fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("png", "png"));
	       		fileChooser.setFileFilter(new FileNameExtensionFilter("jpg", "jpg"));
	             
	       		fileChooser.setMultiSelectionEnabled(false);
	       		fileChooser.setVisible(true);
	             int result = fileChooser.showSaveDialog(MainFrame.this);
	             if (result == JFileChooser.APPROVE_OPTION) {
	                
	                if(fileChooser.getFileFilter().toString().contains(".png")) {
	                   String fileName = fileChooser.getSelectedFile().getPath() + ".png";
	                   File pngFile = new File(fileName);
	                   try {
	                      ImageIO.write(saveImage, "png", pngFile);
	                   } catch (IOException e1) {
	                      e1.printStackTrace();
	                   }
	                }
	                else {
	                   String fileName = fileChooser.getSelectedFile().getPath() + ".jpg";
	                   File imgFile = new File(fileName);
	                   
	                   try {
	                      ImageIO.write(saveImage, "jpg", imgFile);
	                   } catch (IOException e1) {
	                      e1.printStackTrace();
	                   }
	                }
	             }
		    }
        	
        	else if(input.equals("GrayScale")) {
        		for(int y = 0; y < imagePanel.getRightPanel().getImage().getHeight(); y++) {
        		   for(int x = 0; x < imagePanel.getRightPanel().getImage().getWidth(); x++) {
        		       Color colour = new Color(imagePanel.getRightPanel().getImage().getRGB(x, y));
        		       int Y = (int) (0.2126 * colour.getRed() + 0.7152 * colour.getGreen() + 0.0722 * colour.getBlue());
        		       image1.setRGB(x, y, new Color(Y, Y, Y).getRGB());
        		       imagePanel.getRightPanel().getImage().setRGB(x, y, new Color(Y, Y, Y).getRGB());
        		   }
        		}
        		imagePanel.getRightPanel().repaint();
        	}
        	
        	else if(input.equals("Brightness")) {
        		int bright;
        		//slider 설정 
        		JSlider slider = new JSlider(-150, 150, 0);
        		JFrame control = new JFrame();
        		control.setTitle("Brightness");
        		control.setLocation(150, 200);
        		slider.setLayout(null);
                slider.setMajorTickSpacing(50); //큰 눈금 간격 5로 설정
                slider.setMinorTickSpacing(10); //작은 눈금 간격 1로 설정
                slider.setPaintTicks(true); //눈금을 표시한다.
                slider.setPaintLabels(true); //값을 레이블로 표시한다.
                slider.setVisible(true);
                control.add(slider, BorderLayout.CENTER);
                control.setSize(400,150);
                control.setVisible(true);
                control.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                
                
                BufferedImage i;
            	i = imagePanel.getRightPanel().getImage();
				slider.addChangeListener(new ChangeListener() {
                    public void stateChanged(ChangeEvent e) {
                    	BufferedImage temp = i;
						int bright = slider.getValue();
		                for (int y = 0; y < i.getHeight(); y++) {
		                    for (int x = 0; x < i.getWidth(); x++) {
		                       int pixel = image1.getRGB(x, y);
		                       Color color = new Color(pixel, true);
		                       
		                       int red = color.getRed() + bright;
		                       if (red > 255) red = 255;
		                       else if (red < 0) red = 0;
		                       
		                       int green = color.getGreen() + bright;
		                       if (green > 255) green = 255;
		                       else if (green < 0) green = 0;
		                       
		                       int blue = color.getBlue() + bright;
		                       if (blue > 255) blue = 255;
		                       else if (blue < 0) blue = 0;
		                       
		                       color = new Color(red, green, blue);
		                       
		                       temp.setRGB(x, y, color.getRGB());
		                    }
		                 }
		                imagePanel.getRightPanel().setImage(temp);
    	        		imagePanel.getRightPanel().repaint();
                    } 
                });
        	}
        		
    		else if(input.equals("Inversion")) {
    			int red, green, blue;
    			for(int y = 0; y < imagePanel.getRightPanel().getImage().getHeight(); y++) {
         		   for(int x = 0; x < imagePanel.getRightPanel().getImage().getWidth(); x++) {
         		       Color colour = new Color(imagePanel.getRightPanel().getImage().getRGB(x, y));
         		       red = 255 - colour.getRed();
         		       green = 255 - colour.getGreen();
         		       blue = 255 - colour.getBlue();
         		       imagePanel.getRightPanel().getImage().setRGB(x, y, new Color(red, green, blue).getRGB());
         		       image1.setRGB(x, y, new Color(red, green, blue).getRGB());
         		   }
         		}
         		imagePanel.getRightPanel().repaint();
        	}
        	
        	else if(input.equals("Reset")) {
        		try {
        			image1 = ImageIO.read(fileChooser.getSelectedFile());
        			image2 = ImageIO.read(fileChooser.getSelectedFile());
        			imagePanel.getRightPanel().setImage(image2);
	        		imagePanel.getRightPanel().repaint();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        	}
        	
        	else if(input.equals("Crop")) {
        		imagePanel.getLeftPanel().addMouseListener(new MyMouseListener()); 
        		imagePanel.getLeftPanel().addMouseMotionListener(new MyMouseListener()); 
        	}
        }
	};
	
	class MyMouseListener extends MouseAdapter {
//		BufferedImage temp = i;
		public void mousePressed(MouseEvent e){
			x = e.getX() * image1.getWidth() / 649;
			y =  e.getY() * image1.getHeight() / 649;
			System.out.println("클릭이다 : " + x + " " + y);
		}
		
		public void mouseReleased(MouseEvent e){
				w = e.getX() * image1.getWidth() / 649 - x;
				h = e.getY() * image1.getHeight() / 649 - y;
				System.out.println("드래그다 : " + w + " " + h);
				imagePanel.getRightPanel().setImage(image1.getSubimage(x, y, w, h));
	    		imagePanel.getRightPanel().repaint();
	    		x=0;
	    		y=0;
	    		h=0;
	    		w=0;
		}	
	}
}
