package aims;

public class DigitalVideoDisc {
	private String title;
	private String category;
	private String director;
	private int length;
	private float cost;
	
	private static int nbDigitalVideoDisc = 0;
	int id;
	
    public DigitalVideoDisc(String title) {
        this.title = title;
        this.id = ++nbDigitalVideoDisc;
    }
    
    public DigitalVideoDisc(String title, String category, float cost) {
        this.category = category;
        this.title = title;
        this.cost = cost;
        this.id = ++nbDigitalVideoDisc;
    }
    
    public DigitalVideoDisc(String title, String category, String director, float cost) {
        this.director = director;
        this.category = category;
        this.title = title;
        this.cost = cost;
        this.id = ++nbDigitalVideoDisc;
    }
    
    public DigitalVideoDisc(String title, String category, String director, int length, float cost) {
        this.title = title;
        this.category = category;
        this.director = director;
        this.length = length;
        this.cost = cost;
        this.id = ++nbDigitalVideoDisc;
    }

	/**public DigitalVideoDisc(String title) {
		super();
		this.title = title;
	}**/

	public String getTitle() {
		return title;
	}

	public String getCategory() {
		return category;
	}

	public String getDirector() {
		return director;
	}

	public int getLength() {
		return length;
	}

	public float getCost() {
		return cost;
	}
	
	public float getId() {
		return id;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
}
