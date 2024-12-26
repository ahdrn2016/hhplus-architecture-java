package io.hhplus.architecture.domain.lecture;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Lecture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String instructor;
    private LocalDateTime startDtm;
    private LocalDateTime endDtm;
    private int capacity;

    @Builder
    public Lecture(Long id, String title, String instructor, LocalDateTime startDtm, LocalDateTime endDtm, int capacity) {
        this.id = id;
        this.title = title;
        this.instructor = instructor;
        this.startDtm = startDtm;
        this.endDtm = endDtm;
        this.capacity = capacity;
    }

    public void reduceCapacity() {
        this.capacity = this.capacity - 1;
    }

    public boolean isLectureFull() {
        return this.capacity <= 0;
    }
}
