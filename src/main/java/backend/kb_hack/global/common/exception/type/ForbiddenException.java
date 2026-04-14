package backend.kb_hack.global.common.exception.type;

import backend.kb_hack.global.common.exception.enums.BadStatusCode;

public class ForbiddenException extends CustomException {
    public ForbiddenException(BadStatusCode badStatusCode) {
        super(badStatusCode);
    }
}