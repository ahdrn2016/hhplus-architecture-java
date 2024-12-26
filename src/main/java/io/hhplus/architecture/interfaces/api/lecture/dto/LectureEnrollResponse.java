package io.hhplus.architecture.interfaces.api.lecture.dto;

import lombok.Builder;

import io.hhplus.architecture.domain.lecture.LectureEnroll;
import java.time.LocalDateTime;

public class LectureEnrollResponse {

    private Long userId;
    private Long lectureId;
    private String title;
    private String instructor;
    private LocalDateTime startDtm;
    private LocalDateTime endDtm;
    private int capacity;

    @Builder
    private LectureEnrollResponse(Long userId, Long lectureId,String title, String instructor, LocalDateTime startDtm, LocalDateTime endDtm, int capacity) {
        this.userId = userId;
        this.lectureId = lectureId;
        this.title = title;
        this.instructor = instructor;
        this.startDtm = startDtm;
        this.endDtm = endDtm;
        this.capacity = capacity;
    }

    public static LectureEnrollResponse of(LectureEnroll lecture) {
        return LectureEnrollResponse.builder()
                .userId(lecture.getUserId())
                .lectureId(lecture.getLectureId())
                .title(lecture.getTitle())
                .instructor(lecture.getInstructor())
                .startDtm(lecture.getStartDtm())
                .endDtm(lecture.getEndDtm())
                .build();
    }
}
