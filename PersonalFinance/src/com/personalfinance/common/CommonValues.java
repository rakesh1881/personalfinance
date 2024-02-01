package com.personalfinance.common;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.time.LocalDate;
import java.util.Date;

public class CommonValues {
	
	public static final Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	public static final int screenWidth=dimension.width;
	public static final int screenHeight=dimension.height;
	// Top Panel
	public static final int topPanelHeight=50,topPanelX=0,topPanelY=0;  
	// Bottom Panel
	public static final int bottomPanelHeight=70,bottomtopPanelX=0,bottomPanelY=dimension.height-bottomPanelHeight;  
	// Content Panel
	public static final int ContentPanelHeight=dimension.height-bottomPanelHeight-topPanelHeight;
	public static final int contentPanelX=0,contentPanelY=topPanelHeight;
	public static boolean isUpdate=false;
	public static  LocalDate localdate = LocalDate.now();
	public static  Date currentDate = java.sql.Date.valueOf(localdate);
	
	
	

}
