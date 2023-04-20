package com.kytokvinily.vinyls.domain;

import com.kytokvinily.vinyls.config.DataConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;
import org.springframework.data.jdbc.core.JdbcAggregateTemplate;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.Assertions.assertThat;


import java.util.Optional;

@DataJdbcTest
@Import(DataConfig.class)
@AutoConfigureTestDatabase(
        replace = AutoConfigureTestDatabase.Replace.NONE
)
@ActiveProfiles("integration")
public class VinylRepositoryJdbcTests {

    @Autowired
    VinylRepository vinylRepository;

    @Autowired
    private JdbcAggregateTemplate jdbcAggregateTemplate;

    @Test
    void findVinylByTitleAndYearWhenExisting() {
        var title = "title2";
        var year = 1989;
        var vinyl = Vinyl.of(title, "author1", year);
        jdbcAggregateTemplate.insert(vinyl);

        Optional<Vinyl> actualVinyl = vinylRepository.findByTitleAndYear(title, year);

        assertThat(actualVinyl).isPresent();
        assertThat(actualVinyl.get().year()).isEqualTo(actualVinyl.get().year());
        assertThat(actualVinyl.get().title()).isEqualTo(actualVinyl.get().title());
    }
}
