package dao.exception;

public class DalException extends RuntimeException {
    public DalException() {
    }
    
    public DalException(String msg) {
        super(msg);
    }
    
    public DalException(Throwable exception) {
        super(exception);
    }
    
    public DalException(String mag, Exception exception) {
        super(mag, exception);
    }
}
