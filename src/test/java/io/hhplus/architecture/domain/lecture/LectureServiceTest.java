package io.hhplus.architecture.domain.lecture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest
class LectureServiceTest {

    @Autowired
    private LectureService lectureService;


}