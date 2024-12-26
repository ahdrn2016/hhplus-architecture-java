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
    public Lecture findById(Long lectureId) {
        return lectureJpaRepository.findById(lectureId).orElse(null);
    }

    @Override
    public Lecture save(Lecture lecture) {
        return lectureJpaRepository.save(lecture);
    }

}
