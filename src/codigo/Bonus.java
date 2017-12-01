package codigo;
import java.awt.Color;
import acm.graphics.GRect;

/*
 * Autor: Alberto Goujon Gutiérrez 
 * Aqui añadiremos el constructor del sistema de Bonus
 */
public class Bonus extends GRect
{
	/**
	 * 
	 * @param width -> Ancho del bono
	 * @param height -> Largo del bono
	 * @param _color -> Color del bono
	 */
	public Bonus(double width, double height, Color _color) 
	{
		super(width, height);
		setFilled(true);
		setFillColor(_color);
	}	
}