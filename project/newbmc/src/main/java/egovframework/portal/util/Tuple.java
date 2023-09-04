package egovframework.portal.util;

public class Tuple<First, Second> {

	private First first;
	private Second second;

	public Tuple(First first, Second second) {
		this.first = first;
		this.second = second;
	}

	public First getFirst() {
		return first;
	}

	public Second getSecond() {
		return second;
	}

	public void setFirst(First first) {
		this.first = first;
	}

	public void setSecond(Second second) {
		this.second = second;
	}

}
