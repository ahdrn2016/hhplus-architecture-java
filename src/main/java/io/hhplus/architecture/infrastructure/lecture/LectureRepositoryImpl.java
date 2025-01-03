package io.hhplus.architecture.infrastructure.lecture;

import io.hhplus.architecture.domain.lecture.Lecture;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import io.hhplus.architecture.domain.lecture.LectureRepository;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class LectureRepositoryImpl implements LectureRepository {

    private final LectureJpaRepository lectureJpaRepository;

    @Override
    public List<Lecture> findAfterNow() {
        return lectureJpaRepository.findAfterNow();
    }

    @Override
    public Lecture findByIdWithLock(Long lectureId) {
        return lectureJpaRepository.findByIdWithLock(lectureId);
    }

    @Override
    public Lecture save(Lecture lecture) {
        return lectureJpaRepository.save(lecture);
    }

    @Override
    public List<Lecture> saveAll(List<Lecture> lectures) {
        return lectureJpaRepository.saveAll(lectures);
    }

}
