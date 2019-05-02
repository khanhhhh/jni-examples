public class Pointer {
	static {
		System.loadLibrary("Pointer");
	}
	private static native long Cmalloc(long size);
	private static native void Cfree(long addr);
	private static native int Cget(long addr);
	private static native void Cset(long addr, int data);

	private Pointer BasePointer;
	private long OffsetAddress;
	public Pointer() {
		BasePointer = null;
		OffsetAddress = 0;
	}
	public Pointer(long size) {
		BasePointer = null;
		OffsetAddress = Cmalloc(size);
	}
	private Pointer(Pointer base, long offset) {
		BasePointer = base;
		OffsetAddress = offset;
	}
	protected void finalize() {
		if (null == BasePointer) {
			Cfree(OffsetAddress);
		}
	}
	public Pointer add(long offset) {
		if (null == BasePointer) {
			return new Pointer(this, offset);
		} else {
			return new Pointer(BasePointer, OffsetAddress + offset);
		}
	}
	private long getRawPointer() {
		if (null == BasePointer) {
			return OffsetAddress;
		} else {
			return BasePointer.OffsetAddress + OffsetAddress;
		}
	}
	public int getData() {
		return Cget(getRawPointer());
	}
	public void setData(int data) {
		Cset(getRawPointer(), data);
	}
}
