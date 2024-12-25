package java.hhplus.architecture.infrastructure.lecture;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.hhplus.architecture.domain.lecture.Lecture;
import java.hhplus.architecture.domain.lecture.LectureRepository;
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

}
