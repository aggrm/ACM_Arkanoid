package codigo;
/*
 * Autor: Alberto Goujon
 * La clase pelota es la que vamos a utilizar para el juego del arkanoid
 * Tiene dos constructores
 */
import java.awt.Color;
import acm.graphics.GObject;
import acm.graphics.GOval;

public class Pelota extends GOval{

	double xVelocidad = 1; 																//velocidad de la bola en el eje X
	double yVelocidad = -1; 															//velocidad de la bola en el eje Y


	/**
	 * Este es el constructor básico, que es idéntico
	 * al de la clase GOval
	 * @param _ancho
	 * @param _alto
	 */
	public Pelota(double _ancho, double _alto)											//Constructor de la pelota
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
	public Pelota(double _ancho, Color _color)											//Constructor parámetro
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
	public void muevete(Arkanoid _arkanoid)												//La pelota se movera en funcion de comprobar si ha colisionado
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
			if(this.getY() >= _arkanoid.getHeight() && _arkanoid.vidaAbajo.numvidas >=3)						//Juego con 3 vidas
			{
				setLocation(_arkanoid.getWidth()/2, _arkanoid.getHeight()*0.80 - this.getHeight());
				_arkanoid.vidaAbajo.actualizaMarcadorVidas(-1);
			}
			if(this.getY() >= _arkanoid.getHeight() && _arkanoid.vidaAbajo.numvidas >=2)						//Juego con 2 vidas
			{
				setLocation(_arkanoid.getWidth()/2, _arkanoid.getHeight()*0.80 - this.getHeight());
				_arkanoid.vidaAbajo.actualizaMarcadorVidas(-1);
			}
			if(this.getY() >= _arkanoid.getHeight() && _arkanoid.vidaAbajo.numvidas >=1)						//Juego con 1 vida
			{
				setLocation(_arkanoid.getWidth()/2, _arkanoid.getHeight()*0.80 - this.getHeight());
				_arkanoid.vidaAbajo.actualizaMarcadorVidas(-1);
			}
		//-------------------------------------------------------------------------------------------------------------------------------------------------------
		
		//las siguietes condiciones seran para que la pelota compruebe la colisión con la pared
			
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
		

		//voy a crear un metodo chequeacolision generico que sirva para comprobar los choques entre la bola y los ladrillos
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
			if(auxiliar.getY() == posY || auxiliar.getY() + auxiliar.getHeight() == posY)						//Entre las y comprueba el choque con el ladrillo
			{
				yVelocidad *= -1;
			}
			if(auxiliar.getX() == posX || auxiliar.getX() + auxiliar.getWidth() == posX)						//Entre las x comprueba el choque con el ladrillo	
			{
				xVelocidad *= -1;
			}
			_arkanoid.remove(auxiliar);																			//Borra el ladrillo
			_arkanoid.marcador.actualizaMarcador(20);															//Actualiza el marcador 20 puntos
			noHaChocado = false;
		}
		//-------------------------------------------------------------------------------------------------------------------------------------------------------
		
		//Comprueba cuando ha chocado con la barra---------------------------------------------------------------------------------------------------------------
		else if (auxiliar instanceof Barra)																
		{
			//vamos a modificar el rebote de la pelota con el cursor para que no sea igual el calculo de la posición x del 
			
			double centroBola= getX() + getWidth()/2;														//Centro de la pelota
			if(centroBola > auxiliar.getX() + getWidth()/3 && 
					centroBola < auxiliar.getX() + 2 * getWidth()/3)										//Dos tercios de la barra
			{
				yVelocidad = -1;
			}
		
			else if(centroBola > auxiliar.getX()/2 + auxiliar.getWidth()/3 && 
					centroBola < auxiliar.getX()/2 + 2  * auxiliar.getWidth()/3)							//Dentro de los tres tercios
			{
				yVelocidad = -0.75;
			}
			
			else																							//Esquinas de la barra
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
			_arkanoid.anchoBarra +=80; 																		//Cambiamos  el integer de la barra
			_arkanoid.barra1.setSize(_arkanoid.anchoBarra, _arkanoid.barra1.getHeight());					//Redimensionamos el tamaño de la barra
			_arkanoid.remove(((Bonus)auxiliar));															//Quitamos el bonus ya que se ha chocado 
			noHaChocado = false;		
		}
		//-------------------------------------------------------------------------------------------------------------------------------------------------------
		return noHaChocado;

	}





}










