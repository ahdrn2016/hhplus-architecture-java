package java.hhplus.architecture.domain.lecture;

import java.util.List;

public interface LectureRepository {

    List<Lecture> findAfterNow();

    Lecture findById(Long lectureId);
}