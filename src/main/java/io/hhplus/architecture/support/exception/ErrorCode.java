package io.hhplus.architecture.support.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    LECTURE_FULL("LECTURE_FULL", "특강 정원을 초과하여 신청할 수 없습니다."),
    ENROLLED_LECTURE("ENROLLED_LECTURE", "이미 신청한 특강입니다."),
    DUPLICATE_LECTURE("DUPLICATE_LECTURE", "이미 신청한 특강과 시간이 중복됩니다.");

    private final String code;
    private final String message;

}
