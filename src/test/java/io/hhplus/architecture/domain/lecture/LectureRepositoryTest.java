package io.hhplus.architecture.domain.lecture;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

@Transactional
@ActiveProfiles("test")
@SpringBootTest
class LectureRepositoryTest {

    @Autowired
    private LectureRepository lectureRepository;

    @Test
    void 현재보다_이후에_시작하는_특강만_조회한다() {
        // given
        Lecture lecture1 = createLecture("특강1", "짱구", LocalDateTime.of(2024, 12, 26, 9, 0), LocalDateTime.of(2024, 12, 26, 11, 0), 30);
        Lecture lecture2 = createLecture("특강2", "철수", LocalDateTime.of(2024, 12, 29, 15, 0), LocalDateTime.of(2024, 12, 29, 17, 0), 30);
        Lecture lecture3 = createLecture("특강3", "맹구", LocalDateTime.of(2024, 12, 30, 10, 0), LocalDateTime.of(2024, 12, 30, 11, 0), 30);
        lectureRepository.saveAll(List.of(lecture1, lecture2, lecture3));

        // when
        List<Lecture> lectures = lectureRepository.findAfterNow();

        // then
        assertThat(lectures).hasSize(2)
                .extracting("title", "instructor")
                .containsExactlyInAnyOrder(
                        tuple("특강2", "철수"),
                        tuple("특강3", "맹구")
                );
    }

    private Lecture createLecture(String title, String instructor, LocalDateTime startDtm, LocalDateTime endDtm, int capacity) {
        return Lecture.builder()
                .title(title)
                .instructor(instructor)
                .startDtm(startDtm)
                .endDtm(endDtm)
                .capacity(capacity)
                .build();
    }

}