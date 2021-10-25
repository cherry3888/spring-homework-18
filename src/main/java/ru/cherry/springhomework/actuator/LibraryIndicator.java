package ru.cherry.springhomework.actuator;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import ru.cherry.springhomework.dao.BookRepository;

@Component
@RequiredArgsConstructor
public class LibraryIndicator implements HealthIndicator {
    private final BookRepository bookRepository;

    @Override
    public Health health() {
        if (isLibraryLoaded()) {
            return Health.up()
                    .withDetail("message", "Книги загружены.")
                    .build();
        } else {
            return Health.down()
                    .status(Status.DOWN)
                    .withDetail("message", "В библиотеке книги отсутствуют!")
                    .build();
        }
    }

    private boolean isLibraryLoaded() {
        return !CollectionUtils.isEmpty(bookRepository.findAll());
    }
}
