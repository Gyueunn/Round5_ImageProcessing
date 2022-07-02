package camp.java.project5;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ButtonPanel extends JPanel {//버튼액셩 리스너 
	private JButton open, save, grayScale, brightness, function3, origin;
	
	public ButtonPanel() {
//		setLayout(null);
		setLayout(new GridLayout(1, 5));
		setBounds(0, 20, 1440, 50);
		
		open = new JButton("Open");
//		open.setBounds(10, 0, 276, 50);
		this.add(open);
		
		save = new JButton("Save");
//		open.setBounds(286, 0, 276, 50);
		this.add(save);
		
		grayScale = new JButton("GrayScale");
//		open.setBounds(572, 0, 276, 50);
		this.add(grayScale);
		
		brightness = new JButton("Brightness");
//		open.setBounds(858, 0, 276, 50);
		this.add(brightness);
		
		function3 = new JButton("기능3");
//		open.setBounds(1144, 0, 276, 50);
		this.add(function3);
		
		origin = new JButton("Reset");
//		open.setBounds(1430, 0, 276, 50);
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
	
	//원본으로 되돌리기 
	public JButton getOrigin() {
		return origin;
	}
	public void setOrigin(JButton origin) {
		this.origin = origin;
	}
	
	//밝기 조절하기 
	public JButton getBrightness() {
		return brightness;
	}
	public void setBrightness(JButton brightness) {
		this.brightness = brightness;
	}
}
