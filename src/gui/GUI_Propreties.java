package gui;

import java.awt.*;

import javax.swing.BorderFactory;

import javax.swing.*;
import javax.swing.border.*;

/*
 * 
 * 
 * some graphical satic properties
 */
public class GUI_Propreties {
	

	private static int size=90;
	private static int ratio=2;
	
	public static Dimension card_dimension=new Dimension(size,size/ratio);
	public static Color card_default_background=Color.lightGray;
	public static Color card_color_placed=Color.green;
	public static Font card_default_font=new Font("Helvetica", Font.PLAIN, 10);
	public static Border card_default_border=BorderFactory.createLineBorder(Color.DARK_GRAY);
	
	public static Color timeBox_color_placed=Color.white;

	public static Dimension size_label_dialog=new Dimension(320,20);
	public static Dimension dialog_size=new Dimension(670,180);
}