package backend.kb_hack.global.common.exception.type;

import backend.kb_hack.global.common.exception.enums.BadStatusCode;

public class BadRequestException extends CustomException {
    public BadRequestException(BadStatusCode badStatusCode) {
        super(badStatusCode);
    }
}