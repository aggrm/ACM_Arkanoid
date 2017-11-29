package codigo;

import java.awt.Color;
import java.awt.Font;
import acm.graphics.GLabel;
import acm.graphics.GRect;

public class Marcador extends GRect{

	//Declaración de Sistema de puntuación:
	GLabel texto = new GLabel("");
	int puntuacion= 0;

	//Este constructor es chachiguay ya que contabilizara el  puntuación
	public Marcador(double width, double height) 
	{
		super(width, height);
		setFilled(true);
		setFillColor(Color.WHITE);
		texto.setLabel("0" + puntuacion);
		texto.setFont(new Font ("Times New Roman", Font.BOLD, 11));	
	}
	//dibujara en la pantalla de arkanoid el marcador
	public void dibuja(Arkanoid _arkanoid)
	{
		_arkanoid.add(this, _arkanoid.getWidth() -115, getY());
		_arkanoid.add(texto, _arkanoid.getWidth() -111, getY()+30);
	}
	//actualizara el marcador 
	public void actualizaMarcador(int puntos)
	{
		puntuacion += puntos;
		texto.setLabel("Puntuación: " + puntuacion);

	}



}