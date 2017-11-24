package codigo;

import java.awt.Color;
import java.awt.Font;

import acm.graphics.GLabel;
import acm.graphics.GRect;

public class Marcador extends GRect{

	//Declaración de Sistema de puntuación:
	GLabel texto = new GLabel("");
	int puntuacion= 0;
	
	public Marcador(double width, double height) 
	{
		super(width, height);
		setFilled(true);
		setFillColor(Color.WHITE);
		texto.setLabel("0");
		texto.setFont(new Font ("Verdana", Font.BOLD, 11));	
	}
	
	public void dibuja(Arkanoid _arkanoid)
	{
		_arkanoid.add(this, _arkanoid.getWidth() -60, getY());
		_arkanoid.add(texto, _arkanoid.getWidth() -60, getY()+30);
	}
	
	public void actualizaMarcador(int puntos)
	{
		puntuacion += puntos;
		texto.setLabel("" + puntuacion);
	}
	
	

}