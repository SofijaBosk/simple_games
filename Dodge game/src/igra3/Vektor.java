package igra3;


public class Vektor implements Cloneable {

	protected double x,y;
	
	public Vektor(double x, double y) {
		this.x = x;
		this.y = y;
	}
	public synchronized double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	@Override
	protected Object clone() throws CloneNotSupportedException {		
		return new Vektor(x,y);
	}

	public Vektor pomnozi(double d) {
		x*=d; y*=d;
		return this;
	}
	
	public synchronized Vektor saberi(Vektor v) {
		x+=v.x; y+=v.y;
		return this;
	}
	
	
}
