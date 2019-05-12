public class CMatrix {
	static {
		System.loadLibrary("Matrix");
	}
	private static native long newMatrix(int height, int width, double[] data);
	private static native void delMatrix(long reference);
	private static native int getHeightMatrix(long reference);
	private static native int getWidthMatrix(long reference);
	private static native long additionMatrix(long reference1, long reference2);
	private static native long multiplicationMatrix(long reference1, long reference2);

	private long reference;
	
	private CMatrix(long reference) {
		this.reference = reference;
	}
	public CMatrix(int height, int width, double[] data) {
		reference = newMatrix(height, width, data);
	}
	protected void finalize() {
		delMatrix(reference);
	}
	public int getHeight() {
		return getHeightMatrix(reference);
	}
	public int getWidth() {
		return getWidthMatrix(reference);
	}
	public static CMatrix addition(CMatrix m1, CMatrix m2) {
		return new CMatrix(additionMatrix(m1.reference, m2.reference));
	}
	public static CMatrix multiplication(CMatrix m1, CMatrix m2) {
		return new CMatrix(multiplicationMatrix(m1.reference, m2.reference));
	}
}
