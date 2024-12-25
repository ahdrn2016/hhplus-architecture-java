package java.hhplus.architecture.domain.lecture;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class LectureService {

    private final LectureRepository lectureRepository;
    private final LectureEnrollRepository lectureEnrollRepository;

    public List<Lecture> list() {
        return lectureRepository.findAfterNow();
    }

    @Transactional
    public LectureEnroll enroll(Long userId, Long lectureId) {
        Lecture lecture = lectureRepository.findById(lectureId);

        // 특강 시간 중복 체크
        List<LectureEnroll> duplicateLectures = lectureEnrollRepository.findDuplicateLectures(lecture.getStartDtm(), lecture.getEndDtm());
        if (duplicateLectures.isEmpty()) {
            throw new IllegalArgumentException("이미 신청한 특강과 시간이 중복됩니다.");
        }

        // 정원 차감
        lecture.reduceCapacity();
        lectureRepository.save(lecture);

        return lectureEnrollRepository.save(LectureEnroll.of(userId, lectureId, lecture));
    }

    public List<Lecture> enrolled(Long userId) {
        return lectureEnrollRepository.findEnrolledLecturesByUserId(userId);
    }

}
