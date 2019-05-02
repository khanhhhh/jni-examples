class CDouble {
	static {
		System.loadLibrary("CDouble");
	}
	private static native long newDOUBLE(double value);
	private static native void delDOUBLE(long CObj);
	private static native String toStringDOUBLE(long CObj);
	private static native void addDOUBLE(long CObj1, long CObj2, long CObj3);

	private long address;
	public CDouble(double value) {
		address = newDOUBLE(value);
	}
	protected void finalize() {
		delDOUBLE(address);
	}
	public String toString() {
		return toStringDOUBLE(address);
	}
	public static CDouble add(CDouble a, CDouble b) {
		CDouble c = new CDouble(0);
		addDOUBLE(a.address, b.address, c.address);
		return c;
	}
}
public class CObjectJNI {
	public static void main(String[] args) {
		CDouble a = new CDouble(1.5);
		CDouble b = new CDouble(3.5);
		CDouble c = CDouble.add(a, b);
		System.out.println(a);
		System.out.println(b);
		System.out.println(c);
	}
}
