public class JavaMatrix {
	int height;
	int width;
	double[] data;

	public JavaMatrix(){
		height = 0;
		width = 0;
		data = null;
	}
	public JavaMatrix(JavaMatrix m) {
		height = m.height;
		width = m.width;
		data = new double[height*width];
		for (int i=0; i<height*width; i++) {
			data[i] = m.data[i];
		}
	}
	public JavaMatrix(int height, int width) {
		this.height = height;
		this.width = width;
		data = new double[height*width];
	}
	public JavaMatrix(int height, int width, double[] data) {
		this(height, width);
		for (int i=0; i<height*width; i++) {
			this.data[i] = data[i];
		}
	}
	private double get(int h, int w) {
		return data[h*width + w];
	}
	private void set(int h, int w, double value) {
		data[h*width + w] = value;
	}

	public String toString() {
		String out = new String();
		for (int h=0; h<height; h++) {
			for (int w=0; w<width; w++) {
				out += "\t";
				out += Double.toString(get(h, w));
			}
			out += "\n";
		}
		return out;
	}

}
