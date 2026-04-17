package backend.kb_hack.domain.sos.service;

import backend.kb_hack.domain.sos.mapper.SosImageMapper;
import backend.kb_hack.global.common.exception.enums.BadStatusCode;
import backend.kb_hack.global.common.exception.type.BadRequestException;
import backend.kb_hack.global.common.exception.type.ServerErrorException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StorageService {

    private final SosImageMapper sosImageMapper;

    public List<String> uploadAll(List<String> files, Long sosId) {
        if (files == null || files.isEmpty()) return List.of();

        return files.stream().map(file -> {
            try {
                if (file == null || file.isEmpty()) {
                    throw new BadRequestException(BadStatusCode.INVALID_FILE_UPLOAD_EXCEPTION);
                }

                // 그냥 파일명을 key로 사용
                String key = "sos/" + UUID.randomUUID() + "_" + file;
                return key;

            } catch (ServerErrorException e) {
                throw e;
            } catch (Exception e) {
                throw new ServerErrorException(BadStatusCode.FAIL_TO_PROCESSING_FILE_UPLOAD_EXCEPTION);
            }
        }).collect(Collectors.toList());
    }

    public void delete(Long sosImageId) {
        try {
            sosImageMapper.deleteById(sosImageId);
        } catch (Exception e) {
            throw new ServerErrorException(BadStatusCode.FILE_DELETE_FAILED_EXCEPTION);
        }
    }

}
