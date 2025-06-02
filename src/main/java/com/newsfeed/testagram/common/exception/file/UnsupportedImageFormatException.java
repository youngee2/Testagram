package com.newsfeed.testagram.common.exception.file;

/**
 * 지원하지 않는 이미지 형식일 경우 발생하는 예외입니다.
 *
 * 이미지 업로드 시 허용되지 않은 확장자(jpg, jpeg, png 이외)를 사용할 경우 발생합니다.
 *
 */
public class UnsupportedImageFormatException extends RuntimeException {

    public UnsupportedImageFormatException() {
        super("지원하지 않는 이미지 형식입니다. (jpg, jpeg, png만 허용됩니다)");
    }

    public UnsupportedImageFormatException(String message) {
        super(message);
    }
}
