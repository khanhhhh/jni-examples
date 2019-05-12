import java.util.Random;
import java.io.PrintWriter;
public class main {
	private static long Jtest(int size, double[] s1, double[] s2) {
		Matrix m1 = new Matrix(size, size, s1);
		Matrix m2 = new Matrix(size, size, s2);

		long tic = System.nanoTime();
		Matrix m3 = Matrix.multiplication(m1, m2);
		long toc = System.nanoTime();
		return toc-tic;
	}
	private static long Ctest(int size, double[] s1, double[] s2) {
		CMatrix m1 = new CMatrix(size, size, s1);
		CMatrix m2 = new CMatrix(size, size, s2);

		long tic = System.nanoTime();
		CMatrix m3 = CMatrix.multiplication(m1, m2);
		long toc = System.nanoTime();
		return toc-tic;
	}
	public static double[] test(Random r, int size) {
		double[] s1 = new double[size*size];
		double[] s2 = new double[size*size];
		for (int i=0; i<size*size; i++) {
			s1[i] = r.nextDouble();
			s2[i] = r.nextDouble();
		}
		double jtime = Jtest(size, s1, s2);
		double ctime = Ctest(size, s1, s2);
		return new double[]{jtime/1000000, ctime/1000000};
	}
	public static void main(String[] args) {
		Random r = new Random(1234);
		for (int size=1; size <= 1024; size += 50) {
			double[] res = test(r, size);
			System.out.println(size + "," + res[0] + "," + res[1]);
		}
	}
}
