import java.util.Random;
public class main {
	public static void main(String[] args) {
		int size = 500;
		Random r = new Random(1234);
		double[] s1 = new double[size*size];
		double[] s2 = new double[size*size];
		for (int i=0; i<size*size; i++) {
			s1[i] = r.nextDouble();
			s2[i] = r.nextDouble();
		}

		JavaMatrix m1 = new JavaMatrix(size, size, s1);
		JavaMatrix m2 = new JavaMatrix(size, size, s2);

		long tic = System.nanoTime();

		JavaMatrix m3 = JavaMatrix.multiplication(m1, m2);

		long toc = System.nanoTime();

		System.out.println("Elapsed time: " + (toc-tic)/1000000 + " ms");

	}
}
