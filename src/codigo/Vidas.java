package codigo;

import java.awt.Color;
import java.awt.Font;

import acm.graphics.GLabel;
import acm.graphics.GRect;

public class Vidas extends GRect{
	//Declaración de Sistema de vidas:
		GLabel axiliarvida = new GLabel("");
		int vidas= 3;
		GLabel textovida = new GLabel("");
	public Vidas(double width, double height)
	{
		super(width, height);
		setFilled (true);
		setFillColor(Color.WHITE);
		textovida.setLabel("3");
		textovida.setFont(new Font ("Verdana", Font.BOLD, 11));
	}
			
	public void actualizaMarcador(int vida)
	{
		vidas += vida;
		textovida.setLabel("" + vida);
	}
	

}
