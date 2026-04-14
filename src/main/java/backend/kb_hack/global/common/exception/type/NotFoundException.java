package backend.kb_hack.global.common.exception.type;

import backend.kb_hack.global.common.exception.enums.BadStatusCode;

public class NotFoundException extends CustomException {
    public NotFoundException(BadStatusCode badStatusCode) {
        super(badStatusCode);
    }
}