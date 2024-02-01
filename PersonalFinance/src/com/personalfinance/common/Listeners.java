package com.personalfinance.common;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Listeners {

	public Listeners() {
	
	}

	public void changeFocustoNext(JButton focusbtn ,JTextField changefocusText) {
		
		focusbtn.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					changefocusText.requestFocus();
				}
				
				super.keyPressed(e);
			}
		});
		
	}
	
	public void changeFocustoNext( JTextField focusText,JButton changefocusbtn) {
		
		focusText.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					changefocusbtn.requestFocus();
				}
				
				super.keyPressed(e);
			}
		});
	}
		public void changeFocustoNext( JTextField focusText,JTextField changefocusText) {
			
			focusText.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if(e.getKeyCode()==KeyEvent.VK_ENTER) {
						changefocusText.requestFocus();
					}
					
					super.keyPressed(e);
				}
			});
		
	}
		public void changeFocustoNext( JTextField focusText,JPasswordField changepassfocus) {
			
			focusText.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if(e.getKeyCode()==KeyEvent.VK_ENTER) {
						changepassfocus.requestFocus();
					}
					
					super.keyPressed(e);
				}
			});
		
	}	
	
		public void changeFocustoNext( JPasswordField passfocus,JTextField changefocusText) {
			
			passfocus.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if(e.getKeyCode()==KeyEvent.VK_ENTER) {
						changefocusText.requestFocus();
					}
					
					super.keyPressed(e);
				}
			});
		
	}	
		
		
		public void changeFocustoNext( JComboBox<String> combofocus , JTextField changefocusText) {
			
			combofocus.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if(e.getKeyCode()==KeyEvent.VK_ENTER) {
						changefocusText.requestFocus();
					}
					
					super.keyPressed(e);
				}
			});
		
	}	
		public void changeFocustoNext( JComboBox<String> combofocus , JButton changefocusbtn) {
			
			combofocus.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if(e.getKeyCode()==KeyEvent.VK_ENTER) {
						changefocusbtn.requestFocus();
					}
					
					super.keyPressed(e);
				}
			});
	}	
		public void changeFocustoNext( JTextField focusText,JComboBox<String> changecombofocus  ) {
			
			focusText.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if(e.getKeyCode()==KeyEvent.VK_ENTER) {
						changecombofocus.requestFocus();
					}
					
					super.keyPressed(e);
				}
			});
		
	}	
		
	
		
		

	
}
