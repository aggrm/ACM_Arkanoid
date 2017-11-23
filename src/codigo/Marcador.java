package codigo;

import java.awt.Color;
import java.awt.Font;

import acm.graphics.GLabel;
import acm.graphics.GRect;

public class Marcador extends GRect{

	GLabel texto = new GLabel("");

	public Marcador(double width, double height) 
	{
		super(width, height);
		setFilled(true);
		setFillColor(Color.WHITE);
		texto.setLabel("");
		texto.setFont(new Font ("Verdana", Font.BOLD, 18));	
	}
	
	
	public void dibuja(Arkanoid _arkanoid)
	{
		_arkanoid.add(this, _arkanoid.getWidth() -50, getY());
		_arkanoid.add(texto, _arkanoid.getWidth() -50, getY()+30);
	}



}