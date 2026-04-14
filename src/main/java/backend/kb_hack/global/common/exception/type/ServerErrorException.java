package backend.kb_hack.global.common.exception.type;

import backend.kb_hack.global.common.exception.enums.BadStatusCode;

public class ServerErrorException extends CustomException {
    public ServerErrorException(BadStatusCode badStatusCode) {
        super(badStatusCode);
    }
}
