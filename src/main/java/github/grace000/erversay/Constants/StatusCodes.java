package github.grace000.erversay.Constants;

public enum StatusCodes {
    OK_STATUS("200 OK"),
    CREATED("201 Created"),
    NOT_FOUND_STATUS("404 Not Found"),
    NOT_ALLOWED_STATUS("405 Method Not Allowed"),
    REDIRECT_STATUS("301 Moved Permanently");

    public final String code;

    StatusCodes(String code) {
        this.code = code;
    }
}