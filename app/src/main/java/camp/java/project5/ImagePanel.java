package camp.java.project5;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {
	private Image1Graphic leftPanel;
	private Image2Graphic rightPanel;
	
	public ImagePanel() {
		this.setLayout(null);
		this.setBounds(0, 90, 1440, 800);
		
		leftPanel = new Image1Graphic();
		leftPanel.setLayout(null);
		leftPanel.setBounds(50, 0, 650, 650);
		
		rightPanel = new Image2Graphic();
		rightPanel.setLayout(null);
		rightPanel.setBounds(740, 0, 650, 650);
		
		this.add(leftPanel);
		this.add(rightPanel);
	}

	public Image1Graphic getLeftPanel() {
		return leftPanel;
	}

	public void setLeftPanel(Image1Graphic leftPanel) {
		this.leftPanel = leftPanel;
	}

	public Image2Graphic getRightPanel() {
		return rightPanel;
	}

	public void setRightPanel(Image2Graphic rightPanel) {
		this.rightPanel = rightPanel;
	}
}

