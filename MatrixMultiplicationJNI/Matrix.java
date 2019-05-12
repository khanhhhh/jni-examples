public class Matrix {
	int height;
	int width;
	double[] data;

	public Matrix(){
		height = 0;
		width = 0;
		data = null;
	}
	public Matrix(Matrix m) {
		height = m.height;
		width = m.width;
		data = new double[height*width];
		for (int i=0; i<height*width; i++)
			data[i] = m.data[i];
	}
	public Matrix(int height, int width) {
		this.height = height;
		this.width = width;
		data = new double[height*width];
	}
	public Matrix(int height, int width, double[] data) {
		this(height, width);
		for (int i=0; i<height*width; i++)
			this.data[i] = data[i];
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
	// MATRIX ADDITION
	public static Matrix addition(Matrix m1, Matrix m2) {
		if ((m1.height != m2.height) || (m1.width != m2.width)) {
			throw new RuntimeException("Addition wrong dimension!");
		} else {
			Matrix out = new Matrix(m1.height, m1.width);
			for (int h=0; h<out.height; h++)
			for (int w=0; w<out.width; w++)
				out.set(h, w, (m1.get(h,w) + m2.get(h,w)));
			return out;
		}
	}
	public static Matrix multiplication(Matrix m1, Matrix m2) {
		if (m1.width != m2.height) {
			throw new RuntimeException("Multiplication wrong dimension!");
		} else {
			Matrix out = new Matrix(m1.height, m2.width);
			for (int h=0; h<out.height; h++)
			for (int w=0; w<out.width; w++) {
				double value = 0;
				for (int x=0; x<m1.width; x++)
					value += m1.get(h, x) * m2.get(x, w);
				out.set(h, w, value);
			}
			return out;
		}
	}
}
