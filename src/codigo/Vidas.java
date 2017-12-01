package codigo;

import java.awt.Color;
import java.awt.Font;

import acm.graphics.GLabel;
import acm.graphics.GRect;

public class Vidas extends GRect{
	//Declaración de Sistema de vidas:--------------------------------------------------------------------------------------------
	GLabel auxiliarvida = new GLabel("");													//Caja de las vidas
	int numvidas= 3;																		//Numero de Vidas
	GLabel textovida = new GLabel("");														//Para dar estilo a las vidas
	//----------------------------------------------------------------------------------------------------------------------------
	
	//este constructor hace lo mismo que el del marcador-------------------------------------------------------------------------- 
	public Vidas(double width, double height)
	{
		super(width, height);
		setFilled (true);
		setFillColor(Color.WHITE);
		textovida.setLabel("          " + numvidas);
		textovida.setFont(new Font ("Times New Roman", Font.BOLD, 11));
		
	}
	//----------------------------------------------------------------------------------------------------------------------------
	
	//aqui añadiremos las cajas al Arcanoid---------------------------------------------------------------------------------------
	public void dibuja(Arkanoid _arkanoid)
	{
		_arkanoid.add(this, _arkanoid.getWidth() -115, getY() +60);
		_arkanoid.add(auxiliarvida, _arkanoid.getWidth() -111, getY()+150); 
		_arkanoid.add(textovida, _arkanoid.getWidth() -110, getY()+25);
		_arkanoid.add(_arkanoid.heart, _arkanoid.getWidth() -110, getY()+10 );
		
	}
	//----------------------------------------------------------------------------------------------------------------------------
	
	//Actualizar el marcador------------------------------------------------------------------------------------------------------
	public void actualizaMarcadorVidas(int vida)
	{
		numvidas += vida;
		textovida.setLabel("          " + numvidas);
	}
	//----------------------------------------------------------------------------------------------------------------------------

}
