package io.hhplus.architecture.domain.lecture;

import java.time.LocalDateTime;
import java.util.List;

public interface LectureEnrollRepository {

    List<LectureEnroll> findByUserId(Long userId);

    List<LectureEnroll> findDuplicateLectures(LocalDateTime startDtm, LocalDateTime endDtm);

    LectureEnroll save(LectureEnroll lectureEnroll);

    List<LectureEnroll> saveAll(List<LectureEnroll> enrolledLectures);

    LectureEnroll findByUserIdAndLectureId(Long userId, Long lectureId);
}
