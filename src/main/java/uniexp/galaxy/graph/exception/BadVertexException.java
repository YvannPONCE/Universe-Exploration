package uniexp.galaxy.graph.exception;

public class BadVertexException extends IllegalArgumentException {
    public BadVertexException(String tag) {
        super(tag);
    }
}
