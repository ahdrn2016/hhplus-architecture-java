package java.hhplus.architecture.domain.lecture;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class LectureEnroll {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Long lectureId;

    private String title;
    private String instructor;
    private LocalDateTime startDtm;
    private LocalDateTime endDtm;

    @Builder
    private LectureEnroll(Long userId, Long lectureId, String title, String instructor, LocalDateTime startDtm, LocalDateTime endDtm) {
        this.userId = userId;
        this.lectureId = lectureId;
        this.title = title;
        this.instructor = instructor;
        this.startDtm = startDtm;
        this.endDtm = endDtm;
    }

    public static LectureEnroll of(Long userId, Long lectureId, Lecture lecture) {
        return LectureEnroll.builder()
                .userId(userId)
                .lectureId(lectureId)
                .title(lecture.getTitle())
                .instructor(lecture.getInstructor())
                .startDtm(lecture.getStartDtm())
                .endDtm(lecture.getEndDtm())
                .build();
    }

}
