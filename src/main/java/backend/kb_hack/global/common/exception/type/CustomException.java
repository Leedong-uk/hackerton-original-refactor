package backend.kb_hack.global.common.exception.type;

import backend.kb_hack.global.common.exception.enums.BadStatusCode;
import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {
    private final BadStatusCode badStatusCode;

    public CustomException(BadStatusCode badStatusCode) {
        super(badStatusCode.getMessage());
        this.badStatusCode = badStatusCode;
    }
}
