package java.hhplus.architecture.interfaces.api.lecture;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.hhplus.architecture.domain.lecture.Lecture;
import java.hhplus.architecture.domain.lecture.LectureEnroll;
import java.hhplus.architecture.domain.lecture.LectureService;
import java.hhplus.architecture.interfaces.api.lecture.dto.LectureEnrollRequest;
import java.hhplus.architecture.interfaces.api.lecture.dto.LectureEnrollResponse;
import java.hhplus.architecture.interfaces.api.lecture.dto.LectureResponse;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/lectures")
public class LectureController {

    private final LectureService lectureService;

    /**
     * 특강 신청 가능 목록 API
     */
    @GetMapping("/list")
    public List<LectureResponse> list() {
        List<Lecture> lectures = lectureService.list();

        return lectures.stream()
                .map(LectureResponse::of)
                .collect(Collectors.toList());
    }

    /**
     * 특강 신청 API
     */
    @PostMapping("/enroll")
    public LectureEnrollResponse enroll(LectureEnrollRequest request) {
        LectureEnroll enrolledLecture = lectureService.enroll(request.getUserId(), request.getLectureId());
        return LectureEnrollResponse.of(enrolledLecture);
    }

    /**
     * 특강 신청 여부 조회 API
     */
    @GetMapping("/enrolled?{userId}")
    public List<LectureResponse> enrolled(@PathVariable Long userId) {
        List<Lecture> enrolledLectures = lectureService.enrolled(userId);

        return enrolledLectures.stream()
                .map(LectureResponse::of)
                .collect(Collectors.toList());
    }

}