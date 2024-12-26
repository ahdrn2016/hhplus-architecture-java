package io.hhplus.architecture.infrastructure.lecture;

import io.hhplus.architecture.domain.lecture.Lecture;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import io.hhplus.architecture.domain.lecture.LectureEnroll;
import io.hhplus.architecture.domain.lecture.LectureEnrollRepository;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class LectureEnrollRepositoryImpl implements LectureEnrollRepository {

    private final LectureEnrollJpaRepository lectureEnrollJpaRepository;

    @Override
    public List<Lecture> findEnrolledLecturesByUserId(Long userId) {
        return lectureEnrollJpaRepository.findEnrolledLecturesByUserId(userId);
    }

    @Override
    public List<LectureEnroll> findDuplicateLectures(LocalDateTime startDtm, LocalDateTime endDtm) {
        return lectureEnrollJpaRepository.findDuplicateLectures(startDtm, endDtm);
    }

    @Override
    public LectureEnroll save(LectureEnroll lectureEnroll) {
        return lectureEnrollJpaRepository.save(lectureEnroll);
    }

}