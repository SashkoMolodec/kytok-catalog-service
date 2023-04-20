package com.kytokvinily.catalog.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class VinylJsonTests {

    @Autowired
    private JacksonTester<VinylDto> json;

    @Test
    void testSerialize() throws Exception {
        var vinylDto = new VinylDto("title1", 1989, "author1");
        vinylDto.setId(1L);
        var jsonContent = json.write(vinylDto);
        assertThat(jsonContent).extractingJsonPathNumberValue("@.id")
                .isEqualTo(vinylDto.getId().intValue());
        assertThat(jsonContent).extractingJsonPathStringValue("@.title")
                .isEqualTo(vinylDto.getTitle());
        assertThat(jsonContent).extractingJsonPathNumberValue("@.year")
                .isEqualTo(vinylDto.getYear());
        assertThat(jsonContent).extractingJsonPathStringValue("@.author")
                .isEqualTo(vinylDto.getAuthor());
    }

    @Test
    void testDeserialize() throws Exception {
        var body = """
                {
                    "title": "title1",
                    "year": 1992,
                    "author" : "author1"
                }
                """;
        assertThat(json.parse(body))
                .usingRecursiveComparison()
                .isEqualTo(new VinylDto("title1",1992, "author1"));
    }
}
