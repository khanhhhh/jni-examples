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
		for (int i=0; i<height*width; i++)
			data[i] = m.data[i];
	}
	public JavaMatrix(int height, int width) {
		this.height = height;
		this.width = width;
		data = new double[height*width];
		for (int i=0; i<height*width; i++)
			data[i] = 0.0;
	}
	public JavaMatrix(int height, int width, double[] data) {
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
	public JavaMatrix index(int h1, int h2, int w1, int w2) {
		JavaMatrix out = new JavaMatrix(h2-h1, w2-w1);
		for (int h=0; h<out.height; h++)
		for (int w=0; w<out.width; w++)
			out.set(h, w, get(h1+h, w1+w));
		return out;
	}
	public void paste(int h1, int w1, JavaMatrix m) {
		for (int h=0; h<m.height; h++)
		for (int w=0; w<m.width; w++)
			set(h1+h, w1+w, m.get(h,w));
	}
	// MATRIX ADDITION
	public static JavaMatrix addition(JavaMatrix m1, JavaMatrix m2) {
		if ((m1.height != m2.height) || (m1.width != m2.width)) {
			throw new RuntimeException("Addition wronng dimension!");
		} else {
			JavaMatrix out = new JavaMatrix(m1.height, m1.width);
			for (int h=0; h<out.height; h++)
			for (int w=0; w<out.width; w++)
				out.set(h, w, (m1.get(h,w) + m2.get(h,w)));
			return out;
		}
	}
	public static JavaMatrix subtraction(JavaMatrix m1, JavaMatrix m2) {
		if ((m1.height != m2.height) || (m1.width != m2.width)) {
			throw new RuntimeException("Addition wronng dimension!");
		} else {
			JavaMatrix out = new JavaMatrix(m1.height, m1.width);
			for (int h=0; h<out.height; h++)
			for (int w=0; w<out.width; w++)
				out.set(h, w, (m1.get(h,w) - m2.get(h,w)));
			return out;
		}
	}
	// MATRIX MULTIPLICATION DIVIDE AND CONQUER
	private JavaMatrix zeropad2n(int n) {
		int dim = 1; for (int i=0; i<n; i++) dim *= 2;
		if ((dim < height) || (dim < width)) {
			throw new RuntimeException("ZeroPad failed!");
		} else {
			JavaMatrix out = new JavaMatrix(dim, dim);
			for (int h=0; h<height; h++)
			for (int w=0; w<width; w++)
				out.set(h, w, get(h, w));
			return out;
		}
	}
	private static int round2n(int max) {
		int n = 0; int dim = 1;
		while (max > dim) {
			n++;
			dim *= 2;
		}
		return n;
	}
	private static int getmax3(int a, int b, int c) {
		int max = a;
		max = (max > b) ? max : b;
		max = (max > c) ? max : c;
		return max;
	}
	public static JavaMatrix matmul(JavaMatrix m1, JavaMatrix m2) {
		if (m1.width != m2.height) {
			throw new RuntimeException("Matmul wrong dimension!");
		} else {
			int max = getmax3(m1.height, m1.width, m2.width);
			int n = round2n(max);
			JavaMatrix out2n = matmul2n(m1.zeropad2n(n), m2.zeropad2n(n));
			JavaMatrix out = out2n.index(0, m1.height, 0, m2.width);
			return out;
		}
	}
	// MATRIX MULTIPLICATION 2N IMPLEMENTATION
	private JavaMatrix[] split4() {
		int half = height/2;
		JavaMatrix[] out = new JavaMatrix[4];
		out[0] = index(0, half, 0, half);
		out[1] = index(0, half, half, 2*half);
		out[2] = index(half, 2*half, 0, half);
		out[3] = index(half, 2*half, half, 2*half);
		return out;
	}
	private static JavaMatrix merge4(JavaMatrix[] splits) {
		int half = splits[0].height;
		JavaMatrix out = new JavaMatrix(2*half, 2*half);
		out.paste(0,0, splits[0]);
		out.paste(0, half, splits[1]);
		out.paste(half, 0, splits[2]);
		out.paste(half, half, splits[3]);
		return out;
	}
	private static JavaMatrix matmul2n(JavaMatrix m1, JavaMatrix m2) {
		if (m1.height == 1) {
			return new JavaMatrix(1, 1, new double[]{m1.get(0,0) * m2.get(0,0)});
		} else {
			JavaMatrix[] splits1 = m1.split4();
			JavaMatrix[] splits2 = m2.split4();
			JavaMatrix a = splits1[0];
			JavaMatrix b = splits1[1];
			JavaMatrix c = splits1[2];
			JavaMatrix d = splits1[3];
			JavaMatrix e = splits2[0];
			JavaMatrix f = splits2[1];
			JavaMatrix g = splits2[2];
			JavaMatrix h = splits2[3];
			JavaMatrix[] splitsOut = new JavaMatrix[4];
			
			// STRASSEN'S METHOD 
			JavaMatrix p1 = matmul2n(a, subtraction(f, h));
			JavaMatrix p2 = matmul2n(addition(a, b), h);
			JavaMatrix p3 = matmul2n(addition(c, d), e);
			JavaMatrix p4 = matmul2n(d, subtraction(g, e));
			JavaMatrix p5 = matmul2n(addition(a, d), addition(e, h));
			JavaMatrix p6 = matmul2n(subtraction(b, d), addition(g, h));
			JavaMatrix p7 = matmul2n(subtraction(a, c), addition(e, f));

			splitsOut[0] = addition(subtraction(addition(p5, p4), p2), p6);
			splitsOut[1] = addition(p1, p2);
			splitsOut[2] = addition(p3, p4);
			splitsOut[3] = subtraction(subtraction(addition(p1, p5), p3), p7);

			return merge4(splitsOut);
		}
	}
}
