import java.util.Random;
public class main {
	private static long Jtest(int size, double[] s1, double[] s2) {
		Matrix m1 = new Matrix(size, size, s1);
		Matrix m2 = new Matrix(size, size, s2);

		long tic = System.nanoTime();
		Matrix m3 = Matrix.multiplication(m1, m2);
		long toc = System.nanoTime();
		return (toc-tic)/1000000;
	}
	private static long Ctest(int size, double[] s1, double[] s2) {
		CMatrix m1 = new CMatrix(size, size, s1);
		CMatrix m2 = new CMatrix(size, size, s2);

		long tic = System.nanoTime();
		CMatrix m3 = CMatrix.multiplication(m1, m2);
		long toc = System.nanoTime();
		return (toc-tic)/1000000;
	}
	public static void main(String[] args) {
		int size = 500;
		Random r = new Random(1234);
		double[] s1 = new double[size*size];
		double[] s2 = new double[size*size];
		for (int i=0; i<size*size; i++) {
			s1[i] = r.nextDouble();
			s2[i] = r.nextDouble();
		}
		long jtime = Jtest(size, s1, s2);
		long ctime = Ctest(size, s1, s2);

		System.out.println("Jtest: " + jtime + " ms");
		System.out.println("Ctest: " + ctime + " ms");
	}
}
