package Exceptions;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String message) {
        super("Error from Lambda School APP" + message);
    }
}
