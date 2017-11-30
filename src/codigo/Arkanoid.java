package codigo;

import java.awt.Color;
import java.awt.event.MouseEvent;
import acm.graphics.GLabel;
import acm.graphics.GRect;
import acm.util.RandomGenerator;


/*
 * Autor: Alberto Goujon Gutiérrez
 * 
 * El Arkanoid pero orientado a objetos
 */

public class Arkanoid extends acm.program.GraphicsProgram{

	//Integers de pelota, barra y bono
		int anchoLadrillo = 25;																		//Ancho del ladrillo
		int altoLadrillo = 15;																		//Alto del ladrillo
		int largoBarra= 50;																			//Largo barra
		int tamañoPelota = 7;																		//Tamaño Pelota
	
	//Especificaciones de tamaño de los diferentes objetos											
	Pelota pelota1 = new Pelota(tamañoPelota, Color.GREEN);											//Pelota 1
	Barra barra1 = new Barra(largoBarra, 7, Color.RED);												//Barra
	Bonus bonus1 = new Bonus(15,25,Color.WHITE);
	
	//Para las variables random
	RandomGenerator random = new RandomGenerator();													//Random	
		
	
	//Menu del arkanoid
	Marcador marcador = new Marcador(100, 40);														//Caja del marcador
	Vidas vidaAbajo = new Vidas (50,40);															//Caja de las vidas
	Niveles levelUp = new Niveles (50, 40);
	int espacioMenu = 130;																			//Barra del menú
	
	//Fianles del juego
	GLabel hasPerdido = new GLabel("GAME OVER");													//Texto de has perdido
	GLabel hasGanado = new GLabel("YOU WIN!");

	public void init(){
		
		addMouseListeners();
		setSize(550, 600);																			//tamaño de la pantalla
		GRect lateral = new GRect(3, getWidth());													//línea del menú
		lateral.setFilled(true);																	//línea del menú
		add(lateral, getWidth()- espacioMenu - lateral.getWidth()- pelota1.getWidth(), 0);			//lo añade a la pantalla la línea del menú
		add(pelota1, 50, getHeight()*0.60 - pelota1.getHeight());									//añade a la pelota en la pantalla
		add(barra1, 0 , getHeight()*0.80);															//añade la barra del juego
		setBackground(Color.GRAY);



	}
	
	public void run()
	{
		dibujaNivel01();
		//add(bonus1,random.nextInt(15-25),random.nextInt(15-300));
		waitForClick();
		marcador.dibuja(this);																		//añade el marcador en la pantalla
		vidaAbajo.dibuja(this);																		//añade el las vidas en la pantalla
		levelUp.dibuja(this);
		actualizaNivel();																			//Actaulizara el nivel
		
	}

	public void mouseMoved (MouseEvent evento)														//Condición que la barra hace para qu no se pase del espacio del menú
	{
		barra1.mueveBarra(evento.getX(), getWidth()- espacioMenu);
	}

	

	private void dibujaNivel01()																	//Nivel de líneas de ladrillos
	{
		int numLadrillos = 7; 	
		for (int i=0; i < numLadrillos; i++)
		{
			for(int j=0; j < 12; j++)
			{
				Ladrillo miLadrillo =
						new Ladrillo(j * anchoLadrillo + 50, i * altoLadrillo + 60, anchoLadrillo, altoLadrillo,
								random.nextColor());

				add(miLadrillo);
				pause(7);
			}
		}

	}

	private void dibujaNivel02()																	//Nive2 pirámide																		
	{
		int numLadrillos = 14; 	
		for (int j=0; j < numLadrillos; j++)
		{
			for(int i=j; i < numLadrillos; i++)
			{
				Ladrillo miLadrillo =
						new Ladrillo((anchoLadrillo* i - anchoLadrillo*j/2 + ((getWidth()- 140) - anchoLadrillo * numLadrillos)/2),
								altoLadrillo*j,
								anchoLadrillo, 
								altoLadrillo, 
								random.nextColor());

				add(miLadrillo);
				pause(7);
			}
		}
	}	

	private void dibujaNivel03()																	//Nive3 mitad  de pirámide																		
	{
		int numLadrillos = 14; 	
		for (int j=0; j < numLadrillos; j++)
		{
			for(int i=j; i < numLadrillos; i++)
			{
				Ladrillo miLadrillo =
						new Ladrillo((anchoLadrillo* i - anchoLadrillo*j + 
								((getWidth() -180) - anchoLadrillo * numLadrillos)/2),
								altoLadrillo*j,
								anchoLadrillo, 
								altoLadrillo, 
								random.nextColor());

				add(miLadrillo);
				pause(7);
			}
		}
	}

	private void actualizaNivel()																	//Condición para que pase entre niveles  i que el juego uncien a partir de vidas
	{
		while (vidaAbajo.numvidas >=1 && vidaAbajo.numvidas <= 3)									//hace que funcione en funcion del número de vidas
		{
			pelota1.muevete(this);																	//hace que la pelota se mueva
			barra1.mueveBarra((int)pelota1.getX(), getWidth()- espacioMenu);						//truco para seguir a  la pelota
			pause(0);
			//ENLACE ENTRE NIVELE 1 AL 2
			if(marcador.puntuacion >= 1680)
			{
				dibujaNivel02(); 
				levelUp.actualizaMarcadorNivel(+1);
				waitForClick();
				pelota1.setLocation( 50, getHeight()*0.70 - pelota1.getHeight());
				while (vidaAbajo.numvidas >=1 && vidaAbajo.numvidas <= 3)							//hace que funcione en funcion del número de vidas
				{
					pelota1.muevete(this);															//hace que la pelota se mueva
					barra1.mueveBarra((int)pelota1.getX(), getWidth()- espacioMenu);				//truco para seguir a  la pelota
					pause(0);
					//ENLACE ENTRE EL NIVEL 2 AL 3
					if(marcador.puntuacion >= 3780)
					{
						dibujaNivel03();
						levelUp.actualizaMarcadorNivel(-1);
						waitForClick();
						pelota1.setLocation( 50, getHeight()*0.70 - pelota1.getHeight());
						while (vidaAbajo.numvidas >=1 && vidaAbajo.numvidas <= 3)					//hace que funcione en funcion del número de vidas
						{
							pelota1.muevete(this);													//hace que la pelota se mueva
							barra1.mueveBarra((int)pelota1.getX(), getWidth()- espacioMenu);		//truco para seguir a  la pelota
							pause(0);
							if(marcador.puntuacion >= 5880)
							{
								pelota1.setLocation( 50, getHeight()*0.70 - pelota1.getHeight());
								remove(pelota1);
								remove(barra1);
								add(hasGanado, getWidth()/3.5, getHeight()/2.5);
							}
						}
						
					}
				}				
			}
			
			if(vidaAbajo.numvidas <= 0)
			{
				add(hasPerdido, getWidth()/3.5, getHeight()/2.5);
			}

		}
	}
}












