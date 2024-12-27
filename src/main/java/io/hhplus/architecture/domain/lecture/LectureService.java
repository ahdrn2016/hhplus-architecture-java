package io.hhplus.architecture.domain.lecture;

import io.hhplus.architecture.support.exception.CustomException;
import io.hhplus.architecture.support.exception.ErrorCode;
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
        Lecture lecture = lectureRepository.findByIdWithLock(lectureId);

        // 정원 체크
        if (lecture.isLectureFull()) {
            throw new CustomException(ErrorCode.LECTURE_FULL);
        }

        // 특강 중복 신청 체크
        LectureEnroll enrolledLecture = lectureEnrollRepository.findByUserIdAndLectureId(userId, lectureId);
        if (enrolledLecture != null) {
            throw new CustomException(ErrorCode.ENROLLED_LECTURE);
        }

        // 특강 시간 중복 체크
        List<LectureEnroll> duplicateLectures = lectureEnrollRepository.findDuplicateLectures(lecture.getStartDtm(), lecture.getEndDtm());
        if (!duplicateLectures.isEmpty()) {
            throw new CustomException(ErrorCode.DUPLICATE_LECTURE);
        }

        // 정원 차감
        lecture.reduceCapacity();
        lectureRepository.save(lecture);

        return lectureEnrollRepository.save(LectureEnroll.of(userId, lectureId, lecture));
    }

    public List<LectureEnroll> enrolled(Long userId) {
        return lectureEnrollRepository.findByUserId(userId);
    }

}
