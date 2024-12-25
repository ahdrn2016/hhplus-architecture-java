package java.hhplus.architecture.domain.lecture;

import java.time.LocalDateTime;
import java.util.List;

public interface LectureEnrollRepository {

    List<Lecture> findEnrolledLecturesByUserId(Long userId);

    List<LectureEnroll> findDuplicateLectures(LocalDateTime startDtm, LocalDateTime endDtm);

    LectureEnroll save(LectureEnroll lectureEnroll);
}
