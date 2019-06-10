package github.grace000.erversay;

public class Response {
    private int code = 200;
    public String protocol = "HTTP/1.1";
    private String status = "OK";

    public Response withCode(int code){
        this.code = code;
        return this;
    }

    public Response withStatus(String status){
        this.status = status;
        return this;
    }

    public String build(){
        return "HTTP/1.1 " + Integer.toString(this.code) + " " + this.status + "\r\nContent-Length: 0\r\n\r\n";
    }

}
