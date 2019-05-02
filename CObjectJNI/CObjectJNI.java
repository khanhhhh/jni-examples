class CDouble {
	static {
		System.loadLibrary("CDouble");
	}
	private static native long newDOUBLE(double value);
	private static native void delDOUBLE(long CObj);
	private static native String toStringDOUBLE(long CObj);
	private static native void addDOUBLE(long COut, long CObj1, long CObj2);

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
		CDouble out = new CDouble(0);
		addDOUBLE(out.address, a.address, b.address);
		return out;
	}
}
public class CObjectJNI {
	public static void main(String[] args) {
		CDouble a = new CDouble(1.5);
		System.out.println(a);
	}
}
