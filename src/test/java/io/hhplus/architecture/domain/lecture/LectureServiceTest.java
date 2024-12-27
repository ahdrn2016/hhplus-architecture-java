package io.hhplus.architecture.domain.lecture;

import io.hhplus.architecture.support.exception.CustomException;
import io.hhplus.architecture.support.exception.ErrorCode;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("test")
@SpringBootTest
class LectureServiceTest {

    @Autowired
    private LectureService lectureService;

    @Autowired
    private LectureRepository lectureRepository;

    @Autowired
    private LectureEnrollRepository lectureEnrollRepository;

    @Test
    void 동시에_40명이_특강_신청_시_30명만_특강_신청에_성공한다() throws InterruptedException {
        // given
        Lecture lecture = createLecture("특강1", "짱구", LocalDateTime.of(2024, 12, 31, 10, 0), LocalDateTime.of(2024, 12, 28, 13, 0), 30);
        lectureRepository.save(lecture);

        int threadCount = 40;
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        CountDownLatch latch = new CountDownLatch(threadCount);

        AtomicInteger successCount = new AtomicInteger();
        AtomicInteger failCount = new AtomicInteger();

        // when
        for(int i = 1; i <= threadCount; i++) {
            final Long userId = (long) i;
            executorService.submit(() -> {
                try {
                    LectureEnroll enroll = lectureService.enroll(userId, lecture.getId());
                    if (enroll != null) {
                        successCount.incrementAndGet();
                    }
                } catch (IllegalArgumentException e) {
                    failCount.incrementAndGet();
                } finally {
                    latch.countDown();
                }
            });
        }
        latch.await();
        executorService.shutdown();

        // then
        assertEquals(30, successCount.get());
        assertEquals(10, failCount.get());
    }

    @Test
    void 유저는_이미_신청한_동일한_특강을_신청_할_수_없다() {
        // given
        Long userId = 1L;
        Lecture lecture = createLecture("특강1", "짱구", LocalDateTime.of(2024, 12, 31, 10, 0), LocalDateTime.of(2024, 12, 28, 13, 0), 30);
        lectureRepository.save(lecture);
        lectureEnrollRepository.save(LectureEnroll.of(userId, lecture.getId(), lecture));

        // when // then
        assertThatThrownBy(() -> lectureService.enroll(userId, lecture.getId()))
                .isInstanceOf(CustomException.class)
                .hasMessage(ErrorCode.ENROLLED_LECTURE.getMessage());
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