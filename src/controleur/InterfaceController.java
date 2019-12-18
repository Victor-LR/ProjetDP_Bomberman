package controleur;

public interface InterfaceController {

	public void step();
	public void start();
	public void run();
	public void stop();
	public void setTime(double time);
	public void changeMap(String name);
}
