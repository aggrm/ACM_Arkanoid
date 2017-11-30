package codigo;
/*
 * Autor: Alberto Goujon
 * La clase pelota es la que vamos a utilizar para
 * el juego del arkanoid
 * Tiene dos constructores
 */
import java.awt.Color;
import acm.graphics.GObject;
import acm.graphics.GOval;

public class Pelota extends GOval{

	double xVelocidad = 1; 																		//velocidad de la bola en el eje X
	double yVelocidad = -1; 																	//velocidad de la bola en el eje Y


	/**
	 * Este es el constructor básico, que es idéntico
	 * al de la clase GOval
	 * @param _ancho
	 * @param _alto
	 */
	public Pelota(double _ancho, double _alto)													//Constructor de la pelota
	{
		super(_ancho, _alto);
	}

	/**
	 * Este es el constructor dabuti que permite
	 * pasar el color como parámetro 
	 * 
	 * @param _ancho indica el ancho y el alto de la bola
	 * @param _color
	 */
	public Pelota(double _ancho, Color _color)													//Constructor parámetro
	{
		super(_ancho, _ancho);
		if (_ancho <=0){
			setSize(1, 1);
		}
		setFillColor(_color);
		setFilled(true);
	}
	/**
	 * se encarga de mover a la pelota y chequear si ha habido colisiones
	 * 
	 */
	public void muevete(Arkanoid _arkanoid)														//La pelota se movera en funcion de comprobar si ha colisionado
	{
		
		//chequea si ha chocado con las paredes izq o derecha----------------------------------------------------------------------------------------------------
			if (getX() + getWidth() >= _arkanoid.getWidth() - _arkanoid.espacioMenu|| getX()<0)
			{
				xVelocidad *= -1; 
			}
		//-------------------------------------------------------------------------------------------------------------------------------------------------------
		
		//chequea si ha chocado con el techo---------------------------------------------------------------------------------------------------------------------
			if (this.getY()<0)
			{
				yVelocidad *= -1;
			}
		//-------------------------------------------------------------------------------------------------------------------------------------------------------
		
		//Las siguentes 3 condiciones son creadas para que el juego funcione con vidas---------------------------------------------------------------------------
			if(this.getY() >= _arkanoid.getHeight() && _arkanoid.vidaAbajo.numvidas >=3)
			{
				setLocation(_arkanoid.getWidth()/2, _arkanoid.getHeight()*0.80 - this.getHeight());
				_arkanoid.vidaAbajo.actualizaMarcadorVidas(-1);
			}
			if(this.getY() >= _arkanoid.getHeight() && _arkanoid.vidaAbajo.numvidas >=2)
			{
				setLocation(_arkanoid.getWidth()/2, _arkanoid.getHeight()*0.80 - this.getHeight());
				_arkanoid.vidaAbajo.actualizaMarcadorVidas(-1);
			}
			if(this.getY() >= _arkanoid.getHeight() && _arkanoid.vidaAbajo.numvidas >=1)
			{
				setLocation(_arkanoid.getWidth()/2, _arkanoid.getHeight()*0.80 - this.getHeight());
				_arkanoid.vidaAbajo.actualizaMarcadorVidas(-1);
			}
		//-------------------------------------------------------------------------------------------------------------------------------------------------------
		
		//las siguietes condiciones seran para que la pelota compruebe la pared----------------------------------------------------------------------------------
			if(chequeaColision(getX(), getY(), _arkanoid))										
			{ 																					//chequeo la esquina superior izquierda
				if(chequeaColision(getX()+getWidth(), getY(), _arkanoid))
				{ 																				//chequeo la esquina superior derecha
					if(chequeaColision(getX(), getY()+getHeight(), _arkanoid))
					{ 																			//chequeo la esquina inferior izquierda
						if(chequeaColision(getX()+getWidth(), getY()+getHeight(), _arkanoid))
						{																	 	//chequeo la esquina inferior derecha
							
						}
					}
				}
			}
		//-------------------------------------------------------------------------------------------------------------------------------------------------------
		

		//voy a crear un metodo chequeacolision generico
		//que sirva para comprobar los choques entre la bola y los ladrillos
		//y la bola y el cursor
		
		move(xVelocidad, yVelocidad);
	}

	private boolean chequeaColision(double posX, double posY, Arkanoid _arkanoid)						//Este void comprobara las colisiones de la pelota
	{
		boolean noHaChocado = true;
		GObject auxiliar;
		auxiliar = _arkanoid.getElementAt(posX, posY);
		
		//Comprueba por todos los lados del bloque---------------------------------------------------------------------------------------------------------------
		if (auxiliar instanceof Ladrillo)
		{
			if ((int)auxiliar.getY() == (int)posY || (int)auxiliar.getY() + (int)auxiliar.getHeight() == (int)posY)
			{
				yVelocidad *= -1;
			}
			else if((int)auxiliar.getX() == (int)posX || (int)auxiliar.getX() + (int)auxiliar.getWidth() == (int)posX)
			{
				xVelocidad *= -1;
			}
			_arkanoid.remove(auxiliar);
			_arkanoid.marcador.actualizaMarcador(20);
			noHaChocado = false;
		}
		//-------------------------------------------------------------------------------------------------------------------------------------------------------
		
		//Comprueba cuando ha chocado con la barra---------------------------------------------------------------------------------------------------------------
		else if (auxiliar instanceof Barra)																
		{
			//vamos a modificar el rebote de la pelota con el cursor
			//para que no sea igual 

			//calculo la posición x del 
			double centroBola= getX() + getWidth()/2;
			if(centroBola > auxiliar.getX() + getWidth()/3 && 
					centroBola < auxiliar.getX() + 2 * getWidth()/3)				//Dos tercios de la barra
			{
				yVelocidad = -1;
			}
			else if(centroBola > auxiliar.getX()/2 + auxiliar.getWidth()/3 && 
					centroBola < auxiliar.getX()/2 + 2 * auxiliar.getWidth()/3)		//No tan centro
			{
				yVelocidad = -0.8;
			}
			else if(centroBola > auxiliar.getX()/2 + auxiliar.getWidth()/3 && 
					centroBola < auxiliar.getX()/2 + 1.5  * auxiliar.getWidth()/3)	//Dentro de los dos tercios
			{
				yVelocidad = -0.7;
			}
			else if(centroBola > auxiliar.getX()/2 + auxiliar.getWidth()/3 && 
					centroBola < auxiliar.getX()/2 + 1 * auxiliar.getWidth()/3)		//Dentro de los dos tercios
			{
				yVelocidad = -0.4;
			}
			else																																//Esquinas de la barra
			{
				yVelocidad = -0.5;
			}
			noHaChocado = false;
		}
		//-------------------------------------------------------------------------------------------------------------------------------------------------------

		//Sistema de bonus---------------------------------------------------------------------------------------------------------------------------------------
		else if(auxiliar instanceof Bonus)
		//Especificamos que ocurre cuando tocas el Bonus
		{
			_arkanoid.largoBarra +=30; 																		//Cambiamos  el integer de la barra
			_arkanoid.barra1.setSize(_arkanoid.largoBarra, _arkanoid.barra1,getHeight());					//Cambiamos el tamaño de la barra
			_arkanoid.tamañoPelota +=10;																	//Cambiamos  el integer de la pelota																		//Redimensionamos el tamño de la pelota
			_arkanoid.remove(((Bonus)auxiliar));																		//Quitamos el bonus ya que se ha chocado 
			noHaChocado = false;
			
			
		}
		//-------------------------------------------------------------------------------------------------------------------------------------------------------
		return noHaChocado;

	}





}










