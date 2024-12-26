package io.hhplus.architecture.domain.lecture;

import java.util.List;

public interface LectureRepository {

    List<Lecture> findAfterNow();

    Lecture findByIdWithLock(Long lectureId);

    Lecture save(Lecture lecture);

    List<Lecture> saveAll(List<Lecture> lectures);
}
