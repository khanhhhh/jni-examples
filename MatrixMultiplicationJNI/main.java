public class main {
	public static void main(String[] args) {
		JavaMatrix m = new JavaMatrix(2, 2, new double[]{1,2,3,4});
		System.out.println(JavaMatrix.matmul(m, m));
	}
}
