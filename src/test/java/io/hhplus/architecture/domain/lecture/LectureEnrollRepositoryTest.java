package io.hhplus.architecture.domain.lecture;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest
class LectureEnrollRepositoryTest {

    @Autowired
    private LectureEnrollRepository lectureEnrollRepository;

    @Test
    void 신청하려는_특강이_이미_등록한_특강_시간과_중복되는지_체크한다() {
        // given
        LectureEnroll enrolledLecture1 = createLecture(1L, 1L, "특강1", "짱구", LocalDateTime.of(2024, 12, 26, 9, 0), LocalDateTime.of(2024, 12, 26, 11, 0));
        LectureEnroll enrolledLecture2 = createLecture(1L, 2L, "특강2", "철수", LocalDateTime.of(2024, 12, 26, 15, 0), LocalDateTime.of(2024, 12, 26, 17, 0));
        lectureEnrollRepository.saveAll(List.of(enrolledLecture1, enrolledLecture2));

        LocalDateTime startDtm = LocalDateTime.of(2024, 12, 26, 13, 0);
        LocalDateTime endDtm = LocalDateTime.of(2024, 12, 26, 16, 0);

        // when
        List<LectureEnroll> duplicateLectures = lectureEnrollRepository.findDuplicateLectures(startDtm, endDtm);

        // then
        assertThat(duplicateLectures).hasSize(1)
                .extracting("title", "instructor")
                .contains(
                        tuple("특강2", "철수")
                );
    }

    private LectureEnroll createLecture(Long userId, Long lectureId, String title, String instructor, LocalDateTime startDtm, LocalDateTime endDtm) {
        return LectureEnroll.builder()
                .userId(userId)
                .lectureId(lectureId)
                .title(title)
                .instructor(instructor)
                .startDtm(startDtm)
                .endDtm(endDtm)
                .build();
    }

}