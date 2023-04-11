package com.kytokvinily.vinyls;

import com.kytokvinily.vinyls.dto.VinylDto;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
public class VinylsServiceApplicationTests {

    @Autowired
    private WebTestClient webTestClient;

    @Disabled
    @Test
    void postNewVinylThenCreated() {
        var expectedVinyl = new VinylDto("title1", 1989, "author1");

        webTestClient
                .post()
                .uri("/vinyls")
                .bodyValue(expectedVinyl)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(VinylDto.class).value(actualVinylDto -> {
                    assertThat(actualVinylDto).isNotNull();
                    assertThat(actualVinylDto.getTitle()).isEqualTo(expectedVinyl.getTitle());
                    assertThat(actualVinylDto.getYear()).isEqualTo(expectedVinyl.getYear());
                });
    }
}
