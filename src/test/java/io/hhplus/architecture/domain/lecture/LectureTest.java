package io.hhplus.architecture.domain.lecture;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LectureTest {

    @Test
    void 전체_정원_수에서_1을_감소시킨다() {
        // given
        Lecture lecture = Lecture.builder()
                .capacity(30)
                .build();

        // when
        lecture.reduceCapacity();

        // then
        assertThat(lecture.getCapacity()).isEqualTo(29);
    }

}