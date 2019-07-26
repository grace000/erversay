package github.grace000.erversay.Constants;

public enum Paths {
  SIMPLE_GET("/simple_get"),
  METHODS_ONE("/method_options"),
  METHODS_TWO("/method_options2"),
  ECHO_BODY("/echo_body"),
  GET_WITH_BODY("/get_with_body"),
  REDIRECT("/redirect"),
  KITTY_IMAGE("/kitty_image"),
  UPDATE_BODY("/update_body");

    public final String path;

    Paths(String path) {
        this.path = path;
    }
}

