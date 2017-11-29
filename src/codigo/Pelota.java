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
import java.awt.event.MouseEvent;

public class Pelota extends GOval{

	double xVelocidad = 1; //velocidad de la bola en el eje X
	double yVelocidad = -1; //velocidad de la bola en el eje Y


	/**
	 * Este es el constructor básico, que es idéntico
	 * al de la clase GOval
	 * @param _ancho
	 * @param _alto
	 */
	public Pelota(double _ancho, double _alto){
		super(_ancho, _alto);
	}

	/**
	 * Este es el constructor dabuti que permite
	 * pasar el color como parámetro 
	 * 
	 * @param _ancho indica el ancho y el alto de la bola
	 * @param _color
	 */
	public Pelota(double _ancho, Color _color){
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
	public void muevete(Arkanoid _arkanoid){
		
		//chequea si ha chocado con las paredes izq o derecha
		if (getX() + getWidth() >= _arkanoid.getWidth() - _arkanoid.espacioMenu
				|| getX()<0){
			xVelocidad *= -1; 
		}
		
		//chequea si ha chocado con el techo
		if (this.getY()<0){
			yVelocidad *= -1;
		}
		//Las siguentes condiciones son creadas para que el juego funcione con vidas
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
		
		//las siguietes condiciones seran para que la pelota compruebe la pared
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
		

		//voy a crear un metodo chequeacolision generico
		//que sirva para comprobar los choques entre la bola y los ladrillos
		//y la bola y el cursor
		
		move(xVelocidad, yVelocidad);
	}

	private boolean chequeaColision(double posX, double posY, Arkanoid _arkanoid){
		boolean noHaChocado = true;
		GObject auxiliar;
		auxiliar = _arkanoid.getElementAt(posX, posY);
		
		if (auxiliar instanceof Ladrillo)
		{
			if (auxiliar.getY() == posY || auxiliar.getY() + auxiliar.getHeight() == posY)
			{
				yVelocidad *= -1;
			}
			else if(auxiliar.getX() == posX || auxiliar.getX() + auxiliar.getWidth() == posX)
			{
				xVelocidad *= -1;
			}
			_arkanoid.remove(auxiliar);
			_arkanoid.marcador.actualizaMarcador(20);
			noHaChocado = false;
		}
		else if (auxiliar instanceof Barra)
		{
			//vamos a modificar el rebote de la pelota con el cursor
			//para que no sea igual 

			//calculo la posición x del 
			double centroBola= getX() + getWidth()/2;
			if(centroBola > auxiliar.getX() + getWidth()/3 && 
					centroBola < auxiliar.getX() + 2 * getWidth()/3)
			{
				yVelocidad = -1;
			}
			else
			{
				yVelocidad = -0.5;
			}
			yVelocidad = -1;
			noHaChocado = false;
		}
		return noHaChocado;

	}





}










