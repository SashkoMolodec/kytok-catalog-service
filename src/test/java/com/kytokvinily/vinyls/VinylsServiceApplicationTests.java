
package com.kytokvinily.vinyls;

import com.kytokvinily.vinyls.web.VinylDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@ActiveProfiles("integration")
public class VinylsServiceApplicationTests {

    @Autowired
    private WebTestClient webTestClient;

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

