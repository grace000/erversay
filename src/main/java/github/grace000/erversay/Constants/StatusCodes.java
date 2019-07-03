package github.grace000.erversay.Constants;

public enum StatusCodes {
    DEFAULT_STATUS("200 OK"),
    NOT_FOUND_STATUS("404 Not Found");

    public String statusCode;

    StatusCodes(String statusCode){
        this.statusCode = statusCode;
    }

    public String getStatusCode(){
        return this.statusCode;
    }
}




