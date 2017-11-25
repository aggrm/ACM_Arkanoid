package codigo;

import java.awt.Color;
import java.awt.event.MouseEvent;

import acm.graphics.GLabel;
import acm.graphics.GRect;

/*
 * Autor: Alberto Goujon Gutiérrez
 * 
 * El Arkanoid pero orientado a objetos
 */

public class Arkanoid extends acm.program.GraphicsProgram{

	Pelota pelota1 = new Pelota(7, Color.GREEN);
	Barra barra1 = new Barra(60, 15, Color.RED);
	int anchoLadrillo = 25;
	int altoLadrillo = 15;
	int espacioMenu = 130;

	//Marcador del arkanoid
	Marcador marcador = new Marcador(80, 40);
	Vidas vidaAbajo = new Vidas (20,40);
	GLabel hasPerdido = new GLabel("GAME OVER");

	public void init(){
		addMouseListeners();
		setSize(550, 600);
		GRect lateral = new GRect(3, getWidth());
		lateral.setFilled(true);
		add(lateral, getWidth()- espacioMenu - lateral.getWidth()- pelota1.getWidth(), 0);
		add(pelota1, 50, getHeight()*0.60 - pelota1.getHeight());
		add(barra1, 0 , getHeight()*0.80);



	}

	public void run()
	{
		dibujaNivel01();
		marcador.dibuja(this);
		vidaAbajo.dibuja(this);
		while (true)
			//Vidas.numvidas >= 1 && Vidas.numvidas <= 3
		{
			pelota1.muevete(this);
			//barra1.mueveBarra((int)pelota1.getX(), getWidth()- espacioMenu);
			pause(5);
			//	if(Vidas.numvidas <= 0)
			//	{
			//		add(hasPerdido, getWidth()/3.5, getHeight()/2.5);
			//	}
		}
	}

	public void mouseMoved (MouseEvent evento)
	{
		barra1.mueveBarra(evento.getX(), getWidth()- espacioMenu);
	}

	private void dibujaNivel01()
	{
		int numLadrillos = 16; 	
		for (int i=0; i < 10; i++)
		{
			for(int j=0; j < numLadrillos; j++)
			{
				Ladrillo miLadrillo =
						new Ladrillo(j * anchoLadrillo, i * altoLadrillo, anchoLadrillo, altoLadrillo,
								Color.BLUE);

				add(miLadrillo);
				pause(7);
			}
		}

	}

	private void dibujaNivel02()
	{
		int numLadrillos = 14; 	
		for (int j=0; j < numLadrillos; j++)
		{
			for(int i=j; i < numLadrillos; i++)
			{
				Ladrillo miLadrillo =
						new Ladrillo((anchoLadrillo* i - anchoLadrillo*j/2 + ((getWidth()- 100) - anchoLadrillo * numLadrillos)/2),
								altoLadrillo*j,
								anchoLadrillo, 
								altoLadrillo, 
								Color.BLUE);

				add(miLadrillo);
				pause(7);
			}
		}
	}	
}












