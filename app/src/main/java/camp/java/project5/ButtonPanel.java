package camp.java.project5;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ButtonPanel extends JPanel {//버튼액셩 리스너 
	private JButton open, save, grayScale, brightness, crop, origin, inversion;
	
	public ButtonPanel() {
		setLayout(new GridLayout(1, 7));
		setBounds(0, 20, 1440, 50);
		
		open = new JButton("Open");
		open.setFont(new Font("Arial", Font.ITALIC, 30));
		this.add(open);
		
		save = new JButton("Save");
		save.setFont(new Font("Arial", Font.ITALIC, 30));
		this.add(save);
		
		grayScale = new JButton("GrayScale");
		grayScale.setFont(new Font("Arial", Font.ITALIC, 30));
		this.add(grayScale);
		
		brightness = new JButton("Brightness");
		brightness.setFont(new Font("Arial", Font.ITALIC, 30));
		this.add(brightness);
		
		crop = new JButton("Crop");
		crop.setFont(new Font("Arial", Font.ITALIC, 30));
		this.add(crop);
		
		inversion = new JButton("Inversion");
		inversion.setFont(new Font("Arial", Font.ITALIC, 30));
		this.add(inversion);
		
		origin = new JButton("Reset");
		origin.setFont(new Font("Arial", Font.ITALIC, 30));
		this.add(origin);
		
		this.setBackground(Color.LIGHT_GRAY);
	}
	//열기 
	public JButton getOpen() {
		return open;
	}
	public void setOpen(JButton open) {
		this.open = open;
	}
	
	//저장 
	public JButton getSave() {
		return save;
	}
	public void setSave(JButton save) {
		this.save = save;
	}
	
	//흑백 
	public JButton getGrayScale() {
		return grayScale;
	}
	public void setGrayScale(JButton grayScale) {
		this.grayScale = grayScale;
	}
	
	//crop 
	public JButton getCrop() {
		return crop;
	}
	public void setCrop(JButton crop) {
		this.crop =crop;
	}
		
	//밝기 조절하기 
	public JButton getBrightness() {
		return brightness;
	}
	public void setBrightness(JButton brightness) {
		this.brightness = brightness;
	}
	
	//원본으로 되돌리기 
	public JButton getOrigin() {
		return origin;
	}
	public void setOrigin(JButton origin) {
		this.origin = origin;
	}
	
	//색 반전 
	public JButton getInversion() {
		return inversion;
	}
	public void setInversion(JButton inversion) {
		this.inversion = inversion;
	}
	
}
