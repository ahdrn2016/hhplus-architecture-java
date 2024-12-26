package io.hhplus.architecture.interfaces.api.lecture;

import io.hhplus.architecture.interfaces.api.lecture.dto.LectureEnrollResponse;
import io.hhplus.architecture.interfaces.api.lecture.dto.LectureResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import io.hhplus.architecture.domain.lecture.Lecture;
import io.hhplus.architecture.domain.lecture.LectureEnroll;
import io.hhplus.architecture.domain.lecture.LectureService;
import io.hhplus.architecture.interfaces.api.lecture.dto.LectureEnrollRequest;

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
    public LectureEnrollResponse enroll(@RequestBody LectureEnrollRequest request) {
        LectureEnroll enrolledLecture = lectureService.enroll(request.getUserId(), request.getLectureId());
        return LectureEnrollResponse.of(enrolledLecture);
    }

    /**
     * 특강 신청 여부 조회 API
     */
    @GetMapping("/enrolled?{userId}")
    public List<LectureEnrollResponse> enrolled(@PathVariable Long userId) {
        List<LectureEnroll> enrolledLectures = lectureService.enrolled(userId);

        return enrolledLectures.stream()
                .map(LectureEnrollResponse::of)
                .collect(Collectors.toList());
    }

}
