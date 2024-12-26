package io.hhplus.architecture.domain.lecture;

import java.util.List;

public interface LectureRepository {

    List<Lecture> findAfterNow();

    Lecture findById(Long lectureId);

    Lecture save(Lecture lecture);

}
