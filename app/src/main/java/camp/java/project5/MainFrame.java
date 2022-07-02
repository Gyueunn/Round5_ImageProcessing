package camp.java.project5;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MainFrame extends JFrame {
	private ButtonPanel buttonPanel;
	private ImagePanel imagePanel;
	private BufferedImage image1, image2;
	
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
		buttonPanel.getOrigin().addActionListener(listener);
		buttonPanel.getBrightness().addActionListener(listener);
		
		this.add(buttonPanel);
		this.add(imagePanel);
	}
	
	public static void main(String[] args) {
		MainFrame frame = new MainFrame();
	}
	
	ActionListener listener = new ActionListener() {
		JFileChooser fileChooser = new JFileChooser();
		@Override
        public void actionPerformed(ActionEvent e) {
        	
        	String input = e.getActionCommand();
        	
        	if(input.equals("Open")) {
                if(fileChooser.showOpenDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION){
                	String filePath = fileChooser.getSelectedFile().getPath();
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
	       		
		    }
        	
        	else if(input.equals("GrayScale")) {
        		for(int y = 0; y < imagePanel.getRightPanel().getImage().getHeight(); y++) {
        		   for(int x = 0; x < imagePanel.getRightPanel().getImage().getWidth(); x++) {
        		       Color colour = new Color(imagePanel.getRightPanel().getImage().getRGB(x, y));
        		       int Y = (int) (0.2126 * colour.getRed() + 0.7152 * colour.getGreen() + 0.0722 * colour.getBlue());
        		       imagePanel.getRightPanel().getImage().setRGB(x, y, new Color(Y, Y, Y).getRGB());
        		   }
        		}
        		imagePanel.getRightPanel().repaint();
        	}
        	
        	else if(input.equals("Brightness")) {
        		int bright;
        		//slider 설정 
        		JSlider slider = new JSlider(-255, 255, 0);
        		JFrame control = new JFrame();
        		control.setTitle("Brightness");
        		control.setLocation(150, 200);
        		slider.setLayout(null);
                slider.setMajorTickSpacing(50); //큰 눈금 간격 5로 설정
//                slider.setMinorTickSpacing(50); //작은 눈금 간격 1로 설정
//                slider.setPaintTicks(true); //눈금을 표시한다.
                slider.setPaintLabels(true); //값을 레이블로 표시한다.
                slider.setVisible(true);
                control.add(slider, BorderLayout.CENTER);
                control.setSize(300,150);
                control.setVisible(true);
                control.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                
//                try {
//					image2 = ImageIO.read(fileChooser.getSelectedFile());
//				} catch (IOException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
				slider.addChangeListener(new ChangeListener() {
                    public void stateChanged(ChangeEvent e) {
                    	try {
							image2 = ImageIO.read(fileChooser.getSelectedFile());
							int bright = slider.getValue();
			                for (int y = 0; y < image2.getHeight(); y++) {
			                    for (int x = 0; x < image2.getWidth(); x++) {
			                       int pixel = image2.getRGB(x, y);
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
			                       image2.setRGB(x, y, color.getRGB());
			                    }
			                 }
			                imagePanel.getRightPanel().setImage(image2);
	    	        		imagePanel.getRightPanel().repaint();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
                    } 
                });
        	}
        	
        	else if(input.equals("Reset")) {
        		try {
        			image2 = ImageIO.read(fileChooser.getSelectedFile());
        			imagePanel.getRightPanel().setImage(image2);
	        		imagePanel.getRightPanel().repaint();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        	}
        }
	};
}
