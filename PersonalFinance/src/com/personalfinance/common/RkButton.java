package com.personalfinance.common;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

public class RkButton extends JButton {
	
	public Color hoverColorBtn;
	private Color pressedBtn;
    private int borderRadius = 10;
    private int borderThickness = 2;
    Color backColor;
	

	public RkButton(Color backColor,Color hoverColor,Color pressedColor,String text) {
		
		 super(text);
		 setContentAreaFilled(false);
		 setFocusPainted(false);
		 setBorderPainted(false);
		 
		 
			 
       setBackground	(backColor);
       setForeground(Color.WHITE);
       this.backColor=backColor;
       this.hoverColorBtn = hoverColor;
       this.pressedBtn = pressedColor;
       listerners();	
	}  
    
	public RkButton(String text) {
		 super(text);
		 setContentAreaFilled(false);
		 setFocusPainted(false);
		 setBorderPainted(false);
			 
        setBackground(backColor);
        setForeground(Color.WHITE);
        backColor=new Color(114,186,240);
        hoverColorBtn = new Color(71,117,150);
        pressedBtn = new Color(100, 100, 100);
        listerners();	
	}
	
	private void listerners() {
        addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseEntered(MouseEvent e) {
        		setBackground(hoverColorBtn);
        		super.mouseEntered(e);
        	}
        	
        	@Override
        	public void mouseExited(MouseEvent e) {
        		setBackground(backColor);
        		super.mouseExited(e);
        	}
        	
        	@Override
        	public void mousePressed(MouseEvent e) {
        		setBackground(pressedBtn);
        		super.mousePressed(e);
        	}
        	@Override
        	public void mouseReleased(MouseEvent e) {
        		setBackground(backColor);
        		super.mouseReleased(e);
        	}
      
		});
        
        addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				setBackground(backColor);
				
				
			}
			
			@Override
			public void focusGained(FocusEvent e) {
			setBackground(hoverColorBtn);
				
			}
		});
	}
	  protected void paintComponent(Graphics g) {
	    
		  Graphics2D g2d = (Graphics2D) g.create();
	        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

	        if (getModel().isPressed()) {
	            g2d.setColor(pressedBtn);
	        } else if (getModel().isRollover()||hasFocus()) {
	            g2d.setColor(hoverColorBtn);
	        }  else {
	            g2d.setColor(backColor);
	        }
	        
	    

	        int width = getWidth();
	        int height = getHeight();

	        g2d.fillRoundRect(0, 0, width, height, borderRadius, borderRadius);

	        g2d.setColor(getForeground());
	        g2d.setFont(getFont());
	        FontMetrics metrics = g2d.getFontMetrics();
	        int x = (width - metrics.stringWidth(getText())) / 2;
	        int y = ((height - metrics.getHeight()) / 2) + metrics.getAscent();
	        g2d.drawString(getText(), x, y);

	        g2d.setStroke(new BasicStroke(borderThickness));
	        g2d.setColor(backColor);
	        g2d.drawRoundRect(borderThickness / 2, borderThickness / 2, width - borderThickness, height - borderThickness, borderRadius, borderRadius);

	        g2d.dispose();
	    }

	

}
