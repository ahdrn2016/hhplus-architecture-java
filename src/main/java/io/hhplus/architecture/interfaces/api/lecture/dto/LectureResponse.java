package io.hhplus.architecture.interfaces.api.lecture.dto;

import lombok.Builder;

import io.hhplus.architecture.domain.lecture.Lecture;
import java.time.LocalDateTime;

public class LectureResponse {

    private String title;
    private String instructor;
    private LocalDateTime startDtm;
    private LocalDateTime endDtm;
    private int capacity;

    @Builder
    private LectureResponse(String title, String instructor, LocalDateTime startDtm, LocalDateTime endDtm, int capacity) {
        this.title = title;
        this.instructor = instructor;
        this.startDtm = startDtm;
        this.endDtm = endDtm;
        this.capacity = capacity;
    }

    public static LectureResponse of(Lecture lecture) {
        return LectureResponse.builder()
                .title(lecture.getTitle())
                .instructor(lecture.getInstructor())
                .startDtm(lecture.getStartDtm())
                .endDtm(lecture.getEndDtm())
                .capacity(lecture.getCapacity())
                .build();
    }

}
