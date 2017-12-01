package codigo;

import java.awt.Color;
import java.awt.Font;
import acm.graphics.GLabel;
import acm.graphics.GRect;

public class Niveles extends GRect
{
	//Declaramos lo que va a tener el contador de niveles-------------------------------------------------------------------------
	GLabel auxiliarNivel = new GLabel("");														//Caja del nivel
	int numnivel= 3;																			//Inicio nivel
	GLabel textonivel = new GLabel("");															//Para el estilo de letra
	//----------------------------------------------------------------------------------------------------------------------------
	
	//este constructor hace lo mismo que el del marcador--------------------------------------------------------------------------
	public Niveles(double width, double height)
	{
		super(width, height);
		setFilled (true);
		setFillColor(Color.WHITE);
		textonivel.setLabel("Nivel: 1");
		textonivel.setFont(new Font ("Times New Roman", Font.BOLD, 11));
	}
	//----------------------------------------------------------------------------------------------------------------------------
	
	//Para añadirlo en Arkanoid---------------------------------------------------------------------------------------------------
	public void dibuja(Arkanoid _arkanoid)
	{
		_arkanoid.add(this, _arkanoid.getWidth() -115, getY()+ 120);
		_arkanoid.add(auxiliarNivel, _arkanoid.getWidth() -111, getY()+150); 
		_arkanoid.add(textonivel, _arkanoid.getWidth() -110, getY()+25);
	}
	//----------------------------------------------------------------------------------------------------------------------------
	
	//Actualiza el marcador de los niveles----------------------------------------------------------------------------------------
	public void actualizaMarcadorNivel(int nivel)
	{
		numnivel -= nivel;
		textonivel.setLabel("Nivel: " + numnivel);
	}
	//----------------------------------------------------------------------------------------------------------------------------
	
}



