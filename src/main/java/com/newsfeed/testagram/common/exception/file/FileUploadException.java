package com.newsfeed.testagram.common.exception.file;

/**
 * 파일 업로드 중 서버 내부 오류가 발생했을 때 발생하는 예외입니다.
 *
 * 보통 파일 저장 경로 오류, 디스크 용량 부족, 입출력 예외 등의 경우 발생합니다.
 */
public class FileUploadException extends RuntimeException {

    public FileUploadException() {
        super("파일 업로드에 실패했습니다. 잠시 후 다시 시도해주세요.");
    }

    public FileUploadException(String message) {
        super(message);
    }
}
