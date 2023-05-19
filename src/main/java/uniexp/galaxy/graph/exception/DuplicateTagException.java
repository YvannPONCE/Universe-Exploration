package uniexp.galaxy.graph.exception;

@SuppressWarnings("serial")
public class DuplicateTagException extends Exception {

	public DuplicateTagException(String tag) {
		super(tag);
	}
}
