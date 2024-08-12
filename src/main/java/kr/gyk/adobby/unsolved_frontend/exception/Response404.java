package kr.gyk.adobby.unsolved_frontend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "404 (Not Found)")
public class Response404 extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public Response404 (String message) { super(message); }
    public Response404 () { super("404 (Not Found)"); }
}
